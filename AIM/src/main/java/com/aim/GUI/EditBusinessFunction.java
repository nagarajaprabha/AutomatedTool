package com.aim.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


import com.aim.frameworkSQLDB.ActivityMaster_Data;
import com.aim.frameworkSQLDB.BusinessMethods;
import com.aim.frameworkSQLDB.CreateNewDB;
import com.aim.frameworkSQLDB.ORSelectData;
import com.aim.frameworkSQLDB.TestCasesList;

import javax.swing.JLabel;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class EditBusinessFunction extends JFrame {

	static ArrayList<Object> gdata;
	private static JPanel contentPane;
	static JTable tb,dataTable;
	static DefaultTableModel md=null,testDataModel=null;
	static Dimension screenSize=null;
	static Map < Integer, Object[] > tp=null,testData=null;;
	static JComboBox gname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditBusinessFunction frame = new EditBusinessFunction();
					frame.setVisible(true);
					EditBusinessFunction.JTableData();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	


	public static void getData()
	{
		
		{
			Connection c = null;
			   Statement stmt = null;
			   ResultSet rs =null;
			   gdata=new ArrayList<Object>();
			   gdata.add("");
			try
			{
				
				//gdata.clear();
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:AutomatonFramework.db");
		      c.setAutoCommit(false);
				
				 stmt = c.createStatement();
						 
		 
			   rs=stmt.executeQuery("SELECT DISTINCT(groupname) FROM BusinessFunction");
			   
			   while(rs.next())
			   {
				   
				   gdata.add(rs.getString(1));
				 //  System.out.println(rs.getString(1));
				   
				   
			   }
			    
	         rs=null;
	              
	              stmt.close();
	              c.commit();
	              c.close();
				
			}
			catch(Exception e)
			{
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			      System.exit(0);
				
			}
			 System.out.println("Records created successfully");
		}
		
	}
	
public static void JTableData()
	
	{
	
	//tp=ActivityMaster_Data.getTableData();
	
	Object[] cols={"GroupName","Method Title","Method Description"};
	
	 
	 md=new DefaultTableModel(cols,0);
	contentPane.setLayout(null);
	
	
	  
	 tb=new JTable(md);
   //tb.setBounds(30,40,200,300);
	
	 tb.getTableHeader().setFont(new Font("Bookman Old Style", Font.BOLD, 12));
	 tb.getTableHeader().setBackground(new Color(0,106, 78));
	 tb.getTableHeader().setForeground(Color.white);
	
	 tb.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
	    JScrollPane sp=new JScrollPane(tb);  
	    //sp.setBounds(100, 100, 1000, 600);
	screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    double height = screenSize.height/2;
	    int width = screenSize.width;
	  // sp.setSize(width, (int) height/2);
	 
	 sp.setBounds(0, 200, width, (int) height);
	   
	   contentPane.add(sp);
		 
	}
public static void clearData()
{
	int tbcount=tb.getRowCount();
	
	while(tbcount>=1)
	{
	md.removeRow(tbcount-1);
	tbcount--;
	}

}

public  void deleteRecords() 
{
	String ordata=(String) gname.getSelectedItem();
	Connection c = null;
	   Statement stmt = null;
	try
	{
		//gdata.clear();
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:AutomatonFramework.db");
	      c.setAutoCommit(false);
			
			 stmt = c.createStatement();
		 String sql = "DELETE  from BusinessFunction where groupname='" +ordata+"'";
	      stmt.executeUpdate(sql);
	      c.commit();
	  	 stmt.close();
	     c.close();
	}
	
	catch(Exception e)
	{
	System.out.println(e.getMessage());
	}

	 
}

public  void insertRecords()


	
{

System.out.println(tb.getValueAt(0,0));
	Connection c = null;
	   Statement stmt = null;
	try
	{
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:AutomatonFramework.db");
	      c.setAutoCommit(false);
	      stmt=c.createStatement();
		 
	     for(int row=0;row<tb.getRowCount();row++)
	   		{
	    		 String   	 sql= "INSERT INTO BusinessFunction(groupname,methodname,description) " +
		                "VALUES ('" + tb.getValueAt(row,0) +"', '" + tb.getValueAt(row,1) +"','" + tb.getValueAt(row,2) +"' );"; 
   			    stmt.executeUpdate(sql);
   			    
   			 
	   		}
	     
          
          stmt.close();
         c.commit();
          c.close();
		
	}
	catch(Exception e)
	{
		System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
		
	}
	 System.out.println("Records created successfully");
	
	
	
	
}
public void addRow(String data)
{
	
md.fireTableDataChanged();
	
	clearData();
	 tp=BusinessMethods.getTableData(data);
	
	  Set<Integer> keyid = tp.keySet();
	  
	     for (Integer key : keyid)
	     {
	    			    	
	        Object [] objectArr = tp.get(key);
	     
	       		     		           
	           md.addRow(objectArr);   
	           
	     }  
	
}
	
	/**
	 * Create the frame.
	 */
	public EditBusinessFunction() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		getData();
		gname = new JComboBox(gdata.toArray());
		gname.setBounds(231, 111, 180, 26);
		contentPane.add(gname);
		
		JLabel lblNewLabel = new JLabel("GroupName");
		lblNewLabel.setBounds(151, 114, 89, 20);
		contentPane.add(lblNewLabel);
		
		JButton btnRemove = new JButton("");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tb.getSelectedRow()>0)
		   		{
		   if(md.getRowCount()>1)
		   {
		                md.removeRow(tb.getSelectedRow());
		   }
		   		}	
		   	
			}
		});
		btnRemove.setIcon(new ImageIcon("C:\\Users\\dt77419\\Desktop\\STAF\\Images\\close.png"));
		btnRemove.setToolTipText("Delete Record");
		btnRemove.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		btnRemove.setBounds(67, 700, 99, 37);
		btnRemove.setBackground(new Color(0,106,50));
		contentPane.add(btnRemove);
		
		JButton btnSave = new JButton("");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteRecords();
		   		insertRecords();
			}
		});
		btnSave.setIcon(new ImageIcon("C:\\Users\\dt77419\\Desktop\\STAF\\Images\\saveicon.png"));
		btnSave.setToolTipText("Save Record");
		btnSave.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		btnSave.setBounds(258, 700, 99, 37);
		btnSave.setBackground(new Color(0,106,50));
		contentPane.add(btnSave);
		 JButton btnHome = new JButton("");
		  btnHome.setIcon(new ImageIcon("C:\\Users\\dt77419\\Desktop\\STAF\\Images\\home.png"));
		   btnHome.setToolTipText("Go to Home");
		   btnHome.setSelectedIcon(new ImageIcon("C:\\Users\\dt77419\\Desktop\\STAF\\Images\\home.png"));
		   btnHome.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent e)
		   	{
		   	
		   		try {
		   			dispose();
		   			MasterForm frame = new MasterForm();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					frame.setVisible(true);
					
				} catch (Exception ee) {
					ee.printStackTrace();
				}
				
		   		
		   		
		   	}
		   });
		   btnHome.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		   btnHome.setBackground(Color.white);
		   btnHome.setBounds(355, 700, 106, 38);
		   getContentPane().add(btnHome);
		JButton btnAddNew = new JButton("");
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 md.addRow(new Object[]{"","","",""});
			}
		});
		btnAddNew.setIcon(new ImageIcon("C:\\Users\\dt77419\\Desktop\\STAF\\Images\\Add.png"));
		btnAddNew.setToolTipText("Add Record");
		btnAddNew.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		btnAddNew.setBounds(164, 700, 99, 37);
		btnAddNew.setBackground(new Color(0,106,50));
		contentPane.add(btnAddNew);
		gname.addItemListener(new ItemListener() {
			  public void itemStateChanged(ItemEvent event) {
				  String item;
				  
				  				
				  if (event.getStateChange() == ItemEvent.SELECTED)
				  
				  {
					  
			          item = (String) event.getItem();
			          
			          if(!item.isEmpty())
			        	 
			          {
			       
			        	  addRow(item);
					
				
			          }
				  }
			}
		});
		
		
	}
}
