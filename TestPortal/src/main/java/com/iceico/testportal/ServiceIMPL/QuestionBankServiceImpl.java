/**
 * 
 */
package com.iceico.testportal.ServiceIMPL;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iceico.testportal.Exceptions.ResourceNotFoundException;
import com.iceico.testportal.Model.QuestionBank;
import com.iceico.testportal.Repository.QuestionBankRepository;
import com.iceico.testportal.Service.QuestionBankService;

/**
 * @author sameer
 *
 */
@Service
@Transactional
public class QuestionBankServiceImpl implements QuestionBankService {

	/**
	 * 
	 */
	public QuestionBankServiceImpl() {
	}

	@Autowired
	private QuestionBankRepository questionBankRepository;

	@Override
	public void saveQuestionBank(QuestionBank questionBank) {
		this.questionBankRepository.save(questionBank);
	}

	@Override
	public void deleteQuestionBank(Long questionBankId) {
		this.questionBankRepository.deleteById(questionBankId);

	}

	@Override
	public List<QuestionBank> getQuestionBankList() {

		return this.questionBankRepository.findAll();
	}

	@Override
	public QuestionBank getQuestionBankById(Long questionBankId) throws ResourceNotFoundException {

		return this.questionBankRepository.findById(questionBankId)
				.orElseThrow(() -> new ResourceNotFoundException("QuestionBank Id not found" + questionBankId));
	}

}
