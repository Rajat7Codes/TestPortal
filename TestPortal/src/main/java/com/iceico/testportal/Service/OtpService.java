/**
 * 
 */
package com.iceico.testportal.Service;



/**
 * @author Rajat
 * 14 Feb 2020
 */
public interface OtpService {
	
	public int generateOTP(String key);
	
	public int getOtp(String key);
	
	public void clearOTP(String key);
}
