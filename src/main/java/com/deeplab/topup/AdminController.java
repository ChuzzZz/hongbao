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
	public String admin() {
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
	public String addShowInfo(String order, String show_name, String performer, String department, String start_time, Model model) {
		int o = Integer.parseInt(order);
		Timestamp ts = Timestamp.valueOf(start_time);
		if(ShowInfoDAO.addShowInfo(o, show_name, performer, department, ts, jdbcTemplate)) {
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
