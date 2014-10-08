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

	public void handleResult(HttpSession session ,PackageElement dataPackage) {
		if(session.getAttribute(USession.USER_ID) == null){
			sldServices.logonByServiceToken(session);
		}
		
		OPFlag opFlag = dataPackage.getOpFlag();
		try {
			switch (opFlag) {
				case CUST_OPEN_PRODUCT:
					openProduction4Cust(dataPackage);
					break;
				case CUST_CHANGE_PRODUCT:
					changeProduction4Cust(dataPackage);
					break;
				case CUST_UNSUBSCRIBE_PRODUCT:
					unsubscribeProduction4Cust(dataPackage);
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

	private void openProduction4Cust(PackageElement dataPackage) {
		SubscriptionRequest subscriptionRequest = new SubscriptionRequest();
		subscriptionRequest.setCompany(dataPackage.getCustName());
		SubscriptionResponse subscriptionResponse = sldServices.createSubscriptionRequest(subscriptionRequest);
	}
	
	private void changeProduction4Cust(PackageElement dataPackage) {
	}

	private void unsubscribeProduction4Cust(PackageElement dataPackage) {
	}
}
