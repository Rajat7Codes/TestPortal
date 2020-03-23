/**
 * 
 */
package com.iceico.testportal.ServiceIMPL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iceico.testportal.Exceptions.ResourceNotFoundException;
import com.iceico.testportal.Model.TestQuestion;
import com.iceico.testportal.Repository.TestQuestionRepository;
import com.iceico.testportal.Service.TestQuestionService;

/**
 * @author Rajat
 *
 */
@Service
public class TestQuestionServiceIMPL implements TestQuestionService {

	@Autowired
	TestQuestionRepository testQuestionRepository;
	
	@Override
	public void saveTestQuestion(TestQuestion testQuestion) {
		this.testQuestionRepository.save(testQuestion);
	}

	@Override
	public void deleteTestQuestion(Long testQuestionId) {
		this.testQuestionRepository.deleteById(testQuestionId);
	}

	@Override
	public List<TestQuestion> getTestQuestionList() {
		return this.testQuestionRepository.findAll();
	}

	@Override
	public TestQuestion getTestQuestionById(Long testQuestionId) throws ResourceNotFoundException {
		return this.testQuestionRepository.findById(testQuestionId)
				.orElseThrow(() -> new ResourceNotFoundException("TestQuestionId not found" + testQuestionId));
	}
}
