/**
 * 
 */
package com.dashda.data.repositories;

import java.util.Date;
import java.util.List;

import com.dashda.data.entities.Employee;
import com.dashda.data.entities.ReportVisitsPerProduct;
import com.dashda.data.entities.Visit;

/**
 * @author mohamed.hanfy
 *
 */
public interface ReportDistrictDao {

	List<ReportVisitsPerProduct> getVisitsPerDistrict(Employee employee, Date convertToDate, Date convertToDate2);
	
	List<Visit> getVisitsPerDistrictDetails(Employee manager, int flm, Date dateFrom, Date dateTo);

}
