package com.deeplab.topup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.AccountDAO;
import dao.ShowInfoDAO;
import dao.TipTransactionDAO;
import entity.ShowInfo;

@Controller
public class UIShowController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(UIShowController.class);

	@RequestMapping(value = "searchbyrule")
	public String searchByRule(String show_name, String performer, String department, Model model) {
		List<ShowInfo> showlist = ShowInfoDAO.getShowInfoByRule(show_name, performer, department, jdbcTemplate);
		model.addAttribute("showlist", showlist);
		return "showlist";
	}

	@RequestMapping(value = "/tip.do")
	public @ResponseBody Map<String, Object> tip(int amount, int show_id, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();// ��������Ի�ȡһ��cookie����
		int itcode = -1;
		for (Cookie c : cookies) {
			if (c.getName().equals("itcode")) {
				itcode = Integer.parseInt(c.getValue());
				break;
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		if (AccountDAO.hasAccount(itcode, jdbcTemplate)) {
			// ��Ǯ���˻�
			int account_id = AccountDAO.getAccountByItcode(itcode, jdbcTemplate).getId();
			if (AccountDAO.tip(account_id, show_id, amount, jdbcTemplate)) {
				// ����㹻����

				String show_name = ShowInfoDAO.getShowInfoByid(show_id, jdbcTemplate).getShow_name();
				map.put("result", "success");
				map.put("amount", amount);
				map.put("show_name",show_name);
			} else {
				// ��������
				map.put("result", "failed");
			}
		} else {

			// û��Ǯ���˻�
			map.put("result", "erro");
		}
		return map;
	}
}
