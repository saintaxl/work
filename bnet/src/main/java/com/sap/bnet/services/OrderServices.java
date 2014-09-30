/**
 * © 2014 SAP AG. All rights reserved.
 */
package com.sap.bnet.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sap.bnet.constant.Operator;
import com.sap.bnet.controller.receiveOrderController;
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
	
	public Logger logger = LoggerFactory.getLogger(receiveOrderController.class);
	
	@Value("${productId}")
	private String productId;
	
	@Value("${bizId}")
	private String bizId;
	
	@Value("${siId}")
	private String siId;
	
	@Autowired
	private ITradeService tradeService;

	public Map<Operator,Object> addOrder(String streamingNo, String rand,String encode) {
		SimpleDateFormat format = new SimpleDateFormat("yyyymmddhhssmm");
		
		Map<Operator,Object> resMap = new HashMap<Operator, Object>();
		String xml = tradeService.getPortalRequest(streamingNo, rand);
		String opflag = StringUtils.substringBetween(xml,"<OPFlag>", "</OPFlag>");
		String xmlMd5 = tradeService.getEncodeString(xml);
		if(!encode.equals(xmlMd5)){
			logger.warn("respone data not match original data");
			String resXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
					      + "<Package>"
					      +     "<StreamingNo>" + streamingNo + "</StreamingNo>"
					      +     "<OPFlag>" + opflag + "</OPFlag>"
					      +     "<ReturnStatus>00501</ReturnStatus>"
					      +     "<Summary></Summary>"
					      + "</Package>";
			String returnXml = tradeService.getPortalResult(resXml);
			String redirectUrl = StringUtils.substringBetween(returnXml,"<ReturnUrl>", "</ReturnUrl>");
			resMap.put(Operator.Redirect, redirectUrl);
			return resMap;
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
					
				}
				
			}
		}
		return resMap;
	}

}
