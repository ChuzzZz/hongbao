package com.deeplab.topup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.ShowInfoDAO;
import entity.ShowInfo;
import jxl.CellView;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

@Controller
public class AdminShowController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(value = { "/addshow", "/addShow" })
	public String addShow() {
		return "edit_showinfo";
	}
	
	@ResponseBody
	@RequestMapping(value = {"/addshowinfo","/addShowInfo"})
	public Map<String, Object> addShowInfo(String show_name, String performer, String department, String start_time) {
		Timestamp ts = Timestamp.valueOf(start_time);
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(ShowInfoDAO.addShowInfo(show_name, performer, department, ts, jdbcTemplate)) {
			map.put("msg", "success");
		}else {
			map.put("msg", "failed");
		}
		return map;
	}

	@RequestMapping(value = { "/admingetshowlist", "/adminGetShowlist" })
	public String adminGetShowlist(Model model) {
		List<ShowInfo> l = ShowInfoDAO.getAllShowInfoByTimeOrder(jdbcTemplate);
		model.addAttribute("showlist", l);
		return "admin_showlist";
	}

	@RequestMapping(value = "/adjustOrder")
	public String adjustOrder(int before, int after, Model model) {
		ShowInfoDAO.swapShowTime(before, after, jdbcTemplate);
		List<ShowInfo> l = ShowInfoDAO.getAllShowInfoByTimeOrder(jdbcTemplate);
		model.addAttribute("showlist", l);
		return "admin_showlist";
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/export")
	public ResponseEntity<byte[]> download(HttpServletResponse response, HttpServletRequest request)
			throws IOException {
		List<ShowInfo> s = ShowInfoDAO.getAllShowInfoByTimeOrder(jdbcTemplate);
		// 新建Excel文件
		String filePath = "";
		try {
			filePath = request.getRealPath("list.xls");
			File myFilePath = new File(filePath);
			if (!myFilePath.exists())
				myFilePath.createNewFile();
			FileWriter resultFile = new FileWriter(myFilePath);
			PrintWriter myFile = new PrintWriter(resultFile);
			resultFile.close();
			// 用JXL向新建的文件中添加内容
			OutputStream outf = new FileOutputStream(filePath);
			jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(outf);
			jxl.write.WritableSheet ws = wwb.createSheet("sheettest", 0);
			WritableCellFormat wcf = new WritableCellFormat(NumberFormats.TEXT);
			CellView cv = new CellView(); // 定义一个列显示样式
			cv.setFormat(wcf);// 把定义的单元格格式初始化进去
			cv.setSize(10 * 600);// 设置列宽度（不设置的话是0，不会显示）
			ws.setColumnView(3, cv);// 设置工作表中第4列的样式
			int i = 0;
			int j = 0;
			try {
				ws.addCell(new Label(0, 0, "节目名"));
				ws.addCell(new Label(1, 0, "表演者"));
				ws.addCell(new Label(2, 0, "表演部门"));
				ws.addCell(new Label(3, 0, "表演时间"));
			} catch (RowsExceededException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			for (int l = 0; l < s.size(); l++) {
				try {
					ws.addCell(new Label(0, j + i + 1, s.get(l).getShow_name()));
					ws.addCell(new Label(1, j + i + 1, s.get(l).getPerformer()));
					ws.addCell(new Label(2, j + i + 1, s.get(l).getDepartment()));
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					Date date = null;
					try {
						date = sdf.parse(s.get(l).getStart_time().toLocaleString());
						System.out.println(date.getDate());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					jxl.write.DateTime cell = new jxl.write.DateTime(3, j + i + 1, date);
					// ws.addCell(cell);

					ws.addCell(new Label(3, j + i + 1, s.get(l).getStart_time().toLocaleString()));
				} catch (RowsExceededException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
			}

			wwb.write();
			wwb.close();

			HttpHeaders headers = new HttpHeaders();

			headers.setContentDispositionFormData("attachment", "list.xls");
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(myFilePath), headers, HttpStatus.CREATED);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/ima")
	protected String ima(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 检测是否为多媒体上传

		// 上传文件存储目录
		String UPLOAD_DIRECTORY = "img";

		// 上传配置
		int MEMORY_THRESHOLD = 1024 * 1024; // 1MB
		int MAX_FILE_SIZE = 1024 * 1024 * 5; // 5MB
		int MAX_REQUEST_SIZE = 1024 * 1024 * 5; // 5MB

		/**
		 * 上传数据及保存文件
		 */
		if (!ServletFileUpload.isMultipartContent(request)) {
			// 如果不是则停止
			PrintWriter writer = response.getWriter();
			writer.println("Error: 表单必须包含 enctype=multipart/form-data");
			writer.flush();
			return "adminGetShowlist";
		}
		PrintWriter out = response.getWriter();
		// 配置上传参数
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// 设置临时存储目录
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);

		// 设置最大文件上传值
		upload.setFileSizeMax(MAX_FILE_SIZE);

		// 设置最大请求值 (包含文件和表单数据)
		upload.setSizeMax(MAX_REQUEST_SIZE);

		// 中文处理
		upload.setHeaderEncoding("UTF-8");

		// 构造临时路径来存储上传的文件
		// 这个路径相对当前应用的目录
		String uploadPath = request.getRealPath("./") + File.separator + UPLOAD_DIRECTORY;

		String filePath = "";
		// 如果目录不存在则创建
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		try {
			// 解析请求的内容提取文件数据
			List<FileItem> formItems = upload.parseRequest(request);

			if (formItems != null && formItems.size() > 0) {
				// 迭代表单数据
				for (FileItem item : formItems) {
					// 处理不在表单中的字段
					if (!item.isFormField()) {
						String fileName = new File(item.getName()).getName();
						filePath = uploadPath + File.separator + fileName;
						System.out.print(filePath);
						File storeFile = new File(filePath);
						// 在控制台输出文件的上传路径
						// 保存文件到硬盘
						item.write(storeFile);
					}
				}
			}

			jxl.Workbook readwb = null;
			try {
				InputStream instream = new FileInputStream(filePath);
				readwb = Workbook.getWorkbook(instream);
				// 获取第一张Sheet表
				Sheet readsheet = readwb.getSheet(0);
				int rsColumns = 4, rsRows = readsheet.getRows();// 获取Sheet表中所包含的总行数
				// 获取指定单元格的对象引用
				if (!ShowInfoDAO.AllClear(jdbcTemplate))
					return "administrator";
				for (int i = 1; i < rsRows; i++) {
					String showname = readsheet.getCell(0, i).getContents();
					String performer = readsheet.getCell(1, i).getContents();
					String department = readsheet.getCell(2, i).getContents();
					String t = readsheet.getCell(3, i).getContents();
					Timestamp time = null;
					try {
						time = Timestamp.valueOf(t);
					} catch (Exception e) {
						e.printStackTrace();
					}
					ShowInfoDAO.addShowInfo(showname, performer, department, time, jdbcTemplate);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				readwb.close();
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return "redirect:/admingetshowlist";
	}
}
