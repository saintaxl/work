/**
 * © 2014 SAP AG. All rights reserved.
 */
package com.sap.bnet.ws.facade;

import com.sap.bnet.ws.model.PackageElement;

/**
 * @title 
 * @description 
 * @usage 
 * @author I075320<shawn.wang@sap.com>
 * @version ITradeService.java,v 1.0
 * @create Sep 29, 2014 6:56:54 PM
 */
public interface ITradeServiceFacade {

	public PackageElement getPortalRequest(String streamingNo,String randNo);
	
	public String getEncodeString(String decode);

	public PackageElement getPortalResult(String reqXML);
	
}
