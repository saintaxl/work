package com.sap.bnet.test;

import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.rmi.RemoteException;

import javax.annotation.Resource;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ResourceUtils;

import com.sap.bnet.ws.client.ITradeService;
import com.sap.bnet.ws.stub.ProdServiceServiceStub;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/spring/spring-ws.xml")
public class TradeServiceTest extends AbstractJUnit4SpringContextTests {
	
	@Before
	public void before(){
		//System.getProperties().setProperty("http.proxyHost", "10.48.127.169");
		//System.getProperties().setProperty("http.proxyPort", "8080");
	}
	
	@Resource
	private ProdServiceServiceStub tradeServiceStub;
	
	@Resource
	private ITradeService tradeService;
	
	@Test
	public void getPortalRequest() throws RemoteException {
		OMFactory factory = OMAbstractFactory.getOMFactory();
		//http://www.bnet.cn/v3.0/
		OMNamespace namespace =  factory.createOMNamespace("http://www.bnet.cn/v3.0/","");  
		OMElement method = factory.createOMElement("getPortalRequest", null);
		
		OMElement streamingno = factory.createOMElement("streamingno",null);
		streamingno.setText("1212124556");
		method.addChild(streamingno);
		
		OMElement rand = factory.createOMElement("rand",null);
		rand.setText("1477");
		method.addChild(rand);
		
		OMElement portalRequestResponse = tradeServiceStub.getPortalRequest(method);
		
		System.out.println("result:----------------");
		System.out.println(portalRequestResponse);  
	}
	
	@Test
	public void getPortalRequestFunction(){
		String responseXML = tradeService.getPortalRequest("12341", "casdyy");
		assertNotNull(responseXML);
	}
	
	@Test
	public void getEncodeString() throws Exception{
		File file = ResourceUtils.getFile("F:/git_repository/work/bnet/src/test/resources/META-INF/portalResponse.xml");
		BufferedReader reader = null;
		reader = new BufferedReader(new FileReader(file));
		StringBuffer sb = new StringBuffer();
		String tempString = null;
        while ((tempString = reader.readLine()) != null) {
        	sb.append(tempString);
        }
        reader.close();
        String xml = sb.toString();
		String responseXML = tradeService.getEncodeString(xml);
		assertNotNull(responseXML);
	}

}
