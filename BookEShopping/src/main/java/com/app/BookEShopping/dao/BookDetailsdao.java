package com.app.BookEShopping.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.app.BookEShopping.BookDetails;

public class BookDetailsdao {
	
	public String sendbookpriceaddtocart(String bookname){
		double price=0.0;
		
		String data="";
		Connection conn=null;

		Statement mystmt = null;

		String dburl = "jdbc:mysql://localhost:3306/bookshopping?useSSL=false";

		String user = "root";

		String pass = "root";
		try{
			conn = DriverManager.getConnection(dburl, user, pass);
			mystmt=conn.createStatement();

			//"select LastModified from CacheTable where url = '" + url +"'"

			ResultSet myRs=mystmt.executeQuery("select * from  bookdetails where bookname = '"+bookname+"'");
			if(myRs.next()){
				double mrp=Integer.parseInt(myRs.getString("bookrate"));
				int dis=myRs.getInt("discount");
				if(dis>0){
					price=(mrp*dis)/100;
				}
				else{
					price=mrp;
				}
				
				String quan=Integer.toString(myRs.getInt("noofcopiesavailable"));
				
				data+=Double.toString(price)+","+quan;
				
			}
			
			
			
			conn.close();
			mystmt.close();

		}catch (SQLException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}
		return data;

	}
	
	public void addtocartdatabase(String userid,String bookname,double bookprice,int quanity,String rentbuy,String deliveryAddress){
		
		Connection conn=null;

		Statement mystmt = null;

		String dburl = "jdbc:mysql://localhost:3306/bookshopping?useSSL=false";

		String user = "root";

		String pass = "root";
		try{
			conn = DriverManager.getConnection(dburl, user, pass);
			mystmt = conn.createStatement();
			

			mystmt.executeUpdate("INSERT INTO `addtocart`(userid,bookname,bookprice,quanity,rentbuy,deliveryAddress) "

					+ "VALUE ('"+userid+"','"+bookname+"','"+bookprice+"','"+quanity+"','"+rentbuy+"','"+deliveryAddress+"')");
			
			conn.close();
			mystmt.close();

			
		}catch (SQLException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		
	}
	
	public void addcarddetails(String bookname,String cardnumber,String cvv,Date expirydate){
		Connection conn=null;

		Statement mystmt = null;

		String dburl = "jdbc:mysql://localhost:3306/bookshopping?useSSL=false";

		String user = "root";

		String pass = "root";
		try{
			conn = DriverManager.getConnection(dburl, user, pass);
			mystmt = conn.createStatement();
			

			mystmt.executeUpdate("INSERT INTO `carddetails`(cardnumber,cvv,expirydate) "

					+ "VALUE ('"+cardnumber+"','"+cvv+"','"+expirydate+"')");
			
			conn.close();
			mystmt.close();
			
		}catch (SQLException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		
	}

	public void addbookdata(String bookName, String bookCode, String bookDesc, String author, String bookcategory,
			String booktype, String buyingoption, String bookrate, int discount,int copies,int sold,int retu) {
		
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

			

			mystmt.executeUpdate("INSERT INTO `bookdetails`(bookName,bookCode,bookDesc,author,bookcategory,booktype,buyingoption,bookrate,discount,noofcopiesavailable,noofcopiessold,noofcopiesreturned) "

					+ "VALUE ('"+bookName+"','"+bookCode+"','"+bookDesc+"','"+author+"','"+bookcategory+"','"+booktype+"','"+buyingoption+"','"+bookrate+"','"+discount+"','"+copies+
					"','"+sold+"','"+retu+
					"')");

			//("INSERT INTO `time_entry`(pid,tid,rid,tspend,description) VALUE ('"+pid+"','"+tid+"','"+rid+"',"+tspent+",'"+des+"')");
			conn.close();
			mystmt.close();

		} catch (SQLException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		} catch (ClassNotFoundException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		// TODO Auto-generated method stub
		
	}
	
	public void ordered(String bookname,int quantity){
		Connection conn=null;

		Statement mystmt = null;

		String dburl = "jdbc:mysql://localhost:3306/bookshopping?useSSL=false";

		String user = "root";

		String pass = "root";
		try{
			conn = DriverManager.getConnection(dburl, user, pass);
			mystmt=conn.createStatement();

			//"select LastModified from CacheTable where url = '" + url +"'"

			ResultSet myRs=mystmt.executeQuery("select * from  bookdetails where bookname = '"+bookname+"'");
			if(myRs.next()){
				
				int quan=myRs.getInt("noofcopiesavailable");
				quan=quan-quantity;
				int myRsu=mystmt.executeUpdate("update bookdetails set noofcopiesavailable = '"+quan+"'"+"where bookname ='"+bookname+"'");
				int myRsui=mystmt.executeUpdate("update bookdetails set noofcopiessold = '"+quantity+"'"+"where bookname ='"+bookname+"'");
			}
			
			
			
			conn.close();
			mystmt.close();

		}catch (SQLException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}
		
	}
	public BookDetails searchbookbycode(String bookcode){
		BookDetails book=new BookDetails();
		Connection conn=null;
		Statement mystmt = null;
		String dburl = "jdbc:mysql://localhost:3306/bookshopping?useSSL=false";
		String user = "root";
		String passw = "root";
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(dburl, user, passw);
		mystmt=conn.createStatement();
		ResultSet myRs=mystmt.executeQuery("select * from  bookdetails where bookCode = '"+bookcode+"'");
		if (myRs.next()) 
		{
			
			book.setBookName(myRs.getString("bookName"));
			book.setBookCode(myRs.getString("bookCode"));
			book.setBookDesc((myRs.getString("bookDesc")));
			book.setAuthor((myRs.getString("author")));
			book.setBookcategory(myRs.getString("bookcategory"));
			book.setBooktype(myRs.getString("booktype"));
			book.setBuyingoption(myRs.getString("buyingoption"));
			book.setBookrate((myRs.getString("bookrate")));
			book.setDiscount((myRs.getInt("discount")));
			book.setNoofcopiesavailable(myRs.getInt("noofcopiesavailable"));
			book.setNoofcopiesreturned(myRs.getInt("noofcopiesreturned"));
		}

		}
		catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return book;
		}

	public void bookupdate(String bookcode, String rate, String dis, String available, String returned) {
		// TODO Auto-generated method stub
		Connection conn=null;

		Statement mystmt = null;

		String dburl = "jdbc:mysql://localhost:3306/bookshopping?useSSL=false";

		String user = "root";

		String pass = "root";
		try{
			conn = DriverManager.getConnection(dburl, user, pass);
			mystmt=conn.createStatement();

			//"select LastModified from CacheTable where url = '" + url +"'"

			ResultSet myRs=mystmt.executeQuery("select * from  bookdetails where bookcode = '"+bookcode+"'");
			if(myRs.next()){
				
				mystmt.executeUpdate("update bookdetails set bookrate = '"+rate+"'"+"where bookCode ='"+bookcode+"'");
				mystmt.executeUpdate("update bookdetails set discount = '"+dis+"'"+"where bookCode ='"+bookcode+"'");
				mystmt.executeUpdate("update bookdetails set noofcopiesavailable = '"+available+"'"+"where bookCode ='"+bookcode+"'");
				mystmt.executeUpdate("update bookdetails set noofcopiesreturned = '"+returned+"'"+"where bookCode ='"+bookcode+"'");
				
			}
			
			
			
			conn.close();
			mystmt.close();

		}catch (SQLException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}
		
		
	}
	
	
	
	
	}
	
	
		
		
	


