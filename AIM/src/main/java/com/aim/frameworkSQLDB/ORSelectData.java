package com.aim.frameworkSQLDB;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

public class ORSelectData {
	static Statement stmt=null;
	static ResultSet rs;
	static int i=0;
	static Map < Integer, Object[] > tp;
	public static Map < Integer, Object[] >  getTableData(String ordata)
	{
		try
		{
		CreateNewDB.CreateDB();
		CreateNewDB.conn.setAutoCommit(false);
		stmt=CreateNewDB.conn.createStatement();
		
		rs=stmt.executeQuery("SELECT * FROM " + ordata);
		tp=new TreeMap <Integer, Object[]>();
		
		while(rs.next())
		{
				
					
					 tp.put( i, new Object[] {rs.getString("uiname"),rs.getString("uitype"), rs.getString("locator"),rs.getString("locatortype")});
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
}
