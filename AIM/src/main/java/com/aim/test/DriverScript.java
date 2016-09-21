package com.aim.test;

import java.awt.Color;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.apache.poi.common.usermodel.Hyperlink;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FontUnderline;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.aim.util.CreateGraph;
import com.aim.util.CreateWordImages;

import com.aim.util.CreateHTMLReports;
import com.aim.util.CreateHTMLReports;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class DriverScript {

	private static final CategoryItemLabelGenerator CategoryItemLabelGenerator = null;
	public static Logger APP_LOGS;
	// Global Variables for Master Suite
	
	public int currentSuiteID;
	public String currentTestSuite;

	// Global variables for single Suite

	public int currentTestCaseID;
	public String currentTestCaseName;
	public int currentTestStepID;
	public String currentKeyword;
	public static int currentDataSetID = 2;
	public Keywords key;
	public BusinessFunctions bfunction;
	public Method[] method, method1, method3;
	public String keyword_execution_result;
	public static Method capturescreenShot_method;
	public String comments;
	public static int total_pass_scenarios=0;
	public static int total_fail_scenarios=0;

	// Properties

	public static Properties CONFIG;
	public static Properties OR;

	public String data;
	public String object;
	public String locator;
	public String uiname;
	public String filename;
	public String filepath;
	
	public int counter;
	
	static Map < Integer, Object[] > tp,tc,tc_A,tc_B;
	
	static ArrayList<String> images;
	
	XSSFWorkbook wb;
	XSSFSheet ws1,ws2;
	XSSFRow row,row1;
	XSSFCell cell1;

	static String word_foldername,word_DirPath;
	
	public static Connection c;
	public static Statement stmt;
	public static ResultSet rs,rs1,rs2,rs3,rs4;
	
	public static Date start_date,end_date;
	public static String start_time,end_time;
	public static long start_hour;
	public static long start_min;
	public static long start_sec;
	public static long end_hour;
	public static long end_min;
	public static long end_sec;
	public static String totaltime;
	
	
	public DriverScript() throws NoSuchMethodException, SecurityException, ClassNotFoundException, SQLException {
		   c = null;
		     stmt = null;
	    Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:AutomatonFramework.db");
	      c.setAutoCommit(true);
	     

	   stmt = c.createStatement();
		
		
		key = new Keywords();

		// method=key.getClass().getMethods();
		method = key.getClass().getMethods();

		capturescreenShot_method = key.getClass().getMethod("captureScreenshot", String.class, String.class);

		// method=bfunction.getClass().getMethods();

		tp=new TreeMap <Integer, Object[] >();
		tc=new TreeMap<Integer,Object[]>();
		tc_A=new TreeMap<Integer,Object[]>();
		tc_B=new TreeMap<Integer,Object[]>();
		
		images=new ArrayList<String>();
		
		Date d = new Date();
		String date=d.toString().replaceAll(" ", "_");
		date=date.replaceAll(":", "_");
		date=date.replaceAll("\\+", "_");
	
		word_foldername="WordDocuments"+"_"+date;
		word_DirPath=System.getProperty("user.dir")+"//TestResults";
		new File(word_DirPath +"//"+word_foldername).mkdirs();
		
		
	}
	
	public void init() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException
	{
	
		
		APP_LOGS = Logger.getLogger("devpinoyLogger");
		FileInputStream fs = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//com//aim//config//config.properties");
		CONFIG = new Properties();
		CONFIG.load(fs);

		//DriverScript test = new DriverScript();
		
		start_date=new Date();
		start_time=DateFormat.getTimeInstance(DateFormat.MEDIUM).format(start_date);
	
		start_hour=start_date.getHours();
	
		start_min=start_date.getMinutes();
	
		start_sec=start_date.getSeconds();
		
		start_Execution();
		
		end_date=new Date();
		end_time=DateFormat.getTimeInstance(DateFormat.MEDIUM).format(end_date);
	
	 end_hour=end_date.getHours();
		
		end_min=end_date.getMinutes();
		
		end_sec=end_date.getSeconds();
		
		 totaltime=Math.abs(start_hour-end_hour)+" Hrs:"+Math.abs(start_min-end_min)+" Mins:"+Math.abs(start_sec-end_sec)+" Secs";
		
		createExcel();
		saveExcel();
		
		
		
		
		
		CreateHTMLReports.createReport(tc,tp);
		
		  
		getChart(CreateHTMLReports.reportsDirPath +"//"+CreateHTMLReports.result_FolderName);
	getpieChart(CreateHTMLReports.reportsDirPath +"//"+CreateHTMLReports.result_FolderName);
		
	}

	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, IOException, NoSuchMethodException, SecurityException, ClassNotFoundException, SQLException {
		
		DriverScript ds=new DriverScript();
		
		ds.init();

	}

	


	
	
	
	
	@SuppressWarnings("deprecation")
	private void createExcel() throws IOException
	{
		
	 wb=new XSSFWorkbook();
     ws1=wb.createSheet("Test_Summary");
     ws2=wb.createSheet("Detailed_Report");
     
     XSSFFont font = wb.createFont();
     font.setFontHeightInPoints((short) 11);
     font.setFontName("Georgia");
     font.setBold(true);
     font.setColor(HSSFColor.BLUE.index);
     
     XSSFFont font1 = wb.createFont();
     font1.setFontHeightInPoints((short) 11);
     font1.setFontName("Georgia");
     font1.setBold(true);
     font1.setColor(HSSFColor.BLUE_GREY.index);
     
     XSSFFont newFont = wb.createFont();
     newFont .setFontHeightInPoints((short) 11);
     newFont .setFontName("Georgia");
     newFont .setBold(true);
     newFont .setColor(HSSFColor.BLUE_GREY.index);
     
     //Set font into style	
     XSSFCellStyle style = wb.createCellStyle();
    
    style.setFont(newFont);
    style.setAlignment(style.ALIGN_CENTER);
    
    XSSFCellStyle style2 = wb.createCellStyle();
 
   style2.setFont(font);
   style2.setAlignment(style.ALIGN_CENTER);
   
   XSSFCellStyle style3 = wb.createCellStyle();
   //style3.setFillPattern(CellStyle.SOLID_FOREGROUND);
  // style3.setFillBackgroundColor(HSSFColor.TAN.index);
 
 
  style3.setFont(font);
  style3.setAlignment(style.ALIGN_LEFT);
   
    
     XSSFCellStyle style1 = wb.createCellStyle();
    style1.setFont(font1);
     
     row=ws1.createRow(0);
     cell1=row.createCell(0);
     cell1.setCellValue("Release Name");
     cell1.setCellStyle(style);
     ws1.autoSizeColumn(0);
     
     cell1=row.createCell(1);
     cell1.setCellValue("TestCase Name");
     cell1.setCellStyle(style);
     ws1.autoSizeColumn(1);
     
     
     cell1=row.createCell(2);
     cell1.setCellValue("Status");
     cell1.setCellStyle(style);
     ws1.autoSizeColumn(2);
     cell1=row.createCell(3);
     cell1.setCellValue("No Of Steps");
    cell1.setCellStyle(style);
    ws1.autoSizeColumn(3);
     

 	 	 
	 Set<Integer> keyid = tp.keySet();
     int rowid = 0;
    
     
   
     
     for (Integer key : keyid)
     {
    	 
        row = ws2.createRow(rowid++);
        Object [] objectArr = tp.get(key);
        int cellid = 0;
        for (Object obj : objectArr)
        {
        	 ws2.autoSizeColumn(cellid);
           Cell cell = row.createCell(cellid++);
           cell.setCellValue((String)obj);
          cell.setCellStyle(style3);
           if(row.getRowNum()==0)
           
           {
        	  cell.setCellStyle(style); 
        	 
        	   
           }
           
           if(cell.getStringCellValue().equals(Constants.KEYWORD_FAIL))
           {
        	   CreationHelper createHelper = wb.getCreationHelper();
        	    CellStyle hlink_style = wb.createCellStyle();
        	    XSSFFont hlink_font = wb.createFont();
        	    hlink_font.setUnderline(FontUnderline.SINGLE);
        	    hlink_font.setColor(IndexedColors.GREY_80_PERCENT.getIndex());
        	    hlink_style.setFont(hlink_font);
        	    Hyperlink hp = createHelper.createHyperlink(Hyperlink.LINK_FILE);
        	    
        	   int rowindex,colindex;
        	   rowindex=row.getRowNum();
        	   colindex=cell.getColumnIndex();
        	 
        	   
        	   row1=ws2.getRow(rowindex);
        	   cell1=row1.getCell(0);
        	   
        		filepath=cell1.getStringCellValue();	
        		filepath=filepath.replace("\\", "/");
        			   
        		 hp.setAddress(filepath);
        		    cell.setHyperlink((org.apache.poi.ss.usermodel.Hyperlink) hp);
        		    cell.setCellStyle(hlink_style);
        	   
        	   
        	   
        	   
           }
           
           
        
           
           
           
           
        }
     }
	 
     //*********
     
     Set<Integer> tcid = tc.keySet();
     int rowsid = 1;
     for (Integer key : tcid)
     {
        row = ws1.createRow(rowsid++);
        Object [] objectArr = tc.get(key);
        int cellid = 0;
        for (Object obj : objectArr)
        {
        	 	       
           Cell cell = row.createCell(cellid++);
           cell.setCellValue(obj.toString());
           cell.setCellStyle(style3);
           ws1.autoSizeColumn(cellid);
           
           if(cell.getStringCellValue().equals(Constants.KEYWORD_FAIL))
           {
        	   CreationHelper createHelper = wb.getCreationHelper();
        	    CellStyle hlink_style = wb.createCellStyle();
        	    XSSFFont hlink_font = wb.createFont();
        	    hlink_font.setUnderline(FontUnderline.SINGLE);
        	    hlink_font.setColor(IndexedColors.RED.getIndex());
        	    hlink_style.setFont(hlink_font);
        	    Hyperlink hp = createHelper.createHyperlink(Hyperlink.LINK_DOCUMENT);
        	           	           			   
        		 hp.setAddress("'Detailed_Report'!A1");
        		    cell.setHyperlink((org.apache.poi.ss.usermodel.Hyperlink) hp);
        		    cell.setCellStyle(hlink_style);
        	
        	   
        	   
        	   
           }
           else if (cell.getStringCellValue().equals(Constants.KEYWORD_PASS) || cell.getStringCellValue().equals(Constants.KEYWORD_CLOSED) )
           {
        	   CreationHelper createHelper = wb.getCreationHelper();
       	    CellStyle hlink_style = wb.createCellStyle();
       	    XSSFFont hlink_font = wb.createFont();
       	    hlink_font.setUnderline(FontUnderline.SINGLE);
       	    hlink_font.setColor(IndexedColors.GREEN.getIndex());
       	    hlink_style.setFont(hlink_font);
       	    Hyperlink hp = createHelper.createHyperlink(Hyperlink.LINK_DOCUMENT);
       	           	           			   
       		 hp.setAddress("'Detailed_Report'!A1");
       		    cell.setHyperlink((org.apache.poi.ss.usermodel.Hyperlink) hp);
       		    cell.setCellStyle(hlink_style);
        	   
        	   
        	   
           }
          
        }
        
        
     }
     
     
      
    int currentrow=rowsid;
     row=ws1.createRow(currentrow);
     cell1=row.createCell(0);
     cell1.setCellValue("Test Case Summary Report");
     cell1.setCellStyle(style);
    style.setAlignment(style.ALIGN_CENTER);
     
    ws1.addMergedRegion(new CellRangeAddress(
    		currentrow,
    		currentrow,
             0, //first column (0-based)
           1  //last column  (0-based)
     ));
     
    
     row=ws1.createRow(currentrow+1);
     cell1=row.createCell(0);
     cell1.setCellValue("Total Scenarios Executed");
     cell1.setCellStyle(style3);
     ws1.autoSizeColumn(0);
     cell1=row.createCell(1);
     cell1.setCellValue(tc.size());
     cell1.setCellStyle(style3);
     row=ws1.createRow(currentrow+2);
     cell1=row.createCell(0);
     cell1.setCellValue("Total Scenarios Passed");
     cell1.setCellStyle(style3);
     ws1.autoSizeColumn(0);
      cell1=row.createCell(1);
     cell1.setCellValue(total_pass_scenarios);
     cell1.setCellStyle(style3);
     ws1.autoSizeColumn(0);
     row=ws1.createRow(currentrow+3);
         
     cell1=row.createCell(0);
     cell1.setCellValue("Total Scenarios Failed");
     cell1.setCellStyle(style3);
     ws1.autoSizeColumn(0);
     cell1=row.createCell(1);
     cell1.setCellValue(total_fail_scenarios);
     ws1.autoSizeColumn(0);
     cell1.setCellStyle(style3);
     row=ws1.createRow(currentrow+4);
     
     cell1=row.createCell(0);
     cell1.setCellValue("Total Executed Steps");
     ws1.autoSizeColumn(0);
     cell1.setCellStyle(style3);
     cell1=row.createCell(1);
     cell1.setCellValue(tp.size()-1);
     cell1.setCellStyle(style3);
     row=ws1.createRow(currentrow+5);
     cell1=row.createCell(0);
     cell1.setCellValue("Test Execution Date:");
     ws1.autoSizeColumn(0);
     cell1.setCellStyle(style3);
     cell1=row.createCell(1);
     cell1.setCellValue(DateFormat.getDateInstance().format(start_date));
     cell1.setCellStyle(style3);
     row=ws1.createRow(currentrow+6);
     cell1=row.createCell(0);
     cell1.setCellValue("TestExecution_Start_Time:");
     ws1.autoSizeColumn(0);
     cell1.setCellStyle(style3);
     cell1=row.createCell(1);
     cell1.setCellValue(start_time);
     cell1.setCellStyle(style3);
     row=ws1.createRow(currentrow+7);
     
     cell1=row.createCell(0);
     cell1.setCellValue("TestExecution_End_Time:");
     ws1.autoSizeColumn(0);
     cell1.setCellStyle(style3);
     cell1=row.createCell(1);
     cell1.setCellValue(end_time);
     cell1.setCellStyle(style3);
     row=ws1.createRow(currentrow+8);
     
     cell1=row.createCell(0);
     cell1.setCellValue("Total Execution Time:");
     ws1.autoSizeColumn(0);
     cell1.setCellStyle(style3);
     cell1=row.createCell(1);
     cell1.setCellValue(totaltime);
     cell1.setCellStyle(style3);
     row=ws1.createRow(currentrow+9);
     //***********
	 
  
     
     DefaultCategoryDataset my_bar_chart_dataset = new DefaultCategoryDataset();
 
  
     String series1 = "First";   
     String series2 = "Second";   
     String series3 = "Third";  
     //String series4 = "Fourth"; 

     // column keys...   
     String category1 = "Category 1";   
     String category2 = "Category 2";   
     String category3 = "Category 3";   
     String category4 = "Category 4";   
    // String category5 = "Category 5";   

  
   
     my_bar_chart_dataset.addValue(tc.size(), series1, "Total Scenarios Executed" );   
     my_bar_chart_dataset.addValue(total_pass_scenarios, series2, "Total Scenarios Passed");   
     my_bar_chart_dataset.addValue(total_fail_scenarios, series3, "Total Scenarios Failed");   
    // my_bar_chart_dataset.addValue(tp.size(), series4, "Total Executed Steps");   
    
                   
                     
       
     
    
     
     
     CreateGraph cg=new CreateGraph();
    
   
     JFreeChart chart = cg.createChart(my_bar_chart_dataset);
     
     
                  
     int width=640; /* Width of the chart */
     int height=480; /* Height of the chart */  
     
  
     ByteArrayOutputStream chart_out = new ByteArrayOutputStream();          
     ChartUtilities.writeChartAsPNG(chart_out,chart,width,height);
     /* We can now read the byte data from output stream and stamp the chart to Excel worksheet */
     int my_picture_id = wb.addPicture(chart_out.toByteArray(), wb.PICTURE_TYPE_PNG);
     /* we close the output stream as we don't need this anymore */
     chart_out.close();
     /* Create the drawing container */
     XSSFDrawing drawing = ws1.createDrawingPatriarch();
     /* Create an anchor point */
     ClientAnchor my_anchor = new XSSFClientAnchor();
     /* Define top left corner, and we can resize picture suitable from there */
     my_anchor.setCol1(4);
     my_anchor.setRow1(5);
     /* Invoke createPicture and pass the anchor point and ID */
     XSSFPicture  my_picture = drawing.createPicture(my_anchor, my_picture_id);
     /* Call resize method, which resizes the image */
     my_picture.resize();
     /* Close the FileInputStream */
     
    
     ws2.setColumnHidden(0,true);
     
     
     
     
	 }
			 
		 
	 
	

	private void saveExcel() throws IOException
	{
		
		Date d = new Date();
		String date=d.toString().replaceAll(" ", "_");
		date=date.replaceAll(":", "_");
		date=date.replaceAll("\\+", "_");
	
		 String result_FolderName = "Excel_Report"+"_"+date;
		String reportsDirPath=System.getProperty("user.dir")+"//Excel_Reports";
		new File(reportsDirPath +"//"+result_FolderName).mkdirs();
		
		//String path="D://SummaryReport.Xlsx";
		
		String path;
		path=reportsDirPath +"//"+result_FolderName+"//"+"SummaryReport.Xlsx";
		
		
		FileOutputStream fs=new FileOutputStream(path);
		wb.write(fs);
		fs.close();
			
	}



//*****************
	
	
	
	public static void getChart(String path) throws IOException
	{
		
		DefaultCategoryDataset my_bar_chart_dataset = new DefaultCategoryDataset();
		 
		  
	     String series1 = "First";   
	     String series2 = "Second";   
	     String series3 = "Third";  
	    // String series4 = "Fourth"; 

	     // column keys...   
	     String category1 = "Category 1";   
	     String category2 = "Category 2";   
	     String category3 = "Category 3";   
	     String category4 = "Category 4";   
	   //  String category5 = "Category 5";   

	  
	   
	     my_bar_chart_dataset.addValue(tc.size(), series1, "Total Scenarios" );   
	     my_bar_chart_dataset.addValue(total_pass_scenarios, series2, "Total Pass");   
	     my_bar_chart_dataset.addValue(total_fail_scenarios, series3, "Total Fail");   
	    // my_bar_chart_dataset.addValue(tp.size(), series4, "Total Executed Steps");   
	    
	    
	     
	     CreateGraph cg=new CreateGraph();
	    
	   
	     JFreeChart chart = cg.createChart(my_bar_chart_dataset);
	     
	                
	     int width=640; /* Width of the chart */
	     int height=480; /* Height of the chart */  
	     
	  	     
	     
	     
	     File yourFile = new File(path+"\\AutomationChart.png");
	     if(!yourFile.exists()) {
	         yourFile.createNewFile();
	     } 
	     FileOutputStream oFile = new FileOutputStream(yourFile, false); 
	     
	     ChartUtilities.writeChartAsPNG(oFile,chart, width,height);
		
	}
	
	public static void getpieChart(String path) throws IOException
	{
		
		
		 DefaultPieDataset myPieChart = new DefaultPieDataset();
         /* Define Values for the Pie Chart - Programming Languages Percentage Difficulty */
		 myPieChart.setValue("Total Executed", tc.size());
		 myPieChart.setValue("Total Pass", total_pass_scenarios);
		 myPieChart.setValue("Total Fail",total_fail_scenarios);
         
		
	    CreateGraph cg=new CreateGraph();
	    
		   
	     JFreeChart chart = cg.drawPIEChart(myPieChart);
	     
	                
	     int width=640; /* Width of the chart */
	     int height=480; /* Height of the chart */  
	     
	     
	     PiePlot ColorConfigurator = (PiePlot)chart.getPlot();
         /* We can now use setSectionPaint method to change the color of our chart */
        
         ColorConfigurator.setSectionPaint("Total Executed", Color.blue);
         ColorConfigurator.setSectionPaint("Total Pass", Color.GREEN);
         ColorConfigurator.setSectionPaint("Total Fail", Color.red);
   
         float quality=1; /* Quality factor */
         
         PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
                 "{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
         ColorConfigurator.setLabelGenerator(gen);
         
             
             
	  	     
	     File yourFile = new File(path+"\\AutomationPIEChart.png");
	     if(!yourFile.exists()) {
	         yourFile.createNewFile();
	     } 
	     FileOutputStream oFile = new FileOutputStream(yourFile, false); 
	     
	     ChartUtilities.writeChartAsPNG(oFile,chart, width,height);
	   
		
	}
	
	
	
	
	
	
	
	//***********************************EndProgram**************************************************************************
	
	public void getKeyWordsData(String currentTestSuite,String currentTestCaseName,ResultSet r)
	{
		images.clear();
		Connection c = null;
		   Statement stmt = null;
		
			
		    try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:AutomatonFramework.db");
			   stmt = c.createStatement();
		   ResultSet rs=stmt.executeQuery("SELECT * FROM TestCase_Master where release_name='"+currentTestSuite+"' AND tc_name='"+currentTestCaseName+"';");
		  // currentTestCaseID=1;
		   while(rs.next())
		   {
			  
			
		   data = rs.getString("Data");
	          
			
				if (data.startsWith(Constants.DATA_START_COLUMN))
				{
	
				 data=r.getString(data.substring(6));
					
							
				}
			
				uiname=rs.getString("UIName");
				object = rs.getString("Locator");
				locator = rs.getString("Locator_Type");
				currentKeyword = rs.getString("Activity");
				
				
					
					
					//tp.put( 0, new Object[] {"Path","TestCaseName", "UI_NAME", "Activity_Performed","TestData","TestResult","Comments" });
					for (int i = 0; i < method.length; i++)
					{

						if (method[i].getName().equals(currentKeyword)) 
						{
							counter=counter+1;
							
						
                       currentTestStepID=currentTestStepID+1;
							
							
							
							keyword_execution_result = (String) method[i].invoke(key, object, data, locator);
							
											
							
							if(keyword_execution_result.startsWith(Constants.KEYWORD_FAIL))
									{
							
							 tp.put(currentTestStepID, new Object[] {filepath, currentTestCaseName, uiname, currentKeyword,data,keyword_execution_result,comments+"Path:" });
									}
							
							if(keyword_execution_result.startsWith(Constants.KEYWORD_PASS))
							{
								
								 tp.put( currentTestStepID, new Object[] {filepath,currentTestCaseName, uiname, currentKeyword,data,keyword_execution_result,comments});
								
							}
							
							if(keyword_execution_result.startsWith(Constants.KEYWORD_CLOSED))
							{
								
								 tp.put( currentTestStepID, new Object[] {filepath,currentTestCaseName, uiname, currentKeyword,data,"Pass",comments});
								
							}
						
							
							if(!keyword_execution_result.startsWith(Constants.KEYWORD_CLOSED))
							{
								 filename=currentTestSuite + "_" + currentTestCaseName + "_TS"
											+ currentTestStepID ;
								filepath=System.getProperty("user.dir") +"//screenshots//"+filename+".jpg";
																 
								capturescreenShot_method.invoke(key, filename, keyword_execution_result);
														
								images.add(filepath);
							
							

						}

				
					
					
						}
						}
					
					 
					 
					
					
					CreateWordImages.insertImages(images,word_DirPath +"//"+word_foldername+"//"+currentTestSuite + "_" + currentTestCaseName+".docx",keyword_execution_result);	
				
				
					
		   	
		   }
		   rs.close();
		    			
		   	    stmt.close();
		   	    
		   	      c.close();
		   	    } 
		   	    catch ( Exception e ) {
		   	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		   	     
		   	    }
		   	   
		   	  
		
		
	}
	
	public void getKeyWordsData_2(String currentTestSuite,String currentTestCaseName)
	{
		images.clear();
		Connection c = null;
		   Statement stmt = null;
		
			
		    try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:AutomatonFramework.db");
			   stmt = c.createStatement();
		   ResultSet rs=stmt.executeQuery("SELECT * FROM TestCase_Master where release_name='"+currentTestSuite+"' AND tc_name='"+currentTestCaseName+"';");
			//currentTestCaseID=1;
		   while(rs.next())
		   {
			   
		   data = rs.getString("Data");
	         	
				uiname=rs.getString("UIName");
				object = rs.getString("Locator");
				locator = rs.getString("Locator_Type");
				currentKeyword = rs.getString("Activity");
				
							
					//tp.put( 0, new Object[] {"Path","TestCaseName", "UI_NAME", "Activity_Performed","TestData","TestResult","Comments" });
					for (int i = 0; i < method.length; i++)
					{

						if (method[i].getName().equals(currentKeyword)) 
						{
							counter=counter+1;
							
							currentTestStepID=currentTestStepID+1;
							
							
							
							keyword_execution_result = (String) method[i].invoke(key, object, data, locator);
							
											
							
							if(keyword_execution_result.startsWith(Constants.KEYWORD_FAIL))
							{
					
					 tp.put(currentTestStepID, new Object[] {filepath, currentTestCaseName, uiname, currentKeyword,data,keyword_execution_result,comments+"Path:" });
							}
					
					if(keyword_execution_result.startsWith(Constants.KEYWORD_PASS))
					{
						
						 tp.put( currentTestStepID, new Object[] {filepath,currentTestCaseName, uiname, currentKeyword,data,keyword_execution_result,comments});
						
					}
					
					if(keyword_execution_result.startsWith(Constants.KEYWORD_CLOSED))
					{
						
						 tp.put( currentTestStepID, new Object[] {filepath,currentTestCaseName, uiname, currentKeyword,data,"Pass",comments});
						
					}
							
							
							if(!keyword_execution_result.startsWith(Constants.KEYWORD_CLOSED))
							{
								 filename=currentTestSuite + "_" + currentTestCaseName + "_TS"
											+ currentTestStepID ;
								filepath=System.getProperty("user.dir") +"//screenshots//"+filename+".jpg";
																 
								capturescreenShot_method.invoke(key, filename, keyword_execution_result);
														
								images.add(filepath);
								
							}
						

						}

					}
					
					
					
					
					
					CreateWordImages.insertImages(images,word_DirPath +"//"+word_foldername+"//"+currentTestSuite + "_" + currentTestCaseName+".docx",keyword_execution_result);	
				
		   	
		   }
		   rs.close();
		    			
		   	    stmt.close();
		   	    
		   	      c.close();
		   	    } 
		   	    catch ( Exception e ) {
		   	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		   	     
		   	    }
		   	   
		   	  
		
		
	}
	
	
	
	public void start_Execution() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException
	{

		    try {
		  
			rs=stmt.executeQuery("SELECT * FROM MasterTestSuite where Runmode='Yes';");
			currentTestCaseID = 1;
			while(rs.next())
			{
					
			String runmode = rs.getString(3);

			currentTestSuite = rs.getString(1);
			
			if (runmode.equals(Constants.RUNMODE_YES))

			{
				
				rs1=stmt.executeQuery("SELECT DISTINCT(tc_name),RUNMODE,release_name,Data_Flag FROM TestCase_Master where release_name='"+currentTestSuite+"' AND RUNMODE='Yes';");
				currentTestCaseID = 1000;
				while(rs1.next())
				{
										
					
				currentTestCaseName=rs1.getString(1);
								
				
				 String dataFlag=(String)rs1.getString(4);
								
				 tp.put( 0, new Object[] {"Path","TestCaseName", "UI_NAME", "Activity_Performed","TestData","TestResult","Comments" });
				
				 if(dataFlag.equals("Yes"))
				 {
				
				String testDataTable="TestData_"+currentTestSuite+"_"+currentTestCaseName;
					 
				rs2=stmt.executeQuery("SELECT * FROM "+testDataTable+ " where runmode='Yes';"); 
				
				currentDataSetID = 1;
				while(rs2.next())
												
				{
					currentDataSetID=currentDataSetID+1;
					
					String dataValue=null;
				
					try
					{
						counter=0;
						
				getKeyWordsData(currentTestSuite,currentTestCaseName,rs2);
				   
				 if(!keyword_execution_result.equals(Constants.KEYWORD_CLOSED))
				 {
					tc_A.put( currentDataSetID, new Object[] {currentTestSuite,currentTestCaseName,keyword_execution_result ,counter});
				 }
				 if(keyword_execution_result.equals(Constants.KEYWORD_CLOSED))
				 {
					 tc_A.put( currentDataSetID, new Object[] {currentTestSuite,currentTestCaseName,"Pass" ,counter}); 
					 
				 }			
				
					if(keyword_execution_result.startsWith(Constants.KEYWORD_PASS) || keyword_execution_result.startsWith(Constants.KEYWORD_CLOSED))
					{
						
						total_pass_scenarios++;
						
					}
					else
					{
						
						total_fail_scenarios++;
						
						
					}
				
					}
					
					catch(Exception e)
					{
						
					
					}
					//currentDataSetID =currentDataSetID+1;
					
					}
				rs2.close();
					 
				 }
				
				 if(dataFlag.equals("No"))
				 {
					 currentTestCaseID = currentTestCaseID +1; 
					
					 counter=0;
					 getKeyWordsData_2(currentTestSuite,currentTestCaseName);
					 
					
					 if(!keyword_execution_result.equals(Constants.KEYWORD_CLOSED))
					 {
						tc_B.put(  currentTestCaseID, new Object[] {currentTestSuite,currentTestCaseName,keyword_execution_result ,counter});
					 }
					 if(keyword_execution_result.equals(Constants.KEYWORD_CLOSED))
					 {
						 tc_B.put(  currentTestCaseID, new Object[] {currentTestSuite,currentTestCaseName,"Pass" ,counter}); 
						 
					 }
					 
						
						if(keyword_execution_result.startsWith(Constants.KEYWORD_PASS) || keyword_execution_result.startsWith(Constants.KEYWORD_CLOSED))
						{
							
							total_pass_scenarios++;
							
						}
						else
						{
							
							total_fail_scenarios++;
							
							
						}
			
					 
					 
				 }
			
				
				}
				
				
			}
			 
			 
				
		
			
						
			}
			tc.putAll(tc_A);
			tc.putAll(tc_B);
			 			
				      stmt.close();
				      rs.close();
				    rs1.close();
				   
				 
				      c.close();
				    } 
				    catch ( Exception e ) {
				      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				     
				    }
				 
	
	}
	
	
		    
	
}

 


