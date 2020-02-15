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
import com.iceico.testportal.Model.QuestionType;
import com.iceico.testportal.Service.QuestionTypeService;

@Controller
public class QuestionTypeController {

	@Autowired
	private QuestionTypeService questionTypeService;

	public QuestionTypeController() {
	}

	@GetMapping("/admin/questionType")
	public String getQuestionType(ModelMap modelMap, Locale locale) {
		modelMap.addAttribute("questionType", new QuestionType());
		modelMap.addAttribute("questionTypeList", this.questionTypeService.getQuestionTypeList());
		modelMap.addAttribute("user", this.getPrincipal());
		return "questionType";

	}

	@PostMapping("/admin/questionType/save")
	public String saveQuestionType(@ModelAttribute("questionType") @Valid QuestionType questionType,
			BindingResult bindingResult, ModelMap modelMap, Locale locale) {
		if (bindingResult.hasErrors()) {
			modelMap.addAttribute("questionType", new QuestionType());
			modelMap.addAttribute("questionTypeList", this.questionTypeService.getQuestionTypeList());
			modelMap.addAttribute("user", this.getPrincipal());
			return "questionType";

		} else {
			this.questionTypeService.saveQuestionType(questionType);
			modelMap.addAttribute("user", this.getPrincipal());
			return "redirect:/admin/questionType";
		}
	}

	@GetMapping("/admin/questionType/edit/{questionTypeId}")
	public String editQuestionType(@PathVariable("questionTypeId") @Valid Long questionTypeId, ModelMap modelMap,
			Locale locale) throws ResourceNotFoundException {
		modelMap.addAttribute("questionType", this.questionTypeService.getQuestionTypeById(questionTypeId));
		modelMap.addAttribute("questionTypeList", this.questionTypeService.getQuestionTypeList());
		modelMap.addAttribute("user", this.getPrincipal());
		return "questionType";

	}

	@GetMapping("/admin/questionType/delete/{questionTypeId}")
	public String deleteQuestionType(@PathVariable("questionTypeId") @Valid Long questionTypeId, ModelMap modelMap,
			Locale locale) throws ResourceNotFoundException {
		this.questionTypeService.deleteQuestionType(questionTypeId);
		modelMap.addAttribute("user", this.getPrincipal());
		return "redirect:/admin/questionType";
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
