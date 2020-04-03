/**
 * 
 */
package com.iceico.testportal.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.iceico.testportal.Exceptions.ResourceNotFoundException;
import com.iceico.testportal.Model.AddTest;
import com.iceico.testportal.Model.QuestionBank;
import com.iceico.testportal.Model.TestQuestion;
import com.iceico.testportal.Model.TestResult;
import com.iceico.testportal.Service.AddTestService;
import com.iceico.testportal.Service.QuestionBankService;
import com.iceico.testportal.Service.TestResultService;
import com.iceico.testportal.Service.UserService;

/**
 * @author sameer
 *
 */
@Controller
public class TestResultController {

	@Autowired
	private TestResultService testResultService;

	@Autowired
	private AddTestService addtestService;

	@Autowired
	private QuestionBankService questionBankService;

	@Autowired
	private UserService userService;

	/**
	 * 
	 */
	public TestResultController() {

	}

	/* JAVA STUDENT PANEL METHODS */

	/* JAVA STUDENT TEST RESULT */
	@GetMapping("/java/student/test/result")
	public String getTestResultPage_java(ModelMap modelMap) {
		modelMap.addAttribute("testResult", new TestResult());
		modelMap.addAttribute("edit", false);
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));

		return "j_result";
	}

	/* JAVA STUDENT TEST HISTORY */
	@GetMapping("/java/student/test/history")
	public String getTestHistory_java(ModelMap modelMap) {
		List<TestResult> results = this.testResultService.getTestResultList();
		Collections.reverse(results);
		modelMap.addAttribute("resultList", results);
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		return "j_testHistory";
	}

	/* JAVA STUDENT TEST RESULT VIEW */
	@GetMapping("/java/student/view/test/result/{testResultId}")
	public String getTestHistoryEach_java(@PathVariable Long testResultId, ModelMap modelMap)
			throws ResourceNotFoundException {

		TestResult result = this.testResultService.getTestResultById(testResultId);
		AddTest addTest = this.addtestService.getAddTestById(result.getTestId());

		List<QuestionBank> questionList = new ArrayList<QuestionBank>();

		int totalMarks = 0;
		for (TestQuestion testQ : addTest.getTestQuestions()) {
			QuestionBank qBank = this.questionBankService.getQuestionBankById(testQ.getQuestionId());
			totalMarks += qBank.getMarks();
			questionList.add(qBank);
		}

		String allAns = result.getAnswersGiven();
		String testRes[] = allAns.split(",");

		Map<QuestionBank, String> answers = new HashMap<QuestionBank, String>();

		for (QuestionBank questionBank : questionList) {
			answers.put(questionBank, "No Answer Given");
		}
		
		for( int i=0; i<testRes.length; i++) {
			String qIdAns[] = testRes[i].split("&");
			Long qId = Long.parseLong( qIdAns[0]);
			String ans = qIdAns[1];
			for (QuestionBank questionBank : questionList) {
				if( questionBank.getQuestionBankId()==qId) {
					answers.put(questionBank, ans);
				}
			}
		}

		modelMap.addAttribute("answerList", answers);
		modelMap.addAttribute("questionList", questionList);
		modelMap.addAttribute("test", addTest);
		modelMap.addAttribute("questionCount", addTest.getTestQuestions().size());
		modelMap.addAttribute("totalMarks", totalMarks);
		modelMap.addAttribute("result", result);
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));

		return "j_testHistoryEach";
	}

	/* WEB STUDENT PANEL METHODS */

	/* WEB STUDENT TEST RESULT */
	@GetMapping("/web/student/test/result")
	public String getTestResultPage_web(ModelMap modelMap) {
		modelMap.addAttribute("testResult", new TestResult());
		modelMap.addAttribute("edit", false);
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));

		return "w_result";
	}

	/* JAVA STUDENT TEST HISTORY */
	@GetMapping("/web/student/test/history")
	public String getTestHistory_web(ModelMap modelMap) {
		modelMap.addAttribute("resultList", this.testResultService.getTestResultList());
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));

		return "w_testHistory";
	}

	/* WEB STUDENT TEST RESULT VIEW */
	@GetMapping("/web/student/view/test/result/{testResultId}")
	public String getTestHistoryEach_web(@PathVariable Long testResultId, ModelMap modelMap)
			throws ResourceNotFoundException {

		TestResult result = this.testResultService.getTestResultById(testResultId);
		AddTest addTest = this.addtestService.getAddTestById(result.getTestId());

		List<QuestionBank> questionList = new ArrayList<QuestionBank>();

		int totalMarks = 0;
		for (TestQuestion testQ : addTest.getTestQuestions()) {
			QuestionBank qBank = this.questionBankService.getQuestionBankById(testQ.getQuestionId());
			totalMarks += qBank.getMarks();
			questionList.add(qBank);
		}

		String allAns = result.getAnswersGiven();
		String answers[] = allAns.split(",");
		answers[0] = answers[0].replaceFirst("null", "");

		modelMap.addAttribute("answerList", answers);
		modelMap.addAttribute("questionList", questionList);
		modelMap.addAttribute("test", addTest);
		modelMap.addAttribute("questionCount", addTest.getTestQuestions().size());
		modelMap.addAttribute("totalMarks", totalMarks);
		modelMap.addAttribute("result", result);
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));

		return "w_testHistoryEach";
	}

	/**
	 * This method returns the principal[user-name] of logged-in user.
	 */
	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

}
