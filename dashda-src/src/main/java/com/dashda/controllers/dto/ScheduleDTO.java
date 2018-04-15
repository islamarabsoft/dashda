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
public class ScheduleDTO {

	private int scheduleId;
	@NotEmpty
	@Digits(fraction = 0, integer = 8)
	private String doctorId;
	@NotEmpty
	private String scheduleDate;
	
	private int employeeId;
	
	private String employeeName;

	public ScheduleDTO(String doctorId, String scheduleDate) {
		super();
		this.doctorId = doctorId;
		this.scheduleDate = scheduleDate;
	}

	public ScheduleDTO(String doctorId, String scheduleDate, int employeeId, String employeeName) {
		super();
		this.doctorId = doctorId;
		this.scheduleDate = scheduleDate;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
	}

	public ScheduleDTO() {
		// TODO Auto-generated constructor stub
	}

	public ScheduleDTO(int scheduleId) {
		super();
		this.scheduleId = scheduleId;
	}

	public ScheduleDTO(int scheduleId, String doctorId, String scheduleDate, int employeeId) {
		super();
		this.scheduleId = scheduleId;
		this.doctorId = doctorId;
		this.scheduleDate = scheduleDate;
		this.employeeId = employeeId;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
}
