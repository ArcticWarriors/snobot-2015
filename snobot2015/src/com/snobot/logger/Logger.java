package com.snobot.logger;


import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * Class for logger
 * 
 * @author Calvin Do
 *
 */

public class Logger
{
	private String mLogHeader;
	private String mHeaderDate;
	private String mLogDate;
	private FileWriter logWriter;
	
	public Logger(String aLogHeader, String aHeaderDate)
	{
		mLogHeader = aLogHeader;
		mHeaderDate = aHeaderDate;
	}
	public Logger(String aLogDate)
	{
		mLogDate = aLogDate;
	}
	
	/**
	 * Initializes member-variables and opens file-stream
	 * @throws IOException 
	 */
	public void init ()
	{
		
		
		
		try 
		{
			logWriter = new FileWriter ("RobotLog_" + mHeaderDate);
			
			for(int logCount = 0; logCount< 1; logCount++)
			{
				logWriter.write(mLogHeader);
				logWriter.write("\n");
			}
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	/**
	 * Adds a new header to represent logged data
	 * @param aHeader
	 */
	public void addHeader (String aHeader) 
	{
		
	}
	
	/**
	 * Stops accepting new headers
	 */
	public void endHeader () 
	{
		
	}
	
	/**
	 * Begins accepting new log entries
	 */
	public void startLogEntry () {
		
		for(int logCount = 0; logCount< 1; logCount++)
		{
			try 
			{
				if(logWriter != null)
				{
				logWriter.write(mLogDate);
				}
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	/**
	 * Updates log information
	 * @param aEntry
	 */
	public void updateLogger (String aEntry) {  //String aEntry
		
		for(int logCount = 0; logCount< 1; logCount++)
		{
			try 
			{
				logWriter.write("," + "aEntry");
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * Stops accepting log entries
	 */
	public void endLogger ()
	{
		try
		{
			logWriter.write("\n");
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Closes file-stream
	 */
	public void stop () 
	{
		try 
		{
			logWriter.close();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
