/**
 * 
 */
package com.sap.bnet.services;

import java.util.List;

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
import com.sap.bnet.ws.model.Product;

/**
 * @author Shawn
 *
 */
@Service
public class BusinessHandler implements IBusinessHandler {
	
	public Logger logger = LoggerFactory.getLogger(BusinessHandler.class);
	
	@Autowired
	private ISldService sldServices;

	public void handleResult(HttpSession session ,PackageElement portalRequestResponse,PackageElement portalResultResponse) {
		if(session.getAttribute(USession.USER_ID) == null){
			sldServices.logonByServiceToken(session);
		}
		openProduction4Cust(portalResultResponse);
		
		OPFlag opFlag = portalRequestResponse.getOpFlag();
		try {
			switch (opFlag) {
				case CUST_OPEN_PRODUCT:
					openProduction4Cust(portalResultResponse);
					break;
				case CUST_CHANGE_PRODUCT:
					changeProduction4Cust(portalResultResponse);
					break;
				case CUST_UNSUBSCRIBE_PRODUCT:
					unsubscribeProduction4Cust(portalResultResponse);
					break;
				case USER_BOUND_PRODUCT:
					
					break;
				case USER_CHANGE_PRODUCT:
					
					break;
				case USER_UNBOUND_PRODUCT:
					
					break;
				case USER_AUTHENTICATION:
					
					break;
				case USER_LOGON:
				
					break;
				default:
					break;
			}
		} catch (Exception e) {
			logger.error("Call SLD Service error by OPFlag {}",opFlag.getCode(),e);
			throw new RuntimeException("Call SLD Service exception:",e);
		}

	}

	private void openProduction4Cust(PackageElement dataPackage) {
		SubscriptionRequest subscriptionRequest = new SubscriptionRequest();
		subscriptionRequest.setCompany(dataPackage.getCustName());
		
		Product email = getEmail(dataPackage);
		subscriptionRequest.setEmail(email.getProductValue());
		Product license = getLicense(dataPackage);
		subscriptionRequest.setLicense(Integer.valueOf(license.getProductValue()));
		
//		subscriptionRequest.setCompany("Demo6");
//		subscriptionRequest.setEmail("Demo6@sap.com");
//		subscriptionRequest.setPassword("Initial6!");
//		subscriptionRequest.setLicense(5);
		sldServices.createSubscriptionRequest(subscriptionRequest);
	}
	
	private void changeProduction4Cust(PackageElement dataPackage) {
		Product license = getLicense(dataPackage);
		sldServices.changeCustomer(dataPackage.getCustName(),Integer.valueOf(license.getProductValue()));
	}

	private void unsubscribeProduction4Cust(PackageElement dataPackage) {
		sldServices.unsubscribeCustomer(dataPackage.getCustName());
	}

	private Product getEmail(PackageElement dataPackage) {
		Product email = null;
		List<Product> products = dataPackage.getProducts();
		if(null != products && products.size()>0){
			// 358310770030000000 : Email attribute type
			for(Product pro : products){
				if(pro.getProductType().equals("358310770030000000")){
					email = pro;
					break;
				}
			}
		}
		if(null == email){
			logger.error("Email not found!");
		}
		return email;
	}
	
	private Product getLicense(PackageElement dataPackage) {
		Product license = null;
		List<Product> products = dataPackage.getProducts();
		if(null != products && products.size()>0){
			// 358310760020000000 : License attribute type
			for(Product pro : products){
				if(pro.getProductType().equals("358310760020000000")){
					license = pro;
					break;
				}
			}
		}
		if(null == license){
			logger.error("License count not found!");
		}
		return license;
	}
}
