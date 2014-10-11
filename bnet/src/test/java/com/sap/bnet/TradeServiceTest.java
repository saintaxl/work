package com.sap.bnet;

import static org.junit.Assert.assertNotNull;

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

import com.sap.bnet.ws.facade.ITradeServiceFacade;
import com.sap.bnet.ws.model.PackageElement;
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
	private ITradeServiceFacade tradeService;
	
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
		//PackageElement packages = tradeService.getPortalRequest("12341", "casdyy");
		//assertNotNull(packages);
	}
	

}
