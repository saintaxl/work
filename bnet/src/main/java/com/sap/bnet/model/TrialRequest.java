/**
 * © 2014 SAP AG. All rights reserved.
 */
package com.sap.bnet.model;

import java.io.Serializable;

import org.springframework.validation.annotation.Validated;

import com.sap.bnet.validator.EmailUniqueValidator;

/**
 * @title
 * @description
 * @usage
 * @author I075320<shawn.wang@sap.com>
 * @version UserSubscription.java,v 1.0
 * @create Sep 28, 2014 3:29:49 PM
 */
public class TrialRequest implements Serializable {

	private static final long serialVersionUID = -3464987053777487477L;

	private String company;
	
	private String email;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private String localization;
	
	private String defaultLanguage;
	
	private String mobile;

	private String captcha;
	
	private Boolean includeDemoData;

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

	public String getLocalization() {
		return localization;
	}

	public void setLocalization(String localization) {
		this.localization = localization;
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

	public Boolean getIncludeDemoData() {
		return includeDemoData;
	}

	public void setIncludeDemoData(Boolean includeDemoData) {
		this.includeDemoData = includeDemoData;
	}
	
	

}
