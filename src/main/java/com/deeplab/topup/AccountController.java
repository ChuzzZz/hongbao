package com.deeplab.topup;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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

import dao.AccountDAO;
import dao.UserDAO;
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
	

	@RequestMapping(value = "/register.do")
	public String register(@Valid AccountForm accountForm, BindingResult bindingResult, HttpServletRequest request, Model model) {
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

			String password = accountForm.getPassword();
			String paycode = accountForm.getPaycode();
			AccountDAO.initAccount(itcode, password, paycode, jdbcTemplate);
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
	
	@RequestMapping(value = {"/topupresult", "/topupResult"})
	public String topupResult(String paycode, String account_id, String amount, Locale locale, Model model) {
		int id = Integer.parseInt(account_id);
		long am = Long.parseLong(amount);
		String result = "";
		
		if(AccountDAO.verifyPaycode(id, paycode, jdbcTemplate)) {
			if(AccountDAO.topUp(id, am, jdbcTemplate)){
				result = "ƒ„±‰«ø¡À£°";
			}else {
				result = "≥‰÷µ ß∞‹£°«Î…‘∫Û‘Ÿ ‘";
			}
		}else {
			result = "÷ß∏∂√‹¬Î¥ÌŒÛ£°";
			model.addAttribute("account_id", account_id);
			model.addAttribute("amount", amount);
			model.addAttribute("erro", result);
			return "topup_confirm";
		}
		
		model.addAttribute("result", result);
		return "topup_result";
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
	
	@RequestMapping(value = {"/withdrawresult", "/withdrawResult"})
	public String withdrawResult(String paycode, String account_id, String amount, Locale locale, Model model) {
		int id = Integer.parseInt(account_id);
		long am = Long.parseLong(amount);
		String result = "";
		
		if(AccountDAO.verifyPaycode(id, paycode, jdbcTemplate)) {
			if(AccountDAO.withdraw(id, am, jdbcTemplate)){
				result = "ƒ„±‰»ı¡À£°";
			}else {
				result = "Ã·œ÷ ß∞‹£°«Î…‘∫Û‘Ÿ ‘";
			}
		}else {
			result = "÷ß∏∂√‹¬Î¥ÌŒÛ£°";
			model.addAttribute("account_id", account_id);
			model.addAttribute("amount", amount);
			model.addAttribute("erro", result);
			return "withdraw_confirm";
		}
		
		model.addAttribute("result", result);
		return "withdraw_result";
	}
	
	
	
}
