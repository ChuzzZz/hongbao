package com.deeplab.topup;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
		int money = RedPackageDAO.GiveMoney(itcode, jdbcTemplate);
		if (money!=-1) model.addAttribute("redpackageresult", "恭喜获得"+(float)money/100+"元红包！");
		else model.addAttribute("redpackageresult", "您已经抢过这个红包了!");
		return "UIredpackageresult";
	}

}
