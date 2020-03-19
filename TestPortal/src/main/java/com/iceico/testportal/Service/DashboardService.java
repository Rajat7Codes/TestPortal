/**
 * 
 */
package com.iceico.testportal.Service;

import java.util.List;

/**
 * @author sameer
 *
 */
public interface DashboardService {

	public List<String> getTodaysPerformanceJSON();

	public List<String> getMonthlysPerformanceJSON();

}
