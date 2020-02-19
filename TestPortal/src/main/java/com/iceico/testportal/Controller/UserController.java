package com.iceico.testportal.Controller;

import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.iceico.testportal.Model.User;
import com.iceico.testportal.Model.UserProfile;
import com.iceico.testportal.Service.OtpService;
import com.iceico.testportal.Service.UserProfileService;
import com.iceico.testportal.Service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserProfileService userProfileService;
	@Autowired
	private PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
	@Autowired
	private AuthenticationTrustResolver authenticationTrustResolver;
	@Autowired
	private OtpService otpServiceINF;
	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/admin/user" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		model.addAttribute("newUser", user);
		model.addAttribute("edit", false);
		model.addAttribute("userList", userService.findAllUsers());
		model.addAttribute("user", getPrincipal());
		return "register";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/admin/user" }, method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("newUser") @Valid User newUser, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "register";
		}

		/*
		 * Preferred way to achieve uniqueness of field [sso] should be implementing
		 * custom @Unique annotation and applying it on field [sso] of Model class
		 * [User].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill
		 * custom errors outside the validation framework as well while still using
		 * internationalized messages.
		 * 
		 */
		/*
		 * if (!userService.isUserSSOUnique(newUser.getId(), newUser.getSsoId())) {
		 * FieldError ssoError = new FieldError("user", "ssoId", "non.unique.ssoId");
		 * result.addError(ssoError); return "register"; }
		 */

		/*
		 * System.out.println("New User Values ==========================" + newUser);
		 * 
		 * System.out.println("Controller User Id==============" + newUser.getId());
		 * 
		 * if (newUser.getId() == null) {
		 * System.out.println("Controller if ==============" + newUser.getId());
		 * userService.saveUser(newUser); } else {
		 * System.out.println("Controller else ==============" + newUser.getId());
		 * System.out.println("In Controller ===================" + newUser);
		 * userService.updateUser(newUser); }
		 */

		userService.saveUser(newUser);

		return "redirect:/admin/user";
	}

	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/admin/user/update/{ssoId}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String ssoId, ModelMap model) {
		User newUser = userService.findBySSO(ssoId);
		model.addAttribute("newUser", newUser);
		model.addAttribute("userList", userService.findAllUsers());
		model.addAttribute("edit", true);
		model.addAttribute("user", getPrincipal());
		return "register";
	}

	@RequestMapping(value = { "/admin/user/update/{ssoId}" }, method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result, ModelMap model, @PathVariable String ssoId) {

		if (result.hasErrors()) {
			return "register";
		}

		userService.updateUser(user);
		return "redirect:/admin/user";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * updating user in database. It also validates the user input
	 */
	/*
	 * @RequestMapping(value = { "/edit-user-{ssoId}" }, method =
	 * RequestMethod.POST) public String updateUser(@Valid User user, BindingResult
	 * result, ModelMap model, @PathVariable String ssoId) {
	 * 
	 * if (result.hasErrors()) { return "register"; }
	 * 
	 * 
	 * //Uncomment below 'if block' if you WANT TO ALLOW UPDATING SSO_ID in UI which
	 * is a unique key to a User. if(!userService.isUserSSOUnique(user.getId(),
	 * user.getSsoId())){ FieldError ssoError =new
	 * FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new
	 * String[]{user.getSsoId()}, Locale.getDefault())); result.addError(ssoError);
	 * return "registration"; }
	 * 
	 * 
	 * userService.updateUser(user);
	 * 
	 * model.addAttribute("success", "User " + user.getFirstName() + " " +
	 * user.getLastName() + " updated successfully");
	 * model.addAttribute("loggedinuser", getPrincipal()); return
	 * "registrationsuccess"; }
	 */
	/**
	 * This method will delete an user by it's SSOID value.
	 */
	@RequestMapping(value = { "/admin/user/delete/{ssoId}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String ssoId) {
		userService.deleteUserBySSO(ssoId);
		return "redirect:/admin/user";
	}

	/**
	 * This method will provide UserProfile list to views
	 */
	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() {
		return userProfileService.findAll();
	}

	/**
	 * This method handles Access-Denied redirect.
	 */
	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "accessDenied";
	}

	/**
	 * This method handles login GET requests. If users is already logged-in and
	 * tries to goto login page again, will be redirected to list page.
	 */

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {

		/*
		 * if (isCurrentAuthenticationAnonymous()) { return "welcome"; }
		 */

		return "welcome";
	}

	/**
	 * This method handles logout requests. Toggle the handlers if you are
	 * RememberMe functionality is useless in your app.
	 */

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			// new SecurityContextLogoutHandler().logout(request, response, auth);
			persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/login?logout";
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

	private User getUserInfo() {
		String userName = null;
		userName = this.getPrincipal();
		User user = this.userService.findBySSO(userName);
		return user;
	}

	@RequestMapping(value = { "/user/account/setting" }, method = RequestMethod.GET)
	public String updatePerticularUser(ModelMap model) {
		User newUser = this.getUserInfo();
		model.addAttribute("newUser", newUser);
		model.addAttribute("userList", userService.findAllUsers());
		model.addAttribute("edit", true);
		model.addAttribute("user", getPrincipal());
		return "uregister";
	}

	@RequestMapping(value = { "/user/account/setting" }, method = RequestMethod.POST)
	public String updatePerticularUser(@ModelAttribute("newUser") @Valid User user, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "register";
		}

		userService.updateUser(user);
		model.addAttribute("updated", "Updated Successfully");
		model.addAttribute("edit", true);
		model.addAttribute("user", getPrincipal());
		return "uregister";
	}

	@RequestMapping(value = { "/forgot/password" }, method = RequestMethod.GET)
	public String forgotPassword(ModelMap modelMap) {
		return "forgotPassword";
	}

	@PostMapping("/forgot/password/generate/otp")
	public String generateOtp(@RequestParam("data") String data, ModelMap modelMap, Locale locale)
			throws ParseException {

		String emailOtp = this.otpServiceINF.generateOTP();
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
		String toEmail = jsonObject.get("email").toString();
		String mobileNumber = jsonObject.get("mobileNumber").toString();
		String userName = jsonObject.get("userName").toString();

		User user = userService.findBySSO(userName);

		if (user == null) {
			modelMap.addAttribute("userError", "Username Not Available");
			return "forgotPassword";
		} else if (toEmail.equals(user.getEmail()) && mobileNumber.equals(user.getMobileNumber())) {
			String subject = "[ICEICO TEST PORTAL OTP]";

			String emailMessage = "Hello, \n" + "Your One time Passowrd For Registering On ICEICO Test Portal " + " "
					+ "is" + " " + emailOtp + "";

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
					message.setTo(toEmail);
					message.setSubject(subject);
					message.setText(emailMessage);
				}
			};
			mailSender.send(preparator);

			modelMap.addAttribute("data", jsonObject);
			modelMap.addAttribute("emailOtp", emailOtp);
			return "otpVerify";

		} else {
			modelMap.addAttribute("detailsError", "Please Enter Proper Details");
			return "forgotPassword";
		}
	}

	@PostMapping("/forgot/password/resend/otp")
	public String resendOtp(@RequestParam("finalJson") String data, ModelMap modelMap, Locale locale)
			throws ParseException {

		String emailOtp = this.otpServiceINF.generateOTP();
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
		String toEmail = jsonObject.get("email").toString();
		String subject = "[ICEICO TEST PORTAL OTP]";

		String emailMessage = "Hello, \n" + "Your One time Passowrd For Registering On ICEICO Test Portal " + " " + "is"
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

	@PostMapping("/forgot/password/verify/otp")
	public String verifyOtpSave(@RequestParam("finalJson") String sdata,
			@RequestParam("verifyEmailOtp") String verifyEmailOtp, ModelMap modelMap, Locale locale)
			throws ParseException {
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(sdata);
		String emailOtp = jsonObject.get("emailOtp").toString();
		JSONObject data = (JSONObject) jsonParser.parse(jsonObject.get("data").toString());

		if (emailOtp.equalsIgnoreCase(verifyEmailOtp)) {

			String userName = data.get("userName").toString();
			String password = data.get("password").toString();

			User user = userService.findBySSO(userName);

			user.setPassword(passwordEncoder.encode(password));
			userService.updateUser(user);
			return "redirect:/login";
		} else {
			modelMap.addAttribute("emailOtp", emailOtp);
			modelMap.addAttribute("data", data.toJSONString());
			modelMap.addAttribute("verificationFailed", true);
			return "otpVerify";
		}
	}

	/**
	 * This method returns true if users is already authenticated [logged-in], else
	 * false.
	 */
	@SuppressWarnings("unused")
	private boolean isCurrentAuthenticationAnonymous() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authenticationTrustResolver.isAnonymous(authentication);
	}

}