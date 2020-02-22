package com.iceico.testportal.Controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.json.simple.parser.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.iceico.testportal.Model.User;
import com.iceico.testportal.Model.UserProfile;
import com.iceico.testportal.Service.EMailService;
import com.iceico.testportal.Service.OtpService;
import com.iceico.testportal.Service.UserProfileService;
import com.iceico.testportal.Service.UserService;

/**
 * @author LEKHA BHANGE
 * @version 0.1
 * 
 *          Created Date : 11/02/2020
 *
 */

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
	private OtpService otpService;

	@Autowired
	private EMailService emailService;

	private Integer otpSent = null;
	private String changePassUserSSO = null;

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

	@RequestMapping(value = { "/admin/user/verify/mail" }, method = RequestMethod.POST)
	public String verifyUserMail(@RequestParam("emailId") String emailId, @RequestParam("fname") String fName,
			@RequestParam("lname") String lName, @RequestParam("mobile") String mobile,
			@RequestParam("password") String password, @RequestParam("department") String department,
			@RequestParam("position") String position, @RequestParam("image") String image, ModelMap model) {

		String emailOtp = this.otpService.generateOTP();
		String toEmail = emailId;
		String subject = "ICEICO Test Portal OTP";

		String emailMessage = "Hello Student, \n" + " Your One time Passowrd For Registering On ICEICO Test "
				+ "Portal is" + " " + emailOtp + "";

		emailService.sendOtpMessage(toEmail, subject, emailMessage);

		User user = new User();
		user.setEmail(emailId);
		user.setFirstName(fName);
		user.setLastName(lName);
		user.setPassword(password);
		user.setMobileNumber(mobile);

		model.addAttribute("firstName", fName);
		model.addAttribute("lastName", lName);
		model.addAttribute("email", emailId);
		model.addAttribute("password", password);
		model.addAttribute("mobile", mobile);
		model.addAttribute("edit", false);
		model.addAttribute("otp", emailOtp);
		model.addAttribute("userList", userService.findAllUsers());
		model.addAttribute("user", getPrincipal());
		return "verifyMail";
	}

	@SuppressWarnings({ "deprecation" })
	@RequestMapping(value = {
			"/register/user" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public String registerUser(@RequestParam("fname") String fName, @RequestParam("lname") String lName,
			@RequestParam("mob") String mobile, @RequestParam("pass") String password,
			@RequestParam("email") String emailId, ModelMap modelMap) throws ParseException {

		User user = new User();
		user.setEmail(emailId);
		user.setFirstName(fName);
		user.setLastName(lName);
		user.setMobileNumber(mobile);
		user.setPassword(password);
		user.setSsoId(user.getFirstName() + " " + user.getLastName());

		UserProfile profile = this.userProfileService.findByType("STUDENT");
		Set<UserProfile> role = new HashSet<UserProfile>();
		role.add(profile);
		user.setUserProfiles(role);

		this.userService.saveUser(user);
		modelMap.addAttribute("user", getPrincipal());
		return null;
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

	@RequestMapping(value = { "/change/password" }, method = RequestMethod.POST)
	public String changePassword(@ModelAttribute("otp") Integer otpRecieved,
			@ModelAttribute("password") String password, ModelMap modelMap) {
		if ((otpRecieved + "").equals(otpSent + "")) {
			User user = this.userService.findBySSO(this.changePassUserSSO);
			user.setPassword(password);
			this.userService.saveUser(user);
			return "welcome";
		} else {
			modelMap.addAttribute("alertMsg", "Please enter correct OTP");
			return "forgotPassword";
		}
	}

	@RequestMapping(value = { "/forgot/password" }, method = RequestMethod.POST)
	public String forgetPassword(@ModelAttribute("userName") String userName, @ModelAttribute("email") String email,
			@ModelAttribute("mobileNumber") String mobileNumber, ModelMap modelMap) {

		User user = this.userService.findBySSO(userName);

		if (user == null) {
			modelMap.addAttribute("isOtp", false);
			modelMap.addAttribute("alertMsg", "Username Not Available");
		} else if (email.equals(user.getEmail()) && mobileNumber.equals(user.getMobileNumber())) {
			String emailOtp = this.otpService.generateOTP();
			String subject = "ICEICO Test Portal OTP";
			String emailMessage = "Hello Student, \n" + " Your One time Passowrd for changing password On ICEICO Test "
					+ "Portal is" + " " + emailOtp;
			emailService.sendOtpMessage(email, subject, emailMessage);
			this.otpSent = Integer.parseInt(emailOtp);
			this.changePassUserSSO = userName;
			modelMap.addAttribute("alertMsg", "Please Check Your Mail For OTP");
			modelMap.addAttribute("otp", emailOtp);
			modelMap.addAttribute("isOtp", true);
		} else {
			modelMap.addAttribute("isOtp", false);
			modelMap.addAttribute("alertMsg", "Please Enter Proper Details");
		}

		return "forgotPassword";
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