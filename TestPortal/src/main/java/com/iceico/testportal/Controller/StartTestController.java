/*
 * 
 */

package com.iceico.testportal.Controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iceico.testportal.Exceptions.ResourceNotFoundException;
import com.iceico.testportal.Model.AddTest;
import com.iceico.testportal.Model.Options;
import com.iceico.testportal.Model.QuestionBank;
import com.iceico.testportal.Model.TestQuestion;
import com.iceico.testportal.Service.AddTestService;
import com.iceico.testportal.Service.CompilerService;
import com.iceico.testportal.Service.QuestionBankService;
import com.iceico.testportal.Service.UserService;

/**
 * @author Rajat
 * @version 0.1 
 * Creation Date: 16/03/2020
 */
@Controller
public class StartTestController {
	
	public StartTestController() {

	}

	@Autowired
	private AddTestService addTestService;

	@Autowired
	private UserService userService;

	@Autowired
	private QuestionBankService questionBankService;

	@Autowired
	private CompilerService compilerService;

	/* Test List page */
	@RequestMapping("/java/student/test/list")
	public String testList(ModelMap modelMap, Locale locale) throws ResourceNotFoundException, ParseException {
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		modelMap.addAttribute("testList", this.addTestService.getAddTestList());
		return "testList";
	}

	/* Start Test page */
	@RequestMapping("/java/student/start/test/{testId}")
	public String startTest(@PathVariable("testId") @Valid Long testId, ModelMap modelMap, Locale locale)
			throws ResourceNotFoundException, ParseException {

		AddTest test = addTestService.getAddTestById(testId);
		modelMap.addAttribute("addTest", test);

		List<QuestionBank> questionDetailList = new ArrayList<QuestionBank>();

		for (TestQuestion question : test.getTestQuestions()) {
			questionDetailList.add(this.questionBankService.getQuestionBankById(question.getQuestionId()));
		}

		modelMap.addAttribute("testQuestions", questionDetailList);
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		return "startTest";
	}

	@SuppressWarnings({ "deprecation" })
	@RequestMapping(value = "/java/student/start/test/compiler", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public @ResponseBody JSONObject runCode(@RequestParam("language") String languageIn,
			@RequestParam("code") String code)
			throws ResourceNotFoundException, ParseException, org.json.simple.parser.ParseException {

		System.out.println("languageIn =========> " + languageIn);

		String output = compilerService.runCode(languageIn, code);
		JSONObject outObj = (JSONObject) new JSONParser().parse(output);

		System.out.println("===========>" + outObj.get("output"));

		return null;
	}

	@SuppressWarnings({ "deprecation" })
	@RequestMapping(value = "/java/student/end/test", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public @ResponseBody JSONObject endTest(@RequestParam("QnA") String qnA)
			throws ResourceNotFoundException, ParseException, org.json.simple.parser.ParseException {

		System.out.println("==========>" + qnA);
		JSONParser parser = new JSONParser();
		JSONArray allAnswers = (JSONArray) parser.parse(qnA);

		HashMap<QuestionBank, Options> submittedAns = new HashMap<QuestionBank, Options>();

		for (int i = 0; i < allAnswers.size(); i++) {
			JSONObject answer = (JSONObject) allAnswers.get(i);
			QuestionBank question = this.questionBankService
					.getQuestionBankById(Long.parseLong(answer.get("questionId") + ""));
			Options option = null;
			for (Options opt : question.getOptions()) {
				if (opt.getOptionsId() == Long.parseLong(answer.get("optionId") + "")) {
					option = opt;
				}
			}
			submittedAns.put(question, option);
		}

		return null;
	}

	@RequestMapping("/java/student/test/result")
	public String testResult(ModelMap modelMap, Locale locale) throws ResourceNotFoundException, ParseException {
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		return "result";
	}

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
