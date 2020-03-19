/**
 * 
 */
package com.iceico.testportal.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iceico.testportal.Exceptions.ResourceNotFoundException;
import com.iceico.testportal.Model.AddTest;
import com.iceico.testportal.Model.QuestionBank;
import com.iceico.testportal.Model.TestQuestion;
import com.iceico.testportal.Model.TestResult;
import com.iceico.testportal.Model.User;
import com.iceico.testportal.Service.AddTestService;
import com.iceico.testportal.Service.QuestionBankService;
import com.iceico.testportal.Service.SubjectService;
import com.iceico.testportal.Service.TestResultService;
import com.iceico.testportal.Service.UserService;

/**
 * @author Rajat
 * @version 0.1 Creation Date: 18/02/2020
 *
 */
@Controller
public class AddTestController {
	public AddTestController() {
	}

	@Autowired
	private AddTestService addTestService;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private QuestionBankService questionBankService;

	@Autowired
	private TestResultService testResultService;

	@Autowired
	private UserService userService;

	@GetMapping("/admin/add/test")
	public String getTest(ModelMap modelMap, Locale locale) {
		modelMap.addAttribute("addTest", new AddTest());
		modelMap.addAttribute("subjectList", this.subjectService.getSubjectList());
		modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
		return "addTest";
	}

	@GetMapping("/admin/add/test/edit/{addTestId}")
	public String editTest(@PathVariable("addTestId") @Valid Long addTestId, ModelMap modelMap, Locale locale)
			throws ResourceNotFoundException {
		AddTest addTest = this.addTestService.getAddTestById(addTestId);
		modelMap.addAttribute("addTest", addTest);
		modelMap.addAttribute("subjectList", this.subjectService.getSubjectList());
		modelMap.addAttribute("dateValue", new SimpleDateFormat("dd/MM/YYYY").format(addTest.getDate()));
		modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
		return "addTest";
	}

	@GetMapping("/admin/add/test/delete/{addTestId}")
	public String deleteTest(@PathVariable("addTestId") @Valid Long addTestId, ModelMap modelMap, Locale locale)
			throws ResourceNotFoundException {
		AddTest addTest = this.addTestService.getAddTestById(addTestId);
		addTest.setIsDeleted(true);
		this.addTestService.saveAddTest(addTest);
		modelMap.addAttribute("testList", this.addTestService.getAddTestList());
		modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
		return "addTestView";
	}

	@GetMapping("/admin/add/test/view")
	public String viewTest(ModelMap modelMap, Locale locale) {
		modelMap.addAttribute("subjectList", this.subjectService.getSubjectList());
		modelMap.addAttribute("testList", this.addTestService.getAddTestList());
		modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
		return "addTestView";
	}

	@PostMapping("/admin/add/test/save")
	public String saveTest(@RequestParam("questionsJson") String questions,
			@ModelAttribute("addTest") @Valid AddTest addTest, BindingResult bindingResult, ModelMap modelMap,
			Locale locale) throws ParseException, ResourceNotFoundException {
		if (bindingResult.hasErrors()) {
			modelMap.addAttribute("addTest", new AddTest());
			modelMap.addAttribute("subjectList", this.subjectService.getSubjectList());
			modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
			System.out.println("*********************Not Saved!!!");
			System.out.println("***********" + bindingResult.getAllErrors());
			return "addTest";
		} else {

			List<TestQuestion> addedQuestions = new ArrayList<TestQuestion>();

			JSONParser parser = new JSONParser();
			JSONArray questionArray = (JSONArray) parser.parse(questions);

			for (int i = 0; i < questionArray.size(); i++) {
				TestQuestion testQuestions = new TestQuestion();
				JSONObject obj = (JSONObject) questionArray.get(i);
				testQuestions.setQuestionId(Long.parseLong(obj.get("questionId") + ""));
				testQuestions.setAddTest(addTest);
				addedQuestions.add(testQuestions);
				System.out.println("+++++++++++++>>>>>>>> " + obj.get("questionId"));
			}
			System.out.println("********************* Saving in process....one step behind!!!");
			System.out.println("+++++++++++++>>>>>>>> " + addedQuestions);
			addTest.setTestQuestions(addedQuestions);
			addTest.setIsDeleted(false);
			this.addTestService.saveAddTest(addTest);
			System.out.println("********************* Saved!!!");

			modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
			return "redirect:/admin/add/test";
		}
	}

	/* sample method result page designing of admin section */
	/*
	 * @RequestMapping("/admin/test/result") public String viewResult(ModelMap
	 * modelMap, Locale locale) throws ResourceNotFoundException, ParseException {
	 * modelMap.addAttribute("user",
	 * this.userService.findBySSO(this.getPrincipal()));
	 * modelMap.addAttribute("testList", this.addTestService.getAddTestList());
	 * return "viewResult"; }
	 */

	@GetMapping("/admin/test/result/{addTestId}")
	public String viewResult(@PathVariable("addTestId") @Valid Long addTestId, ModelMap modelMap, Locale locale)
			throws ResourceNotFoundException {
		AddTest addTest = this.addTestService.getAddTestById(addTestId);
		modelMap.addAttribute("test", addTest);

		int marks = 0;
		for (TestQuestion question : addTest.getTestQuestions()) {
			QuestionBank questionBank = this.questionBankService.getQuestionBankById(question.getQuestionId());
			marks += questionBank.getMarks();
		}
		int attempted = 0;
		int passed = 0;
		int failed = 0;
		List<String[]> list = new ArrayList<String[]>();
		for (TestResult testResult : this.testResultService.getTestResultList()) {
			if (addTestId == testResult.getTestId()) {
				if (testResult.getResultStatus().equals("PASS")) {

					passed++;
				} else {
					failed++;
				}
				attempted++;
			}
			for (User user : this.userService.findAllUsers()) {
				if (testResult.getUserId() == user.getId()) {
					String array[] = new String[5];
					array[0] = user.getFirstName() + " " + user.getLastName();

					array[1] = testResult.getResultStatus();
					array[2] = new SimpleDateFormat("dd/MM/YYYY").format(testResult.getDate());

					list.add(array);
				}
			}

		}
		System.out.println(list);
		modelMap.addAttribute("userList", list);
		modelMap.addAttribute("marks", marks);
		modelMap.addAttribute("attempted", attempted);
		modelMap.addAttribute("passed", passed);
		modelMap.addAttribute("failed", failed);
		modelMap.addAttribute("dateValue", new SimpleDateFormat("dd/MM/YYYY").format(addTest.getDate()));
		modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
		return "viewResult";
	}

	/* result page of admin section */
	@RequestMapping("/admin/test/result/list")
	public String testResult(ModelMap modelMap, Locale locale) throws ResourceNotFoundException, ParseException {
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		modelMap.addAttribute("test", new AddTest());
		modelMap.addAttribute("testList", this.addTestService.getAddTestList());

		return "testResult";
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
