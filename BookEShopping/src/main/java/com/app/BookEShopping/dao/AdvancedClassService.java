package com.app.BookEShopping.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.app.BookEShopping.BookDetails;

public class AdvancedClassService 
{

public List<BookDetails> AdvancedSearchBook(String category)
{
List<BookDetails> booklist=new ArrayList<BookDetails>();

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
//"select LastModified from CacheTable where url = '" + url +"'"
//'"+firstName+"','"+lastName+"','"+dob+"','"+gender+"','"+contactNo+"','"+email+"','"+userCategory+"','"+userId+"','"+password+"'
//"select * from  logindetails where userId = '"+userId+"'"
ResultSet myRs=mystmt.executeQuery("select * from  bookdetails where bookcategory = '"+category+"'");
while (myRs.next()) 
{
BookDetails book=new BookDetails();
book.setBookName(myRs.getString("bookName"));
book.setBookCode(myRs.getString("bookCode"));
book.setBookDesc((myRs.getString("bookDesc")));
book.setAuthor((myRs.getString("author")));
book.setBookcategory(myRs.getString("bookcategory"));
book.setBooktype(myRs.getString("booktype"));
book.setBuyingoption(myRs.getString("buyingoption"));
book.setBookrate((myRs.getString("bookrate")));
book.setDiscount((myRs.getInt("discount")));
booklist.add(book);
}

}
catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
} catch (ClassNotFoundException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
return booklist;
}
}


