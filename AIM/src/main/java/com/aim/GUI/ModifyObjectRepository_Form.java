package com.aim.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
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

import org.openqa.selenium.Point;

import com.aim.frameworkSQLDB.ActivityMaster_Data;
import com.aim.frameworkSQLDB.CreateNewDB;
import com.aim.frameworkSQLDB.ORSelectData;
import com.aim.frameworkSQLDB.TestCasesList;

import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class ModifyObjectRepository_Form extends JFrame {

	private static JPanel contentPane;
static Statement stmt=null;
static ResultSet rs=null;
static ArrayList<String> al;

static JTable tb;
static DefaultTableModel md;
static Map < Integer, Object[] > tp;
static JComboBox or_Data;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public static void JTableData()
	
	{
	

		
		Object[] cols={"UI Name","UI Type","Locator","Locator Type"};
		
		 
		 md=new DefaultTableModel(cols,0);
		contentPane.setLayout(null);
		
		
		  
		 tb=new JTable(md);
	   
		
		 tb.getTableHeader().setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		 tb.getTableHeader().setBackground(new Color(119,162,248));
		 tb.getTableHeader().setForeground(Color.white);
		
		 tb.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
		 
		    JScrollPane sp=new JScrollPane(tb);  
		   
		    //sp.setBounds(100, 100, 1000, 600);
		    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
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
	
	public void addRow(String data)
	{
		
		md.fireTableDataChanged();
		
		clearData();
		 tp=ORSelectData.getTableData(data);
		
		  Set<Integer> keyid = tp.keySet();
		     
	      
		   
		     
		     for (Integer key : keyid)
		     {
		    			    	
		        Object [] objectArr = tp.get(key);
		     
		       		     		           
		           md.addRow(objectArr);   
		           
		     }  
		   
		    
		
	}
	
	
	
	
	
	public void getUITypeList()
	{
		al=new ArrayList<String>();
		al.add("");
		try
		{
			CreateNewDB.CreateDB();
			CreateNewDB.conn.setAutoCommit(false);
			stmt=CreateNewDB.conn.createStatement();
			rs=stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table' and name LIKE 'OR%' ;");
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
	public  void deleteRecords() 
	{
		String ordata=(String) or_Data.getSelectedItem();
		try
		{
			CreateNewDB.CreateDB();
			CreateNewDB.conn.setAutoCommit(false);
			stmt=CreateNewDB.conn.createStatement();
			 String sql = "DELETE  from " + ordata;
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
		String ordata=(String) or_Data.getSelectedItem();
		try
		{
			CreateNewDB.CreateDB();
			CreateNewDB.conn.setAutoCommit(false);
			
			 stmt = CreateNewDB.conn.createStatement();
			 
		   String sql; 
		    
		   
		     for(int row=0;row<tb.getRowCount();row++)
		   		{
		    	 String str=tb.getValueAt(row,2).toString();
			    	str=str.replaceAll("'", "''");
		   				System.out.println(str);
		    	 sql= "INSERT INTO " +ordata+"(uiname,uitype,locator,locatortype) " +
			                "VALUES ('" + tb.getValueAt(row,0) +"', '" + tb.getValueAt(row,1) +"','" + str +"','" + tb.getValueAt(row,3) +"' );"; 
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
	public ModifyObjectRepository_Form() throws Exception {
		setTitle("AIM-Automation Integration Framework");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\dt77419\\Desktop\\STAF\\Images\\DSTLogo.gif"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 878, 627);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(119,162,248));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setUndecorated( true );
		//setBackground(new Color(119,162,248));
		setDefaultLookAndFeelDecorated( false );
		getRootPane( ).setWindowDecorationStyle(JFrame.DISPOSE_ON_CLOSE );
		setContentPane(contentPane);
		contentPane.setLayout(null);
		getUITypeList();
	 or_Data = new JComboBox(al.toArray());
		or_Data.setBounds(259, 76, 266, 29);
		or_Data.setForeground(new Color(0,106, 78));
		contentPane.add(or_Data);
		or_Data.addItemListener(new ItemListener() {
			  public void itemStateChanged(ItemEvent event) {
				  String item;
				  
				  				
				  if (event.getStateChange() == ItemEvent.SELECTED)
				  
				  {
					  
			          item = (String) event.getItem();
			          
			          if(!item.isEmpty())
			        	 
			          {
			       
			         
			        	  addRow(item);
			        	  System.out.println(item);
					
				
			          }
				  }
			}
		});
		
		
		JLabel lblNewLabel = new JLabel("Select Object Repository");
		lblNewLabel.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
		lblNewLabel.setBounds(74, 82, 180, 14);
		lblNewLabel.setForeground(Color.white);
		lblNewLabel.setBackground(new Color(119,162,248));
		contentPane.add(lblNewLabel);
		
		JButton btnRemove = new JButton("Delete");
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
	
		btnRemove.setToolTipText("Delete Record");
		btnRemove.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		btnRemove.setBounds(67, 700, 99, 37);
		
		contentPane.add(btnRemove);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteRecords();
		   		insertRecords();
			}
		});
		
		btnSave.setToolTipText("Save Record");
		btnSave.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		btnSave.setBounds(258, 700, 99, 37);
		
		contentPane.add(btnSave);
		 JButton btnHome = new JButton("Home");
		  btnHome.setIcon(new ImageIcon("C:\\Users\\dt77419\\Desktop\\STAF\\Images\\home.png"));
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
		  
		   btnHome.setBounds(355, 700, 106, 38);
		   getContentPane().add(btnHome);
		JButton btnAddNew = new JButton("Add New");
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 md.addRow(new Object[]{"","","",""});
			}
		});
		
		btnAddNew.setToolTipText("Add Record");
		btnAddNew.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		btnAddNew.setBounds(164, 700, 99, 37);
		
		contentPane.add(btnAddNew);
		
		
		
		
		
		
		
		

		
	}
}
