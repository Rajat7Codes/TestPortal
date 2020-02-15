package com.iceico.testportal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iceico.testportal.Model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
