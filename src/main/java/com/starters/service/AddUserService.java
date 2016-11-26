package com.starters.service;

import java.util.ArrayList;
import java.util.List;

import com.starters.bean.User;

public class AddUserService {
	
	private List<User> userList = new ArrayList<User>();
	
	 public void add(User user){
		 userList.add(user);
	 }

}
