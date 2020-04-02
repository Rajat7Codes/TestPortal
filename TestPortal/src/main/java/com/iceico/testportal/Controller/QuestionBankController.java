
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
import com.iceico.testportal.Model.Subject;
import com.iceico.testportal.Service.QuestionBankService;
import com.iceico.testportal.Service.QuestionTypeService;
import com.iceico.testportal.Service.SubjectService;
import com.iceico.testportal.Service.UserService;

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

	@Autowired
	private SubjectService subjectService;
	@Autowired
	private UserService userService;

	/**
	 * MASTER ADMIN METHODS
	 *
	 */

	/* NEW QUESTION BANK */
	@SuppressWarnings("unchecked")
	@GetMapping("/admin/question/bank/new")
	public String getQuestionBank(ModelMap modelMap, Locale locale) {
		QuestionBank questionBank = new QuestionBank();

		modelMap.addAttribute("questionBank", questionBank);
		modelMap.addAttribute("questionTypeList", this.questionTypeService.getActiveQuestionType());
		modelMap.addAttribute("subjectList", this.subjectService.getActiveSubject());
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
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		return "questionBank";
	}

	/* SAVE QUESTION BANK */
	@RequestMapping(value = "/admin/question/bank/save", method = RequestMethod.POST)
	public String saveQuestionBank(@ModelAttribute("questionBank") @Valid QuestionBank questionBank,
			BindingResult bindingResult, @RequestParam("imageName") MultipartFile imageName,
			@RequestParam("data") String data, ModelMap modelMap, HttpServletRequest httpServletRequest) {
		JSONParser jsonParser = new JSONParser();
		List<Options> optionsList = new ArrayList<Options>();
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
		if (questionBank.getQuestionBankId() == null) {

			try {
				JSONArray jsonArray = (JSONArray) jsonParser.parse(data);
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject jsonObject = (JSONObject) jsonArray.get(i);
					Options options = new Options();
					Boolean answer = Boolean.parseBoolean((String) jsonObject.get("correctAnswer"));
					options.setOptionName(jsonObject.get("optionName").toString());
					options.setCorrectAnswer(answer);
					options.setQuestionBank(questionBank);
					optionsList.add(options);
				}
				questionBank.setOptions(optionsList);
				questionBank.setDepartmentName(userService.findBySSO(getPrincipal()).getDepartment().getDepartmentName());
				this.questionBankService.saveQuestionBank(questionBank);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		return "redirect:/admin/question/bank";
	}

	/* QUESTION BANK LIST */
	@GetMapping("/admin/question/bank")
	public String viewQuestionBankPage(ModelMap modelMap, Locale locale) {
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		modelMap.addAttribute("questionBankList", this.questionBankService.getQuestionBankList());
		return "questionBankView";
	}

	/* EDIT QUESTION BANK */
	@GetMapping("/admin/question/bank/edit/{questionBankId}")
	public String editQuestionBankPage(@PathVariable("questionBankId") @Valid Long questionBankId, ModelMap modelMap,
			Locale locale) throws ResourceNotFoundException {
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		modelMap.addAttribute("questionBank", this.questionBankService.getQuestionBankById(questionBankId));
		modelMap.addAttribute("questionTypeList", this.questionTypeService.getQuestionTypeList());
		modelMap.addAttribute("questionBankList", this.questionBankService.getQuestionBankList());
		modelMap.addAttribute("edit", true);
		return "questionBank";
	}

	/* QUESTION BANK SEARCH */
	@GetMapping("/admin/question/bank/search")
	public String searchQuestions(@ModelAttribute("questionBank") @Valid QuestionBank questionBank, ModelMap modelMap,
			Locale locale) {
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		modelMap.addAttribute("questionBankList", this.questionBankService.getQuestionBankList());
		modelMap.addAttribute("questionTypeList", this.questionTypeService.getQuestionTypeList());
		modelMap.addAttribute("subjectList", this.subjectService.getSubjectList());
		return "searchQuestions";
	}

	/**
	 * JAVA ADMIN METHODS
	 *
	 */

	/* NEW QUESTION BANK */
	@SuppressWarnings("unchecked")
	@GetMapping("/java/admin/question/bank/new")
	public String getQuestionBank_java(ModelMap modelMap, Locale locale) {
		QuestionBank questionBank = new QuestionBank();

		modelMap.addAttribute("questionBank", questionBank);
		modelMap.addAttribute("questionTypeList", this.questionTypeService.getActiveQuestionType());
		modelMap.addAttribute("subjectList", this.subjectService.getActiveSubject());
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
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		return "j_questionBank";
	}

	/* SAVE QUESTION BANK */
	@RequestMapping(value = "/java/admin/question/bank/save", method = RequestMethod.POST)
	public String saveQuestionBank_java(@ModelAttribute("questionBank") @Valid QuestionBank questionBank,
			BindingResult bindingResult, @RequestParam("imageName") MultipartFile imageName,
			@RequestParam("data") String data, ModelMap modelMap, HttpServletRequest httpServletRequest) {
		JSONParser jsonParser = new JSONParser();
		List<Options> optionsList = new ArrayList<Options>();
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
		if (questionBank.getQuestionBankId() == null) {

			try {
				JSONArray jsonArray = (JSONArray) jsonParser.parse(data);
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject jsonObject = (JSONObject) jsonArray.get(i);
					Options options = new Options();
					Boolean answer = Boolean.parseBoolean((String) jsonObject.get("correctAnswer"));
					options.setOptionName(jsonObject.get("optionName").toString());
					options.setCorrectAnswer(answer);
					options.setQuestionBank(questionBank);
					optionsList.add(options);
				}
				questionBank.setOptions(optionsList);
				questionBank.setDepartmentName(userService.findBySSO(getPrincipal()).getDepartment().getDepartmentName());
				this.questionBankService.saveQuestionBank(questionBank);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		return "redirect:/java/admin/question/bank";
	}

	/* QUESTION BANK LIST */
	@GetMapping("/java/admin/question/bank")
	public String viewQuestionBankPage_java(ModelMap modelMap, Locale locale) {
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		modelMap.addAttribute("questionBankList", this.questionBankService.getQuestionBankList());
		return "j_questionBankView";
	}

	/* EDIT QUESTION BANK */
	@GetMapping("/java/admin/question/bank/edit/{questionBankId}")
	public String editQuestionBankPage_java(@PathVariable("questionBankId") @Valid Long questionBankId,
			ModelMap modelMap, Locale locale) throws ResourceNotFoundException {
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		modelMap.addAttribute("questionBank", this.questionBankService.getQuestionBankById(questionBankId));
		modelMap.addAttribute("questionTypeList", this.questionTypeService.getQuestionTypeList());
		modelMap.addAttribute("questionBankList", this.questionBankService.getQuestionBankList());
		modelMap.addAttribute("edit", true);
		return "j_questionBank";
	}

	/* QUESTION BANK SEARCH */
	@GetMapping("/java/admin/question/bank/search")
	public String searchQuestions_java(@ModelAttribute("questionBank") @Valid QuestionBank questionBank,
			ModelMap modelMap, Locale locale) {
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		modelMap.addAttribute("questionBankList", this.questionBankService.getQuestionBankList());
		modelMap.addAttribute("questionTypeList", this.questionTypeService.getQuestionTypeList());
		modelMap.addAttribute("subjectList", this.subjectService.getSubjectList());
		return "j_searchQuestions";
	}

	/**
	 * WEB ADMIN METHODS
	 *
	 */

	/* NEW QUESTION BANK */
	@SuppressWarnings("unchecked")
	@GetMapping("/web/admin/question/bank/new")
	public String getQuestionBank_web(ModelMap modelMap, Locale locale) {
		QuestionBank questionBank = new QuestionBank();

		modelMap.addAttribute("questionBank", questionBank);
		modelMap.addAttribute("questionTypeList", this.questionTypeService.getActiveQuestionType());
		modelMap.addAttribute("subjectList", this.subjectService.getActiveSubject());
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
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		return "w_questionBank";
	}

	/* SAVE QUESTION BANK */
	@RequestMapping(value = "/web/admin/question/bank/save", method = RequestMethod.POST)
	public String saveQuestionBank_web(@ModelAttribute("questionBank") @Valid QuestionBank questionBank,
			BindingResult bindingResult, @RequestParam("imageName") MultipartFile imageName,
			@RequestParam("data") String data, ModelMap modelMap, HttpServletRequest httpServletRequest) {
		JSONParser jsonParser = new JSONParser();
		List<Options> optionsList = new ArrayList<Options>();
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
		if (questionBank.getQuestionBankId() == null) {

			try {
				JSONArray jsonArray = (JSONArray) jsonParser.parse(data);
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject jsonObject = (JSONObject) jsonArray.get(i);
					Options options = new Options();
					Boolean answer = Boolean.parseBoolean((String) jsonObject.get("correctAnswer"));
					options.setOptionName(jsonObject.get("optionName").toString());
					options.setCorrectAnswer(answer);
					options.setQuestionBank(questionBank);
					optionsList.add(options);
				}
				questionBank.setOptions(optionsList);
				questionBank.setDepartmentName(userService.findBySSO(getPrincipal()).getDepartment().getDepartmentName());
				this.questionBankService.saveQuestionBank(questionBank);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		return "redirect:/web/admin/question/bank";
	}

	/* QUESTION BANK LIST */
	@GetMapping("/web/admin/question/bank")
	public String viewQuestionBankPage_web(ModelMap modelMap, Locale locale) {
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		modelMap.addAttribute("questionBankList", this.questionBankService.getQuestionBankList());
		return "w_questionBankView";
	}

	/* EDIT QUESTION BANK */
	@GetMapping("/web/admin/question/bank/edit/{questionBankId}")
	public String editQuestionBankPage_web(@PathVariable("questionBankId") @Valid Long questionBankId,
			ModelMap modelMap, Locale locale) throws ResourceNotFoundException {
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		modelMap.addAttribute("questionBank", this.questionBankService.getQuestionBankById(questionBankId));
		modelMap.addAttribute("questionTypeList", this.questionTypeService.getQuestionTypeList());
		modelMap.addAttribute("questionBankList", this.questionBankService.getQuestionBankList());
		modelMap.addAttribute("edit", true);
		return "w_questionBank";
	}

	/* QUESTION BANK SEARCH */
	@GetMapping("/web/admin/question/bank/search")
	public String searchQuestions_web(@ModelAttribute("questionBank") @Valid QuestionBank questionBank,
			ModelMap modelMap, Locale locale) {
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		modelMap.addAttribute("questionBankList", this.questionBankService.getQuestionBankList());
		modelMap.addAttribute("questionTypeList", this.questionTypeService.getQuestionTypeList());
		modelMap.addAttribute("subjectList", this.subjectService.getSubjectList());
		return "w_searchQuestions";
	}

	/**
	 * ALL AJAX CALL FOR ALL ADMINS
	 * 
	 */

	/* AJAX CALL FOR QUESTION BANK BY TYPE */
	@SuppressWarnings({ "unchecked", "deprecation" })
	@RequestMapping(value = "/question/bank/type/all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public @ResponseBody JSONArray filterStudentListByAll(@RequestParam("type") Long type,
			@RequestParam("marks") Integer marks, @RequestParam("subject") Long subjectType)
			throws JsonProcessingException, ParseException, ResourceNotFoundException {

		QuestionType questionType = null;

		Subject subject = null;

		if (subjectType != null) {
			subject = this.subjectService.getSubjectById(subjectType);
		}

		if (type != null) {
			questionType = this.questionTypeService.getQuestionTypeById(type);
		}
		JSONArray questionBankArray = new JSONArray();

		if (type == null & subject != null & marks != 0) {

			for (QuestionBank questionBank : questionBankService.questionBanksBySubjetAndMarks(subject, marks)) {
				JSONObject questionBankObject = new JSONObject();
				questionBankObject.put("questionBankId", questionBank.getQuestionBankId());
				questionBankObject.put("subject", questionBank.getSubject().getSubjectName());
				questionBankObject.put("quetionType", questionBank.getQuestionType().getType());
				questionBankObject.put("question", questionBank.getQuestion());
				questionBankObject.put("marks", questionBank.getMarks());
				questionBankArray.add(questionBankObject);
			}
			return questionBankArray;
		}

		if (type == null & subject == null & marks != 0 || type == null & subject == null & marks == 0) {
			for (QuestionBank questionBank : questionBankService.questionBankListByMarks(marks)) {
				JSONObject questionBankObject = new JSONObject();
				questionBankObject.put("questionBankId", questionBank.getQuestionBankId());
				questionBankObject.put("subject", questionBank.getSubject().getSubjectName());
				questionBankObject.put("quetionType", questionBank.getQuestionType().getType());
				questionBankObject.put("question", questionBank.getQuestion());
				questionBankObject.put("marks", questionBank.getMarks());
				questionBankArray.add(questionBankObject);
			}
			return questionBankArray;
		}

		if (type == null & subject != null & marks == 0) {
			for (QuestionBank questionBank : questionBankService.questionBanksBySubjectsList(subject)) {
				JSONObject questionBankObject = new JSONObject();
				questionBankObject.put("questionBankId", questionBank.getQuestionBankId());
				questionBankObject.put("subject", questionBank.getSubject().getSubjectName());
				questionBankObject.put("quetionType", questionBank.getQuestionType().getType());
				questionBankObject.put("question", questionBank.getQuestion());
				questionBankObject.put("marks", questionBank.getMarks());
				questionBankArray.add(questionBankObject);
			}
			return questionBankArray;
		}

		if (type != null & subject == null & marks == 0) {
			for (QuestionBank questionBank : questionBankService.questionBanksByQuestionTypeList(questionType)) {
				JSONObject questionBankObject = new JSONObject();
				questionBankObject.put("questionBankId", questionBank.getQuestionBankId());
				questionBankObject.put("subject", questionBank.getSubject().getSubjectName());
				questionBankObject.put("quetionType", questionBank.getQuestionType().getType());
				questionBankObject.put("question", questionBank.getQuestion());
				questionBankObject.put("marks", questionBank.getMarks());
				questionBankArray.add(questionBankObject);
			}
			return questionBankArray;
		}

		if (type != null & subject != null & marks != 0) {
			for (QuestionBank questionBank : questionBankService.questionBanksByTypeSubjectMarksList(questionType,
					marks, subject)) {
				JSONObject questionBankObject = new JSONObject();
				questionBankObject.put("questionBankId", questionBank.getQuestionBankId());
				questionBankObject.put("subject", questionBank.getSubject().getSubjectName());
				questionBankObject.put("quetionType", questionBank.getQuestionType().getType());
				questionBankObject.put("question", questionBank.getQuestion());
				questionBankObject.put("marks", questionBank.getMarks());
				questionBankArray.add(questionBankObject);
			}
			return questionBankArray;
		}

		if (type != null & subject == null & marks != 0) {
			for (QuestionBank questionBank : questionBankService.questionBanksByTypeAndMarks(questionType, marks)) {
				JSONObject questionBankObject = new JSONObject();
				questionBankObject.put("questionBankId", questionBank.getQuestionBankId());
				questionBankObject.put("subject", questionBank.getSubject().getSubjectName());
				questionBankObject.put("quetionType", questionBank.getQuestionType().getType());
				questionBankObject.put("question", questionBank.getQuestion());
				questionBankObject.put("marks", questionBank.getMarks());
				questionBankArray.add(questionBankObject);
			}
			return questionBankArray;
		}

		if (type != null & subject != null & marks == 0) {
			for (QuestionBank questionBank : questionBankService.questionBanksByTypeAndSubject(questionType, subject)) {
				JSONObject questionBankObject = new JSONObject();
				questionBankObject.put("questionBankId", questionBank.getQuestionBankId());
				questionBankObject.put("subject", questionBank.getSubject().getSubjectName());
				questionBankObject.put("quetionType", questionBank.getQuestionType().getType());
				questionBankObject.put("question", questionBank.getQuestion());
				questionBankObject.put("marks", questionBank.getMarks());
				questionBankArray.add(questionBankObject);
			}
			return questionBankArray;
		}

		return questionBankArray;
	}

	/* AJAX CALL FOR GET GUESTION BANK BY SUBJECT */
	@SuppressWarnings({ "deprecation", "unchecked" })
	@RequestMapping(value = "/add/test/filter/subject", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public @ResponseBody JSONArray filterQuestionListBySubject(@RequestParam("subjectID") Long subjectId)
			throws JsonProcessingException, ParseException, ResourceNotFoundException {

		Subject subject = this.subjectService.getSubjectById(subjectId);
		JSONArray questionArray = new JSONArray();

		for (int i = 0; i < subject.getQuestionBank().size(); i++) {
			JSONObject queObject = new JSONObject();
			queObject.put("questionId", subject.getQuestionBank().get(i).getQuestionBankId());
			queObject.put("question", subject.getQuestionBank().get(i).getQuestion());
			queObject.put("questionType", subject.getQuestionBank().get(i).getQuestionType().getType());
			queObject.put("marks", subject.getQuestionBank().get(i).getMarks());
			questionArray.add(queObject);
		}

		return questionArray;
	}

	/**
	 * 
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
