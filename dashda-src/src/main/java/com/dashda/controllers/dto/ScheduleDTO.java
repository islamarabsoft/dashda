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

	private int id;
	@Digits(fraction = 0, integer = 8)
	private int doctorId;
	@NotEmpty
	private String scheduleDate;
	
	private int employeeId;
	

	public ScheduleDTO(int doctorId, String scheduleDate) {
		super();
		this.doctorId = doctorId;
		this.scheduleDate = scheduleDate;
	}

	public ScheduleDTO(int doctorId, String scheduleDate, int employeeId) {
		super();
		this.doctorId = doctorId;
		this.scheduleDate = scheduleDate;
		this.employeeId = employeeId;

	}

	public ScheduleDTO() {
	}

	public ScheduleDTO(int id) {
		super();
		this.id = id;
	}

	public ScheduleDTO(int id, int doctorId, String scheduleDate, int employeeId) {
		super();
		this.id = id;
		this.doctorId = doctorId;
		this.scheduleDate = scheduleDate;
		this.employeeId = employeeId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
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


}
