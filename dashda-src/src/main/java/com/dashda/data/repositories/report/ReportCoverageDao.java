/**
 * 
 */
package com.dashda.data.repositories.report;

import java.util.Date;

import com.dashda.data.entities.ReportCallsDone;
import com.dashda.data.entities.ReportCoverageEntity;

/**
 * @author mohamed.hanfy
 *
 */
public interface ReportCoverageDao {

	ReportCoverageEntity coverage(int employeeId);

	ReportCallsDone callsDone(int employeeId);

	ReportCoverageEntity coverage(int employeeId, Date dateFrom, Date dateTo);

	ReportCallsDone callsDone(int employeeId, Date dateFrom, Date dateTo);

}
