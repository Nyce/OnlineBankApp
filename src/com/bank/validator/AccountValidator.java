package com.bank.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.capitalone.bank.model.Account;

public class AccountValidator implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub

	}



	public boolean isNull(Account account){
		Boolean isNull = false;
		if(StringUtils.isBlank(account.getBalance().toString()) || StringUtils.isBlank(account.getAccountType().toString()) 
				|| StringUtils.isBlank(account.getDescription())){
			isNull = true;
		}
		return isNull;
	}
}
