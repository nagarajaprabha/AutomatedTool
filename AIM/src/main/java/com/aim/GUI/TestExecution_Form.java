package com.aim.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.aim.frameworkSQLDB.CreateNewDB;
import com.aim.frameworkSQLDB.GetTestDataTable;
import com.aim.frameworkSQLDB.TestCasesList;
import com.aim.frameworkSQLDB.TestExecution;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TestExecution_Form extends JFrame {

	private static JPanel contentPane;

	/**
	 * Launch the application.
	 */

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
			rs=stmt.executeQuery("SELECT DISTINCT(TSID) FROM MasterTestSuite WHERE Runmode='Yes' ;");
			while(rs.next())
			{
				al.add(rs.getString(1));
				
			}
			
	
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
	
		
	Object[] cols={"TestCase","RunMode"};
	
	 
	 md=new DefaultTableModel(cols,0);
	contentPane.setLayout(null);
	
	 tb=new JTable(md);
	
	 tb.getTableHeader().setFont(new Font("Bookman Old Style", Font.BOLD, 12));
	 tb.getTableHeader().setBackground(new Color(119,162,248));
	 tb.getTableHeader().setForeground(Color.white);
	
	 tb.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
	    JScrollPane sp=new JScrollPane(tb);  

	screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    double height = screenSize.height/2;
	    int width = screenSize.width/2;

	 
	 sp.setBounds(0, 200, width, (int) height);
	   
	   contentPane.add(sp);
	   
	   TableColumn sportColumn = ( tb.getColumnModel().getColumn(1));
	   	     
	   	     JComboBox comboBox = new JComboBox();
	   	     comboBox.addItem("Yes");
	   	     comboBox.addItem("No");
	   sportColumn.setCellEditor(new DefaultCellEditor(comboBox));
 	 
	}
public void addRow(String data)
{
	
	md.fireTableDataChanged();
	//md.addRow(new Object[]{data,""});
	clearData();
	 tp=TestExecution.getTableData(data);
	
	  Set<Integer> keyid = tp.keySet();
	     
      
	   
	     
	     for (Integer key : keyid)
	     {
	    			    	
	        Object [] objectArr = tp.get(key);
	     
	       		     		           
	           md.addRow(objectArr);   
	           
	     }  
	   
	    
	
}

public void updateTestCaseMaster()
{
	String rname=(String) releaseData.getSelectedItem();
	
	 

	Connection c=getConnect();
try {
	String sql;
	for(int row=0;row<tb.getRowCount();row++)
	  {
	 sql="UPDATE TestCase_Master SET Runmode='" + tb.getValueAt(row,1) +"' WHERE release_name='" +rname+"' AND tc_name='" + tb.getValueAt(row,0) +"';";
	stmt.executeUpdate(sql);
	  }
	stmt.close();
	c.close();
} 
catch (SQLException e) {
	
	e.printStackTrace();
}
	
	
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

	
	public TestExecution_Form() throws Exception {
		conn=CreateNewDB.getConn();
		getReleaseData();
		setUndecorated( true );
		//setBackground(new Color(119,162,248));
		setDefaultLookAndFeelDecorated( false );
		getRootPane( ).setWindowDecorationStyle(JFrame.DISPOSE_ON_CLOSE );
		setBounds(100, 100, 725, 406);
		setTitle("AIM-Automation Integration Manager");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground(new Color(119,162,248));
		contentPane.setLayout(null);
		
		
		
		
		JLabel label = new JLabel("Select Release:");
		label.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
		label.setBounds(29, 72, 100, 27);
		contentPane.add(label);
		
	
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
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTestCaseMaster();
				
				
			}
		});
		//btnSave.setIcon(new ImageIcon("C:\\Users\\dt77419\\Desktop\\STAF\\Images\\saveicon.png"));
		btnSave.setToolTipText("Save Record");
		btnSave.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		//btnSave.setBackground(new Color(0, 106, 78));
		btnSave.setBounds(600, 92, 100, 37);
		contentPane.add(btnSave);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		//btnHome.setIcon(new ImageIcon("C:\\Users\\dt77419\\Desktop\\STAF\\Images\\home.png"));
		//btnHome.setToolTipText("Go to Home");
		btnHome.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		//btnHome.setBackground(Color.WHITE);
		btnHome.setBounds(700, 92, 100, 37);
		contentPane.add(btnHome);
		
		
	}
}
