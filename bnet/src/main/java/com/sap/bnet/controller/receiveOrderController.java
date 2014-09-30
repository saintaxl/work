/**
 * © 2014 SAP AG. All rights reserved.
 */
package com.sap.bnet.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sap.bnet.constant.Operator;
import com.sap.bnet.services.IOrderServices;


/**
 * @title 
 * @description 
 * @usage 
 * @author I075320<shawn.wang@sap.com>
 * @version CallbackController.java,v 1.0
 * @create Sep 29, 2014 7:28:17 PM
 */

@Controller
public class receiveOrderController {

	public Logger logger = LoggerFactory.getLogger(receiveOrderController.class);
	
	@Autowired
	private IOrderServices orderServices;
	
	@RequestMapping("/receiveOrder") 
	public ModelAndView receiveOrder(HttpServletRequest request,@ModelAttribute("order") OrderRequest order){
		ModelAndView mv = new ModelAndView();
		HashMap modelMap = new HashMap();
		if(StringUtils.isEmpty(order.getStreamingNo()) || StringUtils.isEmpty(order.getRand()) || StringUtils.isEmpty(order.getEncode())){
			logger.error("Order request is Null [StreamingNo:{},Rand:{},Encode:{}]",order.getStreamingNo(),order.getRand(),order.getEncode());
			return new ModelAndView("error");
		}
		
		try {
			String opflag = orderServices.analysis(order.getStreamingNo(), order.getRand(),order.getEncode());
			if(opflag.startsWith("http://")){
				return new ModelAndView("redirect:"+ opflag);
			}
		} catch (Exception e) {
			logger.error("addOrder Services error {} the streamingNo {}",e.getMessage() ,order.getStreamingNo());
			modelMap.put("errormessage", e);
			return new ModelAndView("error",modelMap);
		}
		
		return mv;
	}
	
}
