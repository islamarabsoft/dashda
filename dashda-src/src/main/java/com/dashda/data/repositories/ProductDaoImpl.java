/**
 * 
 */
package com.dashda.data.repositories;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.dashda.data.entities.Account;
import com.dashda.data.entities.Product;
import com.dashda.data.entities.ProductSpecialty;
import com.dashda.data.entities.Specialty;

/**
 * @author mhanafy
 *
 */
@Repository
public class ProductDaoImpl extends AbstractDao implements ProductDao {

	public ProductDaoImpl() {
		super();
		this.setDAOClass(Product.class);
	}

	@Override
	public List<ProductSpecialty> findProductBySpecialty(Specialty specialty, Account account) {
		Criteria criteria = getSession().createCriteria(ProductSpecialty.class);
		
		criteria.createAlias("product", "p");
		criteria.createAlias("p.account", "a");
		
		criteria.add(Restrictions.eq("specialty", specialty));
		criteria.add(Restrictions.eq("a.id", account.getId()));
		
		return criteria.list();
	}

	@Override
	public Product findProductById(int productId) {
		return (Product)findOne(productId);
	}

	
	
}
