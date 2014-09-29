/**
 * © 2014 SAP AG. All rights reserved.
 */
package com.sap.bnet.controller;

import java.io.Serializable;

/**
 * @title 
 * @description 
 * @usage 
 * @author I075320<shawn.wang@sap.com>
 * @version OrderRequest.java,v 1.0
 * @create Sep 29, 2014 7:34:37 PM
 */
public class OrderRequest implements Serializable{

	private static final long serialVersionUID = 2420101874744872269L;
	
	private String streamingNo;
	
	private String rand;
	
	private String encode;

	public String getStreamingNo() {
		return streamingNo;
	}

	public void setStreamingNo(String streamingNo) {
		this.streamingNo = streamingNo;
	}

	public String getRand() {
		return rand;
	}

	public void setRand(String rand) {
		this.rand = rand;
	}

	public String getEncode() {
		return encode;
	}

	public void setEncode(String encode) {
		this.encode = encode;
	}
	

}
