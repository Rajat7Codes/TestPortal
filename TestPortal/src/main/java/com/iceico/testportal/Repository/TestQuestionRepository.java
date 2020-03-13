/**
 * 
 */
package com.iceico.testportal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iceico.testportal.Model.TestQuestion;

/**
 * @author Rajat
 *
 */
@Repository
public interface TestQuestionRepository extends JpaRepository<TestQuestion, Long> {

}
