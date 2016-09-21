package com.aim.frameworkSQLDB;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

public class TestExecution {
	
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
		
		
		
		rs=stmt.executeQuery("SELECT DISTINCT(tc_name),RUNMODE FROM  TestCase_Master WHERE release_name='" + tdata + "' ");
		tp=new TreeMap <Integer, Object[]>();
		
		while(rs.next())
		{
							
					 tp.put( i, new Object[] {rs.getString("tc_name"),rs.getString("RUNMODE")});
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
