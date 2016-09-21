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
import java.beans.PropertyVetoException;
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
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.aim.frameworkSQLDB.CreateNewDB;
import com.aim.frameworkSQLDB.GetTestDataTable;
import com.aim.frameworkSQLDB.TestCasesList;

public class TestDataEntry_Form extends JFrame {

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
	
	Object[] cols={"TestCase"};
	
	 
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
	    int width = screenSize.width/2;
	  // sp.setSize(width, (int) height/2);
	    sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	 sp.setBounds(0, 200, width, (int) height);
	   
	   contentPane.add(sp);
		 
	     tb.addMouseListener(new java.awt.event.MouseAdapter() {
	    	    @Override
	    	    public void mouseClicked(java.awt.event.MouseEvent evt) {
	    	    	{
	    	    		String  rName=(String) releaseData.getSelectedItem();
	    	    		String tName=(String) tb.getValueAt(tb.getSelectedRow(),0);
	    	    		
	    	    		
	    	    		if(internalFrame !=null) {//make sure its not null
	    	    	           internalFrame.dispose(); 
	    	     		
	    	     				
	    	     		        internalFrame = new JInternalFrame("TestData Sheet", true, true, true, true);
	    	     		        internalFrame.setBounds(800, 200, 700, 450);
	    	     		       ((javax.swing.plaf.basic.BasicInternalFrameUI) 
	    	     						internalFrame.getUI()).setNorthPane(null);
	    	     		       contentPane.add(internalFrame, new Integer(1));
	    	     		     
	    	     		     
	    	     				
	    	     		      
	   	    	    	    dataTable=GetTestDataTable.getTestData("TestData_"+rName+"_"+tName);
	   	    	    	    
	   	    	    	    dataTable.getTableHeader().setFont(new Font("Bookman Old Style", Font.BOLD, 12));
	   	    	     		dataTable.getTableHeader().setBackground(new Color(119,162,248));
	   	    	     		dataTable.getTableHeader().setForeground(Color.white);

	   	    	     		dataTable.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
	   	    	     		dataTable.setEnabled(true);
	   	    	     		
	   	    	      	    dp=new JScrollPane(dataTable); 
	   	    	      	   internalFrame.getContentPane().add(dp);
	   	    	      	internalFrame.repaint();
	   	    	      	SwingUtilities.updateComponentTreeUI(dp);
	   	    	      	  internalFrame.setVisible(true); 
	   	    	       TableColumn sportColumn = (dataTable.getColumnModel().getColumn(2));
	   	   	     
	   	   	     JComboBox comboBox = new JComboBox();
	   	   	     comboBox.addItem("Yes");
	   	   	     comboBox.addItem("No");
	   	   	 sportColumn.setCellEditor(new DefaultCellEditor(comboBox));
	    		    	    	}
	    	    		else
	    	    		{
	    	    			internalFrame = new JInternalFrame("TestData Sheet", true, true, true, true);
    	     		        internalFrame.setBounds(800, 200, 600, 400);
    	     		       contentPane.add(internalFrame, new Integer(1));
    	     		      
   	    	    	    dataTable=GetTestDataTable.getTestData("TestData_"+rName+"_"+tName);
   	    	    	    
   	    	    	    dataTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
   	    	     		dataTable.getTableHeader().setBackground(Color.GRAY);
   	    	     		dataTable.getTableHeader().setForeground(Color.white);

   	    	     		dataTable.setFont(new Font("Serif", Font.PLAIN, 12));
   	    	     		dataTable.setEnabled(true);
   	    	     		
   	    	      	    dp=new JScrollPane(dataTable); 
   	    	      	   internalFrame.getContentPane().add(dp);
   	    	      	internalFrame.repaint();
   	    	      	SwingUtilities.updateComponentTreeUI(dp);
   	    	      	  internalFrame.setVisible(true); 
   	    	      	TableColumn sportColumn = (dataTable.getColumnModel().getColumn(2));
   	   	   	     
   	   	   	     JComboBox comboBox = new JComboBox();
   	   	   	     comboBox.addItem("Yes");
   	   	   	     comboBox.addItem("No");
   	   	   sportColumn.setCellEditor(new DefaultCellEditor(comboBox));
	    	    			
	    	    		}
	    		    	   
	    	     			
	    	    	
	    		    	    	}
	    	    	
	    	    }
	    	        
	    	
	    	});
	     
	     
	     
	     
	     
	     
		 
	}
