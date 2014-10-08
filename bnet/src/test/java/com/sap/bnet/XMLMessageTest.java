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
import com.sap.bnet.ws.constant.OPFlag;
import com.sap.bnet.ws.model.PackageElement;
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
		PackageElement packagesResp = JAXBUtils.unmarshal(response);
		assertEquals(packagesResp.getStreamingNo(), "1141");
		assertEquals(packagesResp.getOpFlag(), OPFlag.CUST_OPEN_PRODUCT);
		
		String request = ResourcesUtils.getResourceContent("classpath:/META-INF/Product/OpenProductRequest.xml");
		assertNotNull(request);
		PackageElement packageReq = JAXBUtils.unmarshal(request);
		assertEquals(packageReq.getOpFlag(), OPFlag.CUST_OPEN_PRODUCT);
	}
	
	
	@Test
	public void openChangeProduct(){
		String response = ResourcesUtils.getResourceContent("classpath:/META-INF/Product/ChangeProductResponse.xml");
		assertNotNull(response);
		PackageElement packagesResp = JAXBUtils.unmarshal(response);
		assertEquals(packagesResp.getOpFlag(), OPFlag.CUST_CHANGE_PRODUCT);
		
		String request = ResourcesUtils.getResourceContent("classpath:/META-INF/Product/ChangeProductRequest.xml");
		assertNotNull(request);
		PackageElement packageReq = JAXBUtils.unmarshal(request);
		assertEquals(packageReq.getOpFlag(), OPFlag.CUST_CHANGE_PRODUCT);
	}
	
	@Test
	public void unsubscribeProduct(){
		String response = ResourcesUtils.getResourceContent("classpath:/META-INF/Product/UnsubscribeProductResponse.xml");
		assertNotNull(response);
		PackageElement packagesResp = JAXBUtils.unmarshal(response);
		assertEquals(packagesResp.getOpFlag(), OPFlag.CUST_UNSUBSCRIBE_PRODUCT);
		
		String request = ResourcesUtils.getResourceContent("classpath:/META-INF/Product/UnsubscribeProductRequest.xml");
		assertNotNull(request);
		PackageElement packageReq = JAXBUtils.unmarshal(request);
		assertEquals(packageReq.getOpFlag(), OPFlag.CUST_UNSUBSCRIBE_PRODUCT);
	}
	
	@Test
	public void QueryCustomer(){
		String response = ResourcesUtils.getResourceContent("classpath:/META-INF/Customer/QueryResponse.xml");
		assertNotNull(response);
		PackageElement packagesResp = JAXBUtils.unmarshal(response);
		assertEquals(packagesResp.getOpFlag(), OPFlag.CUST_QUERY);
		
		String request = ResourcesUtils.getResourceContent("classpath:/META-INF/Customer/QueryRequest.xml");
		assertNotNull(request);
		PackageElement packageReq = JAXBUtils.unmarshal(request);
		assertEquals(packageReq.getOpFlag(), OPFlag.CUST_QUERY);
	}
	
	@Test
	public void authentication(){
		String request = ResourcesUtils.getResourceContent("classpath:/META-INF/User/AuthenticationResponse.xml");
		assertNotNull(request);
		PackageElement packageReq = JAXBUtils.unmarshal(request);
		assertEquals(packageReq.getOpFlag(), OPFlag.USER_AUTHENTICATION);
	}
	
	@Test
	public void boundProduct(){
		String response = ResourcesUtils.getResourceContent("classpath:/META-INF/User/BoundProductResponse.xml");
		assertNotNull(response);
		PackageElement packagesResp = JAXBUtils.unmarshal(response);
		assertEquals(packagesResp.getOpFlag(), OPFlag.USER_BOUND_PRODUCT);
		
		String request = ResourcesUtils.getResourceContent("classpath:/META-INF/User/BoundProductRequest.xml");
		assertNotNull(request);
		PackageElement packageReq = JAXBUtils.unmarshal(request);
		assertEquals(packageReq.getOpFlag(), OPFlag.USER_BOUND_PRODUCT);
	}
	
	@Test
	public void changeProduct(){
		String response = ResourcesUtils.getResourceContent("classpath:/META-INF/User/ChangeProductResponse.xml");
		assertNotNull(response);
		PackageElement packagesResp = JAXBUtils.unmarshal(response);
		assertEquals(packagesResp.getOpFlag(), OPFlag.USER_CHANGE_PRODUCT);
		
		String request = ResourcesUtils.getResourceContent("classpath:/META-INF/User/ChangeProductRequest.xml");
		assertNotNull(request);
		PackageElement packageReq = JAXBUtils.unmarshal(request);
		assertEquals(packageReq.getOpFlag(), OPFlag.USER_CHANGE_PRODUCT);
	}
	
	@Test
	public void queryUserInfo(){
		String response = ResourcesUtils.getResourceContent("classpath:/META-INF/User/QueryUserInfoResponse.xml");
		assertNotNull(response);
		PackageElement packagesResp = JAXBUtils.unmarshal(response);
		assertEquals(packagesResp.getOpFlag(), OPFlag.USER_QUERY);
		
		String request = ResourcesUtils.getResourceContent("classpath:/META-INF/User/QueryUserInfoRequest.xml");
		assertNotNull(request);
		PackageElement packageReq = JAXBUtils.unmarshal(request);
		assertEquals(packageReq.getOpFlag(), OPFlag.USER_QUERY);
	}
	
	@Test
	public void unboundProduct(){
		String response = ResourcesUtils.getResourceContent("classpath:/META-INF/User/UnboundProductResponse.xml");
		assertNotNull(response);
		PackageElement packagesResp = JAXBUtils.unmarshal(response);
		assertEquals(packagesResp.getOpFlag(), OPFlag.USER_UNBOUND_PRODUCT);
		
		String request = ResourcesUtils.getResourceContent("classpath:/META-INF/User/UnboundProductRequest.xml");
		assertNotNull(request);
		PackageElement packageReq = JAXBUtils.unmarshal(request);
		assertEquals(packageReq.getOpFlag(), OPFlag.USER_UNBOUND_PRODUCT);
	}
	
}
