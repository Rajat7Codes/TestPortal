/**
 * 
 */
package com.iceico.testportal.Model;

import java.io.Serializable;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.iceico.testportal.audit.Auditable.Auditable;

/**
 * @author SAMEER KADGAYE
 * @version 0.1
 * 
 * Created Date : 14/02/2020
 *
 */
@Entity
@Table(name = "tab_question_bank")
@EntityListeners(AuditingEntityListener.class)
public class QuestionBank extends Auditable<String> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7239795614090219812L;

	/**
	 * 
	 */
	public QuestionBank() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_bank_Id")
	private Long questionBankId;

	@Column(name = "question")
	private String question;
	
	@Column(name = "sample_input")
	private String sampleInput;
	
	@Column(name = "sample_output")
	private String sampleOutput;
	
	@Column(name = "hidden_input")
	private String hiddenInput;
	
	@Column(name = "hidden_output")
	private String hiddenOutput;

	@Column(name = "marks")
	private Integer marks;

	@Column(name = "image")
	private String imageName;

	@Column(name = "filePath")
	private String filePath;

	@Column(name = "fileType")
	private String contentType;

	@Column(name = "description")
	private String description;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "question_Type_Id", insertable = true, nullable = true, updatable = true)
	private QuestionType questionType;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "test_question_id", insertable = true, nullable = true, updatable = true)
	private TestQuestion testQuestion;

	@OneToMany(mappedBy = "questionBank", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Options> options;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "subject_id", insertable = true, nullable = true, updatable = true)
	private Subject subject;

	/**
	 * @param questionBankId
	 * @param question
	 * @param marks
	 * @param imageName
	 * @param filePath
	 * @param contentType
	 * @param description
	 * @param questionType
	 * @param addTest
	 * @param testQuestion
	 * @param options
	 * @param subject
	 */
	public QuestionBank(Long questionBankId, String question, Integer marks, String imageName, String filePath,
			String contentType, String description, QuestionType questionType, AddTest addTest,
			TestQuestion testQuestion, List<Options> options, Subject subject) {
		super();
		this.questionBankId = questionBankId;
		this.question = question;
		this.marks = marks;
		this.imageName = imageName;
		this.filePath = filePath;
		this.contentType = contentType;
		this.description = description;
		this.questionType = questionType;
		this.testQuestion = testQuestion;
		this.options = options;
		this.subject = subject;
	}

	/**
	 * @return the questionBankId
	 */
	public Long getQuestionBankId() {
		return questionBankId;
	}

	/**
	 * @param questionBankId the questionBankId to set
	 */
	public void setQuestionBankId(Long questionBankId) {
		this.questionBankId = questionBankId;
	}

	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * @return the marks
	 */
	public Integer getMarks() {
		return marks;
	}

	/**
	 * @param marks the marks to set
	 */
	public void setMarks(Integer marks) {
		this.marks = marks;
	}

	/**
	 * @return the imageName
	 */
	public String getImageName() {
		return imageName;
	}

	/**
	 * @param imageName the imageName to set
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the questionType
	 */
	public QuestionType getQuestionType() {
		return questionType;
	}

	/**
	 * @param questionType the questionType to set
	 */
	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	/**
	 * @return the testQuestion
	 */
	public TestQuestion getTestQuestion() {
		return testQuestion;
	}

	/**
	 * @param testQuestion the testQuestion to set
	 */
	public void setTestQuestion(TestQuestion testQuestion) {
		this.testQuestion = testQuestion;
	}

	/**
	 * @return the options
	 */
	public List<Options> getOptions() {
		return options;
	}

	/**
	 * @param options the options to set
	 */
	public void setOptions(List<Options> options) {
		this.options = options;
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
	 * @return the sampleInput
	 */
	public String getSampleInput() {
		return sampleInput;
	}

	/**
	 * @param sampleInput the sampleInput to set
	 */
	public void setSampleInput(String sampleInput) {
		this.sampleInput = sampleInput;
	}

	/**
	 * @return the sampleOutput
	 */
	public String getSampleOutput() {
		return sampleOutput;
	}

	/**
	 * @param sampleOutput the sampleOutput to set
	 */
	public void setSampleOutput(String sampleOutput) {
		this.sampleOutput = sampleOutput;
	}

	/**
	 * @return the hiddenInput
	 */
	public String getHiddenInput() {
		return hiddenInput;
	}

	/**
	 * @param hiddenInput the hiddenInput to set
	 */
	public void setHiddenInput(String hiddenInput) {
		this.hiddenInput = hiddenInput;
	}

	/**
	 * @return the hiddenOutput
	 */
	public String getHiddenOutput() {
		return hiddenOutput;
	}

	/**
	 * @param hiddenOutput the hiddenOutput to set
	 */
	public void setHiddenOutput(String hiddenOutput) {
		this.hiddenOutput = hiddenOutput;
	}
}
