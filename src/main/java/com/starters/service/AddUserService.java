package com.starters.service;

import java.util.ArrayList;
import java.util.List;

import com.starters.bean.User;
import com.starters.inf.AddUserInterface;

public class AddUserService implements AddUserInterface{
	
	private List<User> userList = new ArrayList<User>();

	@Override
	public void addUser(User user) {
		 userList.add(user);
	}

}
