/**
 * 
 */
package com.dashda.data.repositories;

import com.dashda.data.repositories.AbstractDao;

import org.springframework.stereotype.Repository;

import com.dashda.data.entities.Contact;

/**
 * @author mhanafy
 *
 */
@Repository
public class ContactDaoImpl extends AbstractDao implements ContactDao {

	/* (non-Javadoc)
	 * @see com.dashda.data.repositories.ContactDao#createContact(com.dashda.data.entities.Contact)
	 */
	@Override
	public void createContact(Contact contact) {
		getSession().save(contact);
		getSession().flush();
		getSession().clear();

	}

}
