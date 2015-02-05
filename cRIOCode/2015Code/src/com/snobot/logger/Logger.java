package com.snobot.logger;

import java.io.IOException;

import com.snobot.ConfigurationNames;

/**
 * Class for logger
 * 
 * @author Calvin Do
 *
 */

public class Logger
{
    public Logger(String aLogDate)
    {
    }

    /**
     * Initializes member-variables and opens file-stream
     * 
     * @throws IOException
     */
    public void init()
    {

    }

    /**
     * Adds a new header to represent logged data
     * 
     * @param aHeader
     */
    public void addHeader(String aHeader)
    {

    }

    /**
     * Begins accepting new log entries
     */
    public void startLogEntry(String mLogDate)
    {

    }

    /**
     * Updates log information
     * 
     * @param aEntry
     */
    public void updateLogger(String aEntry)
    {
       
    }

    /**
     * Updates log information
     * 
     * @param aEntry
     */
    public void updateLogger(int aEntry)
    {
        updateLogger("" + aEntry);
    }

    /**
     * Updates log information
     * 
     * @param aEntry
     */
    public void updateLogger(double aEntry)
    {
       updateLogger("" + aEntry);
    }

    /**
     * Updates log information
     * 
     * @param aEntry
     */
    public void updateLogger(boolean aEntry)
    {
        //Convert boolean to a number, then log
        updateLogger(aEntry ? 1 : 0);

    }

    /**
     * Stops accepting log entries
     */
    public void endLogger()
    {
        
    }

    /**
     * Closes file-stream
     */
    public void stop()
    {
        
    }

    /**
     * Lets robot check for when to log
     */
    public boolean logNow()
    {
        return false;
    }

   public void endHeader()
   {
   }
}
