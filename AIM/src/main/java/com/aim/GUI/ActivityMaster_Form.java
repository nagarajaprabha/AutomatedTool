package com.aim.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.util.Map;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.aim.frameworkSQLDB.ActivityMaster_Data;
import com.aim.frameworkSQLDB.CreateNewDB;
import com.aim.frameworkSQLDB.SelectTableData;

public class ActivityMaster_Form extends JFrame {

	private static JPanel contentPane;
	
	
	static JTable tb;
	static DefaultTableModel md;
	static Map < Integer, Object[] > tp;
	static Statement stmt=null;

	
	public static void JTableData()
	{
tp=ActivityMaster_Data.getTableData();
		
		Object[] cols={"ObjectType","Method","Activity_KeyWord"};
		
		 
		 md=new DefaultTableModel(cols,0);
		contentPane.setLayout(null);
		
		
		  
		 tb=new JTable(md);
	   //tb.setBounds(30,40,200,300);
		
		 tb.getTableHeader().setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		 tb.getTableHeader().setBackground(new Color(119,162,248));
		 tb.getTableHeader().setForeground(Color.white);
		
		 tb.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		 //tb.setBackground(Color.white);
		    JScrollPane sp=new JScrollPane(tb);  
		    //sp.setBounds(100, 100, 1000, 600);
		    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		    double height = screenSize.height/2;
		    int width = screenSize.width;
		    sp.setSize(width, (int) height);
		    //sp.setBackground(Color.WHITE);
		    sp.setOpaque(true);
		    sp.setAutoscrolls(true);
		   contentPane.add(sp);
		   Set<Integer> keyid = tp.keySet();
		     
		     for (Integer key : keyid)
		     {
		    			    	
		        Object [] objectArr = tp.get(key);
		     
		       		     		           
		           md.addRow(objectArr);     
		     }  
		   
		
	}
	
	public  void deleteRecords() 
	{
		try
		{
			CreateNewDB.CreateDB();
			CreateNewDB.conn.setAutoCommit(false);
			stmt=CreateNewDB.conn.createStatement();
			 String sql = "DELETE  from Activity_Master;";
		      stmt.executeUpdate(sql);
		      CreateNewDB.conn.commit();
		  	 stmt.close();
		      CreateNewDB.conn.close();
		}
		
		catch(Exception e)
		{
		System.out.println(e.getMessage());
		}
	
		 
	}
	
	public  void insertRecords()
	{
		
		try
		{
			CreateNewDB.CreateDB();
			CreateNewDB.conn.setAutoCommit(false);
			
			 stmt = CreateNewDB.conn.createStatement();
			 
		   String sql; 
		    
		   
		     for(int row=0;row<tb.getRowCount();row++)
		   		{
		   			
		   				
		   				sql= "INSERT INTO Activity_Master(objecttype,method,keyword) " +
				                "VALUES ('" + tb.getValueAt(row,0) +"', '" + tb.getValueAt(row,1) +"','" + tb.getValueAt(row,2) +"' );"; 
		   			    stmt.executeUpdate(sql);
		   		
		   		}
		     
              
              stmt.close();
              CreateNewDB.conn.commit();
              CreateNewDB.conn.close();
			
		}
		catch(Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
			
		}
		 System.out.println("Records created successfully");
		
		
		
		
	}
	/**
	 * Create the frame.
	 */
	public ActivityMaster_Form() {
		//setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setFont(new Font("Bookman Old Style", Font.BOLD, 16));
		setTitle("Activity Master Form");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\dt77419\\Desktop\\STAF\\Images\\dst.jpg"));
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setUndecorated( true );
		
		setBounds(100, 100, 1000, 1000);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(119,162,248));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setUndecorated( true );
		//setBackground(new Color(119,162,248));
		setDefaultLookAndFeelDecorated( false );
		getRootPane( ).setWindowDecorationStyle(JFrame.DISPOSE_ON_CLOSE );
		setContentPane(contentPane);
		
		   
		   JButton btnDelete = new JButton("Delete Record");
		   btnDelete.setToolTipText("Delete Record");
		  // btnDelete.setBackground(new Color(0,106,78));
		 //  btnDelete.setIcon(new ImageIcon("C:\\Users\\dt77419\\Desktop\\STAF\\Images\\close.png"));
		   btnDelete.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		   btnDelete.addActionListener(new ActionListener() {
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
		   contentPane.setLayout(null);
		   btnDelete.setBounds(96, 600, 99, 37);
		   contentPane.add(btnDelete);
		   
		   JButton btnNewButton = new JButton("Add New");
		   btnNewButton.setToolTipText("Add Record");
		 
		   //btnNewButton.setIcon(new ImageIcon("C:\\Users\\dt77419\\Desktop\\STAF\\Images\\Add.png"));
		   btnNewButton.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		   btnNewButton.setBounds(193, 600, 99, 37);
		   contentPane.add(btnNewButton);
		   
		   JButton btnSave = new JButton("Save");
		  // btnSave.setIcon(new ImageIcon("C:\\Users\\dt77419\\Desktop\\STAF\\Images\\saveicon.png"));
		   btnSave.setToolTipText("Save Record");
		  // btnSave.setSelectedIcon(new ImageIcon("C:\\Users\\dt77419\\Desktop\\STAF\\Images\\saveicon.png"));
		   btnSave.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent e)
		   	{
		   	deleteRecords();
		   		insertRecords();
				
		   		
		   		
		   	}
		   });
		   btnSave.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		  // btnSave.setBackground(new Color(0,106,78));
		   btnSave.setBounds(287, 600, 112, 37);
		   contentPane.add(btnSave);
		   
		   JButton btnHome = new JButton("Home");
		//  btnHome.setIcon(new ImageIcon("C:\\Users\\dt77419\\Desktop\\STAF\\Images\\home.png"));
		   btnHome.setToolTipText("Go to Home");
		  // btnHome.setSelectedIcon(new ImageIcon("C:\\Users\\dt77419\\Desktop\\STAF\\Images\\home.png"));
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
		  // btnHome.setBackground(Color.white);
		   btnHome.setBounds(400, 600, 112, 37);
		   contentPane.add(btnHome);
		   
		   btnNewButton.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent e) {
		   		
		   	 md.addRow(new Object[]{"","",""});
		   	 
		   	}
		   });
		   
		  
		 
		  
		     
		     
		     
		           
		      
	}


}
