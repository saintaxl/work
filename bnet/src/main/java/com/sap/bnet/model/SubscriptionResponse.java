/**
 * © 2014 SAP AG. All rights reserved.
 */
package com.sap.bnet.model;

import java.io.Serializable;

/**
 * @title
 * @description
 * @usage
 * @author I075320<shawn.wang@sap.com>
 * @version TrialResponse.java,v 1.0
 * @create Sep 28, 2014 4:23:08 PM
 */
public class SubscriptionResponse implements Serializable{

	private static final long serialVersionUID = 5163857854521980959L;

	private Integer taskId;
	
	private String loginToken;
	
	private String customerToken;

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getLoginToken() {
		return loginToken;
	}

	public void setLoginToken(String loginToken) {
		this.loginToken = loginToken;
	}

	public String getCustomerToken() {
		return customerToken;
	}

	public void setCustomerToken(String customerToken) {
		this.customerToken = customerToken;
	}
	
}
