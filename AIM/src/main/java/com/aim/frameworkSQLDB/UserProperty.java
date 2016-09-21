package com.aim.frameworkSQLDB;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class UserProperty {

	static String userID=null;
	static String osName=null;
	
	public static String getUserInfo()
	{
		
		return userID=System.getProperty("user.name");
				
	}
	
	public static String getOSInfo()
	{
		
		return osName=System.getProperty("os.name");
				
	}
	


}
