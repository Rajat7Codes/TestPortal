/**
 * 
 */
package com.iceico.testportal.Controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
		modelMap.addAttribute("departmentList", this.departmentService.getDepartmentList());
		return "register";
	}

	/* USER REGISTRATION SAVE */
	/*
	 * @PostMapping(value = { "/register/save" }) public String
	 * saveStudentRegistration(@ModelAttribute("user") @Valid User user,
	 * BindingResult bindingResult, ModelMap modelMap, Locale locale) { UserProfile
	 * profile = userProfileService.findByType("STUDENT"); Set<UserProfile> role =
	 * new HashSet<>(); role.add(profile); user.setUserProfiles(role);
	 * this.userService.saveUser(user); return "redirect:/login"; }
	 */

	/* USER DASHBOARD */
	@GetMapping(value = { "/student/dashboard" })
	public String studentDashboard(ModelMap modelMap, Locale locale) {
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		return "studentDashboard";
	}

	/* USER REGISTRATION SAVE */
	@PostMapping(value = { "/register/generate/otp" })
	public String generateOTP(@RequestParam("data") String data, ModelMap modelMap, Locale locale)
			throws ParseException {
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
		mailSender.setUsername("iceico.testportal@gmail.com");
		mailSender.setPassword("testportal@2020");

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
			HttpServletRequest httpServletRequest)
			throws ParseException, NumberFormatException, ResourceNotFoundException {

		String character = sdata.substring(sdata.length() - 1, sdata.length());
		if (character.equals(",")) {
			sdata = sdata.substring(0, sdata.length() - 1);
		}

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(sdata);
		String emailOtp = jsonObject.get("emailOtp").toString();

		String jobj = jsonObject.get("data").toString();
		JSONObject data = (JSONObject) jsonParser.parse(jobj);

		UserProfile profile = null;

		if (emailOtp.equalsIgnoreCase(verifyEmailOtp)) {

			Department department = this.departmentService
					.getDepartmentById(Long.parseLong(data.get("department").toString()));
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

	@PostMapping("/register/resend/otp")
	public String resendOtp(@RequestParam("finalJson") String data, ModelMap modelMap, Locale locale)
			throws ParseException {

		String emailOtp = this.otpServiceINF.generateOTP();
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
		String toEmail = jsonObject.get("emailId").toString();
		String subject = "[ICEICO TEST PORTAL OTP]";
		String emailMessage = "Hello, \n" + "Your One time Passowrd For Registering On ICEICO Test Portal " + " " + "is " + emailOtp + "";

		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("iceico.testportal@gmail.com");
		mailSender.setPassword("testportal@2020");

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
				message.setTo(toEmail);
				message.setSubject(subject);
				message.setText(emailMessage);
			}
		};
		mailSender.send(preparator);

		modelMap.addAttribute("data", data);
		modelMap.addAttribute("emailOtp", emailOtp);
		return "otpVerify";
	}

	/* STUDENT PROFILE */
	@GetMapping(value = { "/student/profile" })
	public String studentProfile(ModelMap modelMap, Locale locale) {
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		return "studentProfile";
	}

	/* STUDENT PROFILE */
	@GetMapping(value = { "/student/profile/update" })
	public String updateStudentProfile(ModelMap modelMap, Locale locale) {
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		return "updateStudProfile";
	}

	/* STUDENT PROFILE */
	@PostMapping(value = { "/student/profile/save" })
	public String saveStudentProfile(@RequestParam("jsonData") String jsonData,
			@RequestParam("fileName") MultipartFile fileName, HttpServletRequest httpServletRequest, ModelMap modelMap,
			Locale locale) throws ParseException {

		String uploadFolder = httpServletRequest.getServletContext().getRealPath("/uploaded");

		File directory = new File(uploadFolder);
		if (!directory.exists()) {
			directory.mkdir();
			// If you require it to make the entire directory path including parents,
			// use directory.mkdirs(); here instead.
		}

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonData);

		Integer id = Integer.parseInt(jsonObject.get("id").toString());

		User user = userService.findBySSO(jsonObject.get("ssoId").toString());

		if (fileName.isEmpty()) {
			user.setFileName(user.getFileName());
			user.setContentType(user.getContentType());
			user.setFilePath(user.getFilePath());
		}

		if (!fileName.isEmpty()) {
			try {
				user.setFileName(fileName.getOriginalFilename());
				user.setContentType(fileName.getContentType());
				BufferedImage src = ImageIO.read(new ByteArrayInputStream(fileName.getBytes()));
				File destination = new File(uploadFolder + "/" + fileName.getOriginalFilename());
				ImageIO.write(src, "jpg", destination);
				user.setFilePath(uploadFolder + "/" + fileName.getOriginalFilename());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (id != null) {
			user.setSsoId(jsonObject.get("ssoId").toString());
			user.setPassword(jsonObject.get("password").toString());
			user.setFirstName(jsonObject.get("firstName").toString());
			user.setLastName(jsonObject.get("lastName").toString());
			user.setEmail(jsonObject.get("email").toString());
			user.setPosition(jsonObject.get("position").toString());
			user.setDob(jsonObject.get("dob").toString());
			user.setGender(jsonObject.get("gender").toString());
			user.setDescription(jsonObject.get("description").toString());
			user.setMobileNumber(jsonObject.get("mobileNumber").toString());
			userService.updateUser(user);
		}
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		return "redirect:/student/profile";
	}

	@RequestMapping(value = "/getImage/{imagePath}")
	@ResponseBody
	public byte[] getImage(@PathVariable String imagePath, HttpServletRequest request) throws IOException {

		String rpath = request.getServletContext().getRealPath("/uploaded") + "/" + imagePath + ".jpg";
		Path path = Paths.get(rpath);
		byte[] data = Files.readAllBytes(path);
		return data;
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
