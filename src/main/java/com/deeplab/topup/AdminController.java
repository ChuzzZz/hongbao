package com.deeplab.topup;

import org.springframework.web.bind.annotation.RequestMapping;

public class AdminController {
	@RequestMapping(value = "/administrator")
	public String admin() {
		//用户是否是administrator
		return "administrator";
	}
	
	@RequestMapping(value = "/luckyrain_start")
	public String startRain(String round) {
		int i = Integer.parseInt(round);
		System.out.println(i);
		return "administrator";
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
