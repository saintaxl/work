/**
 * © 2014 SAP AG. All rights reserved.
 */
package com.sap.bnet.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sap.bnet.model.TrialRequest;
import com.sap.bnet.sldclient.SldServices;

/**
 * @title
 * @description
 * @usage
 * @author I075320<shawn.wang@sap.com>
 * @version TrialRequestValidator.java,v 1.0
 * @create Sep 28, 2014 3:48:01 PM
 */
public class EmailUniqueValidator implements Validator {

	private SldServices sldServices;
	
	public void setSldServices(SldServices sldServices) {
		this.sldServices = sldServices;
	}

	public boolean supports(Class<?> clazz) {
		return TrialRequest.class.equals(clazz);
	}

	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "username", null, "Username is empty.");
		TrialRequest user = (TrialRequest) obj;
		boolean isemail = sldServices.isEmailUnique(user.getEmail());
		System.out.println(isemail);
	}

}
