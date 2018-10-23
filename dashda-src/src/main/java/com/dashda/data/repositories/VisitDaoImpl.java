/**
 * 
 */
package com.dashda.data.repositories;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.dashda.data.entities.ServiceProvider;
import com.dashda.controllers.dto.visit.VisitReportInputDTO;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.ReportUnVisit;
import com.dashda.data.entities.Visit;
import com.dashda.data.entities.VisitReportComments;
import com.dashda.data.entities.VisitReportCount;
import com.dashda.data.entities.VisitReportCountByDay;
import com.dashda.data.entities.VisitReportDetailsByDay;
import com.dashda.data.entities.VisitStatus;
import com.dashda.enums.VisitStatusEnum;

import javassist.expr.NewArray;

/**
 * @author mhanafy
 *
 */
@Repository
public class VisitDaoImpl extends AbstractDao implements VisitDao {

	/* (non-Javadoc)
	 * @see com.dashda.data.repositories.VisitDao#addVisit(com.dashda.data.entities.Visit)
	 */
	@Override
	public void addVisit(Visit visit) {
		getSession().save(visit);
		getSession().flush();
		getSession().clear();

	}

	@Override
	public List<Visit> findVisitItemsByEmployee(Employee employee) {
		Criteria criteria = getSession().createCriteria(Visit.class);
		criteria.add(Restrictions.eq("employeeByEmployeeId.id", employee.getId()));
		criteria.add(Restrictions.eq("visitStatus.id", VisitStatusEnum.PLANNED.getValue()));
		
		return criteria.list();
	}

	@Override
	public List<Visit> findVisitInPeriodItemsByEmployee(Employee employee, Date fromDate, Date toDate) {
		Criteria criteria = getSession().createCriteria(Visit.class);
		criteria.add(Restrictions.eq("employeeByEmployeeId.id", employee.getId()));
		criteria.add(Restrictions.in("visitStatus.id", VisitStatusEnum.PLANNED.getValue()));
		criteria.add(Restrictions.between("datetime", fromDate, toDate));
		
		return criteria.list();
	}
	
	@Override
	public List<Visit> findVisitInPeriodItemsByEmployee(Employee employee, Date fromDate, Date toDate,
			int serviceTypeId) {
		Criteria criteria = getSession().createCriteria(Visit.class);
		criteria.createAlias("serviceProvider", "sp");
		criteria.createAlias("sp.serviceProviderType", "ty");
		
		criteria.add(Restrictions.eq("employeeByEmployeeId.id", employee.getId()));
		criteria.add(Restrictions.in("visitStatus.id", VisitStatusEnum.PLANNED.getValue()));
		criteria.add(Restrictions.between("datetime", fromDate, toDate));
		if(serviceTypeId != 0)
			criteria.add(Restrictions.eq("ty.id", serviceTypeId));
		
		
		return criteria.list();
	}
	
	@Override
	public Visit findVisitByIdAndNotComplete(Integer visitId) {
		Criteria criteria = getSession().createCriteria(Visit.class);
		criteria.add(Restrictions.eq("id", visitId));
		criteria.add(Restrictions.eq("visitStatus.id", VisitStatusEnum.PLANNED.getValue()));
		
		return (Visit)criteria.uniqueResult();
	}
	
	@Override
	public Visit findUserVisitByIdAndNotComplete(int visitId, int employeeId) {
		Criteria criteria = getSession().createCriteria(Visit.class);
		criteria.add(Restrictions.eq("id", visitId));
		criteria.add(Restrictions.eq("employeeByEmployeeId.id", employeeId));
		criteria.add(Restrictions.eq("visitStatus.id", VisitStatusEnum.PLANNED.getValue()));
		
		return (Visit)criteria.uniqueResult();
	}

	@Override
	public void updateVisit(Visit visit) {
		save(visit);
		
	}

	@Override
	public Visit findCompletedVisitByDoctorAndEmployee(ServiceProvider serviceProvider, Employee employee) {
		List status = new ArrayList();
		status.add(VisitStatusEnum.PLANNED.getValue());
		status.add(VisitStatusEnum.COMPLETE.getValue());
		
		Criteria criteria = getSession().createCriteria(Visit.class);
		criteria.add(Restrictions.eq("serviceProvider", serviceProvider));
		criteria.add(Restrictions.eq("employeeByEmployeeId", employee));
		criteria.add(Restrictions.in("visitStatus.id", status));
		criteria.addOrder(Order.desc("id"));
		criteria.setMaxResults(1);
		
		return (Visit)criteria.uniqueResult();
	}

