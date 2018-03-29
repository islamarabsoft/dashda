/**
 * 
 */
package com.dashda.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author mhanafy
 *
 */
@RestController
@RequestMapping("/agent")
public class AgentControllers {

	protected final Logger log = LoggerFactory.getLogger(AgentControllers.class);
	
	@RequestMapping("/agent-info")
	public String customerInfo() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		
		log.info("Agent");
		return "Agent";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) throws ServletException {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    
	    request.logout();
	    return "Username is :" + auth.getName();//You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}
}
