/**
 * 
 */
package com.sap.bnet.services;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sap.bnet.constant.USession;
import com.sap.bnet.model.SubscriptionRequest;
import com.sap.bnet.model.SubscriptionResponse;
import com.sap.bnet.ws.constant.OPFlag;
import com.sap.bnet.ws.model.PackageElement;

/**
 * @author Shawn
 *
 */
@Service
public class BusinessHandler implements IBusinessHandler {
	
	public Logger logger = LoggerFactory.getLogger(BusinessHandler.class);
	
	@Autowired
	private ISldService sldServices;

	public void handleResult(HttpSession session ,PackageElement portalResultResponse) {
		if(session.getAttribute(USession.USER_ID) == null){
			sldServices.logonByServiceToken(session);
		}
		
		OPFlag opFlag = portalResultResponse.getOpFlag();
		try {
			switch (opFlag) {
				case CUST_OPEN_PRODUCT:
					SubscriptionRequest subscriptionRequest = new SubscriptionRequest();
					subscriptionRequest.setCompany(portalResultResponse.getCustName());
					SubscriptionResponse subscriptionResponse = sldServices.createSubscriptionRequest(subscriptionRequest);
					break;
				case CUST_CHANGE_PRODUCT:
					
					break;
				case CUST_UNSUBSCRIBE_PRODUCT:
					
					break;
				case USER_BOUND_PRODUCT:
					
					break;
				case USER_CHANGE_PRODUCT:
					
					break;
				case USER_UNBOUND_PRODUCT:
					
					break;
				case AUTHENTICATION:
					
					break;
				case LOGINON:
				
					break;
				default:
					break;
			}
		} catch (Exception e) {
			logger.error("Call SLD Service error by OPFlag {}",opFlag.getCode());
			throw new RuntimeException("Call SLD Service exception:",e);
		}

	}

}
