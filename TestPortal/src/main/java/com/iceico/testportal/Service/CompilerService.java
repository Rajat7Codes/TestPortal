/**
 * 
 */
package com.iceico.testportal.Service;

import org.json.simple.parser.ParseException;

/**
 * @author Rajat
 * Created on : 17 March 2020
 */
public interface CompilerService {
	public String runCode( String language, String script) throws ParseException;
}
