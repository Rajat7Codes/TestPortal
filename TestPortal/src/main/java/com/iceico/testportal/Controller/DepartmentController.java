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
import com.iceico.testportal.Service.UserService;

/**
 * @author puja
 * @version 0.1
 * 
 *          Created Date: 13/02/2020
 *
 */
@Controller
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private UserService userService;

	public DepartmentController() {

	}

	/**
	 * MASTER ADMIN METHODS
	 */

	/* NEW DEPARTMENT & DEPARTMENT LIST */
	@GetMapping("/admin/department")
	public String getDepartment(ModelMap modelMap, Locale locale) {
		modelMap.addAttribute("department", new Department());
		modelMap.addAttribute("departmentList", this.departmentService.getDepartmentList());
		modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
		return "department";
	}

	/* SAVE DEPARTMENT */
	@PostMapping("/admin/department/save")
	public String saveDepartment(@ModelAttribute("department") @Valid Department department,
			BindingResult bindingResult, ModelMap modelMap, Locale locale) {
		if (bindingResult.hasErrors()) {
			modelMap.addAttribute("department", new Department());
			modelMap.addAttribute("departmentList", this.departmentService.getDepartmentList());
			modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));

			return "department";
		} else {
			this.departmentService.saveDepartment(department);
			modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
			return "redirect:/admin/department";
		}
	}

	/* EDIT DEPARTMENT */
	@GetMapping("/admin/department/edit/{departmentId}")
	public String editDepartment(@PathVariable("departmentId") @Valid Long departmentId, ModelMap modelMap,
			Locale locale) throws ResourceNotFoundException {
		modelMap.addAttribute("department", this.departmentService.getDepartmentById(departmentId));
		modelMap.addAttribute("departmentList", this.departmentService.getDepartmentList());
		modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
		return "department";
	}

	/* DELETE DEPARTMENT */
	@GetMapping("/admin/department/delete/{departmentId}")
	public String deleteDepartment(@PathVariable("departmentId") @Valid Long departmentId, ModelMap modelMap,
			Locale locale) throws ResourceNotFoundException {
		this.departmentService.deleteDepartment(departmentId);
		return "redirect:/admin/department";
	}

	/**
	 * JAVA ADMIN METHODS
	 */

	/* NEW DEPARTMENT & DEPARTMENT LIST */
	@GetMapping("/java/admin/department")
	public String getDepartment_java(ModelMap modelMap, Locale locale) {
		modelMap.addAttribute("department", new Department());
		modelMap.addAttribute("departmentList", this.departmentService.getDepartmentList());
		modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
		return "j_department";
	}

	/* SAVE DEPARTMENT */
	@PostMapping("/java/admin/department/save")
	public String saveDepartment_java(@ModelAttribute("department") @Valid Department department,
			BindingResult bindingResult, ModelMap modelMap, Locale locale) {
		if (bindingResult.hasErrors()) {
			modelMap.addAttribute("department", new Department());
			modelMap.addAttribute("departmentList", this.departmentService.getDepartmentList());
			modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));

			return "j_department";
		} else {
			this.departmentService.saveDepartment(department);
			modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
			return "redirect:/java/admin/department";
		}
	}

	/* EDIT DEPARTMENT */
	@GetMapping("/java/admin/department/edit/{departmentId}")
	public String editDepartment_java(@PathVariable("departmentId") @Valid Long departmentId, ModelMap modelMap,
			Locale locale) throws ResourceNotFoundException {
		modelMap.addAttribute("department", this.departmentService.getDepartmentById(departmentId));
		modelMap.addAttribute("departmentList", this.departmentService.getDepartmentList());
		modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
		return "j_department";
	}

	/* DELETE DEPARTMENT */
	@GetMapping("/java/admin/department/delete/{departmentId}")
	public String deleteDepartment_java(@PathVariable("departmentId") @Valid Long departmentId, ModelMap modelMap,
			Locale locale) throws ResourceNotFoundException {
		this.departmentService.deleteDepartment(departmentId);
		return "redirect:/java/admin/department";
	}

	/**
	 * WEB ADMIN METHODS
	 */

	/* NEW DEPARTMENT & DEPARTMENT LIST */
	@GetMapping("/web/admin/department")
	public String getDepartment_web(ModelMap modelMap, Locale locale) {
		modelMap.addAttribute("department", new Department());
		modelMap.addAttribute("departmentList", this.departmentService.getDepartmentList());
		modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
		return "w_department";
	}

	/* SAVE DEPARTMENT */
	@PostMapping("/web/admin/department/save")
	public String saveDepartment_web(@ModelAttribute("department") @Valid Department department,
			BindingResult bindingResult, ModelMap modelMap, Locale locale) {
		if (bindingResult.hasErrors()) {
			modelMap.addAttribute("department", new Department());
			modelMap.addAttribute("departmentList", this.departmentService.getDepartmentList());
			modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));

			return "w_department";
		} else {
			this.departmentService.saveDepartment(department);
			modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
			return "redirect:/web/admin/department";
		}
	}

	/* EDIT DEPARTMENT */
	@GetMapping("/web/admin/department/edit/{departmentId}")
	public String editDepartment_web(@PathVariable("departmentId") @Valid Long departmentId, ModelMap modelMap,
			Locale locale) throws ResourceNotFoundException {
		modelMap.addAttribute("department", this.departmentService.getDepartmentById(departmentId));
		modelMap.addAttribute("departmentList", this.departmentService.getDepartmentList());
		modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
		return "w_department";
	}

	/* DELETE DEPARTMENT */
	@GetMapping("/web/admin/department/delete/{departmentId}")
	public String deleteDepartment_web(@PathVariable("departmentId") @Valid Long departmentId, ModelMap modelMap,
			Locale locale) throws ResourceNotFoundException {
		this.departmentService.deleteDepartment(departmentId);
		return "redirect:/web/admin/department";
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
