package com.bank.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.capitalone.bank.model.Account;
import com.capitalone.bank.model.User;

@Controller
@RequestMapping("/")
public class TransferFundsController {

	//logger used to log excepions and errors
	final static Logger logger = Logger.getLogger(LoginController.class);

	/**
	 * @param user
	 * @param acctNumb
	 * @param request
	 * @return ModelAndView
	 * @throws JSONException
	 */
	@RequestMapping(value="/transfer/{acctNumb}",  method = RequestMethod.GET)
	public ModelAndView transfer( @ModelAttribute("user") User user, @PathVariable Long acctNumb, HttpServletRequest request) throws JSONException{

		ModelAndView mv = new ModelAndView("transfer");
		return mv;

	}

	

	/**
	 * This method maps to the withdrawal page
	 * @param user
	 * @param acctNumb
	 * @return ModelAndView
	 * @throws JSONException
	 */
	@RequestMapping(value="withdrawal/{acctNumb}",  method = RequestMethod.GET)
	public ModelAndView toWithdrawal(@ModelAttribute("account") Account account, @PathVariable Long acctNumb) throws JSONException{
		account.setCustomerid(acctNumb);
		Properties prop = parseProps();
		//create a new Http header to be used to communicate with the REST API
		HttpHeaders httpHeaders = new HttpHeaders();
		//create new rest template to send request and retrieve the response
		RestTemplate rest = createRestTemp(httpHeaders);
		JSONObject 	obj = new JSONObject();	
		obj.put("customerid", acctNumb);
		logger.debug("Account Number " + acctNumb);
		HttpEntity<String> entityCredentials = new HttpEntity<String>(obj.toString(), httpHeaders);
		ResponseEntity<String> responseEntity = rest.exchange(prop.getProperty("ret_bal_url"),
				HttpMethod.POST, entityCredentials, String.class);

		
		String acctObject = responseEntity.getBody();
		JSONObject accountDetails = new JSONObject(acctObject); 
		
		String bal = getBalFromJson(accountDetails);
		ModelAndView mv = new ModelAndView("withdrawal");
		mv.addObject("bal", bal);
		return mv;
	}
	
	/**
	 * This method maps to the deposit page
	 * @param user
	 * @param acctNumb
	 * @return ModelAndView
	 * @throws JSONException
	 */
	@RequestMapping(value="deposit/{acctNumb}",  method = RequestMethod.GET)
	public ModelAndView toDeposit( @ModelAttribute("account") Account account, @PathVariable Long acctNumb) throws JSONException{
		
		Properties prop = parseProps();
		//create a new Http header to be used to communicate with the REST API
		HttpHeaders httpHeaders = new HttpHeaders();
		
		//create new rest template to send request and retrieve the response
		RestTemplate rest = createRestTemp(httpHeaders);
		JSONObject 	obj = new JSONObject();	
		obj.put("customerid", acctNumb);
		
		HttpEntity<String> entityCredentials = new HttpEntity<String>(obj.toString(), httpHeaders);
		ResponseEntity<String> responseEntity = rest.exchange(prop.getProperty("ret_bal_url"),
				HttpMethod.POST, entityCredentials, String.class);

		String acctObject = responseEntity.getBody();
		JSONObject accountDetails = new JSONObject(acctObject); 
		
		String bal = getBalFromJson(accountDetails);
		ModelAndView mv = new ModelAndView("withdrawal");
		mv.addObject("bal", bal);
		return mv;

	}

	/**
	 * Method retrieves balance from the JSON Object
	 * @param accountDetails
	 * @return String
	 */
	public String getBalFromJson(JSONObject accountDetails) {
		String bal = "";
		//this checks null balance exception
		try {
			bal = accountDetails.getString("balance"); 
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		return bal;
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
	 * Method creates a rest template
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
		 * model attribute passes customer object in the method
		 * @param customer
		 * @param result
		 * @return ModelAndView
		 */
		@RequestMapping(value="/AccountDetails", method = RequestMethod.GET)
		public ModelAndView showAccount(){
			//this modelview adds the object to the jsp page
			ModelAndView mv = new ModelAndView("AccountDetails");
			return mv;
	
		}
	
		
		
		/**
		 * Withdraws money from account
		 * @param amount
		 * @param balance
		 * @return ModelAndView
		 */
		@RequestMapping(value="/newBalance", method = RequestMethod.POST)
		public ModelAndView withdrawnFunds(@RequestParam("amount") BigDecimal amount, @RequestParam("balance") BigDecimal balance){
			//this modelview adds the object to the jsp page
			ModelAndView mv = new ModelAndView("withdrawalConfirmation");
	
			int diff = balance.compareTo(amount);
			if(diff > 0){
				balance = balance.subtract(amount);
			}
			
			String message = "<p> Insufficient funds </p>";
	
			mv.addObject("bal", balance);
			mv.addObject("amount", amount);
			return mv;
	
		}
		
		/**
		 * Deposit money into account
		 * @param amount
		 * @param balance
		 * @return ModelAndView
		 */
		@RequestMapping(value="/updatedBalance", method = RequestMethod.POST)
		public ModelAndView depositedFunds(@RequestParam("amount") BigDecimal amount, @RequestParam("balance") BigDecimal balance){
			//this modelview adds the object to the jsp page
			ModelAndView mv = new ModelAndView("depositConfirmation");
			balance = balance.add(amount);
			mv.addObject("bal", balance);
			mv.addObject("amount", amount);
			return mv;

		}
		
}
