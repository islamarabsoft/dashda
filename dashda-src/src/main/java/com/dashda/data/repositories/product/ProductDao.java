/**
 * 
 */
package com.dashda.data.repositories.product;

import java.util.List;

import com.dashda.data.entities.Account;
import com.dashda.data.entities.Product;
import com.dashda.data.entities.ProductLine;
import com.dashda.data.entities.ProductSpecialty;
import com.dashda.data.entities.ProductVisitFilters;
import com.dashda.data.entities.Specialty;

/**
 * @author mhanafy
 *
 */
public interface ProductDao {

	List<ProductSpecialty> findProductBySpecialty(Specialty specialty, Account account);

	List<ProductSpecialty> findProductBySpecialty(Product product, Account account);
	
	List<ProductSpecialty> findProductBySpecialty(Specialty specialty, Account account, ProductLine productLine);
	
	List<ProductVisitFilters> findAllProductSpecialty();
	
	Product findProductByIdAndAccount(int productId, Account account);

	 <T> List<T> findall(Class<T> cls);	
	public void saveProduct(Product product);

	void deleteProduct(Product product);

	List<Product> finAllProductsByAccount(Account account);

	void deleteProductSpecialty(Specialty specialty);

	void saveProductSpecialty(ProductSpecialty productSpecialty);

	List<ProductLine> finPrductLines(Account account);

	ProductLine findProductLine(int lineId, Account account);

	
}
