/**
 * 
 */
package com.iceico.testportal.ServiceIMPL;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.iceico.testportal.Service.OtpService;

/**
 * @author Rajat Date: 14 Feb 2020
 */

@Service
@Transactional
public class OtpServiceIMPL implements OtpService {

	// cache based on expiry mins 15
	private static final Integer EXPIRE_MINS = 15;

	private LoadingCache<String, Integer> otpCache;

	public OtpServiceIMPL() {
		super();
		otpCache = CacheBuilder.newBuilder().expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES)
				.build(new CacheLoader<String, Integer>() {
					public Integer load(String key) {
						return 0;
					}
				});
	}

	//This method is used to push the opt number or Rewrite the OTP if it exists
	@Override
	public int generateOTP(String key) {
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		otpCache.put(key, otp);
		return otp;
	}

	//This method is used to return the OPT number for given Key
	@Override
	public int getOtp(String key) {
		try {
			return otpCache.get(key);
		} catch (Exception e) {
			return 0;
		}
	}

	//This method is used to clear the OTP catch
	@Override
	public void clearOTP(String key) {
		 otpCache.invalidate(key);
	}

}
