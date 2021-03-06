/**
 * © 2014 SAP AG. All rights reserved.
 */
package com.sap.sld;

import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sap.bnet.model.SubscriptionRequest;
import com.sap.bnet.model.SubscriptionResponse;
import com.sap.bnet.services.ISldService;

/**
 * @title
 * @description
 * @usage
 * @author I075320<shawn.wang@sap.com>
 * @version TestCreateSubscriptionRequest.java,v 1.0
 * @create Sep 28, 2014 4:29:57 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/spring/spring-ws.xml")
public class CreateSubscriptionRequestTest {
	
	@Resource
	private ISldService sldService;
	
	@Before
	public void before(){
		sldService.logonByServiceToken(null);
	}

	@Test
	public void createSubscriptionRequestTest() {
		SubscriptionRequest request = new SubscriptionRequest();
		SubscriptionResponse result = sldService.createSubscriptionRequest(request);
		assertNotNull(result);
		//assertEquals(result, true);
	}

}
