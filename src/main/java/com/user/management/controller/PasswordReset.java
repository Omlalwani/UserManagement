package com.user.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.user.management.service.UserService;

@Controller
public class PasswordReset 
{
	@Autowired
	UserService userService;
	
	@PostMapping("reset-password")
	public String resetPassword(String email, String newPassword)
	{
		userService.updatePassword(email, newPassword);
		return "login";
	}
}
