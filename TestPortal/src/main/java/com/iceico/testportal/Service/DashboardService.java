/**
 * 
 */
package com.iceico.testportal.Service;

import java.util.Date;
import java.util.List;

import com.iceico.testportal.Model.TestResult;

/**
 * @author sameer
 *
 */
public interface DashboardService {

	/* For Java Department dashboard */

	public List<TestResult> getTodaysPerformancePercentageAll(Date date);

	public List<TestResult> getMonthlysPerformancePercentageAll(Date startDate, Date lastDate);

	/* End Java Department dashboard */

	public List<TestResult> getTopTenStudentList(Date date);

	public List<TestResult> getTopTenStudentListMonthly(Date startDate, Date lastDate);

	/* For Find Rank Per Student wise */

	public TestResult getCurrentRankOfStudent(Long testResultId);

}
