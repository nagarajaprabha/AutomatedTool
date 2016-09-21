package com.aim.frameworkSQLDB;

import java.sql.Statement;

public class InsertData {
	
	public static Statement stmt=null;
	
	public static void putData()
	{
		try
		{
			CreateNewDB.CreateDB();
			CreateNewDB.conn.setAutoCommit(false);
			
			 stmt = CreateNewDB.conn.createStatement();
			 /*
		   String sql = "INSERT INTO MasterTestSuite(TSID,Description,Runmode) " +
		                "VALUES ('SGXRegression', 'SGX Regression Suite', 'Yes' );"; 
		     stmt.executeUpdate(sql);
		     sql = "INSERT INTO MasterTestSuite(TSID,Description,Runmode) " +
		                "VALUES ('UBS', 'UBS Regression Suite', 'Yes' );"; 
		     stmt.executeUpdate(sql);
		     sql = "INSERT INTO MasterTestSuite(TSID,Description,Runmode) " +
		                "VALUES ('Allianz', 'Allianz Regression Suite', 'Yes' );"; 
		     stmt.executeUpdate(sql);
		     
		     */
			 
			 String   sql = "INSERT INTO Activity_Master(objecttype,method,keyword) " +
		                "VALUES ('TextField', 'sendkeys', 'writeInput' );"; 
		     stmt.executeUpdate(sql);
		     
		     sql = "INSERT INTO Activity_Master(objecttype,method,keyword) " +
		                "VALUES ('TextField', 'click', 'clickTextField' );"; 
		     stmt.executeUpdate(sql);
		     sql = "INSERT INTO Activity_Master(objecttype,method,keyword) " +
		                "VALUES ('TextField', 'clear', 'clearTextField' );"; 
		     stmt.executeUpdate(sql);
		     sql = "INSERT INTO Activity_Master(objecttype,method,keyword) " +
		                "VALUES ('Button', 'click', 'clickButton' );"; 
		     stmt.executeUpdate(sql);
		    
             // stmt.executeUpdate(sql);
              
              stmt.close();
              CreateNewDB.conn.commit();
              CreateNewDB.conn.close();
			
		}
		catch(Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
			
		}
		 System.out.println("Records created successfully");
	}

	public static void main(String[] args) {
		
		InsertData.putData();


	}

}
