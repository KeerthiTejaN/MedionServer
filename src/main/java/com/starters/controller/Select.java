package com.starters.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Select {
	
	@Autowired
	private DataSource dataSource;
	Connection conn = null;
	
	public void printDatabase(){
		try{
			System.out.println("print DB");
			conn =  dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT * from meetupApp");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				System.out.println(rs);
			}
			rs.close();
			ps.close();
		}catch(Exception e){
			
		}
		finally{
			try{
				conn.close();
			}catch(Exception e){
				
			}
		}
	}

}
