
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
 * @author RAJAT PATIL
 * @version 0.1
 * 
 *          Created Date : 04/04/2020
 *
 */
@Controller
public class DriveQuestionBankController {

	/**
	 * 
	 */
	public DriveQuestionBankController() {

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
	 * DRIVE ADMIN METHODS
	 *
	 */

	/* NEW QUESTION BANK */
	@SuppressWarnings("unchecked")
	@GetMapping("/drive/admin/question/bank/new")
	public String getQuestionBank_drive(ModelMap modelMap, Locale locale) {
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
		return "d_questionBank";
	}

	/* SAVE QUESTION BANK */
	@RequestMapping(value = "/drive/admin/question/bank/save", method = RequestMethod.POST)
	public String saveQuestionBank_drive(@ModelAttribute("questionBank") @Valid QuestionBank questionBank,
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
		return "redirect:/drive/admin/question/bank";
	}

	/* QUESTION BANK LIST */
	@GetMapping("/drive/admin/question/bank")
	public String viewQuestionBankPage_drive(ModelMap modelMap, Locale locale) {
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		modelMap.addAttribute("questionBankList", this.questionBankService.getQuestionBankList());
		return "d_questionBankView";
	}

	/* EDIT QUESTION BANK */
	@GetMapping("/drive/admin/question/bank/edit/{questionBankId}")
	public String editQuestionBankPage_drive(@PathVariable("questionBankId") @Valid Long questionBankId,
			ModelMap modelMap, Locale locale) throws ResourceNotFoundException {
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		modelMap.addAttribute("questionBank", this.questionBankService.getQuestionBankById(questionBankId));
		modelMap.addAttribute("questionTypeList", this.questionTypeService.getQuestionTypeList());
		modelMap.addAttribute("questionBankList", this.questionBankService.getQuestionBankList());
		modelMap.addAttribute("edit", true);
		return "d_questionBank";
	}

	/* QUESTION BANK SEARCH */
	@GetMapping("/drive/admin/question/bank/search")
	public String searchQuestions_drive(@ModelAttribute("questionBank") @Valid QuestionBank questionBank,
			ModelMap modelMap, Locale locale) {
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		modelMap.addAttribute("questionBankList", this.questionBankService.getQuestionBankList());
		modelMap.addAttribute("questionTypeList", this.questionTypeService.getQuestionTypeList());
		modelMap.addAttribute("subjectList", this.subjectService.getSubjectList());
		return "d_searchQuestions";
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
