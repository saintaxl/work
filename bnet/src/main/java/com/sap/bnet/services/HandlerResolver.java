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
import com.sap.bnet.model.TrialRequest;
import com.sap.bnet.model.TrialResponse;
import com.sap.bnet.sldclient.SldServices;
import com.sap.bnet.ws.constant.OPFlag;
import com.sap.bnet.ws.model.PackageElement;

/**
 * @author Shawn
 *
 */
@Service
public class HandlerResolver implements IHandlerResolver {
	
	public Logger logger = LoggerFactory.getLogger(HandlerResolver.class);
	
	@Autowired
	private SldServices sldServices;

	public void handlerResult(HttpSession session ,PackageElement portalResultResponse) {
		if(session.getAttribute(USession.USER_ID) == null){
			sldServices.logonByServiceToken(session);
		}
		
		OPFlag opFlag = portalResultResponse.getOpFlag();
		try {
			switch (opFlag) {
				case CUST_OPEN_PRODUCT:
					TrialRequest trialRequest = new TrialRequest();
					TrialResponse trialResponse = sldServices.createSubscriptionRequest(trialRequest);
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
