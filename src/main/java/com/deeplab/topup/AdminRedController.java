package com.deeplab.topup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.RedPackageDAO;

@Controller
public class AdminRedController {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value = {"/ActivateRedPackage","/activateredpackage"})
	public String ActivateRedPackage(int round,Model model) {
		RedPackageDAO.ActicateRound(round, jdbcTemplate);
		return "redirect:/adminredpackage";
	}
	
	@RequestMapping(value = {"/StopRedPackage","/stopredpackage"})
	public String StopRedPackage(int round,Model model) {
		RedPackageDAO.StopRound(round, jdbcTemplate);
		return "redirect:/adminredpackage";
	}
	
	@RequestMapping(value = {"/adminredpackage","/AdminRedPackage"})
	public String adminredpackage(Model model) {
		int round1 = RedPackageDAO.CheckStatus(1, jdbcTemplate);
		int round2 = RedPackageDAO.CheckStatus(2, jdbcTemplate);
		int round3 = RedPackageDAO.CheckStatus(3, jdbcTemplate);
		model.addAttribute("round1", round1);
		model.addAttribute("round2", round2);
		model.addAttribute("round3", round3);
		return "adminredpackage";
	}
	
}
