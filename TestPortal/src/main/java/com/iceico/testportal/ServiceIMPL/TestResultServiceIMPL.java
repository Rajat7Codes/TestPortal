/**
 * 
 */
package com.iceico.testportal.ServiceIMPL;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
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

	@Autowired
	private EntityManager entityManager;

	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}

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

	@SuppressWarnings("unchecked")
	@Override
	public List<TestResult> getTodaysAllResultStatusList(Date date) {

		
		return this.getSession().createQuery("select resultStatus from TestResult where date=:date ")
				.setParameter("date", date).getResultList();
	}

	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public List<TestResult> getTodaysAllResultStatusList() { return
	 * this.getSession().createQuery("select resultStatus from TestResult").
	 * getResultList(); }
	 * 
	 * @Override public List<TestResult> getMonthlyAllResultStatusList() { return
	 * null; }
	 * 
	 * @Override public List<TestResult> getTopTenStudentResult() { return null; }
	 */

}
