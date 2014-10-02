/**
 * © 2014 SAP AG. All rights reserved.
 */
package com.sap.bnet.ws.facade;

import java.rmi.RemoteException;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sap.bnet.ws.model.PackageElement;
import com.sap.bnet.ws.stub.ProdServiceService;
import com.sap.bnet.ws.utils.JAXBUtils;

/**
 * @title 
 * @description 
 * @usage 
 * @author I075320<shawn.wang@sap.com>
 * @version TradeServiceClient.java,v 1.0
 * @create Sep 29, 2014 6:45:07 PM
 */
@Service
public class TradeServiceFacade implements ITradeServiceFacade{
	
	public static final Logger logger = LoggerFactory.getLogger(TradeServiceFacade.class);
	
	@Autowired
	private ProdServiceService tradeWebService;
	
	public PackageElement getPortalRequest(String streamingNo,String randNo){
		logger.debug("Call remote service [getPortalRequest] para [streamingNo:{} , randNo:{}]",streamingNo,randNo);
		OMFactory factory = OMAbstractFactory.getOMFactory();
		OMElement method = factory.createOMElement(new QName("getPortalRequest"));
		
		OMElement streamingno = factory.createOMElement(new QName("streamingno"));
		streamingno.setText(streamingNo);
		method.addChild(streamingno);
		
		OMElement rand = factory.createOMElement(new QName("rand"));
		rand.setText(randNo);
		method.addChild(rand);
		
		OMElement respElement = null;
		try {
			respElement = tradeWebService.getPortalRequest(method);
		} catch (RemoteException e) {
			logger.error("Call remote service getPortalRequestRequest fail {} streamingNo: {} randNo: {}",e,streamingNo,randNo);
			throw new RuntimeException(e);
		}
		
		if(respElement == null){
			throw new RuntimeException("no result data of remote service by getPortalRequest");
		}
		
		OMElement returnElement = respElement.getFirstChildWithName(new QName("getPortalRequestReturn"));
		if(returnElement == null){
			logger.error("no return data as xml of remote service by getPortalRequest streamingNo: {} randNo: {}",streamingNo,randNo);
			throw new RuntimeException("no return data as xml of remote service by getPortalRequest");
		}
		
		String portalRequestReturn = returnElement.getText();
		logger.debug("Call remote service [getPortalRequest] result XML :{}", portalRequestReturn);
		if(StringUtils.isEmpty(portalRequestReturn)){
			logger.error("no return data as xml of remote service by getPortalRequest streamingNo: {} randNo: {}",streamingNo,randNo);
			throw new RuntimeException("no return data as xml of remote service by getPortalRequest");
		}
		 
		return JAXBUtils.unmarshal(portalRequestReturn);
	}

	public String getEncodeString(String decode) {
		logger.debug("Call remote service [getEncodeString] para [decode:{}]",decode);
		OMFactory factory = OMAbstractFactory.getOMFactory();
		OMElement method = factory.createOMElement(new QName("getEncodeString"));
		
		OMElement decodeElement = factory.createOMElement(new QName("decode"));
		decodeElement.setText(decode);
		method.addChild(decodeElement);
		
		
		OMElement respElement = null;
		try {
			respElement = tradeWebService.getPortalRequest(method);
		} catch (RemoteException e) {
			logger.error("Call remote service getEncodeString fail {}",e);
			throw new RuntimeException(e);
		}
		
		if(respElement == null){
			logger.error("no result data of remote service by getEncodeString decode:{}",decode);
			throw new RuntimeException("no result data of remote service by getEncodeString");
		}
		
		OMElement returnElement = respElement.getFirstChildWithName(new QName("getEncodeStringReturn"));
		if(returnElement == null){
			logger.error("no return data as xml of remote service by getEncodeString decode:{}",decode);
			throw new RuntimeException("no return data as xml of remote service by getEncodeString");
		}
		
		String encode = returnElement.getText();
		logger.debug("Call remote service [getEncodeString] result XML :{}", encode);
		if(StringUtils.isEmpty(encode)){
			logger.error("no return data as encode of remote service by getEncodeString decode:{}",decode);
			throw new RuntimeException("no return data as encode of remote service by getEncodeString");
		}
		
		return encode;
	}

	@Override
	public PackageElement getPortalResult(String reqXML) {
		logger.debug("Call remote service [getPortalResult] para [reqXML:{}]",reqXML);
		OMFactory factory = OMAbstractFactory.getOMFactory();
		OMElement method = factory.createOMElement(new QName("getPortalResult"));
		
		OMElement decodeElement = factory.createOMElement(new QName("reqXML"));
		decodeElement.setText(reqXML);
		method.addChild(decodeElement);
		
		
		OMElement respElement = null;
		try {
			respElement = tradeWebService.getPortalResult(method);
		} catch (RemoteException e) {
			logger.error("Call remote service getPortalResult fail {} reqXML:{}", e ,reqXML);
			throw new RuntimeException(e);
		}
		
		if(respElement == null){
			logger.error("no result data of remote service by getPortalResult reqXML:{}",reqXML);
			throw new RuntimeException("no result data of remote service by getPortalResult");
		}
		
		OMElement returnElement = respElement.getFirstChildWithName(new QName("getPortalResultReturn"));
		if(returnElement == null){
			logger.error("no getPortalResultReturn of remote service by getPortalResult reqXML:{}",reqXML);
			throw new RuntimeException("no getPortalResultReturn of remote service by getPortalResult");
		}
		
		String portalResultReturn = returnElement.getText();
		logger.debug("Call remote service [getPortalResult] result :{}", portalResultReturn);
		if(StringUtils.isEmpty(portalResultReturn)){
			logger.error("no return data of remote service by getPortalResult reqXML:{}",reqXML);
			throw new RuntimeException("no return data of remote service by getPortalResult");
		}
		
		return JAXBUtils.unmarshal(portalResultReturn);
	}

}
