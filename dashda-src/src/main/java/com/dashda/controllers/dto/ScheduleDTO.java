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
	private String doctorId;
	private String doctorName;
	private String scheduleDate;
	private String employeeId;
	private String employeeName;

	

	public ScheduleDTO(String doctorId, String scheduleDate) {
		super();
		this.doctorId = doctorId;
		this.scheduleDate = scheduleDate;
	}

	public ScheduleDTO(String doctorId, String scheduleDate, String employeeId) {
		super();
		this.doctorId = doctorId;
		this.scheduleDate = scheduleDate;
		this.employeeId = employeeId;

	}

	public ScheduleDTO() {
	}

	public ScheduleDTO(int scheduleId) {
		super();
		this.scheduleId = scheduleId;
	}

	public ScheduleDTO(int scheduleId, String doctorId, String scheduleDate, String employeeId) {
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

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}



}
