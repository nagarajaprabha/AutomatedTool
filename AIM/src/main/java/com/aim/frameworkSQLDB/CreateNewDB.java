package com.aim.frameworkSQLDB;

import java.sql.*;

public class CreateNewDB {
	
	public static Connection conn=null;
	
	public static Connection getConn() throws Exception {
	    if(conn == null){
	    Class.forName("org.sqlite.JDBC");
	    conn=DriverManager.getConnection("jdbc:sqlite:AutomatonFramework.db");
	    }
	    return conn;
	    }
	public static void CreateDB()
	{
		
		try
		{
			Class.forName("org.sqlite.JDBC");
			conn=DriverManager.getConnection("jdbc:sqlite:AutomatonFramework.db");
		
				
	   } catch ( Exception e ) 
		{
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
		
		//System.out.println("Database Created Successfully");
	}

	public static void main(String[] args)
	{
		CreateNewDB.CreateDB();
		

	}

}
