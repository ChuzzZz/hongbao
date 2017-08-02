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

	private static final Logger logger = LoggerFactory.getLogger(TopupController.class);
	@Autowired
    JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value="/topup_page")
	public String topUp(String itcode, String username, String amount, Locale locale, Model model) {
		
		logger.info("ITcode:" +itcode +"username:"+username + "充值"+ amount);
		
		
		int i = AccountDAO.topUp(itcode, username, amount, locale, jdbcTemplate);
		String result = null;
		if(i == 1) {
			result = "你变强了！";
		}else {
			if(i == -1) {
				result = "账号信息填写错误";
			}else {
				result = "充值失败，等一会再来吧";
			}
		}
		
		model.addAttribute("result", result);
		return "topup_result";
	}
}
