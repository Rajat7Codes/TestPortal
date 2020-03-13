package com.iceico.testportal.Model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Rajat Patil
 * @version 0.1
 *
 */
@Entity
@Table(name = "tab_test_question")
public class TestQuestion implements Serializable {

	private static final long serialVersionUID = -7059080413677665606L;

	public TestQuestion() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "test_question_id")
	private Long testQuestionId;

	@Column(name = "question_id")
	private Long questionId;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, optional = false)
    @JoinColumn( name = "add_test_id", nullable = true)
    private AddTest addTest;
	
	/**
	 * @return the questionId
	 */
	public Long getQuestionId() {
		return questionId;
	}

	/**
	 * @param questionId the questionId to set
	 */
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	/**
	 * @return the testQuestionId
	 */
	public Long getTestQuestionId() {
		return testQuestionId;
	}

	/**
	 * @param testQuestionId the testQuestionId to set
	 */
	public void setTestQuestionId(Long testQuestionId) {
		this.testQuestionId = testQuestionId;
	}

	/**
	 * @return the addTest
	 */
	public AddTest getAddTest() {
		return addTest;
	}

	/**
	 * @param addTest the addTest to set
	 */
	public void setAddTest(AddTest addTest) {
		this.addTest = addTest;
	}

}
