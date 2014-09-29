/**
 * © 2014 SAP AG. All rights reserved.
 */
package com.sap.bnet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sap.bnet.ws.client.ITradeService;

/**
 * @title 
 * @description 
 * @usage 
 * @author I075320<shawn.wang@sap.com>
 * @version OrderServices.java,v 1.0
 * @create Sep 29, 2014 8:01:32 PM
 */
@Service
public class OrderServices implements IOrderServices{
	
	@Value("${productId}")
	private String siid;
	
	@Value("${bizId}")
	private String productId;
	
	@Value("${siId}")
	private String bizId;
	
	@Autowired
	private ITradeService tradeService;

	public void addOrder(String streamingNo, String rand) {
		
		String xml = tradeService.getPortalRequestRequest(streamingNo, rand);
		
	}

}
