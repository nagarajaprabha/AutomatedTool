package com.aim.util;

import static com.aim.test.DriverScript.CONFIG;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.aim.test.Constants;

public class CreateWordImages {
	
	public  static XWPFDocument doc;
	public static XWPFParagraph p;
	public static XWPFRun xwpfRun;
	 
	
	public  static void insertImages(ArrayList img,String path,String keyword_execution_result) throws IOException
	{
		if(CONFIG.getProperty("screenshot_everystep").equals("Y")){
			
	      doc = new XWPFDocument();
	        p = doc.createParagraph();
	     xwpfRun = p.createRun();
	      	        ArrayList<String> IMageargs=new ArrayList<String>();
	        IMageargs.addAll(img);
	    	        
	        for (String imgFile : IMageargs) {
	            int format=XWPFDocument.PICTURE_TYPE_JPEG;
	            xwpfRun.setText(imgFile);
	            xwpfRun.addBreak();
	            try {
					xwpfRun.addPicture (new FileInputStream(imgFile), format, imgFile, Units.toEMU(400), Units.toEMU(400));
				} catch (InvalidFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // 200x200 pixels
	            //xwpfRun.addBreak(BreakType.PAGE);
	        }
	        
	        FileOutputStream out = new FileOutputStream(path);
	        doc.write(out);
	        out.close();
	        IMageargs.clear();
	        
	    }
		/*
		else if (keyword_execution_result.startsWith(Constants.KEYWORD_FAIL) && CONFIG.getProperty("screenshot_error").equals("Y") )
		{
			 doc = new XWPFDocument();
		        p = doc.createParagraph();
		     xwpfRun = p.createRun();
		      	        ArrayList<String> IMageargs=new ArrayList<String>();
		        IMageargs.addAll(img);
		    	        
		        for (String imgFile : IMageargs) {
		            int format=XWPFDocument.PICTURE_TYPE_JPEG;
		            xwpfRun.setText(imgFile);
		            xwpfRun.addBreak();
		            try {
						xwpfRun.addPicture (new FileInputStream(imgFile), format, imgFile, Units.toEMU(400), Units.toEMU(400));
					} catch (InvalidFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} // 200x200 pixels
		            //xwpfRun.addBreak(BreakType.PAGE);
		        }
		        
		       
	
		        FileOutputStream out = new FileOutputStream(path);
		        doc.write(out);
		        out.close();
		         */
		    	
				
			
		
		
		
}
	
    
}