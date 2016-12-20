package com.starters.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.starters.HttpURLConnectionExample;
import com.starters.inf.AddUserEventInterface;
import com.starters.model.Coordinates;
import com.starters.model.Eid;
import com.starters.model.EventMedian;
import com.starters.model.UserEvent;
import com.starters.service.CalculateMedianService;
import com.starters.service.FcmNotificationService;


@RestController
public class MedionController {
	
	@Autowired
	AddUserEventInterface addUserEventInterface;
	
//	@Autowired
	Coordinates coordinate;
	
//	@Autowired
	private CalculateMedianService calculateMedionService;
	private FcmNotificationService fcmNotificationService;
	private UserEvent userEvent;
	private String finalres;
	
	public <UserEvent> ArrayList<UserEvent> makeCollection(Iterable<UserEvent> iter) {
    ArrayList<UserEvent> list = new ArrayList<UserEvent>();
    for (UserEvent item : iter) {
        list.add(item);
    }
    return list;
	}
	
	public List<UserEvent> findSpecificUserEvents(int eventId)
	{
		List<UserEvent> userEventList = new ArrayList<UserEvent>();
		for(UserEvent userEvent: addUserEventInterface.findAll()){
			if(userEvent.getEventId() == eventId){
				userEventList.add(userEvent);
			}
		}
		return userEventList;
	}

	@RequestMapping(value="/api/calcMedian", method=RequestMethod.POST, produces=MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody String calcMedian(@RequestBody Eid eid){
		fcmNotificationService = new FcmNotificationService();
		coordinate = new Coordinates();
		calculateMedionService = new CalculateMedianService();
		int eventid = eid.getId();
		List<Coordinates> coordinateList = new ArrayList<Coordinates>();
		List<UserEvent> userEvents;
		userEvents = findSpecificUserEvents(eventid);
		System.out.println("Number of members:"+userEvents.size());
		for(int i=0; i<userEvents.size(); i++){
			coordinate.setLatitude(Double.parseDouble(userEvents.get(i).getLatitude()));
			coordinate.setLongitude(Double.parseDouble(userEvents.get(i).getLongitude()));
			coordinateList.add(coordinate);
		}
		String coor;
		coordinate = calculateMedionService.getMedian(coordinateList);
		if(coordinate.getLatitude()==0.0&& coordinate.getLongitude()==0.0 ){
			coor=Double.toString(39.768597)+","+Double.toString(-86.162682);
		}
		else
		{
			coor= Double.toString(coordinate.getLatitude())+","+Double.toString(coordinate.getLongitude());
		}
		System.out.println(coordinate.getLatitude()+","+coordinate.getLongitude());
		System.out.println(coor);
		HttpURLConnectionExample httpurlconnection = new HttpURLConnectionExample(coor,"5000","restaurant","good");
		try {
				finalres = httpurlconnection.sendGet();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String evid= Integer.toString(eventid); 
		String med[] =finalres.split(",");
		
		for(int i=0; i<userEvents.size(); i++){
			fcmNotificationService.notify("MedionCalculated,"+med[0]+","+med[1]+","+evid, userEvents.get(i).getUserFcmToken());
		}
		
		return finalres;
		
	}
	
	@RequestMapping(value="/api/sendMedian", method=RequestMethod.POST, produces=MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody String sendMedion(@RequestBody EventMedian eventMedion){
		fcmNotificationService = new FcmNotificationService();
		List<UserEvent> userEvents;
		userEvents = findSpecificUserEvents(eventMedion.getEventID());
		for(int i=0; i<userEvents.size(); i++){
			fcmNotificationService.notify("FinalPlace,"+eventMedion.getLatitude()+","+Double.toString(coordinate.getLongitude()), userEvents.get(i).getUserFcmToken());
		}
		
		return null;
		
	}
	
}
