/**
 * 
 */
package com.iceico.testportal.Controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.text.ParseException;
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
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iceico.testportal.Exceptions.ResourceNotFoundException;
import com.iceico.testportal.Model.Options;
import com.iceico.testportal.Model.QuestionBank;
import com.iceico.testportal.Model.QuestionType;
import com.iceico.testportal.Service.QuestionBankService;
import com.iceico.testportal.Service.QuestionTypeService;

/**
 * @author SAMEER KADGAYE
 * @version 0.1
 * 
 *          Created Date : 14/02/2020
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

	@GetMapping("/admin/question/bank")
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

	/* AJAX CALL FOR SEARCH BY TYPE */
	@GetMapping("/admin/question/bank/search")
	public String searchQuestions(@ModelAttribute("questionBank") @Valid QuestionBank questionBank, ModelMap modelMap,
			Locale locale) {

		modelMap.addAttribute("questionBankList", this.questionBankService.getQuestionBankList());
		modelMap.addAttribute("questionTypeList", this.questionTypeService.getQuestionTypeList());
		return "searchQuestions";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/question/bank/type", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public @ResponseBody JSONArray filterStudentListByType(@RequestParam("type") Long type)
			throws JsonProcessingException, ParseException, ResourceNotFoundException {
		System.out.println("Type ==========>>" + type);
		JSONArray questionBankArray = new JSONArray();
		QuestionType questionType = this.questionTypeService.getQuestionTypeById(type);
		for (QuestionBank questionBank : questionType.getQuestionBank()) {
			JSONObject questionBankObject = new JSONObject();
			questionBankObject.put("questionBankId", questionBank.getQuestionBankId());
			questionBankObject.put("question", questionBank.getQuestion());
			questionBankObject.put("marks", questionBank.getMarks());
			questionBankObject.put("subject", questionBank.getSubject());
			questionBankObject.put("quetionType", questionBank.getQuestionType().getType());
			questionBankArray.add(questionBankObject);
		}
		return questionBankArray;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/question/bank/type/subject", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public @ResponseBody JSONArray filterStudentListBySubject(@RequestParam("subject") String sub)
			throws JsonProcessingException, ParseException, ResourceNotFoundException {

		/* System.out.println("Subject =============>>" + subject); */
		JSONArray questionBankArray = new JSONArray();

		for (QuestionBank questionBank : questionBankService.questionBanksBySubjectsList(sub)) {
			JSONObject questionBankObject = new JSONObject();
			questionBankObject.put("questionBankId", questionBank.getQuestionBankId());
			questionBankObject.put("question", questionBank.getQuestion());
			questionBankObject.put("marks", questionBank.getMarks());
			questionBankObject.put("subject", questionBank.getSubject());
			questionBankObject.put("quetionType", questionBank.getQuestionType().getType());
			questionBankArray.add(questionBankObject);
		}

		return questionBankArray;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/question/bank/type/marks", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public @ResponseBody JSONArray filterStudentListByMarks(@RequestParam("marks") Integer marks)
			throws JsonProcessingException, ParseException, ResourceNotFoundException {
		/* System.out.println("marks =============>>" + marks); */
		JSONArray questionBankArray = new JSONArray();
		for (QuestionBank questionBank : questionBankService.questionBanksByMarkList(marks)) {
			JSONObject questionBankObject = new JSONObject();
			questionBankObject.put("questionBankId", questionBank.getQuestionBankId());
			questionBankObject.put("question", questionBank.getQuestion());
			questionBankObject.put("marks", questionBank.getMarks());
			questionBankObject.put("subject", questionBank.getSubject());
			questionBankObject.put("quetionType", questionBank.getQuestionType().getType());
			questionBankArray.add(questionBankObject);
		}
		return questionBankArray;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/question/bank/type/all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public @ResponseBody JSONArray filterStudentListByAll(@RequestParam("type") Long type,
			@RequestParam("marks") Integer marks, @RequestParam("subject") String subject)
			throws JsonProcessingException, ParseException, ResourceNotFoundException {

		System.out.println("marks =============>>" + marks);
		System.out.println("Subject =============>>" + subject);
		System.out.println("Type ==========>>" + type);
		QuestionType questionType = null;

		if (subject != null) {
			System.out.println("NOT NULL");

		}

		if (type != null) {
			questionType = this.questionTypeService.getQuestionTypeById(type);
		}
		JSONArray questionBankArray = new JSONArray();

		if (type == null && subject != "0" && marks != 0) {
			System.out.println("Subject & Marks ========>>>");
			for (QuestionBank questionBank : questionBankService.questionBanksBySubjetAndMarks(subject, marks)) {
				JSONObject questionBankObject = new JSONObject();
				questionBankObject.put("questionBankId", questionBank.getQuestionBankId());
				questionBankObject.put("subject", questionBank.getSubject());
				questionBankObject.put("quetionType", questionBank.getQuestionType().getType());
				questionBankObject.put("question", questionBank.getQuestion());
				questionBankObject.put("marks", questionBank.getMarks());
				questionBankArray.add(questionBankObject);
				return questionBankArray;
			}
		}

		if (type == null && subject == "0" && marks != null) {
			System.out.println("Marks only =====>>>");
		}

		return questionBankArray;

	}

}
