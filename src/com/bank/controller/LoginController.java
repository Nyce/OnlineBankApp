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

	/**
	 * @return ModelAndView
	 */
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView login(){
		//this modelview adds the object to the jsp page
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}

	/**
	 * @param username
	 * @param password
	 * @param user
	 * @param result
	 * @param request
	 * @return ModelAndView
	 * @throws JSONException
	 */
	@RequestMapping(value="loginSuccess", method = RequestMethod.POST)
	public ModelAndView loginSuccess(@RequestParam("username") String username, @RequestParam("password") String password, 
			@ModelAttribute("user") User user, BindingResult result, HttpServletRequest request ) throws JSONException {

		//account details variables
		Long acctNumb = null;
		Long acctType = null;
		String bal = "";

		//this modelview maps to the home jsp page
		ModelAndView mv = new ModelAndView("home");
		//create new properties file to parse BankService URL
		Properties prop = parseProps();
		//create a new Http header to be used to communicate with the REST API
		HttpHeaders httpHeaders = new HttpHeaders();
		//create new rest template to send request and retrieve the response
		RestTemplate rest = createRestTemp(httpHeaders);
		//Validator class validates data
		UserValidator userValidator = new UserValidator();

		setUser(username, password, user);

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
		System.out.println(acctObject);
		JSONObject accountDetails = new JSONObject(); 

		//returns exception if username or password doesn't exist
		//this checks username or password exception
		if(StringUtils.isBlank(acctObject)){
			return loginFailure();
		}
		
			try {

				accountDetails = new JSONObject(acctObject);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Exception = true;
				logger.info("Someting aint right " + accountDetails);
			}

			//this checks null balance exception
			try {
				bal = accountDetails.getString("balance"); 
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Exception = true;
				logger.info("Someting aint right " + bal);
			}

			//this checks null account number exception
			try {
				acctNumb = accountDetails.getLong("accountNumber");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Exception = true;
				logger.info("Someting aint right " + acctNumb);
			}

			//this checks null account type exception
			try {
				 acctType = accountDetails.getLong("acctType");
				logger.info("account type:" + acctType);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Exception = true;
				logger.info("Someting aint right " + acctType);
			}
			
			//If an exception is needed, return exception page
			if(Exception == true){
				return loginFailure();
			}

		//this method takes in the user's attributes and adds them to the ModelAndView
		showAccountDetails(username, user, mv, bal, acctNumb, acctType);

		return mv;
	}

	/**
	 * @param username
	 * @param password
	 * @param user
	 */
	public void setUser(String username, String password, User user) {
		user.setUsername(username);
		user.setPassword(password);
	}

	/**
	 * @param username
	 * @param user
	 * @param mv
	 * @param bal
	 * @param acctNumb
	 * @param acctType
	 */
	public void showAccountDetails(String username, User user, ModelAndView mv,
			String bal, Long acctNumb, Long acctType) {
		mv.addObject("balMessage",  " Here is your balance: ");
		mv.addObject("acctTypeMessage",  " Your account type: ");
		mv.addObject("acctNumberMessage",  " Here is your account number: ");
		mv.addObject("username", username);

		mv.addObject("bal", bal);
		mv.addObject("acctNumb", acctNumb);
		accountType(mv, acctType);
		mv.addObject("user",user);
	}

	/**
	 * @return Properties
	 */
	public Properties parseProps() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("C:\\Development\\Workspace\\OnlineBank\\URL.props"));
			logger.debug(""+prop);
		} catch (IOException ie) {
			//log error to my log file
			logger.error("File not found " +  ie.getMessage());

		}
		return prop;
	}


	/**
	 * @param mv
	 * @param acctType
	 */
	public void accountType(ModelAndView mv, Long acctType) {
		if(acctType == 100){
			mv.addObject("acctType", "Checking Account");
		}
		else if(acctType == 200){
			mv.addObject("acctType", "Savings Account");
		}
	}

	/**
	 * @param httpHeaders
	 * @return RestTemplate
	 */
	public RestTemplate createRestTemp(HttpHeaders httpHeaders) {
		httpHeaders.add("Content-Type", "*/*");
		RestTemplate rest = new RestTemplate();
		rest.getMessageConverters().add(new StringHttpMessageConverter());
		return rest;
	}


	/**
	 * @return ModelAndView
	 */
	@RequestMapping(value="loginFailure", method = RequestMethod.POST)
	public ModelAndView loginFailure(){
		ModelAndView exception = new ModelAndView("LoginException");
		exception.addObject("exception", "Invalid username or password. Please try again");
		return exception;
	}
}