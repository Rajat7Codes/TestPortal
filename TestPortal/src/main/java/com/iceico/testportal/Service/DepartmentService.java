package com.iceico.testportal.Service;

import java.util.List;

import com.iceico.testportal.Exceptions.ResourceNotFoundException;
import com.iceico.testportal.Model.Department;

public interface DepartmentService {

	public void saveDepartment(Department department);

	public void deleteDepartment(Long departmetId);

	public List<Department> getDepartmentList();

	public Department getDepartmentById(Long departmentId) throws ResourceNotFoundException;

}
