package com.bank.validator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

import com.bank.model.Customer;

public class TestProps {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fn = null;
		String ln = null;
		String address = null;
		String city = null;
		String state = null;
		Long zip = null;
		String email = null;
		String un = null;
		String pw = null;
		String balance = null;
		String acct_type = null;
		String txn_type = null;
		String dob = null;
		String  description = null;
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("validator.props"));
		} catch (IOException ie) {
			System.out.println(ie);
		}

		Customer customer = new Customer();
		for(String key : properties.stringPropertyNames()) {
			String value = properties.getProperty(key);
			customer.setFirstname("Niyi");
			if(StringUtils.isBlank(customer.getFirstname())){
				key.equalsIgnoreCase("firstname.empty");
				String message = properties.getProperty(key);
				System.out.println(message);
				break;
			}
			else if(StringUtils.isEmpty(customer.getLastname())){
				key.equalsIgnoreCase("lastname.empty");
				String message = properties.getProperty(key);
				System.out.println(message);
			}
			else if(StringUtils.isEmpty(customer.getAddress())){
				key.equalsIgnoreCase("address.empty");
				String message = properties.getProperty(key);
				System.out.println(message);
			}
			else if(StringUtils.isEmpty(customer.getCity())){
				key.equalsIgnoreCase("city.empty");
				String message = properties.getProperty(key);
				System.out.println(message);
			}
			else if(StringUtils.isEmpty(customer.getState())){
				key.equalsIgnoreCase("state.empty");
				String message = properties.getProperty(key);
				System.out.println(message);
			}
			else if(StringUtils.isEmpty(customer.getZip().toString())){
				key.equalsIgnoreCase("zip.empty");
				String message = properties.getProperty(key);
				System.out.println(message);
			}
			else if(StringUtils.isEmpty(customer.getDob().toString())){
				key.equalsIgnoreCase("dob.empty");
				String message = properties.getProperty(key);
				System.out.println(message);
			}
			else if(StringUtils.isEmpty(customer.getEmail())){
				key.equalsIgnoreCase("email.empty");
				String message = properties.getProperty(key);
				System.out.println(message);
			}
			else if(StringUtils.isEmpty(customer.getSex())){
				key.equalsIgnoreCase("sex.empty");
				String message = properties.getProperty(key);
				System.out.println(message);
			}


		}
	}

}


