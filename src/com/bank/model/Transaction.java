package com.bank.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class Transaction {
	@NotNull
	private  Long txnid;
	@NotNull
	private Integer txnType;
	@NotNull
	private BigDecimal amount;
	@NotNull
	private String description;
	@NotNull
	private Long accountid;
	
	public Transaction(){
		super();
	}

	public long getTxnid() {
		return txnid;
	}

	public void setTxnid(Long txnid) {
		this.txnid = txnid;
	}

	public Integer getTxnType() {
		return txnType;
	}

	public void setTxnType(Integer txnType) {
		this.txnType = txnType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getAccountid() {
		return accountid;
	}

	public void setAccountid(long accountid) {
		this.accountid = accountid;
	}
}