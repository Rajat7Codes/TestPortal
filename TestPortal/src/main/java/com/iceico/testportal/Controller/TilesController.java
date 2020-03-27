/**
 * 
 */
package com.iceico.testportal.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iceico.testportal.Exceptions.ResourceNotFoundException;
import com.iceico.testportal.Model.AddTest;
import com.iceico.testportal.Model.QuestionBank;
import com.iceico.testportal.Model.TestResult;
import com.iceico.testportal.Model.User;
import com.iceico.testportal.Service.AddTestService;
import com.iceico.testportal.Service.DashboardService;
import com.iceico.testportal.Service.QuestionBankService;
import com.iceico.testportal.Service.TestQuestionService;
import com.iceico.testportal.Service.TestResultService;
import com.iceico.testportal.Service.UserProfileService;
import com.iceico.testportal.Service.UserService;

/**
 * @author LEKHA BHANGE
 * @version 0.1
 * 
 *          Created Date : 11/02/2020
 *
 */

@Controller
public class TilesController {

	/*
	 * @Autowired private DashboardService dashboardService;
	 */

	@Autowired
	private TestResultService testResultService;

	@Autowired
	private DashboardService dashboardService;

	@Autowired
	private QuestionBankService questionBankService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserProfileService userProfileService;

	@Autowired
	private TestQuestionService testQuestionService;

	@Autowired
	private AddTestService addTestService;

	@RequestMapping("/admin/dashboard")
	public String adminDashboard(ModelMap modelMap, Locale locale) throws ResourceNotFoundException, ParseException {
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		Date date = new Date();
		LocalDate currentdate = LocalDate.now();
		Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(currentdate.withDayOfMonth(1).toString());
		Date lastDate = new SimpleDateFormat("yyyy-MM-dd")
				.parse(currentdate.withDayOfMonth(currentdate.getMonth().maxLength()).toString());
		String currentAdminDepartment = this.userService.findBySSO(this.getPrincipal()).getDepartment()
				.getDepartmentName();

		/* questions Count */
		List<QuestionBank> questionBanks = this.questionBankService.getQuestionBankList();
		modelMap.addAttribute("totalQuestionsCount", this.questionBankService.getQuestionBankList().size());
		/* End questions Count */

		/* Total users Count List */
		List<Integer> userListCount = new ArrayList<Integer>();
		for (User userPro : this.userService.findAllUsers()) {
			if (currentAdminDepartment.equals("JAVA")) {
				String users = userPro.getFirstName();
				Integer UserId = userPro.getId();
				if (!users.equals("Admin")) {
					userListCount.add(UserId);
					modelMap.addAttribute("totalJavaUsersCount", userListCount.size());
				}
			}
		}
		/* END Total users Count List */
		/* for today list */
		List<String> todayResultListForPassStudent = new ArrayList<String>();
		List<String> todayResultListForFailStudent = new ArrayList<String>();
		List<Integer> todayCountPassFailStudents = new ArrayList<Integer>();

		/* for monthly lists */
		List<String> monthlyResultListForPassStudent = new ArrayList<String>();
		List<String> monthlyResultListForFailStudent = new ArrayList<String>();
		List<Integer> monthlyCountPassFailStudents = new ArrayList<Integer>();

		for (TestResult testResult : this.dashboardService.getTodaysPerformancePercentageAll(date)) {
			if (testResult.getResultStatus().equals("PASS")) {
				todayResultListForPassStudent.add(testResult.getResultStatus());
			}
			if (testResult.getResultStatus().equals("FAIL")) {
				todayResultListForFailStudent.add(testResult.getResultStatus());
			}
		}
		todayCountPassFailStudents.add(todayResultListForPassStudent.size());
		todayCountPassFailStudents.add(todayResultListForFailStudent.size());
		modelMap.addAttribute("todayTotalTestCount",
				this.dashboardService.getTodaysPerformancePercentageAll(date).size());
		modelMap.addAttribute("todayPassFailStudentCount", todayCountPassFailStudents);

		for (TestResult testResult : this.dashboardService.getMonthlysPerformancePercentageAll(startDate, lastDate)) {
			if (testResult.getResultStatus().equals("PASS")) {
				monthlyResultListForPassStudent.add(testResult.getResultStatus());
			}
			if (testResult.getResultStatus().equals("FAIL")) {
				monthlyResultListForFailStudent.add(testResult.getResultStatus());
			}
		}
		monthlyCountPassFailStudents.add(monthlyResultListForPassStudent.size());
		monthlyCountPassFailStudents.add(monthlyResultListForFailStudent.size());
		modelMap.addAttribute("monthlyTotalTestCount",
				this.dashboardService.getMonthlysPerformancePercentageAll(startDate, lastDate).size());
		modelMap.addAttribute("monthlyPassFailStudentCount", monthlyCountPassFailStudents);
		modelMap.addAttribute("OverallTestCount", this.testResultService.getTestResultList().size());

		/* Today top ten result */
		List<Double> topTenStudentsPercentages = new ArrayList<Double>();
		List<String> topTenStudentsNames = new ArrayList<String>();
		for (TestResult tResult : this.dashboardService.getTopTenStudentList(date)) {
			topTenStudentsPercentages.add(tResult.getPercentage());
			topTenStudentsNames.add(tResult.getCreatedBy());
		}
		modelMap.addAttribute("topTenPercentages", topTenStudentsPercentages);
		modelMap.addAttribute("topTenStudentsNames", topTenStudentsNames);
		/* End Today top ten result */
		return "adminDashboard";
	}

