package com.sap.bnet.model;

import java.io.Serializable;

public class Task implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7322621341153209334L;
	
	private Integer ID;
	
	private String Status;

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
	
	

}
