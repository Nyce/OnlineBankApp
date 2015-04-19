package com.bank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LogOutController {
	@RequestMapping("LogOut")
	public ModelAndView logOut(){
		//this modelview adds the object to the jsp page
		ModelAndView mv = new ModelAndView("LogOut");
		mv.addObject("LogOutMessage", "You have successfully logged out");
		return mv;

	}
}
