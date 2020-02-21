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

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * @author puja
 *
 */
@Entity
@Table(name = "tab_subject")
public class Subject implements Serializable {

	private static final long serialVersionUID = -7059080413677665606L;

	public Subject() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "subject_id")
	private Long subjectId;

	@Column(name = "subject_name")
	private String subjectName;

	@Column(name = "status")
	private Boolean status;

	@OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<AddTest> addTest;

	@OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<QuestionBank> questionBank;

	/**
	 * @param subjectId
	 * @param subjectName
	 * @param status
	 * @param addTest
	 * @param questionBank
	 */
	public Subject(Long subjectId, String subjectName, Boolean status, List<AddTest> addTest,
			List<QuestionBank> questionBank) {
		// super();
		this.subjectId = subjectId;
		this.subjectName = subjectName;
		this.status = status;
		this.addTest = addTest;
		this.questionBank = questionBank;
	}

	/**
	 * @return the subjectId
	 */
	public Long getSubjectId() {
		return subjectId;
	}

	/**
	 * @param subjectId the subjectId to set
	 */
	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	/**
	 * @return the subjectName
	 */
	public String getSubjectName() {
		return subjectName;
	}

	/**
	 * @param subjectName the subjectName to set
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
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
	 * @return the addTest
	 */
	public List<AddTest> getAddTest() {
		return addTest;
	}

	/**
	 * @param addTest the addTest to set
	 */
	public void setAddTest(List<AddTest> addTest) {
		this.addTest = addTest;
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
