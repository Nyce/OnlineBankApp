package com.bank.restfulcalls;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class RetireveBalanceImpl implements RetrieveBalance{

	@Override
	public String getBalance(String customerid) {

		  ClientConfig config = new DefaultClientConfig();
		  Client client = Client.create(config);
		  WebResource service = client.resource(getBaseURI());
		  String response = service.path("idToBal").accept(MediaType.TEXT_PLAIN).post(String.class, customerid);
		  
//		  System.out.println(service.path("service").path("idToBal").accept( MediaType.TEXT_PLAIN).get(ClientResponse.class).toString());
//		  
//		  System.out.println(service.path("service").path("idToBal").accept(MediaType.TEXT_PLAIN).get(String.class));
//	  
//		  System.out.println(service.path("service").path("idToBal").accept( MediaType.TEXT_XML).get(String.class));
//	 
//		  System.out.println(service.path("service").path("idToBal").accept( MediaType.TEXT_HTML).get(String.class));
		  return response;
	}



private  URI getBaseURI() {

    return UriBuilder.fromUri("http://localhost:8181/BankService/service").build();

  }
}