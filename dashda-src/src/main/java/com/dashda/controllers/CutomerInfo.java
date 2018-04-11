/**
 * 
 */
package com.dashda.controllers;


import javax.servlet.http.HttpSession;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dashda.service.components.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author mhanafy
 *
 */
@RestController
@RequestMapping("/admin")
public class CutomerInfo {
	
	protected final Logger log = LoggerFactory.getLogger(CutomerInfo.class);
	
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping("/logout")
	public void logout(HttpSession session) {
		session.invalidate();
	}
	
}
