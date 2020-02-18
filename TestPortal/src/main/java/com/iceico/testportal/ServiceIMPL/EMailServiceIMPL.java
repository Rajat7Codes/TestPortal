/**
 * 
 */
package com.iceico.testportal.ServiceIMPL;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.iceico.testportal.Service.EMailService;

/**
 * @author Rajat Date: 14 Feb 2020
 */

@Service
@Transactional
public class EMailServiceIMPL implements EMailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void sendOtpMessage(String to, String subject, String message) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(message);

		javaMailSender.send(simpleMailMessage);
	}

}
