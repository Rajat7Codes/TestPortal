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
import com.iceico.testportal.Service.UserService;

/**
 * @author PUJA POKALE
 * @version 0.1
 * 
 *          Created Date: 13/02/2020
 *
 */

@Controller
public class SubjectController {

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private UserService userService;

	public SubjectController() {

	}

	/**
	 * MASTER ADMIN METHODS
	 * 
	 */

	/* NEW SUBJECT & SUBJECT LIST */
	@GetMapping("/admin/subject")
	public String getSubject(ModelMap modelMap, Locale locale) {
		modelMap.addAttribute("subject", new Subject());
		modelMap.addAttribute("subjectList", this.subjectService.getSubjectList());
		modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
		return "subject";
	}

	/* SAVE SUBJECT */
	@PostMapping("/admin/subject/save")
	public String saveSubject(@ModelAttribute("subject") @Valid Subject subject, BindingResult bindingResult,
			ModelMap modelMap, Locale locale) {
		if (bindingResult.hasErrors()) {
			modelMap.addAttribute("subject", new Subject());
			modelMap.addAttribute("subjectList", this.subjectService.getSubjectList());
			modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));

			return "subject";
		} else {
			this.subjectService.saveSubject(subject);
			modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
			return "redirect:/admin/subject";
		}
	}

	/* EDIT SUBJECT */
	@GetMapping("/admin/subject/edit/{subjectId}")
	public String editSubject(@PathVariable("subjectId") @Valid Long subjectId, ModelMap modelMap, Locale locale)
			throws ResourceNotFoundException {
		modelMap.addAttribute("subject", this.subjectService.getSubjectById(subjectId));
		modelMap.addAttribute("subjectList", this.subjectService.getSubjectList());
		modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
		return "subject";
	}

	/* DELETE SUBJECT */
	@GetMapping("/admin/subject/delete/{subjectId}")
	public String deleteSubject(@PathVariable("subjectId") @Valid Long subjectId, ModelMap modelMap, Locale locale)
			throws ResourceNotFoundException {
		this.subjectService.deleteSubject(subjectId);
		return "redirect:/admin/subject";
	}

	/**
	 * 
	 * JAVA ADMIN METHODS
	 */

	/* NEW SUBJECT & SUBJECT LIST */
	@GetMapping("/java/admin/subject")
	public String getSubject_java(ModelMap modelMap, Locale locale) {
		modelMap.addAttribute("subject", new Subject());
		modelMap.addAttribute("subjectList", this.subjectService.getSubjectList());
		modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
		return "j_subject";
	}

	/* SAVE SUBJECT */
	@PostMapping("/java/admin/subject/save")
	public String saveSubject_java(@ModelAttribute("subject") @Valid Subject subject, BindingResult bindingResult,
			ModelMap modelMap, Locale locale) {
		if (bindingResult.hasErrors()) {
			modelMap.addAttribute("subject", new Subject());
			modelMap.addAttribute("subjectList", this.subjectService.getSubjectList());
			modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));

			return "j_subject";
		} else {
			this.subjectService.saveSubject(subject);
			modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
			return "redirect:/java/admin/subject";
		}
	}

	/* EDIT SUBJECT */
	@GetMapping("/java/admin/subject/edit/{subjectId}")
	public String editSubject_java(@PathVariable("subjectId") @Valid Long subjectId, ModelMap modelMap, Locale locale)
			throws ResourceNotFoundException {
		modelMap.addAttribute("subject", this.subjectService.getSubjectById(subjectId));
		modelMap.addAttribute("subjectList", this.subjectService.getSubjectList());
		modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
		return "j_subject";
	}

	/* DELETE SUBJECT */
	@GetMapping("/java/admin/subject/delete/{subjectId}")
	public String deleteSubject_java(@PathVariable("subjectId") @Valid Long subjectId, ModelMap modelMap, Locale locale)
			throws ResourceNotFoundException {
		this.subjectService.deleteSubject(subjectId);
		return "redirect:/java/admin/subject";
	}

	/**
	 * WEB ADMIN METHODS
	 * 
	 */

	/* NEW SUBJECT & SUBJECT LIST */
	@GetMapping("/web/admin/subject")
	public String getSubject_web(ModelMap modelMap, Locale locale) {
		modelMap.addAttribute("subject", new Subject());
		modelMap.addAttribute("subjectList", this.subjectService.getSubjectList());
		modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
		return "w_subject";
	}

	/* SAVE SUBJECT */
	@PostMapping("/web/admin/subject/save")
	public String saveSubject_web(@ModelAttribute("subject") @Valid Subject subject, BindingResult bindingResult,
			ModelMap modelMap, Locale locale) {
		if (bindingResult.hasErrors()) {
			modelMap.addAttribute("subject", new Subject());
			modelMap.addAttribute("subjectList", this.subjectService.getSubjectList());
			modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));

			return "w_subject";
		} else {
			this.subjectService.saveSubject(subject);
			modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
			return "redirect:/web/admin/subject";
		}
	}

	/* EDIT SUBJECT */
	@GetMapping("/web/admin/subject/edit/{subjectId}")
	public String editSubject_web(@PathVariable("subjectId") @Valid Long subjectId, ModelMap modelMap, Locale locale)
			throws ResourceNotFoundException {
		modelMap.addAttribute("subject", this.subjectService.getSubjectById(subjectId));
		modelMap.addAttribute("subjectList", this.subjectService.getSubjectList());
		modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
		return "w_subject";
	}

	/* DELETE SUBJECT */
	@GetMapping("/web/admin/subject/delete/{subjectId}")
	public String deleteSubject_web(@PathVariable("subjectId") @Valid Long subjectId, ModelMap modelMap, Locale locale)
			throws ResourceNotFoundException {
		this.subjectService.deleteSubject(subjectId);
		return "redirect:/web/admin/subject";
	}

	/**
	 * DRIVE ADMIN METHODS
	 * 
	 */

	/* NEW SUBJECT & SUBJECT LIST */
	@GetMapping("/drive/admin/subject")
	public String getSubject_drive(ModelMap modelMap, Locale locale) {
		modelMap.addAttribute("subject", new Subject());
		modelMap.addAttribute("subjectList", this.subjectService.getSubjectList());
		modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
		return "d_subject";
	}

	/* SAVE SUBJECT */
	@PostMapping("/drive/admin/subject/save")
	public String saveSubject_drive(@ModelAttribute("subject") @Valid Subject subject, BindingResult bindingResult,
			ModelMap modelMap, Locale locale) {
		if (bindingResult.hasErrors()) {
			modelMap.addAttribute("subject", new Subject());
			modelMap.addAttribute("subjectList", this.subjectService.getSubjectList());
			modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));

			return "d_subject";
		} else {
			this.subjectService.saveSubject(subject);
			modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
			return "redirect:/drive/admin/subject";
		}
	}

	/* EDIT SUBJECT */
	@GetMapping("/drive/admin/subject/edit/{subjectId}")
	public String editSubject_drive(@PathVariable("subjectId") @Valid Long subjectId, ModelMap modelMap, Locale locale)
			throws ResourceNotFoundException {
		modelMap.addAttribute("subject", this.subjectService.getSubjectById(subjectId));
		modelMap.addAttribute("subjectList", this.subjectService.getSubjectList());
		modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
		return "d_subject";
	}

	/* DELETE SUBJECT */
	@GetMapping("/drive/admin/subject/delete/{subjectId}")
	public String deleteSubject_drive(@PathVariable("subjectId") @Valid Long subjectId, ModelMap modelMap, Locale locale)
			throws ResourceNotFoundException {
		this.subjectService.deleteSubject(subjectId);
		return "redirect:/drive/admin/subject";
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
