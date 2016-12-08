package com.starters.inf;

import org.springframework.data.repository.CrudRepository;

import com.starters.model.UserEvent;

public interface AddUserEventInterface extends CrudRepository<UserEvent, Integer>{

}
