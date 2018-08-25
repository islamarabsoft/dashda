/**
 * 
 */
package com.dashda.data.repositories.report;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.dashda.data.entities.ReportCallsDone;
import com.dashda.data.entities.ReportCoverageEntity;
import com.dashda.data.repositories.AbstractDao;
import com.dashda.data.repositories.NamedQueries;

/**
 * @author mohamed.hanfy
 *
 */
@Repository
public class ReportCoverageDaoImpl extends AbstractDao implements ReportCoverageDao, NamedQueries {

	@Override
	public ReportCoverageEntity coverage(int employeeId) {
		SQLQuery sqlQuery = getSession().createSQLQuery(SQL_REPORT_COVERAGE);
		sqlQuery.setParameter("employeeId", employeeId);
		sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Map result = (Map)sqlQuery.uniqueResult();
		BigInteger listCount = (BigInteger)result.get("LIST_COUNT");
		BigInteger visitCount = (BigInteger)result.get("VISITS_COUNT");
	
		ReportCoverageEntity coverageEntity = new ReportCoverageEntity(listCount.intValueExact(), visitCount.intValueExact());
		
		return coverageEntity;
	}

	@Override
	public ReportCallsDone callsDone(int employeeId) {
		Map params = new HashMap<>();
		params.put("employeeId", employeeId);
		
		Map result = findRowNative(SQL_REPORT_CALLS_DONE, params);
		BigInteger listCount = (BigInteger)result.get("LIST_COUNT");
		BigInteger visitCount = (BigInteger)result.get("VISITS_COUNT");
	
		ReportCallsDone coverageEntity = new ReportCallsDone(listCount.intValueExact(), visitCount.intValueExact());
		
		return coverageEntity;
	}

	
}
