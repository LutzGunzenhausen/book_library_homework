package com.booklibrary.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String initialize(Model model) {
		return "/test";
	}

}
