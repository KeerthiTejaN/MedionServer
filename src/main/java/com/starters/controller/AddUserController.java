package com.starters.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.starters.model.User;
import com.starters.service.AddUserService;

@Path("/")
public class AddUserController {
	
	@Autowired
	private AddUserService adduserservice;
	
	@POST
	@Path("/printUser")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response printTheUser(InputStream incomingData){
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
			String line = null;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
		} catch (Exception e) {
			System.out.println("Error Parsing: - ");
		}
		System.out.println("Data Received: " + sb.toString());
 
		// return HTTP response 200 in case of success
		return Response.status(200).entity(sb.toString()).build();
	}
	
	@POST
	@Path("/addTheUser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
//	@RequestMapping(value = {"addTheUser"}, method = RequestMethod.POST)
	public @ResponseBody String addTheUser(@RequestBody User user){
		
		adduserservice.save(user);
		System.out.println("NAME: "+user.getName());
		System.out.println("FCM: "+user.getFcmToken());
		
		// return String response in case of success
		return "USER SAVED";
	}
	
//	@GET
//	@Path("/verify")
//	@Produces(MediaType.TEXT_PLAIN)
//	public Response verifyRESTService(InputStream incomingData) {
//		String result = "Medion Successfully started..";
// 
//		// return HTTP response 200 in case of success
//		return Response.status(200).entity(result).build();
//	}
}
