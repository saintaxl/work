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
	/**
	 * 用户帐号
	 */
	@XmlElement(name="UserID")
	private String userID;
	
	/**
	 * 手机的IMSI
	 */
	@XmlElement(name="IMSI")
	private String imsi;
	
	/**
	 * 用户类型 ：1管理员 2：普通用户
	 */
	@XmlElement(name="AccType")
	private String accType;
	
	/**
	 * 用户名称
	 */
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
