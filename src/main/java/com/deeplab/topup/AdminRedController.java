package com.deeplab.topup;

import java.util.List;

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
public class AdminRedController {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value = {"/adminredpackage","/AdminRedPackage"})
	public String adminRedpackage(Model model) {
		int round1 = RedPackageDAO.CheckStatus(1, jdbcTemplate);
		int round2 = RedPackageDAO.CheckStatus(2, jdbcTemplate);
		int round3 = RedPackageDAO.CheckStatus(3, jdbcTemplate);
		model.addAttribute("round1", round1);
		model.addAttribute("round2", round2);
		model.addAttribute("round3", round3);
		return "admin_redpackage";
	}
	
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
	
	@RequestMapping(value ="GetAllRedPackageTransaction")
	public String GetAllRedPackageTransaction(Model model,HttpServletRequest request) {
		List<RedPackageTransaction> r = RedPackageTransactionDAO.getAllTransactions(jdbcTemplate);
		model.addAttribute("redpackagetransaction", r);
		return "redpackage_result";
	}
}
