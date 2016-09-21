package com.aim.frameworkSQLDB;
import java.sql.*;
public class CreateNewTable {
	
	public static Statement stmt=null;
	
	public static void createTable()
	{
		try
		{
		CreateNewDB.CreateDB();
		stmt=CreateNewDB.conn.createStatement();
		 String sql = "CREATE TABLE MasterTestSuite " +
                 "(TSID           TEXT    NOT NULL, " + 
                 " Description    TEXT     , " + 
                 " Runmode        CHAR(50) )"; 
		 
		 String sql1="CREATE TABLE UserLogin " +
                 "(UID INTEGER PRIMARY KEY   AUTOINCREMENT, " + 
                 " username    TEXT     , " + 
                 " password    TEXT     , " + 
                 " email    TEXT     , " +
                 " active        INT )"; 
		 
		 String sql2="CREATE TABLE TestCase_Master " +
                 "(TCID INTEGER PRIMARY KEY   AUTOINCREMENT, " + 
                 " release_name    TEXT     , " + 
                 " tc_name    TEXT     , " + 
                 " UIName    TEXT ," +
                 " Locator    TEXT ," +
                 " Locator_Type    TEXT ," +
                 " Activity    TEXT ," +
                 " Data    TEXT )";
		 
		 String sql3="CREATE TABLE TestData_Master " +
                 "(TDID INTEGER  AUTOINCREMENT, " + 
                 " release_name    TEXT     , " + 
                 " tc_name    TEXT     , " + 
                 " runmode    TEXT )";
		 
		 String sql4="CREATE TABLE BusinessFunction " +
                 "(groupname    TEXT     , " + 
                 " methodname    TEXT     , " + 
                 " description    TEXT )";
		 
               
		String altsql="ALTER TABLE TestCase_Master ADD COLUMN Data_Flag TEXT";
		 
		
    stmt.executeUpdate(sql4);
    stmt.close();
    CreateNewDB.conn.close();
		}
		catch(Exception e)
		{
			 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
			
		}
		
		
	}

	public static void main(String[] args) {
	
		CreateNewTable.createTable();

	}

}
