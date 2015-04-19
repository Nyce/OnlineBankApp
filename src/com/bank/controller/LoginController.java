package com.bank.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;


import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.http.MediaType;

import com.bank.exceptions.LoginException;
import com.bank.model.Account;
import com.bank.model.Output;
import com.bank.model.User;
import com.bank.validator.UserValidator;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.grails.datastore.gorm.rest.client.json.JsonHttpMessageConverter;
import org.json.JSONException;
import org.json.JSONObject;



@Controller
public class LoginController {
	
	//logger used to log excepions and errors
	final static Logger logger = Logger.getLogger(LoginController.class);

	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView login(){
		//this modelview adds the object to the jsp page
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}

	@RequestMapping(value="loginSuccess", method = RequestMethod.POST)
	public ModelAndView loginSuccess(@RequestParam("username") String username, @RequestParam("password") String password, 
			 @ModelAttribute("user") User user, BindingResult result, HttpServletRequest request ) throws JSONException {
		
		
		//this modelview maps to the home jsp page
		ModelAndView mv = new ModelAndView("home");

		//create new properties file to parse BankService URL
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("C:\\Development\\Workspace\\OnlineBankApp\\URL.props"));
			logger.debug(""+prop);
		} catch (IOException ie) {
			//log error to my log file
			logger.error("File not found " +  ie.getMessage());
			
		}
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Type", "*/*");
		RestTemplate rest = new RestTemplate();
		rest.getMessageConverters().add(new StringHttpMessageConverter());
		
		//Validator class validates data
		UserValidator userValidator = new UserValidator();

		user.setUsername(username);
		user.setPassword(password);
		
		//validates null values for username or password
		if( userValidator.isNull(user)){
			logger.error("User input fields contain null values");
			return loginFailure();
		}
		
		JSONObject 	obj = new JSONObject();	
		boolean Exception = false;
		try {
			obj.put("username", username);
			obj.put("password", password);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Exception = true;
		}

		if(Exception == true){
			 return loginFailure();
		}
		HttpEntity<String> entityCredentials = new HttpEntity<String>(obj.toString(), httpHeaders);
		ResponseEntity<String> responseEntity = rest.exchange(prop.getProperty("login_url"),
				HttpMethod.POST, entityCredentials, String.class);

		String acctObject = responseEntity.getBody();
		JSONObject accountDetails = new JSONObject(); 
		
		//returns exception if username or password doesn't exist
		if(StringUtils.isBlank(acctObject)){
			logger.error("User input contains null values");
			return loginFailure();
		}

		//this checks username or password exception
		try {
			
			accountDetails = new JSONObject(acctObject);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Exception = true;
		}

		String bal = "";
		//this checks null balance exception
		try {
			bal = accountDetails.getString("balance"); 
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Exception = true;
		}

		Long acctNumb = null;
		//this checks null account number exception
		try {
			acctNumb = accountDetails.getLong("accountNumber");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Exception = true;
		}

		Long acctType = null;
		//this checks null account type exception
		try {
			acctType = accountDetails.getLong("acctType");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Exception = true;
		}

		//		request.getSession().setAttribute("username", user.getUsername());
		//		request.getSession().setAttribute("password", user.getPassword());

		//If an exception is needed, return exception page
		if(Exception == true){
			 return loginFailure();
		}

		mv.addObject("balMessage",  " Here is your balance: ");
		mv.addObject("acctTypeMessage",  " Your account type: checking (100), savings(200): ");
		mv.addObject("acctNumberMessage",  " Here is your account number: ");
		mv.addObject("username", username);

		mv.addObject("bal", bal);
		mv.addObject("acctNumb", acctNumb);
		mv.addObject("acctType", acctType);
		mv.addObject("user",user);

		return mv;
	}


	@RequestMapping(value="loginFailure", method = RequestMethod.POST)
	public ModelAndView loginFailure(){
		ModelAndView exception = new ModelAndView("LoginException");
		exception.addObject("exception", "Invalid username or password. Please try again");
		return exception;
	}
}
