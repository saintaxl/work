/**
 * © 2014 SAP AG. All rights reserved.
 */
package com.sap.bnet.ws.client;

/**
 * @title 
 * @description 
 * @usage 
 * @author I075320<shawn.wang@sap.com>
 * @version ITradeService.java,v 1.0
 * @create Sep 29, 2014 6:56:54 PM
 */
public interface ITradeService {

	public String getPortalRequest(String streamingNo,String randNo);
	
	public String getEncodeString(String decode);

	public String getPortalResult(String resXml);
	
}
