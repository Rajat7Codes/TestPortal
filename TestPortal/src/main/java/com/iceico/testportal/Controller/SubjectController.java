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
import com.iceico.testportal.Model.Subject;
import com.iceico.testportal.Service.SubjectService;

@Controller
public class SubjectController {

	@Autowired
	private SubjectService subjectService;

	public SubjectController() {
	}

	@GetMapping("/admin/subject")
	public String getSubject(ModelMap modelMap, Locale locale) {
		modelMap.addAttribute("subject", new Subject());
		modelMap.addAttribute("subjectList", this.subjectService.getSubjectList());
		modelMap.addAttribute("user", this.getPrincipal());
		return "subject";
	}

	@PostMapping("/admin/subject/save")
	public String saveSubject(@ModelAttribute("subject") @Valid Subject subject, BindingResult bindingResult,
			ModelMap modelMap, Locale locale) {
		if (bindingResult.hasErrors()) {
			modelMap.addAttribute("subject", new Subject());
			modelMap.addAttribute("subjectList", this.subjectService.getSubjectList());
			modelMap.addAttribute("user", this.getPrincipal());

			return "subject";
		} else {
			this.subjectService.saveSubject(subject);
			modelMap.addAttribute("user", this.getPrincipal());
			return "redirect:/admin/subject";
		}
	}

	@GetMapping("/admin/subject/edit/{subjectId}")
	public String editSubject(@PathVariable("subjectId") @Valid Long subjectId, ModelMap modelMap, Locale locale)
			throws ResourceNotFoundException {
		modelMap.addAttribute("subject", this.subjectService.getSubjectById(subjectId));
		modelMap.addAttribute("subjectList", this.subjectService.getSubjectList());
		modelMap.addAttribute("user", this.getPrincipal());
		return "subject";
	}

	@GetMapping("/admin/subject/delete/{subjectId}")
	public String deleteSubject(@PathVariable("subjectId") @Valid Long subjectId, ModelMap modelMap, Locale locale)
			throws ResourceNotFoundException {
		this.subjectService.deleteSubject(subjectId);
		modelMap.addAttribute("user", this.getPrincipal());
		return "redirect:/admin/subject";
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
