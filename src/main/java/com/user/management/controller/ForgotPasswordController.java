package com.user.management.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.user.management.service.EmailService;
import com.user.management.service.UserService;

@Controller
public class ForgotPasswordController 
{
	@Autowired
	UserService userService;
	
	@Autowired
	EmailService emailService;
	
	@GetMapping("forgotPassword")
	public String forgotPassword()
	{
		return "forgot-password";
	}
	
	@PostMapping("forgot-password")
	public String forgotPassword(@RequestParam String email, Model model)
	{
		String otp = generateOtp();
		userService.updateByOtp(email, otp);
		emailService.sendOtpEmail(email, otp);
		model.addAttribute("email", email);
		return "verify-otp";
	}
	
	private String generateOtp()
	{
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		return String.valueOf(otp);
	}
}
