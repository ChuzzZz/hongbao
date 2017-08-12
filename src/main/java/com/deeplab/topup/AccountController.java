package com.deeplab.topup;

import java.util.List;
import java.util.Locale;

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
import form.AccountForm;

@Controller
public class AccountController {

	@Autowired
	JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@RequestMapping(value= {"/topupPage","/topuppage"})
	public String goTopup() {
		return "topup_page";
	}
	
	@RequestMapping(value= {"/topupResult","topupresult"})
	public String topupResult(@Valid AccountForm accountForm,BindingResult bindingResult, Locale locale, Model model) {
		
		if(bindingResult.hasErrors()) {
			String message = "";
			List<ObjectError> errors = bindingResult.getAllErrors();
			for(ObjectError error : errors) {
				message += error.getDefaultMessage()+"<br/>";
			}
			model.addAttribute("message", message);
			return "topup";
		}else {
			int itcode = accountForm.getItcode();
			String name = accountForm.getName();
			long amount = accountForm.getAmount();
			String result;
			if(AccountDAO.topUp(itcode, name, amount, locale, jdbcTemplate)) {
				result = "你变强了！";
			}else {
				result = "充值失败，请确认用户信息后再尝试！";
			}
			
			model.addAttribute("result", result);
			return "topup_result";
		}
	}
}
