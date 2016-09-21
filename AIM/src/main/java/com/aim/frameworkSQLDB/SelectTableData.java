package com.aim.frameworkSQLDB;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class SelectTableData {

	static Statement stmt=null;
	static ResultSet rs;
	static int i=0;
	static ArrayList<Object> al;
	static Map < Integer, Object[] > tp;
	public static Map < Integer, Object[] >  getTableData()
	{
		try
		{
		CreateNewDB.CreateDB();
		CreateNewDB.conn.setAutoCommit(false);
		stmt=CreateNewDB.conn.createStatement();
		
		rs=stmt.executeQuery("SELECT * FROM MasterTestSuite");
		tp=new TreeMap <Integer, Object[]>();
		al=new ArrayList<Object>();
		while(rs.next())
		{
					
					
					 tp.put( i, new Object[] {rs.getString("TSID"),rs.getString("Description"), rs.getString("Runmode")});
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
