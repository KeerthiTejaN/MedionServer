package com.starters.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//import com.starters.inf.AddEventInterface;
import com.starters.model.Event;
import com.starters.service.AddUserService;
import com.starters.service.FcmNotificationService;

@RestController
public class EventManagementController {
	
	@Autowired
	private AddUserService adduserservice;
	
//	@Autowired
//	private AddEventInterface addEventInterface;
	private List<String> fcms = new ArrayList<String>();
	
//	@Autowired
//	FcmNotificationService fcmNotificationService;
	
	@GetMapping("/fcm")
	public List<String> allFcms()
	{
		fcms = adduserservice.findAllFcmTokens();
		return adduserservice.findAllFcmTokens();
	}
	
	@RequestMapping(value="/api/notifyMembers", method=RequestMethod.POST, produces=MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody String notifyMembers(@RequestBody Event event){
		
		//Write logic to save Event object here
//		addEventInterface.save(event);
		
		//invoke Google FCM server to notify users
		String members = event.getMemberList();
		List<String> memberList = new ArrayList<String>(Arrays.asList(members.split(",")));
		
		/* memberList above has a list of Client numbers
		 * We need to create another ArrayList<String> memberFcmList;
		 * that contains FCMtokens of each client by fetching from DB */
		
		/*fcms is the list string that has all the client fcm tokens*/
		
		
		
		StringBuilder message = new StringBuilder();
		FcmNotificationService fcmNotificationService = new FcmNotificationService();
		for(int i=0; i<memberList.size(); i++){
			message.setLength(0);
			message.append(memberList.get(i));
			message.append(event.getEventName()+"Event Created");
			message.append("Date"+event.getEventDate());
			message.append("Time"+event.getEventTime());
			System.out.println(message.toString());
			fcmNotificationService.notify(message.toString(), memberList.get(i));
		}
		return "Notified";
	}
	
	
}

