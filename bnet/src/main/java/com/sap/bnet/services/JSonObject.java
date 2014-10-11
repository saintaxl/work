package com.sap.bnet.services;

import java.io.Serializable;

public class JSonObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9079866007300311574L;
	
	private JSonData d;

	public JSonData getD() {
		return d;
	}

	public void setD(JSonData d) {
		this.d = d;
	}
}
