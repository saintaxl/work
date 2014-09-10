/**
 * 
 */
package com.spring.demo;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Shawn
 *
 */
@Controller
public class UserLogonController {
	
	public Logger logger = LoggerFactory.getLogger(UserLogonController.class);
	
	@RequestMapping("/getUser") 
	public ModelAndView getUser(){
		logger.error("GetUserError {}",new Date());
		logger.info("GetUserInfo {}","info");
		logger.info("throw exception errorcode:[{}] | errormessage:{}","ER001032","no password");
		ModelAndView mv = new ModelAndView();  
		
		return mv;
	}

}
