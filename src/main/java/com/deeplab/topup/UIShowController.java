package com.deeplab.topup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.AccountDAO;
import dao.ShowInfoDAO;
import entity.ShowInfo;

@Controller
public class UIShowController {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(value = "searchbyrule")
	public String searchByRule(String show_name, String performer, String department, Model model) {
		List<ShowInfo> showlist = ShowInfoDAO.getShowInfoByRule(show_name, performer, department, jdbcTemplate);
		
		model.addAttribute("showlist", showlist);
		return "showlist";
	}

	@RequestMapping(value = "/tip.do")
	public @ResponseBody Map<String, Object> tip(String amount, int show_id, HttpServletRequest request) {
		//获取用户ITcode
		Cookie[] cookies = request.getCookies();
		int itcode = -1;
		for (Cookie c : cookies) {
			if (c.getName().equals("itcode")) {
				itcode = Integer.parseInt(c.getValue());
				break;
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		long l = AccountDAO.toDbAmount(amount);
		if (AccountDAO.hasAccount(itcode, jdbcTemplate)) {
			// 有钱包账户
			int account_id = AccountDAO.getAccountByItcode(itcode, jdbcTemplate).getId();
			if (AccountDAO.tip(account_id, show_id, l, jdbcTemplate)) {
				// 余额足够打赏
				String show_name = ShowInfoDAO.getShowInfoById(show_id, jdbcTemplate).getShow_name();
				map.put("result", "success");
				map.put("amount", amount);
				map.put("show_name",show_name);
			} else {
				// 余额不勾打赏
				map.put("result", "failed");
			}
		} else {
			// 没有钱包账户
			map.put("result", "erro");
		}
		return map;
	}
}
