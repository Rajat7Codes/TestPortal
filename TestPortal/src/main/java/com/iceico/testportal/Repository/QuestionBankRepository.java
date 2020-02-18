/**
 * 
 */
package com.iceico.testportal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iceico.testportal.Model.QuestionBank;
import com.iceico.testportal.Model.QuestionType;

/**
 * @author sameer
 *
 */
@Repository
public interface QuestionBankRepository extends JpaRepository<QuestionBank, Long> {

}
