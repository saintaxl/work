package com.sap.bnet.model;

import java.io.Serializable;

public class TaskObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8871764321849037368L;
	
	private Task d;

	public Task getD() {
		return d;
	}

	public void setD(Task d) {
		this.d = d;
	}

}
