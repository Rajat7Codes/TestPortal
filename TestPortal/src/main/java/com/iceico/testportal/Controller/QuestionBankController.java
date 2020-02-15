/**
 * 
 */
package com.iceico.testportal.Controller;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

		/*
		 * String Option_Four = "Option_Four"; String Option_Two = "Option_Two"; String
		 * Image_question = "Image_question"; String Write_Program = "Write_Program";
		 */

		String[] strArray = { "Option_Four", "Option_Two", "Image_question", "Write_Program" };
		modelMap.addAttribute("type", strArray);
		for (String string : strArray) {
			System.out.println("==========>>" + string);
			modelMap.addAttribute("questionType", string);

		}

		/*
		 * JSONArray jsonArray = new JSONArray(); JSONObject jsonObject = new
		 * JSONObject(); jsonObject.put("Option_Four", Option_Four);
		 * jsonObject.put("Option_Two", Option_Two); jsonObject.put("Image_question",
		 * Image_question); jsonObject.put("Write_Program", Write_Program);
		 * jsonArray.add(jsonObject); modelMap.addAttribute("quetionTypeArray",
		 * jsonArray);
		 */
		return "questionBank";
	}

	@PostMapping(name = "/admin/question/bank/save")
	public String saveQuestionBank(@ModelAttribute("questionBank") @Valid QuestionBank questionBank,
			BindingResult bindingResult, ModelMap modelMap) {

		return "";
	}

}
