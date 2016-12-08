package com.starters.model;

public class UserEvent {
	
	private int eventId;
	private String userFcmToken;
	private boolean acceptance;
	private double latitude;
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
