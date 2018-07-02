/**
 * 
 */
package com.dashda.data.repositories;

import com.dashda.data.entities.ReportCallsDone;
import com.dashda.data.entities.ReportCoverageEntity;

/**
 * @author mohamed.hanfy
 *
 */
public interface ReportCoverageDao {

	ReportCoverageEntity coverage(int employeeId);

	ReportCallsDone callsDone(int employeeId);

}
