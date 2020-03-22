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
import com.iceico.testportal.Model.QuestionBank;
import com.iceico.testportal.Model.TestResult;
import com.iceico.testportal.Model.User;
import com.iceico.testportal.Service.DashboardService;
import com.iceico.testportal.Service.QuestionBankService;
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
		// System.out.println("Current admin department ==========>>>" +
		// currentAdminDepartment);

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
			// System.out.println("top ====>>" + tResult.getPercentage());
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
		/*
		 * Date date = new Date(); LocalDate currentdate = LocalDate.now(); Date
		 * startDate = new
		 * SimpleDateFormat("yyyy-MM-dd").parse(currentdate.withDayOfMonth(1).toString()
		 * ); Date lastDate = new SimpleDateFormat("yyyy-MM-dd")
		 * .parse(currentdate.withDayOfMonth(currentdate.getMonth().maxLength()).
		 * toString()); List<Double> getTodayPercentage = new ArrayList<Double>();
		 * List<Double> getStudentTodayTestMarks = new ArrayList<Double>(); List<Double>
		 * getStudentMonthlyTestMarks = new ArrayList<Double>(); List<Double>
		 * getMonthlyPercentage = new ArrayList<Double>(); Integer userId =
		 * this.userService.findBySSO(this.getPrincipal()).getId(); for (TestResult
		 * testMonthly :
		 * this.dashboardService.getMonthlysPerformancePercentageAll(startDate,
		 * lastDate)) { if (userId == testMonthly.getUserId()) {
		 * getStudentMonthlyTestMarks.add(testMonthly.getObtainedMarks());
		 * getMonthlyPercentage.add(testMonthly.getPercentage());
		 * modelMap.addAttribute("percentageMonthly", getMonthlyPercentage);
		 * modelMap.addAttribute("testMonthly", getStudentMonthlyTestMarks); } } for
		 * (TestResult testToday :
		 * this.dashboardService.getTodaysPerformancePercentageAll(date)) { if (userId
		 * == testToday.getUserId()) {
		 * getStudentTodayTestMarks.add(testToday.getObtainedMarks());
		 * getTodayPercentage.add(testToday.getPercentage());
		 * modelMap.addAttribute("percentageToday", getTodayPercentage);
		 * modelMap.addAttribute("testToday", getStudentTodayTestMarks); } }
		 */
		return "javaDashboard";
	}

	@RequestMapping("/web/student/dashboard")
	public String webDashboard(ModelMap modelMap, Locale locale) throws ResourceNotFoundException, ParseException {
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		return "webDashboard";
	}

	@RequestMapping("/drive/student/dashboard")
	public String driveDashboard(ModelMap modelMap, Locale locale) throws ResourceNotFoundException, ParseException {
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		return "driveDashboard";
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
