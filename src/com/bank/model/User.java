package com.bank.model;

import javax.validation.constraints.NotNull;

public class User {
	
	@NotNull
	private  Long userid;
	@NotNull
	private String username;
	@NotNull
	private String password;
	@NotNull
	private Long customerid;
	
	public User() {
		super();
		}
	public void setUserID(Long id){
		this.userid = id;
	}
	
	public long getUserID(){
		return userid;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public String getUsername(){
		return username;
	}

	public void setPassword(String pword){
		this.password = pword;
	}
	
	public String getPassword(){
		return password;
	}
	public void setCustomerID(Long id){
		this.customerid = id;
	}
	
	public long getCustomerID(){
		return customerid;
	}
}
