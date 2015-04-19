package com.bank.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Properties;

import javax.ws.rs.FormParam;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.bank.model.Account;
import com.bank.model.Customer;
import com.bank.model.Transaction;
import com.bank.model.User;
import com.bank.validator.AccountValidator;
import com.bank.validator.CustomerValidator;
import com.bank.validator.TransactionValidator;
import com.bank.validator.UserValidator;


@Controller
public class OpenAccountController  {
	
	
	//logger used to log excepions and errors
	final static Logger logger = Logger.getLogger(OpenAccountController.class);

	
	/**
	 * 
	 * @return ModelAndView
	 */

	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView hello(){
		//this modelview adds the object to the jsp page
		ModelAndView index = new ModelAndView("index");
		index.addObject("openAccountMessage", "Create a new account");
		index.addObject("loginMessage", "Login");
		return index;
	}

	@RequestMapping(value="/register", method = RequestMethod.GET)
	public ModelAndView openAccount(){
		//this modelview adds the object to the jsp page
		ModelAndView openAccount = new ModelAndView("register");
		openAccount.addObject("message", "Please enter your account details");
		return openAccount;
	}

	/**
	 * model attribute passes customer object in the method
	 * Binding Result binds all the errors into a result reference
	 * @param customer
	 * @param result
	 * @return ModelAndView
	 * @throws JSONException 
	 */
	@RequestMapping(value="/welcome", method = RequestMethod.POST)

	public ModelAndView registerSuccess(@FormParam("firstname") String firstname, @FormParam("lastname") String lastname, @FormParam("address") String address, 
			@FormParam("city") String city, @FormParam("state") String state, @FormParam("zip") Long zip, @FormParam("dob") Date dob, @FormParam("email") String email, 
			@FormParam("sex") String sex, @FormParam("username") String username, @FormParam("password") String password, @FormParam("accountType") Integer accountType,
			@FormParam("balance") BigDecimal balance, @FormParam("acct_description") String acct_description, @FormParam("amount") BigDecimal amount, 
			@FormParam("txnType") Integer txnType, @FormParam("txn_description") String txn_description, @ModelAttribute("customer") Customer customer, @ModelAttribute("user") User user, 
			@ModelAttribute("account") Account account, @ModelAttribute("txn") Transaction txn,BindingResult result) throws JSONException{

		//Create new properties object
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("C:\\Development\\Workspace\\OnlineBankApp\\URL.props"));
		} catch (IOException ie) {
			ie.printStackTrace();
		}
		CustomerValidator customerValidator = new CustomerValidator();
		UserValidator userValidator = new UserValidator();

		customer.setFirstname(firstname);
		customer.setLastname(lastname);
		customer.setAddress(address);
		customer.setCity(city);
		customer.setState(state);
		customer.setEmail(email);
		customer.setDob(dob);
		customer.setZip(zip);
		customer.setSex(sex);

		user.setUsername(username);
		user.setPassword(password);
		
		account.setBalance(balance);
		account.setAccountType(accountType);
		
		txn.setAmount(amount);
		txn.setTxnType(txnType);
		
		if(customerValidator.isNull(customer) == true || userValidator.isNull(user) == true)
		{
			logger.error("Customer or user input fields contain null data");
			return registerFailure();
		}
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Type", "*/*");

		RestTemplate rest = new RestTemplate();
		rest.getMessageConverters().add(new StringHttpMessageConverter());

		JSONObject 	custObj = new JSONObject();	
		custObj.put("firstname", firstname);
		custObj.put("lastname", lastname);
		custObj.put("address", address);
		custObj.put("city", city);
		custObj.put("state", state);
		custObj.put("zip", zip);
		custObj.put("dob", dob);
		custObj.put("email", email);
		custObj.put("sex", sex);

		//		user credentials
		custObj.put("username", username);
		custObj.put("password", password);

		//		account credentials	
		custObj.put("accountType",accountType);
		custObj.put("balance", balance);
		custObj.put("acct_description",acct_description);

		//		transaction credentials
		custObj.put("amount", amount);
		custObj.put("txnType",txnType);
		custObj.put("txn_description", txn_description);

		HttpEntity<String> customerEntity = new HttpEntity<String>(custObj.toString(), httpHeaders);
		ResponseEntity<String> responseEntity = rest.exchange(prop.getProperty("open_account_url"),
				HttpMethod.POST, customerEntity, String.class);

		String customerObject = responseEntity.getBody();
		JSONObject accountDetails = new JSONObject(customerObject); 

		String un = accountDetails.getString("username"); 

		ModelAndView welcome = new ModelAndView("welcome");
		welcome.addObject("username", un);
		welcome.addObject("message", " , your account has been created");
		return welcome;
	}

	@RequestMapping(value="/registerFailure", method = RequestMethod.GET)
	public ModelAndView registerFailure(){
		ModelAndView exception = new ModelAndView("registerException");
		exception.addObject("exception", "Your account was not successfully created. Please try again.");
		return exception;
	}

}



