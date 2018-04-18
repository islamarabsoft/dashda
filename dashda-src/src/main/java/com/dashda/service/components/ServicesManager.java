/**
 * 
 */
package com.dashda.service.components;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author mhanafy
 *
 */
@Transactional
public abstract class ServicesManager {
	
	
	@Value("${ERROR_CODE_1001}")
	protected String ERROR_CODE_1001;
	
	@Value("${ERROR_CODE_1002}")
	protected String ERROR_CODE_1002;
	

	@Value("${ERROR_CODE_1003}")
	protected String ERROR_CODE_1003;
	
	@Value("${ERROR_CODE_1004}")
	protected String ERROR_CODE_1004;
	
	@Value("${ERROR_CODE_1005}")
	protected String ERROR_CODE_1005;
	
	@Value("${ERROR_CODE_1006}")
	protected String ERROR_CODE_1006;
	
	@Value("${ERROR_CODE_1007}")
	protected String ERROR_CODE_1007;
	
	@Value("${ERROR_CODE_1008}")
	protected String ERROR_CODE_1008;
	
	@Value("${ERROR_CODE_1009}")
	protected String ERROR_CODE_1009;
	
	@Value("${ERROR_CODE_1010}")
	protected String ERROR_CODE_1010;
	
	@Value("${ERROR_CODE_1011}")
	protected String ERROR_CODE_1011;
	
	@Value("${ERROR_CODE_1012}")
	protected String ERROR_CODE_1012;
	
	@Value("${ERROR_CODE_1013}")
	protected String ERROR_CODE_1013;
	
	@Value("${ERROR_CODE_1014}")
	protected String ERROR_CODE_1014;
	
	/**
	 * THIS ATTRIBUTE NOT USED
	 */
	@Autowired
	PropertySourcesPlaceholderConfigurer properties;

	protected final Logger log = LoggerFactory.getLogger(ServicesManager.class);
	
	protected ModelMapper mapper = new ModelMapper();
}
