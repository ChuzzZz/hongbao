package com.deeplab.topup;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request, Locale locale, Model model) {

		logger.info("Welcome home! The client locale is {}.", locale);

		// Cookie[] cookies = request.getCookies();
		// if (cookies == null) {
		// Date date = new Date();
		// DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
		// DateFormat.LONG, locale);
		// String formattedDate = dateFormat.format(date);
		//
		// model.addAttribute("serverTime", formattedDate);
		//
		// return "login";
		// }else {
		// return "UI";
		// }

		return "login";

	}

	@RequestMapping(value = { "/MyPage", "myPage" })
	public String myPage(Model model, HttpServletRequest request) {
		
		Cookie[] cookies = request.getCookies();
		
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("itcode")) {
						return "UI";
				}
			}
		}
		return "login";
	}

}
