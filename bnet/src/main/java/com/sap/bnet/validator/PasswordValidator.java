/**
 * © 2014 SAP AG. All rights reserved.
 */
package com.sap.bnet.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.sap.bnet.model.TrialRequest;
import com.sap.bnet.sldclient.SldServices;

/**
 * @title 
 * @description 
 * @usage 
 * @author I075320<shawn.wang@sap.com>
 * @version PasswordValidator.java,v 1.0
 * @create Sep 28, 2014 4:00:49 PM
 */
public class PasswordValidator implements Validator{
	
	private SldServices sldServices;
	
	public void setSldServices(SldServices sldServices) {
		this.sldServices = sldServices;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return TrialRequest.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Integer status = sldServices.checkUserPassword((TrialRequest)target);
		System.out.println(status);
	}

}
