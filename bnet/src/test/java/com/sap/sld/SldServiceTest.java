/**
 * © 2014 SAP AG. All rights reserved.
 */
package com.sap.sld;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sap.bnet.services.ISldService;

/**
 * @title
 * @description
 * @usage
 * @author I075320<shawn.wang@sap.com>
 * @version SldServiceTest.java,v 1.0
 * @create Sep 28, 2014 2:19:08 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/spring/spring-beans.xml")
public class SldServiceTest extends AbstractJUnit4SpringContextTests {

	@Resource
	private ISldService sldService;

	@Test
	public void logonByServiceToken() {
		boolean result = sldService.logonByServiceToken(null);
		assertNotNull(result);
		assertEquals(result, true);
	}
	
	public static void main(String[] args) {
		System.out.println("dddd");
	}

}
