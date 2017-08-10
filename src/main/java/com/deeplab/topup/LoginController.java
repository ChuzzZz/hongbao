package com.deeplab.topup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.UserDAO;

@Controller
public class LoginController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/login_verify")
	public String logIn(String itcode, String username, Model model) {
		int ic = Integer.parseInt(itcode);
		System.out.println(ic);
		System.out.println(username);
		if (ic == 10086 && username.equals("管理员")) {
			return "administrator";
		} else {
			if (UserDAO.userExists(ic, username, jdbcTemplate)) {
				return "UI";
			} else {
				model.addAttribute("login_result", "用户不存在！");
				return "login";
			}
		}
	}
	
}
