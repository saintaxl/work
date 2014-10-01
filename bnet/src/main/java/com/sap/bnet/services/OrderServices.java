/**
 * © 2014 SAP AG. All rights reserved.
 */
package com.sap.bnet.services;

import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sap.bnet.ws.facade.ITradeServiceFacade;
import com.sap.bnet.ws.model.Package;
import com.sap.bnet.ws.utils.JAXBUtils;

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
	
	public Logger logger = LoggerFactory.getLogger(OrderServices.class);
	
	@Value("${productId}")
	private String productId;
	
	@Value("${bizId}")
	private String bizId;
	
	@Value("${siId}")
	private String siId;
	
	@Autowired
	private ITradeServiceFacade tradefacade;

	public String analysis(String streamingNo, String rand,String encode) {
		SimpleDateFormat format = new SimpleDateFormat("yyyymmddhhssmm");
		String opflag = validation(streamingNo, rand, encode);
		return opflag;
		
		
		
		
	/*	if(!encode.equals(xmlMd5)){
			
		}else{
			if(opflag.equals("0101") || opflag.equals("0102") || opflag.equals("0103")){
				String customeId = StringUtils.substringBetween(xml,"<CustID>", "</CustID>");
				String custAccount = StringUtils.substringBetween(xml,"<CustAccount>", "</CustAccount>");
				String currentDate = format.format(new Date());
				
				String customerXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
						              +   "<Package>"
						              +        "<OPFlag>0104</OPFlag>"
						              +        "<SIID>" + siId + "</SIID>"
						              +        "<TimeStamp>" + currentDate + "</TimeStamp>"
						              +        "<ProductID>" + productId + "</ProductID>"
						              +        "<CustAccount>" + custAccount + "</custAccount>"
						              +        "<Summary>客户查询</Summary>" 
						              +   "</Package>";
				String customerResultXml = tradeService.getPortalResult(customerXml);
				String returnStatus = StringUtils.substringBetween(customerResultXml, "<ReturnStatus>", "</ReturnStatus>");
				logger.info("Search customer status: {}",returnStatus);
				
				if(!returnStatus.equals("00000")){
					
				}else{
					//客户开户
					if(opflag.equals("0101")){
						
					}else if(opflag.equals("0102")){//客户变更
						
					}else{//客户退订
						
					}
				}
				
			}
		}
		return resMap;*/
	}
	
	private String validation(String streamingNo, String rand,String encode){
		String xml = tradefacade.getPortalRequest(streamingNo, rand);
		Package packages = JAXBUtils.unmarshal(xml);
		String opflag = packages.getOpFlag();
		String xmlMd5 = tradefacade.getEncodeString(xml);
		if(!encode.equals(xmlMd5)){
			logger.warn("respone data not match original data");
			Package reqPackage = new Package();
			reqPackage.setStreamingNo(streamingNo);
			reqPackage.setOpFlag(opflag);
			reqPackage.setReturnStatus("");
			
			
			String resXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
					      + "<Package>"
					      +     "<StreamingNo>" + streamingNo + "</StreamingNo>"
					      +     "<OPFlag>" + opflag + "</OPFlag>"
					      +     "<ReturnStatus>00501</ReturnStatus>"
					      +     "<Summary></Summary>"
					      + "</Package>";
			String returnXml = tradefacade.getPortalResult(resXml);
			return StringUtils.substringBetween(returnXml,"<ReturnUrl>", "</ReturnUrl>");
		}
		return opflag;
	}

}
