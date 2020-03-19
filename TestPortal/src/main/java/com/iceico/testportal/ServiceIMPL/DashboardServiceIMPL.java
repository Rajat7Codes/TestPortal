/**
 * 
 */
package com.iceico.testportal.ServiceIMPL;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.iceico.testportal.Service.DashboardService;

/**
 * @author sameer
 *
 */
@Service
@Transactional
public class DashboardServiceIMPL implements DashboardService {

	/**
	 * 
	 */
	public DashboardServiceIMPL() {
	}

	@Override
	public List<String> getTodaysPerformanceJSON() {
		return null;
	}

	@Override
	public List<String> getMonthlysPerformanceJSON() {
		return null;
	}

}
