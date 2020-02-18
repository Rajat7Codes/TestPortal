/**
 * 
 */
package com.iceico.testportal.Controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.iceico.testportal.Model.AddTest;
import com.iceico.testportal.Service.AddTestService;

/**
 * @author puja
 *
 */
@Controller
public class AddTestController {
	public AddTestController() {
	}

	@Autowired
	private AddTestService addTestService;

	@GetMapping("/admin/addTest")
	public String getDepartment(ModelMap modelMap, Locale locale) {
		modelMap.addAttribute("addTest", new AddTest());
		// modelMap.addAttribute("departmentList",
		// this.departmentService.getDepartmentList());
		modelMap.addAttribute("user", this.getPrincipal());
		return "addTest";
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
