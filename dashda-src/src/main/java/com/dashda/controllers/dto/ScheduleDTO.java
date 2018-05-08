/**
 * 
 */
package com.dashda.controllers.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

/**
 * @author mhanafy
 *
 */
public class ScheduleDTO implements AbstractDTO{

	@Digits(fraction = 0, integer = 8)
	private int scheduleId;
	@Digits(fraction = 0, integer = 8)
	private String serviceProviderId;
	private String serviceProviderName;
	private String scheduleDate;
	private String employeeId;
	private String employeeName;

	

	public ScheduleDTO(String serviceProviderId, String scheduleDate) {
		super();
		this.serviceProviderId = serviceProviderId;
		this.scheduleDate = scheduleDate;
	}

	public ScheduleDTO(String serviceProviderId, String scheduleDate, String employeeId) {
		super();
		this.serviceProviderId = serviceProviderId;
		this.scheduleDate = scheduleDate;
		this.employeeId = employeeId;

	}

	public ScheduleDTO() {
	}

	public ScheduleDTO(int scheduleId) {
		super();
		this.scheduleId = scheduleId;
	}

	public ScheduleDTO(int scheduleId, String serviceProviderId, String scheduleDate, String employeeId) {
		super();
		this.scheduleId = scheduleId;
		this.serviceProviderId = serviceProviderId;
		this.scheduleDate = scheduleDate;
		this.employeeId = employeeId;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getServiceProviderId() {
		return serviceProviderId;
	}

	public void setServiceProviderId(String serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}

	public String getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getServiceProviderName() {
		return serviceProviderName;
	}

	public void setServiceProviderName(String serviceProviderName) {
		this.serviceProviderName = serviceProviderName;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}



}