public void addRow(String data)
{
	
	md.fireTableDataChanged();
	//md.addRow(new Object[]{data,""});
	clearData();
	 tp=TestCasesList.getTableData(data);
	
	  Set<Integer> keyid = tp.keySet();
	     
      
	   
	     
	     for (Integer key : keyid)
	     {
	    			    	
	        Object [] objectArr = tp.get(key);
	     
	       		     		           
	           md.addRow(objectArr);   
	           
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





	


	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public  void insertRecords()
	{
		
		try
		{
			Connection c=getConnect();
		
			c.setAutoCommit(true);
			String  rName=(String) releaseData.getSelectedItem();
			String tName=(String) tb.getValueAt(tb.getSelectedRow(),0);
			String testDataTable="TestData_"+rName+"_"+tName;
			
			
			 
		   String sql=null;
		   String colName=null;
		   Object[] colArray = new Object[dataTable.getColumnCount()];
		   
		   for(int col=0;col<dataTable.getColumnCount();col++)
				{
			   System.out.println(dataTable.getColumnName(col));
			   colArray[col]=dataTable.getColumnName(col);
			   
				}
		  

			
			  
			  for(int row=0;row<dataTable.getRowCount();row++)
			  {
				  sql = "INSERT INTO "+testDataTable+ " (";
			for(int col=0;col<dataTable.getColumnCount();col++)
			{
				if(col+1==dataTable.getColumnCount())
				{
					sql = sql + dataTable.getColumnName(col)+") values (";
				}
				else
				{
					sql = sql + dataTable.getColumnName(col)+",";
				}
			}
			
			for(int col=0;col<dataTable.getColumnCount();col++)
			{
				if(col+1==dataTable.getColumnCount())
				{
					sql = sql+"'" + dataTable.getValueAt(row, col)+"')";
				}
				else
				{
					sql = sql +"'"+ dataTable.getValueAt(row, col)+"',";
				}
			}
			System.out.println("sql =  "+sql);
			
						//("+dataTable.getColumnName(col)+"')VALUES('"+dataTable.getValueAt(row, col) +"');";
		    stmt.execute(sql);
			}
			  
			  
			 
	   	
	          stmt.close();
	          c.close();
     	
		}
		catch(Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
			
		}
		 System.out.println("Records created successfully");
	
	}
	public  void deleteRecords() 
	{
		try
		{
			String  rName=(String) releaseData.getSelectedItem();
			String tName=(String) tb.getValueAt(tb.getSelectedRow(),0);
			String testDataTable="TestData_"+rName+"_"+tName;
			
			Connection c=getConnect();
			 String sql = "DELETE  from  " +testDataTable;
		      stmt.executeUpdate(sql);
		    		  	 stmt.close();
		    		  	
		    		c.close();
		    				
		}
		
		catch(Exception e)
		{
		System.out.println(e.getMessage());
		}
	 
	}
	public TestDataEntry_Form() throws Exception {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\dt77419\\Desktop\\STAF\\Images\\DSTLogo.gif"));

		 
		conn=CreateNewDB.getConn();
		getReleaseData();
		setTitle("TestData Sheet");
		setBounds(100, 100, 940, 1171);
		setUndecorated( true );
		
		
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(119,162,248));
		setDefaultLookAndFeelDecorated( false );
		getRootPane( ).setWindowDecorationStyle(JFrame.DISPOSE_ON_CLOSE );
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
		btnDelete.setIcon(new ImageIcon("C:\\Users\\dt77419\\Desktop\\STAF\\Images\\close.png"));
		btnDelete.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent e) {
		   		
		   		
		   		
		   		GetTestDataTable.deletteDataRow();
		   		
		   	}
		   });
		btnDelete.setToolTipText("Delete Record");
		btnDelete.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		btnDelete.setBounds(600, 800, 100, 37);
	
		contentPane.add(btnDelete);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteRecords();
				insertRecords();
			}
		});
		//btnSave.setIcon(new ImageIcon("C:\\Users\\dt77419\\Desktop\\STAF\\Images\\saveicon.png"));
		btnSave.setToolTipText("Save Record");
		btnSave.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		btnSave.setBounds(700,800, 100, 37);
		
		contentPane.add(btnSave);
		JButton btnHome = new JButton("Home");
		 // btnHome.setIcon(new ImageIcon("C:\\Users\\dt77419\\Desktop\\STAF\\Images\\home.png"));
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
		
		JButton btnNewButton = new JButton("Add New");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			 	//testDataModel.addRow(new Object[]{"","","","",""});
				String tName=(String) tb.getValueAt(tb.getSelectedRow(),0);
				String rdata=(String) releaseData.getSelectedItem();
			 	GetTestDataTable.addTestDataRow(rdata,tName);
			 
			}
		});
		//btnNewButton.setIcon(new ImageIcon("C:\\Users\\dt77419\\Desktop\\STAF\\Images\\Add.png"));
		btnNewButton.setToolTipText("Add Record");
		btnNewButton.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		btnNewButton.setBounds(900,800, 100, 37);
		
		contentPane.add(btnNewButton);
		
		
		
		
	}
}
