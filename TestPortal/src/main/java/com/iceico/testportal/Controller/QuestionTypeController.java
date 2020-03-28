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
import com.iceico.testportal.Service.UserService;

/**
 * @author PUJA POKALE
 * @version 0.1
 * 
 *          Created Date: 13/02/2020
 * 
 *          Updated By : SAMEER KADGAYE
 * 
 *          Updated Date : 21/02/2020
 * 
 */

@Controller
public class QuestionTypeController {

	@Autowired
	private QuestionTypeService questionTypeService;
	@Autowired
	private UserService usrService;

	@Autowired
	private UserService userService;

	public QuestionTypeController() {

	}

	/**
	 * MASTER ADMIN METHODS
	 * 
	 */

	/* NEW QUESTION TYPE & QUESTION TYPE LIST */
	@GetMapping("/admin/questionType")
	public String getQuestionType(ModelMap modelMap, Locale locale) {
		modelMap.addAttribute("questionType", new QuestionType());
		modelMap.addAttribute("questionTypeList", this.questionTypeService.getQuestionTypeList());
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		return "questionType";
	}

	/* SAVE QUESTION TYPE */
	@PostMapping("/admin/questionType/save")
	public String saveQuestionType(@ModelAttribute("questionType") @Valid QuestionType questionType,
			BindingResult bindingResult, ModelMap modelMap, Locale locale) {
		if (bindingResult.hasErrors()) {
			modelMap.addAttribute("questionType", new QuestionType());
			modelMap.addAttribute("questionTypeList", this.questionTypeService.getQuestionTypeList());
			modelMap.addAttribute("user", usrService.findBySSO(this.getPrincipal()));
			return "questionType";

		} else {
			this.questionTypeService.saveQuestionType(questionType);
			modelMap.addAttribute("user", usrService.findBySSO(this.getPrincipal()));
			return "redirect:/admin/questionType";
		}
	}

	/* EDIT QUESTION TYPE */
	@GetMapping("/admin/questionType/edit/{questionTypeId}")
	public String editQuestionType(@PathVariable("questionTypeId") @Valid Long questionTypeId, ModelMap modelMap,
			Locale locale) throws ResourceNotFoundException {
		modelMap.addAttribute("questionType", this.questionTypeService.getQuestionTypeById(questionTypeId));
		modelMap.addAttribute("questionTypeList", this.questionTypeService.getQuestionTypeList());
		modelMap.addAttribute("user", usrService.findBySSO(this.getPrincipal()));
		return "questionType";
	}

	/* DELETE QUESTION TYPE */
	@GetMapping("/admin/questionType/delete/{questionTypeId}")
	public String deleteQuestionType(@PathVariable("questionTypeId") @Valid Long questionTypeId, ModelMap modelMap,
			Locale locale) throws ResourceNotFoundException {
		this.questionTypeService.deleteQuestionType(questionTypeId);
		return "redirect:/admin/questionType";
	}

	/**
	 * JAVA ADMIN METHODS
	 * 
	 */

	/* NEW QUESTION TYPE & QUESTION TYPE LIST */
	@GetMapping("/java/admin/questionType")
	public String getQuestionType_java(ModelMap modelMap, Locale locale) {
		modelMap.addAttribute("questionType", new QuestionType());
		modelMap.addAttribute("questionTypeList", this.questionTypeService.getQuestionTypeList());
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		return "j_questionType";
	}

	/* SAVE QUESTION TYPE */
	@PostMapping("/java/admin/questionType/save")
	public String saveQuestionType_java(@ModelAttribute("questionType") @Valid QuestionType questionType,
			BindingResult bindingResult, ModelMap modelMap, Locale locale) {
		if (bindingResult.hasErrors()) {
			modelMap.addAttribute("questionType", new QuestionType());
			modelMap.addAttribute("questionTypeList", this.questionTypeService.getQuestionTypeList());
			modelMap.addAttribute("user", usrService.findBySSO(this.getPrincipal()));
			return "j_questionType";

		} else {
			this.questionTypeService.saveQuestionType(questionType);
			modelMap.addAttribute("user", usrService.findBySSO(this.getPrincipal()));
			return "redirect:/java/admin/questionType";
		}
	}

	/* EDIT QUESTION TYPE */
	@GetMapping("/java/admin/questionType/edit/{questionTypeId}")
	public String editQuestionType_java(@PathVariable("questionTypeId") @Valid Long questionTypeId, ModelMap modelMap,
			Locale locale) throws ResourceNotFoundException {
		modelMap.addAttribute("questionType", this.questionTypeService.getQuestionTypeById(questionTypeId));
		modelMap.addAttribute("questionTypeList", this.questionTypeService.getQuestionTypeList());
		modelMap.addAttribute("user", usrService.findBySSO(this.getPrincipal()));
		return "j_questionType";
	}

	/* DELETE QUESTION TYPE */
	@GetMapping("/java/admin/questionType/delete/{questionTypeId}")
	public String deleteQuestionType_java(@PathVariable("questionTypeId") @Valid Long questionTypeId, ModelMap modelMap,
			Locale locale) throws ResourceNotFoundException {
		this.questionTypeService.deleteQuestionType(questionTypeId);
		return "redirect:/java/admin/questionType";
	}

	/**
	 * WEB ADMIN METHODS
	 * 
	 */

	/* NEW QUESTION TYPE & QUESTION TYPE LIST */
	@GetMapping("/web/admin/questionType")
	public String getQuestionType_web(ModelMap modelMap, Locale locale) {
		modelMap.addAttribute("questionType", new QuestionType());
		modelMap.addAttribute("questionTypeList", this.questionTypeService.getQuestionTypeList());
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		return "w_questionType";
	}

	/* SAVE QUESTION TYPE */
	@PostMapping("/web/admin/questionType/save")
	public String saveQuestionType_web(@ModelAttribute("questionType") @Valid QuestionType questionType,
			BindingResult bindingResult, ModelMap modelMap, Locale locale) {
		if (bindingResult.hasErrors()) {
			modelMap.addAttribute("questionType", new QuestionType());
			modelMap.addAttribute("questionTypeList", this.questionTypeService.getQuestionTypeList());
			modelMap.addAttribute("user", usrService.findBySSO(this.getPrincipal()));
			return "w_questionType";

		} else {
			this.questionTypeService.saveQuestionType(questionType);
			modelMap.addAttribute("user", usrService.findBySSO(this.getPrincipal()));
			return "redirect:/web/admin/questionType";
		}
	}

	/* EDIT QUESTION TYPE */
	@GetMapping("/web/admin/questionType/edit/{questionTypeId}")
	public String editQuestionType_web(@PathVariable("questionTypeId") @Valid Long questionTypeId, ModelMap modelMap,
			Locale locale) throws ResourceNotFoundException {
		modelMap.addAttribute("questionType", this.questionTypeService.getQuestionTypeById(questionTypeId));
		modelMap.addAttribute("questionTypeList", this.questionTypeService.getQuestionTypeList());
		modelMap.addAttribute("user", usrService.findBySSO(this.getPrincipal()));
		return "w_questionType";
	}

	/* DELETE QUESTION TYPE */
	@GetMapping("/web/admin/questionType/delete/{questionTypeId}")
	public String deleteQuestionType_web(@PathVariable("questionTypeId") @Valid Long questionTypeId, ModelMap modelMap,
			Locale locale) throws ResourceNotFoundException {
		this.questionTypeService.deleteQuestionType(questionTypeId);
		return "redirect:/web/admin/questionType";
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
