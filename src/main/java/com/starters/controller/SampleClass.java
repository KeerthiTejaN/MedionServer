package com.starters.controller;


import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.starters.model.User;
import com.starters.service.AddUserService;

@RestController
public class SampleClass {
	
	@Autowired
	private AddUserService adduserservice;
	
	@GetMapping("/")
	public String Hello()
	{
		return "Hello World!";
	}
	
	@GetMapping("/hello")
	public String Home()
	{
		return "index";
	}
	
	@GetMapping("/getuserdata")
	public String allUsers()
	{
		return adduserservice.findAll().toString();
	}
	
	@GetMapping("/saveuserdata")
	public String saveUser(@RequestParam String name,@RequestParam String email,@RequestParam String phone,@RequestParam String password,@RequestParam String fcmtoken)
	{
		User user = new User(name,email,phone,password,fcmtoken);
		adduserservice.save(user);
		return "User SAVED!";
	}
	
	@GetMapping("/deluserwithid")
	public String delUser(@RequestParam int id)
	{
		adduserservice.delete(id);
		return "User Deleted";
	}
	
	@GetMapping("/v")
	public String v(){
		
		return "Medion Started!";
		
	}
	
	

}
