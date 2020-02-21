package com.iceico.testportal.Controller;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.iceico.testportal.Exceptions.ResourceNotFoundException;
import com.iceico.testportal.Model.User;
import com.iceico.testportal.Service.UserService;

/**
 * @author puja
 *
 */
@Controller
public class JavaUserController {

	@Autowired
	private UserService userService;

	public JavaUserController() {
	}

	@GetMapping("/java/user")
	public String displayUserInformation(ModelMap modelMap, Locale locale) {

		modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
		return "javaUser";
	}

	@PostMapping("/java/user/save")
	public String saveJavaUser(@ModelAttribute("user") @Valid User user,
			BindingResult bindingResult, ModelMap modelMap, Locale locale) {

			modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
			return "redirect:/java/user";
	}

	@GetMapping("/java/user/edit/{userId}")
	public String editUser(@PathVariable("userId") @Valid Long userId, ModelMap modelMap,
			Locale locale) throws ResourceNotFoundException {

		modelMap.addAttribute("user", userService.findBySSO(this.getPrincipal()));
		return "javaUser";
	}

	@GetMapping("/java/user/delete/{userId}")
	public String deleteUser(@PathVariable("userId") @Valid Long userId, ModelMap modelMap,
			Locale locale) throws ResourceNotFoundException {

		return "redirect:/java/user";
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
