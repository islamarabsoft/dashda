/**
 * 
 */
package com.dashda.data.repositories;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.dashda.data.entities.User;

/**
 * @author mhanafy
 *
 */
@Repository
public class UserDaoImpl extends AbstractDao implements UserDao {

	/* (non-Javadoc)
	 * @see com.dashda.data.repositories.UserDao#addUser(com.dashda.data.entities.User)
	 */
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.dashda.data.repositories.UserDao#editUser(com.dashda.data.entities.User, int)
	 */
	@Override
	public void editUser(User user, int userId) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.dashda.data.repositories.UserDao#deleteUser(int)
	 */
	@Override
	public void deleteUser(int userId) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.dashda.data.repositories.UserDao#find(int)
	 */
	@Override
	public User find(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.dashda.data.repositories.UserDao#findAll()
	 */
	@Override
	public List<User> findAll() {
		Criteria criteria = getSession().createCriteria(User.class);
		return criteria.list();
	}

}