	@Override
	public List<Visit> findVisitNotComplete(ServiceProvider serviceProvider, Employee employee) {
		Criteria criteria = getSession().createCriteria(Visit.class);
		criteria.add(Restrictions.eq("serviceProvider", serviceProvider));
		criteria.add(Restrictions.eq("employeeByEmployeeId", employee));
		criteria.add(Restrictions.in("visitStatus.id", VisitStatusEnum.PLANNED.getValue()));
		
		return criteria.list();
	}

	@Override
	public void discardAllVisitsBeforeDate(Date executionDate) {
		Query query = getSession().createQuery("update Visit set visitStatus.id = 3 where datetime < :date and visitStatus.id = 1");
		query.setParameter("date", executionDate);
		query.executeUpdate();
		
	}

	@Override
	public List<VisitReportCount> findVisitReportCount(VisitReportInputDTO input) {
		// TODO Auto-generated method stub
		List<VisitReportCount> visitReportCount=new ArrayList<>();
		
		 String sql="select count(distinct v.ID) as count ,pl.ID as productlineid, pl.NAME as productline,e1.ID as mpid,e1.NAME as mp,e2.ID as flmid,e2.name as flm,e3.ID as regionalid,e3.NAME as regional   from VISIT as v,PRODUCT as p,PRODUCT_VISIT  as pv, EMPLOYEE as e1,EMPLOYEE as e2,EMPLOYEE as e3,PRODUCT_LINE as pl ,PRODUCT_SPECIALTY as ps , SPECIALTY as s,SERVICE_PROVIDER as sv  where v.id=pv.VISIT_ID and pv.product_id=p.ID and p.PRODUCT_LINE_ID=pl.ID and  v.EMPLOYEE_ID=e1.ID and  e1.MANAGER_ID=e2.id and pv.PRODUCT_ID=ps.PRODUCT_ID and ps.SPECIALTY_ID=s.ID  \r\n" + 
		 		"and e2.MANAGER_ID=e3.ID and v.SERVICE_PROVIDER_ID=sv.ID ";
		 System.out.println("flm : "+input.getFlm());
		   if(input.getMp()!=0)
			   sql+="and e1.id="+input.getMp()+" ";
		   if(input.getFlm()!=0)
			   sql+="and e2.id="+input.getFlm()+" ";
		   if(input.getRegional()!=0)
			   sql+="and e3.id="+input.getRegional()+" ";
		   if(input.getProductline()!=0)
			   sql+="and pl.id="+input.getProductline()+" ";
		   if(input.getProduct()!=0)
			   sql+="and p.id="+input.getProduct()+" ";
		   if(input.getSpecialty()!=0)
			   sql+="and s.id="+input.getSpecialty()+" ";
		   if(input.getAmpm()!=0)
			   sql+="and sv.SERVICE_PROVIDER_TYPE_ID="+input.getAmpm()+" ";
		   if(input.getDatefrom()!=null)
			   sql+="and v.DATETIME >='"+input.getDatefrom()+"' ";
		   if(input.getDateto()!=null)
			   sql+="and v.DATETIME <='"+input.getDateto()+"' ";
		   
		 sql+=" group by  pl.NAME,e1.ID,e1.NAME ,pl.ID  ";
		
		 SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		 
		 sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			List<Map> result = (List<Map>) sqlQuery.list();
		/* List <VisitReportCount> list= sqlQuery.addScalar("count").addScalar("productlineid").addScalar("productline").addScalar("mpid").addScalar("mp").addScalar("flmid").addScalar("flm").addScalar("regionalid").addScalar("regional").
				 setResultTransformer(new AliasToBeanResultTransformer(VisitReportCount.class)).list();
		 ResultSet s;*/
		 for (Iterator iterator = result.iterator(); iterator.hasNext();) {
				Map object = (Map) iterator.next();
				
				//VisitReportCount visitreport = new VisitReportCount((BigInteger)object.get("productlineid"),(String)object.get("productline"),(String)object.get("regional"),(BigInteger)object.get("regionalid"),(String)object.get("flm"),(BigInteger)object.get("flmid"),(String)object.get("mp"),(BigInteger)object.get("mpid"),(BigInteger)object.get("count"));
				VisitReportCount visitreport = new VisitReportCount();
				visitreport.setProductline((String)object.get("productline"));
				visitreport.setProductlineid((Integer)object.get("productlineid"));
				visitreport.setRegional((String)object.get("regional"));
				visitreport.setRegionalid((Integer)object.get("regionalid"));
				visitreport.setFlm((String)object.get("flm"));
				visitreport.setFlmid((Integer)object.get("flmid"));
				visitreport.setMp((String)object.get("mp"));
				visitreport.setMpid((Integer)object.get("mpid"));
				visitreport.setCount((BigInteger)object.get("count"));
				visitReportCount.add(visitreport);
			}
		 
		return visitReportCount ;
	}

