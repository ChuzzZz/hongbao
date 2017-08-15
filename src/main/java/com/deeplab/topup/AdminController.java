package com.deeplab.topup;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.LuckyMoneyDAO;
import dao.LuckyMoneyTransactionDAO;
import dao.ShowInfoDAO;
import dao.TipTransactionDAO;
import entity.LuckyMoneyTransaction;
import entity.ShowInfo;
import entity.TipTransaction;
import myThread.LuckyRainThread;

@Controller
public class AdminController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value = "/admin")
	public String admin(Model model) {
		if (LuckyMoneyDAO.getTotalByRound(1, jdbcTemplate)!=0){model.addAttribute("round1",0);}
		if (LuckyMoneyDAO.getTotalByRound(2, jdbcTemplate)!=0){model.addAttribute("round2",0);}
		if (LuckyMoneyDAO.getTotalByRound(3, jdbcTemplate)!=0){model.addAttribute("round3",0);}
		long k = LuckyMoneyDAO.getTotalByRound(1, jdbcTemplate);
		System.out.println(k);
		return "administrator";
	}
	
	@RequestMapping(value = {"/startluckyrain","/startLuckyrain"})
	public void startLuckyrain(String round) {
		int i = Integer.parseInt(round);
		LuckyRainThread t = new LuckyRainThread();
		t.setJdbcTemplate(jdbcTemplate);
		t.setRound(i);
		t.setFlag(true);
		t.start();
	}
	
	@RequestMapping(value = {"/addshow","/addShow"})
	public String addShow() {
		return "edit_showinfo";
	}
	
	@RequestMapping(value = {"/addshowinfo","/addShowInfo"})
	public String addShowInfo(String show_name, String performer, String department, String start_time, Model model) {
		Timestamp ts = Timestamp.valueOf(start_time);
		if (department.equals("Soft")) {department="软件学院";}
		if (department.equals("Teda")) {department="泰达学院";}
		if (department.equals("Study")) {department="电竞学院";}
		if(ShowInfoDAO.addShowInfo( show_name, performer, department, ts, jdbcTemplate)) {
			return "addshow_result";
		}else {
			model.addAttribute("result", "节目信息填写错误！");
			return "edit_showinfo";
		}
	}
	
	@RequestMapping(value = {"/admingetshowlist","/adminGetShowlist"})
	public String adminGetShowlist(Model model) {
		List<ShowInfo> l = ShowInfoDAO.getAllShowInfoByOrder(jdbcTemplate);
		model.addAttribute("showlist", l);
		return "admin_showlist";
	}
	
	@RequestMapping(value = {"/showluckyrainresult","/showLuckyrainResult"})
	public String showLuckyrainResult(Model model) {
		List<LuckyMoneyTransaction> m = null;
		m = LuckyMoneyTransactionDAO.getAllTransactions(jdbcTemplate);
		model.addAttribute("result",m);
		return "luckyrain_result";
	}

}
