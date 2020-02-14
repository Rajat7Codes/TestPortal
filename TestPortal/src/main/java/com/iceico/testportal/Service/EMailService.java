/**
 * 
 */
package com.iceico.testportal.Service;



/**
 * @author Rajat
 * 14 Feb 2020
 */
public interface EMailService {
	
	public void sendOtpMessage( String to, String subject, String message);
}
