/**
 * ProdServiceServiceTest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
package com.sap.bnet;

/*
 *  ProdServiceServiceTest Junit test case
 */

public class ProdServiceServiceTest extends junit.framework.TestCase {
	
	protected void setUp() throws Exception {
		System.getProperties().setProperty("http.proxyHost", "10.48.127.169");
		System.getProperties().setProperty("http.proxyPort", "8080");
    }

	
	private String targetEndpoint = "http://118.85.200.62/services/TradeService?wsdl";

	/**
	 * Auto generated test method
	 */
	public void testgetEncodeString() throws java.lang.Exception {
		com.sap.bnet.ws.stub.ProdServiceServiceStub stub = new com.sap.bnet.ws.stub.ProdServiceServiceStub(targetEndpoint,3000L);// the
																												// default
																												// implementation
																												// should
																												// point
																												// to
																												// the
																												// right
																												// endpoint

		org.apache.axiom.om.OMElement getEncodeString18 = (org.apache.axiom.om.OMElement) getTestObject(org.apache.axiom.om.OMElement.class);
		// TODO : Fill in the getEncodeString18 here

		assertNotNull(stub.getEncodeString(getEncodeString18));

	}

	/**
	 * Auto generated test method
	 */
	public void testStartgetEncodeString() throws java.lang.Exception {
		com.sap.bnet.ws.stub.ProdServiceServiceStub stub = new com.sap.bnet.ws.stub.ProdServiceServiceStub(targetEndpoint,3000L);
		org.apache.axiom.om.OMElement getEncodeString18 = (org.apache.axiom.om.OMElement) getTestObject(org.apache.axiom.om.OMElement.class);
		// TODO : Fill in the getEncodeString18 here

		stub.startgetEncodeString(getEncodeString18, new tempCallbackN1000C());

	}

	private class tempCallbackN1000C extends com.sap.bnet.ws.stub.ProdServiceServiceCallbackHandler {
		public tempCallbackN1000C() {
			super(null);
		}

		public void receiveResultgetEncodeString(org.apache.axiom.om.OMElement result) {

		}

		public void receiveErrorgetEncodeString(java.lang.Exception e) {
			fail();
		}

	}

	/**
	 * Auto generated test method
	 */
	public void testgetPortalRequest() throws java.lang.Exception {
		com.sap.bnet.ws.stub.ProdServiceServiceStub stub = new com.sap.bnet.ws.stub.ProdServiceServiceStub(targetEndpoint,3000L);// the
																												// default
																												// implementation
																												// should
																												// point
																												// to
																												// the
																												// right
																												// endpoint

		org.apache.axiom.om.OMElement getPortalRequest20 = (org.apache.axiom.om.OMElement) getTestObject(org.apache.axiom.om.OMElement.class);
		// TODO : Fill in the getPortalRequest20 here

		assertNotNull(stub.getPortalRequest(getPortalRequest20));

	}

	/**
	 * Auto generated test method
	 */
	public void testStartgetPortalRequest() throws java.lang.Exception {
		com.sap.bnet.ws.stub.ProdServiceServiceStub stub = new com.sap.bnet.ws.stub.ProdServiceServiceStub(targetEndpoint,3000L);
		org.apache.axiom.om.OMElement getPortalRequest20 = (org.apache.axiom.om.OMElement) getTestObject(org.apache.axiom.om.OMElement.class);
		// TODO : Fill in the getPortalRequest20 here

		stub.startgetPortalRequest(getPortalRequest20, new tempCallbackN10037());

	}

	private class tempCallbackN10037 extends com.sap.bnet.ws.stub.ProdServiceServiceCallbackHandler {
		public tempCallbackN10037() {
			super(null);
		}

		public void receiveResultgetPortalRequest(org.apache.axiom.om.OMElement result) {

		}

		public void receiveErrorgetPortalRequest(java.lang.Exception e) {
			fail();
		}

	}

	/**
	 * Auto generated test method
	 */
	public void testgetPortalResult() throws java.lang.Exception {
		com.sap.bnet.ws.stub.ProdServiceServiceStub stub = new com.sap.bnet.ws.stub.ProdServiceServiceStub(targetEndpoint,3000L);// the
																												// default
																												// implementation
																												// should
																												// point
																												// to
																												// the
																												// right
																												// endpoint

		org.apache.axiom.om.OMElement getPortalResult22 = (org.apache.axiom.om.OMElement) getTestObject(org.apache.axiom.om.OMElement.class);
		// TODO : Fill in the getPortalResult22 here

		assertNotNull(stub.getPortalResult(getPortalResult22));

	}

	/**
	 * Auto generated test method
	 */
	public void testStartgetPortalResult() throws java.lang.Exception {
		com.sap.bnet.ws.stub.ProdServiceServiceStub stub = new com.sap.bnet.ws.stub.ProdServiceServiceStub(targetEndpoint,3000L);
		org.apache.axiom.om.OMElement getPortalResult22 = (org.apache.axiom.om.OMElement) getTestObject(org.apache.axiom.om.OMElement.class);
		// TODO : Fill in the getPortalResult22 here

		stub.startgetPortalResult(getPortalResult22, new tempCallbackN10062());

	}

	private class tempCallbackN10062 extends com.sap.bnet.ws.stub.ProdServiceServiceCallbackHandler {
		public tempCallbackN10062() {
			super(null);
		}

		public void receiveResultgetPortalResult(org.apache.axiom.om.OMElement result) {

		}

		public void receiveErrorgetPortalResult(java.lang.Exception e) {
			fail();
		}

	}

	// Create an OMElement and provide it as the test object
	public org.apache.axiom.om.OMElement getTestObject(java.lang.Object dummy) {
		org.apache.axiom.om.OMFactory factory = org.apache.axiom.om.OMAbstractFactory.getOMFactory();
		org.apache.axiom.om.OMNamespace defNamespace = factory.createOMNamespace("", null);
		return org.apache.axiom.om.OMAbstractFactory.getOMFactory().createOMElement("test", defNamespace);
	}

}
