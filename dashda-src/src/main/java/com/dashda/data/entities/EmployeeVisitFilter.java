package com.dashda.data.entities;

public class EmployeeVisitFilter {
	
	private int employeeId;
	private Integer managerId;
	private int type;
	private String employeename;
	
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		if(managerId==null)
			this.managerId=-1;
		else
		this.managerId = managerId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getEmployeename() {
		return employeename;
	}
	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}

}
