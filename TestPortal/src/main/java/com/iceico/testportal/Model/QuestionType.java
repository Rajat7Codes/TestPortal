package com.iceico.testportal.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

	/**
	 * @param questionTypeId
	 * @param type
	 * @param status
	 */
	public QuestionType(Long questionTypeId, String type, Boolean status) {
		// super();
		this.questionTypeId = questionTypeId;
		this.type = type;
		this.status = status;
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

}
