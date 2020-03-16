/**
 * 
 */
package com.iceico.testportal.Controller;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iceico.testportal.Exceptions.ResourceNotFoundException;
import com.iceico.testportal.Service.AddTestService;
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

	@Autowired
	private UserService userService;

	@Autowired
	private AddTestService addTestService;

	@RequestMapping("/admin/dashboard")
	public String adminDashboard(ModelMap modelMap, Locale locale) throws ResourceNotFoundException, ParseException {
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		return "adminDashboard";
	}

	@RequestMapping("/java/student/dashboard")
	public String javaDashboard(ModelMap modelMap, Locale locale) throws ResourceNotFoundException, ParseException {
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
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
