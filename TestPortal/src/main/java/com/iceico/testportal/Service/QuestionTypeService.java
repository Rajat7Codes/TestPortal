package com.iceico.testportal.Service;

import java.util.List;

import com.iceico.testportal.Exceptions.ResourceNotFoundException;
import com.iceico.testportal.Model.QuestionType;

/**
 * @author puja
 *
 */
public interface QuestionTypeService {

	public void saveQuestionType(QuestionType questionType);

	public void deleteQuestionType(Long questionTypeId);

	public List<QuestionType> getQuestionTypeList();

	public QuestionType getQuestionTypeById(Long questionTypeId) throws ResourceNotFoundException;
	
	public QuestionType getActiveQuestionType();
}
