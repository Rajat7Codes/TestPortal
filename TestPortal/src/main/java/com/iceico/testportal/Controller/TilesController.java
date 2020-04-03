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
import com.iceico.testportal.Model.UserProfile;
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

		/* Total Student Count List */

		List<User> userListCount = new ArrayList<User>();
		for (User userPro : this.userService.findAllUsers()) {
			userListCount.add(userPro);
		}
		for (int i = 0; i < userListCount.size(); i++) {
			for (UserProfile userProf : userListCount.get(i).getUserProfiles()) {
				if ((userProf.getType().equals("ADMIN") || userProf.getType().equals("JAVAADMIN")
						|| userProf.getType().equals("WEBADMIN"))) {
					userListCount.remove(i);
				}
			}
		}
		modelMap.addAttribute("totalUsersCount", userListCount.size());

		/* END Total Student Count List */
		/* for today list */
		List<String> todayResultListForPassStudent = new ArrayList<String>();
		List<String> todayResultListForFailStudent = new ArrayList<String>();
		List<Integer> todayCountPassFailStudents = new ArrayList<Integer>();

		/* for monthly lists */
		List<String> monthlyResultListForPassStudent = new ArrayList<String>();
		List<String> monthlyResultListForFailStudent = new ArrayList<String>();
		List<Integer> monthlyCountPassFailStudents = new ArrayList<Integer>();
		List<Integer> topTenStudentsUserId = new ArrayList<Integer>();
		List<Double> topTenStudentsPercentagesMonthly = new ArrayList<Double>();
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
			topTenStudentsUserId.add(testResult.getUserId());
			topTenStudentsPercentagesMonthly.add(testResult.getPercentage());
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
		modelMap.addAttribute("topTenStudentUserId", topTenStudentsUserId);
		modelMap.addAttribute("topTenStudentsPercentagesMonthly", topTenStudentsPercentagesMonthly);
		modelMap.addAttribute("OverallTestCount", this.addTestService.getAddTestList().size());
		/* Today top ten result */
		List<Double> topTenStudentsPercentages = new ArrayList<Double>();
		List<String> topTenStudentsNames = new ArrayList<String>();

		for (TestResult tResult2 : this.dashboardService.getTopTenStudentList(date)) {
			topTenStudentsPercentages.add(tResult2.getPercentage());
			// System.out.println("I want per ============>>>" + tResult2.getPercentage());
			topTenStudentsNames.add(tResult2.getCreatedBy());

		}
		modelMap.addAttribute("topTenPercentages", topTenStudentsPercentages);
		modelMap.addAttribute("topTenStudentsNames", topTenStudentsNames);

		/* End Today top ten result */

		modelMap.addAttribute("testResultStudentMonthly",
				this.dashboardService.getTopTenStudentListMonthly(startDate, lastDate));
		modelMap.addAttribute("testResultStudentToday", this.dashboardService.getTopTenStudentList(date));
		modelMap.addAttribute("userService", userService);
		return "adminDashboard";
	}

	@RequestMapping("/java/student/dashboard")
	public String javaDashboard(ModelMap modelMap, Locale locale) throws ResourceNotFoundException, ParseException {

		String userDepartmentName = "JAVA";
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		// System.out.println("Inside==============================>>>>>>>>>>>>>");
		/* START student dashboard releated stuff */
		Date date = new Date();
		LocalDate currentdate = LocalDate.now();
		Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(currentdate.withDayOfMonth(1).toString());
		Date lastDate = new SimpleDateFormat("yyyy-MM-dd")
				.parse(currentdate.withDayOfMonth(currentdate.getMonth().maxLength()).toString());
		Integer currentUserId = this.userService.findBySSO(this.getPrincipal()).getId();
		// System.out.println("currentUserId ==============>>>" + currentUserId);
		/* All Question Count */
		List<QuestionBank> questionBankList = this.questionBankService.getQuestionBankList();
		/* All Added Test Count */
		List<AddTest> totalTestList = this.addTestService.getAddTestList();
		String testDepartment = null;
		/* Total Student Count List */
		String currentAdminDepartment = this.userService.findBySSO(this.getPrincipal()).getDepartment()
				.getDepartmentName();

		List<User> userListCount = new ArrayList<User>();
		for (User userPro : this.userService.findAllUsers()) {
			if (userPro.getDepartment().getDepartmentName().equals(currentAdminDepartment)) {
				if (userPro.getDepartment().getDepartmentName()
						.equals(this.userService.findBySSO(getPrincipal()).getDepartment().getDepartmentName())) {
					userListCount.add(userPro);
				}
			}
		}
		for (int i = 0; i < userListCount.size(); i++) {
			for (UserProfile userProf : userListCount.get(i).getUserProfiles()) {
				if ((userProf.getType().equals("ADMIN") || userProf.getType().equals("JAVAADMIN")
						|| userProf.getType().equals("WEBADMIN"))) {
					userListCount.remove(i);
				}
			}
		}
		modelMap.addAttribute("totalJavaUsersCount", userListCount.size());

		/* END Total Student Count List */
		/* FOR CALCULATE TOTAL TEST COUNT DEPARTMENT WISE */
		List<Long> javaTestCount = new ArrayList<Long>();
		for (AddTest test : totalTestList) {
			testDepartment = test.getDepartmentName();
			if (currentAdminDepartment.equals(testDepartment)) {
				javaTestCount.add(test.getAddTestId());
			}
		}
		modelMap.addAttribute("totalTestCountThis", javaTestCount.size());
		/* End HERE FOR CALCULATE TOTAL TEST COUNT DEPARTMENT WISE */

		/* Today Wise */
		List<TestResult> todayTestResult = this.dashboardService
				.getTodaysPerformanceForDepartmentWiseAll(userDepartmentName, date);
		List<String> todayPassStudents = new ArrayList<String>();
		List<String> todayFailStudents = new ArrayList<String>();
		List<Integer> todayPassFailStudentsCount = new ArrayList<Integer>();

		/* Monthly Wise */
		List<TestResult> monthlyTestResult = this.dashboardService
				.getMonthlysPerformanceForDepartmentWiseAllResult(userDepartmentName, startDate, lastDate);
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
		todayPassFailStudentsCount.add(todayPassStudents.size());
		todayPassFailStudentsCount.add(todayFailStudents.size());

		monthlyPassFailStudentsCount.add(monthlyPassStudents.size());
		monthlyPassFailStudentsCount.add(monthlyFailStudents.size());

		modelMap.addAttribute("todayStudentPassFailStatus", todayPassFailStudentsCount);
		modelMap.addAttribute("todayStudentPassFailStatusTotalCount", todayTestResult.size());

		modelMap.addAttribute("monthlyStudentPassFailStatus", monthlyPassFailStudentsCount);
		modelMap.addAttribute("monthlyStudentPassFailStatusTotalCount", monthlyTestResult.size());

		// java Student Monthly TOP 1O LIST
		modelMap.addAttribute("testResultStudentMonthly", this.dashboardService
				.getMonthlysPerformanceForDepartmentWiseAll(userDepartmentName, startDate, lastDate));
		modelMap.addAttribute("testResultStudentToday",
				this.dashboardService.getTodaysPerformanceForDepartmentWiseAll(userDepartmentName, date));
		modelMap.addAttribute("testQuestions", questionBankList.size());
		modelMap.addAttribute("totalTestList", totalTestList.size());
		modelMap.addAttribute("userService", userService);
		// System.out.println("bjkjbkjb" +
		// userService.findBySSO(this.getPrincipal()).getFirstName());
		return "javaDashboard";
	}

	@RequestMapping("/web/student/dashboard")
	public String webDashboard(ModelMap modelMap, Locale locale) throws ResourceNotFoundException, ParseException {
		String userDepartmentName = "WEB";
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		// System.out.println("Inside==============================>>>>>>>>>>>>>");
		/* START student dashboard releated stuff */
		Date date = new Date();
		LocalDate currentdate = LocalDate.now();
		Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(currentdate.withDayOfMonth(1).toString());
		Date lastDate = new SimpleDateFormat("yyyy-MM-dd")
				.parse(currentdate.withDayOfMonth(currentdate.getMonth().maxLength()).toString());
		Integer currentUserId = this.userService.findBySSO(this.getPrincipal()).getId();
		// System.out.println("currentUserId ==============>>>" + currentUserId);
		/* All Question Count */
		List<QuestionBank> questionBankList = this.questionBankService.getQuestionBankList();
		/* All Added Test Count */
		List<AddTest> totalTestList = this.addTestService.getAddTestList();
		String testDepartment = null;
		/* Total Student Count List */
		String currentAdminDepartment = this.userService.findBySSO(this.getPrincipal()).getDepartment()
				.getDepartmentName();

		List<User> userListCount = new ArrayList<User>();
		for (User userPro : this.userService.findAllUsers()) {
			if (userPro.getDepartment().getDepartmentName().equals(currentAdminDepartment)) {
				if (userPro.getDepartment().getDepartmentName()
						.equals(this.userService.findBySSO(getPrincipal()).getDepartment().getDepartmentName())) {
					userListCount.add(userPro);
				}
			}
		}
		for (int i = 0; i < userListCount.size(); i++) {
			for (UserProfile userProf : userListCount.get(i).getUserProfiles()) {
				if ((userProf.getType().equals("ADMIN") || userProf.getType().equals("JAVAADMIN")
						|| userProf.getType().equals("WEBADMIN"))) {
					userListCount.remove(i);
				}
			}
		}
		modelMap.addAttribute("totalWebUsersCount", userListCount.size());

		/* END Total Student Count List */
		/* FOR CALCULATE TOTAL TEST COUNT DEPARTMENT WISE */
		List<Long> webTestCount = new ArrayList<Long>();
		for (AddTest test : totalTestList) {
			testDepartment = test.getDepartmentName();
			if (currentAdminDepartment.equals(testDepartment)) {
				webTestCount.add(test.getAddTestId());
			}
		}
		modelMap.addAttribute("totalTestCountThis", webTestCount.size());
		/* End HERE FOR CALCULATE TOTAL TEST COUNT DEPARTMENT WISE */

		/* Today Wise */
		List<TestResult> todayTestResult = this.dashboardService
				.getTodaysPerformanceForDepartmentWiseAll(userDepartmentName, date);
		List<String> todayPassStudents = new ArrayList<String>();
		List<String> todayFailStudents = new ArrayList<String>();
		List<Integer> todayPassFailStudentsCount = new ArrayList<Integer>();

		/* Monthly Wise */
		List<TestResult> monthlyTestResult = this.dashboardService
				.getMonthlysPerformanceForDepartmentWiseAllResult(userDepartmentName, startDate, lastDate);
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
		todayPassFailStudentsCount.add(todayPassStudents.size());
		todayPassFailStudentsCount.add(todayFailStudents.size());

		monthlyPassFailStudentsCount.add(monthlyPassStudents.size());
		monthlyPassFailStudentsCount.add(monthlyFailStudents.size());

		modelMap.addAttribute("todayStudentPassFailStatus", todayPassFailStudentsCount);
		modelMap.addAttribute("todayStudentPassFailStatusTotalCount", todayTestResult.size());

		modelMap.addAttribute("monthlyStudentPassFailStatus", monthlyPassFailStudentsCount);
		modelMap.addAttribute("monthlyStudentPassFailStatusTotalCount", monthlyTestResult.size());

		modelMap.addAttribute("testResultStudentMonthly", this.dashboardService
				.getMonthlysPerformanceForDepartmentWiseAll(userDepartmentName, startDate, lastDate));
		modelMap.addAttribute("testResultStudentToday",
				this.dashboardService.getTodaysPerformanceForDepartmentWiseAll(userDepartmentName, date));

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
		String userDepartmentName = "JAVA";
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		Date date = new Date();
		LocalDate currentdate = LocalDate.now();
		Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(currentdate.withDayOfMonth(1).toString());
		Date lastDate = new SimpleDateFormat("yyyy-MM-dd")
				.parse(currentdate.withDayOfMonth(currentdate.getMonth().maxLength()).toString());
		String currentAdminDepartment = this.userService.findBySSO(this.getPrincipal()).getDepartment()
				.getDepartmentName();
		Integer currentUserId = this.userService.findBySSO(this.getPrincipal()).getId();
		/* questions Count */
		List<QuestionBank> questionBanks = this.questionBankService.getQuestionBankList();
		modelMap.addAttribute("totalQuestionsCount", this.questionBankService.getQuestionBankList().size());
		/* End questions Count */

		/* Total Student Count List */

		List<User> userListCount = new ArrayList<User>();
		for (User userPro : this.userService.findAllUsers()) {
			if (userPro.getDepartment().getDepartmentName().equals(currentAdminDepartment)) {
				if (userPro.getDepartment().getDepartmentName()
						.equals(this.userService.findBySSO(getPrincipal()).getDepartment().getDepartmentName())) {
					userListCount.add(userPro);
				}
			}
		}
		for (int i = 0; i < userListCount.size(); i++) {
			for (UserProfile userProf : userListCount.get(i).getUserProfiles()) {
				if ((userProf.getType().equals("ADMIN") || userProf.getType().equals("JAVAADMIN")
						|| userProf.getType().equals("WEBADMIN"))) {
					userListCount.remove(i);
				}
			}
		}
		modelMap.addAttribute("totalJavaUsersCount", userListCount.size());

		/* END Total Student Count List */
		/* for today list */
		List<String> todayResultListForPassStudent = new ArrayList<String>();
		List<String> todayResultListForFailStudent = new ArrayList<String>();
		List<Integer> todayCountPassFailStudents = new ArrayList<Integer>();

		/* for monthly lists */
		List<String> monthlyResultListForPassStudent = new ArrayList<String>();
		List<String> monthlyResultListForFailStudent = new ArrayList<String>();
		List<Integer> monthlyCountPassFailStudents = new ArrayList<Integer>();
		List<Integer> topTenStudentsUserId = new ArrayList<Integer>();
		List<Double> topTenStudentsPercentagesMonthly = new ArrayList<Double>();
		for (TestResult testResult : this.dashboardService.getTodaysPerformanceForDepartmentWiseAll(userDepartmentName,
				date)) {
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

		for (TestResult testResult : this.dashboardService
				.getMonthlysPerformanceForDepartmentWiseAll(userDepartmentName, startDate, lastDate)) {
			topTenStudentsUserId.add(testResult.getUserId());
			topTenStudentsPercentagesMonthly.add(testResult.getPercentage());
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
		modelMap.addAttribute("topTenStudentUserId", topTenStudentsUserId);
		modelMap.addAttribute("topTenStudentsPercentagesMonthly", topTenStudentsPercentagesMonthly);
		modelMap.addAttribute("OverallTestCount", this.addTestService.getAddTestList().size());
		/* Today top ten result */
		List<Double> topTenStudentsPercentages = new ArrayList<Double>();
		List<Integer> topTenStudentsUserIdJava = new ArrayList<Integer>();
		List<String> topTenStudentsNames = new ArrayList<String>();

		for (TestResult tResult2 : this.dashboardService.getTodaysPerformanceForDepartmentWiseAll(userDepartmentName,
				date)) {
			topTenStudentsPercentages.add(tResult2.getPercentage());
			topTenStudentsUserIdJava.add(tResult2.getUserId());
			topTenStudentsNames.add(tResult2.getCreatedBy());
		}
		modelMap.addAttribute("topTenPercentagesjava", topTenStudentsPercentages);
		modelMap.addAttribute("topTenStudentsUserIdJava", topTenStudentsUserIdJava);
		modelMap.addAttribute("topTenStudentsNames", topTenStudentsNames);
		/* End Today top ten result */
		modelMap.addAttribute("testResultStudentMonthly", this.dashboardService
				.getMonthlysPerformanceForDepartmentWiseAll(userDepartmentName, startDate, lastDate));
		modelMap.addAttribute("testResultStudentToday",
				this.dashboardService.getTodaysPerformanceForDepartmentWiseAll(userDepartmentName, date));
		modelMap.addAttribute("userService", userService);
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		return "javaAdminDashboard";
	}

	@RequestMapping("/web/admin/dashboard")
	public String webAdminDashboard(ModelMap modelMap, Locale locale) throws ResourceNotFoundException, ParseException {
		String userDepartmentName = "WEB";
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		Date date = new Date();
		LocalDate currentdate = LocalDate.now();
		Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(currentdate.withDayOfMonth(1).toString());
		Date lastDate = new SimpleDateFormat("yyyy-MM-dd")
				.parse(currentdate.withDayOfMonth(currentdate.getMonth().maxLength()).toString());
		String currentAdminDepartment = this.userService.findBySSO(this.getPrincipal()).getDepartment()
				.getDepartmentName();
		//System.out.println("WEB ===========>>" + currentAdminDepartment);
		Integer currentUserId = this.userService.findBySSO(this.getPrincipal()).getId();
		/* questions Count */
		List<QuestionBank> questionBanks = this.questionBankService.getQuestionBankList();
		modelMap.addAttribute("totalQuestionsCount", this.questionBankService.getQuestionBankList().size());
		/* End questions Count */

		/* Total Student Count List */
		List<User> userListCount = new ArrayList<User>();
		for (User userPro : this.userService.findAllUsers()) {
			if (userPro.getDepartment().getDepartmentName().equals(currentAdminDepartment)) {
				if (userPro.getDepartment().getDepartmentName()
						.equals(this.userService.findBySSO(getPrincipal()).getDepartment().getDepartmentName())) {
					userListCount.add(userPro);
				}
			}
		}
		for (int i = 0; i < userListCount.size(); i++) {
			for (UserProfile userProf : userListCount.get(i).getUserProfiles()) {
				if ((userProf.getType().equals("ADMIN") || userProf.getType().equals("JAVAADMIN")
						|| userProf.getType().equals("WEBADMIN"))) {
					userListCount.remove(i);
				}
			}
		}
		modelMap.addAttribute("totalWebUsersCount", userListCount.size());

		/* END Total Student Count List */
		/* for today list */
		List<String> todayResultListForPassStudent = new ArrayList<String>();
		List<String> todayResultListForFailStudent = new ArrayList<String>();
		List<Integer> todayCountPassFailStudents = new ArrayList<Integer>();

		/* for monthly lists */
		List<String> monthlyResultListForPassStudent = new ArrayList<String>();
		List<String> monthlyResultListForFailStudent = new ArrayList<String>();
		List<Integer> monthlyCountPassFailStudents = new ArrayList<Integer>();
		List<Integer> topTenStudentsUserId = new ArrayList<Integer>();
		List<Double> topTenStudentsPercentagesMonthly = new ArrayList<Double>();
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

		for (TestResult testResult : this.dashboardService
				.getMonthlysPerformanceForDepartmentWiseAll(userDepartmentName, startDate, lastDate)) {

			topTenStudentsUserId.add(testResult.getUserId());
			topTenStudentsPercentagesMonthly.add(testResult.getPercentage());
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
		modelMap.addAttribute("topTenStudentUserId", topTenStudentsUserId);
		modelMap.addAttribute("topTenStudentsPercentagesMonthly", topTenStudentsPercentagesMonthly);
		modelMap.addAttribute("OverallTestCount", this.addTestService.getAddTestList().size());
		/* Today top ten result */
		List<Double> topTenStudentsPercentages = new ArrayList<Double>();
		List<String> topTenStudentsNames = new ArrayList<String>();
		List<Integer> topTenStudentsUserIdWeb = new ArrayList<Integer>();

		for (TestResult tResult2 : this.dashboardService.getTodaysPerformanceForDepartmentWiseAll(userDepartmentName,
				date)) {
			topTenStudentsPercentages.add(tResult2.getPercentage());
			topTenStudentsUserIdWeb.add(tResult2.getUserId());
			// System.out.println("I want per ============>>>" + tResult2.getPercentage());
			topTenStudentsNames.add(tResult2.getCreatedBy());

		}
		modelMap.addAttribute("topTenStudentsUserIdWeb", topTenStudentsUserIdWeb);
		modelMap.addAttribute("topTenPercentages", topTenStudentsPercentages);
		modelMap.addAttribute("topTenStudentsNames", topTenStudentsNames);

		/* End Today top ten result */
		modelMap.addAttribute("testResultStudentMonthly", this.dashboardService
				.getMonthlysPerformanceForDepartmentWiseAll(userDepartmentName, startDate, lastDate));
		modelMap.addAttribute("testResultStudentToday",
				this.dashboardService.getTodaysPerformanceForDepartmentWiseAll(userDepartmentName, date));
		modelMap.addAttribute("userService", userService);
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
