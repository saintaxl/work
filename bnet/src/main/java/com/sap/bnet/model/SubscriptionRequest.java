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
 * @version UserSubscription.java,v 1.0
 * @create Sep 28, 2014 3:29:49 PM
 */
public class SubscriptionRequest implements Serializable {

	private static final long serialVersionUID = -3464987053777487477L;

	private String company;
	
	private String email;
	
	private String password = "Initial4!";
	
	private String firstName="first name";
	
	private String lastName="last name";
	
	private String defaultLanguage = "15";
	
	private String mobile;

	private String captcha;
	
	private String country = "CN";
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	private String packageType = "Suite";
	
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDefaultLanguage() {
		return defaultLanguage;
	}

	public void setDefaultLanguage(String defaultLanguage) {
		this.defaultLanguage = defaultLanguage;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

}
