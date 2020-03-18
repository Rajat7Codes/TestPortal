/**
 * 
 */
package com.iceico.testportal.ServiceIMPL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.transaction.Transactional;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.iceico.testportal.Service.CompilerService;

/**
 * @author Rajat
 * Created on : 17 March 2020
 */
@Service
@Transactional
public class CompilerServiceIMPL implements CompilerService {

	@SuppressWarnings("unchecked")
	@Override
	public String runCode(String languageIn, String script) throws ParseException {
		String clientId = "6a12fd18773efb45dd8c612433895194"; //Replace with your client ID
		String clientSecret = "18e0fd8cdd07136086af0f620d57a4af982d04e5c9e29b8274371a6c82b58a94"; //Replace with your client Secret
		int versionIndex = 0;
		String output = null;

        // Python Configuration
        if(languageIn.equalsIgnoreCase("Python")) languageIn = "python3";

        // Java Configuration
        if(languageIn.equalsIgnoreCase("Java")) {
        	languageIn = "java";
        	versionIndex = 1;
        }

        // JavaScript Configuration
        if(languageIn.equalsIgnoreCase("Javascript")) {
        	languageIn = "nodejs";
        	versionIndex = 1;
        }

        // Php Configuration
        if(languageIn.equalsIgnoreCase("Php")) {
        	script = script.replace( "echo", " echo");
        	languageIn = "php";
        	versionIndex = 1;
        }
        
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL("https://api.jdoodle.com/execute").openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("clientId", clientId);
            jsonObject.put("clientSecret", clientSecret);
            jsonObject.put("script", script);
            jsonObject.put("language", languageIn);
            jsonObject.put("versionIndex", versionIndex);

            System.out.println("input ===> "+jsonObject.toJSONString());
            byte[] in = jsonObject.toJSONString().getBytes("utf-8");
            OutputStream outputStream = connection.getOutputStream();
            
            outputStream.write(in,0,in.length);
            outputStream.flush();
            
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Please check your inputs : HTTP error code : "+ connection.getResponseCode());
            }

            BufferedReader bufferedReader;
            bufferedReader = new BufferedReader(new InputStreamReader((connection.getInputStream())));
            String outputA = "";
            while ((outputA = bufferedReader.readLine()) != null) {
                output = outputA;
            }
            connection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

		return output;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public String runCodeWithInput(String languageIn, String script, String input) throws ParseException {
		String clientId = "6a12fd18773efb45dd8c612433895194"; //Replace with your client ID
		String clientSecret = "18e0fd8cdd07136086af0f620d57a4af982d04e5c9e29b8274371a6c82b58a94"; //Replace with your client Secret
		int versionIndex = 0;
		String output = null;

        // Python Configuration
        if(languageIn.equalsIgnoreCase("Python")) languageIn = "python3";

        // Java Configuration
        if(languageIn.equalsIgnoreCase("Java")) {
        	languageIn = "java";
        	versionIndex = 1;
        }

        // JavaScript Configuration
        if(languageIn.equalsIgnoreCase("Javascript")) {
        	languageIn = "nodejs";
        	versionIndex = 1;
        }

        // Php Configuration
        if(languageIn.equalsIgnoreCase("Php")) {
        	script = script.replace( "echo", " echo");
        	languageIn = "php";
        	versionIndex = 1;
        }
        
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL("https://api.jdoodle.com/execute").openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("clientId", clientId);
            jsonObject.put("clientSecret", clientSecret);
            jsonObject.put("script", script);
            jsonObject.put("stdin", input);
            jsonObject.put("language", languageIn);
            jsonObject.put("versionIndex", versionIndex);

            System.out.println("input ===> "+jsonObject.toJSONString());
            byte[] in = jsonObject.toJSONString().getBytes("utf-8");
            OutputStream outputStream = connection.getOutputStream();
            
            outputStream.write(in,0,in.length);
            outputStream.flush();
            
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Please check your inputs : HTTP error code : "+ connection.getResponseCode());
            }

            BufferedReader bufferedReader;
            bufferedReader = new BufferedReader(new InputStreamReader((connection.getInputStream())));
            String outputA = "";
            while ((outputA = bufferedReader.readLine()) != null) {
                output = outputA;
            }
            connection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

		return output;
	}
	
}