	@RequestMapping("/java/student/dashboard")
	public String javaDashboard(ModelMap modelMap, Locale locale) throws ResourceNotFoundException, ParseException {
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));

		/* START student dashboard releated stuff */
		Date date = new Date();
		LocalDate currentdate = LocalDate.now();
		Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(currentdate.withDayOfMonth(1).toString());
		Date lastDate = new SimpleDateFormat("yyyy-MM-dd")
				.parse(currentdate.withDayOfMonth(currentdate.getMonth().maxLength()).toString());

		Integer currentUserId = this.userService.findBySSO(this.getPrincipal()).getId();

		/* All Question Count */
		List<QuestionBank> questionBankList = this.questionBankService.getQuestionBankList();
		/* All Added Test Count */
		List<AddTest> totalTestList = this.addTestService.getAddTestList();

		/* Total Student Count List */
		String currentAdminDepartment = this.userService.findBySSO(this.getPrincipal()).getDepartment()
				.getDepartmentName();
		List<Integer> userListCount = new ArrayList<Integer>();
		for (User userPro : this.userService.findAllUsers()) {
			if (currentAdminDepartment.equals("JAVA")) {
				String users = userPro.getFirstName();
				Integer UserId = userPro.getId();
				if (!users.equals("Admin")) {
					if (!userPro.getDepartment().getDepartmentName().equals("WEB")) {
						userListCount.add(UserId);
						modelMap.addAttribute("totalJavaUsersCount", userListCount.size());
					}
				}
			}
		}
		/* END Total Student Count List */

		/* Today Wise */
		List<TestResult> todayTestResult = this.dashboardService.getTodaysPerformancePercentageAll(date);
		List<String> todayPassStudents = new ArrayList<String>();
		List<String> todayFailStudents = new ArrayList<String>();
		List<Integer> todayPassFailStudentsCount = new ArrayList<Integer>();

		/* Monthly Wise */
		List<TestResult> monthlyTestResult = this.dashboardService.getMonthlysPerformancePercentageAll(startDate,
				lastDate);
		List<String> monthlyPassStudents = new ArrayList<String>();
		List<String> monthlyFailStudents = new ArrayList<String>();
		List<Integer> monthlyPassFailStudentsCount = new ArrayList<Integer>();

		/* END student dashboard releated stuff */

		for (TestResult tResult : todayTestResult) {
			if (tResult.getResultStatus().equals("PASS")) {
				todayPassStudents.add(tResult.getResultStatus());
			}
			if (tResult.getResultStatus().equals("FAIL")) {
				todayFailStudents.add(tResult.getResultStatus());
			}
		}

		for (TestResult tResult : monthlyTestResult) {
			if (tResult.getResultStatus().equals("PASS")) {
				monthlyPassStudents.add(tResult.getResultStatus());
			}
			if (tResult.getResultStatus().equals("FAIL")) {
				monthlyFailStudents.add(tResult.getResultStatus());
			}
		}

		/* pass fail for each end */

		for (TestResult testResult1 : todayTestResult) {
			for (int i = 0; i < todayTestResult.size(); i++) {
			}

			Double percentage = testResult1.getPercentage();

			if (testResult1.getUserId() == currentUserId) {

			}
		}
		todayPassFailStudentsCount.add(todayPassStudents.size());
		todayPassFailStudentsCount.add(todayFailStudents.size());

		monthlyPassFailStudentsCount.add(monthlyPassStudents.size());
		monthlyPassFailStudentsCount.add(monthlyFailStudents.size());

		modelMap.addAttribute("todayStudentPassFailStatus", todayPassFailStudentsCount);
		modelMap.addAttribute("todayStudentPassFailStatusTotalCount", todayTestResult.size());

		modelMap.addAttribute("monthlyStudentPassFailStatus", monthlyPassFailStudentsCount);
		modelMap.addAttribute("monthlyStudentPassFailStatusTotalCount", monthlyTestResult.size());
		modelMap.addAttribute("testResultStudentMonthly",
				this.dashboardService.getTopTenStudentListMonthly(startDate, lastDate));
		modelMap.addAttribute("testResultStudentToday", this.dashboardService.getTopTenStudentList(date));

		modelMap.addAttribute("testQuestions", questionBankList.size());
		modelMap.addAttribute("totalTestList", totalTestList.size());

		modelMap.addAttribute("userService", userService);
		return "javaDashboard";
	}

	@RequestMapping("/web/student/dashboard")
	public String webDashboard(ModelMap modelMap, Locale locale) throws ResourceNotFoundException, ParseException {
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));

		/* START student dashboard releated stuff */
		Date date = new Date();
		LocalDate currentdate = LocalDate.now();
		Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(currentdate.withDayOfMonth(1).toString());
		Date lastDate = new SimpleDateFormat("yyyy-MM-dd")
				.parse(currentdate.withDayOfMonth(currentdate.getMonth().maxLength()).toString());

		/* All Question Count */
		List<QuestionBank> questionBankList = this.questionBankService.getQuestionBankList();
		/* All Added Test Count */
		List<AddTest> totalTestList = this.addTestService.getAddTestList();

		/* Total Student Count List */
		String currentAdminDepartment = this.userService.findBySSO(this.getPrincipal()).getDepartment()
				.getDepartmentName();
		List<Integer> userListCount = new ArrayList<Integer>();
		for (User userPro : this.userService.findAllUsers()) {
			if (currentAdminDepartment.equals("JAVA")) {
				String users = userPro.getFirstName();
				Integer UserId = userPro.getId();
				if (!users.equals("Admin")) {
					if (!userPro.getDepartment().getDepartmentName().equals("WEB")) {
						userListCount.add(UserId);
						modelMap.addAttribute("totalJavaUsersCount", userListCount.size());
					}
				}
			}
		}
		/* END Total Student Count List */

		/* Today Wise */
		List<TestResult> todayTestResult = this.dashboardService.getTodaysPerformancePercentageAll(date);
		List<String> todayPassStudents = new ArrayList<String>();
		List<String> todayFailStudents = new ArrayList<String>();
		List<Integer> todayPassFailStudentsCount = new ArrayList<Integer>();

		/* Monthly Wise */
		List<TestResult> monthlyTestResult = this.dashboardService.getMonthlysPerformancePercentageAll(startDate,
				lastDate);
		List<String> monthlyPassStudents = new ArrayList<String>();
		List<String> monthlyFailStudents = new ArrayList<String>();
		List<Integer> monthlyPassFailStudentsCount = new ArrayList<Integer>();

		/* END student dashboard releated stuff */

		for (TestResult tResult : todayTestResult) {
			if (tResult.getResultStatus().equals("PASS")) {
				todayPassStudents.add(tResult.getResultStatus());
			}
			if (tResult.getResultStatus().equals("FAIL")) {
				todayFailStudents.add(tResult.getResultStatus());
			}
		}

		for (TestResult tResult : monthlyTestResult) {
			if (tResult.getResultStatus().equals("PASS")) {
				monthlyPassStudents.add(tResult.getResultStatus());
			}
			if (tResult.getResultStatus().equals("FAIL")) {
				monthlyFailStudents.add(tResult.getResultStatus());
			}
		}

		todayPassFailStudentsCount.add(todayPassStudents.size());
		todayPassFailStudentsCount.add(todayFailStudents.size());

		monthlyPassFailStudentsCount.add(monthlyPassStudents.size());
		monthlyPassFailStudentsCount.add(monthlyFailStudents.size());

		modelMap.addAttribute("todayStudentPassFailStatus", todayPassFailStudentsCount);
		modelMap.addAttribute("todayStudentPassFailStatusTotalCount", todayTestResult.size());

		modelMap.addAttribute("monthlyStudentPassFailStatus", monthlyPassFailStudentsCount);
		modelMap.addAttribute("monthlyStudentPassFailStatusTotalCount", monthlyTestResult.size());
		modelMap.addAttribute("testResultStudentMonthly",
				this.dashboardService.getTopTenStudentListMonthly(startDate, lastDate));
		modelMap.addAttribute("testResultStudentToday", this.dashboardService.getTopTenStudentList(date));

		modelMap.addAttribute("testQuestions", questionBankList.size());
		modelMap.addAttribute("totalTestList", totalTestList.size());

		modelMap.addAttribute("userService", userService);

		return "webDashboard";
	}

	@RequestMapping("/drive/student/dashboard")
	public String driveDashboard(ModelMap modelMap, Locale locale) throws ResourceNotFoundException, ParseException {
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		return "driveDashboard";
	}

	@RequestMapping("/java/admin/dashboard")
	public String javaAdminDashboard(ModelMap modelMap, Locale locale)
			throws ResourceNotFoundException, ParseException {
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		return "javaAdminDashboard";
	}

	@RequestMapping("/web/admin/dashboard")
	public String webAdminDashboard(ModelMap modelMap, Locale locale) throws ResourceNotFoundException, ParseException {
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		return "webAdminDashboard";
	}

	/* sample method start test designing */
//	@RequestMapping("/java/student/start/test")
//	public String startTest(ModelMap modelMap, Locale locale) throws ResourceNotFoundException, ParseException {
//		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
//		return "startTest";
//	}

	/* sample method start test designing */
	@RequestMapping("/java/student/start/test/image")
	public String startTestImage(ModelMap modelMap, Locale locale) throws ResourceNotFoundException, ParseException {
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		return "startTestImage";
	}

	/* sample method start test list designing */
//	@RequestMapping("/java/student/test/list")
//	public String testList(ModelMap modelMap, Locale locale) throws ResourceNotFoundException, ParseException {
//		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
//		modelMap.addAttribute("testList", this.addTestService.getAddTestList());
//		return "testList";
//	}

	/* per student wise performance */

	/*
	 * @GetMapping("/java/student/performance") public String
	 * getStudentPerformance(@PathVariable("userId") Integer userId, ModelMap
	 * modelMap) {
	 * 
	 * return "studentPerformance"; }
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
