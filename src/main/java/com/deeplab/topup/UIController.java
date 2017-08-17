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
import dao.RedPackageDAO;
import dao.RedPackageTransactionDAO;
import dao.ShowInfoDAO;
import entity.Account;
import entity.RedPackageTransaction;
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
	
	@RequestMapping(value = { "/getshowlist", "getShowlist" })
	public String getShowlist(Model model) {
		List<ShowInfo> showlist = ShowInfoDAO.getAllShowInfoByOrder(jdbcTemplate);
		model.addAttribute("showlist", showlist);
		return "showlist";
	}
	
	@RequestMapping(value ="redpackage")
	public String getredpackage(Model model,HttpServletRequest request) {
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
		int money = RedPackageDAO.GiveMoney(itcode, jdbcTemplate);
		if (money!=-1) model.addAttribute("redpackageresult", "恭喜获得"+(float)money/100+"元红包！");
		else model.addAttribute("redpackageresult", "您已经抢过这个红包了!");
		return "redpackage_result";
	}

	@RequestMapping(value ="GetAllRedPackageTransaction")
	public String GetAllRedPackageTransaction(Model model,HttpServletRequest request) {
		List<RedPackageTransaction> r = RedPackageTransactionDAO.getAllTransactions(jdbcTemplate);
		model.addAttribute("redpackagetransaction", r);
		return "redpackagetransaction";
	}
	
	
}
