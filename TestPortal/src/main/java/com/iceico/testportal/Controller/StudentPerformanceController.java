/**
 * 
 */
package com.iceico.testportal.Controller;

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
import org.springframework.web.bind.annotation.GetMapping;

import com.iceico.testportal.Model.TestResult;
import com.iceico.testportal.Service.DashboardService;
import com.iceico.testportal.Service.UserService;

/**
 * @author LEKHA BHANGE
 * @version 0.1
 * 
 *          Created Date : 26/03/2020
 *
 */
@Controller
public class StudentPerformanceController {

	/**
	 * 
	 */
	public StudentPerformanceController() {

	}

	@Autowired
	private DashboardService dashboardService;

	@Autowired
	private UserService userService;

	/* JAVA STUDENT PANEL METHODS */

	/* JAVA STUDENT PERFORMANCE PAGE */
	@GetMapping("/java/student/individual/performance")
	public String javaStudentIndividualPerformance(ModelMap modelMap, Locale locale) throws java.text.ParseException {
		Date date = new Date();
		LocalDate currentdate = LocalDate.now();
		Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(currentdate.withDayOfMonth(1).toString());
		Date lastDate = new SimpleDateFormat("yyyy-MM-dd")
				.parse(currentdate.withDayOfMonth(currentdate.getMonth().maxLength()).toString());
		List<Double> getTodayPercentage = new ArrayList<Double>();
		List<Double> getStudentTodayTestMarks = new ArrayList<Double>();
		List<Double> getStudentMonthlyTestMarks = new ArrayList<Double>();
		List<Double> getMonthlyPercentage = new ArrayList<Double>();
		Integer userId = this.userService.findBySSO(this.getPrincipal()).getId();
		for (TestResult testMonthly : this.dashboardService.getMonthlysPerformancePercentageAll(startDate, lastDate)) {
			if (userId == testMonthly.getUserId()) {
				getStudentMonthlyTestMarks.add(testMonthly.getObtainedMarks());
				getMonthlyPercentage.add(testMonthly.getPercentage());
				modelMap.addAttribute("percentageMonthly", getMonthlyPercentage);
				modelMap.addAttribute("testMonthly", getStudentMonthlyTestMarks);
			}
		}
		for (TestResult testToday : this.dashboardService.getTodaysPerformancePercentageAll(date)) {
			if (userId == testToday.getUserId()) {
				getStudentTodayTestMarks.add(testToday.getObtainedMarks());
				getTodayPercentage.add(testToday.getPercentage());
				modelMap.addAttribute("percentageToday", getTodayPercentage);
				modelMap.addAttribute("testToday", getStudentTodayTestMarks);
			}
		}
		modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));

		return "j_studentIndividualPerformance";
	}

	/* WEB STUDENT PANEL METHODS */

	/* WEB STUDENT PERFORMANCE PAGE */
	@GetMapping("/web/student/individual/performance")
	public String webStudentIndividualPerformance(ModelMap modelMap, Locale locale) throws java.text.ParseException {
		Date date = new Date();
		LocalDate currentdate = LocalDate.now();
		Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(currentdate.withDayOfMonth(1).toString());
		Date lastDate = new SimpleDateFormat("yyyy-MM-dd")
				.parse(currentdate.withDayOfMonth(currentdate.getMonth().maxLength()).toString());
		List<Double> getTodayPercentage = new ArrayList<Double>();
		List<Double> getStudentTodayTestMarks = new ArrayList<Double>();
		List<Double> getStudentMonthlyTestMarks = new ArrayList<Double>();
		List<Double> getMonthlyPercentage = new ArrayList<Double>();
		Integer userId = this.userService.findBySSO(this.getPrincipal()).getId();
		for (TestResult testMonthly : this.dashboardService.getMonthlysPerformancePercentageAll(startDate, lastDate)) {
			if (userId == testMonthly.getUserId()) {
				getStudentMonthlyTestMarks.add(testMonthly.getObtainedMarks());
				getMonthlyPercentage.add(testMonthly.getPercentage());
				modelMap.addAttribute("percentageMonthly", getMonthlyPercentage);
				modelMap.addAttribute("testMonthly", getStudentMonthlyTestMarks);
			}
		}
		for (TestResult testToday : this.dashboardService.getTodaysPerformancePercentageAll(date)) {
			if (userId == testToday.getUserId()) {
				getStudentTodayTestMarks.add(testToday.getObtainedMarks());
				getTodayPercentage.add(testToday.getPercentage());
				modelMap.addAttribute("percentageToday", getTodayPercentage);
				modelMap.addAttribute("testToday", getStudentTodayTestMarks);
			}
		}
		modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));

		return "w_studentIndividualPerformance";
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
