package com.starters.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name ="UserEvents")
public class UserEvent {
	
	@Column(name = "eventId")
	private int eventId;
	@Column(name = "userFcmToken")
	private String userFcmToken;
	@Column(name = "acceptance")
	private boolean acceptance;
	@Column(name = "latitude")
	private double latitude;
	@Column(name = "longitude")
	private double longitude;
	
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
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	

}
