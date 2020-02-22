package com.iceico.testportal.Controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.iceico.testportal.Exceptions.ResourceNotFoundException;
import com.iceico.testportal.Model.User;
import com.iceico.testportal.Service.EMailService;
import com.iceico.testportal.Service.UserService;

/**
 * @author Rajat Date : 21 Feb 2020
 *
 */
@Controller
public class JavaUserController {

	@Autowired
	private UserService userService;

	@Autowired
	private EMailService emailService;

	private String passwordToken = null;

	public JavaUserController() {
	}

	@GetMapping("/java/user")
	public String displayUserInformation(ModelMap modelMap, Locale locale) {

		modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
		return "javaUser";
	}

	@PostMapping("/java/user/save")
	public String saveJavaUser(@RequestParam("jsonData") String jsonData,
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
		return "redirect:/java/user";
	}

	@GetMapping("/java/user/send/token")
	public String sendToken(ModelMap modelMap, Locale locale) throws ResourceNotFoundException {

		String charString = "abcdefghijklmnopqrstuvwxyz0123456789";
		String randAlphaNum = "";
		double randRoll;
		String randChar;

		// Generate Random Alphanumberic Token
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

		User user = userService.findBySSO(this.getPrincipal());
		String email = user.getEmail();

		String subject = "ICEICO Test Portal OTP";
		String emailMessage = "Hello Student, \n" + " Your link for changing password On ICEICO Test " + "Portal is"
				+ " http://localhost:9003/java/user/validate/token/" + randAlphaNum;
		emailService.sendOtpMessage(email, subject, emailMessage);

		modelMap.addAttribute("passwordChange", false);
		modelMap.addAttribute("user", user);
		return "javaUser";
	}

	@GetMapping("/java/user/validate/token/{token}")
	public String validateToken(@PathVariable("token") @Valid String token, ModelMap modelMap, Locale locale)
			throws ResourceNotFoundException {

		modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
		if (token.equals(this.passwordToken) && !token.equals("Used")) {
			modelMap.addAttribute("tokenMsg", true);
			return "redirect:/java/change/password";
		} else {
			modelMap.addAttribute("tokenMsg", false);
		}

		return "javaUser";
	}

	@GetMapping("/java/change/password")
	public String getPassword( ModelMap modelMap, Locale locale)
			throws ResourceNotFoundException {
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		return "changePassword";
	}

	@PostMapping("/java/change/password")
	public String changePassword(@RequestParam("password") String password, ModelMap modelMap, Locale locale)
			throws ResourceNotFoundException {

		User user = this.userService.findBySSO(this.getPrincipal());
		user.setPassword(password);
		this.userService.saveUser(user);
		this.passwordToken = "Used";

		modelMap.addAttribute("user", user);
		return "javaUser";
	}

	@GetMapping("/java/user/update")
	public String editUser(ModelMap modelMap, Locale locale) throws ResourceNotFoundException {

		modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
		return "updateUser";
	}

	@GetMapping("/java/user/delete/{userId}")
	public String deleteUser(@PathVariable("userId") @Valid Long userId, ModelMap modelMap, Locale locale)
			throws ResourceNotFoundException {

		return "redirect:/java/user";
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
