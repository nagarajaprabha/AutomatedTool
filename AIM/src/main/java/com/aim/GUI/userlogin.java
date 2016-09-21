package com.aim.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.aim.frameworkSQLDB.CreateNewDB;

import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public  class userlogin extends JDialog implements ActionListener  {

	private final static JPanel contentPanel = new JPanel();
	private static JTextField username;
	private static JPasswordField userpass;
	private static JLabel validationcap;
	static String result=null;
	static Timer t;
	static int i=0;
    static Statement stmt=null;
    private static JProgressBar progressBar_1;
    private JButton btnNewButton;
 
	public static void main(String[] args) {
		try {
			userlogin dialog = new userlogin();
			//dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setUndecorated( true );
			dialog.setDefaultLookAndFeelDecorated( false );
			dialog.getRootPane( ).setWindowDecorationStyle(JDialog.DISPOSE_ON_CLOSE );
			dialog.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	 public void actionPerformed(ActionEvent e) {
	      dispose();
	  }
	 
	
	
	public void validateUser() throws SQLException
	{
		validationcap.setText(null);
		
		try
		{
			CreateNewDB.CreateDB();
			CreateNewDB.conn.setAutoCommit(false);
			
			stmt=CreateNewDB.conn.createStatement();
			
		ResultSet	rs=stmt.executeQuery("SELECT count(*) FROM UserLogin where username='"+ username.getText()+"' AND password='"+userpass.getText()+"'");
			
		
			if(rs.getInt(1)>0)
			{
				
			createProgressBar();
			result="Validation Successful";
			//validationcap.setText("Validation Successful");
			validationcap.setForeground(Color.green);
			validationcap.setVisible(true);
			 dispose();
			
			MasterForm frame = new MasterForm();
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			frame.setVisible(true);
			
			
			}
			else
			{
				createProgressBar();
				result="Authentication Failed..!";
				//validationcap.setText("Validation Fail");
				validationcap.setForeground(Color.RED);
				
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
	
	public static void createProgressBar()
	{
		progressBar_1.setValue(0);
		int timerDelay = 10;
		  new javax.swing.Timer(timerDelay , new ActionListener() {
		     private int index = 0;
		     private int maxIndex = 100;
		     public void actionPerformed(ActionEvent e) {
		        if (index < maxIndex) {
		        	progressBar_1.setValue(index);
		        	progressBar_1.setVisible(true);
		           index++;
		        } else {
		        	progressBar_1.setValue(maxIndex);
		        	progressBar_1.setVisible(true);
		           ((javax.swing.Timer)e.getSource()).stop(); // stop the timer
		           
		           
		           validationcap.setText(result);
		           
		          
		        }
		     }
		  }).start();

		  progressBar_1.setValue(progressBar_1.getMinimum());
		 
		 
	}
	public userlogin() {
			
		Image image = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir")+"\\Images\\DSTLogo.gif");
		setIconImage(image);
		
		System.out.println(image);
		setForeground(Color.RED);
		setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		setTitle("AIM-Automation Integration Manager");
		setBounds(50, 100, 600, 400);
		// setUndecorated(true);
		
	
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(119,162,248));
		contentPanel.setToolTipText("Password");
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		contentPanel.setLayout(null);
		
		username = new JTextField();
		username.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
		username.setToolTipText("User Name");
		username.setBounds(300,150, 244, 33);
		
		contentPanel.add(username);
		username.setColumns(10);
		
		userpass = new JPasswordField();
		userpass.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
		userpass.setToolTipText("Password");
		userpass.setBounds(300, 200, 244, 33);
		contentPanel.add(userpass);
		
		JLabel lblUser = new JLabel("User:");
		lblUser.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblUser.setForeground(Color.black);
		lblUser.setBounds(250, 160, 46, 14);
		contentPanel.add(lblUser);
		
		JLabel lblPassword = new JLabel("Key:");
		lblPassword.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblPassword.setForeground(Color.black);
		lblPassword.setBounds(250, 210, 50, 14);
		contentPanel.add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setToolTipText("Click on the Button to Login");
		btnLogin.setForeground(Color.BLACK);
		//btnLogin.setBackground(new Color(192,192,192));
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				try {
					validateUser();
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnLogin.setBounds(350, 275, 100, 35);
		btnLogin.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		contentPanel.add(btnLogin);
		
		validationcap = new JLabel("");
		validationcap.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		validationcap.setBounds(247, 100, 175, 19);
		contentPanel.add(validationcap);
		
		progressBar_1 = new JProgressBar();
		progressBar_1.setForeground(Color.LIGHT_GRAY);
		progressBar_1.setBounds(0, 316, 600, 23);
		progressBar_1.setVisible(false);
		contentPanel.add(progressBar_1);
		
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\Images\\key_PNG1181.png"));
		lblNewLabel.setBounds(0, 0,300, 400);
		contentPanel.add(lblNewLabel);
	
		
		JLabel lblNewLabel_1 = new JLabel("User Login");
		lblNewLabel_1.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		
		lblNewLabel_1.setBounds(350, 50, 100, 50);
		lblNewLabel_1.setForeground(Color.black);
		lblNewLabel_1.setBackground(new Color(119,162,248));
		lblNewLabel_1.setOpaque(true);
		
		contentPanel.add(lblNewLabel_1);
		/*
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 153, 556, 19);
		separator.setBackground(new Color(0,106,78));
		
		contentPanel.add(separator);
		*/
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
		
		
	}
}
