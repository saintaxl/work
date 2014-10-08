/**
 * © 2014 SAP AG. All rights reserved.
 */
package com.sap.bnet.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sap.bnet.model.SubscriptionRequest;
import com.sap.bnet.services.ISldService;

/**
 * @title
 * @description
 * @usage
 * @author I075320<shawn.wang@sap.com>
 * @version TrialRequestValidator.java,v 1.0
 * @create Sep 28, 2014 3:48:01 PM
 */
public class EmailUniqueValidator implements Validator {

	private ISldService sldServices;
	
	public void setSldServices(ISldService sldServices) {
		this.sldServices = sldServices;
	}

	public boolean supports(Class<?> clazz) {
		return SubscriptionRequest.class.equals(clazz);
	}

	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "username", null, "Username is empty.");
		SubscriptionRequest user = (SubscriptionRequest) obj;
		boolean isemail = sldServices.isEmailUnique(user.getEmail());
		System.out.println(isemail);
	}

}
