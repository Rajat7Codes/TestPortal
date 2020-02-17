package com.iceico.testportal.ServiceIMPL;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iceico.testportal.Exceptions.ResourceNotFoundException;
import com.iceico.testportal.Model.QuestionType;
import com.iceico.testportal.Repository.QuestionTypeRepository;
import com.iceico.testportal.Service.QuestionTypeService;

@Service
@Transactional
public class QuestionTypeServiceIMPL implements QuestionTypeService {

	@Autowired
	private QuestionTypeRepository questionTypeRepository;

	@Override
	public void saveQuestionType(QuestionType questionType) {
		this.questionTypeRepository.save(questionType);
	}

	@Override
	public void deleteQuestionType(Long questionTypeId) {
		this.questionTypeRepository.deleteById(questionTypeId);
	}

	@Override
	public List<QuestionType> getQuestionTypeList() {
		return this.questionTypeRepository.findAll();
	}

	@Override
	public QuestionType getQuestionTypeById(Long questionTypeId) throws ResourceNotFoundException {
		return this.questionTypeRepository.findById(questionTypeId)
				.orElseThrow(() -> new ResourceNotFoundException("QuestionTypeId not found" + questionTypeId));
	}
}
