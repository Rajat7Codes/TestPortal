package com.iceico.testportal.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Rajat
 *
 */

@Entity
@Table(name = "tab_add_test")
public class AddTest implements Serializable {

	private static final long serialVersionUID = 1705449679883875529L;

	public AddTest() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "add_test_id")
	private Long addTestId;

	@Column(name = "test_name")
	private String testName;

	@Column(name = "time")
	private Integer time;

	@Column(name = "date")
	private Date date;

	@Column(name = "negative_marking")
	private Boolean negativeMarking;

	@Column(name = "ratio")
	private Integer ratio;

	@Column(name = "delete_flag")
	private Boolean isDeleted;

	@Column(name = "instructions")
	private String instructions;

	@Column(name = "passing_percent")
	private float passingPercent;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "subject_id", insertable = true, nullable = true, updatable = true)
	private Subject subject;

	@OneToMany(mappedBy = "addTest", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<TestQuestion> testQuestions;

	/**
	 * @return the addTestId
	 */
	public Long getAddTestId() {
		return addTestId;
	}

	/**
	 * @param addTestId the addTestId to set
	 */
	public void setAddTestId(Long addTestId) {
		this.addTestId = addTestId;
	}

	/**
	 * @return the testName
	 */
	public String getTestName() {
		return testName;
	}

	/**
	 * @param testName the testName to set
	 */
	public void setTestName(String testName) {
		this.testName = testName;
	}

	/**
	 * @return the time
	 */
	public Integer getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(Integer time) {
		this.time = time;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the negativeMarking
	 */
	public Boolean getNegativeMarking() {
		return negativeMarking;
	}

	/**
	 * @param negativeMarking the negativeMarking to set
	 */
	public void setNegativeMarking(Boolean negativeMarking) {
		this.negativeMarking = negativeMarking;
	}

	/**
	 * @return the ratio
	 */
	public Integer getRatio() {
		return ratio;
	}

	/**
	 * @param ratio the ratio to set
	 */
	public void setRatio(Integer ratio) {
		this.ratio = ratio;
	}

	/**
	 * @return the isDeleted
	 */
	public Boolean getIsDeleted() {
		return isDeleted;
	}

	/**
	 * @param isDeleted the isDeleted to set
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * @return the instructions
	 */
	public String getInstructions() {
		return instructions;
	}

	/**
	 * @param instructions the instructions to set
	 */
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	/**
	 * @return the passingPercent
	 */
	public float getPassingPercent() {
		return passingPercent;
	}

	/**
	 * @param passingPercent the passingPercent to set
	 */
	public void setPassingPercent(float passingPercent) {
		this.passingPercent = passingPercent;
	}

	/**
	 * @return the subject
	 */
	public Subject getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	/**
	 * @return the testQuestions
	 */
	public List<TestQuestion> getTestQuestions() {
		return testQuestions;
	}

	/**
	 * @param testQuestions the testQuestions to set
	 */
	public void setTestQuestions(List<TestQuestion> testQuestions) {
		this.testQuestions = testQuestions;
	}
	
}
