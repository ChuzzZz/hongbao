package com.deeplab.topup;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.AccountDAO;
import dao.ShowInfoDAO;
import entity.Account;
import entity.ShowInfo;

@Controller
public class UIController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(value = { "/myaccount", "/myAccount" })
	public String myAccount(HttpServletRequest request, Model model) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return "login";
		}
		int itcode = -1;
		for (Cookie c : cookies) {
			if (c.getName().equals("itcode")) {
				itcode = Integer.parseInt(c.getValue());
				break;
			}
		}
		if (AccountDAO.hasAccount(itcode, jdbcTemplate)) {
			Account account = AccountDAO.getAccountByItcode(itcode, jdbcTemplate);
			model.addAttribute("account_id", account.getId());
			model.addAttribute("balance", (float)account.getBalance() / 100);
			return "myAccount";
		} else {
			return "register_account";
		}
	}
	
	@RequestMapping(value = { "/getshowlist", "/getShowlist" })
	public String getShowlist(Model model) {
		List<ShowInfo> showlist = ShowInfoDAO.getAllShowInfoByTimeOrder(jdbcTemplate);
		
		model.addAttribute("showlist", showlist);
		return "showlist";
	}
	
}
