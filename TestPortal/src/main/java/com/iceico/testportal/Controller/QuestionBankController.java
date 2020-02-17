/**
 * 
 */
package com.iceico.testportal.Controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.iceico.testportal.Model.QuestionBank;

/**
 * @author sameer
 *
 */
@Controller
public class QuestionBankController {

	/**
	 * 
	 */
	public QuestionBankController() {
	}

	@GetMapping("/admin/question/bank")
	public String getQuestionBank(ModelMap modelMap, Locale locale) {
		modelMap.addAttribute("questionBank", new QuestionBank());
		return "questionBank";
	}
}
