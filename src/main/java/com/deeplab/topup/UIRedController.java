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
import dao.RedPackageDAO;
import dao.RedPackageTransactionDAO;
import entity.RedPackageTransaction;

@Controller
public class UIRedController {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(value ="redpackage")
	public String getredpackage(Model model,HttpServletRequest request) {
		int itcode = -1;
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return "login";
		}
		for (Cookie c : cookies) {
			if (c.getName().equals("itcode")) {
				itcode = Integer.parseInt(c.getValue());
				break;
			}
		}
		if (!AccountDAO.hasAccount(itcode, jdbcTemplate)){return "redirect:/myaccount";}
		if (RedPackageDAO.getActiveRound(jdbcTemplate)==-1) {model.addAttribute("redpackageresult", "�����δ����!");return "UIredpackageresult";}
		int money = RedPackageDAO.GiveMoney(itcode, jdbcTemplate);
		if (money!=-1) model.addAttribute("redpackageresult", "��ϲ���"+(float)money/100+"Ԫ�����");
		else model.addAttribute("redpackageresult", "���Ѿ�������������!");
		return "UIredpackageresult";
	}

}
