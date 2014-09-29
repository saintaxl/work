/**
 * © 2014 SAP AG. All rights reserved.
 */
package com.sap.bnet.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * @title 
 * @description 
 * @usage 
 * @author I075320<shawn.wang@sap.com>
 * @version CallbackController.java,v 1.0
 * @create Sep 29, 2014 7:28:17 PM
 */

@Controller
public class CallbackController {

	public Logger logger = LoggerFactory.getLogger(CallbackController.class);
	
	@RequestMapping("/callback") 
	public ModelAndView callback(HttpServletRequest request,@ModelAttribute("order") OrderRequest order){
		ModelAndView mv = new ModelAndView();
		if(StringUtils.isEmpty(order.getStreamingNo()) || StringUtils.isEmpty(order.getRand()) || StringUtils.isEmpty(order.getEncode())){
			logger.error("Order request is Null [StreamingNo:{},Rand:{},Encode:{}]",order.getStreamingNo(),order.getRand(),order.getEncode());
			mv.setViewName("errorpage");
		}
		
		
		
		return mv;
	}
	
}
