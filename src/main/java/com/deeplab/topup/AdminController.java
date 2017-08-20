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
import java.util.List;

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

import dao.LuckyMoneyDAO;
import dao.LuckyMoneyTransactionDAO;
import dao.RedPackageDAO;
import dao.ShowInfoDAO;
import entity.LuckyMoneyTransaction;
import entity.ShowInfo;
import jxl.CellView;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import myThread.LuckyRainThread;

@Controller
public class AdminController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value = "/admin")
	public String admin() {
		return "administrator";
	}
	
}
