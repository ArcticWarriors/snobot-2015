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

	private String mLogDate;
	private FileWriter mLogWriter;

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
			mLogWriter = new FileWriter ("RobotLog_" + mLogDate);
			
			for(int logCount = 0; logCount< 1; logCount++)
			{
				mLogWriter.write(mLogDate);
			
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
		for(int logCount = 0;logCount<1;logCount++)
		{
			
				try
				{
					if(mLogWriter != null)
					{
						mLogWriter.write("," + "aHeader");
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
	 * Stops accepting new headers
	 */
	public void endHeader () 
	{
		try
		{	
			if(mLogWriter != null)
			{
				mLogWriter.write("\n");
				mLogWriter.flush();
			}
			
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Begins accepting new log entries
	 */
	public void startLogEntry (String mLogDate) {
		
		for(int logCount = 0; logCount< 1; logCount++)
		{
			try 
			{
				if(mLogWriter != null)
				{
					mLogWriter.write(mLogDate);
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
	public void updateLogger (String aEntry) {
		
		for(int logCount = 0; logCount< 1; logCount++)
		{
			try 
			{
				if(mLogWriter != null)
				{
					mLogWriter.write("," + "aEntry");
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
	public void updateLogger (int aEntry) {
		
		for(int logCount = 0; logCount< 1; logCount++)
		{
			try 
			{
				if(mLogWriter != null)
				{
					mLogWriter.write("," + "aEntry");
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
	public void updateLogger (double aEntry) {
		
		for(int logCount = 0; logCount< 1; logCount++)
		{
			try 
			{
				if(mLogWriter != null)
				{
					mLogWriter.write("," + "aEntry");
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
	public void updateLogger (boolean aEntry) {
		
		for(int logCount = 0; logCount< 1; logCount++)
		{
			try 
			{
				if(mLogWriter != null)
				{
					mLogWriter.write("," + "aEntry");
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
	 * Stops accepting log entries
	 */
	public void endLogger ()
	{
		try
		{
			if(mLogWriter != null)
			{
				mLogWriter.write("\n");
				mLogWriter.flush();
			}
			
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
			if(mLogWriter != null)
			{
				mLogWriter.close();
			}
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
