package com.aim.frameworkSQLDB;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

public class TestCasesList {
	
	static Statement stmt=null;
	static ResultSet rs;
	static int i=0;
	static Map < Integer, Object[] > tp,tc;
	
	public static Map < Integer, Object[] >  getTableData(String tdata)
	{
		try
		{
			
		CreateNewDB.CreateDB();
		stmt=CreateNewDB.conn.createStatement();
		
		
		
		rs=stmt.executeQuery("SELECT DISTINCT(tc_name),RUNMODE FROM  TestCase_Master WHERE release_name='" + tdata + "' AND Data_Flag='Yes'");
		tp=new TreeMap <Integer, Object[]>();
		
		while(rs.next())
		{
							
					 tp.put( i, new Object[] {rs.getString("tc_name")});
					 i++;
			
		}	
	
	rs.close();
		stmt.close();
		CreateNewDB.conn.close();
			
		}
		catch(Exception e)
		{
		System.out.println(e.getMessage());	
			
		}
		
		return tp;
		
	}
	public static Map < Integer, Object[] >  getCompleteData(String tdata)
	{
		try
		{
			
		CreateNewDB.CreateDB();
		stmt=CreateNewDB.conn.createStatement();
		
		
		
		rs=stmt.executeQuery("SELECT * FROM  TestCase_Master WHERE release_name='" + tdata + "'");
		tc=new TreeMap <Integer, Object[]>();
		
		while(rs.next())
		{
							
					 tc.put( i, new Object[] {rs.getString("release_name"),rs.getString("tc_name"),rs.getString("UIName"),rs.getString("Locator"),rs.getString("Locator_Type"),rs.getString("Activity"),rs.getString("Data"),rs.getString("RUNMODE"),rs.getString("Data_Flag")});
					 i++;
			
		}	
	
		rs.close();
		stmt.close();
		CreateNewDB.conn.close();
			
		}
		catch(Exception e)
		{
		System.out.println(e.getMessage());	
			
		}
		
		return tc;
		
	}

}
