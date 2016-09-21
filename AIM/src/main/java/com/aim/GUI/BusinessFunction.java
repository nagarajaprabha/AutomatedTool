package com.aim.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.aim.frameworkSQLDB.CreateNewDB;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class BusinessFunction extends JFrame {

	private JPanel contentPane;
	private static JTextField groupname;
	private static JTextField methodtitle;
	static JTextArea methoddesc;
	static ArrayList<Object> gdata;
	static JComboBox gname;


	/**
	 * Create the frame.
	 */
	
	public static void getData()
	{
		
		{
			Connection c = null;
			   Statement stmt = null;
			   ResultSet rs =null;
			try
			{
				gdata=new ArrayList<Object>();
				gdata.clear();
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
	
	public static void delData()
	{
		
		
		Connection c = null;
		   Statement stmt = null;
		try
		{
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:AutomatonFramework.db");
	      c.setAutoCommit(false);
			
			 stmt = c.createStatement();
					 
			 String   sql = "DELETE FROM BusinessFunction";
		     stmt.executeUpdate(sql);
		     
		  	     stmt.executeUpdate(sql);
		    
      
           
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
    
    public static void putData()
	{
		Connection c = null;
		   Statement stmt = null;
		try
		{
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:AutomatonFramework.db");
	      c.setAutoCommit(false);
			
			 stmt = c.createStatement();
				
			 if(groupname.isVisible())
			 {
			 String   sql = "INSERT INTO BusinessFunction(groupname,methodname,description) " +
		                "VALUES ('"+groupname.getText()+ "', '"+methodtitle.getText()+"', '"+methoddesc.getText()+"' )"; 
		     stmt.executeUpdate(sql);
			 }
			 
			 if(gname.isVisible() )
			 {
			 String   sql = "INSERT INTO BusinessFunction(groupname,methodname,description) " +
		                "VALUES ('"+gname.getSelectedItem() +"', '"+methodtitle.getText()+"', '"+methoddesc.getText()+"' )"; 
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
	public BusinessFunction() {
	
		setTitle("Business Functions");
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(119,162,248));
		setUndecorated( true );
		//setBackground(new Color(119,162,248));
		setDefaultLookAndFeelDecorated( false );
		getRootPane( ).setWindowDecorationStyle(JFrame.DISPOSE_ON_CLOSE );
		
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Add New GroupName");
		rdbtnNewRadioButton.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
		rdbtnNewRadioButton.setBounds(130, 121, 160, 23);
		rdbtnNewRadioButton.setSelected(true);
	
		
		JButton btnHome = new JButton("Home");
	
		   btnHome.setToolTipText("Go to Home");
		   
		
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
		  
		   btnHome.setBounds(700, 600, 100, 37);
		 
		   contentPane.add(btnHome);
	
		
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Select Existing Group");
		rdbtnNewRadioButton_1.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
		rdbtnNewRadioButton_1.setBounds(358, 121, 160, 23);
		
		contentPane.add(rdbtnNewRadioButton_1);
		
		ButtonGroup bG = new ButtonGroup();
	     bG.add(rdbtnNewRadioButton);
	     bG.add(rdbtnNewRadioButton_1);
	     
	     
	  
	    getData();
	   //delData();
	     
	     
	    gname = new JComboBox(gdata.toArray());
	     gname.setBounds(129, 155, 389, 23);
	     gname.setVisible(false);
	     contentPane.add(gname);
	     
	     groupname = new JTextField();
	     groupname.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
	     groupname.setBounds(129, 157, 386, 23);
	     
	     contentPane.add(groupname);
	     groupname.setColumns(10);
	    // groupname.setVisible(false);
	     
	     ActionListener sliceActionListener = new ActionListener() {
	         public void actionPerformed(ActionEvent actionEvent) {
	           AbstractButton aButton = (AbstractButton) actionEvent.getSource();
	          
	           
	           groupname.setText(null);
	           if(aButton.getText().equals("Select Existing Group"))
	           {
	        	   groupname.setVisible(false);
	        	   gname.setVisible(true);
	           }
	           if(aButton.getText().equals("Add New GroupName"))
	           {
	        	   groupname.setVisible(true);
	        	   gname.setVisible(false);
	           }
	           
	           
	           
	         }
	       };
	       
	       rdbtnNewRadioButton.addActionListener(sliceActionListener);
	       rdbtnNewRadioButton_1.addActionListener(sliceActionListener);
	     
	     
	     
	     JLabel lblNewLabel = new JLabel("Method Title");
	     lblNewLabel.setForeground(new Color(255, 255, 255));
	     lblNewLabel.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
	     lblNewLabel.setBounds(45, 212, 87, 14);
	     contentPane.add(lblNewLabel);
	     
	     methodtitle = new JTextField();
	     methodtitle.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
	     methodtitle.setBounds(130, 208, 389, 23);
	     contentPane.add(methodtitle);
	     methodtitle.setColumns(10);
	     
	     methoddesc = new JTextArea();
	     methoddesc.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
	     methoddesc.setBounds(132, 251, 387, 134);
	     contentPane.add(methoddesc);
	     
	     JLabel lblNewLabel_1 = new JLabel("Description");
	     lblNewLabel_1.setForeground(new Color(255, 255, 255));
	     lblNewLabel_1.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
	     lblNewLabel_1.setBounds(45, 256, 77, 14);
	     contentPane.add(lblNewLabel_1);
	     
	     JLabel lblNewLabel_2 = new JLabel("Application Specific Functions");
	     lblNewLabel_2.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
	     lblNewLabel_2.setBounds(159, 42, 300, 34);
	     contentPane.add(lblNewLabel_2);
	     
	     JButton btnNewButton = new JButton("Submit");
	     btnNewButton.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {
	     		
	     		putData();
	     	}
	     });
	     btnNewButton.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
	     btnNewButton.setBounds(142, 421, 116, 34);
	     contentPane.add(btnNewButton);
	     
	     JLabel lblNewLabel_3 = new JLabel("Group Name");
	     lblNewLabel_3.setForeground(new Color(255, 255, 255));
	     lblNewLabel_3.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
	     lblNewLabel_3.setBounds(41, 159, 78, 14);
	     contentPane.add(lblNewLabel_3);
		
	}
}
