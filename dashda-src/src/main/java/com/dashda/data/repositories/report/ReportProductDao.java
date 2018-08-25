/**
 * 
 */
package com.dashda.data.repositories.report;

import java.util.Date;
import java.util.List;

import com.dashda.data.entities.Employee;
import com.dashda.data.entities.Visit;

/**
 * @author mohamed.hanfy
 *
 */
public interface ReportProductDao {

	List getVisitsPerProduct(Employee manager, Date dateFrom, Date dateTo);

	List<Visit> getVisitsPerProductDetails(Employee manager, int productId, Date dateFrom, Date dateTo);
}
