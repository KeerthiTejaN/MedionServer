package com.starters.controller;


import java.util.ArrayList;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.starters.inf.AddUserInterface;
import com.starters.model.User;
import com.starters.service.LoginService;

@RestController
public class UserManagementController {

	@Autowired
	AddUserInterface addUserInterface;
	
	@Autowired
	LoginService login;
	
	@RequestMapping(value="/api/verify", method=RequestMethod.GET, produces=MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody String display(){
		String result = "Medion Started?";
//		return Response.status(200).entity(result).build();
		return result;
	}
	
	@RequestMapping(value="/api/addUser", method=RequestMethod.POST, produces=MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody String addUser(@RequestBody User user){
		
		addUserInterface.save(user);
		return "User Saved";
	}

	@RequestMapping(value="/api/login", method=RequestMethod.POST, produces=MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody String login(@RequestBody JSONObject obj){
		System.out.println("Inside login");
		String phone=obj.get("username").toString();
		String pass = obj.get("password").toString();
		String result =login.loginCheck(phone, pass);
		return result;
		
	}
	
}
