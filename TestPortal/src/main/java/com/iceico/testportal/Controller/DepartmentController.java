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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.iceico.testportal.Exceptions.ResourceNotFoundException;
import com.iceico.testportal.Model.Department;
import com.iceico.testportal.Service.DepartmentService;

@Controller
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	public DepartmentController() {
	}

	@GetMapping("/admin/department")
	public String getDepartment(ModelMap modelMap, Locale locale) {
		modelMap.addAttribute("department", new Department());
		modelMap.addAttribute("departmentList", this.departmentService.getDepartmentList());
		modelMap.addAttribute("user", this.getPrincipal());
		return "department";
	}

	@PostMapping("/admin/department/save")
	public String saveDepartment(@ModelAttribute("department") @Valid Department department,
			BindingResult bindingResult, ModelMap modelMap, Locale locale) {
		if (bindingResult.hasErrors()) {
			modelMap.addAttribute("department", new Department());
			modelMap.addAttribute("departmentList", this.departmentService.getDepartmentList());
			modelMap.addAttribute("user", this.getPrincipal());

			return "department";
		} else {
			this.departmentService.saveDepartment(department);
			modelMap.addAttribute("user", this.getPrincipal());
			return "redirect:/admin/department";
		}
	}

	@GetMapping("/admin/department/edit/{departmentId}")
	public String editDepartment(@PathVariable("departmentId") @Valid Long departmentId, ModelMap modelMap,
			Locale locale) throws ResourceNotFoundException {
		modelMap.addAttribute("department", this.departmentService.getDepartmentById(departmentId));
		modelMap.addAttribute("departmentList", this.departmentService.getDepartmentList());
		modelMap.addAttribute("user", this.getPrincipal());
		return "department";
	}

	@GetMapping("/admin/department/delete/{departmentId}")
	public String deleteDepartment(@PathVariable("departmentId") @Valid Long departmentId, ModelMap modelMap,
			Locale locale) throws ResourceNotFoundException {
		this.departmentService.deleteDepartment(departmentId);
		modelMap.addAttribute("user", this.getPrincipal());
		return "redirect:/admin/department";
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
