package com.aim.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.apache.poi.common.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FontUnderline;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFFont;

import com.aim.frameworkSQLDB.CreateNewDB;
import com.aim.frameworkSQLDB.SelectTableData;
import com.aim.test.Constants;
import javax.swing.table.AbstractTableModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;

import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
public class MTS_Form extends JFrame  {

	private static final String WHEN_FOCUSED = null;
	private static JPanel contentPane;
	private boolean DEBUG = false;
	static JTable tb;
	static DefaultTableModel md;
	static Map < Integer, Object[] > tp;
	static Statement stmt=null;
	
	  	


	/**
	 * Create the frame.
	 */
	
	public static void JTableData()
	{
tp=SelectTableData.getTableData();
		
		Object[] cols={"TSID","Description","Runmode"};
		
		 
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
		    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		    double height = screenSize.height/1.2;
		    int width = screenSize.width;
		    sp.setSize(width, (int) height);
		   
		   contentPane.add(sp);
		   TableColumn sportColumn = (tb.getColumnModel().getColumn(2));
 	   	     
 	   	     JComboBox comboBox = new JComboBox();
 	   	     comboBox.addItem("Yes");
 	   	     comboBox.addItem("No");
 	   	 sportColumn.setCellEditor(new DefaultCellEditor(comboBox));
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
			 String sql = "DELETE  from MasterTestSuite;";
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
		   			
		   				
		   				sql= "INSERT INTO MasterTestSuite(TSID,Description,Runmode) " +
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
	public MTS_Form() {
		setFont(new Font("Bookman Old Style", Font.BOLD, 16));
		setTitle("Master Test Suite Form");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\dt77419\\Desktop\\STAF\\Images\\dst.jpg"));
	
		setUndecorated( true );
		//setBackground(new Color(119,162,248));
		setDefaultLookAndFeelDecorated( false );
		getRootPane( ).setWindowDecorationStyle(JFrame.DISPOSE_ON_CLOSE );
		setBounds(100, 100, 1000, 1000);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(119,162,248));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
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
		  
		   btnHome.setBounds(700, 769, 100, 37);
		  // btnHome.setBackground(new Color(0,106, 78));
		   contentPane.add(btnHome);
		   JButton btnDelete = new JButton("Delete");
		   btnDelete.setToolTipText("Delete Record");
		  // btnDelete.setIcon(new ImageIcon("C:\\Users\\dt77419\\Desktop\\STAF\\Images\\close.png"));
		   btnDelete.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		  // btnDelete.setBackground(new Color(0,106, 78));
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
		   btnDelete.setBounds(400, 770, 100, 37);
		   contentPane.add(btnDelete);
		   
		   JButton btnNewButton = new JButton("Add New");
		   btnNewButton.setToolTipText("Add Record");
		   //btnNewButton.setIcon(new ImageIcon("C:\\Users\\dt77419\\Desktop\\STAF\\Images\\Add.png"));
		   btnNewButton.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		  // btnNewButton.setBackground(new Color(0,106, 78));
		   btnNewButton.setBounds(500, 770, 100, 37);
		   contentPane.add(btnNewButton);
		   
		   JButton btnSave = new JButton("Save");
		   //btnSave.setIcon(new ImageIcon("C:\\Users\\dt77419\\Desktop\\STAF\\Images\\saveicon.png"));
		   btnSave.setToolTipText("Save Record");
		   //btnSave.setSelectedIcon(new ImageIcon("C:\\Users\\dt77419\\Desktop\\STAF\\Images\\saveicon.png"));
		  
		   btnSave.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent e)
		   	{
		   	deleteRecords();
		   		insertRecords();
				
		   		
		   		
		   	}
		   });
		   btnSave.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		   btnSave.setBounds(600, 770, 100, 37);
		   contentPane.add(btnSave);
		   btnNewButton.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent e) {
		   		
		   	 md.addRow(new Object[]{"","",""});
		   	 
		   	}
		   });
		   
		  
		 
		  
		     
		     
		     
		           
		      
	}
}
