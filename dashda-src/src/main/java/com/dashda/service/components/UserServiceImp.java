/**
 * 
 */
package com.dashda.service.components;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.dashda.data.entities.User;
import com.dashda.data.repositories.UserDao;

/**
 * @author mhanafy
 *
 */
@Service("userService")
public class UserServiceImp extends ServicesManager implements UserService {

	/* (non-Javadoc)
	 * @see com.dashda.service.components.UserService#findListOfUsers()
	 */

	@Autowired
	private UserDao userDao;


	@Override
	public List<User> findListOfUsers() {
    	
		//beginTransaction();
		
		List<User> users = userDao.findAll();
		
		//commitTransaction();
		return users;
		
	}

}
