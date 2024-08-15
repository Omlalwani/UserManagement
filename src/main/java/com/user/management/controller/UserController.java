package com.user.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.user.management.dao.UserDao;
import com.user.management.model.User;
import com.user.management.service.UserService;

@Controller
public class UserController 
{
	@Autowired
	UserService service;
	
	@Autowired
	UserDao dao;
	
	@RequestMapping("/")
	public String home()
	{
		return "index";
	}
	
	@RequestMapping("register")
	public String register()
	{
		return "index";
	}
	
	@RequestMapping("login")
	public String login()
	{
		return "login";
	}
	
	@PostMapping("addUser")
	public String addUser(@ModelAttribute User user)
	{
		this.service.addUser(user);
		return "login";
	}
	
	@PostMapping("loginUser")
	public String loginUser(@RequestParam String email,@RequestParam String password)
	{
		User user = service.loginByEncrypt(password, email);
		if(user != null)
		{
			return "sucess";
		}
		
		return "login";
	}
}
