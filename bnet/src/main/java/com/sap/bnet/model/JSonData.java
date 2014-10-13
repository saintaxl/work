package com.sap.bnet.model;

import java.io.Serializable;


public class JSonData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -269052694250534300L;
	
	private Boolean LogonByServiceToken;
	
	private SubscriptionResponse CreateSubscriptionRequest;

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
}
