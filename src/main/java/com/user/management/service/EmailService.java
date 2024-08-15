package com.user.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService 
{
	@Autowired
	JavaMailSender mailSender;
	
	public void sendOtpEmail(String to, String otp)
	{
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject("Reset Password - Otp");
		message.setText("Your Reset Password OTP is : " + otp);
		mailSender.send(message);
	}
}
