package com.starters.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.starters.inf.AddUserInterface;
import com.starters.model.User;



@Service
public class LoginService {

private final AddUserInterface addUserInterface;

//	EntityManagerFactory entityFactory = Persistence
//			.createEntityManagerFactory("test");
//	EntityManager entityManager = entityFactory.createEntityManager();
	
	public LoginService(AddUserInterface adduserinterface)
	{
		this.addUserInterface = adduserinterface;
	}

	public String loginCheck(String phone, String password){
//		String select = "SELECT u FROM User u WHERE ua.phone=:phone and ua.password=:password";
//
//		Query query = (Query) entityManager.createQuery(select);
//		((javax.persistence.Query) query).setParameter("phone", phone);
//		((javax.persistence.Query) query).setParameter("password", password);
//
//		User u = (User) ((javax.persistence.Query) query).getSingleResult();
		

		for(User user:addUserInterface.findAll())
		{
			if(user.getPhone().equals(phone)&&user.getPassword().equals(password))
			{
				return "Valid User";
			}
		}
		return "User doesn't exist!";
	}
}
