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
import dao.UserDAO;
import entity.ShowInfo;

@Controller
public class LoginController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(TopupController.class);
	
	@RequestMapping(value = "/login_verify")
	public String logIn(String itcode, String username,Model model) {
		int ic = Integer.parseInt(itcode);
		if (ic==10086&&username.equals("admin")) return "administrator";
		else if(UserDAO.userExists(ic, username, jdbcTemplate)) {
			return "topup";
		}else {
			model.addAttribute("login_result", "用户不存在！");
			return "login";
		}
	}
	
	@RequestMapping(value = "/getshowlist")
	public String getshowlist(Model model) {
		List<ShowInfo> l = ShowInfoDAO.getAllShowInfo(jdbcTemplate);
		model.addAttribute("showlist",l);
		return "showlist";
	}
	
	@RequestMapping(value = "/tip")
	public String tip(int amount,int sid,Model model) {
		model.addAttribute("sid",sid);
		model.addAttribute("amount",amount);
		return "tipresult";
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
