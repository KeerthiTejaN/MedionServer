package com.starters.inf;

import org.springframework.data.repository.CrudRepository;

import com.starters.model.Event;

public interface AddEventInterface extends CrudRepository<Event,Integer>{

}