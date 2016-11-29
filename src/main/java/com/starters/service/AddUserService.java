package com.starters.service;

import java.util.ArrayList;
import java.util.List;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import com.starters.bean.User;
import com.starters.inf.AddUserInterface;

public class AddUserService implements AddUserInterface{
	
	private static List<User> userList = new ArrayList<User>();
	
	 public static void main(String[] args) throws Exception {
	        
	        Connection connection = getConnection();
	        
	        Statement stmt = connection.createStatement();
	        
	        //stmt.executeUpdate("CREATE TABLE USERS(id SERIAL,name varchar(30), email varchar(30), phone integer,password varchar(30), Fcmtoken varchar(40));");
	        //stmt.executeUpdate("INSERT INTO USERS VALUES(2,'keerthi','keenuthi@gmail.com',123456789,'password123','asfdga456');");
	        for(int i=0;i<userList.size();i++)
	        {
	        	User u = userList.get(i);
	        	String name =u.getName();
	        	String email = u.getEmail();
	        	String password= u.getPassword();
	        	int phone = Integer.parseInt(u.getPhone());
	        	String Fcmtoken = u.getFcmToken();
	        	int id =i+2;
	        	stmt.executeUpdate("INSERT INTO USERS VALUES("+id+","+name+","+email+","+phone+","+password+","+Fcmtoken+");");
	        }
	        
	    }
	    
	 	private static Connection getConnection() throws URISyntaxException, SQLException {

		    String username = "qxmdagnvxyyqkb";
		    String password = "ylIoOtLJxDe-xo6JwZpH1OusVe";
		    String dbUrl = "jdbc:postgresql://" + "ec2-23-23-225-158.compute-1.amazonaws.com"+ ':' + "5432" + "/d4t56k004hd5vk"+"?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";

		    return DriverManager.getConnection(dbUrl, username, password);
		}

	@Override
	public void addUser(User user) {
		 userList.add(user);
	}

}
