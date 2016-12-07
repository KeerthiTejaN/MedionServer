package com.starters.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.starters.controller.EventManagementController;
import com.starters.inf.AddUserInterface;
import com.starters.model.User;


@Service
@Transactional
public class AddUserService{
	
	private final AddUserInterface adduserinterface;
	

//	private EventManagementController eventManagementController = new EventManagementController();
//	private List<String> members = eventManagementController.getCurrentMemberList();
	
	public AddUserService(AddUserInterface adduserinterface)
	{
		this.adduserinterface = adduserinterface;
	}
	
	public List<User> findAll()
	{
		List<User> users = new ArrayList<>();
		for(User user:adduserinterface.findAll())
		{
			users.add(user);
		}
		return users;
	}
	
	public List<String> findAllFcmTokens(List<String> members)
	{
		List<String> fcms = new ArrayList<>();
		for(User user:adduserinterface.findAll())
		{
			for(int i=0;i<members.size();i++){
				if(members.get(i) ==user.getPhone())
				{
					fcms.add(user.getFcmToken());
				}
			
			}
		}
		return fcms;
	}
	
	public void save(User user)
	{
		adduserinterface.save(user);
	}
	
	public void delete(int id)
	{
		adduserinterface.delete(id);
	}

}
