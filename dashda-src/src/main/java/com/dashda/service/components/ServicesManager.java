/**
 * 
 */
package com.dashda.service.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.dashda.data.repositories.UserDao;

/**
 * @author mhanafy
 *
 */
@Transactional
public class ServicesManager {

	protected final Logger log = LoggerFactory.getLogger(ServicesManager.class);
	
}
