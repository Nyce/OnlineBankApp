package com.bank.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.bank.model.Transaction;

public class TransactionValidator implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isNull(Transaction txn){
		Boolean isNull = false;
		if(StringUtils.isBlank(txn.getAmount().toString()) || StringUtils.isBlank(txn.getTxnType().toString()) 
				|| StringUtils.isBlank(txn.getDescription())){
			isNull = true;
		}
		return isNull;
	}
}
