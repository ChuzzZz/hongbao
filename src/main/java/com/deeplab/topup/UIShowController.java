package com.deeplab.topup;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.AccountDAO;
import dao.ShowInfoDAO;
import dao.TipTransactionDAO;
import entity.ShowInfo;

@Controller
public class UIShowController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(UIShowController.class);

	@RequestMapping(value ="searchbyrule")
	public String searchByRule(String show_name, String performer, String department, Model model) {
		List<ShowInfo> showlist = ShowInfoDAO.getShowInfoByRule(show_name, performer, department, jdbcTemplate);
		model.addAttribute("showlist", showlist);
		return "showlist";
	}
	
	@RequestMapping(value = "/tip.do")
	public String tip(int amount, int show_id, Model model, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();// 这样便可以获取一个cookie数组
		int itcode = -1;
		if (cookies == null)
			return "login";
		else {
			for (Cookie c : cookies) {
				if (c.getName().equals("itcode")) {
					itcode = Integer.parseInt(c.getValue());
					break;
				}
			}
		}

		if (AccountDAO.hasAccount(itcode, jdbcTemplate)) {
			// 有钱包账户
			int account_id = AccountDAO.getAccountByItcode(itcode, jdbcTemplate).getId();
			if (AccountDAO.tip(account_id, show_id, amount, jdbcTemplate)) {
				// 余额足够打赏
				
			} else {
				// 余额不勾打赏
				return "topup_page";
			}
		} else {
			// 没有钱包账户 
			return "register_account";
		}

		model.addAttribute("show_id", show_id);
		model.addAttribute("amount", amount);
		return "tip_result";
	}
}
