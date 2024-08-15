package com.user.management.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.management.dao.UserDao;
import com.user.management.model.User;

@Service
public class UserService 
{
	@Autowired
	UserDao dao;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	public void addUser(User user)
	{
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		dao.save(user);
	}
	
	
	public User loginByEncrypt(String password, String email)
	{
		User user = dao.findByEmail(email);
		String enodedPassword = user.getPassword();
		
		if(passwordEncoder.matches(password, enodedPassword))
		{
			return user;
		}
		return null;
	}
	
	public void updateByOtp(String email, String otp)
	{
		User user = dao.findByEmail(email);
		if(user != null)
		{
			user.setOtp(otp);
			user.setOtpGeneratedTime(LocalDateTime.now());
			dao.save(user);
			System.out.println("OTP for email : " + user.getEmail() + "is : " + user.getOtp());
		}
		
		
	}
	
	//3 - Checks weather the OTP is valid or not
	public boolean isOtpValid(String email, String otp)
	{
		User user = dao.findByEmail(email);
		if(user != null && user.getOtp().equals(otp))
		{
			LocalDateTime now = LocalDateTime.now();
			return user.getOtpGeneratedTime().isAfter(now.minusMinutes(5));
		}
		return false;
	}

	public void updatePassword(String email, String newPassword)
	{
		User user = dao.findByEmail(email);
		user.setPassword(passwordEncoder.encode(newPassword));
		user.setOtp(null); // Clears OTP
		user.setOtpGeneratedTime(null); // Clears OTP Time Stamp
		dao.save(user);
	}
}
