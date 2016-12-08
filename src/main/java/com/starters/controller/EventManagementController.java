package com.starters.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.starters.inf.AddUserInterface;
//import com.starters.inf.AddEventInterface;
import com.starters.model.Event;
import com.starters.model.User;
import com.starters.service.FcmNotificationService;

@RestController
public class EventManagementController {
	
	
	@Autowired
	private AddUserInterface addUserInterface;
	
//	@Autowired
//	private AddEventInterface addEventInterface;
	private List<String> memberFcmTokenList;
	private List<String> memberList;
	private List<User> userlist= new ArrayList<>();
	
//	@Autowired
//	FcmNotificationService fcmNotificationService;
	
	
	@RequestMapping(value="/api/notifyMembers", method=RequestMethod.POST, produces=MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody String notifyMembers(@RequestBody Event event){
		
		//Write logic to save Event object here
//		addEventInterface.save(event);
		
		//invoke Google FCM server to notify users
		String members = event.getMemberList();
		memberList = new ArrayList<String>(Arrays.asList(members.split(",")));
		System.out.println(memberList.size());
		System.out.println(memberFcmTokenList.size());
		System.out.println(memberList.get(0));
		System.out.println(memberList.get(1));
		
		/* memberList above has a list of Client numbers
		 * We need to create another ArrayList<String> memberFcmList;
		 * that contains FCMtokens of each client by fetching from DB */
		
		/*fcms is the list string that has all the client fcm tokens*/
		for(int j=0;j<memberList.size();j++)
		{
			for(User user:addUserInterface.findAll())
			{
				System.out.println(user.getName());
					if(user.getPhone().equals(memberList.get(j)))
					{
						System.out.println(user.getFcmToken());
					}
			}
			
		}
		
		
		
		StringBuilder message = new StringBuilder();
		FcmNotificationService fcmNotificationService = new FcmNotificationService();
		for(int i=0; i<memberFcmTokenList.size(); i++){
			message.setLength(0);
			message.append(memberFcmTokenList.get(i));
			message.append(event.getEventName()+"Event Created");
			message.append("Date"+event.getEventDate());
			message.append("Time"+event.getEventTime());
			System.out.println(message.toString());
			fcmNotificationService.notify(message.toString(), memberFcmTokenList.get(i));
		}
		return "Notified";
	}
	
	
}

