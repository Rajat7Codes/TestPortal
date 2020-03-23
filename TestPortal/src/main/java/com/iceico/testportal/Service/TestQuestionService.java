/**
 * 
 */
package com.iceico.testportal.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iceico.testportal.Exceptions.ResourceNotFoundException;
import com.iceico.testportal.Model.TestQuestion;

/**
 * @author Rajat
 *
 */
public interface TestQuestionService {

	public void saveTestQuestion(TestQuestion testQuestion);

	public void deleteTestQuestion(Long testQuestionId);

	public List<TestQuestion> getTestQuestionList();

	public TestQuestion getTestQuestionById(Long testQuestionId) throws ResourceNotFoundException;
}
