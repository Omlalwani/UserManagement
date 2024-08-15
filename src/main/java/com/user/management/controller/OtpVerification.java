package com.user.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.user.management.service.UserService;

@Controller
public class OtpVerification 
{
	@Autowired
	private UserService userService;
	
	//2 - It will check for OTP verification
	@PostMapping("verify-otp")
	public String verifyOtp(@RequestParam("email") String email,@RequestParam("otp") String otp, Model model)
	{
		if(userService.isOtpValid(email, otp))
		{
			return "reset-password";
		}else {
			model.addAttribute("message", "Invalid or Expired OTP");
			return "verify-otp";
		}		
	}
}
