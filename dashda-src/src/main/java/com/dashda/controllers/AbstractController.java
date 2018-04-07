/**
 * 
 */
package com.dashda.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dashda.service.components.ServicesManager;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author mhanafy
 *
 */
public abstract class AbstractController {

	protected final Logger log = LoggerFactory.getLogger(AbstractController.class);
	
	protected ObjectMapper jsonObjectmapper = new ObjectMapper();
}
