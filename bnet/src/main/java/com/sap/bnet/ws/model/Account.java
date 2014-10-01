/**
 * 
 */
package com.sap.bnet.ws.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author Shawn
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Account implements Serializable{

	private static final long serialVersionUID = -8731548173524424490L;
	
	@XmlElement(name="UserID")
	private String userID;
	
	@XmlElement(name="IMSI")
	private String imsi;
	
	@XmlElement(name="AccType")
	private String accType;
	
	@XmlElement(name="AccName")
	private String accName;

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}
	

}
