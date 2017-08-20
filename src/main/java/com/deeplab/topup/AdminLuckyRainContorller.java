package com.deeplab.topup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.LuckyMoneyTransactionDAO;
import entity.LuckyMoneyTransaction;
import myThread.LuckyRainThread;

@Controller
public class AdminLuckyRainContorller {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value = {"/startluckyrain","/startLuckyrain"})
	public void startLuckyrain(String round) {
		int i = Integer.parseInt(round);
		LuckyRainThread t = new LuckyRainThread();
		t.setJdbcTemplate(jdbcTemplate);
		t.setRound(i);
		t.setFlag(true);
		t.start();
	}
	
	@RequestMapping(value = {"/showluckyrainresult","/showLuckyrainResult"})
	public String showLuckyrainResult(Model model) {
		List<LuckyMoneyTransaction> m = null;
		m = LuckyMoneyTransactionDAO.getAllTransactions(jdbcTemplate);
		model.addAttribute("result",m);
		return "luckyrain_result";
	}
}
