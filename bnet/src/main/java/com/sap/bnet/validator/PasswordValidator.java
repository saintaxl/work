/**
 * © 2014 SAP AG. All rights reserved.
 */
package com.sap.bnet.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.sap.bnet.model.SubscriptionRequest;
import com.sap.bnet.services.ISldService;

/**
 * @title 
 * @description 
 * @usage 
 * @author I075320<shawn.wang@sap.com>
 * @version PasswordValidator.java,v 1.0
 * @create Sep 28, 2014 4:00:49 PM
 */
public class PasswordValidator implements Validator{
	
	private ISldService sldServices;
	
	public void setSldServices(ISldService sldServices) {
		this.sldServices = sldServices;
	}

	public boolean supports(Class<?> clazz) {
		return SubscriptionRequest.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		Integer status = sldServices.checkUserPassword((SubscriptionRequest)target);
		System.out.println(status);
	}

}
