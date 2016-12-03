package com.starters.model;

import java.util.ArrayList;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Event {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY,generator="user_id_seq")
		private int id;
	 	private String eventName;
	    private String eventData;
	    private String eventTime;
	    private ArrayList<String> memberList;
	
	    public String getEventName() {
	        return eventName;
	    }
	
	    public void setEventName(String eventName) {
	        this.eventName = eventName;
	    }
	
	    public String getEventData() {
	        return eventData;
	    }
	
	    public void setEventData(String eventData) {
	        this.eventData = eventData;
	    }
	
	    public String getEventTime() {
	        return eventTime;
	    }
	
	    public void setEventTime(String eventTime) {
	        this.eventTime = eventTime;
	    }
	
	    public ArrayList<String> getMemberList() {
	        return memberList;
	    }
	
	    public void setMemberList(ArrayList<String> memberList) {
	        this.memberList = memberList;
	    } 
}
