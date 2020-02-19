/**
 * 
 */
package com.iceico.testportal.Controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.iceico.testportal.Exceptions.ResourceNotFoundException;
import com.iceico.testportal.Model.Options;
import com.iceico.testportal.Model.QuestionBank;
import com.iceico.testportal.Model.QuestionType;
import com.iceico.testportal.Service.QuestionBankService;
import com.iceico.testportal.Service.QuestionTypeService;

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

	@Autowired
	private QuestionTypeService questionTypeService;

	@Autowired
	private QuestionBankService questionBankService;

	@SuppressWarnings("unchecked")
	@GetMapping("/admin/question/bank/new")
	public String getQuestionBank(ModelMap modelMap, Locale locale) {
		QuestionBank questionBank = new QuestionBank();

		modelMap.addAttribute("questionBank", questionBank);
		modelMap.addAttribute("questionTypeList", this.questionTypeService.getQuestionTypeList());
		List<QuestionType> questionTypeList = this.questionTypeService.getQuestionTypeList();
		JSONArray jsonArray = new JSONArray();

		for (QuestionType questionType : questionTypeList) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("questionTypeId", questionType.getQuestionTypeId());
			jsonObject.put("type", questionType.getType());
			jsonObject.put("programType", questionType.getProgramType());
			jsonObject.put("imageType", questionType.getImageType());
			jsonArray.add(jsonObject);
		}
		modelMap.addAttribute("questionTypeJson", jsonArray);
		modelMap.addAttribute("edit", false);
		return "questionBank";
	}

	@RequestMapping(value = "/admin/question/bank/save", method = RequestMethod.POST)
	public String saveQuestionBank(@ModelAttribute("questionBank") @Valid QuestionBank questionBank,
			BindingResult bindingResult, @RequestParam("imageName") MultipartFile imageName,
			@RequestParam("data") String data, ModelMap modelMap, HttpServletRequest httpServletRequest) {
		System.out.println("data =====>>>" + data);
		String uploadFolder = httpServletRequest.getServletContext().getRealPath("/uploaded");
		File directory = new File(uploadFolder);
		if (!directory.exists()) {
			directory.mkdir();
		}
		if (imageName.isEmpty()) {
			questionBank.setImageName(questionBank.getImageName());
			questionBank.setContentType(questionBank.getContentType());
			questionBank.setFilePath(questionBank.getFilePath());
		}
		if (!imageName.isEmpty()) {
			try {
				questionBank.setImageName(imageName.getOriginalFilename());
				questionBank.setContentType(imageName.getContentType());

				BufferedImage src = ImageIO.read(new ByteArrayInputStream(imageName.getBytes()));
				File destination = new File(uploadFolder + "/" + imageName.getOriginalFilename());
				ImageIO.write(src, "jpg", destination);
				questionBank.setFilePath(uploadFolder + "/" + imageName.getOriginalFilename());
			} catch (Exception e) {
				System.out.println("File Upload Error " + e);
			}
		}

		JSONParser jsonParser = new JSONParser();
		List<Options> optionsList = new ArrayList<Options>();

		if (questionBank.getQuestionBankId() == null) {

			try {
				JSONArray jsonArray = (JSONArray) jsonParser.parse(data);
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject jsonObject = (JSONObject) jsonArray.get(i);
					Options options = new Options();
					options.setOptionName(jsonObject.get("optionName").toString());
					options.setCorrectAnswer(jsonObject.get("correctAnswer").toString());
					options.setQuestionBank(questionBank);
					optionsList.add(options);
				}
				questionBank.setOptions(optionsList);
				this.questionBankService.saveQuestionBank(questionBank);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				JSONArray jsonArray = (JSONArray) jsonParser.parse(data);
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject jsonObject = (JSONObject) jsonArray.get(i);
					Options options = new Options();
					String id = jsonObject.get("optionsId").toString();
					Long optionsId = Long.parseLong(id);

					options.setOptionsId(optionsId);
					options.setOptionName(jsonObject.get("optionName").toString());
					options.setCorrectAnswer(jsonObject.get("correctAnswer").toString());
					options.setQuestionBank(questionBank);
					optionsList.add(options);
				}
				questionBank.setOptions(optionsList);
				this.questionBankService.saveQuestionBank(questionBank);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:/admin/question/bank/new";
	}

	@GetMapping("/admin/question/bank/view")
	public String viewQuestionBankPage(ModelMap modelMap, Locale locale) {

		modelMap.addAttribute("questionBankList", this.questionBankService.getQuestionBankList());
		return "questionBankView";
	}

	@GetMapping("/admin/question/bank/edit/{questionBankId}")
	public String editQuestionBankPage(@PathVariable("questionBankId") @Valid Long questionBankId, ModelMap modelMap,
			Locale locale) throws ResourceNotFoundException {
		modelMap.addAttribute("questionBank", this.questionBankService.getQuestionBankById(questionBankId));
		modelMap.addAttribute("questionTypeList", this.questionTypeService.getQuestionTypeList());
		modelMap.addAttribute("questionBankList", this.questionBankService.getQuestionBankList());
		modelMap.addAttribute("edit", true);
		return "questionBank";
	}

	/*
	 * @GetMapping("/admin/question/bank/delete/{questionBankId}") public String
	 * deleteQuestionBankPage(@PathVariable("questionBankId") @Valid Long
	 * questionBankId, ModelMap modelMap, Locale locale) throws
	 * ResourceNotFoundException {
	 * this.questionBankService.deleteQuestionBank(questionBankId);
	 * modelMap.addAttribute("questionBankList",
	 * this.questionBankService.getQuestionBankList()); return
	 * "redirect:/admin/question/bank/new"; }
	 */

}
