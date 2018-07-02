/**
 * 
 */
package com.dashda.data.repositories;

/**
 * @author mohamed.hanfy
 *
 */
public interface NamedQueries {

	public final static String HQL_ASSIGNED_SERVICE_PROVIDER = "SELECT DISTINCT S"
			+ " FROM EmployeeServiceProvider AS ES"
			+ " INNER JOIN ES.serviceProvider AS S WHERE ES.employee.id = :id"
			+ " ORDER BY S.firstName";
	public final static String HQL_ASSIGNED_DISTRICT = "SELECT DISTINCT S.district"
			+ " FROM EmployeeServiceProvider AS ES"
			+ " INNER JOIN ES.serviceProvider AS S WHERE ES.employee.id = :id"
			+ " ORDER BY S.district.enName";
	public final static String HQL_ASSIGNED_SPECIALTY = "SELECT DISTINCT S.specialty"
			+ " FROM EmployeeServiceProvider AS ES"
			+ " INNER JOIN ES.serviceProvider AS S WHERE ES.employee.id = :id"
			+ " ORDER BY S.specialty.name";
	public final static String HQL_SUBORDINATES = "SELECT EH.employee FROM EmployeeHierarchy EH"
			+ " WHERE EH.manager.id = :managerId";
	public final static String HQL_SUBORDINATES_WITHOUT_LOW_LEVEL = "SELECT EH.employee FROM EmployeeHierarchy EH"
			+ " INNER JOIN EH.employee AS E"
			+ " INNER JOIN E.users U"
			+ " WHERE EH.manager.id = :managerId"
			+ " AND U.userRole != 2";
	public final static String SQL_REPORT_COVERAGE = "SELECT COUNT(DISTINCT ESP.SERVICE_PROVIDER_ID) AS LIST_COUNT"
			+ " , COUNT(DISTINCT V.ID) VISITS_COUNT FROM VISIT V"
			+ " RIGHT JOIN EMPLOYEE_SERVICE_PROVIDER ESP ON ESP.EMPLOYEE_ID = V.EMPLOYEE_ID" 
			+ " WHERE ESP.EMPLOYEE_ID = :employeeId";
	public final static String SQL_REPORT_CALLS_DONE = "SELECT COUNT(DISTINCT ESP.SERVICE_PROVIDER_ID)*2 AS LIST_COUNT"
			+ " , COUNT(DISTINCT V.ID) VISITS_COUNT FROM VISIT V"
			+ " RIGHT JOIN EMPLOYEE_SERVICE_PROVIDER ESP ON ESP.EMPLOYEE_ID = V.EMPLOYEE_ID" 
			+ " WHERE ESP.EMPLOYEE_ID = :employeeId";
	public final static String SQL_COACHING_SUMARRY_GROUPED_BY_MANAGER_COUNT = "SELECT E.ID AS ID, E.NAME AS NAME,"
			+ " COUNT(DV.ID) AS E_COUNT FROM EMPLOYEE E" 
			+ " LEFT JOIN DOUBLE_VISIT DV ON DV.MANAGER_ID = E.ID" 
			+ " LEFT JOIN VISIT V ON V.ID = DV.VISIT_ID"
			+ " AND V.DATETIME BETWEEN :dateFrom AND :dateTo" 
			+ " WHERE E.ID IN (:managerId)" 
			+ " GROUP BY E.ID, E.NAME" 
			+ " ORDER BY E.NAME";
	public final static String SQL_REPORT_SUMMARY_GROUPED_BY_EMPLOYEE_DATE_COUNT = "SELECT E.ID, E.NAME AS NAME,"
			+ " V.DATETIME DATETIME, COUNT(DV.ID) AS E_COUNT FROM DOUBLE_VISIT DV" 
			+ " INNER JOIN EMPLOYEE E ON E.ID = DV.EMPLOYEE_ID AND DV.MANAGER_ID = :managerId"
			+ " INNER JOIN VISIT V ON V.ID = DV.VISIT_ID AND V.DATETIME BETWEEN :dateFrom AND :dateTo" 
			+ " GROUP BY E.ID, E.NAME, V.DATETIME"
			+ " ORDER BY E.NAME, V.DATETIME";
	public final static String HQL_SUBORDINATES_LOW_LEVEL = "SELECT EH.employee FROM EmployeeHierarchy EH"
			+ " INNER JOIN EH.employee AS E"
			+ " INNER JOIN E.users U"
			+ " WHERE EH.manager.id = :managerId"
			+ " AND U.userRole = 2";
	public final static String HQL_TARGET_VISITS = "SELECT ESP.serviceProvider FROM EmployeeServiceProvider ESP"
			+ " INNER JOIN ESP.serviceProvider SP"
			+ " INNER JOIN SP.specialty S"
			+ " INNER JOIN SP.district D"
			+ " WHERE ESP.employee.id = :employeeId";
}
