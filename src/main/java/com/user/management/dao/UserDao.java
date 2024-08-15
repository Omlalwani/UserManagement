package com.user.management.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.user.management.model.User;



@Repository
public class UserDao 
{
	@Autowired
	HibernateTemplate hibernateTemplate;
	
	@Transactional
	public void save(User user)
	{
		this.hibernateTemplate.saveOrUpdate(user);
	}
	
	@Transactional
	public User findByEmail(String email)
	{	
		String hql = "from User where email=:email";
		List<User> users = (List<User>) hibernateTemplate.findByNamedParam(hql, "email", email);
		return users.isEmpty()? null : users.get(0);	
	}
	
	
}
