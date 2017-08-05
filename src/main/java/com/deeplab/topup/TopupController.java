package com.deeplab.topup;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.AccountDAO;

@Controller
public class TopupController {

	@Autowired
	JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(TopupController.class);
	
	@RequestMapping(value="/topup_page")
	public String topUp(String itcode, String username, String amount, Locale locale, Model model) {
		
		logger.info("ITcode:" +itcode +"	username:"+username + "	充值"+ amount);
		
		int ic = Integer.parseInt(itcode);
		long am = Long.parseLong(amount);
		
		String result = null;
		if(AccountDAO.topUp(ic, username, am, locale, jdbcTemplate)) {
			result = "你变强了！";
		}else {
			result = "充值失败，请确认用户信息后再尝试！";
		}
		
		model.addAttribute("result", result);
		return "topup_result";
	}
}
