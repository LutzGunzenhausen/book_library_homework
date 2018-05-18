package com.booklibrary.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller, that serves requests from the /index page.
 *
 * @author Christian-PC
 * 2018
 */
@Controller
@RequestMapping("/index")
public class NetIncomeCalculationController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String initialize(Model model) {
		return "/index";
	}
}
