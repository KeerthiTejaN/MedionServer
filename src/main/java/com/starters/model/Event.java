package com.starters.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity(name ="Events")
public class Event {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY,generator="event_id_seq")
		@SequenceGenerator(name="event_id_seq",sequenceName="event_id_seq",allocationSize=1)
		private int id;
		@Column(name = "eventname")
	 	private String eventName;
	    @Column(name = "eventdate")
		private String eventDate;
	    @Column(name = "eventtime")
	    private String eventTime;
	    @Column(name = "memberlist")
	    private String memberList;
	
	    
	    public int getId() {
			return id;
		}
		public String getEventName() {
	        return eventName;
	    }
	
	    public void setEventName(String eventName) {
	        this.eventName = eventName;
	    }
	
	    public String getEventDate() {
	        return eventDate;
	    }
	
	    public void setEventDate(String eventData) {
	        this.eventDate = eventData;
	    }
	
	    public String getEventTime() {
	        return eventTime;
	    }
	
	    public void setEventTime(String eventTime) {
	        this.eventTime = eventTime;
	    }
	
	    public String getMemberList() {
	        return memberList;
	    }
	
	    public void setMemberList(String memberList) {
	        this.memberList = memberList;
	    } 
}
