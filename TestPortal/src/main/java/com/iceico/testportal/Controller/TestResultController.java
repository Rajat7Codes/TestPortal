/**
 * 
 */
package com.iceico.testportal.Controller;

import java.util.ArrayList;
import java.util.List;

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

	@GetMapping("/java/student/test/result")
	public String getTestResultPage(ModelMap modelMap) {
		modelMap.addAttribute("testResult", new TestResult());
		modelMap.addAttribute("edit", false);
		return "result";
	}
	


	@GetMapping("/java/student/test/history")
	public String getTestHistory(ModelMap modelMap) {

		modelMap.addAttribute("resultList", this.testResultService.getTestResultList());
		return "testHistory";
	}
	

	@GetMapping("/java/student/view/test/result/{testResultId}")
	public String getTestHistoryEach( @PathVariable Long testResultId, 
			ModelMap modelMap) throws ResourceNotFoundException {

		TestResult result = this.testResultService.getTestResultById(testResultId);
		AddTest addTest = this.addtestService.getAddTestById(result.getTestId());

		List<QuestionBank> questionList = new ArrayList<QuestionBank>();
		
		int totalMarks = 0;
		for(TestQuestion testQ : addTest.getTestQuestions()) {
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
		return "testHistoryEach";
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
