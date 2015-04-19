package com.bank.validator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bank.model.Account;
import com.bank.model.Customer;
import com.bank.model.Transaction;
import com.bank.model.User;

public class CustomerValidator implements Validator {


	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Customer.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors e) {
		// TODO Auto-generated method stub
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("validator.props"));
		} catch (IOException ie) {
			System.out.println(ie);
		}
		Customer customer = (Customer) obj;
		String exceptionMessage = "";



		if (customer.getZip() < 00000) {
			e.rejectValue("zip", "negativevalue");
		} else if (customer.getZip() > 99999) {
			e.rejectValue("zip", "too many figures");
		}


	}


	public boolean isNull(Object obj){
		Boolean	isNull = false;
		Customer customer = (Customer) obj;
		

		if(StringUtils.isEmpty(customer.getEmail()) || StringUtils.isEmpty(customer.getFirstname()) || StringUtils.isEmpty(customer.getLastname())
				|| StringUtils.isEmpty(customer.getCity()) || StringUtils.isEmpty(customer.getZip().toString()) || StringUtils.isEmpty(customer.getDob().toString())
				|| StringUtils.isEmpty(customer.getState()) || StringUtils.isEmpty(customer.getSex()) || StringUtils.isEmpty(customer.getAddress()))
		{
			isNull = true;
		}
		
	
		return isNull;
	}

}
