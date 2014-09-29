/**
 * ProdServiceServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package com.sap.bnet.ws.stub;

/**
 * ProdServiceServiceCallbackHandler Callback class, Users can extend this class
 * and implement their own receiveResult and receiveError methods.
 */
public abstract class ProdServiceServiceCallbackHandler {

	protected Object clientData;

	/**
	 * User can pass in any object that needs to be accessed once the
	 * NonBlocking Web service call is finished and appropriate method of this
	 * CallBack is called.
	 * 
	 * @param clientData
	 *            Object mechanism by which the user can pass in user data that
	 *            will be avilable at the time this callback is called.
	 */
	public ProdServiceServiceCallbackHandler(Object clientData) {
		this.clientData = clientData;
	}

	/**
	 * Please use this constructor if you don't want to set any clientData
	 */
	public ProdServiceServiceCallbackHandler() {
		this.clientData = null;
	}

	/**
	 * Get the client data
	 */

	public Object getClientData() {
		return clientData;
	}

	/**
	 * auto generated Axis2 call back method for getEncodeString method override
	 * this method for handling normal response from getEncodeString operation
	 */
	public void receiveResultgetEncodeString(org.apache.axiom.om.OMElement result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getEncodeString operation
	 */
	public void receiveErrorgetEncodeString(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getPortalRequest method
	 * override this method for handling normal response from getPortalRequest
	 * operation
	 */
	public void receiveResultgetPortalRequest(org.apache.axiom.om.OMElement result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getPortalRequest operation
	 */
	public void receiveErrorgetPortalRequest(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getPortalResult method override
	 * this method for handling normal response from getPortalResult operation
	 */
	public void receiveResultgetPortalResult(org.apache.axiom.om.OMElement result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getPortalResult operation
	 */
	public void receiveErrorgetPortalResult(java.lang.Exception e) {
	}

}
