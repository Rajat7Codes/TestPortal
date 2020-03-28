/**
 * 
 */
package com.iceico.testportal.Controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.iceico.testportal.Model.TestResult;
import com.iceico.testportal.Model.User;
import com.iceico.testportal.Service.AddTestService;
import com.iceico.testportal.Service.CompilerService;
import com.iceico.testportal.Service.QuestionBankService;
import com.iceico.testportal.Service.TestResultService;
import com.iceico.testportal.Service.UserService;

/**
 * @author Rajat
 * @version 0.1 Creation Date: 16/03/2020
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

	@Autowired
	private TestResultService testResultService;

	/* Test List page */
	@RequestMapping("/java/student/test/list")
	public String testList(ModelMap modelMap, Locale locale) throws ResourceNotFoundException, ParseException {

		HashMap<Long, String> testList = new HashMap<Long, String>();
		
		for (AddTest addTest : this.addTestService.getAddTestList()) {
			for (TestResult result : testResultService.getTestResultList()) {
				if (this.userService.findBySSO(getPrincipal()).getId() == result.getUserId()) {
					testList.put(addTest.getAddTestId(), "not given");
				}
			}
		}
					
		
		for (AddTest addTest : this.addTestService.getAddTestList()) {
			for (TestResult result : testResultService.getTestResultList()) {
				if (this.userService.findBySSO(getPrincipal()).getId() == result.getUserId()) {
					if (addTest.getAddTestId() == result.getTestId()) {
						testList.put(addTest.getAddTestId(), "given");
					}
				}
			}
		}
		
		List<AddTest> addTestList = new ArrayList<AddTest>();
		for( Long testId : testList.keySet()) {
			if(testList.get(testId).equals("not given")) {
				addTestList.add(this.addTestService.getAddTestById(testId));
			}
		}


		modelMap.addAttribute("testList", addTestList);
		User user = this.userService.findBySSO(this.getPrincipal());
		modelMap.addAttribute("user", user);
		return "testList";

	}

	/* Start Test page */
	@RequestMapping("/java/student/start/test/{testId}")
	public String startTest(@PathVariable("testId") @Valid Long testId, ModelMap modelMap, Locale locale)
			throws ResourceNotFoundException, ParseException {

		AddTest test = addTestService.getAddTestById(testId);
		modelMap.addAttribute("addTest", test);
		// System.out.println("");
		List<QuestionBank> questionDetailList = new ArrayList<QuestionBank>();

		for (TestQuestion question : test.getTestQuestions()) {
			questionDetailList.add(this.questionBankService.getQuestionBankById(question.getQuestionId()));
		}

		modelMap.addAttribute("testQuestions", questionDetailList);
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		return "startTest";
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@RequestMapping(value = "/java/student/start/test/compiler", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public @ResponseBody JSONObject runCode(@RequestParam("language") String languageIn,
			@RequestParam("code") String code, @RequestParam("quesId") Long quesId)
			throws ResourceNotFoundException, ParseException, org.json.simple.parser.ParseException {

		QuestionBank question = this.questionBankService.getQuestionBankById(quesId);
		String response = this.compilerService.runCodeWithInput(languageIn, code, question.getSampleInput());
		JSONObject resObj = (JSONObject) new JSONParser().parse(response);

		JSONObject outObj = new JSONObject();

		if (question.getSampleOutput().equals(resObj.get("output"))
				|| question.getSampleOutput().equals("\n" + resObj.get("output"))) {
			outObj.put("testCase", "successfull");
		} else {
			outObj.put("testCase", "failed");
		}
		outObj.put("output", resObj.get("output"));

		return outObj;
	}

	@SuppressWarnings({ "deprecation" })
	@RequestMapping(value = "/java/student/end/test", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public @ResponseBody JSONObject endTest(@RequestParam("QnA") String qnA, @RequestParam("testName") String testName,
			@RequestParam("testId") Long testId, ModelMap modelMap)
			throws ResourceNotFoundException, ParseException, org.json.simple.parser.ParseException {

		double totalMarks = 0;
		String yourAnswers = null;

		AddTest addTest = this.addTestService.getAddTestById(testId);

		TestResult testResult = new TestResult();
		for (TestQuestion testQuestion : this.addTestService.getAddTestById(testId).getTestQuestions()) {
			totalMarks += this.questionBankService.getQuestionBankById(testQuestion.getQuestionId()).getMarks();
		}

		JSONParser parser = new JSONParser();
		JSONArray allAnswers = (JSONArray) parser.parse(qnA);

		double obtainedMarks = 0;
		double wm = 0;
		double twm = 0;
		int attempted = allAnswers.size();

		for (int i = 0; i < allAnswers.size(); i++) {
			JSONObject answer = (JSONObject) allAnswers.get(i);
			QuestionBank question = this.questionBankService
					.getQuestionBankById(Long.parseLong(answer.get("questionId") + ""));

			// Checks if question type is coding
			if (question.getQuestionType().getProgramType()) {

				System.out.println("================= In Program type code =================");
				String code = answer.get("code") + "";
				String lang = answer.get("lang") + "";
				String input = question.getHiddenInput();

				String response = this.compilerService.runCodeWithInput(lang, code, input);
				JSONObject resObj = (JSONObject) new JSONParser().parse(response);

				if (yourAnswers == null)
					yourAnswers += resObj.get("output");
				else
					yourAnswers += "," + resObj.get("output");

				if (question.getHiddenOutput().equals(resObj.get("output"))
						|| question.getHiddenOutput().equals("\n" + resObj.get("output"))) {
					obtainedMarks += Integer.parseInt(answer.get("marks").toString());
					System.out.println(obtainedMarks);
				} else {
					// Below code check whether question has negative marking or not
					if (addTest.getNegativeMarking()) {
						String ratio = addTest.getRatio();
						System.out.println("ratio========>" + addTest.getRatio());
						String array[] = ratio.split("/");
						wm = Integer.parseInt(answer.get("marks").toString());
						int r = 0;
						for (String temp : array) {
							array[r] = temp;
							r++;
						}
						System.out.println(array[0]);
						System.out.println(array[1]);

						System.out.println("original marks of wrong question ============>" + wm);
						wm = wm * (Integer.parseInt(array[0]));
						System.out.println(" multiplied by numerator============>" + wm);
						wm = wm / (Integer.parseInt(array[1]));
						System.out.println(" divided by denominator============>" + wm);
						twm += wm;
						System.out.println(" twm============>" + twm);
					}
				}

			} else {
				for (Options opt : question.getOptions()) {

					if (opt.getOptionsId() == Long.parseLong(answer.get("optionId") + "")) {
						Boolean userAnswer = opt.getCorrectAnswer();
						if (yourAnswers == null)
							yourAnswers += opt.getOptionName();
						else
							yourAnswers += "," + opt.getOptionName();

						if (userAnswer == true) {
							obtainedMarks += Integer.parseInt(answer.get("marks").toString());
						} else {
							// Below code check whether question has negative marking or not
							if (addTest.getNegativeMarking()) {
								wm = Integer.parseInt(answer.get("marks").toString());
								String ratio = addTest.getRatio();
								System.out.println("ration========>" + addTest.getRatio());
								String array[] = ratio.split("/");
								int r = 0;
								for (String temp : array) {
									array[r] = temp;
									r++;
								}
								System.out.println(array[0]);
								System.out.println(array[1]);

								System.out.println("original marks of wrong question ============>" + wm);
								wm = wm * (Integer.parseInt(array[0]));
								System.out.println(" multiplied by numerator============>" + wm);
								wm = wm / (Integer.parseInt(array[1]));
								System.out.println(" divided by denominator============>" + wm);
								twm += wm;
								System.out.println(" twm============>" + twm);
							}
						}
					}
				}
			}

		}
		System.out.println("marks without deduction" + obtainedMarks);
		obtainedMarks -= twm;
		System.out.println("twm=>" + twm);
		System.out.println("marks after deduction" + obtainedMarks);

		// Below code returns whether user is failed or passed
		double passingCriteria = this.addTestService.getAddTestById(testId).getPassingPercent();
		double per = (obtainedMarks / totalMarks) * 100;
		String result = null;

		if (per >= passingCriteria) {
			result = "PASS";

		} else {
			result = "FAIL";

		}

		// Saving TestResult
		testResult.setAttempted(attempted);
		testResult.setObtainedMarks(obtainedMarks);
		testResult.setResultStatus(result);
		testResult.setTestName(testName);
		testResult.setTotalMarks(totalMarks);
		testResult.setDate(Calendar.getInstance().getTime());
		testResult.setTestId(testId);
		testResult.setAnswersGiven(yourAnswers);
		testResult.setUserId(this.userService.findBySSO(this.getPrincipal()).getId());
		testResult.setPercentage(per);
		this.testResultService.saveTestResult(testResult);

		JSONObject obj = new JSONObject();
		obj.put("testId", testResult.getTestResultId());
		return obj;
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
