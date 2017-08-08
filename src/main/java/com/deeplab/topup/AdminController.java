package com.deeplab.topup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;

import myThread.LuckyRainThread;

public class AdminController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value = "/administrator")
	public String admin() {
		//用户是否是administrator
		return "administrator";
	}
	
	@RequestMapping(value = "/luckyrain_start")
	public String startRain(String round) {
		int i = Integer.parseInt(round);
		System.out.println(i);
		LuckyRainThread t = new LuckyRainThread();
		t.setTemplate(jdbcTemplate);
		int r = Integer.parseInt(round);
		t.setRound(r);
		
		t.start();
		return "luckyrain_start";
	}
	
	@RequestMapping(value = "/add_performance")
	public String addPerformance() {
		return "add_performance";
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
