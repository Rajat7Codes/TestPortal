package com.iceico.testportal.ServiceIMPL;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iceico.testportal.Exceptions.ResourceNotFoundException;
import com.iceico.testportal.Model.Department;
import com.iceico.testportal.Repository.DepartmentRepository;
import com.iceico.testportal.Service.DepartmentService;

/**
 * @author puja
 *
 */
@Service
@Transactional
public class DepartmentServiceIMPL implements DepartmentService {
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public void saveDepartment(Department department) {
		this.departmentRepository.save(department);
	}

	@Override
	public void deleteDepartment(Long departmentId) {
		this.departmentRepository.deleteById(departmentId);

	}

	@Override
	public List<Department> getDepartmentList() {

		return this.departmentRepository.findAll();
	}

	@Override
	public Department getDepartmentById(Long departmentId) throws ResourceNotFoundException {
		return this.departmentRepository.findById(departmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Department Id not found" + departmentId));
	}

}
