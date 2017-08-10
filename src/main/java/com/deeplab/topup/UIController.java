package com.deeplab.topup;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.ShowInfoDAO;
import entity.ShowInfo;

@Controller
public class UIController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(UIController.class);
	
	@RequestMapping(value = "/myAccount")
	public String myAccount(){
		return "MyAccount";
	}
	
	@RequestMapping(value = "/getshowlist")
	public String getshowlist(Model model) {
		List<ShowInfo> l = ShowInfoDAO.getAllShowInfo(jdbcTemplate);
		model.addAttribute("showlist", l);
		return "showlist";
	}

	@RequestMapping(value = "/tip")
	public String tip(int amount, int sid, Model model) {
		model.addAttribute("sid", sid);
		model.addAttribute("amount", amount);
		return "tipresult";
	}
	
}