	@Override
	public List<VisitReportComments> findVisitReportComments(VisitReportInputDTO input) {
		// TODO Auto-generated method stub
		List<VisitReportComments> visitReportComment=new ArrayList<>();
		
		 String sql="select  e1.NAME as mr ,d.EN_NAME as district ,p.NAME as product ,s.NAME as specialty ,v.COMMENT as comment  from VISIT as v,PRODUCT as p,PRODUCT_VISIT  as pv, EMPLOYEE as e1,EMPLOYEE as e2,EMPLOYEE as e3,PRODUCT_LINE as pl ,PRODUCT_SPECIALTY as ps , SPECIALTY as s,SERVICE_PROVIDER as sv , DISTRICT as d  where v.id=pv.VISIT_ID and pv.product_id=p.ID and p.PRODUCT_LINE_ID=pl.ID and  v.EMPLOYEE_ID=e1.ID and  e1.MANAGER_ID=e2.id and pv.PRODUCT_ID=ps.PRODUCT_ID and ps.SPECIALTY_ID=s.ID \r\n" + 
		 		"and e2.MANAGER_ID=e3.ID and v.SERVICE_PROVIDER_ID=sv.ID and sv.DISTRICT_ID=d.ID ";
		 System.out.println("flm : "+input.getFlm());
		   if(input.getMp()!=0)
			   sql+="and e1.id="+input.getMp()+" ";
		   if(input.getFlm()!=0)
			   sql+="and e2.id="+input.getFlm()+" ";
		   if(input.getRegional()!=0)
			   sql+="and e3.id="+input.getRegional()+" ";
		   if(input.getProductline()!=0)
			   sql+="and pl.id="+input.getProductline()+" ";
		   if(input.getProduct()!=0)
			   sql+="and p.id="+input.getProduct()+" ";
		   if(input.getSpecialty()!=0)
			   sql+="and s.id="+input.getSpecialty()+" ";
		   if(input.getAmpm()!=0)
			   sql+="and sv.SERVICE_PROVIDER_TYPE_ID="+input.getAmpm()+" ";
		   if(input.getDatefrom()!=null)
			   sql+="and v.DATETIME >='"+input.getDatefrom()+"' ";
		   if(input.getDateto()!=null)
			   sql+="and v.DATETIME <='"+input.getDateto()+"' ";
		   
		
		
		 SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		 
		 sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			List<Map> result = (List<Map>) sqlQuery.list();
		
		 for (Iterator iterator = result.iterator(); iterator.hasNext();) {
				Map object = (Map) iterator.next();
				
				VisitReportComments visitreport = new VisitReportComments();
				visitreport.setMr((String)object.get("mr"));
				visitreport.setDistrict((String)object.get("district"));
				
				visitreport.setProduct((String)object.get("product"));
				visitreport.setSpecialty((String)object.get("specialty"));
				visitreport.setComment((String)object.get("comment"));
				
				visitReportComment.add(visitreport);
			}
		 
		return visitReportComment;
	}

