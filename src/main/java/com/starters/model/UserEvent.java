package com.starters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name ="userevents")
public class UserEvent {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator="userevent_id_seq")
	@SequenceGenerator(name="userevent_id_seq",sequenceName="userevent_id_seq",allocationSize=1)
	private int id;
	@Column(name = "eventid")
	private int eventId;
	@Column(name = "userfcmtoken")
	private String userFcmToken;
	@Column(name = "acceptance")
	private boolean acceptance;
	@Column(name = "latitude")
	private String latitude;
	@Column(name = "longitude")
	private String longitude;
	
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public String getUserFcmToken() {
		return userFcmToken;
	}
	public void setUserFcmToken(String userFcmToken) {
		this.userFcmToken = userFcmToken;
	}
	public boolean isAcceptance() {
		return acceptance;
	}
	public void setAcceptance(boolean acceptance) {
		this.acceptance = acceptance;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	

}
