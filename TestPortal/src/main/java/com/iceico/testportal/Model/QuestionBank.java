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
 *          Created Date : 14/02/2020 updated by: puja
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
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_bank_Id")
	private Long questionBankId;

	@Column(name = "question")
	private String question;

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

	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "add_test_id", insertable = true, nullable = true, updatable = true)
	private AddTest addTest;

	@OneToMany(mappedBy = "questionBank", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Options> options;

	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
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
	 * @param options
	 * @param subject
	 */
	public QuestionBank(Long questionBankId, String question, Integer marks, String imageName, String filePath,
			String contentType, String description, QuestionType questionType, AddTest addTest, List<Options> options,
			Subject subject) {
		super();
		this.questionBankId = questionBankId;
		this.question = question;
		this.marks = marks;
		this.imageName = imageName;
		this.filePath = filePath;
		this.contentType = contentType;
		this.description = description;
		this.questionType = questionType;
		this.addTest = addTest;
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

}
