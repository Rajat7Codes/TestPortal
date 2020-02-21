/**
 * 
 */
package com.iceico.testportal.Controller;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.iceico.testportal.Model.AddTest;
import com.iceico.testportal.Service.AddTestService;
import com.iceico.testportal.Service.SubjectService;
import com.iceico.testportal.Service.UserService;

/**
 * @author puja
 * @version 0.1
 * Creation Date: 18/02/2020
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

	@PostMapping("/admin/add/test/save")
	public String saveTest(@ModelAttribute("addTest") @Valid AddTest addTest, BindingResult bindingResult,
			ModelMap modelMap, Locale locale) {
		if (bindingResult.hasErrors()) {
			modelMap.addAttribute("addTest", new AddTest());
			modelMap.addAttribute("subjectList", this.subjectService.getSubjectList());
			modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
			System.out.println("*********************Not Saved!!!");
			System.out.println("***********" + bindingResult.getAllErrors());
			return "addTest";
		} else {
			System.out.println("====================>Saved!!!");
			this.addTestService.saveAddTest(addTest);
			modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
			return "redirect:/admin/add/test";
		}
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
