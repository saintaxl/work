/**
 * © 2014 SAP AG. All rights reserved.
 */
package com.sap.bnet.sldclient;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.sap.bnet.constant.USession;
import com.sap.bnet.model.TrialRequest;
import com.sap.bnet.model.TrialResponse;
import com.sap.sbo.odatathinclient.ODataClientManager;
import com.sap.sbo.securestorage.ODCipher;

/**
 * @title
 * @description
 * @usage
 * @author I075320<shawn.wang@sap.com>
 * @version SldServicesImpl.java,v 1.0
 * @create Sep 28, 2014 1:15:16 PM
 */
public class SldServicesImpl implements SldServices {
	
	private ODataClientManager odataManager;
	
	private String sldServiceToken;
	
	public void setSldServiceToken(String sldServiceToken) {
		this.sldServiceToken = sldServiceToken;
	}

	public void setOdataManager(ODataClientManager odataManager) {
        this.odataManager = odataManager;
    }

	public boolean logonByServiceToken(HttpSession session) {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("Token", ODCipher.getInstance().decrypt(sldServiceToken));
        Boolean result = odataManager.invokeFunction("LogonByServiceToken", parameters, Boolean.class);
        if(session!=null && result){
        	session.setAttribute(USession.USER_ID, result);
        	session.setMaxInactiveInterval(60 * 20);
        }
        return result;
	}

	public boolean isEmailUnique(String email) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("Email", email);
		Boolean res = odataManager.invokeFunction("IsEmailUnique", parameters, Boolean.class);
		return res;
	}

	public Integer checkUserPassword(TrialRequest target) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		String firstName = target.getFirstName();
        String lastName = target.getLastName();
        String email = target.getEmail();
        String password = target.getPassword();
        if (StringUtils.isNotEmpty(firstName)) {
        	parameters.put("FirstName", firstName);
        }
        if (StringUtils.isNotEmpty(lastName)) {
        	parameters.put("LastName", lastName);
        }
        if (StringUtils.isNotEmpty(email)) {
        	parameters.put("Email", email);
        }
        parameters.put("Password", password);
        
        return odataManager.invokeAction("CheckUserPasswordReturnWithCode", parameters, int.class);
	}
	
	public TrialResponse createSubscriptionRequest(TrialRequest request) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("CompanyName", request.getCompany());
		parameters.put("FirstName", request.getFirstName());
		parameters.put("LastName", request.getLastName());
		parameters.put("Mobile", request.getMobile());
		parameters.put("Email", request.getEmail());
		parameters.put("Password", request.getPassword());
		parameters.put("Country", request.getLocalization());

        // hard code to set default language for limited two country US and CN
		parameters.put("DefaultLanguage", request.getDefaultLanguage());
		parameters.put("IncludeDemoData", request.getIncludeDemoData());

		Object resp = odataManager.invokeFunction("CreateSubscriptionRequest", parameters, Object.class);
		
		return null;
	}  

}
