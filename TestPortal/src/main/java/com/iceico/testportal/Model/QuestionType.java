package com.iceico.testportal.Model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tab_question_type")
public class QuestionType implements Serializable {

	private static final long serialVersionUID = -7951876516053022509L;

	public QuestionType() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_type_id")
	private Long questionTypeId;

	@Column(name = "type")
	private String type;

	@Column(name = "status")
	private Boolean status;

	@Column(name = "program_type")
	private Boolean programType;

	@Column(name = "image_type")
	private Boolean imageType;

	@OneToMany(mappedBy = "questionType", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<QuestionBank> questionBank;

	/**
	 * @param questionTypeId
	 * @param type
	 * @param status
	 * @param programType
	 * @param imageType
	 * @param questionBank
	 */
	public QuestionType(Long questionTypeId, String type, Boolean status, Boolean programType, Boolean imageType,
			List<QuestionBank> questionBank) {
		super();
		this.questionTypeId = questionTypeId;
		this.type = type;
		this.status = status;
		this.programType = programType;
		this.imageType = imageType;
		this.questionBank = questionBank;
	}

	/**
	 * @return the questionTypeId
	 */
	public Long getQuestionTypeId() {
		return questionTypeId;
	}

	/**
	 * @param questionTypeId the questionTypeId to set
	 */
	public void setQuestionTypeId(Long questionTypeId) {
		this.questionTypeId = questionTypeId;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the status
	 */
	public Boolean getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}

	/**
	 * @return the programType
	 */
	public Boolean getProgramType() {
		return programType;
	}

	/**
	 * @param programType the programType to set
	 */
	public void setProgramType(Boolean programType) {
		this.programType = programType;
	}

	/**
	 * @return the imageType
	 */
	public Boolean getImageType() {
		return imageType;
	}

	/**
	 * @param imageType the imageType to set
	 */
	public void setImageType(Boolean imageType) {
		this.imageType = imageType;
	}

	/**
	 * @return the questionBank
	 */
	public List<QuestionBank> getQuestionBank() {
		return questionBank;
	}

	/**
	 * @param questionBank the questionBank to set
	 */
	public void setQuestionBank(List<QuestionBank> questionBank) {
		this.questionBank = questionBank;
	}

}
