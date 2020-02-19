/**
 * 
 */
package com.iceico.testportal.Service;

import java.util.List;

import com.iceico.testportal.Exceptions.ResourceNotFoundException;
import com.iceico.testportal.Model.QuestionBank;

/**
 * @author sameer
 *
 */
public interface QuestionBankService {

	public void saveQuestionBank(QuestionBank questionBank);

	public void deleteQuestionBank(Long questionBankId);

	public List<QuestionBank> getQuestionBankList();

	public QuestionBank getQuestionBankById(Long questionBankId) throws ResourceNotFoundException;
}
