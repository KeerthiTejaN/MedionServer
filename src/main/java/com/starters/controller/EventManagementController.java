package com.starters.controller;

import java.util.ArrayList;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.starters.model.Event;
import com.starters.service.FcmNotificationService;

@RestController
public class EventManagementController {
	
	FcmNotificationService fcm = new FcmNotificationService();
//	private ArrayList<String> memeberList;
	@RequestMapping(value="/api/notifyMembers", method=RequestMethod.POST, produces=MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody String notifyMembers(@RequestBody Event event){
		
		//Write logic to save Event object here
		
		final ArrayList<String> memberList = event.getMemberList();
		StringBuilder message = new StringBuilder();
		for(int i=0; i<memberList.size(); i++){
			message.setLength(0);
			message.append(event.getEventName()+"Event Created");
			message.append("Date"+event.getEventData());
			message.append("Time"+event.getEventTime());
			fcm.notify(message.toString(), memberList.get(i));
		}
		return "Notified";
	}
}
