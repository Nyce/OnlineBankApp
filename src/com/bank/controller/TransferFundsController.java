package com.bank.controller;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.bank.model.User;
import com.bank.restfulcalls.RetireveBalanceImpl;
import com.bank.restfulcalls.RetrieveBalance;
@Controller
public class TransferFundsController {

	@RequestMapping(value="/transfer",  method = RequestMethod.GET)
	public ModelAndView toDeposit(@RequestParam("customerid") Long customerid, @ModelAttribute("user") User user, SessionStatus status, HttpServletRequest request) throws JSONException{

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Type", "*/*");
		JSONObject 	obj = new JSONObject();	

		String username =  (String) request.getSession().getAttribute(user.getUsername());
		String password =  (String) request.getSession().getAttribute(user.getPassword());

		obj.put("customerid", customerid.toString());

		RestTemplate rest = new RestTemplate();
		rest.getMessageConverters().add(new StringHttpMessageConverter());

		HttpEntity<String> entityCredentials = new HttpEntity<String>(obj.toString(), httpHeaders);
		ResponseEntity<String> responseEntity = rest.exchange("http://localhost:8181/BankService/service/idToBal",
				HttpMethod.POST, entityCredentials, String.class);


		//this modelview adds the object to the jsp page
		String bal = responseEntity.getBody();

		//		RetrieveBalance getBal = new RetireveBalanceImpl();
		//		String bal = getBal.getBalance(customerid.toString());
		ModelAndView mv = new ModelAndView("transfer");
		mv.addObject("bal", bal);
		//			mv.addObject("username", username);
		status.setComplete();
		return mv;

	}
	
	@RequestMapping(value="/updatedBalance", method = RequestMethod.POST)
	public ModelAndView depositedFunds(@RequestParam("amount") BigDecimal amount, @RequestParam("balance") BigDecimal balance){
		//this modelview adds the object to the jsp page
		ModelAndView mv = new ModelAndView("home");

		balance = balance.add(amount);

		mv.addObject("bal", balance);
		return mv;

	}

//	@RequestMapping(value="/withdrawal",  method = RequestMethod.GET)
//	public ModelAndView toWithdrawal(@RequestParam("customerid") Long customerid, @ModelAttribute("user") User user, SessionStatus status, HttpServletRequest request){
//
//		HttpHeaders httpHeaders = new HttpHeaders();
//		httpHeaders.add("Content-Type", "*/*");
//		JSONObject 	obj = new JSONObject();	
//
//		String username =  (String) request.getSession().getAttribute(user.getUsername());
//		String password =  (String) request.getSession().getAttribute(user.getPassword());
//
//		obj.put("customerid", customerid.toString());
//
//		RestTemplate rest = new RestTemplate();
//		rest.getMessageConverters().add(new StringHttpMessageConverter());
//
//		HttpEntity<String> entityCredentials = new HttpEntity<String>(obj.toString(), httpHeaders);
//		ResponseEntity<String> responseEntity = rest.exchange("http://localhost:8181/BankService/service/idToBal",
//				HttpMethod.POST, entityCredentials, String.class);
//
//
//		//this modelview adds the object to the jsp page
//		String bal = responseEntity.getBody();
//
//		//	RetrieveBalance getBal = new RetireveBalanceImpl();
//		//	String bal = getBal.getBalance(customerid.toString());
//		ModelAndView mv = new ModelAndView("transfer");
//		mv.addObject("bal", bal);
//		//		mv.addObject("username", username);
//		status.setComplete();
//		return mv;
//
//	}
//
//	/**
//	 * model attribute passes customer object in the method
//	 * @param customer
//	 * @param result
//	 * @return ModelAndView
//	 */
//	@RequestMapping(value="/AccountDetails", method = RequestMethod.GET)
//	public ModelAndView showAccount(){
//		//this modelview adds the object to the jsp page
//		ModelAndView mv = new ModelAndView("AccountDetails");
//		return mv;
//
//	}
//
//	
//	
//	@RequestMapping(value="/updatedBalanced", method = RequestMethod.POST)
//	public ModelAndView withdrawnFunds(@RequestParam("amount") BigDecimal amount, @RequestParam("balance") BigDecimal balance){
//		//this modelview adds the object to the jsp page
//		ModelAndView mv = new ModelAndView("home");
//
//		int diff = balance.compareTo(amount);
//		if(diff > 0){
//			balance = balance.subtract(amount);
//		}
//		
//		String message = "<p> Insufficient funds </p>";
//
//		mv.addObject("bal", balance);
//		return mv;
//
//	}
}
