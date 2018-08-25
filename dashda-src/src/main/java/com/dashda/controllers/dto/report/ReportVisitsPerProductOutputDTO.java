/**
 * 
 */
package com.dashda.controllers.dto.report;

import com.dashda.controllers.dto.AbstractDTO;

/**
 * @author mohamed.hanfy
 *
 */
public class ReportVisitsPerProductOutputDTO implements AbstractDTO {

	private int id;
	private String name;
	private int count;
	
	
	public ReportVisitsPerProductOutputDTO() {
		super();
	}


	public ReportVisitsPerProductOutputDTO(int id, String name, int count) {
		super();
		this.id = id;
		this.name = name;
		this.count = count;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}
	
	
	
}
