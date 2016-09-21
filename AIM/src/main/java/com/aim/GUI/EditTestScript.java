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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.aim.frameworkSQLDB.ActivityMaster_Data;
import com.aim.frameworkSQLDB.CreateNewDB;
import com.aim.frameworkSQLDB.GetTestDataTable;
import com.aim.frameworkSQLDB.TestCasesList;

public class EditTestScript extends JFrame {

	private static JPanel contentPane;
	static JComboBox releaseData;
	static JScrollPane sp,dp;
	static ArrayList<String> al,ol,ui,activity,colList;
	static Statement stmt=null;
	static PreparedStatement pstmt=null;
	static Connection conn=null;
	static ResultSet rs=null;
	static JTable tb,dataTable;
	static DefaultTableModel md=null,testDataModel=null;
	static Map < Integer, Object[] > tp=null,testData=null;;
	static Object [] objectArr=null;
	Set<Integer> keyid=null;
	static Dimension screenSize=null;
	static JInternalFrame internalFrame=null;

	
	public static Connection  getConnect()
	{
		
		 try
		 {
		 Class.forName("org.sqlite.JDBC");
	      conn = DriverManager.getConnection("jdbc:sqlite:AutomatonFramework.db");
	 	
		stmt=conn.createStatement();
		 }
		 catch(Exception e)
		 {
			 
			 
		 }
		 return conn;
		
	}
	public void getReleaseData()
	{
		al=new ArrayList<String>();
		al.add("");
		try
		{
		
			Connection c=getConnect();
			rs=stmt.executeQuery("SELECT DISTINCT(TSID) FROM MasterTestSuite ;");
			while(rs.next())
			{
				al.add(rs.getString(1));
				
			}
			
			//rs.close();
			stmt.close();
		rs.close();
		c.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			
		}
	
		
	}
public static void JTableData()
	
	{
	
	//tp=ActivityMaster_Data.getTableData();
	
	Object[] cols={"Release","TestCase","UIName","Locator","Locator_Type","Activity ","TestData","RunMode","Data_Flag"};
	
	 
	 md=new DefaultTableModel(cols,0);
	contentPane.setLayout(null);
	
	
	  
	 tb=new JTable(md);
   //tb.setBounds(30,40,200,300);
	
	 tb.getTableHeader().setFont(new Font("Bookman Old Style", Font.BOLD, 12));
	 tb.getTableHeader().setBackground(new Color(119,162,248));
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
	   TableColumn runmode,ltype,activity;
	   runmode=(tb.getColumnModel().getColumn(7));
	   ltype=(tb.getColumnModel().getColumn(4));
	   activity=(tb.getColumnModel().getColumn(5));
	     JComboBox runCombo = new JComboBox();
	     runCombo.addItem("Yes");
	     runCombo.addItem("No");
	     
	     runmode.setCellEditor(new DefaultCellEditor(runCombo));
	     
	     JComboBox locatorCombo = new JComboBox();
	     locatorCombo.addItem("id");
	     locatorCombo.addItem("linkText");
	     locatorCombo.addItem("partialLinkText");
	     locatorCombo.addItem("name");
	     locatorCombo.addItem("xpath");
	     locatorCombo.addItem("className");
	     locatorCombo.addItem("cssSelector");
	     
	     ltype.setCellEditor(new DefaultCellEditor(locatorCombo));
 		   
	     al=ActivityMaster_Data.getKeywords();
 	
	     JComboBox activeCombo = new JComboBox(al.toArray());
	    
	     
	     activity.setCellEditor(new DefaultCellEditor(activeCombo));
	     
		 
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
	//md.addRow(new Object[]{data,""});
	clearData();
	 tp=TestCasesList.getCompleteData(data);
	
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
		String rname=(String) releaseData.getSelectedItem();
		CreateNewDB.CreateDB();
		CreateNewDB.conn.setAutoCommit(false);
		stmt=CreateNewDB.conn.createStatement();
		 String sql = "DELETE  from TestCase_Master WHERE release_name='"+ rname +"';";
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
	    	 String str=tb.getValueAt(row,3).toString();
		    	str=str.replaceAll("'", "''");
	   			
	   				
	   				sql= "INSERT INTO TestCase_Master(release_name,tc_name,UIName,Locator,Locator_Type,Activity,Data,RUNMODE,Data_Flag) " +
			                "VALUES ('" + tb.getValueAt(row,0) +"', '" + tb.getValueAt(row,1) +"','" + tb.getValueAt(row,2) +"','" + str +"','" + tb.getValueAt(row,4) +"','" + tb.getValueAt(row,5) +"','" + tb.getValueAt(row,6) +"','" + tb.getValueAt(row,7) +"','" + tb.getValueAt(row,8) +"' );"; 
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
	public EditTestScript() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Modify TestCases");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setUndecorated( true );
			
		contentPane.setBackground(new Color(119,162,248));
		setDefaultLookAndFeelDecorated( false );
		getRootPane( ).setWindowDecorationStyle(JFrame.DISPOSE_ON_CLOSE );
		//setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\dt77419\\Desktop\\STAF\\Images\\DSTLogo.gif"));

		 
		conn=CreateNewDB.getConn();
		getReleaseData();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 940, 1171);
	
		
		JLabel release = new JLabel("Select Release:");
		release.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
		release.setBounds(126, 83, 100, 27);
		contentPane.add(release);
		
		releaseData = new JComboBox(al.toArray());
		
		releaseData.addItemListener(new ItemListener() {
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
		
		
		releaseData.setBounds(226, 80, 276, 32);
		contentPane.add(releaseData);
		
		
		 
		   JButton btnDelete = new JButton("Delete");
		   btnDelete.setToolTipText("Delete Record");
	
		 
		   btnDelete.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		   btnDelete.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent e) {
		   		
		  
		                md.removeRow(tb.getSelectedRow());
		 	
		   	}
		   });
		   contentPane.setLayout(null);
		   btnDelete.setBounds(600, 800, 100, 37);
		   contentPane.add(btnDelete);
		   
		   JButton btnNewButton = new JButton("Add New");
		   btnNewButton.setToolTipText("Add Record");
		  
		   btnNewButton.setIcon(new ImageIcon("C:\\Users\\dt77419\\Desktop\\STAF\\Images\\Add.png"));
		   btnNewButton.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		   btnNewButton.setBounds(700, 800, 100, 37);
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
		;
		   btnSave.setBounds(800, 800, 100, 37);
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
		 
		   btnHome.setBounds(900, 800, 100, 37);
		   contentPane.add(btnHome);
		   
		   btnNewButton.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent e) {
		   		
		   	 md.addRow(new Object[]{"","",""});
		   	 
		   	}
		   });
		
	}

}
