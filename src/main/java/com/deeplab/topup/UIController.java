package com.deeplab.topup;

import java.util.List;

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

import dao.AccountDAO;
import dao.ShowInfoDAO;
import entity.ShowInfo;

@Controller
public class UIController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(UIController.class);

	@RequestMapping(value = "/home")
	public String Home() {
		return "UI";
	}

	@RequestMapping(value = {"/myaccount", "/myAccount"})
	public String myAccount(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return "login";
		}
		int itcode = -1;
		for(Cookie c : cookies) {
			if(c.getName().equals("itcode")) {
				itcode = Integer.parseInt(c.getValue());
				break;
			}
		}
		if(AccountDAO.hasAccount(itcode, jdbcTemplate)) {
			return "myAccount";
		}else {
			return "register_account";
		}
	}
	
	@RequestMapping(value = { "/getshowlist", "getShowlist" })
	public String getShowlist(Model model) {
		List<ShowInfo> showlist = ShowInfoDAO.getAllShowInfoByOrder(jdbcTemplate);
		model.addAttribute("showlist", showlist);
		return "showlist";
	}

}
