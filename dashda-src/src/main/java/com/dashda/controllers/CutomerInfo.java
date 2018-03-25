/**
 * 
 */
package com.dashda.controllers;


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
public class CutomerInfo {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/customer-info")
	public String customerInfo() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		
		return mapper.writeValueAsString(userService.findListOfUsers());
	}
}
