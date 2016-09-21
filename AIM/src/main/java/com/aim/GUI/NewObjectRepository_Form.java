package com.aim.GUI;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

//import com.jgoodies.forms.layout.FormLayout;
import com.aim.frameworkSQLDB.ActivityMaster_Data;
import com.aim.frameworkSQLDB.CreateNewDB;
//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.layout.RowSpec;
//import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;


public class NewObjectRepository_Form extends JFrame {
	private static JPanel contentPane;
	private JTextField uiName;
	private JTextField locatorName;
	private JTextField orTitle;
    static Statement stmt=null;
    static ResultSet rs;
    static ArrayList<String> al;
    static JComboBox uiType, locatorType;
    static int checkTableCount=0;
	
	static JTable tb;
	static DefaultTableModel md;
	static Map < Integer, Object[] > tp;
	
	
	/**
	 * Launch the application.
	 */

	
	
	public void getUITypeList()
	{
		al=new ArrayList<String>();
		al.add("");
		try
		{
			CreateNewDB.CreateDB();
			CreateNewDB.conn.setAutoCommit(false);
			stmt=CreateNewDB.conn.createStatement();
			rs=stmt.executeQuery("SELECT DISTINCT(objecttype) FROM Activity_Master;");
			while(rs.next())
			{
				al.add(rs.getString("objecttype"));
				
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
	
	public  void checkTableExist()
	{
		String title="OR_"+orTitle.getText().trim();
		
		try
		{
			CreateNewDB.CreateDB();
			CreateNewDB.conn.setAutoCommit(false);
			stmt=CreateNewDB.conn.createStatement();
			
			rs=stmt.executeQuery("SELECT count(name) FROM sqlite_master WHERE type='table' AND name='"+title+"' ;" );
			while(rs.next())
			{
				
				checkTableCount=rs.getInt(1);
				
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
	
	public void createNewOR()
	{
		String title="OR_"+orTitle.getText().trim();
		
		if( (orTitle.getText() !=null) || (orTitle.getText() !=""))
		{
			checkTableExist();
			if(checkTableCount==0)
			{
		
		try
		{
		CreateNewDB.CreateDB();
		stmt=CreateNewDB.conn.createStatement();
				 
		 String sql="CREATE TABLE  "    + title + 
                 "(uiname TEXT, " + 
                 " uitype    TEXT     , " + 
                 " locator    NVARCHAR(400)     , " + 
                 " locatortype   TEXT   )";
		 
    stmt.executeUpdate(sql);
    
    
    stmt.close();
    CreateNewDB.conn.close();
		}
		catch(Exception e)
		{
			 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
			
		}
			}
			else
			{
				System.out.println("Table Exist");
				
			}
		}
		
	}
	
	
	public  void insertRecords()
	{
		String title="OR_"+orTitle.getText().trim();
		
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
		    	
		    	
		   				sql= "INSERT INTO " +title+"(uiname,uitype,locator,locatortype) " +
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
		
		
		
		
		
	}
	/**
	 * Create the frame.
	 */
	public NewObjectRepository_Form() {
		getContentPane().setBackground(new Color(119,162,248));
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\dt77419\\Desktop\\STAF\\Images\\dst.jpg"));
		setFont(new Font("Bookman Old Style", Font.BOLD, 30));
		setTitle("Object Repository Creation Form");
		getUITypeList();
		String Ltype[]={"","id","name","xpath","linkText","partialLinkText","tagName","className","cssSelector"};
		getContentPane().setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1257, 569);
		getContentPane().setLayout(null);
		setUndecorated( true );
		//setBackground(new Color(119,162,248));
		setDefaultLookAndFeelDecorated( false );
		getRootPane( ).setWindowDecorationStyle(JFrame.DISPOSE_ON_CLOSE );
		
		
		JLabel lblNewLabel = new JLabel("UI Name:");
		lblNewLabel.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		lblNewLabel.setBounds(23, 136, 81, 14);
		lblNewLabel.setForeground(Color.white);
		getContentPane().add(lblNewLabel);
		
		uiName = new JTextField();
		uiName.setBounds(23, 150, 384, 27);
		getContentPane().add(uiName);
		uiName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("UI Type:");
		lblNewLabel_1.setForeground(Color.white);
		lblNewLabel_1.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		lblNewLabel_1.setBounds(23, 188, 81, 14);
		getContentPane().add(lblNewLabel_1);
		
		locatorName = new JTextField();
		locatorName.setBounds(23, 304, 384, 27);
		getContentPane().add(locatorName);
		
		locatorName.setColumns(10);
		
		
		JLabel lblNewLabel_3 = new JLabel("Locator Type:");
		lblNewLabel_3.setForeground(Color.white);
		lblNewLabel_3.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		lblNewLabel_3.setBounds(23, 239, 114, 14);
		getContentPane().add(lblNewLabel_3);
		
		locatorType = new JComboBox(Ltype);
		locatorType.setBounds(23, 253, 384, 27);
				
		getContentPane().add(locatorType);
		
		
		JLabel lblNewLabel_2 = new JLabel("Locator:");
		lblNewLabel_2.setForeground(Color.white);
		lblNewLabel_2.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		lblNewLabel_2.setBounds(23, 291, 114, 14);
		getContentPane().add(lblNewLabel_2);
		
		uiType = new JComboBox(al.toArray());
		uiType.setBounds(23, 201, 384, 27);
		getContentPane().add(uiType);
		
	
		
	
		
		
		JButton insertRow = new JButton("Insert");
		//insertRow.setIcon(new ImageIcon("C:\\Users\\dt77419\\Desktop\\STAF\\Images\\addnew.png"));
		insertRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title=(orTitle.getText().trim());
				String uiname=uiName.getText().trim();
				String uitype=(String) uiType.getSelectedItem();
				String lname=locatorName.getText().trim();
				String ltype=(String)locatorType.getSelectedItem();
				
				if( !title.isEmpty() && (!uiname.isEmpty()) && (!uitype.isEmpty()) && ( !lname.isEmpty()) && (!ltype.isEmpty()) )
		{
				 md.addRow(new Object[]{uiName.getText(),uiType.getSelectedItem(),locatorName.getText(),locatorType.getSelectedItem()});
		}
				
				uiName.setText(null);
				uiType.setSelectedItem(null);
				locatorType.setSelectedItem(null);
				locatorName.setText(null);
				
			}
		});
		insertRow.setBounds(417, 328, 100, 39);
		getContentPane().add(insertRow);
		
		JLabel lblNewLabel_4 = new JLabel("ObjectRepository Title:");
		lblNewLabel_4.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		lblNewLabel_4.setBounds(49, 51, 172, 14);
		lblNewLabel_4.setForeground(Color.white);
		getContentPane().add(lblNewLabel_4);
		
		orTitle = new JTextField();
		orTitle.setBounds(203, 46, 344, 27);
		getContentPane().add(orTitle);
		orTitle.setColumns(10);
		
		
		
		Object[] cols={"UI Name","UI Type","Locator","Locator Type"};
		
		 
		 md=new DefaultTableModel(cols,0);
		 getContentPane().setLayout(null);
		
		
		  
		 tb=new JTable(md);
	   //tb.setBounds(30,40,200,300);
		
		 tb.getTableHeader().setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		 tb.getTableHeader().setBackground(new Color(119,162,248));
		 tb.getTableHeader().setForeground(Color.white);
		
		 tb.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
		    JScrollPane sp=new JScrollPane(tb);  
		    sp.setLocation(23, 389);
		    //sp.setBounds(100, 100, 1000, 600);
		    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		    
		    sp.setSize((int)1000,300 );
		    sp.setAutoscrolls(true);
		    getContentPane().add(sp);
		    
		    JButton saveButton = new JButton("Save");
		    //saveButton.setIcon(new ImageIcon("C:\\Users\\dt77419\\Desktop\\STAF\\Images\\saveicon.png"));
		    saveButton.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		
		    		
		    	
		    		if( (tb.getRowCount()>=1)  )
		    		{
		    			if( (tb.getValueAt(1, 0) !=null) || ( tb.getValueAt(1, 0) !=""))
		    			{
		    			createNewOR();
		    			insertRecords();
		    			JOptionPane.showMessageDialog(null,"Object Repository File with Name " +orTitle.getText()+" Created Successfully","Successfully Created",JOptionPane.INFORMATION_MESSAGE);
		    			}
		    			
		    		}
		    		
		    		
		    		
		    	}
		    });
		    saveButton.setBounds(350, 769, 100, 50);
		    //saveButton.setBackground(new Color(0,106, 78));
		    getContentPane().add(saveButton);
		    
		    JButton btnHome = new JButton("Home");
			 // btnHome.setIcon(new ImageIcon("C:\\Users\\dt77419\\Desktop\\STAF\\Images\\home.png"));
			   btnHome.setToolTipText("Go to Home");
			 //  btnHome.setSelectedIcon(new ImageIcon("C:\\Users\\dt77419\\Desktop\\STAF\\Images\\home.png"));
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
			   btnHome.setBounds(425, 769, 100, 50);
			   getContentPane().add(btnHome);
		
	}
}
