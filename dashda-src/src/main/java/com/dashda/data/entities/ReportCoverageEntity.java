/**
 * 
 */
package com.dashda.data.entities;

/**
 * @author mohamed.hanfy
 *
 */
public class ReportCoverageEntity {

	private int ListCount;
	private int visitsCount;
	
	
	public ReportCoverageEntity(int listCount, int visitCount) {
		this.ListCount = listCount;
		this.visitsCount = visitCount;
	}
	public int getListCount() {
		return ListCount;
	}
	public void setListCount(int listCount) {
		ListCount = listCount;
	}
	public int getVisitsCount() {
		return visitsCount;
	}
	public void setVisitsCount(int visitsCount) {
		visitsCount = visitsCount;
	}
	
	
}
