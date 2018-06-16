/**
 * 
 */
package com.dashda.data.repositories;

import java.util.List;

import com.dashda.data.entities.Account;
import com.dashda.data.entities.Product;
import com.dashda.data.entities.ProductSpecialty;
import com.dashda.data.entities.Specialty;

/**
 * @author mhanafy
 *
 */
public interface ProductDao {

	List<ProductSpecialty> findProductBySpecialty(Specialty specialty, Account account);

	Product findProductById(int productId);
}
