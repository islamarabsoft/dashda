/**
 * 
 */
package com.dashda.data.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
/**
 * @author mhanafy
 *
 */
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dashda.data.entities.EmployeeServiceProvider;
import com.dashda.data.entities.BaseEntity;
import com.dashda.data.entities.Employee;
 

public abstract class AbstractDao < T extends Serializable >{
 
	protected final Logger logDao = LoggerFactory.getLogger(AbstractDao.class);
	private Class< T > DAOClass;
	
    @Autowired
    private SessionFactory sessionFactory;

	protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
 
    public final void setDAOClass( Class< T > DAOClass ){
        this.DAOClass = DAOClass;
     }
    
    public Class<T> getDAOClass() {
		return DAOClass;
	}

	/**
     * Don't Use Before Setting <br>
     * DAOClass value on child class <br>
     * Like <br>
     * this.setDAOClass(MyDAOClass.class);
     */
    public T findOne(int id ){
        return (T) getSession().get(DAOClass, id );
     }
    /**
     * Don't Use Before Setting <br>
     * DAOClass value on child class <br>
     * Like <br>
     * this.setDAOClass(MyDAOClass.class);
     */
     public List< T > findAll(){
        return getSession().createQuery( "from " + DAOClass.getName() ).list();
     }
     
     /**
      * Don't Use Before Setting <br>
      * DAOClass value on child class <br>
      * Like <br>
      * this.setDAOClass(MyDAOClass.class);
      */
    public void persist(Object entity) {
        getSession().persist(entity);
    }
    /**
     * Don't Use Before Setting <br>
     * DAOClass value on child class <br>
     * Like <br>
     * this.setDAOClass(MyDAOClass.class);
     */
    public void delete(Object entity) {
        getSession().delete(entity);
    }
    
    public void save(BaseEntity entity) {
    	getSession().save(entity);
    	getSession().flush();
    	getSession().clear();
    }
    
    /**
     * Don't Use Before Setting <br>
     * DAOClass value on child class <br>
     * Like <br>
     * this.setDAOClass(MyDAOClass.class);
     */
     public void create( T entity ){
        getSession().persist( entity );
     }
   
     /**
      * Don't Use Before Setting <br>
      * DAOClass value on child class <br>
      * Like <br>
      * this.setDAOClass(MyDAOClass.class);
      */
     public void update( T entity ){
    	 getSession().merge( entity );
     }
   
     /**
      * Don't Use Before Setting <br>
      * DAOClass value on child class <br>
      * Like <br>
      * this.setDAOClass(MyDAOClass.class);
      */
     public void delete( T entity ){
    	 getSession().delete( entity );
     }
     
     /**
      * Don't Use Before Setting <br>
      * DAOClass value on child class <br>
      * Like <br>
      * this.setDAOClass(MyDAOClass.class);
      */
     public void deleteById( int entityId ) {
        T entity = findOne( entityId );
        delete( entity );
     }
     
     
     
 	public List<Object> findList(String hql, Map<String, Object> params) {
		Query query = getSession().createQuery(hql);
		params.forEach((k, v) -> query.setParameter(k, v));
		List<Object> results = query.list();
		
		return results;
	}
 	
 	
 	public Map findRowNative(String sql, Map<String, Object> params) {
 		
 		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
 		params.forEach((k, v) -> sqlQuery.setParameter(k, v));
 		
		sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Map result = (Map)sqlQuery.uniqueResult();
		
		return result;
 	}
 	
 	
 	public List<Map> findListNative(String sql, Map<String, Object> params) {
 		
 		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
 		params.forEach((k, v) -> sqlQuery.setParameter(k, v));
 		
		sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map> result = (List<Map>) sqlQuery.list();
		
		return result;
 	}
 	
 	public List<Map> findListNative(String sql, Map<String, Object> params, Map<String, Object[]> arrayParams) {
 		
 		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
 		params.forEach((k, v) -> sqlQuery.setParameter(k, v));
 		arrayParams.forEach((k, v) -> sqlQuery.setParameterList(k, v));
 		
		sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map> result = (List<Map>) sqlQuery.list();
		
		return result;
 	}
}