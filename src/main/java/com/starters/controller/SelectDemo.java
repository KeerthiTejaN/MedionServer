package com.starters.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SelectDemo {

	public static void main(String[] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		Select s = ctx.getBean("Select", Select.class);
		System.out.println("Main ");
		s.printDatabase();
	}

}
