package com.deeplab.topup;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.AccountDAO;
import dao.UserDAO;
import entity.AccountTransaction;
import form.AccountForm;

@Controller
public class UIAccountController {

	@Autowired
	JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(UIAccountController.class);

	@RequestMapping(value = { "topup" })
	public String topup() {
		return "topup_page";
	}

	@RequestMapping(value = { "withdraw" })
	public String withdraw() {
		return "withdraw_page";
	}
	/**
	 * 查找用户的所有交易记录
	 * @param request
	 * @param model
	 * @return 用户交易记录界面
	 */
	@RequestMapping(value = { "getaccounttransactions" })
	public String getAccountTransactins(HttpServletRequest request, Model model) {
		int itcode = -1;
		Cookie[] cookies = request.getCookies();
		for (Cookie c : cookies) {
			if (c.getName().equals("itcode")) {
				itcode = Integer.parseInt(c.getValue());
				break;
			}
		}

		int account_id = AccountDAO.getAccountIdByItcode(itcode, jdbcTemplate);
		List<AccountTransaction> t = AccountDAO.getAccountTransactionsOrderByTime(account_id, jdbcTemplate);
		model.addAttribute("AccountTransaction", t);
		return "account_transaction";
	}

	@RequestMapping(value = "/register.do")
	public String register(@Valid AccountForm accountForm, BindingResult bindingResult, HttpServletRequest request,
			Model model) {
		if (bindingResult.hasErrors()) {
			String message = "";
			List<ObjectError> errors = bindingResult.getAllErrors();
			for (ObjectError error : errors) {
				message += error.getDefaultMessage() + "<br/>";
			}
			model.addAttribute("message", message);
			return "register_account";
		} else {
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
			int itcode2 = accountForm.getItcode();
			if (itcode!=itcode2) {
				model.addAttribute("message", "请勿使用他人账号注册钱包！");
				return "register_account";
			}
			String paycode = accountForm.getPaycode();
			if (AccountDAO.initAccount(itcode, paycode, jdbcTemplate)) {

				model.addAttribute("account_id", AccountDAO.getAccountByItcode(itcode, jdbcTemplate).getId());
				model.addAttribute("balance", 0);
			}
			return "redirect:/myaccount";
		}
	}
	
	@RequestMapping(value = "/checkbalance")
	public @ResponseBody Map<String, Object> checkBalance(String amount, HttpServletRequest request) {
		//获取用户ITCODE
		Cookie[] cookies = request.getCookies();
		int itcode = -1;
		for (Cookie c : cookies) {
			if (c.getName().equals("itcode")) {
				itcode = Integer.parseInt(c.getValue());
				break;
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		int account_id = AccountDAO.getAccountIdByItcode(itcode, jdbcTemplate);
		long a = AccountDAO.toDbAmount(amount);
		if (AccountDAO.preTransaction(account_id, a, jdbcTemplate)) {
			map.put("msg", "yes");
		} else {
			map.put("msg", "no");
		}

		return map;
	}


	
	@RequestMapping(value = { "/topup.do", "/Topup.do" })
	public String topupResult(String paycode, String amount, Locale locale, Model model,HttpServletRequest request) {
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
		int id = AccountDAO.getAccountIdByItcode(itcode, jdbcTemplate);
		long a = AccountDAO.toDbAmount(amount);
		String result = "";
		if (AccountDAO.verifyPaycode(id, paycode, jdbcTemplate)){
			if (AccountDAO.topUp(id, a, jdbcTemplate)) {
				result = "充值成功！";
			} else {
				result = "充值失败！请输入正确的金额";
			}
		} 
		else result = "密码错误！";
			model.addAttribute("result", result);
			return "topup_result";
	}


	@RequestMapping(value = { "/withdraw.do", "/withdraw.do" })
	public String withdrawResult(String paycode, String amount, Locale locale, Model model,HttpServletRequest request) {
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
		int id = AccountDAO.getAccountIdByItcode(itcode, jdbcTemplate);
		long a = AccountDAO.toDbAmount(amount);
		String result = "";

		if (AccountDAO.verifyPaycode(id, paycode, jdbcTemplate)){
			if (AccountDAO.getAccountById(id, jdbcTemplate).getBalance()<a){result = "余额不足！";}
			else if (AccountDAO.withdraw(id, a, jdbcTemplate)) {
				result = "提现成功！";
			} else {
				result = "提现失败！请输入正确的金额";
			}
		}
		else result = "密码错误！";
		model.addAttribute("result", result);
		return "withdraw_result";
		}

}
