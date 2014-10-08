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
public interface IBusinessHandler {

	public void handleResult(HttpSession session,PackageElement portalResultResponse);
	
}
