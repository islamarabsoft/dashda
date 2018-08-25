/**
 * 
 */
package com.dashda.data.repositories.product;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.dashda.data.entities.Account;
import com.dashda.data.entities.Product;
import com.dashda.data.entities.ProductLine;
import com.dashda.data.entities.ProductSpecialty;
import com.dashda.data.entities.Specialty;
import com.dashda.data.repositories.AbstractDao;

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
	public List<ProductSpecialty> findProductBySpecialty(Specialty specialty, Account account,
			ProductLine productLine) {
		Criteria criteria = getSession().createCriteria(ProductSpecialty.class);
		
		criteria.createAlias("product", "p");
		criteria.createAlias("p.account", "a");
		criteria.createAlias("p.productLine", "pl");
		
		criteria.add(Restrictions.eq("specialty", specialty));
		criteria.add(Restrictions.eq("a.id", account.getId()));
		criteria.add(Restrictions.eq("pl.id", productLine.getId()));
		
		return criteria.list();
	}
	
	@Override
	public List<ProductSpecialty> findProductBySpecialty(Product product, Account account) {
		Criteria criteria = getSession().createCriteria(ProductSpecialty.class);
		
		criteria.createAlias("product", "p");
		criteria.createAlias("p.account", "a");
		
		criteria.add(Restrictions.eq("product", product));
		criteria.add(Restrictions.eq("a.id", account.getId()));
		
		return criteria.list();
	}

	
	@Override
	public Product findProductByIdAndAccount(int productId, Account account) {
		Criteria criteria = getSession().createCriteria(Product.class);
		criteria.add(Restrictions.eq("id", productId));
		criteria.add(Restrictions.eq("account", account));
		
		return (Product)criteria.uniqueResult();
	}

	@Override
	public void saveProduct(Product product) {
		this.save(product);
	}

	@Override
	public void deleteProduct(Product product) {
		this.delete(product);
	}

	@Override
	public List<Product> finAllProductsByAccount(Account account) {
		Criteria criteria = getSession().createCriteria(Product.class);
		criteria.add(Restrictions.eq("account", account));
		
		return criteria.list();
	}

	@Override
	public void deleteProductSpecialty(Specialty specialty) {
		Query query = getSession().createQuery("delete ProductSpecialty where specialty.id = :specialtyId");
		query.setParameter("specialtyId", specialty.getId());
		query.executeUpdate();
		
	}

	@Override
	public void saveProductSpecialty(ProductSpecialty productSpecialty) {
		save(productSpecialty);
		
	}

	@Override
	public List<ProductLine> finPrductLines(Account account) {
		Criteria criteria = getSession().createCriteria(ProductLine.class);
		criteria.add(Restrictions.eq("account", account));
		
		return criteria.list();
	}

	@Override
	public ProductLine findProductLine(int lineId, Account account) {
		Criteria criteria = getSession().createCriteria(ProductLine.class);
		criteria.add(Restrictions.eq("account", account));
		criteria.add(Restrictions.eq("id", lineId));
		
		return (ProductLine) criteria.uniqueResult();
	}



	
	
}
