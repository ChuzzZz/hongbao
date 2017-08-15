package com.deeplab.topup;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.LuckyMoneyDAO;
import dao.UserDAO;

@Controller
public class LoginController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = {"/verify","Verify"})
	public String verify(String itcode, String username, Model model,HttpServletResponse response) {
		int ic = Integer.parseInt(itcode);
		System.out.println(ic);
		System.out.println(username);
		if (ic == 10086 && username.equals("管理员")) {
			if (LuckyMoneyDAO.getTotalByRound(1, jdbcTemplate)!=0){model.addAttribute("round1",0);}
			if (LuckyMoneyDAO.getTotalByRound(2, jdbcTemplate)!=0){model.addAttribute("round2",0);}
			if (LuckyMoneyDAO.getTotalByRound(3, jdbcTemplate)!=0){model.addAttribute("round3",0);}
			long k = LuckyMoneyDAO.getTotalByRound(1, jdbcTemplate);
			System.out.println(k);
			return "administrator";
		} else {
			if (UserDAO.userExists(ic, username, jdbcTemplate)) {
				Cookie cookie = new Cookie("itcode", itcode.trim());  
	            cookie.setMaxAge(30 * 60);// 设置为30min
	            cookie.setPath("/");  
	            System.out.println("已添加===============");  
	            response.addCookie(cookie);  
				return "UI";
			} else {
				model.addAttribute("login_result", "用户不存在！");
				return "login";
			}
		}
	}
	
}
