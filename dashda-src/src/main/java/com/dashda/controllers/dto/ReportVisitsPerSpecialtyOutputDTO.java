/**
 * 
 */
package com.dashda.controllers.dto;

/**
 * @author mohamed.hanfy
 *
 */
public class ReportVisitsPerSpecialtyOutputDTO implements AbstractDTO {

	private String id;
	private String name;
	private String count;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	
}
