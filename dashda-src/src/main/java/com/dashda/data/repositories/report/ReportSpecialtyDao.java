/**
 * 
 */
package com.dashda.data.repositories.report;

import java.util.Date;
import java.util.List;

import com.dashda.data.entities.Employee;
import com.dashda.data.entities.ReportVisitsPerProduct;
import com.dashda.data.entities.ReportVisitsPerSpecialty;
import com.dashda.data.entities.Visit;

/**
 * @author mohamed.hanfy
 *
 */
public interface ReportSpecialtyDao {

	List<ReportVisitsPerSpecialty> getVisitsPerSpecialty(Employee employee, Date convertToDate, Date convertToDate2);
	
	List<Visit> getVisitsPerSpecialtyDetails(Employee manager, int flm, Date dateFrom, Date dateTo);

}
