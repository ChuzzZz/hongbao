package com.deeplab.topup;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.LuckyMoneyDAO;
import dao.UserDAO;

@Controller
public class LoginController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = { "/verify", "Verify" })
	public @ResponseBody Map<String, Object> verify(String inputItcode, String inputName) {
		int itcode = Integer.parseInt(inputItcode);
		String name = inputName;
		Map<String, Object> map = new HashMap<String, Object>();
		if ((itcode == 10086 && name.equals("管理员")) || UserDAO.userExists(itcode, name, jdbcTemplate)) {
			map.put("msg", "yes");
		}else {
			map.put("msg", "no");
		}
		return map;
	}

	@RequestMapping(value = "/login.do")
	public String login(String inputItcode, String inputName, Model model, HttpServletResponse response) {
		int itcode = Integer.parseInt(inputItcode);
		String name = inputName;
		if (itcode == 10086 && name.equals("管理员")) {
			if (LuckyMoneyDAO.getTotalByRound(1, jdbcTemplate) != 0) {
				model.addAttribute("round1", 0);
			}
			if (LuckyMoneyDAO.getTotalByRound(2, jdbcTemplate) != 0) {
				model.addAttribute("round2", 0);
			}
			if (LuckyMoneyDAO.getTotalByRound(3, jdbcTemplate) != 0) {
				model.addAttribute("round3", 0);
			}
			return "administrator";
		} else {
			Cookie cookie = new Cookie("itcode", inputItcode.trim());
			response.addCookie(cookie);
			return "UI";
		}
	}

	@RequestMapping(value = "/logout.do")
	public String logout() {
		return "login";
	}

}
