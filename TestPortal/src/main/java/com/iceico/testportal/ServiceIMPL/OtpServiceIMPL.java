/**
 * 
 */
package com.iceico.testportal.ServiceIMPL;

import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.iceico.testportal.Service.OtpService;

/**
 * @author Rajat Date: 14 Feb 2020
 */

@Service
@Transactional
public class OtpServiceIMPL implements OtpService {

	/**
	 * 
	 */
	public OtpServiceIMPL() {

	}

	@Override
	public String generateOTP() {
		String numbers = "0123456789";
		Random rndm_method = new Random();
		char[] otp = new char[6];
		for (int i = 0; i < 6; i++) {
			otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
		}
		String stringOtp = new String(otp);

		return stringOtp;
	}

}
