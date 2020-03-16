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
import com.iceico.testportal.Model.TestQuestion;
import com.iceico.testportal.Service.AddTestService;
import com.iceico.testportal.Service.SubjectService;
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

			System.out.println("+++++++++++++>>>>>>>> " + addedQuestions);

			addTest.setTestQuestions(addedQuestions);
			addTest.setIsDeleted(false);
			this.addTestService.saveAddTest(addTest);

			modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
			return "redirect:/admin/add/test";
		}
	}

	@RequestMapping("/java/student/test/list")
	public String testList(ModelMap modelMap, Locale locale) throws ResourceNotFoundException, ParseException {
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		modelMap.addAttribute("subjectList", this.subjectService.getSubjectList());
		modelMap.addAttribute("testList", this.addTestService.getAddTestList());
		return "testList";
	}

	/* sample method start test designing */
	@RequestMapping("/java/student/start/test")
	public String startTest(ModelMap modelMap, Locale locale) throws ResourceNotFoundException, ParseException {
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		return "startTest";
	}

	/* sample method start test designing */
	@RequestMapping("/java/student/start/test/image")
	public String startTestImage(ModelMap modelMap, Locale locale) throws ResourceNotFoundException, ParseException {
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		return "startTestImage";
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
