package com.sap.bnet.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class JSonData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -269052694250534300L;
	
	private Boolean LogonByServiceToken;
	
	private SubscriptionResponse CreateSubscriptionRequest;
	
	private List<Customer> results =  new ArrayList<Customer>();

	public Boolean isLogonByServiceToken() {
		return LogonByServiceToken;
	}

	public void setLogonByServiceToken(Boolean logonByServiceToken) {
		this.LogonByServiceToken = logonByServiceToken;
	}

	public SubscriptionResponse getCreateSubscriptionRequest() {
		return CreateSubscriptionRequest;
	}

	public void setCreateSubscriptionRequest(SubscriptionResponse createSubscriptionRequest) {
		CreateSubscriptionRequest = createSubscriptionRequest;
	}

	public List<Customer> getResults() {
		return results;
	}

	public void setResults(List<Customer> results) {
		this.results = results;
	}
}
