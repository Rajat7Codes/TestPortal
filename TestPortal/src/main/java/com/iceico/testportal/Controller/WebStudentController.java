/**
 * 
 */
package com.iceico.testportal.Controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.iceico.testportal.Exceptions.ResourceNotFoundException;
import com.iceico.testportal.Model.User;
import com.iceico.testportal.Service.EMailService;
import com.iceico.testportal.Service.UserService;

/**
 * @author LEKHA BHANGE
 * @version 0.1
 * 
 *          Created Date : 22/02/2020
 *
 */
@Controller
public class WebStudentController {

	@Autowired
	private UserService userService;

	@Autowired
	private EMailService emailService;

	private String passwordToken = null;

	private String tempPass = null;

	/**
	 * 
	 */
	public WebStudentController() {

	}

	/* WEB STUDENT PROFILE */
	@GetMapping("/web/student/profile")
	public String displayStudentInformation(ModelMap modelMap, Locale locale) {
		modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
		return "webStudProfile";
	}

	/* WEN STUDENT PROFILE SAVE */
	@PostMapping("/web/student/profile/save")
	public String saveWebStudent(@RequestParam("jsonData") String jsonData,
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

		modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
		return "redirect:/web/student/profile";
	}

	/* WEB STUDENT PROFILE UPDATE */
	@GetMapping("/web/student/profile/update")
	public String editStudent(ModelMap modelMap, Locale locale) throws ResourceNotFoundException {
		modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
		return "updateWebStudProfile";
	}

	/* SEND VERIFICATION LINK FOR CHANGE PASSWORD */
	@GetMapping("/web/student/profile/send/token")
	public String sendToken(@RequestParam("username") String userName, @RequestParam("mobile") String mobNo,
			@RequestParam("mailId") String mailId, @RequestParam("password") String password, ModelMap modelMap,
			Locale locale) throws ResourceNotFoundException {

		User user = this.userService.findBySSO(this.getPrincipal());

		if (user.getSsoId().equals(userName)) {
			if (user.getMobileNumber().equals(mobNo) && user.getEmail().equals(mailId)) {

				// Generate Random Alphanumberic Token
				String charString = "abcdefghijklmnopqrstuvwxyz0123456789";
				String randAlphaNum = "";
				double randRoll;
				String randChar;

				for (double i = 0; i < 30; i++) {
					randRoll = Math.random();
					randChar = "";
					for (int j = 1; j <= 35; j++) {
						if (randRoll <= (1.0 / 36.0 * j)) {
							randChar = Character.toString(charString.charAt(j - 1));
							break;
						}
					}
					randAlphaNum += randChar;
				}

				this.passwordToken = randAlphaNum;
				this.tempPass = password;

				// Assets Used for Sending Token Via Mail
				String email = user.getEmail();
				String subject = "ICEICO Test Portal OTP";
				String emailMessage = "Hello Student, \n" + " Your link for changing password On ICEICO Test "
						+ "Portal is" + " http://localhost:9003/java/student/profile/validate/token/" + randAlphaNum;

				// Sends Mail
				emailService.sendOtpMessage(email, subject, emailMessage);
				modelMap.addAttribute("passwordMsg", "Check your mail to change password");
			} else {
				modelMap.addAttribute("passwordMsg", "Please enter correct user information");
			}
		} else {
			modelMap.addAttribute("passwordMsg", "User Name not found");
		}
		modelMap.addAttribute("user", user);
		return "webStudProfile";
	}

	/* VERIFY SENT VRIFICATION LINK */
	@GetMapping("/web/student/profile/validate/token/{token}")
	public String validateToken(@PathVariable("token") @Valid String token, ModelMap modelMap, Locale locale)
			throws ResourceNotFoundException {

		User user = userService.findBySSO(this.getPrincipal());

		if (token.equals(this.passwordToken) && !token.equals("Used")) {
			user.setPassword(this.tempPass);
			this.userService.saveUser(user);
			modelMap.addAttribute("passwordMsg", "Password Changed Successfully");
		} else {
			modelMap.addAttribute("passwordMsg", "User Token Invalid");
		}
		modelMap.addAttribute("user", user);
		return "webStudProfile";
	}

	/* CHANGE PASSWORD */
	@GetMapping("/web/change/password")
	public String getPassword(ModelMap modelMap, Locale locale) throws ResourceNotFoundException {
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		return "changePassword";
	}

	/* CHANGE PASSWORD */
	@PostMapping("/web/change/password")
	public String changePassword(@RequestParam("password") String password, ModelMap modelMap, Locale locale)
			throws ResourceNotFoundException {

		User user = this.userService.findBySSO(this.getPrincipal());
		user.setPassword(password);
		this.userService.saveUser(user);
		this.passwordToken = "Used";
		modelMap.addAttribute("passMsg", false);

		modelMap.addAttribute("user", user);
		return "redirect:/web/user";
	}

	/* STUDENT PROFILE DELETE */
	@GetMapping("/web/student/profile/delete/{userId}")
	public String deleteUser(@PathVariable("userId") @Valid Long userId, ModelMap modelMap, Locale locale)
			throws ResourceNotFoundException {

		return "redirect:/web/student/profile";
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
