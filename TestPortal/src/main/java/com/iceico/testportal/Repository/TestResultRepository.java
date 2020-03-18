/**
 * 
 */
package com.iceico.testportal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iceico.testportal.Model.TestResult;

/**
 * @author sameer
 *
 */
@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Long>{

}
