/**
 * 
 */
package com.dashda.data.entities;

/**
 * @author mohamed.hanfy
 *
 */
public class ReportVisitsPerEmployee {

	private int id;
	private String name;
	private Long count;
	
	
	public ReportVisitsPerEmployee() {
		super();
	}


	public ReportVisitsPerEmployee(int id, String name, Long count) {
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


	public Long getCount() {
		return count;
	}


	public void setCount(Long count) {
		this.count = count;
	}
	
	
}
