/**
 * 
 */
package com.sap.bnet;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sap.bnet.utils.ResourcesUtils;
import com.sap.bnet.ws.model.Package;
import com.sap.bnet.ws.utils.JAXBUtils;

/**
 * @author Shawn
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/spring/spring-ws.xml")
public class XMLMessageTest extends AbstractJUnit4SpringContextTests  {
	
	@Test
	public void openProduct(){
		String response = ResourcesUtils.getResourceContent("classpath:/META-INF/Product/OpenProductResponse.xml");
		assertNotNull(response);
		Package packagesResp = JAXBUtils.unmarshal(response);
		assertEquals(packagesResp.getStreamingNo(), "1141");
		assertEquals(packagesResp.getOpFlag(), "0101");
		
		String request = ResourcesUtils.getResourceContent("classpath:/META-INF/Product/OpenProductRequest.xml");
		assertNotNull(request);
		Package packageReq = JAXBUtils.unmarshal(request);
		assertEquals(packageReq.getOpFlag(), "0101");
	}
	
	
	@Test
	public void openChangeProduct(){
		String response = ResourcesUtils.getResourceContent("classpath:/META-INF/Product/ChangeProductResponse.xml");
		assertNotNull(response);
		Package packagesResp = JAXBUtils.unmarshal(response);
		assertEquals(packagesResp.getOpFlag(), "0102");
		
		String request = ResourcesUtils.getResourceContent("classpath:/META-INF/Product/ChangeProductRequest.xml");
		assertNotNull(request);
		Package packageReq = JAXBUtils.unmarshal(request);
		assertEquals(packageReq.getOpFlag(), "0102");
	}
	
	@Test
	public void unsubscribeProduct(){
		String response = ResourcesUtils.getResourceContent("classpath:/META-INF/Product/UnsubscribeProductResponse.xml");
		assertNotNull(response);
		Package packagesResp = JAXBUtils.unmarshal(response);
		assertEquals(packagesResp.getOpFlag(), "0103");
		
		String request = ResourcesUtils.getResourceContent("classpath:/META-INF/Product/UnsubscribeProductRequest.xml");
		assertNotNull(request);
		Package packageReq = JAXBUtils.unmarshal(request);
		assertEquals(packageReq.getOpFlag(), "0103");
	}
	
	@Test
	public void QueryCustomer(){
		String response = ResourcesUtils.getResourceContent("classpath:/META-INF/Customer/QueryResponse.xml");
		assertNotNull(response);
		Package packagesResp = JAXBUtils.unmarshal(response);
		assertEquals(packagesResp.getOpFlag(), "0104");
		
		String request = ResourcesUtils.getResourceContent("classpath:/META-INF/Customer/QueryRequest.xml");
		assertNotNull(request);
		Package packageReq = JAXBUtils.unmarshal(request);
		assertEquals(packageReq.getOpFlag(), "0104");
	}
	
	@Test
	public void authentication(){
		String request = ResourcesUtils.getResourceContent("classpath:/META-INF/User/AuthenticationResponse.xml");
		assertNotNull(request);
		Package packageReq = JAXBUtils.unmarshal(request);
		assertEquals(packageReq.getOpFlag(), "0205");
	}
	
	@Test
	public void boundProduct(){
		String response = ResourcesUtils.getResourceContent("classpath:/META-INF/User/BoundProductResponse.xml");
		assertNotNull(response);
		Package packagesResp = JAXBUtils.unmarshal(response);
		assertEquals(packagesResp.getOpFlag(), "0201");
		
		String request = ResourcesUtils.getResourceContent("classpath:/META-INF/User/BoundProductRequest.xml");
		assertNotNull(request);
		Package packageReq = JAXBUtils.unmarshal(request);
		assertEquals(packageReq.getOpFlag(), "0201");
	}
	
	@Test
	public void changeProduct(){
		String response = ResourcesUtils.getResourceContent("classpath:/META-INF/User/ChangeProductResponse.xml");
		assertNotNull(response);
		Package packagesResp = JAXBUtils.unmarshal(response);
		assertEquals(packagesResp.getOpFlag(), "0202");
		
		String request = ResourcesUtils.getResourceContent("classpath:/META-INF/User/ChangeProductRequest.xml");
		assertNotNull(request);
		Package packageReq = JAXBUtils.unmarshal(request);
		assertEquals(packageReq.getOpFlag(), "0202");
	}
	
	@Test
	public void queryUserInfo(){
		String response = ResourcesUtils.getResourceContent("classpath:/META-INF/User/QueryUserInfoResponse.xml");
		assertNotNull(response);
		Package packagesResp = JAXBUtils.unmarshal(response);
		assertEquals(packagesResp.getOpFlag(), "0204");
		
		String request = ResourcesUtils.getResourceContent("classpath:/META-INF/User/QueryUserInfoRequest.xml");
		assertNotNull(request);
		Package packageReq = JAXBUtils.unmarshal(request);
		assertEquals(packageReq.getOpFlag(), "0204");
	}
	
	@Test
	public void unboundProduct(){
		String response = ResourcesUtils.getResourceContent("classpath:/META-INF/User/UnboundProductResponse.xml");
		assertNotNull(response);
		Package packagesResp = JAXBUtils.unmarshal(response);
		assertEquals(packagesResp.getOpFlag(), "0203");
		
		String request = ResourcesUtils.getResourceContent("classpath:/META-INF/User/UnboundProductRequest.xml");
		assertNotNull(request);
		Package packageReq = JAXBUtils.unmarshal(request);
		assertEquals(packageReq.getOpFlag(), "0203");
	}
	
	

}
