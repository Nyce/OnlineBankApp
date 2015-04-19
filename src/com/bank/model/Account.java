package com.bank.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class Account {
	@NotNull
	private  Long accountid;
	@NotNull
	private Integer accountType;
	@NotNull
	private BigDecimal balance;
	@NotNull
	private Long customerid;
	@NotNull
	private String description;
	
	public Account(){
		super();
	}

	public long getAccountid() {
		return accountid;
	}

	public void setAccountid(Long accountid) {
		this.accountid = accountid;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public long getCustomerid() {
		return customerid;
	}

	public void setCustomerid(Long customerid) {
		this.customerid = customerid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
