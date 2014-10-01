/**
 * © 2014 SAP AG. All rights reserved.
 */
package com.sap.sld;

import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sap.bnet.model.TrialRequest;
import com.sap.bnet.model.TrialResponse;
import com.sap.bnet.sldclient.SldServices;

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
	private SldServices sldService;
	
	@Before
	public void before(){
		sldService.logonByServiceToken();
	}

	@Test
	public void createSubscriptionRequestTest() {
		TrialRequest request = new TrialRequest();
		
		
		TrialResponse result = sldService.createSubscriptionRequest(request);
		assertNotNull(result);
		//assertEquals(result, true);
	}

}
