/**
 * © 2014 SAP AG. All rights reserved.
 */
package com.sap.bnet.sldclient;

import com.sap.bnet.model.TrialRequest;
import com.sap.bnet.model.TrialResponse;


/**
 * @title 
 * @description 
 * @usage 
 * @author I075320<shawn.wang@sap.com>
 * @version SldServices.java,v 1.0
 * @create Sep 28, 2014 1:06:09 PM
 */
public interface SldServices {
	
    public boolean logonByServiceToken();
    
    public boolean isEmailUnique(String email);
    
    public Integer checkUserPassword(TrialRequest target);
    
    public TrialResponse createSubscriptionRequest(TrialRequest request);
    
}
