/**
 * 
 */
package com.dashda.data.repositories.user;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.dashda.data.entities.User;
import com.dashda.data.repositories.AbstractDao;

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

	@Override
	public User findUserByUsername(String username) {
		
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("username", username));
		
		return (User)criteria.uniqueResult();
	}

	@Override
	public void createUser(User user) {
		getSession().save(user);
		getSession().flush();
		getSession().clear();
		
	}

	@Override
	public User findActiveUserByUsernameAndPassword(String username, String password) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("username", username));
		//criteria.add(Restrictions.eq("password", password));
		criteria.add(Restrictions.eq("active", Byte.parseByte("1")));
		
		return (User)criteria.uniqueResult();
	}

}
