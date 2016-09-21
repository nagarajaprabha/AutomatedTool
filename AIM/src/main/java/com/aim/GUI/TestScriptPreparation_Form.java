package com.aim.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.aim.frameworkSQLDB.CreateNewDB;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.ItemSelectable;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTabbedPane;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import javax.swing.JTextArea;
import javax.swing.JInternalFrame;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

   public class TestScriptPreparation_Form extends JFrame {

	private static JPanel contentPane;
	static Statement stmt=null;
	static PreparedStatement pstmt=null;
	static Connection conn=null;
	static ResultSet rs=null;
	static ArrayList<String> al,ol,ui,activity,colList,waitList;

	static JTable tb;
	static DefaultTableModel md;
	static Map < Integer, Object[] > tp;
	public static JComboBox releaseName;
	private JTextField tcTitle;
	private JLabel lblTestcaseTitle;
	private JLabel lblNewLabel_1;
	private JComboBox uiName;
	static JComboBox orData;
	private JLabel lblNewLabel_2;
	private JTextField lTypeValue;
	private JTextField lValue;
	private JLabel lblNewLabel_3;
	private JTextField uiTypeValue;
	private JLabel lblNewLabel_4;
	private JLabel btnActivity,mlable,glabel;
	private JComboBox activeButton,bmethod,waitBox;
	private JTextField testData;
	
	JButton endTC,startTC;
	static ArrayList<Object> gdata,gmethod;
public	static JInternalFrame internalFrame;
static JComboBox groupname;
JRadioButton dvalue,dparam;
	static JScrollPane sp;
	
	static String tname=null;
	
	  static int checkTableCount=0;
		
		
		
	
	/**
	 * Create the frame.
	 */
	
	 public void createRCombo()
	 {
		releaseName = new JComboBox();
		 releaseName.addItem("Phani");
		   releaseName.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
		   releaseName.setBounds(163, 61, 291, 35);
		  contentPane.add(releaseName);
		// System.out.println(al.size());
	 }
	 
	 
	public void getActivityData()
	{
String utype=uiTypeValue.getText();


	activity=new ArrayList<String>();
	activity.clear();
	activity.add("");
	
	if(!utype.equals(null))
	{
		try
		{
			stmt=conn.createStatement();
			rs=stmt.executeQuery("SELECT keyword FROM Activity_Master WHERE objecttype='"+utype+"' OR objecttype='Wait'");
			while(rs.next())
			{
				activity.add(rs.getString(1));
				
			}
			
			rs.close();
			stmt.close();
		
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			
		}
	}
}
	
	public void getWaitMethod()
	{
		
		Connection con;
		Statement stmt;
		ResultSet rs;
		
		waitList=new ArrayList<String>();
		try
		{
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:AutomatonFramework.db");
		      con.setAutoCommit(false);
					 stmt = con.createStatement();
			rs=stmt.executeQuery("SELECT keyword FROM Activity_Master WHERE  objecttype='Wait'");
			waitList.clear();
			while(rs.next())
			{
				waitList.add(rs.getString(1));
				
			}
			
			rs.close();
			stmt.close();
		con.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			
		}
		
		
	}
	
	public static Connection  getConnect()
	{
		
		 
		 try
		 {
		 Class.forName("org.sqlite.JDBC");
	      conn = DriverManager.getConnection("jdbc:sqlite:AutomatonFramework.db");
	      conn.setAutoCommit(true);
		stmt=conn.createStatement();
		 }
		 catch(Exception e)
		 {
			 
			 
		 }
		 return conn;
		
	}
	public void getORData()
	{
	ol=new ArrayList<String>();
		ol.add("");
		try
		{
			Connection c=getConnect();
			rs=stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table' and name LIKE 'OR%' ;");
			while(rs.next())
			{
				ol.add(rs.getString(1));
				
			}
			
			rs.close();
			stmt.close();
			c.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			
		}
	
		
	}
	public void getMiscData()
	{
		
String uname=(String) uiName.getSelectedItem();
String ordata=(String) orData.getSelectedItem();


	
		try
		{
			
			Connection c=getConnect();
			rs=stmt.executeQuery("SELECT uitype,locator,locatortype FROM " +ordata+" WHERE uiname='" + uname + "'");
			while(rs.next())
			{
				lTypeValue.setText(rs.getString(3));
				lValue.setText(rs.getString(2));
				uiTypeValue.setText(rs.getString(1));
				
			}
			getActivityData();
			activeButton.removeAllItems();
			for(String obj:activity)
			{
				activeButton.addItem(obj);
				
			}
			
			rs.close();
			stmt.close();
			c.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			
	
}
		
		
		
	}
	
	public void getUIData()
	{
String ordata=(String) orData.getSelectedItem();

if(!ordata.isEmpty())
{
   
	ui=new ArrayList<String>();
		ui.add("");
		try
		{
			
			Connection c=getConnect();
			rs=stmt.executeQuery("SELECT uiname FROM " +ordata );
			while(rs.next())
			{
				ui.add(rs.getString(1));
				
			}
			
			rs.close();
			stmt.close();
		c.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			
		}
	
}


	}
	
	public static void getGroupMethod(String gname)
	{
		
		{
			Connection c = null;
			   Statement stmt = null;
			   ResultSet rs =null;
			   gmethod=new ArrayList<Object>();
			   gmethod.add("");
			try
			{
				
				//gdata.clear();
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:AutomatonFramework.db");
		      c.setAutoCommit(false);
				
				 stmt = c.createStatement();
						 
		 
			   rs=stmt.executeQuery("SELECT methodname FROM BusinessFunction where groupname='"+gname+"'");
			   
			   while(rs.next())
			   {
				   
				   gmethod.add(rs.getString(1));
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
		
		}
		
		
		
		
	}
	public static void getGroupName()
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
		
		}
		
	}
	
	public void clearData()
	{
		int tbcount=tb.getRowCount();
		
		do
		{
		md.removeRow(tbcount-1);
		tbcount--;
		}	

		while(tbcount>=1);
		
	}
	
	public  void insertRecords() 
	{
		String rname=(String) releaseName.getSelectedItem();
		String title=(tcTitle.getText().trim().replaceAll(" ", ""));
	String[] data;
		
		
		try
		{
			
			Connection c=getConnect(); 
		    
		String sql;
		data=new String[tb.getRowCount()];
		  for(int row=0;row<tb.getRowCount();row++)
		  {
				String str=tb.getValueAt(row,2).toString();
		    	str=str.replaceAll("'", "''");
			  
			  sql= "INSERT INTO TestCase_Master(release_name,tc_name,UIName,Locator,Locator_Type,Activity,Data,RUNMODE,Data_Flag) " +
		                "VALUES ('" + rname +"', '" + tb.getValueAt(row,0) +"','" + tb.getValueAt(row,1) +"','"+ str+"','" + tb.getValueAt(row,3) +"','" + tb.getValueAt(row,4) +"','" + tb.getValueAt(row,5) +"','','No' );"; 
			  
			  stmt.executeUpdate(sql);
			  data[row]=(String) tb.getValueAt(row,5);
				    	 	
		    	 
		  }	 
	   						   		
		 stmt.close();
		 c.close();	
		 
		 for(String str:data)
		 {
			
			 if(str.startsWith("PARAM|"))
			 {
			createTestDataTable();
			updateTestCaseMaster();
			 break;
			 			 }
			 else
			 {
				
				 updateTestCaseMaster_No(); 
				 
			 }
			 
		//createTestDataTable();
		 }
		 JOptionPane.showMessageDialog(null,"Test Case Created Successfully","Successfully Created",JOptionPane.INFORMATION_MESSAGE);
		 clearData();
		 tcTitle.setText(null);
		 
		 internalFrame.setVisible(false);
			internalFrame.setEnabled(false);
			
			sp.setVisible(false);
			
			
		}
		catch(Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		     
				
			
		}
		
				}
		

	public static void getReleaseData()
	{
   
		 
		
		
		al=new ArrayList<String>();
		
		try
		{
		
		
		al.add("");
		 
			 
			System.out.println("Hii");
			Connection c=getConnect();
			rs=stmt.executeQuery("SELECT * FROM MasterTestSuite ;");
		
			
			while(rs.next())
			{
				al.add(rs.getString(1));
				
			}
			
			
			rs.close();
			stmt.close();
			c.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
			
		}
		
		System.out.println(al.size());
	
		
	}
	public void addTestDataColumn()
	{
		colList=new ArrayList<String>();
		colList.clear();
		String colName=null;
		int count=tb.getRowCount();
	
		try {
	    	String sql;
	    	Connection c=getConnect();
	    	for(int i=0;i<count;i++)
			{
			
		colName=(String) tb.getValueAt(i,5);
			if(colName.startsWith("PARAM"))
			{
				colList.add(colName.substring(6));
					
			}
			}
	    	for(String col:colList)
	    	{
			 sql="ALTER TABLE "+ tname + " ADD COLUMN " + col + " TEXT";
	                
			stmt.executeUpdate(sql);
			
	    	}
	    	stmt.close();
	    	c.close();
		} 
	    catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	
	
	}
	
	public void updateTestCaseMaster()
	{
		String rname=(String) releaseName.getSelectedItem();
		String title=tcTitle.getText().replaceAll(" ", "");
		 tname="TestData_"+rname+"_"+title.trim();
		 
		 System.out.println("Tname="+tname);
		Connection c=getConnect();
    try {
    	String sql;
		 sql="UPDATE TestCase_Master SET Data_Flag='Yes' WHERE release_name='" +rname+"' AND tc_name='"+title+"';";
		stmt.executeUpdate(sql);
		stmt.close();
		c.close();
	} 
    catch (SQLException e) {
		
		e.printStackTrace();
	}
		
		
	}

	public void updateTestCaseMaster_No()
	{
		String rname=(String) releaseName.getSelectedItem();
		String title=tcTitle.getText().replaceAll(" ", "");
		 tname="TestData_"+rname+"_"+title.trim();
		 
		 System.out.println("Tname="+tname);
		Connection c=getConnect();
    try {
    	String sql;
		 sql="UPDATE TestCase_Master SET Data_Flag='No' WHERE release_name='" +rname+"' AND tc_name='"+title+"';";
		stmt.executeUpdate(sql);
		stmt.close();
		c.close();
	} 
    catch (SQLException e) {
		
		e.printStackTrace();
	}
		
		
	}
	public void createTestDataTable()
	{
		String rname=(String) releaseName.getSelectedItem();
		String title=tcTitle.getText().replaceAll(" ", "");
		 tname="TestData_"+rname+"_"+title.trim();
		 
		 System.out.println("Tname="+tname);
		Connection c=getConnect();
    try {
    	String sql;
		 sql="CREATE TABLE "+ tname  +
                "(release_name    TEXT     , " + 
                " tc_name    TEXT     , " + 
                " runmode    TEXT )";
		stmt.executeUpdate(sql);
		stmt.close();
		c.close();
	} 
    catch (SQLException e) {
		
		e.printStackTrace();
	}
    System.out.println("Table with Name:"+tname +"Created Successfully");
    addTestDataColumn();	
	}
	
	
	
	public TestScriptPreparation_Form() throws Exception {
		setTitle("Test Script Preparation Form");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\dt77419\\Desktop\\STAF\\Images\\DSTLogo.gif"));
		conn=CreateNewDB.getConn();
		getReleaseData();
		getORData();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1140, 990);
		contentPane = new JPanel();
		setUndecorated( true );
		//setBackground(new Color(119,162,248));
		
		contentPane.setBackground(new Color(119,162,248));
		setDefaultLookAndFeelDecorated( false );
		getRootPane( ).setWindowDecorationStyle(JFrame.DISPOSE_ON_CLOSE );
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	    contentPane.setLayout(null);
	    
	 
	  // releaseName = new JComboBox(al.toArray());
	   //releaseName.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
	  // releaseName.setBounds(163, 61, 291, 35);
	   //releaseName.setBackground(new Color(0,106, 78));
		//contentPane.add(releaseName);
	    releaseName = new JComboBox(al.toArray());
		
		   releaseName.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
		   releaseName.setBounds(163, 61, 291, 35);
		  contentPane.add(releaseName);
	   
		
		JLabel lblSelectRelease = new JLabel("Select Release:");
		lblSelectRelease.setForeground(Color.WHITE);
		lblSelectRelease.setBounds(58, 61, 110, 25);
		lblSelectRelease.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
		lblSelectRelease.setBackground(new Color(119,162,248));
		contentPane.add(lblSelectRelease);
		
		tcTitle = new JTextField();
		tcTitle.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
		tcTitle.setBounds(163, 137, 651, 35);
		contentPane.add(tcTitle);
		tcTitle.setColumns(10);
		
		lblTestcaseTitle = new JLabel("TestCase Title:");
		lblTestcaseTitle.setForeground(Color.WHITE);
		lblTestcaseTitle.setBounds(58, 141, 110, 25);
		lblTestcaseTitle.setBackground(new Color(0,106, 78));
		lblTestcaseTitle.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
		contentPane.add(lblTestcaseTitle);
		
		 internalFrame = new JInternalFrame("TEST STEPS INTERFACE");
		internalFrame.setFrameIcon(null);
		internalFrame.getContentPane().setBackground(new Color(255, 255, 255));
		 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		  // int width = (int) (screenSize.width/4);
		  // int  height=(int) (screenSize.height/1.5);
		
		
		internalFrame.setBounds(0, 200, 600,600);
		
		internalFrame.setVisible(false);
		internalFrame.setEnabled(false);
		internalFrame.setAutoscrolls(true);
		
		((javax.swing.plaf.basic.BasicInternalFrameUI) 
				internalFrame.getUI()).setNorthPane(null);
		contentPane.add(internalFrame);
		
		internalFrame.getContentPane().setLayout(null);
		
		
		
		JLabel lblNewLabel = new JLabel("Select Object Repository:");
		lblNewLabel.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 32, 160, 14);
		internalFrame.getContentPane().add(lblNewLabel);
		
		//orData.removeAllItems();
		 orData = new JComboBox(ol.toArray());
		 orData.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
		
		orData.setBounds(169, 25, 241, 30);
		internalFrame.getContentPane().add(orData);
		
		lblNewLabel_1 = new JLabel("Select UI Name");
		lblNewLabel_1.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 84, 108, 14);
		internalFrame.getContentPane().add(lblNewLabel_1);
		uiName = new JComboBox();
		uiName.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
		
		
		uiName.setBounds(111, 76, 235, 30);
		internalFrame.getContentPane().add(uiName);
		
		lblNewLabel_2 = new JLabel("Locator Type");
		lblNewLabel_2.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 187, 86, 14);
		internalFrame.getContentPane().add(lblNewLabel_2);
		
		lTypeValue = new JTextField();
		lTypeValue.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
		lTypeValue.setBounds(94, 180, 116, 30);
		internalFrame.getContentPane().add(lTypeValue);
		lTypeValue.setColumns(10);
		
		lValue = new JTextField();
		lValue.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
		lValue.setBounds(94, 226, 389, 31);
		internalFrame.getContentPane().add(lValue);
		lValue.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Locator");
		lblNewLabel_3.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(10, 234, 46, 14);
		internalFrame.getContentPane().add(lblNewLabel_3);
		
		uiTypeValue = new JTextField();
		uiTypeValue.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
		uiTypeValue.setBounds(94, 132, 116, 30);
		internalFrame.getContentPane().add(uiTypeValue);
		uiTypeValue.setColumns(10);
		
		lblNewLabel_4 = new JLabel("UI Type");
		lblNewLabel_4.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(10, 141, 74, 18);
		internalFrame.getContentPane().add(lblNewLabel_4);
		
		btnActivity = new JLabel("Select Activity");
		btnActivity.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
		btnActivity.setBounds(25, 314, 108, 14);
		btnActivity.setVisible(false);
		internalFrame.getContentPane().add(btnActivity);
		
		activeButton = new JComboBox();
		activeButton.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
		activeButton.setBounds(10, 339, 180, 30);
		activeButton.setVisible(false);
		internalFrame.getContentPane().add(activeButton);
		
	
		
		JLabel lblNewLabel_5 = new JLabel("TestData:");
		lblNewLabel_5.setFont(new Font("Bookman Old Style", Font.PLAIN, 25));
		lblNewLabel_5.setBounds(81, 453, 137, 30);
		internalFrame.getContentPane().add(lblNewLabel_5);
		
		testData = new JTextField();
		testData.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
		testData.setBounds(59, 529, 207, 30);
		internalFrame.getContentPane().add(testData);
		testData.setColumns(10);
		
		/*
		 JRadioButton male = new JRadioButton("male");
	     JRadioButton female = new JRadioButton("Female");
	     
	     
	     internalFrame.getContentPane().add(male);
	     */
		  JRadioButton genericM = new JRadioButton("Generic Method");
		  genericM.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
		     genericM.setBounds(10, 284, 160, 23);
		    // genericM.setSelected(true);
		     internalFrame.getContentPane().add(genericM);
		     
		     JRadioButton applicationM = new JRadioButton("Application Method");
		     applicationM.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
		     applicationM.setBounds(167, 284, 179, 23);
		     internalFrame.getContentPane().add(applicationM);
	     
		     JRadioButton waitRadio = new JRadioButton("Wait Method");
		     waitRadio.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
		     waitRadio.setBounds(346, 284, 160, 23);
		     internalFrame.getContentPane().add(waitRadio);
		     
		 dvalue = new JRadioButton("Data Value");
		dvalue.setBounds(59, 499, 109, 23);
		dvalue.setSelected(true);
		internalFrame.getContentPane().add(dvalue);
		
		 dparam = new JRadioButton("Data Param");
		dparam.setBounds(186, 499, 96, 23);
		internalFrame.getContentPane().add(dparam);
		internalFrame.setVisible(true);
		ButtonGroup bG = new ButtonGroup();
	     bG.add(dvalue);
	     bG.add(dparam);
	     
	     ButtonGroup mgroup = new ButtonGroup();
	     
	     mgroup.add(genericM );
	     mgroup.add(applicationM);
	     mgroup.add(waitRadio);
	     
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
	     btnRemove.setIcon(new ImageIcon("C:\\Users\\dt77419\\Desktop\\STAF\\Images\\close.png"));
			btnRemove.setToolTipText("Delete Record");
			btnRemove.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
			btnRemove.setBounds(600, 800,100, 35);
			
			contentPane.add(btnRemove);
			
			JButton btnSave = new JButton("Save");
			btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
							insertRecords();
						
					
				}
			});
			btnSave.setIcon(new ImageIcon("C:\\Users\\dt77419\\Desktop\\STAF\\Images\\saveicon.png"));
			btnSave.setToolTipText("Save Record");
			btnSave.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
			btnSave.setBounds(700, 800, 100, 35);
		
			contentPane.add(btnSave);
			
			 JButton btnHome = new JButton("Home");
			  //btnHome.setIcon(new ImageIcon("C:\\Users\\dt77419\\Desktop\\STAF\\Images\\home.png"));
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
			  
			   btnHome.setBounds(800, 800, 100, 35);
			   contentPane.add(btnHome);
			   
			
	     JButton insertStep = new JButton("Insert Test Step");
	    // insertStep.setForeground(Color.WHITE);
	     insertStep.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
	     insertStep.setBounds(414, 533, 160, 37);
	    // insertStep.setBackground(new Color(0,106, 78));
	     internalFrame.getContentPane().add(insertStep);
	     
	     
	     getGroupName();
	     
	     
	     groupname = new JComboBox(gdata.toArray());
	     groupname.setBounds(187, 338, 165, 30);
	     groupname.setVisible(false);
	     
	     internalFrame.getContentPane().add(groupname);
	     
	    bmethod = new JComboBox();
	     bmethod.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
	     bmethod.setBounds(188, 404, 179, 30);
	     bmethod.setVisible(false);
	     internalFrame.getContentPane().add(bmethod);
	     
	     
	     groupname.addItemListener(new ItemListener() {
			  public void itemStateChanged(ItemEvent event) {
				  String item;
				  
				  				
				  if (event.getStateChange() == ItemEvent.SELECTED)
				  
				  {
					  
			          item = (String) event.getItem();
			          
			          if(!item.isEmpty())
			        	 
			          {
			       
			        	 bmethod.removeAllItems();
				        	
			        	 getGroupMethod(item.trim());
				         		
			        	 bmethod.setModel(new DefaultComboBoxModel(gmethod.toArray()));
			        	  
			        	 
					
				
			          }
				  }
			}
		});
	     
	     
	     
	     
	     mlable = new JLabel("Methods");
	     mlable.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
	     mlable.setBounds(186, 379, 69, 14);
	     mlable.setVisible(false);
	     internalFrame.getContentPane().add(mlable);
	     
	     glabel = new JLabel("Group Name");
	     glabel.setBounds(177, 313, 74, 14);
	     glabel.setVisible(false);
	     internalFrame.getContentPane().add(glabel);
	     
	     getWaitMethod();
	     
	    waitBox = new JComboBox(waitList.toArray());
	     waitBox.setBounds(351, 339, 155, 30);
	     waitBox.setVisible(false);
	     internalFrame.getContentPane().add(waitBox);
	     
	   
	     
	   
	     insertStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title=(tcTitle.getText().trim().replaceAll(" ",""));
				String uiname=(String) uiName.getSelectedItem();
				String uitype=uiTypeValue.getText().trim();
				String lname=lValue.getText().trim();
				String ltype=lTypeValue.getText().trim();
				String activity=(String) activeButton.getSelectedItem();
				String data=testData.getText().trim();
				
				if(activeButton.isVisible())
				{
				
				if( !title.isEmpty() && (!uiname.isEmpty()) && (!uitype.isEmpty()) && ( !lname.isEmpty()) && (!ltype.isEmpty()) )
		{
				 md.addRow(new Object[]{title,uiname,lname,ltype,activity,data});
		}
				}
				
				if((bmethod.isVisible()) &&  (bmethod.getSelectedItem()!= null && groupname.getSelectedItem()!= null))
				{
				
		
				 md.addRow(new Object[]{title,"","","",bmethod.getSelectedItem(),""});
		
				}
				
				if((waitBox.isVisible()) )
				{
				
		
				 md.addRow(new Object[]{title,"","","",waitBox.getSelectedItem(),""});
		
				}
				
				
				
			}
		});
	     
         Object[] cols={"TCID","UIName","Locator","Locator_Type","Activity","Data"};
		
		 
		 md=new DefaultTableModel(cols,0);
		 getContentPane().setLayout(null);
		
		
		  
		 tb=new JTable(md);
	   //tb.setBounds(30,40,200,300);
		
		 tb.getTableHeader().setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		 tb.getTableHeader().setBackground(new Color(119,162,248));
		 tb.getTableHeader().setForeground(Color.white);
		
		 tb.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
		    sp=new JScrollPane(tb);  
		    sp.setLocation(600, 200);
		    //sp.setBounds(100, 100, 1000, 600);
		    Dimension screenSize1 = Toolkit.getDefaultToolkit().getScreenSize();
		    double height1 = screenSize1.height/2;
		    double width1 = screenSize1.width/2;
		   sp.setSize(900,600);
		    sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		    sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		   
		    getContentPane().add(sp);
		    
		    startTC = new JButton("Start TestCase");
		    startTC.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		
		    		String str=tcTitle.getText().replaceAll(" ", "");
		    		if(!str.isEmpty())
		    		{
		    			internalFrame.setVisible(true);
		    			internalFrame.setEnabled(true);
		    			sp.setVisible(true);
		    			md.addRow(new Object[]{str,"","","","Start_TestCase",""});
		    			
		    		}
		    		
		    		startTC.setEnabled(false);
		    		 endTC.setEnabled(true);
		    	}
		    });
		    startTC.setForeground(Color.WHITE);
		    startTC.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
		    startTC.setBounds(822, 137, 144, 30);
		    startTC.setBackground(new Color(0,106, 78));
		    contentPane.add(startTC);
		    
		     endTC = new JButton("Stop TestCase");
		    endTC.setEnabled(false);
		    endTC.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		
		    		String str=tcTitle.getText().replaceAll(" ", "");
		    		if(!str.isEmpty())
		    		{
		    			
		    			md.addRow(new Object[]{str,"","","","Stop_TestCase",""});
		    			
		    		}
		    		
		    		endTC.setEnabled(false);
		    		startTC.setEnabled(true);
		    	}
		    });
		    endTC.setForeground(Color.WHITE);
		    endTC.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
		    endTC.setBounds(962, 137, 129, 30);
		    endTC.setBackground(new Color(203, 51, 18 ));
		    contentPane.add(endTC);
	    
		    
		    
	     ActionListener sliceActionListener = new ActionListener() {
	         public void actionPerformed(ActionEvent actionEvent) {
	           AbstractButton aButton = (AbstractButton) actionEvent.getSource();
	          
	           
	           testData.setText(null);
	           if(aButton.getText().equals("Data Param"))
	           {
	        	   testData.setText("PARAM|"+uiName.getSelectedItem());
	        	   
	           }
	           if(aButton.getText().equals("Generic Method"))
	           {
	        	   btnActivity.setVisible(true);
	        	   activeButton.setVisible(true);
	        	   mlable.setVisible(false);
	        	   bmethod.setVisible(false);
	        	   glabel.setVisible(false);
	        	   groupname.setVisible(false);
	        	   waitBox.setVisible(false);
	        	   
	           }
	           if(aButton.getText().equals("Application Method"))
	           {
	        	  
	        	   btnActivity.setVisible(false);
	        	   activeButton.setVisible(false);
	        	   mlable.setVisible(true);
	        	   bmethod.setVisible(true);
	        	   glabel.setVisible(true);
	        	   groupname.setVisible(true);
	        	   waitBox.setVisible(false);
	        	   
	           }
	           
	           if(aButton.getText().equals("Wait Method"))
	           {
	        	  
	        	   btnActivity.setVisible(false);
	        	   activeButton.setVisible(false);
	        	   mlable.setVisible(false);
	        	   bmethod.setVisible(false);
	        	   glabel.setVisible(false);
	        	   groupname.setVisible(false);
	        	   waitBox.setVisible(true);
	           }
	           
	         }
	       };
	       
	       dparam.addActionListener(sliceActionListener);
		     dvalue.addActionListener(sliceActionListener);
	       genericM.addActionListener(sliceActionListener);
	       applicationM.addActionListener(sliceActionListener);
	       waitRadio.addActionListener(sliceActionListener);
		uiName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//getMiscData();
				
			}
		});
		uiName.addItemListener(new ItemListener() {
			
			 public void itemStateChanged(ItemEvent event) {
				  String item;
				  				
				  if (event.getStateChange() == ItemEvent.SELECTED)
				  
				  {
					  
			          item = (String) event.getItem();
			          
			         	          
			      
			          if(!item.isEmpty())
			        	   
			        	  
			          {
			        	 
			        	  getMiscData();
			        	  dvalue.setSelected(true);
			        	  testData.setText(null);
			          }
			
			          
			          
			          
			       }
				 
		        }
		    });
			
		
		  
			
		 
		
		  orData.addItemListener(new ItemListener() {
			  public void itemStateChanged(ItemEvent event) {
				  String item;
				  				
				  if (event.getStateChange() == ItemEvent.SELECTED)
				  
				  {
					  
			          item = (String) event.getItem();
			          
			         	          
			      
			          if(!item.isEmpty())
			        	   
			        	  
			          {
			        	 
			          uiName.removeAllItems();
			        				        	
			          getUIData();
			         		
			          uiName.setModel(new DefaultComboBoxModel(ui.toArray()));
				 	
				 		
			          }
			
			          
			          
			          
			       }
				 
		        }
		    });
		 
		
		
	}
}
