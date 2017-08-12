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
import dao.TipTransactionDAO;
import entity.ShowInfo;

@Controller
public class UIController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(UIController.class);
	
	@RequestMapping(value = "/myaccount")
	public String myAccount(){
		return "myAccount";
	}
	
	@RequestMapping(value = {"/getshowlist","getShowlist"})
	public String getShowlist(Model model) {
		List<ShowInfo> l = ShowInfoDAO.getAllShowInfo(jdbcTemplate,"s_order");
		model.addAttribute("showlist", l);
		return "showlist";
	}

	@RequestMapping(value = "/tip")
	public String tip(int amount, int sid, Model model,HttpServletRequest request) {
		 Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组  
		 int itcode=0;
		 if (cookies==null) return "login";
		 else {
			 for (int i=0;i<cookies.length;i++){
				 System.out.println(cookies[i].getName());
				 if (cookies[i].getName().equals("itcode")) {itcode=Integer.parseInt(cookies[i].getValue());System.out.println(cookies[i].getValue());break;}
			 }
		 }
		model.addAttribute("sid", sid);
		model.addAttribute("amount", amount);
		System.out.println(cookies.length);
		TipTransactionDAO.addTipTransaction(AccountDAO.getAccountByItcode(itcode, jdbcTemplate).getId(), sid, amount, jdbcTemplate);
		
		return "tip_result";
	}
	
}
