/**
 * 
 */
package com.dashda.data.entities;

/**
 * @author mohamed.hanfy
 *
 */
public class ReportCallsDone {
	
	private int target;
	private int actual;
	
	public ReportCallsDone() {
		super();
	}

	public ReportCallsDone(int target, int actual) {
		super();
		this.target = target;
		this.actual = actual;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public int getActual() {
		return actual;
	}

	public void setActual(int actual) {
		this.actual = actual;
	}
	
	

}
