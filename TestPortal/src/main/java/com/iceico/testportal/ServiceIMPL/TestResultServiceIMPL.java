/**
 * 
 */
package com.iceico.testportal.ServiceIMPL;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iceico.testportal.Exceptions.ResourceNotFoundException;
import com.iceico.testportal.Model.TestResult;
import com.iceico.testportal.Repository.TestResultRepository;
import com.iceico.testportal.Service.TestResultService;

/**
 * @author sameer
 *
 */
@Service
@Transactional
public class TestResultServiceIMPL implements TestResultService {

	/**
	 * 
	 */
	public TestResultServiceIMPL() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private TestResultRepository testResultRepository;

	@Override
	public void saveTestResult(TestResult testResult) {
		this.testResultRepository.save(testResult);
	}

	@Override
	public List<TestResult> getTestResultList() {
		return this.testResultRepository.findAll();
	}

	@Override
	public TestResult getTestResultById(Long testResultId) throws ResourceNotFoundException {
		return this.testResultRepository.findById(testResultId)
				.orElseThrow(() -> new ResourceNotFoundException("testResultId not found" + testResultId));
	}

}
