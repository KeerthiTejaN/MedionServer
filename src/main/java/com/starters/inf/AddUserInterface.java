package com.starters.inf;

import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.config.Task;

import com.starters.model.User;

public interface AddUserInterface extends CrudRepository<User,Integer>{

	//public void addUser(User user);
}
