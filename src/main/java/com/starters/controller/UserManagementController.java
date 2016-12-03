package com.starters.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.starters.model.User;
import com.starters.service.AddUserService;

@RestController
public class UserManagementController {

	@Autowired
	AddUserService addUserService;
	
	@RequestMapping(value="/api/verify", method=RequestMethod.GET, produces=MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody String display(){
		String result = "Medion Started?";
//		return Response.status(200).entity(result).build();
		return result;
	}
	
	@RequestMapping(value="/api/addUser", method=RequestMethod.POST, produces=MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody String addUser(@RequestBody User user){
		
		addUserService.save(user);
		System.out.println("FCM: "+user.getFcmToken());
		return "User Saved";
	}
	
}
