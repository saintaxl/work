/**
 * © 2014 SAP AG. All rights reserved.
 */
package com.sap.bnet.services;

import java.io.IOException;
import java.lang.reflect.Method;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.JsonSerializable;
import org.core4j.Enumerable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.bnet.constant.USession;
import com.sap.bnet.model.JSonObject;
import com.sap.bnet.model.SubscriptionRequest;
import com.sap.bnet.model.SubscriptionResponse;
import com.sap.bnet.utils.JsonUtils;
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
	
	private HttpClient client;
	
	public Logger logger = LoggerFactory.getLogger(SldServiceImpl.class);

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
		try{
			HttpPost postMethod = new HttpPost(sldrooturl+"LogonByServiceToken");
			postMethod.addHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
			postMethod.addHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());
			
			postMethod.setEntity(new StringEntity("{Token:'"+ODCipher.getInstance().decrypt(sldServiceToken)+"'}",ContentType.APPLICATION_JSON));
			HttpResponse response = this.client.execute(postMethod);
			String reponseBody = EntityUtils.toString(response.getEntity(),"UTF-8");
			logger.info("Call login response: ["+reponseBody+"].");
			JSonObject object = JsonUtils.toObject(reponseBody, JSonObject.class);
			boolean logonByServiceToken = object.getD().isLogonByServiceToken();
			if(session!=null && logonByServiceToken){
	        	session.setAttribute(USession.USER_ID, logonByServiceToken);
	        	session.setMaxInactiveInterval(60 * 20);
	        }
	        return logonByServiceToken;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
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
		try{
			HttpPost postMethod = new HttpPost(sldrooturl+"CreateSubscriptionRequest");
			postMethod.addHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
			postMethod.addHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());
			
			String company = request.getCompany()==null?"":request.getCompany();
			String firstName = request.getFirstName()==null?"":request.getFirstName();
			String lastName = request.getLastName()==null?"":request.getLastName();
			String mobile = request.getMobile()==null?"":request.getMobile();
			String email = request.getEmail()==null?"":request.getEmail();
			String password = request.getPassword()==null?"":request.getPassword();
			String country = request.getCountry()==null?"":request.getCountry();
			String defaultLanguage = request.getDefaultLanguage()==null?"":request.getDefaultLanguage();
			String requestBody = "{SubscriptionRequest:{CompanyName:'"+company+"'," +
					"FirstName:'"+firstName+"'," +
					"LastName:'"+lastName+"'," +
					"Mobile:'"+mobile+"'," +
					"Email:'"+email+"'," +
					"Password:'"+password+"'," +
					"Country:'"+country+"'," +
					"DefaultLanguage:'"+defaultLanguage+"'}}";
			logger.info("Call CreateSubscriptionRequest params: ["+requestBody+"].");
			StringEntity stringEntity = new StringEntity(requestBody,ContentType.APPLICATION_JSON);
			postMethod.setEntity(stringEntity);
			HttpResponse response = this.client.execute(postMethod);
			String reponseBody = EntityUtils.toString(response.getEntity(),"UTF-8");
			logger.info("Call CreateSubscriptionRequest response: ["+reponseBody+"].");
			JSonObject object = JsonUtils.toObject(reponseBody, JSonObject.class);
			return object.getD().getCreateSubscriptionRequest();
		}catch(Exception e){
			throw new RuntimeException(e);
		}
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
	
	private void createConsumer() {
		SSLSocketFactory sf;
        try {
               sf = new SSLSocketFactory(new TrustStrategy() {
                     @Override
                     public boolean isTrusted(X509Certificate[] chain, String authType) {
                            return true;
                     }
               }, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        } catch (KeyManagementException | UnrecoverableKeyException | NoSuchAlgorithmException | KeyStoreException e) {
               throw new RuntimeException(e);
        }

        SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme("https", 443, sf ));
        registry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
        PoolingClientConnectionManager connManager = new PoolingClientConnectionManager(registry);
        connManager.setDefaultMaxPerRoute(50);
        connManager.setMaxTotal(200);
        HttpParams httpParams  = new BasicHttpParams(); 
        HttpConnectionParams.setConnectionTimeout(httpParams, 30000);

		client = new DefaultHttpClient(connManager,httpParams);
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
    
    class MyX509TrustManager implements X509TrustManager{

		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
    	
    }
}
