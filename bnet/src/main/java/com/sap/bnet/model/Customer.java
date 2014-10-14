package com.sap.bnet.model;

import java.io.Serializable;

public class Customer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4545507015313492249L;

	private Integer ID;
	
	private String Status;
	
	private Integer MaxUsers;

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public Integer getMaxUsers() {
		return MaxUsers;
	}

	public void setMaxUsers(Integer maxUsers) {
		MaxUsers = maxUsers;
	}
}
