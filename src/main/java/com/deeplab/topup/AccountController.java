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
public class AccountController {

	@Autowired
	JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@RequestMapping(value = { "topup", "Topup" })
	public String topup() {
		return "topup_page";
	}

	@RequestMapping(value = { "withdraw" })
	public String withdraw() {
		return "withdraw_page";
	}

	@RequestMapping(value = { "getaccounttransactions" })
	public String getAccountTransactins(String ordertype, HttpServletRequest request, HttpServletResponse response, Model model) {
		// 确定排序的顺序
		String order = null;
		int itcode = -1;
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			order = "";
		}
		for (Cookie c : cookies) {
			if (c.getName().equals("order")) {
				//如果之前是升序就变为降序,反之同理
				if (c.getValue().equals("desc")) {
					order = "";
				} else {
					order = "desc";
				}
			}
			if (c.getName().equals("itcode")) {
				itcode = Integer.parseInt(c.getValue());
			}
		}
		// 将现在的排序顺序加入cookie
		Cookie cookie = new Cookie("order", order);
		response.addCookie(cookie);
		// 确定排序的类型
		if (ordertype == null) {
			ordertype = "time";
		}
		int account_id = AccountDAO.getAccountIdByItcode(itcode, jdbcTemplate);
		System.out.println(ordertype+"======="+order+"======="+account_id);
		List<AccountTransaction> t = AccountDAO.getAccountTransactions(ordertype, order, account_id, jdbcTemplate);
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

			String paycode = accountForm.getPaycode();
			if (AccountDAO.initAccount(itcode, paycode, jdbcTemplate)) {

				model.addAttribute("account_id", AccountDAO.getAccountByItcode(itcode, jdbcTemplate).getId());
				model.addAttribute("balance", 0);
			}
			return "myAccount";
		}
	}

	@RequestMapping(value = "/topup.do")
	public String topupDo(String amount, Locale locale, HttpServletRequest request, Model model) {
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
		int account_id = AccountDAO.getAccountIdByItcode(itcode, jdbcTemplate);
		model.addAttribute("amount", amount);
		model.addAttribute("account_id", account_id);
		return "topup_confirm";
	}

	@RequestMapping(value = { "/verifypaycode", "/verifyPaycode" })
	public void verifyPaycode(String paycode, String account_id, HttpServletResponse response) {
		int id = Integer.parseInt(account_id);
		response.setCharacterEncoding("UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			if (AccountDAO.verifyPaycode(id, paycode, jdbcTemplate)) {
				out.print("支付密码正确");
			} else {
				out.print("支付密码错误");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping(value = { "/topupresult", "/topupResult" })
	public String topupResult(String paycode, String account_id, String amount, Locale locale, Model model) {
		int id = Integer.parseInt(account_id);
		long am = Long.parseLong(amount);
		String result = "";

		if (AccountDAO.topUp(id, am, jdbcTemplate)) {
			result = "你变强了！";
		} else {
			result = "充值失败！请稍后再试";
		}

		model.addAttribute("result", result);
		return "topup_result";
	}

	@RequestMapping(value = "/checkbalance")
	public @ResponseBody Map<String, Object> checkBalance(String amount, HttpServletRequest request) {
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
		long am = Long.parseLong(amount);
		if (AccountDAO.preTransaction(account_id, am, jdbcTemplate)) {
			map.put("msg", "yes");
		} else {
			map.put("msg", "no");
		}

		return map;
	}

	@RequestMapping(value = "/withdraw.do")
	public String withdrawDo(String amount, Locale locale, HttpServletRequest request, Model model) {
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
		int account_id = AccountDAO.getAccountIdByItcode(itcode, jdbcTemplate);
		model.addAttribute("amount", amount);
		model.addAttribute("account_id", account_id);
		return "withdraw_confirm";
	}

	@RequestMapping(value = { "/withdrawresult", "/withdrawResult" })
	public String withdrawResult(String paycode, String account_id, String amount, Locale locale, Model model) {
		int id = Integer.parseInt(account_id);
		long am = Long.parseLong(amount);
		String result = "";

		if (AccountDAO.withdraw(id, am, jdbcTemplate)) {
			result = "你变弱了！";
		} else {
			result = "提现失败！请稍后再试";
		}

		model.addAttribute("result", result);
		return "withdraw_result";
	}

}
