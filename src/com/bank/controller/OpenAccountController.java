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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;






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
	 * Maps to the bank app's first page
	 * This is method maps to the first page of the application
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

	/**
	 * Maps to the open account page
	 * @return ModelAndView
	 */
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
			@FormParam("city") String city, @FormParam("state") String state, @FormParam("zip") String zip, @FormParam("dob") Date dob, @FormParam("email") String email, 
			@FormParam("sex") String sex, @FormParam("username") String username, @FormParam("password") String password, @FormParam("accountType") Integer accountType,
			@FormParam("balance") BigDecimal balance, @FormParam("acct_description") String acct_description, @FormParam("amount") BigDecimal amount, 
			@FormParam("txnType") Integer txnType, @FormParam("txn_description") String txn_description, @ModelAttribute("customer") Customer customer, @ModelAttribute("user") User user, 
			@ModelAttribute("account") Account account, @ModelAttribute("txn") Transaction txn, BindingResult result, RedirectAttributes redirectAttrs) throws JSONException{

		//create new properties file to parse BankService URL
		Properties prop = parseProps();
		CustomerValidator customerValidator = new CustomerValidator();
		UserValidator userValidator = new UserValidator();

		//set customer credentials
		setCustomer(firstname, lastname, address, city, state, zip, dob, email,	sex, customer);
		//set user credentials
		setUser(username, password, user);
		//set account credentials
		setAccount(accountType, balance, account);
		//set txn credentials
		setTxn(amount, txnType, txn);


		if(customerValidator.isNull(customer) == true || userValidator.isNull(user) == true)
		{
			logger.error("Customer or user input fields contain null data");
			return registerExceptions(customer, user);
		}

		HttpHeaders httpHeaders = new HttpHeaders();

		JSONObject custObj = createCustomerJson(firstname, lastname, address, city,
				state, zip, dob, email, sex, username, password, accountType,
				balance, acct_description, amount, txnType, txn_description);

		HttpEntity<String> customerEntity = new HttpEntity<String>(custObj.toString(), httpHeaders);
		ResponseEntity<String> responseEntity = createRestTemp().exchange(prop.getProperty("open_account_url"),
				HttpMethod.POST, customerEntity, String.class);

		String customerObject = responseEntity.getBody();

		//if an empty string is returned from the service, validate the customer object
		if(StringUtils.isBlank(customerObject)){
			logger.error("Username already exists");
			return registerExceptions(customer, user);
		}

		ModelAndView success = showSuccessfulRegistration(customerObject);
		return success;
	}

	/**
	 * @param customerObject
	 * @return ModelAndView
	 * @throws JSONException
	 */
	public ModelAndView showSuccessfulRegistration(String customerObject)
			throws JSONException {
		JSONObject accountDetails = new JSONObject(customerObject); 
		String un = accountDetails.getString("username"); 
		ModelAndView success = new ModelAndView("welcome");
		success.addObject("username", un);
		success.addObject("message", " , your account has been created");
		return success;
	}

	/**
	 * This creates a JSON customer object
	 * @param firstname
	 * @param lastname
	 * @param address
	 * @param city
	 * @param state
	 * @param zip
	 * @param dob
	 * @param email
	 * @param sex
	 * @param username
	 * @param password
	 * @param accountType
	 * @param balance
	 * @param acct_description
	 * @param amount
	 * @param txnType
	 * @param txn_description
	 * @return JSONObject
	 * @throws JSONException
	 */
	public JSONObject createCustomerJson(String firstname, String lastname,
			String address, String city, String state, String zip, Date dob,
			String email, String sex, String username, String password,
			Integer accountType, BigDecimal balance, String acct_description,
			BigDecimal amount, Integer txnType, String txn_description)
					throws JSONException {
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
		return custObj;
	}

	/**
	 * @param amount
	 * @param txnType
	 * @param txn
	 */
	public void setTxn(BigDecimal amount, Integer txnType, Transaction txn) {
		txn.setAmount(amount);
		txn.setTxnType(txnType);
	}

	/**
	 * @param accountType
	 * @param balance
	 * @param account
	 */
	public void setAccount(Integer accountType, BigDecimal balance,
			Account account) {
		account.setBalance(balance);
		account.setAccountType(accountType);
	}

	/**
	 * Method sets new user properties
	 * @param username
	 * @param password
	 * @param user
	 */
	public void setUser(String username, String password, User user) {
		user.setUsername(username);
		user.setPassword(password);
	}

	/**
	 * Method sets new customer properties
	 * @param firstname
	 * @param lastname
	 * @param address
	 * @param city
	 * @param state
	 * @param zip
	 * @param dob
	 * @param email
	 * @param sex
	 * @param customer
	 */
	public void setCustomer(String firstname, String lastname, String address,
			String city, String state, String zip, Date dob, String email,
			String sex, Customer customer) {
		customer.setFirstname(firstname);
		customer.setLastname(lastname);
		customer.setAddress(address);
		customer.setCity(city);
		customer.setState(state);
		customer.setEmail(email);
		customer.setDob(dob);
		customer.setZip(zip);
		customer.setSex(sex);
	}

	/**
	 *  Maps to registration exception page
	 * @return ModelAndView
	 */
	@RequestMapping(value="/registerFailure", method = RequestMethod.GET)
	public ModelAndView registerFailure(){
		ModelAndView exception = new ModelAndView("registerException");
		exception.addObject("exception", "Your account was not successfully created. Please try again.");
		return exception;
	}

	/**
	 * Maps to register exception page and throws exceptions for invalid inputs
	 * @param customer
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/registerException", method = RequestMethod.GET)
	public ModelAndView registerExceptions(Customer customer, User user){
		ModelAndView register = new ModelAndView("registerFailure");
		if(StringUtils.isEmpty(customer.getFirstname())){
			register.addObject("fnException", "You must type in a first name");
		}
		else if(customer.getFirstname().matches(".*\\d+.*")){
			register.addObject("#fnException", "This field cannot contain numbers");
			register.addObject("fn", customer.getFirstname());
		}
		
		else {
			register.addObject("fn", customer.getFirstname());
		}

		if(StringUtils.isEmpty(customer.getLastname())){
			register.addObject("lnException", "You must type in a last name");
		}
		else if(customer.getLastname().matches(".*\\d+.*")){
			register.addObject("#lnException", "This field cannot contain numbers");
			register.addObject("lastname", customer.getLastname());
		}
		else {
			register.addObject("lastname", customer.getLastname());
		}

		if(StringUtils.isEmpty(customer.getCity()) ){
			register.addObject("cityException", "You must type in a city");
			register.addObject("city", customer.getCity());
		}
		else if(customer.getCity().matches(".*\\d+.*")){
			register.addObject("#cityException", "This field cannot contain numbers");
			register.addObject("city", customer.getCity());
		}
		else {
			register.addObject("city", customer.getCity());
		}
		
			if(StringUtils.isEmpty(customer.getZip())) {
				logger.debug("zip is null " + customer.getZip());
				register.addObject("zipException", "You must type in a zip code");
			}
			else if(customer.getZip().toString().matches("[a-zA-Z]+")) {
				register.addObject("letterZipException", "This field cannot contain letters");
				register.addObject("zip", customer.getZip());
			}
		
			else{
				register.addObject("zip", customer.getZip());
			}
			
		

		
		if(StringUtils.isEmpty(customer.getDob().toString())){
			register.addObject("dobException", "You must type in a dob");
		}
		else {
			register.addObject("dob", customer.getDob().toString());
		}
		
		if(StringUtils.isEmpty(customer.getAddress()) ){
			register.addObject("addressException", "You must type in an address");
		}
		else {
			register.addObject("address", customer.getAddress());
		}

		if(StringUtils.isEmpty(customer.getEmail())){
			register.addObject("emailException", "You must type in a email");
			
		}
		
		else {
			register.addObject("email", customer.getEmail());
		}
		if(StringUtils.isEmpty(customer.getSex())){
			register.addObject("sexException", "You must select a sex");
		}
		else {
			register.addObject("sex", customer.getSex());
		}
		if(StringUtils.isEmpty(user.getUsername())){
			register.addObject("usernameException", "You must type in a username");
		}
		else {
			//create new properties file to parse BankService URL
			Properties prop = parseProps();

			//create a new json object using the user properties
			JSONObject obj = createUserJson(user);
			//create new http header object for the restrul service
			HttpHeaders httpHeaders = new HttpHeaders();
			
			HttpEntity<String> entityCredentials = new HttpEntity<String>(obj.toString(), httpHeaders);
			ResponseEntity<String> usernameCheck = createRestTemp().exchange(prop.getProperty("duplicates_url"),
					HttpMethod.POST, entityCredentials, String.class);
			String username = usernameCheck.getBody(); 
			logger.debug("Username is " + username);

			if(StringUtils.isEmpty(username)){
				register.addObject("username", user.getUsername());
				
			}
			else if(username.equalsIgnoreCase("exists")){
				register.addObject("username", user.getUsername());
				register.addObject("usernameExists", "Username already exists! Choose another");
			}
		}

		register.addObject("username", user.getUsername());

		if(StringUtils.isEmpty(user.getPassword())){
			register.addObject("passwordException", "You must type in a pssword");
		}

		return register;
	}

	/**
	 * @param user
	 * @return JSONObject
	 */
	public JSONObject createUserJson(User user) {
		JSONObject 	obj = new JSONObject();
		try {
			obj.put("username", user.getUsername());
			obj.put("password", user.getPassword());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}




	/**
	 * @return RestTemplate
	 */
	public RestTemplate createRestTemp(){
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Type", "*/*");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		return restTemplate;
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

}

