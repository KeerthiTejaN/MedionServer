package com.starters.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlRootElement;

@Entity (name="Users")
public class User implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator="user_id_seq")
	@SequenceGenerator(name="user_id_seq",sequenceName="user_id_seq",allocationSize=1)
	 private int id;
	@Column(name ="name")
	 private String name;
	@Column(name ="email")
	 private String email;
	@Column(name ="phone")
	 private String phone;
	@Column(name ="password")
	 private String password;
	@Column(name ="Fcmtoken")
	 private String fcmToken;
	 
	
	public User()
	{
		
	}
	 public User(String name,String email,String phone,String password,String fcmToken)
	 {
		 super();
		 this.name = name;
		 this.email = email;
		 this.phone=phone;
		 this.password = password;
		 this.fcmToken = fcmToken;
		 
	 }
	 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFcmToken() {
		return fcmToken;
	}
	public void setFcmToken(String fcmToken) {
		this.fcmToken = fcmToken;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", password=" + password
				+ ", fcmToken=" + fcmToken + "]";
	}
	
	 
	
}
