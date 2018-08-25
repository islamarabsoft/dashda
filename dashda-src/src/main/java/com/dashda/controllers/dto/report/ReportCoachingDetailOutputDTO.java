/**
 * 
 */
package com.dashda.controllers.dto.report;

import com.dashda.controllers.dto.AbstractDTO;

/**
 * @author mohamed.hanfy
 *
 */
public class ReportCoachingDetailOutputDTO implements AbstractDTO {

	private String employeeName;
	private String account;
	private String specialty;
	private String brick;
	private String date;
	
	public ReportCoachingDetailOutputDTO() {
		super();
	}

	public ReportCoachingDetailOutputDTO(String employeeName, String account, String specialty, String brick,
			String date) {
		super();
		this.employeeName = employeeName;
		this.account = account;
		this.specialty = specialty;
		this.brick = brick;
		this.date = date;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getBrick() {
		return brick;
	}

	public void setBrick(String brick) {
		this.brick = brick;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
	
	
	
}
