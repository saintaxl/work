/**
 * © 2014 SAP AG. All rights reserved.
 */
package com.sap.bnet.services;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.core4j.Enumerable;

import com.sap.bnet.constant.USession;
import com.sap.bnet.model.SubscriptionRequest;
import com.sap.bnet.model.SubscriptionResponse;
import com.sap.businessone.odata4j.consumer.ODataConsumer;
import com.sap.businessone.odata4j.core.OComplexObject;
import com.sap.businessone.odata4j.core.OComplexObjects;
import com.sap.businessone.odata4j.core.OEntity;
import com.sap.businessone.odata4j.core.OFunctionParameters;
import com.sap.businessone.odata4j.core.OLink;
import com.sap.businessone.odata4j.core.OObject;
import com.sap.businessone.odata4j.core.OProperties;
import com.sap.businessone.odata4j.core.OProperty;
import com.sap.businessone.odata4j.core.OSimpleObject;
import com.sap.businessone.odata4j.core.OSimpleObjects;
import com.sap.businessone.odata4j.cxf.consumer.ODataCxfConsumer;
import com.sap.businessone.odata4j.edm.EdmComplexType;
import com.sap.businessone.odata4j.edm.EdmDataServices;
import com.sap.businessone.odata4j.edm.EdmSimpleType;
import com.sap.sbo.odatathinclient.ODataClientManager;
import com.sap.sbo.odatathinclient.ODataClientManagerFactory;
import com.sap.sbo.securestorage.ODCipher;

/**
 * @title
 * @description
 * @usage
 * @author I075320<shawn.wang@sap.com>
 * @version SldServicesImpl.java,v 1.0
 * @create Sep 28, 2014 1:15:16 PM
 */
public class SldServiceImpl implements ISldService {
	
	private ODataClientManager odataManager;
	
	private String sldServiceToken;
	
	private String sldrooturl;
	
	private ODataConsumer consumer;

    public void setConsumer(ODataConsumer consumer) {
        this.consumer = consumer;
    }
	
	public void setSldServiceToken(String sldServiceToken) {
		this.sldServiceToken = sldServiceToken;
	}
	
	public void setSldrooturl(String sldrooturl) {
		this.sldrooturl = sldrooturl;
	}

	public void setOdataManager(ODataClientManager odataManager) {
        this.odataManager = odataManager;
    }

	public boolean logonByServiceToken(HttpSession session) {
		OObject oToken = OSimpleObjects.create(EdmSimpleType.STRING, ODCipher.getInstance().decrypt(sldServiceToken));
        Enumerable<?> e = consumer.callFunction("LogonByServiceToken")
                .parameter(OFunctionParameters.create("Token", oToken)).execute();
        Boolean result = this.getSimpleResultValue(e, boolean.class);
        
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

	public Integer checkUserPassword(SubscriptionRequest target) {
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
	
	public SubscriptionResponse createSubscriptionRequest(SubscriptionRequest request) {
		EdmDataServices edmDataServices = consumer.getMetadata();
        EdmComplexType trailRequestParam = edmDataServices.findEdmComplexType("SubscriptionRequestParam");

        List<OProperty<?>> paramProperties = new ArrayList<OProperty<?>>();
        paramProperties.add(OProperties.string("CompanyName", request.getCompany()));
        paramProperties.add(OProperties.string("FirstName", request.getFirstName()));
        paramProperties.add(OProperties.string("LastName", request.getLastName()));
        paramProperties.add(OProperties.string("Mobile", request.getMobile()));
        paramProperties.add(OProperties.string("Email", request.getEmail()));
        paramProperties.add(OProperties.string("Password", request.getPassword()));
        paramProperties.add(OProperties.string("Country", request.getCountry()));
        paramProperties.add(OProperties.string("DefaultLanguage", request.getDefaultLanguage()));
        
        OComplexObject oObj = OComplexObjects.create(trailRequestParam, paramProperties);

        Enumerable<?> e = consumer.callFunction("CreateSubscriptionRequest")
                .parameter(OFunctionParameters.create("SubscriptionRequest", oObj)).execute();
        return extractTrialSubscriptionRequestResponse(e);
	}
	
	public void changeCustomer(String custName, String email,Integer licenseCount){
		Enumerable<OEntity> customers = this.consumer.getEntities("Customers").filter("Name eq '"+custName+"' and Email eq '"+email+"'").execute();
		if(customers.count() !=1){
			return;
		}
		OEntity customer = customers.first();
		OProperty<Integer> status = customer.getProperty("MaxUsers",Integer.class);
		status.setValue(licenseCount);
		
		this.consumer.updateEntity(customer);
	}

	@Override
	public void unsubscribeCustomer(String custName, String email) {
		Enumerable<OEntity> customers = this.consumer.getEntities("Customers").filter("Name eq '"+custName+"' and Email eq '"+email+"'").execute();
		if(customers.count() !=1){
			return;
		}
		OEntity customer = customers.first();
		OProperty<String> status = customer.getProperty("Status",String.class);
		status.setValue("Offline");
		
		OProperty<Integer> id = customer.getProperty("ID",Integer.class);
		Integer idValue = id.getValue();
		this.consumer.updateEntity(customer);
		
		Enumerable<OEntity> tenants = this.consumer.getEntities("Tenants").filter("Customer/ID eq "+idValue).execute();
		for(OEntity tenant : tenants){
			OProperty<String> statusPro = tenant.getProperty("Status",String.class);
			statusPro.setValue("Offline");
			this.consumer.updateEntity(tenant);
		}
	}  
	
	public SubscriptionResponse extractTrialSubscriptionRequestResponse(Enumerable<?> e) {
        Object first = e.first();
        Integer taskId = null;
        String loginToken = null;
        if (first instanceof OComplexObject) {
            OComplexObject comp = (OComplexObject) first;
            taskId = comp.getProperty("TaskId", Integer.class).getValue();
            try {
                loginToken = comp.getProperty("LoginToken", String.class).getValue();
            } catch (Exception ex) {
                    throw ex;
            }
        }
        return new SubscriptionResponse(taskId, loginToken);
    }

	public <T> T getSimpleResultValue(Enumerable<?> e, Class<T> type) {
        for (Object o : e) {
            if (OSimpleObject.class.isAssignableFrom(o.getClass())) {
                @SuppressWarnings("unchecked")
                OSimpleObject<T> value = (OSimpleObject<T>) o;
                return value.getValue();
            }
        }
        return null;
    }
	
	private void createConsumer() {
        if (consumer == null) {
            consumer = ODataCxfConsumer.create(this.addEndSlash(this.sldrooturl));
        }
        if (null == odataManager) {
            odataManager = ODataClientManagerFactory.getODataClientManager(this.sldrooturl);
        }
    }

    /**
     * add slash if sldEnpoint not end with slash.
     * 
     */
    private String addEndSlash(String sldEnpoint) {
        String sldEnpointRtn = sldEnpoint;
        if (!sldEnpointRtn.endsWith("/")) {
            sldEnpointRtn = sldEnpointRtn + "/";
        }
        return sldEnpointRtn;
    }

    /**
     * after spring set the property value.
     */
    @PostConstruct
    public void afterPropertiesSet() {
        createConsumer();
    }
}
