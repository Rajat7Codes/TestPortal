package com.iceico.testportal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iceico.testportal.Model.QuestionType;
/**
 * @author puja
 *
 */
@Repository
public interface QuestionTypeRepository extends JpaRepository<QuestionType, Long> {

}
