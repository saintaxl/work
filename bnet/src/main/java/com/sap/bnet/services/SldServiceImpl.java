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
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
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
import com.sap.bnet.model.Customer;
import com.sap.bnet.model.JSonObject;
import com.sap.bnet.model.SubscriptionRequest;
import com.sap.bnet.model.SubscriptionResponse;
import com.sap.bnet.utils.JsonUtils;
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
	
	private String sldServiceToken;
	
	private String sldrooturl;
	
	private HttpClient client;
	
	public Logger logger = LoggerFactory.getLogger(SldServiceImpl.class);

	public void setSldServiceToken(String sldServiceToken) {
		this.sldServiceToken = sldServiceToken;
	}
	
	public void setSldrooturl(String sldrooturl) {
		this.sldrooturl = sldrooturl;
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
	
	public void changeCustomer(String custName,Integer licenseCount){
		try{
			HttpGet getMethod = new HttpGet(sldrooturl+"Customers?$filter=Name%20eq%20'"+custName+"'");
			getMethod.addHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
			getMethod.addHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());
			HttpResponse response = this.client.execute(getMethod);
			String reponseBody = EntityUtils.toString(response.getEntity(),"UTF-8");
			logger.info("Call customer query response: ["+reponseBody+"].");
			JSonObject object = JsonUtils.toObject(reponseBody, JSonObject.class);
			List<Customer> customers = null;
			if(null !=object.getD()){
				customers = object.getD().getResults();
			}
			if(null==customers ||customers.size() !=1){
				return;
			}
			Customer customer = customers.get(0);
			Integer id = customer.getID();
			HttpPut putMethod = new HttpPut(sldrooturl+"Customers("+id+")");
			putMethod.addHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
			putMethod.addHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());
			String requestBody = "{\"ID\":"+id+"," +
					"\"MaxUsers\":\""+licenseCount+"\"}";
			logger.info("Call customer update params: ["+requestBody+"].");
			StringEntity stringEntity = new StringEntity(requestBody,ContentType.APPLICATION_JSON);
			putMethod.setEntity(stringEntity);
			HttpResponse putResponse = this.client.execute(putMethod);
			String putReponseBody = EntityUtils.toString(putResponse.getEntity(),"UTF-8");
			logger.info("Call customer update response: ["+putReponseBody+"].");
		}catch(Exception ex){
			throw new RuntimeException(ex);
		}
	}

	@Override
	public void unsubscribeCustomer(String custName) {
		try{
			HttpGet getMethod = new HttpGet(sldrooturl+"Customers?$filter=Name%20eq%20'"+custName+"'");
			getMethod.addHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
			getMethod.addHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());
			HttpResponse response = this.client.execute(getMethod);
			String reponseBody = EntityUtils.toString(response.getEntity(),"UTF-8");
			logger.info("Call customer query response: ["+reponseBody+"].");
			JSonObject object = JsonUtils.toObject(reponseBody, JSonObject.class);
			List<Customer> customers = null;
			if(null !=object.getD()){
				customers = object.getD().getResults();
			}
			if(null==customers ||customers.size() !=1){
				return;
			}
			Customer customer = customers.get(0);
			Integer id = customer.getID();
			HttpPut putMethod = new HttpPut(sldrooturl+"Customers("+id+")");
			putMethod.addHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
			putMethod.addHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());
			String requestBody = "{\"ID\":"+id+"," +
					"\"Status\":\"Offline\"}";
			logger.info("Call customer update params: ["+requestBody+"].");
			StringEntity stringEntity = new StringEntity(requestBody,ContentType.APPLICATION_JSON);
			putMethod.setEntity(stringEntity);
			HttpResponse putResponse = this.client.execute(putMethod);
			String putReponseBody = EntityUtils.toString(putResponse.getEntity(),"UTF-8");
			logger.info("Call customer update response: ["+putReponseBody+"].");
		}catch(Exception ex){
			throw new RuntimeException(ex);
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
