/**
 * 
 */
package com.iceico.testportal.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iceico.testportal.Exceptions.ResourceNotFoundException;
import com.iceico.testportal.Model.AddTest;
import com.iceico.testportal.Model.QuestionBank;
import com.iceico.testportal.Model.TestQuestion;
import com.iceico.testportal.Service.AddTestService;
import com.iceico.testportal.Service.QuestionBankService;
import com.iceico.testportal.Service.UserService;

/**
 * @author Rajat
 * @version 0.1
 * Creation Date: 16/03/2020
 *
 */
@Controller
public class StartTestController {
	public StartTestController() {
		
	}

	@Autowired
	private AddTestService addTestService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private QuestionBankService questionBankService;


	/* Test List page */
	@RequestMapping("/java/student/test/list")
	public String testList(ModelMap modelMap, Locale locale) throws ResourceNotFoundException, ParseException {
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		modelMap.addAttribute("testList", this.addTestService.getAddTestList());
		return "testList";
	}

	/* Start Test page */
	@RequestMapping("/java/student/start/test/{testId}")
	public String startTest(@PathVariable("testId") @Valid Long testId,ModelMap modelMap, Locale locale) throws ResourceNotFoundException, ParseException {
		
		AddTest test = addTestService.getAddTestById(testId);
		modelMap.addAttribute( "addTest", test);
		
		List<QuestionBank> questionDetailList = new ArrayList<QuestionBank>();
		
		for( TestQuestion question: test.getTestQuestions()) {
			questionDetailList.add( this.questionBankService.getQuestionBankById(question.getQuestionId()));
		}
		
		modelMap.addAttribute( "testQuestions", questionDetailList);
		modelMap.addAttribute( "user", this.userService.findBySSO(this.getPrincipal()));
		return "startTest";
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@RequestMapping(value="/java/student/start/test/compiler", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public @ResponseBody JSONObject  runCode(
			 @RequestParam("language") String languageIn,@RequestParam("code") String code) throws ResourceNotFoundException, ParseException, org.json.simple.parser.ParseException {		

		
		String clientId = "6a12fd18773efb45dd8c612433895194"; //Replace with your client ID
		String clientSecret = "18e0fd8cdd07136086af0f620d57a4af982d04e5c9e29b8274371a6c82b58a94"; //Replace with your client Secret
        String script = code;
        String language = null;
        int versionIndex = 0;
        
        String a = "\"";
    	String b = "\\\"";
    	script = script.replace( a, b);
        
        // Python Configuration
        if(languageIn.equalsIgnoreCase("Python")) language = "python3";

        // Java Configuration
        if(languageIn.equalsIgnoreCase("Java")) {
        	language = "java";
        	versionIndex = 1;
        }

        // JavaScript Configuration
        if(languageIn.equalsIgnoreCase("Javascript")) {
        	language = "nodejs";
        	versionIndex = 1;
        }

        // Php Configuration
        if(languageIn.equalsIgnoreCase("Php")) {
        	script = script.replace( "echo", " echo");
        	language = "php";
        	versionIndex = 1;
        }

        String output = null;
        

        try {

        	String urlStr = "https://api.jdoodle.com/execute";

            URL url = new URL( urlStr);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            String input = "{\"clientId\": \"" + clientId + "\",\"clientSecret\":\"" + clientSecret + "\",\"script\":\"" + script +
            "\",\"language\":\"" + language + "\",\"versionIndex\": " + versionIndex + "} ";

            System.out.println("input ===> "+input);
            
            OutputStream outputStream = connection.getOutputStream();
            
            outputStream.write(input.getBytes());
            outputStream.flush();
            
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Please check your inputs : HTTP error code : "+ connection.getResponseCode());
            }

            BufferedReader bufferedReader;
            bufferedReader = new BufferedReader(new InputStreamReader((connection.getInputStream())));

            while ((output = bufferedReader.readLine()) != null) {
                System.out.println("Output ====> "+output);
                JSONObject outJson = new JSONObject();
                outJson = (JSONObject) new JSONParser().parse(output);
            }
            

            connection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

		JSONObject ad = new JSONObject();
		ad.put("output", output);
		return ad;
	}

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