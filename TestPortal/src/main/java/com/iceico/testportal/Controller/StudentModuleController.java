/**
 * 
 */
package com.iceico.testportal.Controller;

import java.util.HashSet;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iceico.testportal.Exceptions.ResourceNotFoundException;
import com.iceico.testportal.Model.Department;
import com.iceico.testportal.Model.User;
import com.iceico.testportal.Model.UserProfile;
import com.iceico.testportal.Service.DepartmentService;
import com.iceico.testportal.Service.OtpService;
import com.iceico.testportal.Service.UserProfileService;
import com.iceico.testportal.Service.UserService;

/**
 * @author iceico
 *
 */
@Controller
public class StudentModuleController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserProfileService userProfileService;
	
	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private OtpService otpServiceINF;

	/* USER REGISTER */
	@GetMapping(value = { "/register" })
	public String registerStudent(ModelMap modelMap, Locale locale) {
		modelMap.addAttribute("user", new User());
		return "register";
	}

	/* USER REGISTRATION SAVE */
	@PostMapping(value = { "/register/save" })
	public String saveStudentRegistration(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
			ModelMap modelMap, Locale locale) {
		UserProfile profile = userProfileService.findByType("STUDENT");
		Set<UserProfile> role = new HashSet<>();
		role.add(profile);
		user.setUserProfiles(role);
		this.userService.saveUser(user);
		return "redirect:/login";
	}

	/* USER DASHBOARD */
	@GetMapping(value = { "/student/dashboard" })
	public String studentDashboard(ModelMap modelMap, Locale locale) {
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
//		modelMap.addAttribute("user", this.getPrincipal());
		return "studentDashboard";
	}

	/* USER REGISTRATION SAVE */
	@PostMapping(value = { "/register/generate/otp" })
	public String generateOTP(@RequestParam("data") String data, ModelMap modelMap, Locale locale)
			throws ParseException {
		System.out.println("data===" + data);
		String emailOtp = this.otpServiceINF.generateOTP();

		JSONParser jsonParser = new JSONParser();

		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
		String emailId = jsonObject.get("emailId").toString();

		String subject = "[ICEICO TEST PORTAL OTP]";

		String emailMessage = "Hello, \n" + "Your One time Passowrd For Registering On ICEICO TEST PORTAL " + " " + "is"
				+ " " + emailOtp + "";

		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("lekhabhange.iceico@gmail.com");
		mailSender.setPassword("lekha@iceico");

		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.starttls.enable", "true");
		javaMailProperties.put("mail.smtp.auth", "true");
		javaMailProperties.put("mail.transport.protocol", "smtp");
		javaMailProperties.put("mail.debug", "true");

		mailSender.setJavaMailProperties(javaMailProperties);

		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				message.setTo(emailId);
				message.setSubject(subject);
				message.setText(emailMessage);
			}
		};
		mailSender.send(preparator);

		modelMap.addAttribute("data", jsonObject);
		modelMap.addAttribute("emailOtp", emailOtp);

		return "otpVerify";
	}

	@PostMapping("/register/verify/otp")
	public String verifyOtpSave(@RequestParam("finalJson") String sdata,
			@RequestParam("verifyEmailOtp") String verifyEmailOtp, ModelMap modelMap, Locale locale,
			HttpServletRequest httpServletRequest) throws ParseException, NumberFormatException, ResourceNotFoundException {
		System.out.println("sdata========" + sdata);

		String character = sdata.substring(sdata.length() - 1, sdata.length());
		if (character.equals(",")) {
			sdata = sdata.substring(0, sdata.length() - 1);
		}

		System.out.println("sdata========" + sdata);

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(sdata);
		String emailOtp = jsonObject.get("emailOtp").toString();

		String jobj = jsonObject.get("data").toString();
		System.out.println("jobj========" + jobj);
		JSONObject data = (JSONObject) jsonParser.parse(jobj);

		UserProfile profile = null;

		if (emailOtp.equalsIgnoreCase(verifyEmailOtp)) {

			Department department = this.departmentService.getDepartmentById( Long.parseLong( data.get("department")+""));
			profile = userProfileService.findByType(department.getDepartmentName());
			profile.setType(department.getDepartmentName());

			Set<UserProfile> role = new HashSet<>();
			role.add(profile);

			User user = new User();
			user.setUserProfiles(role);
			user.setSsoId(data.get("username").toString());
			user.setFirstName(data.get("fname").toString());
			user.setLastName(data.get("lname").toString());
			user.setDepartment(department);
			user.setEmail(data.get("emailId").toString());
			user.setPosition(data.get("position").toString());
			user.setMobileNumber(data.get("mobile").toString());
			user.setGender(data.get("gender").toString());
			user.setPassword(data.get("password").toString());

			this.userService.saveUser(user);
			return "redirect:/login";
		} else {
			modelMap.addAttribute("emailOtp", emailOtp);
			modelMap.addAttribute("data", data.toJSONString());
			modelMap.addAttribute("verificationFailed", true);
			return "otpVerify";
		}
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
