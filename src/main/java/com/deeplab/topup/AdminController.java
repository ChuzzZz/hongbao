package com.deeplab.topup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.LuckyMoneyDAO;
import dao.LuckyMoneyTransactionDAO;
import entity.LuckyMoneyTransaction;
import myThread.LuckyRainThread;

@Controller
public class AdminController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value = "/administrator")
	public String admin() {
		//用户是否是administrator
		return "administrator";
	}
	
	@RequestMapping(value = "/start_luckyrain")
	public void startRain(String round) {
		int i = Integer.parseInt(round);
		System.out.println(i);
		LuckyRainThread t = new LuckyRainThread();
		t.setJdbcTemplate(jdbcTemplate);
		t.setRound(i);
		t.setFlag(true);
		t.start();
	}
	
	@RequestMapping(value = "/add_performance")
	public String addPerformance() {
		return "performance_adding";
	}
	
	@RequestMapping(value = "/showresult")
	public String show_result1(Model model) {
		List<LuckyMoneyTransaction> m = null;
		m = LuckyMoneyTransactionDAO.getAllTransactions(jdbcTemplate);
		model.addAttribute("result",m);
		return "luckymoneyresult";
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
