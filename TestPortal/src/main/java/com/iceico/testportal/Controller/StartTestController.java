/**
 * 
 */
package com.iceico.testportal.Controller;

import java.text.ParseException;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iceico.testportal.Exceptions.ResourceNotFoundException;
import com.iceico.testportal.Model.AddTest;
import com.iceico.testportal.Service.AddTestService;
import com.iceico.testportal.Service.UserService;

/**
 * @author Rajat
 * @version 0.1
 * Creation Date: 16/03/2020
 *
 */
@Controller
public class StartTestController {
	public StartTestController() {
		
	}

	@Autowired
	private AddTestService addTestService;

	@Autowired
	private UserService userService;



	/* Test List page */
	@RequestMapping("/java/student/test/list")
	public String testList(ModelMap modelMap, Locale locale) throws ResourceNotFoundException, ParseException {
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		modelMap.addAttribute("testList", this.addTestService.getAddTestList());
		return "testList";
	}

	/* Start Test page */
	@RequestMapping("/java/student/start/test/{testId}")
	public String startTest(@PathVariable("testId") @Valid Long testId,ModelMap modelMap, Locale locale) throws ResourceNotFoundException, ParseException {
		
		AddTest test = addTestService.getAddTestById(testId);
		modelMap.addAttribute( "addTest", test);
		modelMap.addAttribute( "testQuestions", test.getTestQuestions());
		modelMap.addAttribute( "user", this.userService.findBySSO(this.getPrincipal()));
		return "startTest";
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
