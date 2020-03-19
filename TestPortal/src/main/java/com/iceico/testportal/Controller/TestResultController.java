/**
 * 
 */
package com.iceico.testportal.Controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.iceico.testportal.Model.TestResult;

/**
 * @author sameer
 *
 */
@Controller
public class TestResultController {

	/**
	 * 
	 */
	public TestResultController() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/java/student/test/result")
	public String getTestResultPage(ModelMap modelMap) {
		modelMap.addAttribute("testResult", new TestResult());
		modelMap.addAttribute("edit", false);
		return "result";
	}

}
