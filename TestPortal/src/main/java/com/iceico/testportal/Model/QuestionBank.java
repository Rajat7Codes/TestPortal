/**
 * 
 */
package com.iceico.testportal.Model;

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
 * @author sameer
 *
 */
@Entity
@Table(name = "tab_question_bank")
public class QuestionBank {

	/**
	 * 
	 */
	public QuestionBank() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_bank_Id")
	private Long questionBankId;

	@Column(name = "question")
	private String question;

	@Column(name = "marks")
	private String marks;

	@Column(name = "subject")
	private String subject;

	@Column(name = "image")
	private String imageName;

	@Column(name = "filePath")
	private String filePath;

	@Column(name = "fileType")
	private String contentType;

	@Column(name = "description")
	private String description;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "questionTypeId", insertable = true, nullable = true, updatable = true)
	private QuestionType questionType;

	@OneToMany(mappedBy = "questionBank", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Options> options;

	/**
	 * @param questionBankId
	 * @param question
	 * @param marks
	 * @param subject
	 * @param imageName
	 * @param filePath
	 * @param contentType
	 * @param description
	 * @param questionType
	 * @param options
	 */
	public QuestionBank(Long questionBankId, String question, String marks, String subject, String imageName,
			String filePath, String contentType, String description, QuestionType questionType, List<Options> options) {
		super();
		this.questionBankId = questionBankId;
		this.question = question;
		this.marks = marks;
		this.subject = subject;
		this.imageName = imageName;
		this.filePath = filePath;
		this.contentType = contentType;
		this.description = description;
		this.questionType = questionType;
		this.options = options;
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
	public String getMarks() {
		return marks;
	}

	/**
	 * @param marks the marks to set
	 */
	public void setMarks(String marks) {
		this.marks = marks;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
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

}
