package com.sap.bnet.services;

import java.io.Serializable;

public class JSonData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -269052694250534300L;
	
	private Boolean LogonByServiceToken;

	public Boolean isLogonByServiceToken() {
		return LogonByServiceToken;
	}

	public void setLogonByServiceToken(Boolean logonByServiceToken) {
		this.LogonByServiceToken = logonByServiceToken;
	}
}
