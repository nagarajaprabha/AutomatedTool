package com.aim.GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import org.apache.log4j.Logger;

import com.aim.frameworkSQLDB.UserProperty;
import com.aim.test.DriverScript;
import com.aim.util.CreateHTMLReports;


import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.Properties;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.SwingConstants;
 
public class MasterForm extends JFrame  {

private static JMenuBar menuBar;
private JPanel contentPane;
private static JMenu mnuFile,mnuTestScript,mnuTestExecution,mnuTestData,mnuObject,mnuActivity,mnuConfigurations,mnuAdmin;
private static JMenuItem mnuOpenMTS,mnuTestPrep,mnuOpenORNew,mnuOpenOREdit,mnuActivityForm,mnuData,mnuEditTest,mnuExecute,startExecute,mnuBusinessFunction,mnuAdminop;
private JLabel footer;
private String userId,osname;

	class MenuItemListener implements ActionListener {
	   
		public void actionPerformed(ActionEvent e) {
			
			if(e.getActionCommand().equals("MTS"))
			{
				try {
					dispose();
					MTS_Form frame = new MTS_Form();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					frame.setVisible(true);
					MTS_Form.JTableData();
				} 
				catch (Exception ee) {
					ee.printStackTrace();
				}
			 
					 
			}
			
			
			if(e.getActionCommand().equals("OpenActivity"))
			{
				try {
					dispose();
					ActivityMaster_Form frame = new ActivityMaster_Form();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					frame.setVisible(true);
					
					ActivityMaster_Form.JTableData();
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			 
				
				
				
			}
			
			
			if(e.getActionCommand().equals("OpenApplication"))
			{
				try {
					dispose();
					BusinessFunction frame = new BusinessFunction();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					frame.setVisible(true);
									} catch (Exception ee) {
					ee.printStackTrace();
				}
			 
				
				
				
			}
			
			
			if(e.getActionCommand().equals("OpenOR"))
			{
				try {
					dispose();
					NewObjectRepository_Form frame =new  NewObjectRepository_Form();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					frame.setVisible(true);
					
				//NewObjectRepository_Form.JTableData();
				} catch (Exception ee) {
					ee.printStackTrace();
				}
				
				
				
				
			}
			
			
			if(e.getActionCommand().equals("OpenOREdit"))
			{
				try {
					dispose();
					ModifyObjectRepository_Form frame =new  ModifyObjectRepository_Form();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					frame.JTableData();
					frame.setVisible(true);
					
					//ModifyObjectRepository_Form.JTableData();
				} catch (Exception ee) {
					ee.printStackTrace();
				}
				
				
				
				
			}	
			
			
			if(e.getActionCommand().equals("editTestScript"))
			{
				try {
					dispose();
					EditTestScript frame = new EditTestScript();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					EditTestScript.JTableData();
					frame.setVisible(true);
					
					//ModifyObjectRepository_Form.JTableData();
				} catch (Exception ee) {
					ee.printStackTrace();
				}
				
				
				
				
			}
			
			if(e.getActionCommand().equals("openTestScript"))
			{
				
				try {
					dispose();
					TestScriptPreparation_Form frame = new TestScriptPreparation_Form();
					frame.setVisible(true);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					
					frame.internalFrame.setVisible(false);
					frame.internalFrame.setEnabled(false);
					frame.sp.setVisible(false);
					//frame.createRCombo();
					//TestScriptPreparation_Form.JTableData();
				} catch (Exception ee) {
					ee.printStackTrace();
				}
				
			}
			
			if(e.getActionCommand().equals("openTestData"))
			{
				try {
					dispose();
					TestDataEntry_Form frame =new 	TestDataEntry_Form();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					frame.setVisible(true);
					
					TestDataEntry_Form.JTableData();
				} catch (Exception ee) {
					ee.printStackTrace();
				}
				
				
				
			}
			
			if(e.getActionCommand().equals("executeTC"))
			{
				try {
					dispose();
					TestExecution_Form frame = new TestExecution_Form();
					frame.setVisible(true);
					TestExecution_Form.JTableData();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				
				} catch (Exception ee) {
					ee.printStackTrace();
				}
				
				
				
			}
			
			
			if(e.getActionCommand().equals("startExe"))
			{
				try {
					DriverScript ds=new DriverScript();
					
					ds.init();

				
				} catch (Exception ee) {
					ee.printStackTrace();
				}
				
				
				
			}
			
			
			if(e.getActionCommand().equals("mngtable"))
			{
				try {
					Admin frame = new Admin();
					frame.setVisible(true);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				
				} catch (Exception ee) {
					ee.printStackTrace();
				}
				
				
				
			}
			
			
			
			
			
			
			
		}

	    
	   }
	public MasterForm() {
		
		userId=UserProperty.getUserInfo();
		osname=UserProperty.getOSInfo();
		setFont(new Font("Bookman Old Style", Font.BOLD, 25));
		setTitle("AIM-Automation Integration Framework");
		setBackground(new Color(119,162,248));
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\dt77419\\Desktop\\STAF\\Images\\DSTLogo.gif"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 631, 402);
		setUndecorated( true );
		setDefaultLookAndFeelDecorated( false );
		getRootPane( ).setWindowDecorationStyle(JFrame.DISPOSE_ON_CLOSE );
		 menuBar = new JMenuBar();
		
		
		//menuBar.setBackground(new Color(0,106,78));
		
	
		menuBar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setJMenuBar(menuBar);
	
		 mnuActivity=new JMenu("Activity");
		  	//mnuFile.setBorder(BorderFactory.createCompoundBorder(mnuFile.getBorder(),BorderFactory.createEmptyBorder(20, 300, 100, 0)));
		 mnuActivity.setForeground(Color.black);
		 mnuActivity.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		 mnuActivity.setMnemonic('A');
		 mnuActivityForm=new JMenuItem("Activity Form");
		 mnuActivity.add(mnuActivityForm);
		 menuBar.add(mnuActivity);
		 mnuActivity.add(new JSeparator());
		mnuBusinessFunction=new JMenuItem("Application Functions");
		mnuActivity.add(mnuBusinessFunction);
		menuBar.add(mnuActivity);	 
		//mnuActivity.add(new JSeparator());		
		
		 mnuObject=new JMenu("ObjectRepository");
		  	//mnuFile.setBorder(BorderFactory.createCompoundBorder(mnuFile.getBorder(),BorderFactory.createEmptyBorder(20, 300, 100, 0)));
		 mnuObject.setForeground(Color.black);
		 mnuObject.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		 mnuObject.setMnemonic('O');
				mnuOpenORNew=new JMenuItem("New OR");
				mnuObject.add(mnuOpenORNew);
				menuBar.add(mnuObject);
				mnuOpenORNew.add(new JSeparator());
				mnuOpenOREdit=new JMenuItem("Edit OR");
				mnuObject.add(mnuOpenOREdit);
				menuBar.add(mnuObject);
				mnuOpenOREdit.add(new JSeparator());
		
		 mnuTestScript=new JMenu("TestScripting");
		  	//mnuFile.setBorder(BorderFactory.createCompoundBorder(mnuFile.getBorder(),BorderFactory.createEmptyBorder(20, 300, 100, 0)));
		 mnuTestScript.setForeground(Color.black);
		 mnuTestScript.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		 mnuTestScript.setMnemonic('T');
				 mnuOpenMTS=new JMenuItem("MasterTestSuite");
				 mnuTestScript.add(mnuOpenMTS);
				menuBar.add(mnuTestScript);
				mnuTestScript.add(new JSeparator());
				 mnuTestPrep=new JMenuItem("Create TestScript");
				 mnuTestScript.add(mnuTestPrep);
				menuBar.add(mnuTestScript);
				mnuTestScript.add(new JSeparator());
				 mnuEditTest=new JMenuItem("Edit TestScript");
				 mnuTestScript.add(mnuEditTest);
				menuBar.add(mnuTestScript);
			
				mnuTestData=new JMenu("TestData");
				  	//mnuFile.setBorder(BorderFactory.createCompoundBorder(mnuFile.getBorder(),BorderFactory.createEmptyBorder(20, 300, 100, 0)));
				mnuTestData.setForeground(Color.black);
				mnuTestData.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
				mnuTestData.setMnemonic('T');
						 mnuData=new JMenuItem("Create TestData");
						 mnuTestData.add(mnuData);
						menuBar.add(mnuTestData);
						mnuTestData.add(new JSeparator());
				
				
						mnuTestExecution=new JMenu("TestExecution");
					  
						mnuTestExecution.setForeground(Color.black);
						mnuTestExecution.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
						mnuTestExecution.setMnemonic('T');
						mnuExecute=new JMenuItem("Execute_TestCases");
						mnuTestExecution.add(mnuExecute);
						menuBar.add(mnuTestExecution);
						mnuTestExecution.add(new JSeparator());
						startExecute=new JMenuItem("Start Execution");
						mnuTestExecution.add(startExecute);
						menuBar.add(mnuTestExecution);
						
						
						mnuAdmin=new JMenu("Administration");
						mnuAdmin.setForeground(Color.black);
						mnuAdmin.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
						mnuAdmin.setMnemonic('A');
						mnuAdminop=new JMenuItem("Manage Tables");
						mnuAdmin.add(mnuAdminop);
						mnuAdmin.add(new JSeparator());
						menuBar.add(mnuAdmin);
							
							mnuConfigurations=new JMenu("TestConfiguration");
						  	//mnuFile.setBorder(BorderFactory.createCompoundBorder(mnuFile.getBorder(),BorderFactory.createEmptyBorder(20, 300, 100, 0)));
							mnuConfigurations.setForeground(Color.WHITE);
							mnuConfigurations.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
							mnuConfigurations.setMnemonic('T');
								
								
	
	 MenuItemListener menuItemListener = new MenuItemListener();

	 mnuOpenMTS.addActionListener(menuItemListener);
	 mnuOpenMTS.setActionCommand("MTS");
	
	mnuActivityForm.addActionListener(menuItemListener);
	mnuActivityForm.setActionCommand("OpenActivity");
	
	mnuBusinessFunction.addActionListener(menuItemListener);
	mnuBusinessFunction.setActionCommand("OpenApplication");
	
	
	mnuOpenORNew.addActionListener(menuItemListener);
	mnuOpenORNew.setActionCommand("OpenOR");
	
	mnuOpenOREdit.addActionListener(menuItemListener);
	mnuOpenOREdit.setActionCommand("OpenOREdit");
	mnuTestPrep.addActionListener(menuItemListener);
	mnuTestPrep.setActionCommand("openTestScript");
	
	 mnuData.addActionListener(menuItemListener);
	 mnuData.setActionCommand("openTestData");
	 mnuEditTest.addActionListener(menuItemListener);
	 mnuEditTest.setActionCommand("editTestScript");
	 mnuExecute.addActionListener(menuItemListener);
	 mnuExecute.setActionCommand("executeTC");
	 startExecute.addActionListener(menuItemListener);
	 startExecute.setActionCommand("startExe");
	 
	 mnuAdminop.addActionListener(menuItemListener);
	 mnuAdminop.setActionCommand("mngtable");
	 
	 
	menuBar.setLayout(new GridBagLayout());
		contentPane = new JPanel();
		contentPane.setBackground(new Color(119,162,248));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/*
		JLabel left = new JLabel("");
		left.setBounds(0, 0, 200, 1000);
		left.setBackground(new Color(0,106,78));
		left.setOpaque(true);
		contentPane.add(left);
		*/
		
		JLabel uname=new JLabel("User:"+userId);
		uname.setBounds(0, 100, 300, 50);
		//uname.setBackground(new Color(100,106,78));
		uname.setFont(new Font("Bookman Old Style", Font.BOLD,14));
		uname.setForeground(Color.white);
		Border border = BorderFactory.createLineBorder(Color.GRAY);

		uname.setBorder(border);
		contentPane.add(uname);
		
		JLabel os=new JLabel("Operating System:"+osname);
		 os.setBounds(0, 150, 300, 50);
		 //os.setBackground(new Color(100,106,78));
		 os.setFont(new Font("Bookman Old Style", Font.BOLD,14));
		 os.setForeground(Color.white);
		 os.setBorder(border);
		contentPane.add(os);
		
		JButton folder=new JButton("FolderExplorer");
		
		folder.setBounds(0,300, 180, 50);
	//folder.setBackground(new Color(119,162,248));
		folder.setFont(new Font("Bookman Old Style", Font.BOLD,12));
		folder.setIcon(new ImageIcon("C:\\Users\\dt77419\\Desktop\\STAF\\Images\\Windows-Explorer-Icon.png"));
		folder.setSelectedIcon(new ImageIcon("C:\\Users\\dt77419\\Desktop\\STAF\\Images\\Windows-Explorer-Icon.png"));
		folder.setForeground(new Color(0,106,78));
		
		folder.setBorder(border);
		contentPane.add(folder);
		
		folder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane jp=new JOptionPane();
				//jp.showMessageDialog(folder, "Welcome");
				
				 JFrame f = new JFrame("File Browser");
	               // f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	                FileManager fileManager = new FileManager();
	                f.setContentPane(fileManager.getGui());
	                f.pack();
	                f.setLocationByPlatform(true);
	                f.setMinimumSize(f.getSize());
	                f.setVisible(true);

	                fileManager.showRootFile();
		   	
			}
		});
		
		footer = new JLabel("Copyright \u00A9 2016 DST  Pvt Ltd. All rights reserved.");
		footer.setVerticalAlignment(SwingConstants.TOP);
		footer.setForeground(Color.black);
		footer.setFont(new Font("Bookman Old Style", Font.PLAIN,12));
		footer.setAlignmentX(CENTER_ALIGNMENT);
		//footer.setBackground(new Color(0,106,78));
		footer.setOpaque(true);
		 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		    //double height = screenSize.height/1.2;
		    int width = screenSize.width;
		footer.setBounds(0, 800, width, 25);
		
		contentPane.add(footer);
		
	}
}
