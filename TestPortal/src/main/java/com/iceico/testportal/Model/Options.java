/**
 * 
 */
package com.iceico.testportal.Model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.iceico.testportal.audit.Auditable.Auditable;

/**
 * @author sameer
 *
 */
@Entity
@Table(name = "tab_options")
@EntityListeners(AuditingEntityListener.class)
public class Options extends Auditable<String> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -248038106833636883L;

	/**
	 * 
	 */
	public Options() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "options_Id")
	private Long optionsId;

	@Column(name = "option_Name")
	private String optionName;

	@Column(name = "correct_answer")
	private Boolean correctAnswer;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "questionBankId", insertable = true, nullable = true, updatable = true)
	private QuestionBank questionBank;

	/**
	 * @param optionsId
	 * @param optionName
	 * @param correctAnswer
	 * @param questionBank
	 */
	public Options(Long optionsId, String optionName, Boolean correctAnswer, QuestionBank questionBank) {
		super();
		this.optionsId = optionsId;
		this.optionName = optionName;
		this.correctAnswer = correctAnswer;
		this.questionBank = questionBank;
	}

	/**
	 * @return the optionsId
	 */
	public Long getOptionsId() {
		return optionsId;
	}

	/**
	 * @param optionsId the optionsId to set
	 */
	public void setOptionsId(Long optionsId) {
		this.optionsId = optionsId;
	}

	/**
	 * @return the optionName
	 */
	public String getOptionName() {
		return optionName;
	}

	/**
	 * @param optionName the optionName to set
	 */
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	/**
	 * @return the correctAnswer
	 */
	public Boolean getCorrectAnswer() {
		return correctAnswer;
	}

	/**
	 * @param correctAnswer the correctAnswer to set
	 */
	public void setCorrectAnswer(Boolean correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	/**
	 * @return the questionBank
	 */
	public QuestionBank getQuestionBank() {
		return questionBank;
	}

	/**
	 * @param questionBank the questionBank to set
	 */
	public void setQuestionBank(QuestionBank questionBank) {
		this.questionBank = questionBank;
	}

}
