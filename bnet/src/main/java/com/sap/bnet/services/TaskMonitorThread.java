package com.sap.bnet.services;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.bnet.model.Customer;
import com.sap.bnet.model.JSonObject;
import com.sap.bnet.model.TaskObject;
import com.sap.bnet.utils.JsonUtils;

public class TaskMonitorThread extends Thread {
	private Integer taskId;
	private HttpClient client;
	private String customerName;
	private String sldrooturl;
	private Integer license;
	
	public Logger logger = LoggerFactory.getLogger(TaskMonitorThread.class);
	
	public TaskMonitorThread(Integer taskId,HttpClient client,String customerName,Integer license,String sldrooturl){
		this.taskId = taskId;
		this.client = client;
		this.customerName = customerName;
		this.sldrooturl = sldrooturl;
		this.license =  license;
	}
	
	public synchronized  void run(){
		while(true){
			try{
				HttpGet getMethod = new HttpGet(sldrooturl+"Tasks("+taskId+")");
				getMethod.addHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
				getMethod.addHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());
				HttpResponse response = this.client.execute(getMethod);
				String reponseBody = EntityUtils.toString(response.getEntity(),"UTF-8");
				logger.info("Call task query response: ["+reponseBody+"].");
				TaskObject object = JsonUtils.toObject(reponseBody, TaskObject.class);
				if(object.getD().getStatus().equals("Successful")){
					updateLicenseCount();
					break;
				}
				Thread.sleep(5000);
			}catch(Exception ex){
				logger.error("In customet provisioning, update max users failed.",ex);
			}
		}
    }

	private void updateLicenseCount() throws IOException, ClientProtocolException {
		HttpGet getMethod = new HttpGet(sldrooturl+"Customers?$filter=Name%20eq%20'"+customerName+"'");
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
				"\"MaxUsers\":\""+license+"\"}";
		logger.info("Call customer update params: ["+requestBody+"].");
		StringEntity stringEntity = new StringEntity(requestBody,ContentType.APPLICATION_JSON);
		putMethod.setEntity(stringEntity);
		HttpResponse putResponse = this.client.execute(putMethod);
		String putReponseBody = EntityUtils.toString(putResponse.getEntity(),"UTF-8");
		logger.info("Call customer update response: ["+putReponseBody+"].");
	}  
}
