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

import com.starters.inf.AddEventInterface;
import com.starters.inf.AddUserEventInterface;
import com.starters.inf.AddUserInterface;
import com.starters.model.Delid;
//import com.starters.inf.AddEventInterface;
import com.starters.model.Event;
import com.starters.model.User;
import com.starters.model.UserEvent;
import com.starters.service.FcmNotificationService;

@RestController
public class EventManagementController {
	
	
	@Autowired
	private AddUserInterface addUserInterface;
	
	@Autowired
	private AddEventInterface addEventInterface;
	
	@Autowired
	private AddUserEventInterface addUserEventInterface;
	
//	@Autowired
//	FcmNotificationService fcmNotificationService;
	
	private List<String> memberFcmTokenList;
	private List<String> memberList;
	private List<String> delMemberList;
	private Event tempEvent;
	
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
	
	@RequestMapping(value="/api/notifyNewMembers", method=RequestMethod.POST, produces=MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody String notifyNewMembers(@RequestBody Event event){
		
		String members = event.getMemberList();
		String newmembers=null;
	
		String evename = event.getEventName();
		String [] times = event.getEventTime().split("/");
		String time = times[0];
		String id=times[1];
		String date = event.getEventDate();
		for(Event eve:addEventInterface.findAll())
		{
			if(Integer.toString(eve.getId()).equals(id))
			{
				String currentmem = eve.getMemberList();
				String newmem = currentmem+","+members;
				eve.setMemberList(newmem);
				addEventInterface.save(eve);
				newmembers = eve.getMemberList();
			}
		}		
		memberList = new ArrayList<String>(Arrays.asList(members.split(",")));
		memberFcmTokenList = findAllFcmTokens(memberList);
		
		//Send EventID along with Event details
		
		StringBuilder message = new StringBuilder();
		FcmNotificationService fcmNotificationService = new FcmNotificationService();
		for(int i=0; i<memberFcmTokenList.size(); i++){
			message.setLength(0);
			message.append("EventCreated"+",");
			//Fetching Event ID of the event stored in DB just above.
			message.append(id+",");
			message.append(evename+",");
			message.append(date+",");
			message.append(time+",");
			message.append(newmembers);
			System.out.println(message.toString());
			System.out.println(memberFcmTokenList.get(i));
			fcmNotificationService.notify(message.toString(), memberFcmTokenList.get(i));
			
		}
		String responseFromServer = "New Members Notified";
		return responseFromServer;
	}
	
	
	
	
	@RequestMapping(value="/api/notifyMembers", method=RequestMethod.POST, produces=MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody String notifyMembers(@RequestBody Event event){
		String[] parts =event.getEventTime().split("/");
		event.setEventTime(parts[0]);
		System.out.println(parts[0]);
		String[] userlatlongs = parts[1].split(",");
		tempEvent = addEventInterface.save(event);
		String lat = userlatlongs[0];
		String longs= userlatlongs[1];
		String phonenum = userlatlongs[2];	
		System.out.println("admin lat:"+lat);
		System.out.println("admin long:"+longs);
		System.out.println("admin phone:"+phonenum+":");
		System.out.println("event id:"+tempEvent.getId());
		String fcm=null;
		for(User user:addUserInterface.findAll())
		{
			if(user.getPhone().equals(phonenum))
			{
				fcm = user.getFcmToken();
				System.out.println("matched!");
			}
		}
		System.out.println("The matched phone number's fcm token is:"+fcm);
		int event_id= tempEvent.getId();
		UserEvent userevent = new UserEvent();
		userevent.setAcceptance(true);
		userevent.setEventId(event_id);
		userevent.setLatitude(lat);
		userevent.setLongitude(longs);
		userevent.setUserFcmToken(fcm);
		addUserEventInterface.save(userevent);

		
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
			//Fetching Event ID of the event stored in DB just above.
			message.append(Integer.toString(tempEvent.getId())+",");
			message.append(event.getEventName()+",");
			message.append(event.getEventDate()+",");
			message.append(event.getEventTime()+",");
			message.append(members);
			System.out.println(message.toString());
			System.out.println(memberFcmTokenList.get(i));
			fcmNotificationService.notify(message.toString(), memberFcmTokenList.get(i));
			
		}
		String responseFromServer = Integer.toString(tempEvent.getId());
		return responseFromServer;
	}
	
	@RequestMapping(value="/api/addUserEvent", method=RequestMethod.POST, produces=MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody String addUserEventLocation(@RequestBody UserEvent userEvent){
		
		//code to save UserEvent object to UserEvent table
		addUserEventInterface.save(userEvent);
		
		
		return "Saved To UserEvent Table";
	}
	
	@RequestMapping(value="/serv/delevent",method=RequestMethod.POST, produces= MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody String delEvent(@RequestBody Delid delid)
	{
		String eventId = delid.getId();
		System.out.println(eventId);
		String members=null;
		for(UserEvent userevent:addUserEventInterface.findAll())
		{
			if(Integer.toString(userevent.getEventId()).equals(eventId))
			{
				addUserEventInterface.delete(userevent);
			}
		}
		for(Event event:addEventInterface.findAll())
		{
			if(Integer.toString(event.getId()).equals(eventId))
			{
				members = event.getMemberList();
				addEventInterface.delete(event);
			}
		}
		try
		{
		delMemberList = new ArrayList<String>(Arrays.asList(members.split(",")));
		memberFcmTokenList = findAllFcmTokens(delMemberList);
		}
		catch(Exception e)
		{
			return "Event cannot be found!";
		}
		StringBuilder message = new StringBuilder();
		FcmNotificationService fcmNotificationService = new FcmNotificationService();
		for(int i=0; i<memberFcmTokenList.size(); i++){
			message.setLength(0);
			message.append("Event with id,"+eventId+",deleted");
			//Fetching Event ID of the event stored in DB just above.
			System.out.println(message.toString());
			System.out.println(memberFcmTokenList.get(i));
			fcmNotificationService.notify(message.toString(), memberFcmTokenList.get(i));
		
		}
		return "Successfully deleted event with id: "+eventId;
	}
	
	@RequestMapping(value="/serv/delmember",method=RequestMethod.POST, produces= MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody String delMember(@RequestBody Delid delidx)
	{
	String userphone = delidx.getId();
	String [] parts = userphone.split("!");
	String phone = parts[0];
	String eventId = parts[1];
	System.out.println(phone+":"+eventId);
	String userfcm =null;
	String adminfcm =null;
	for(User userx:addUserInterface.findAll())
	{
		if(userx.getPhone().equals(phone))
		{
			userfcm =userx.getFcmToken();
		}
	}
	System.out.println(userfcm);
	for(Event event:addEventInterface.findAll())
	{
		if(Integer.toString(event.getId()).equals(eventId))
		{
			String s =event.getMemberList();
			String [] members=s.split(",");
			StringBuilder newmembers = new StringBuilder();
			for(int i=0;i<members.length;i++)
			{
				if(members[i].equals(phone))
				{
				}
				else
				{
					newmembers.append(members[i]+",");
				}
			}
			if(newmembers.length()==0)
			{}
			else
			{
			newmembers.setLength(newmembers.length()-1);}
			event.setMemberList(newmembers.toString());
			System.out.println("new member list:"+newmembers);
			addEventInterface.save(event);
			}
		}
	for(UserEvent userevent:addUserEventInterface.findAll())
	{
		System.out.println(Integer.parseInt("comparing factor:"+eventId));
		System.out.println(userevent.getEventId());	
		System.out.println("comparing factor ; "+userfcm);
		System.out.println(userevent.getUserFcmToken());
		if(userevent.getEventId()==Integer.parseInt(eventId)&&userevent.getUserFcmToken().equals(userfcm))
		{
			System.out.println("inside deleting userevent of member deleted!");
			addUserEventInterface.delete(userevent);
		}
		else
		{
			return "Can't find the record of your registration to this event!";
		}
	}
	for(UserEvent userevent:addUserEventInterface.findAll())
	{
		if(Integer.toString(userevent.getEventId()).equals(eventId))
		{
			adminfcm = userevent.getUserFcmToken();
			break;
		}
	}
	String message = "Member with Phone,"+phone+","+eventId+",dropped out! Please recalculate medion with pick places again.!";
	FcmNotificationService fcmNotificationService = new FcmNotificationService();
	fcmNotificationService.notify(message, adminfcm);
	return "Successfully deleted you from this event!";
	}
	
	@RequestMapping(value="/api/finEvent", method=RequestMethod.POST, produces=MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody String finEvent(@RequestBody Delid delid){
		System.out.println("inside finevent");
		FcmNotificationService fcmNotificationService = new FcmNotificationService();
		String[] evedetails = delid.getId().split("!");
		String eveid = evedetails[0];
		String evefinlatlongs = evedetails[1];
		String evemem = evedetails[2];
		for(UserEvent userevent:addUserEventInterface.findAll())
		{
			if(Integer.toString(userevent.getEventId()).equals(eveid))
			{
				fcmNotificationService.notify("Event Finalized!"+eveid+"!With members= !"+evemem+"! at latlongs= !"+evefinlatlongs, userevent.getUserFcmToken());
			}
		
		return "Event Finalized!";
		}
		return null;
		
	}
	
	
}

