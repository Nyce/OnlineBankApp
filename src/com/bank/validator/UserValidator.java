package com.bank.validator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bank.model.Customer;
import com.bank.model.User;

public class UserValidator implements Validator {

	private static final int MINIMUM_PASSWORD_LENGTH = 8;
	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object obj, Errors e) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "username", "username cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "password", "password cannot be empty");

		User user = (User) obj;
		

			if (user.getPassword() != null
					&& user.getPassword().trim().length() < MINIMUM_PASSWORD_LENGTH) {
				e.rejectValue("password", "password.min.length",
						new Object[]{Integer.valueOf(MINIMUM_PASSWORD_LENGTH)},
						"The password must be at least [" + MINIMUM_PASSWORD_LENGTH + "] characters in length.");
			}
		}
	
	
	
	public boolean isNull(User user){
		Boolean isNull = false;
		 if(StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())){
			isNull = true;
		}
		 return isNull;
	}
}
