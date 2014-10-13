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

	private Integer TaskId;
	
	private String LoginToken;
	
	private String CustomerToken;
	
	public Integer getTaskId() {
		return TaskId;
	}

	public void setTaskId(Integer taskId) {
		TaskId = taskId;
	}

	public String getLoginToken() {
		return LoginToken;
	}

	public void setLoginToken(String loginToken) {
		LoginToken = loginToken;
	}

	public String getCustomerToken() {
		return CustomerToken;
	}

	public void setCustomerToken(String customerToken) {
		CustomerToken = customerToken;
	}

}
