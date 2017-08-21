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
		// �½�Excel�ļ�
		String filePath = "";
		try {
			filePath = request.getRealPath("list.xls");
			File myFilePath = new File(filePath);
			if (!myFilePath.exists())
				myFilePath.createNewFile();
			FileWriter resultFile = new FileWriter(myFilePath);
			PrintWriter myFile = new PrintWriter(resultFile);
			resultFile.close();
			// ��JXL���½����ļ����������
			OutputStream outf = new FileOutputStream(filePath);
			jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(outf);
			jxl.write.WritableSheet ws = wwb.createSheet("sheettest", 0);
			WritableCellFormat wcf = new WritableCellFormat(NumberFormats.TEXT);
			CellView cv = new CellView(); // ����һ������ʾ��ʽ
			cv.setFormat(wcf);// �Ѷ���ĵ�Ԫ���ʽ��ʼ����ȥ
			cv.setSize(10 * 600);// �����п�ȣ������õĻ���0��������ʾ��
			ws.setColumnView(3, cv);// ���ù������е�4�е���ʽ
			int i = 0;
			int j = 0;
			try {
				ws.addCell(new Label(0, 0, "��Ŀ��"));
				ws.addCell(new Label(1, 0, "������"));
				ws.addCell(new Label(2, 0, "���ݲ���"));
				ws.addCell(new Label(3, 0, "����ʱ��"));
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
		// ����Ƿ�Ϊ��ý���ϴ�

		// �ϴ��ļ��洢Ŀ¼
		String UPLOAD_DIRECTORY = "img";

		// �ϴ�����
		int MEMORY_THRESHOLD = 1024 * 1024; // 1MB
		int MAX_FILE_SIZE = 1024 * 1024 * 5; // 5MB
		int MAX_REQUEST_SIZE = 1024 * 1024 * 5; // 5MB

		/**
		 * �ϴ����ݼ������ļ�
		 */
		if (!ServletFileUpload.isMultipartContent(request)) {
			// ���������ֹͣ
			PrintWriter writer = response.getWriter();
			writer.println("Error: ��������� enctype=multipart/form-data");
			writer.flush();
			return "adminGetShowlist";
		}
		PrintWriter out = response.getWriter();
		// �����ϴ�����
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// �����ڴ��ٽ�ֵ - �����󽫲�����ʱ�ļ����洢����ʱĿ¼��
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// ������ʱ�洢Ŀ¼
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);

		// ��������ļ��ϴ�ֵ
		upload.setFileSizeMax(MAX_FILE_SIZE);

		// �����������ֵ (�����ļ��ͱ�����)
		upload.setSizeMax(MAX_REQUEST_SIZE);

		// ���Ĵ���
		upload.setHeaderEncoding("UTF-8");

		// ������ʱ·�����洢�ϴ����ļ�
		// ���·����Ե�ǰӦ�õ�Ŀ¼
		String uploadPath = request.getRealPath("./") + File.separator + UPLOAD_DIRECTORY;

		String filePath = "";
		// ���Ŀ¼�������򴴽�
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		try {
			// ���������������ȡ�ļ�����
			List<FileItem> formItems = upload.parseRequest(request);

			if (formItems != null && formItems.size() > 0) {
				// ����������
				for (FileItem item : formItems) {
					// �����ڱ��е��ֶ�
					if (!item.isFormField()) {
						String fileName = new File(item.getName()).getName();
						filePath = uploadPath + File.separator + fileName;
						System.out.print(filePath);
						File storeFile = new File(filePath);
						// �ڿ���̨����ļ����ϴ�·��
						// �����ļ���Ӳ��
						item.write(storeFile);
					}
				}
			}

			jxl.Workbook readwb = null;
			try {
				InputStream instream = new FileInputStream(filePath);
				readwb = Workbook.getWorkbook(instream);
				// ��ȡ��һ��Sheet��
				Sheet readsheet = readwb.getSheet(0);
				int rsColumns = 4, rsRows = readsheet.getRows();// ��ȡSheet������������������
				// ��ȡָ����Ԫ��Ķ�������
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
