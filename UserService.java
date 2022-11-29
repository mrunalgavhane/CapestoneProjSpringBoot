package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;

@Service
public class UserService {
	
	@Autowired
	public UserRepo userrepo;
	
	public User users(String mail) {
		if(userrepo.existsById(mail)){
			return userrepo.findById(mail).get();
		}
		else {
			return null;
		}
	}
	
}
