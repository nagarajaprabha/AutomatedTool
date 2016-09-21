package com.aim.frameworkSQLDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class Test {
	static PreparedStatement pstmt=null;
	public static void main(String[] args) {
		
		Connection c = null;
	   Statement stmt = null;
		
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:AutomatonFramework.db");
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");

	   
	
//String sql="ALTER TABLE TestCase_Master ADD RUNMODE TEXT;";
	
	  
	
	    // String sql= "INSERT INTO TestCase_Master(release_name,tc_name,UIName,Locator,Locator_Type,Activity,Data) " +
	             //   "VALUES (?,?,?,?,?,?,?)"; 
	     
	    String sql= "INSERT INTO UserLogin(username,password,email,active) " +
	                "VALUES ('test','test','test@gmail.com',1)"; 
 				
	    stmt = c.createStatement();
  
stmt.executeUpdate(sql);		
	 c.commit();
		/*
	 
	ResultSet rs=stmt.executeQuery("SELECT * FROM UserLogin;");

while(rs.next())
{
System.out.println(rs.getString(1));
System.out.print(rs.getString(2));
System.out.print(rs.getString(3));
System.out.print(rs.getString(4));
System.out.print(rs.getString(5));	
	
}
 			*/
	 
	      stmt.close();
	    
	      c.close();
	    } 
	    catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	     
	    }
	    System.out.println("Operation done successfully");
	  }

	

}
