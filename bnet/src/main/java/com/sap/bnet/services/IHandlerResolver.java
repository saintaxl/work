/**
 * 
 */
package com.sap.bnet.services;

import javax.servlet.http.HttpSession;

import com.sap.bnet.ws.model.PackageElement;

/**
 * @author Shawn
 *
 */
public interface IHandlerResolver {

	public void handlerResult(HttpSession session,PackageElement portalResultResponse);
	
}
