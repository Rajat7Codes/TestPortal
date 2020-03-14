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
import java.util.Locale;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iceico.testportal.Exceptions.ResourceNotFoundException;
import com.iceico.testportal.Service.UserService;

/**
 * @author Rajat
 *
 */

@Controller
public class CompilerController {

	@Autowired
	private UserService userService;
	
	
	/**
	 * 
	 */
	public CompilerController() {

	}
	
	/* sample method start test 2 designing*/
	@RequestMapping("/java/student/start/test/compiler")
	public String startTest2(ModelMap modelMap, Locale locale) throws ResourceNotFoundException, ParseException {
		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		return "startTestCompiler";
	}


	@PostMapping("/java/student/start/test/compiler")
	public String runCode(ModelMap modelMap, Locale locale, 
			@RequestParam("code") String code, @RequestParam("language") String languageIn) throws ResourceNotFoundException, ParseException, org.json.simple.parser.ParseException {		

		modelMap.addAttribute("code", code);
		modelMap.addAttribute("language", languageIn);
		
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
        

        try {
            URL url = new URL("https://api.jdoodle.com/execute");
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

            String output;
            while ((output = bufferedReader.readLine()) != null) {
                System.out.println("Output ====> "+output);
                JSONObject outJson = new JSONObject();
                outJson = (JSONObject) new JSONParser().parse(output);
                modelMap.addAttribute("output1", outJson.get("output"));
            }
            

            connection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

		modelMap.addAttribute("user", this.userService.findBySSO(this.getPrincipal()));
		return "startTestCompiler";
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
