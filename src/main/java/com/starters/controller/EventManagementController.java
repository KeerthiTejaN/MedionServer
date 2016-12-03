package com.starters.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.starters.inf.AddEventInterface;
import com.starters.model.Event;
import com.starters.service.FcmNotificationService;

@RestController
public class EventManagementController {
	
	@Autowired
	private AddEventInterface addEventInterface;
	
	@Autowired
	FcmNotificationService fcmNotificationService;
	
	@RequestMapping(value="/api/notifyMembers", method=RequestMethod.POST, produces=MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody String notifyMembers(@RequestBody Event event){
		
		//Write logic to save Event object here
		addEventInterface.save(event);
		
		//invoke Google FCM server to notify users
		final ArrayList<String> memberList = event.getMemberList();
		StringBuilder message = new StringBuilder();
		
		for(int i=0; i<memberList.size(); i++){
			message.setLength(0);
			message.append(event.getEventName()+"Event Created");
			message.append("Date"+event.getEventDate());
			message.append("Time"+event.getEventTime());
			fcmNotificationService.notify(message.toString(), memberList.get(i));
		}
		return "Notified";
	}
	
	
}
