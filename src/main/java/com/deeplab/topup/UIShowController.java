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
	
	@RequestMapping(value = "/tip.do")
	public String tip(int amount, int show_id, Model model, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();// ��������Ի�ȡһ��cookie����
		int itcode = -1;
		if (cookies == null)
			return "login";
		else {
			for (int i = 0; i < cookies.length; i++) {
				System.out.println(cookies[i].getName());
				if (cookies[i].getName().equals("itcode")) {
					itcode = Integer.parseInt(cookies[i].getValue());
					break;
				}
			}
		}
		
		if(AccountDAO.hasAccount(itcode, jdbcTemplate)) {
			//��Ǯ���˻�
			int account_id = AccountDAO.getAccountByItcode(itcode, jdbcTemplate).getId();
			if(AccountDAO.tip(account_id, show_id, amount, jdbcTemplate)) {
				//����㹻����
				
			}else {
				//��������
			}
		}else {
			//û��Ǯ���˻�
		}
		
		
		model.addAttribute("show_id", show_id);
		model.addAttribute("amount", amount);
		return "tip_result";
	}
}
