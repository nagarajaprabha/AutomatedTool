package com.aim.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.aim.frameworkSQLDB.CreateNewDB;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Admin extends JFrame {

	private JPanel contentPane;
	static ArrayList<String> al;
	static JComboBox tableTitle;

	
	
	public void deleteTable(String tablename)
	{
		Connection c = null;
		   Statement stmt = null;
		    try {
			      Class.forName("org.sqlite.JDBC");
			      c = DriverManager.getConnection("jdbc:sqlite:AutomatonFramework.db");
			      c.setAutoCommit(false);
			      
			      stmt=c.createStatement();
			      stmt.executeUpdate("DROP TABLE "+tablename);
			      c.commit();
			      System.out.println("Table Deleted Successfully");
		    }
		    catch(Exception e)
		    {
		    	
		    	
		    }
		
		
	}
	public void getUITypeList()
	{
		al=new ArrayList<String>();
		al.add("");
		Connection con;
		Statement stmt;
		ResultSet rs;
		try
		{
			 
		    Class.forName("org.sqlite.JDBC");
		    con = DriverManager.getConnection("jdbc:sqlite:AutomatonFramework.db");
		 	stmt=con.createStatement();
			rs=stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table' and name LIKE 'OR%' or name LIKE 'TestData_%' ;");
			while(rs.next())
			{
				al.add(rs.getString(1));
				
			}
			
			rs.close();
			stmt.close();
			CreateNewDB.conn.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			
		}
	
		
	}
	public Admin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 611, 379);
		
		setUndecorated( true );
		//setBackground(new Color(119,162,248));
		setDefaultLookAndFeelDecorated( false );
		getRootPane( ).setWindowDecorationStyle(JFrame.DISPOSE_ON_CLOSE );
		setTitle("Admin Form");
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground(new Color(119,162,248));
		contentPane.setLayout(null);
		getUITypeList();
		 tableTitle = new JComboBox(al.toArray());
		tableTitle.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
		tableTitle.setBounds(161, 82, 220, 28);
		contentPane.add(tableTitle);
		
		JLabel lblNewLabel = new JLabel("Select Table:");
		lblNewLabel.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
		lblNewLabel.setBounds(74, 86, 77, 21);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Delete Table");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String tablename=(String) tableTitle.getSelectedItem();
				if(tablename !=null || tablename !="")
				{
					
					deleteTable(tablename);
					
					
				}
				
				
			}
		});
		btnNewButton.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
		btnNewButton.setBounds(143, 700, 145, 28);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Home");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
	   			MasterForm frame = new MasterForm();
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				frame.setVisible(true);
				
			}
		});
		btnNewButton_1.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
		btnNewButton_1.setBounds(284, 700, 120, 28);
		contentPane.add(btnNewButton_1);
	}
}
