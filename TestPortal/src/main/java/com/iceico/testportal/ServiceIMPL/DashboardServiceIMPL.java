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

import com.iceico.testportal.Model.TestResult;
import com.iceico.testportal.Repository.TestResultRepository;
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

	@Autowired
	private EntityManager entityManager;

	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TestResult> getTodaysPerformancePercentageAll(Date date) {
		return this.getSession().createQuery("from TestResult where date=:date").setParameter("date", date)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TestResult> getMonthlysPerformancePercentageAll(Date startDate, Date lastDate) {
		return this.getSession().createQuery("from TestResult where date BETWEEN :startDate AND :lastDate ")
				.setParameter("startDate", startDate).setParameter("lastDate", lastDate).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TestResult> getTopTenStudentList(Date date) {
		return this.getSession().createQuery("from TestResult where date=:date ORDER BY percentage DESC")
				.setParameter("date", date).setMaxResults(10).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TestResult> getTopTenStudentListMonthly(Date startDate, Date lastDate) {
		return this.getSession()
				.createQuery("from TestResult where date BETWEEN :startDate AND :lastDate ORDER BY percentage DESC")
				.setParameter("startDate", startDate).setParameter("lastDate", lastDate).setMaxResults(10)
				.getResultList();
	}

}
