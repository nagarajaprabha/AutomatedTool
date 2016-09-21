package com.aim.frameworkSQLDB;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

public class GetTestDataTable {

	static Statement stmt=null;
	static ResultSet rs1=null,rs2=null,rs3=null;
	static Map < Integer, Object[] > tp;
	static Object[] obj;
	static ArrayList<Object> al;
	static ResultSetMetaData metadata=null;
	static JTable dtable=null;
	static DefaultTableModel dtableModel=null;
	
	
	
	public static JTable getTestData(String TestData) 
	{
		al=getColumnCountList(TestData);
		 obj=al.toArray();
			dtableModel=new DefaultTableModel(obj,0);
			dtable=new JTable();
			dtable.setEnabled(true);
			
			
		
			
		try
		{
		dtableModel.setNumRows(0);
		CreateNewDB.CreateDB();
		stmt=CreateNewDB.conn.createStatement();
		 
			rs1=stmt.executeQuery("SELECT * FROM  " +TestData);
		   // rs3=stmt.executeQuery("SELECT COUNT(*) FROM  " +TestData);
			
			
				
			
				if (dtableModel.getRowCount() > 0) {
				    for (int i = dtableModel.getRowCount() - 1; i > -1; i--) {
				    	dtableModel.removeRow(i);
				    }
				}
				
				
				int i=1;	
				
					while(rs1.next())
					{
						
					System.out.println("Hii"+TestData+rs1.getString(1));
					dtableModel.setNumRows(i);
					// dtableModel.addRow(new Object[]{rs1.getString(1)});
					for(int j=1;j<=al.size();j++)
					{
						
					dtableModel.setValueAt(rs1.getString(j),i-1,j-1);
					}
										
					 i++;
						
					 	}	
				
					
					dtableModel.fireTableDataChanged();
				
					
		rs1.close();
		stmt.close();
		CreateNewDB.conn.close();
		
		}
		catch(Exception e)
		{
		System.out.println(e.getMessage());	
				}
		
		
		dtable.setModel(dtableModel);
		dtable.tableChanged(new TableModelEvent(dtable.getModel())) ;

		return dtable;	
		
		
	}

	public static void addTestDataRow(String rdata,String rname)
	{
		
		dtableModel.addRow(new Object[]{rdata,rname,""});
		dtable.setEnabled(true);
		dtable.setModel(dtableModel);
		dtable.tableChanged(new TableModelEvent(dtable.getModel())) ;
	}
	
	public static void deletteDataRow()
	{
		
		dtableModel.removeRow(dtable.getSelectedRow());;
		
		
	}

	public static ArrayList getColumnCountList(String TestData)
	{
		
		try
		{
			
		CreateNewDB.CreateDB();
		stmt=CreateNewDB.conn.createStatement();
		al=new ArrayList<Object>();
		
		
		rs2=stmt.executeQuery("SELECT * FROM  " +TestData);
		metadata = rs2.getMetaData();

		int columnCount = metadata.getColumnCount();

		for (int i=1; i<=columnCount; i++) {

			 String columnName = metadata.getColumnName(i);
			 al.add(columnName);
			
		
		 }	
	
		
		stmt.close();
		rs2=null;
		rs2.close();
		rs3=null;
		rs3.close();
		CreateNewDB.conn.close();
			
		}
		catch(Exception e)
		{
		System.out.println(e.getMessage());	
			
		}
		
	
	return al;
		
	}

	
	
	
}
