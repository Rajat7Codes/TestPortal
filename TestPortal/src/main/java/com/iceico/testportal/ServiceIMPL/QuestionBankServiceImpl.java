/**
 * 
 */
package com.iceico.testportal.ServiceIMPL;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iceico.testportal.Exceptions.ResourceNotFoundException;
import com.iceico.testportal.Model.QuestionBank;
import com.iceico.testportal.Model.QuestionType;
import com.iceico.testportal.Repository.QuestionBankRepository;
import com.iceico.testportal.Service.QuestionBankService;

/**
 * @author SAMEER KADGAYE
 * @version 0.1
 * 
 *          Created Date : 14/02/2020
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

	@Autowired
	private EntityManager entityManager;

	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}

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

	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionBank> questionBanksBySubjectsList(String subject) throws ResourceNotFoundException {

		return this.getSession().createQuery("from QuestionBank where subject=:subject")
				.setParameter("subject", subject).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionBank> questionBankListByMarks(Integer marks) throws ResourceNotFoundException {
		return this.getSession().createQuery("from QuestionBank where marks=:marks").setParameter("marks", marks)
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionBank> questionBanksByTypeSubjectMarksList(QuestionType question_Type_Id, Integer marks,
			String subject) throws ResourceNotFoundException {
		return this.getSession().createQuery(
				"from QuestionBank where question_Type_Id=:question_Type_Id AND marks=:marks AND subject=:subject")
				.setParameter("question_Type_Id", question_Type_Id).setParameter("marks", marks)
				.setParameter("subject", subject).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionBank> questionBanksBySubjetAndMarks(String subject, Integer marks)
			throws ResourceNotFoundException {
		return this.getSession().createQuery("from QuestionBank where subject=:subject AND marks=:marks")
				.setParameter("subject", subject).setParameter("marks", marks).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionBank> questionBanksByQuestionTypeList(QuestionType question_Type_Id)
			throws ResourceNotFoundException {

		return this.getSession().createQuery("from QuestionBank where question_Type_Id=:question_Type_Id")
				.setParameter("question_Type_Id", question_Type_Id).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionBank> questionBanksByTypeAndMarks(QuestionType question_Type_Id, Integer marks)
			throws ResourceNotFoundException {

		return this.getSession()
				.createQuery("from QuestionBank where question_Type_Id=:question_Type_Id AND marks=:marks")
				.setParameter("question_Type_Id", question_Type_Id).setParameter("marks", marks).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionBank> questionBanksByTypeAndSubject(QuestionType question_Type_Id, String subject)
			throws ResourceNotFoundException {

		return this.getSession()
				.createQuery("from QuestionBank where question_Type_Id=:question_Type_Id AND subject=:subject")
				.setParameter("question_Type_Id", question_Type_Id).setParameter("subject", subject).list();
	}

}
