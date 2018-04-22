/**
 * 
 */
package com.dashda.controllers.dto;

/**
 * @author mhanafy
 *
 */
public class OkResponse extends AppResponse {

	private AbstractDTO data;

	public AbstractDTO getData() {
		return data;
	}

	public void setData(AbstractDTO data) {
		this.data = data;
	}
	
	
}
