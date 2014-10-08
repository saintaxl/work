/**
 * © 2014 SAP AG. All rights reserved.
 */
package com.sap.bnet.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sap.bnet.services.IBusinessHandler;
import com.sap.bnet.services.IRemoteService;
import com.sap.bnet.ws.constant.OPFlag;
import com.sap.bnet.ws.model.PackageElement;


/**
 * @title 
 * @description 
 * @usage 
 * @author I075320<shawn.wang@sap.com>
 * @version CallbackController.java,v 1.0
 * @create Sep 29, 2014 7:28:17 PM
 */

@Controller
public class ReceiveOrderController {

	public Logger logger = LoggerFactory.getLogger(ReceiveOrderController.class);
	
	@Autowired
	private IRemoteService remoteServices;
	
	@Autowired
	private IBusinessHandler handler;
	
	@RequestMapping("/receiveOrder") 
	public void receiveOrder(HttpServletRequest request,@ModelAttribute("order") OrderRequest order){
		if(StringUtils.isEmpty(order.getStreamingNo()) || StringUtils.isEmpty(order.getRand()) || StringUtils.isEmpty(order.getEncode())){
			logger.error("Order request is Null [StreamingNo:{},Rand:{},Encode:{}]",order.getStreamingNo(),order.getRand(),order.getEncode());
			return ;
			//return new ModelAndView("error");
		}
		
		PackageElement portalResultResponse = null;
		try {
			PackageElement portalRequest = remoteServices.getPortalRequest(order.getStreamingNo(), order.getRand(),order.getEncode());
			OPFlag opFlag = portalRequest.getOpFlag();
			if(opFlag == opFlag.CUST_OPEN_PRODUCT || opFlag == opFlag.CUST_CHANGE_PRODUCT || opFlag == opFlag.CUST_UNSUBSCRIBE_PRODUCT ){
				portalResultResponse = remoteServices.queryCustomer(order.getStreamingNo(),portalRequest);
			}else if(opFlag == opFlag.USER_BOUND_PRODUCT || opFlag == opFlag.USER_CHANGE_PRODUCT || opFlag == opFlag.USER_UNBOUND_PRODUCT){
				portalResultResponse = remoteServices.queryUserInfo(order.getStreamingNo(),portalRequest);
			}else if(opFlag == opFlag.USER_AUTHENTICATION){
				portalResultResponse = remoteServices.getAuthentication(order.getStreamingNo(),portalRequest);
			}
		} catch (Exception e) {
			logger.error("RemoteServices Services error {} the streamingNo {}",e.getMessage() ,order.getStreamingNo());
			HashMap modelMap = new HashMap();
			modelMap.put("errormessage", e);
			return ;
			//return new ModelAndView("error",modelMap);
		}
		
		try {
			handler.handleResult(request.getSession(), portalResultResponse);
		} catch (Exception e) {
			logger.error("HandlerResolver Services error {} the streamingNo {}",e.getMessage() ,order.getStreamingNo());
			HashMap modelMap = new HashMap();
			modelMap.put("errormessage", e);
			return ;
			//return new ModelAndView("error",modelMap);
		}
		
		return ;
		//return new ModelAndView("success");
	}
	
}
