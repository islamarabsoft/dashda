/**
 * 
 */
package com.dashda.controllers.dto;

import java.util.List;

/**
 * @author mhanafy
 *
 */
public class ListResponse extends AppResponse{


	private List<AbstractDTO> data;
	

	public List<AbstractDTO> getData() {
		return data;
	}


	public void setData(List<AbstractDTO> data) {
		this.data = data;
	}





	
	
}
