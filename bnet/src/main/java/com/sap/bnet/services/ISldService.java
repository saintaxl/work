/**
 * © 2014 SAP AG. All rights reserved.
 */
package com.sap.bnet.services;

import javax.servlet.http.HttpSession;

import com.sap.bnet.model.SubscriptionRequest;
import com.sap.bnet.model.SubscriptionResponse;


/**
 * @title 
 * @description 
 * @usage 
 * @author I075320<shawn.wang@sap.com>
 * @version SldServices.java,v 1.0
 * @create Sep 28, 2014 1:06:09 PM
 */
public interface ISldService {
	
    public boolean logonByServiceToken(HttpSession session);
    
    public boolean isEmailUnique(String email);
    
    public Integer checkUserPassword(SubscriptionRequest target);
    
    public SubscriptionResponse createSubscriptionRequest(SubscriptionRequest request);
    
    public void changeCustomer(String custName, String email,Integer licenseCount);
    
    public void unsubscribeCustomer(String custName, String email);
    
}
