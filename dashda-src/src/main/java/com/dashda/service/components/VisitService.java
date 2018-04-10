/**
 * 
 */
package com.dashda.service.components;

import java.util.List;

/**
 * @author mhanafy
 *
 */
public interface VisitService {

	Object visitItemsList(String username);

	void completeVisits(String username, List<Integer> visits);

	void dicardVisits(String username, List<Integer> visits);

}
