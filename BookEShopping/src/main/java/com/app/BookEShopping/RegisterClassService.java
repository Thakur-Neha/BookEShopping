package com.app.BookEShopping;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class RegisterClassService {
	
	
	public String LoginValidation(String userId,String password){
		String usercategory="";
		Connection conn=null;
		Statement mystmt = null;
		String dburl = "jdbc:mysql://localhost:3306/bookshopping?useSSL=false";
		String user = "root";
		String passw = "root";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dburl, user, passw);
			mystmt=conn.createStatement();
			//"select LastModified from CacheTable where url = '" + url +"'"
			ResultSet myRs=mystmt.executeQuery("select * from  logindetails where userId = '"+userId+"'");
			
			if((myRs.next())){
				String p=myRs.getString("pass");
				if(p.equals(password)){
					usercategory+=myRs.getString("userCategory");
					System.out.println(usercategory);
				}
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usercategory;
		
	}
	


	public void adddata(String firstName, String lastName, Date dob, String gender, String contactNo, String email,
			String userCategory, String userId, String password) {
		//RegisterClassService rsobj = new RegisterClassService();
		System.out.println(firstName);
		Connection conn=null;
		Statement mystmt = null;
		String dburl = "jdbc:mysql://localhost:3306/bookshopping?useSSL=false";
		String user = "root";
		String pass = "root";
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dburl, user, pass);
			mystmt = conn.createStatement();
//			int ra = mystmt.executeUpdate("insert into logindetails"
//					+ "(firstName,lastName,dob,gender,contactNo,email,userCategory,userId,pass)" + 
//					"value"
//					+ "(firstName,lastName,dob,gender,contactNo,email,userCategory,userId,'hj')");
			
			mystmt.executeUpdate("INSERT INTO `logindetails`(firstName,lastName,dob,gender,contactNo,email,userCategory,userId,pass) "
					+ "VALUE ('"+firstName+"','"+lastName+"','"+dob+"','"+gender+"','"+contactNo+"','"+email+"','"+userCategory+"','"+userId+"','"+password+"')");
			//("INSERT INTO `time_entry`(pid,tid,rid,tspend,description) VALUE ('"+pid+"','"+tid+"','"+rid+"',"+tspent+",'"+des+"')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String generateuserId() {
		Random n = new Random();
		int ui = (int) (n.nextDouble() * 1000000);

		String userid = Integer.toString(ui);
		return userid;
	}

}