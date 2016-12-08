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
	
//	@Autowired
//	FcmNotificationService fcmNotificationService;
	
	private List<String> memberFcmTokenList;
	private List<String> memberList;
	
//	public <User> ArrayList<User> makeCollection(Iterable<User> iter) {
//	    ArrayList<User> list = new ArrayList<User>();
//	    for (User item : iter) {
//	        list.add(item);
//	    }
//	    return list;
//	}
	
	
	public List<String> findAllFcmTokens(List<String> memberList)
	{
		List<String> fcms = new ArrayList<String>();
//		ArrayList<User> users;
//		users = makeCollection(addUserInterface.findAll());
		for(int i=0; i<memberList.size(); i++){
//			for(User user: users)
			for(User user: addUserInterface.findAll())
			{
				if(memberList.get(i).equals(user.getPhone())){
					fcms.add(user.getFcmToken());
				}
			}
		}
		return fcms;
	}
	
	@RequestMapping(value="/api/notifyMembers", method=RequestMethod.POST, produces=MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody String notifyMembers(@RequestBody Event event){
		
		//Write logic to save Event object here
//		addEventInterface.save(event);
		
		//invoke Google FCM server to notify users
		String members = event.getMemberList();
		memberList = new ArrayList<String>(Arrays.asList(members.split(",")));
		memberFcmTokenList = findAllFcmTokens(memberList);
		
		//Send EventID along with Event details
		
		StringBuilder message = new StringBuilder();
		FcmNotificationService fcmNotificationService = new FcmNotificationService();
		for(int i=0; i<memberFcmTokenList.size(); i++){
			message.setLength(0);
			message.append("EventCreated"+",");
			message.append("EventID"+",");
			message.append(memberList.get(i)+",");
			message.append(event.getEventName()+",");
			message.append(event.getEventDate()+",");
			message.append(event.getEventTime()+",");
			System.out.println(message.toString());
			fcmNotificationService.notify(message.toString(), memberFcmTokenList.get(i));
		}
		return "Notified";
	}
	
	
}