	@Override
	public List<VisitReportCountByDay> findVisitReportCountByDay(VisitReportInputDTO input) {
		List<VisitReportCountByDay> visitReportCountByDay=new ArrayList<>();
		
		 String sql="select count(distinct v.ID) as count ,v.DATETIME as date from VISIT as v,PRODUCT as p,PRODUCT_VISIT  as pv, EMPLOYEE as e1,EMPLOYEE as e2,EMPLOYEE as e3,PRODUCT_LINE as pl   where v.id=pv.VISIT_ID and pv.product_id=p.ID and p.PRODUCT_LINE_ID=pl.ID and  v.EMPLOYEE_ID=e1.ID and  e1.MANAGER_ID=e2.id  \r\n" + 
		 		"and e2.MANAGER_ID=e3.ID ";
		 System.out.println("flm : "+input.getFlm());
		   if(input.getMp()!=0)
			   sql+="and e1.id="+input.getMp()+" ";
		   if(input.getFlm()!=0)
			   sql+="and e2.id="+input.getFlm()+" ";
		   if(input.getRegional()!=0)
			   sql+="and e3.id="+input.getRegional()+" ";
		   if(input.getProductline()!=0)
			   sql+="and pl.id="+input.getProductline()+" ";
		   
		 sql+=" group by  v.DATETIME order by v.DATETIME asc ";
		
		 SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		 
		 sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			List<Map> result = (List<Map>) sqlQuery.list();
		/* List <VisitReportCount> list= sqlQuery.addScalar("count").addScalar("productlineid").addScalar("productline").addScalar("mpid").addScalar("mp").addScalar("flmid").addScalar("flm").addScalar("regionalid").addScalar("regional").
				 setResultTransformer(new AliasToBeanResultTransformer(VisitReportCount.class)).list();
		 ResultSet s;*/
		 for (Iterator iterator = result.iterator(); iterator.hasNext();) {
				Map object = (Map) iterator.next();
				
				//VisitReportCount visitreport = new VisitReportCount((BigInteger)object.get("productlineid"),(String)object.get("productline"),(String)object.get("regional"),(BigInteger)object.get("regionalid"),(String)object.get("flm"),(BigInteger)object.get("flmid"),(String)object.get("mp"),(BigInteger)object.get("mpid"),(BigInteger)object.get("count"));
				VisitReportCountByDay visitreport = new VisitReportCountByDay();
				visitreport.setDay((Timestamp)object.get("date"));
				visitreport.setCount((BigInteger)object.get("count"));
				
				visitReportCountByDay.add(visitreport);
			}
		 
		return visitReportCountByDay;
	}

	@Override
	public List<VisitReportDetailsByDay> findVisitReportDetailsByDay(VisitReportInputDTO input) {
		
		List<VisitReportDetailsByDay> visitReportDetailsByDay=new ArrayList<>();
		String sql="select distinct( v.ID) as visitId,e2.NAME as flmName,v.DATETIME as date,a.name as accountName,d.EN_NAME as districtName  from VISIT as v,PRODUCT as p,PRODUCT_VISIT  as pv, EMPLOYEE as e1,EMPLOYEE as e2,EMPLOYEE as e3,PRODUCT_LINE as pl ,PRODUCT_SPECIALTY as ps , SPECIALTY as s,SERVICE_PROVIDER as sv ,ACCOUNT as a,DISTRICT as d where v.id=pv.VISIT_ID and pv.product_id=p.ID and p.PRODUCT_LINE_ID=pl.ID and  v.EMPLOYEE_ID=e1.ID and  e1.MANAGER_ID=e2.id and pv.PRODUCT_ID=ps.PRODUCT_ID and ps.SPECIALTY_ID=s.ID \r\n" + 
		 		"and e2.MANAGER_ID=e3.ID and v.SERVICE_PROVIDER_ID=sv.ID and e1.ACCOUNT_ID=a.id and sv.DISTRICT_ID=d.ID  ";
			 System.out.println("flm : "+input.getFlm());
			   if(input.getMp()!=0)
				   sql+="and e1.id="+input.getMp()+" ";
			   if(input.getFlm()!=0)
				   sql+="and e2.id="+input.getFlm()+" ";
			   if(input.getRegional()!=0)
				   sql+="and e3.id="+input.getRegional()+" ";
			   if(input.getProductline()!=0)
				   sql+="and pl.id="+input.getProductline()+" ";
			   if(input.getSpecificDate()!=null)
			   {
				   sql+="and v.DATETIME >='"+input.getSpecificDate()+"' ";
				   sql+="and v.DATETIME <='"+input.getSpecificDate()+"' ";
			   }
			
			 SQLQuery sqlQuery = getSession().createSQLQuery(sql);
			 
			 sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				List<Map> result = (List<Map>) sqlQuery.list();
			
			 for (Iterator iterator = result.iterator(); iterator.hasNext();) {
					Map object = (Map) iterator.next();
					
					VisitReportDetailsByDay visitreport = new VisitReportDetailsByDay();
					visitreport.setVisitId((Integer)object.get("visitId"));
					visitreport.setFlmName((String)object.get("flmName"));
					visitreport.setDate((Timestamp)object.get("date"));
					visitreport.setDistrictName((String)object.get("districtName"));
					visitreport.setAccountName((String)object.get("accountName"));
					
					visitReportDetailsByDay.add(visitreport);
				}
			 
			return visitReportDetailsByDay;
	}



}
