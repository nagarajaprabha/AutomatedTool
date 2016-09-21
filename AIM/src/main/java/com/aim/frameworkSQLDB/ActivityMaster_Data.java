package com.aim.frameworkSQLDB;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
public class ActivityMaster_Data {

	static Statement stmt=null;
	static ResultSet rs;
	static int i=0;
	static Map < Integer, Object[] > tp;
	static ArrayList<Object> al=null;
	public static Map < Integer, Object[] >  getTableData()
	{
		try
		{
		CreateNewDB.CreateDB();
		CreateNewDB.conn.setAutoCommit(false);
		stmt=CreateNewDB.conn.createStatement();
		
		rs=stmt.executeQuery("SELECT * FROM Activity_Master");
		tp=new TreeMap <Integer, Object[]>();
		
		while(rs.next())
		{
				
					
					 tp.put( i, new Object[] {rs.getString("objecttype"),rs.getString("method"), rs.getString("keyword")});
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
	
	public static ArrayList getKeywords()
	{
		
		try
		{
		CreateNewDB.CreateDB();
		CreateNewDB.conn.setAutoCommit(false);
		stmt=CreateNewDB.conn.createStatement();
		
		rs=stmt.executeQuery("SELECT * FROM Activity_Master");
		al=new ArrayList<Object>();
		al.add("");
		while(rs.next())
		{
				
					
					 al.add(rs.getString("keyword"));
			
		}	
	
		rs.close();
		stmt.close();
		CreateNewDB.conn.close();
			
		}
		catch(Exception e)
		{
		System.out.println(e.getMessage());	
			
		}
		
		return al;
		
		
	}
	
	
	
	}