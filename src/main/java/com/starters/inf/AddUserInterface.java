package com.starters.inf;

import org.springframework.data.repository.CrudRepository;

import com.starters.model.User;

public interface AddUserInterface extends CrudRepository<User,Integer>{

}
