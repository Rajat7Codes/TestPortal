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

	/*
	 * MASTER ADMIN PANEL METHODS
	 */

	/* ADD TEST */
	@GetMapping("/admin/add/test")
	public String getTest(ModelMap modelMap, Locale locale) {
		modelMap.addAttribute("addTest", new AddTest());
		modelMap.addAttribute("subjectList", this.subjectService.getSubjectList());
		modelMap.addAttribute("edit", false);
		modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
		return "addTest";
	}

	/* EDIT TEST */
	@GetMapping("/admin/add/test/edit/{addTestId}")
	public String editTest(@PathVariable("addTestId") @Valid Long addTestId, ModelMap modelMap, Locale locale)
			throws ResourceNotFoundException {
		AddTest addTest = this.addTestService.getAddTestById(addTestId);
		modelMap.addAttribute("subjectList", this.subjectService.getSubjectList());
		modelMap.addAttribute("addTest", addTest);
		modelMap.addAttribute("edit", true);
		modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
		return "addTest";
	}

	/* DELETE TEST */
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

	/* VIEW TEST */
	@GetMapping("/admin/add/test/view")
	public String viewTest(ModelMap modelMap, Locale locale) {
		modelMap.addAttribute("subjectList", this.subjectService.getSubjectList());
		modelMap.addAttribute("testList", this.addTestService.getAddTestList());
		modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
		return "addTestView";
	}

	/* SAVE TEST */
	@PostMapping("/admin/add/test/save")
	public String saveTest(@RequestParam("questionsJson") String questions,
			@ModelAttribute("addTest") @Valid AddTest addTest, BindingResult bindingResult, ModelMap modelMap,
			Locale locale) throws ParseException, ResourceNotFoundException {
		if (bindingResult.hasErrors()) {
				modelMap.addAttribute("addTest", new AddTest());
				modelMap.addAttribute("subjectList", this.subjectService.getSubjectList());
				modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
				return "addTest";
			} else {

				List<TestQuestion> addedQuestions = new ArrayList<TestQuestion>();
				Integer currentUserId = this.userService.findBySSO(this.getPrincipal()).getId();
				String currentAdminDepartment = this.userService.findBySSO(this.getPrincipal()).getDepartment()
						.getDepartmentName();

				JSONParser parser = new JSONParser();
				JSONArray questionArray = (JSONArray) parser.parse(questions);

				for (int i = 0; i < questionArray.size(); i++) {
					TestQuestion testQuestions = new TestQuestion();
					JSONObject obj = (JSONObject) questionArray.get(i);
					testQuestions.setQuestionId(Long.parseLong(obj.get("questionId") + ""));
					testQuestions.setAddTest(addTest);
					addedQuestions.add(testQuestions);
				}
				addTest.setTestQuestions(addedQuestions);
				addTest.setIsDeleted(false);
				addTest.setUserId(currentUserId);
				addTest.setSubject(addTest.getSubject());
				addTest.setDepartmentName(currentAdminDepartment);
				this.addTestService.saveAddTest(addTest);

				modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
				return "redirect:/admin/add/test/view";
			}
	}

	/* TEST RESULT */
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
			}
			for (User user : this.userService.findAllUsers()) {
				if (testResult.getUserId() == user.getId()) {
					if (addTestId == testResult.getTestId()) {
						String array[] = new String[5];
						array[0] = user.getFirstName() + " " + user.getLastName();
						array[1] = testResult.getResultStatus();
						array[2] = new SimpleDateFormat("dd/MM/YYYY").format(testResult.getDate());
						array[3] = testResult.getObtainedMarks().toString().replace(".0", "");
						list.add(array);
						attempted++;
					}
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

	/* TEST RESULT LIST */
	@RequestMapping("/admin/test/result/list")
	public String testResult(ModelMap modelMap, Locale locale) throws ResourceNotFoundException, ParseException {
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		modelMap.addAttribute("test", new AddTest());
		modelMap.addAttribute("testList", this.addTestService.getAddTestList());

		return "testResult";
	}

	/**
	 * JAVA ADMIN PANEL METHODS
	 *
	 */

	/* ADD TEST */
	@GetMapping("/java/admin/add/test")
	public String getTest_java(ModelMap modelMap, Locale locale) {
		modelMap.addAttribute("addTest", new AddTest());
		modelMap.addAttribute("edit", false);
		modelMap.addAttribute("subjectList", this.subjectService.getSubjectList());
		modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
		return "j_addTest";
	}

	/* EDIT TEST */
	@GetMapping("/java/admin/add/test/edit/{addTestId}")
	public String editTest_java(@PathVariable("addTestId") @Valid Long addTestId, ModelMap modelMap, Locale locale)
			throws ResourceNotFoundException {
		AddTest addTest = this.addTestService.getAddTestById(addTestId);
		modelMap.addAttribute("addTest", addTest);
		modelMap.addAttribute("edit", true);
		modelMap.addAttribute("subjectList", this.subjectService.getSubjectList());
		modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
		return "j_addTest";
	}

	/* DELETE TEST */
	@GetMapping("/java/admin/add/test/delete/{addTestId}")
	public String deleteTest_java(@PathVariable("addTestId") @Valid Long addTestId, ModelMap modelMap, Locale locale)
			throws ResourceNotFoundException {
		AddTest addTest = this.addTestService.getAddTestById(addTestId);
		addTest.setIsDeleted(true);
		this.addTestService.saveAddTest(addTest);
		modelMap.addAttribute("testList", this.addTestService.getAddTestList());
		modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
		return "addTestView";
	}

	/* VIEW TEST */
	@GetMapping("/java/admin/add/test/view")
	public String viewTest_java(ModelMap modelMap, Locale locale) {
		modelMap.addAttribute("subjectList", this.subjectService.getSubjectList());
		modelMap.addAttribute("testList", this.addTestService.getAddTestList());
		modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
		return "j_addTestView";
	}

	/* SAVE TEST */
	@PostMapping("/java/admin/add/test/save")
	public String saveTest_java(@RequestParam("questionsJson") String questions,
			@ModelAttribute("addTest") @Valid AddTest addTest, BindingResult bindingResult, ModelMap modelMap,
			Locale locale) throws ParseException, ResourceNotFoundException {
		if (bindingResult.hasErrors()) {
			modelMap.addAttribute("addTest", new AddTest());
			modelMap.addAttribute("subjectList", this.subjectService.getSubjectList());
			modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
			return "j_addTest";
		} else {

			List<TestQuestion> addedQuestions = new ArrayList<TestQuestion>();
			Integer currentUserId = this.userService.findBySSO(this.getPrincipal()).getId();
			String currentAdminDepartment = this.userService.findBySSO(this.getPrincipal()).getDepartment()
					.getDepartmentName();

			JSONParser parser = new JSONParser();
			JSONArray questionArray = (JSONArray) parser.parse(questions);

			for (int i = 0; i < questionArray.size(); i++) {
				TestQuestion testQuestions = new TestQuestion();
				JSONObject obj = (JSONObject) questionArray.get(i);
				testQuestions.setQuestionId(Long.parseLong(obj.get("questionId") + ""));
				testQuestions.setAddTest(addTest);
				addedQuestions.add(testQuestions);
			}
			addTest.setTestQuestions(addedQuestions);
			addTest.setIsDeleted(false);
			addTest.setUserId(currentUserId);
			addTest.setSubject(addTest.getSubject());
			addTest.setDepartmentName(currentAdminDepartment);
			this.addTestService.saveAddTest(addTest);

			modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
			return "redirect:/java/admin/add/test/view";
		}
	}

	/* TEST RESULT */
	@GetMapping("/java/admin/test/result/{addTestId}")
	public String viewResult_java(@PathVariable("addTestId") @Valid Long addTestId, ModelMap modelMap, Locale locale)
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
			}
			for (User user : this.userService.findAllUsers()) {
				if (testResult.getUserId() == user.getId()) {
					if (addTestId == testResult.getTestId()) {
						String array[] = new String[5];
						array[0] = user.getFirstName() + " " + user.getLastName();
						array[1] = testResult.getResultStatus();
						array[2] = new SimpleDateFormat("dd/MM/YYYY").format(testResult.getDate());
						array[3] = testResult.getObtainedMarks().toString().replace(".0", "");
						list.add(array);
						attempted++;
					}
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
		return "j_viewResult";
	}

	/* TEST RESULT LIST */
	@RequestMapping("/java/admin/test/result/list")
	public String testResult_java(ModelMap modelMap, Locale locale) throws ResourceNotFoundException, ParseException {
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		modelMap.addAttribute("test", new AddTest());
		modelMap.addAttribute("testList", this.addTestService.getAddTestList());
		return "j_testResult";
	}

	/**
	 * WEB ADMIN PANEL METHODS
	 *
	 */

	/* ADD TEST */
	@GetMapping("/web/admin/add/test")
	public String getTest_web(ModelMap modelMap, Locale locale) {
		modelMap.addAttribute("addTest", new AddTest());
		modelMap.addAttribute("subjectList", this.subjectService.getSubjectList());
		modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
		return "w_addTest";
	}

	/* EDIT TEST */
	@GetMapping("/web/admin/add/test/edit/{addTestId}")
	public String editTest_web(@PathVariable("addTestId") @Valid Long addTestId, ModelMap modelMap, Locale locale)
			throws ResourceNotFoundException {
		AddTest addTest = this.addTestService.getAddTestById(addTestId);
		modelMap.addAttribute("addTest", addTest);
		modelMap.addAttribute("edit", true);
		modelMap.addAttribute("subjectList", this.subjectService.getSubjectList());
		modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
		return "w_addTest";
	}

	/* DELETE TEST */
	@GetMapping("/web/admin/add/test/delete/{addTestId}")
	public String deleteTest_web(@PathVariable("addTestId") @Valid Long addTestId, ModelMap modelMap, Locale locale)
			throws ResourceNotFoundException {
		AddTest addTest = this.addTestService.getAddTestById(addTestId);
		addTest.setIsDeleted(true);
		this.addTestService.saveAddTest(addTest);
		modelMap.addAttribute("testList", this.addTestService.getAddTestList());
		modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
		return "w_addTestView";
	}

	/* VIEW TEST */
	@GetMapping("/web/admin/add/test/view")
	public String viewTest_web(ModelMap modelMap, Locale locale) {
		modelMap.addAttribute("subjectList", this.subjectService.getSubjectList());
		modelMap.addAttribute("testList", this.addTestService.getAddTestList());
		modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
		return "w_addTestView";
	}

	/* SAVE TEST */
	@PostMapping("/web/admin/add/test/save")
	public String saveTest_web(@RequestParam("questionsJson") String questions,
			@ModelAttribute("addTest") @Valid AddTest addTest, BindingResult bindingResult, ModelMap modelMap,
			Locale locale) throws ParseException, ResourceNotFoundException {
		if (bindingResult.hasErrors()) {
			modelMap.addAttribute("addTest", new AddTest());
			modelMap.addAttribute("subjectList", this.subjectService.getSubjectList());
			modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
			return "w_addTest";
		} else {

			List<TestQuestion> addedQuestions = new ArrayList<TestQuestion>();
			Integer currentUserId = this.userService.findBySSO(this.getPrincipal()).getId();
			String currentAdminDepartment = this.userService.findBySSO(this.getPrincipal()).getDepartment()
					.getDepartmentName();

			JSONParser parser = new JSONParser();
			JSONArray questionArray = (JSONArray) parser.parse(questions);

			for (int i = 0; i < questionArray.size(); i++) {
				TestQuestion testQuestions = new TestQuestion();
				JSONObject obj = (JSONObject) questionArray.get(i);
				testQuestions.setQuestionId(Long.parseLong(obj.get("questionId") + ""));
				testQuestions.setAddTest(addTest);
				addedQuestions.add(testQuestions);
			}
			addTest.setTestQuestions(addedQuestions);
			addTest.setIsDeleted(false);
			addTest.setUserId(currentUserId);
			addTest.setSubject(addTest.getSubject());
			addTest.setDepartmentName(currentAdminDepartment);
			this.addTestService.saveAddTest(addTest);

			modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
			return "redirect:/web/admin/add/test/view";
		}
	}

	/* TEST RESULT */
	@GetMapping("/web/admin/test/result/{addTestId}")
	public String viewResult_web(@PathVariable("addTestId") @Valid Long addTestId, ModelMap modelMap, Locale locale)
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
			}
			for (User user : this.userService.findAllUsers()) {
				if (testResult.getUserId() == user.getId()) {
					if (addTestId == testResult.getTestId()) {
						String array[] = new String[5];
						array[0] = user.getFirstName() + " " + user.getLastName();
						array[1] = testResult.getResultStatus();
						array[2] = new SimpleDateFormat("dd/MM/YYYY").format(testResult.getDate());
						array[3] = testResult.getObtainedMarks().toString().replace(".0", "");
						list.add(array);
						attempted++;
					}
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
		return "w_viewResult";
	}

	/* TEST RESULT LIST */
	@RequestMapping("/web/admin/test/result/list")
	public String testResult_web(ModelMap modelMap, Locale locale) throws ResourceNotFoundException, ParseException {
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		modelMap.addAttribute("test", new AddTest());
		modelMap.addAttribute("testList", this.addTestService.getAddTestList());
		return "w_testResult";
	}

	/**
	 * 
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