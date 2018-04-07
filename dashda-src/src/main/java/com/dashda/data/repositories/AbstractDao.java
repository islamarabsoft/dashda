/**
 * 
 */
package com.dashda.data.repositories;

/**
 * @author mhanafy
 *
 */
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.dashda.service.components.ServicesManager;
 

public abstract class AbstractDao {
 
	protected final Logger logDao = LoggerFactory.getLogger(AbstractDao.class);
	
    @Autowired
    private SessionFactory sessionFactory;
 
    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
 
    public void persist(Object entity) {
        getSession().persist(entity);
    }
 
    public void delete(Object entity) {
        getSession().delete(entity);
    }
}