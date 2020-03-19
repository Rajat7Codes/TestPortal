/**
 * 
 */
package com.iceico.testportal.Service;

import java.util.Date;
import java.util.List;

import com.iceico.testportal.Exceptions.ResourceNotFoundException;
import com.iceico.testportal.Model.TestResult;

/**
 * @author sameer
 *
 */
public interface TestResultService {

	public void saveTestResult(TestResult testResult);

	public List<TestResult> getTestResultList();

	public TestResult getTestResultById(Long testResultId) throws ResourceNotFoundException;

	public List<TestResult> getTodaysAllResultStatusList(Date date);
	/*
	 * public List<TestResult> getMonthlyAllResultStatusList();
	 * 
	 * public List<TestResult> getTopTenStudentResult();
	 */

}
