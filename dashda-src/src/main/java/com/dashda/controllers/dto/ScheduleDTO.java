/**
 * 
 */
package com.dashda.controllers.dto;


/**
 * @author mhanafy
 *
 */
public class ScheduleDTO {

	private int scheduleId;
	
	private int doctorId;
	
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
		// TODO Auto-generated constructor stub
	}

	public ScheduleDTO(int scheduleId) {
		super();
		this.scheduleId = scheduleId;
	}

	public ScheduleDTO(int scheduleId, int doctorId, String scheduleDate, int employeeId) {
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
