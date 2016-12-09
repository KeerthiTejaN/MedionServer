package com.starters.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.starters.inf.AddUserEventInterface;
import com.starters.model.Coordinates;
import com.starters.model.UserEvent;
import com.starters.service.CalculateMedianService;


public class MedionController {
	
	@Autowired
	AddUserEventInterface addUserEventInterface;
	
	@Autowired
	Coordinates coordinate;
	
	@Autowired
	CalculateMedianService calculateMedionService;
	
	UserEvent userEvent;
	
	public <UserEvent> ArrayList<UserEvent> makeCollection(Iterable<UserEvent> iter) {
    ArrayList<UserEvent> list = new ArrayList<UserEvent>();
    for (UserEvent item : iter) {
        list.add(item);
    }
    return list;
}

	@RequestMapping(value="/api/calcMedian", method=RequestMethod.POST, produces=MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody String calcMedian(@RequestBody int eventID){
		List<Coordinates> coordinateList = new ArrayList<Coordinates>();
		List<Integer> input = new ArrayList<Integer>();
		input.add(eventID);
		addUserEventInterface.findAll(input);
		ArrayList<UserEvent> userEvents;
		userEvents = makeCollection(addUserEventInterface.findAll());
		for(int i=0; i<userEvents.size(); i++){
			coordinate.setLatitude(Double.parseDouble(userEvents.get(i).getLatitude()));
			coordinate.setLongitude(Double.parseDouble(userEvents.get(i).getLongitude()));
			coordinateList.add(coordinate);
		}
		coordinate = calculateMedionService.getMedian(coordinateList);
		
		
		return "MedianCalculated";
	}
	
}
