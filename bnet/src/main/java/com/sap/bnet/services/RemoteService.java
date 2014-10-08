/**
 * © 2014 SAP AG. All rights reserved.
 */
package com.sap.bnet.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sap.bnet.ws.constant.OPFlag;
import com.sap.bnet.ws.constant.RetunCode;
import com.sap.bnet.ws.facade.ITradeServiceFacade;
import com.sap.bnet.ws.model.Account;
import com.sap.bnet.ws.model.PackageElement;

/**
 * @title 
 * @description 
 * @usage 
 * @author I075320<shawn.wang@sap.com>
 * @version OrderServices.java,v 1.0
 * @create Sep 29, 2014 8:01:32 PM
 */
@Service
public class RemoteService implements IRemoteService {
	
	public Logger logger = LoggerFactory.getLogger(RemoteService.class);
	
	@Value("${productId}")
	private String productId;
	
	@Value("${bizId}")
	private String bizId;
	
	@Value("${siId}")
	private String siId;
	
	@Value("${password}")
	private String password;
	
	@Autowired
	protected ITradeServiceFacade tradefacade;

	public PackageElement getPortalRequest(String streamingNo, String rand,String encode) {
		PackageElement packages = tradefacade.getPortalRequest(streamingNo, rand);
		String ciphertext = tradefacade.getEncodeString(packages.toXML());
		if(!encode.equals(ciphertext)){
			PackageElement portalResultRequest = new PackageElement();
			portalResultRequest.setStreamingNo(streamingNo);
			portalResultRequest.setOpFlag(packages.getOpFlag());
			portalResultRequest.setReturnStatus(RetunCode.VERIFY_ERROR);
			portalResultRequest.setSummary("");
			String reqXML = portalResultRequest.toXML();
			logger.debug("call encode not match reqXML:{}",reqXML);
			tradefacade.getPortalResult(reqXML);
			logger.warn("respone data not match original data");
			throw new RuntimeException("encode not match");
		}
		PackageElement portalResultRequest = new PackageElement();
		portalResultRequest.setStreamingNo(streamingNo);
		portalResultRequest.setOpFlag(packages.getOpFlag());
		portalResultRequest.setReturnStatus(RetunCode.SUCCESSFUL);
		portalResultRequest.setSummary(packages.getSummary());
		String reqXML = portalResultRequest.toXML();
		logger.debug("call getPortalRequest response message by reqXML:{}",reqXML);
		tradefacade.getPortalResult(reqXML);
		return packages;
	}


	@Override
	public PackageElement queryCustomer(String streamingNo,PackageElement portalRequest) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		PackageElement portalResultRequest = new PackageElement();
		portalResultRequest.setStreamingNo(streamingNo);
		portalResultRequest.setOpFlag(OPFlag.CUST_QUERY);
		portalResultRequest.setSiId(siId);
		portalResultRequest.setTimeStamp(sdf.format(new Date()));
		portalResultRequest.setBizID(bizId);
		portalResultRequest.setAreaCode("");
		portalResultRequest.setProductId(productId);
		portalResultRequest.setPassword(password);
		portalResultRequest.setCustID(portalRequest.getCustID());
		portalResultRequest.setCustAccount(portalRequest.getCustAccount());
		portalResultRequest.setCustName(portalRequest.getCustName());
		portalResultRequest.setAccessNo(portalRequest.getAccessNo());
		portalResultRequest.setSummary("客户查询 ");
		String reqXML = portalResultRequest.toXML();
		logger.debug("call queryCustomer reqXML:{}",reqXML);
		PackageElement response = tradefacade.getPortalResult(reqXML);
		logger.debug("call queryCustomer respXML:{} ",response.toXML());
		return response;
	}


	@Override
	public PackageElement queryUserInfo(String streamingNo, PackageElement portalRequest) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		PackageElement portalResultRequest = new PackageElement();
		portalResultRequest.setStreamingNo(streamingNo);
		portalResultRequest.setOpFlag(OPFlag.USER_QUERY);
		portalResultRequest.setSiId(siId);
		portalResultRequest.setTimeStamp(sdf.format(new Date()));
		portalResultRequest.setProductId(productId);
		portalResultRequest.setPassword(password);
		portalResultRequest.setCustID(portalRequest.getCustID());
		portalResultRequest.setCustAccount(portalRequest.getCustAccount());
		List<Account> accounts = portalRequest.getAccounts();
		if(CollectionUtils.isNotEmpty(accounts)){
			String userID = accounts.get(0).getUserID();
			portalResultRequest.setUserId(userID);
		}
		portalResultRequest.setSummary("用户查询");
		String reqXML = portalResultRequest.toXML();
		logger.debug("call queryUserInfo reqXML:{}",reqXML);
		PackageElement response = tradefacade.getPortalResult(reqXML);
		logger.debug("call queryUserInfo respXML:{} ",response.toXML());
		return response;
	}


	@Override
	public PackageElement getAuthentication(String streamingNo, PackageElement portalRequest) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		PackageElement portalResultRequest = new PackageElement();
		portalResultRequest.setStreamingNo(streamingNo);
		portalResultRequest.setOpFlag(OPFlag.USER_LOGON);
		portalResultRequest.setTimeStamp(sdf.format(new Date()));
		portalResultRequest.setProductId(productId);
		portalResultRequest.setCustID(portalRequest.getCustID());
		portalResultRequest.setCustAccount(portalRequest.getCustAccount());
		portalResultRequest.setUserId(portalRequest.getUserId());
		portalResultRequest.setPassword(password);
		portalResultRequest.setSummary("客户端用户登录");
		String reqXML = portalResultRequest.toXML();
		logger.debug("call getAuthentication reqXML:{}",reqXML);
		PackageElement response = tradefacade.getPortalResult(reqXML);
		logger.debug("call getAuthentication respXML:{} ",response.toXML());
		return response;
	}


}
