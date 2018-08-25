/**
 * 
 */
package com.dashda.data.repositories.report;

import java.util.Date;
import java.util.List;

import com.dashda.data.entities.Employee;
import com.dashda.data.entities.ReportVisitsPerFLM;
import com.dashda.data.entities.ReportVisitsPerProduct;
import com.dashda.data.entities.Visit;

/**
 * @author mohamed.hanfy
 *
 */
public interface ReportFLMDao {

	List<ReportVisitsPerFLM> getVisitsPerFLM(Employee employee, Date dateFrom, Date dateTo);

	List<Visit> getVisitsPerFLMDetails(Employee manager, int flm, Date dateFrom, Date dateTo);

}
