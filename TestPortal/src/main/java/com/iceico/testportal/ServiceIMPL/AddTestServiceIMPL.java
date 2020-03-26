/**
 * 
 */
package com.iceico.testportal.ServiceIMPL;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iceico.testportal.Exceptions.ResourceNotFoundException;
import com.iceico.testportal.Model.AddTest;
import com.iceico.testportal.Repository.AddTestRepository;
import com.iceico.testportal.Service.AddTestService;

/**
 * @author puja
 *
 */
@Service
@Transactional
public class AddTestServiceIMPL implements AddTestService {

	@Autowired
	private AddTestRepository addTestRepository;

	@Autowired
	private EntityManager entityManager;

	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	@Override
	public void saveAddTest(AddTest addTest) {
		this.addTestRepository.save(addTest);
	}

	@Override
	public void deleteAddTest(Long addTestId) {
		this.addTestRepository.deleteById(addTestId);
	}

	@Override
	public List<AddTest> getAddTestList() {
		return this.addTestRepository.findAll();
	}

	@Override
	public AddTest getAddTestById(Long addTestId) throws ResourceNotFoundException {
		return this.addTestRepository.findById(addTestId)
				.orElseThrow(() -> new ResourceNotFoundException("addTestId not found" + addTestId));
	}

	/*
	 * @Override public List<AddTest> getTestDepartmentWise(String department) {
	 * return
	 * this.getSession().createNativeQuery("select * from tab_add_test where "); }
	 */

}
