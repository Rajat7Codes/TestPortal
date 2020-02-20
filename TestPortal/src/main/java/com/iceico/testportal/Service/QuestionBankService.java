/**
 * 
 */
package com.iceico.testportal.Service;

import java.util.List;

import com.iceico.testportal.Exceptions.ResourceNotFoundException;
import com.iceico.testportal.Model.QuestionBank;
import com.iceico.testportal.Model.QuestionType;

/**
 * @author SAMEER KADGAYE
 * @version 0.1
 * 
 *          Created Date : 14/02/2020
 *
 */
public interface QuestionBankService {

	public void saveQuestionBank(QuestionBank questionBank);

	public void deleteQuestionBank(Long questionBankId);

	public List<QuestionBank> getQuestionBankList();

	public QuestionBank getQuestionBankById(Long questionBankId) throws ResourceNotFoundException;

	public List<QuestionBank> questionBanksBySubjectsList(String sub) throws ResourceNotFoundException;

	public List<QuestionBank> questionBanksByMarkList(Integer marks) throws ResourceNotFoundException;

	public List<QuestionBank> questionBanksByAllList(QuestionType questionTypeId, Integer marks, String subject)
			throws ResourceNotFoundException;

	public List<QuestionBank> questionBanksByIndividualList(QuestionType questionTypeId, Integer marks, String subject)
			throws ResourceNotFoundException;

	public List<QuestionBank> questionBanksBySubjetAndMarks(String subject, Integer marks)
			throws ResourceNotFoundException;

}
