package com.aim.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import com.aim.test.Constants;
import com.aim.test.DriverScript;


public class CreateHTMLReports
{

	
	static Map < Integer, Object[] > tp,ts;
	public static String result_FolderName=null;
	public static String reportsDirPath=null;
	
	
	public static void createReport(Map < Integer, Object[] > tcdata,Map<Integer, Object[] > tcsteps) throws IOException
	
	{
		
		tp=new TreeMap <Integer, Object[] >();
		
		tp.putAll(tcdata);
		ts=new TreeMap<Integer,Object[]>();
		ts.putAll(tcsteps);
		
	
		Date d = new Date();
		String date=d.toString().replaceAll(" ", "_");
		date=date.replaceAll(":", "_");
		date=date.replaceAll("\\+", "_");
	
		 result_FolderName="HTML_Reports"+"_"+date;
		reportsDirPath=System.getProperty("user.dir")+"//HTML_Reports";
		new File(reportsDirPath +"//"+result_FolderName).mkdirs();
		
		FileInputStream fs = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//com//aim//config//config.properties");
		Properties CONFIG= new Properties();
		CONFIG.load(fs);
		String environment=CONFIG.getProperty("environment");
		String release=CONFIG.getProperty("release");
		

		
		
		// create index.html
		String indexHtmlPath=reportsDirPath +"//"+result_FolderName+"\\index.html";
		new File(indexHtmlPath).createNewFile();
		

		try{
			  
			  FileWriter fstream = new FileWriter(indexHtmlPath);
			  BufferedWriter out = new BufferedWriter(fstream);
			  out.write("<html><HEAD> <TITLE>Automation Test Results</TITLE> <style>tr:nth-child(even){background-color: white} .dropdown:hover .dropdown-content{display: block;} .dropdown-content a:hover{background-color: #f1f1f1;}.dropdown-content a{ color: black; padding: 0px 0px;text-decoration: none;display: block;}.dropdown-content{display: none; position: relative;left:25px;background-color: #f9f9f9;min-width: 160px;box-shadow: 0px 2px 2px 0px rgba(0,0,0,0.2);}.dropdown{position: relative; display: inline-block;}.dropbtn{padding: 2px;font-size: 16px;border: none;cursor: pointer;}  #header {color:white;text-align:center; padding:5px;}#nav {line-height:30px;background-color:#eeeeee; float:left;padding:5px;}#section {padding:10px;}#footer {background-color:#F6F7F9; color:black;clear:both;text-align:center; padding:5px;}</style></HEAD>");
			  out.write("<body style='background-color: #77A2F8;'>");
			  		  String companylogo = System.getProperty("user.dir") +"//Images//logo.jpg";
			  out.write("<div id='section'>");
			  out.write("<table width=60% align='center' style='border:1'><tr align='left'>");
			  out.write("<td style='color:white;'>");
			  out.write("<h1>AIM-Automation Integration Framework</h1>");
			  out.write("</td>");
			  out.write("</tr>");
			  out.write("<tr align='center' style='background-color:#F6F7F9'>");
			  out.write("<td colspan='2' style='color:black;'>");
			  out.write("<h2>Automation Execution Status Summary Report</h2>");
			  out.write("</td>");
		      out.write("</tr>");
			  
			  out.write("<tr align='center' ><td><img src='AutomationChart.png' width=465 height=300px alt='MyLogo'></img></td><td><img src='AutomationPIEChart.png' width=460 height=300px alt='MyLogo'></img></td>");
			  out.write("</tr></table>");
			   out.write("<table align='center' border=1 cellspacing=1 cellpadding=1 width=60%><tr  style='background-color: #F6F7F9'><td width=30% align= center  ><FONT color=black FACE= Arial  SIZE=2><b>Suite Name</b></td><td width=30% align= center  ><FONT color=black FACE= Arial  SIZE=2><b>TestCase</b></td><td width=30% align= center  ><FONT color=black FACE= Arial  SIZE=2><b>Result</b></td><td width=20% align= center  ><FONT color=black FACE= Arial  SIZE=2><b>Total Steps</b></td></tr>");

			  String currentTestSuite=null;
		
			  String suite_result="";
			  String currentTestSuite_Desc=null;
			  
																
						  Iterator<Object[]> tpIt = tp.values().iterator();
							
							while(tpIt.hasNext())
							 {
								
								Object[] next = tpIt.next();
								
								 String testSteps_file=reportsDirPath +"//"+result_FolderName+"\\"+ next[1] +"_steps.html";
								  new File(testSteps_file).createNewFile();
								  FileWriter fstream_test_steps= new FileWriter(testSteps_file);
								  BufferedWriter out_test_steps= new BufferedWriter(fstream_test_steps);
														
						 
						out.write("<tr><td style='text-align: left;padding: 8px;'>"+ next[0] +"</td><td style='text-align: left;padding: 8px;'><a href="+ next[1] +"_steps.html >"+ next[1] +"</a> </td><td>"+ next[2] +"</td><td style='text-align: left;padding: 8px;'>"+ next[3] +"</td></tr>");
						 
						 out_test_steps.write("<html><HEAD> <TITLE>"+currentTestSuite+" Test Results</TITLE></HEAD><body><h4 align=center><FONT COLOR=660066 FACE=AriaL SIZE=6><b><u> "+next[1]+" Detailed Test Steps</u></b></h4><table width=100% border=1 cellspacing=1 cellpadding=1 >");
						
						 out_test_steps.write("<tr style='background-color:brown;'><td width=20% align= center  ><FONT color=white FACE= Arial  SIZE=2><b>TestCaseName</b></td><td width=40% align= center  ><FONT color=white FACE= Arial  SIZE=2><b>UI_NAME</b></td><td width=10% align= center  ><FONT color=white FACE= Arial  SIZE=2><b>Activity_Performed</b></td><td width=10% align= center  ><FONT color=white FACE= Arial  SIZE=2><b>TestData</b></td><td width=10% align= center  ><FONT color=white FACE= Arial  SIZE=2><b>TestResult</b></td></tr>");
						 Iterator<Object[]> tsit = ts.values().iterator();
						 int j=1;
						 
						 while(tsit.hasNext())
							 {
							
							Object[] nexti = tsit.next();
						 
							if(next[1].equals(nexti[1]))
							{
						 out_test_steps.write("<tr>");
						 out_test_steps.write("<td style='color:#153E7E'>"+ nexti[1]+"</td>");
						 out_test_steps.write("<td style='color:#153E7E'>"+ nexti[2]+"</td>");
						 out_test_steps.write("<td style='color:#153E7E'>"+ nexti[3]+"</td>");
						 out_test_steps.write("<td style='color:#153E7E'>"+ nexti[4]+"</td>");
						 
						 if(nexti[5].equals(Constants.KEYWORD_PASS))
						 {
						 out_test_steps.write("<td><a href="+ nexti[0] +" style='color:green'><b>"+ nexti[5]+"</b></a></td>");
						 }
						 else
						 {
							 
						  out_test_steps.write("<td><a href="+ nexti[0] +" style='color:red'><b>"+ nexti[5]+"</b></a></td>");
							 
						 }
						  out_test_steps.write("</tr>");
							}
						 
						 j++;
						 						  
						 }
						 
						 out_test_steps.write("<tr rowspan='4'>");
						 out_test_steps.write("<td> <a href='index.html' style='font-name:georgia;font-size:30;color:navy;font-style:bold'>Home</a>");
						 out_test_steps.write("</td>");
						 out_test_steps.write("</td>");
						 out_test_steps.write("</table>");  
						 out_test_steps.close();
					
						
							 }
						 
						 out.write("</div></div></td>");
						
						
			  out.write("<tr rowspan='4'>");
			  out.write("<td colspan='3'>");
			  
			  out.write("<table  border=1 cellspacing=1 cellpadding=1  ><tr><h4> <FONT color=black FACE=Arial SIZE=4.5> <u>Test Details :</u></h4><td width=150 align=left style='background-color:#F6F7F9'><FONT color=black FACE=Arial SIZE=2.75><b>Run Date</b></td><td width=150 align=left style='background-color:#F6F7F9'><FONT color:black;  FACE=Arial SIZE=2.75><b>");
			  out.write(d.toString());
			  out.write("</b></td></tr><tr><td width=150 align=left  style='background-color:#F6F7F9'><FONT color=black FACE=Arial SIZE=2.75><b>Run Environment</b></td><td width=150 align=left><FONT COLOR=#153E7E FACE=Arial SIZE=2.75><b>");
			  out.write(environment);
			  out.write("</b></td></tr><tr><td width=150 align= left  style='background-color:#F6F7F9'><FONT color=black FACE= Arial  SIZE=2.75><b>Release</b></td><td width=150 align= left style='background-color:#F6F7F9'><FONT COLOR=#153E7E FACE= Arial  SIZE=2.75><b>");
			  out.write(release);
			  out.write("</b></td></tr></table><h4>");
			  out.write("</tr>");
			  out.write("</table>");
			  out.write(" </div>");
			  out.write(" <div id='footer'>");
			  out.write("  Copyright © DST.com");
			  out.write(" </div>");
			 
			  out.close();
			
			  }
		
		catch (Exception e){
			  System.err.println("Error: " + e.getMessage());
			  e.printStackTrace();
			  
			  }
		

	}
		
		

		
}


	

