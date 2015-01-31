package com.snobot.logger;

import java.io.FileWriter;
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
    private String mLogHeader;

    private String mLogDate;
    private FileWriter mLogWriter;

    private int mCurrentLogCount;
    private int mConfigLogCount;
    private String mLogFilePath;

    public Logger(String aLogDate)
    {
        mLogDate = aLogDate;

    }

    /**
     * Initializes member-variables and opens file-stream
     * 
     * @throws IOException
     */
    public void init()
    {

        mConfigLogCount = ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sLOG_COUNT, 25);
        mLogFilePath = ConfigurationNames.getOrSetPropertyString(ConfigurationNames.sLOG_FILE_PATH, "");

        mCurrentLogCount = 0;

        try
        {
            mLogWriter = new FileWriter(mLogFilePath + "RobotLog_" + mLogDate + "_log.csv");

            mLogWriter.write(mLogDate);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    /**
     * Adds a new header to represent logged data
     * 
     * @param aHeader
     */
    public void addHeader(String aHeader)
    {

        try
        {
            if (mLogWriter != null)
            {
                mLogWriter.write("," + aHeader);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Stops accepting new headers
     */
    public void endHeader()
    {
        try
        {
            if (mLogWriter != null)
            {
                mLogWriter.write("\n");
                mLogWriter.flush();
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Begins accepting new log entries
     */
    public void startLogEntry(String mLogDate)
    {

        try
        {
            if (mLogWriter != null)
            {
                mLogWriter.write(mLogDate);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    /**
     * Updates log information
     * 
     * @param aEntry
     */
    public void updateLogger(String aEntry)
    {
        try
        {
            if (mLogWriter != null)
            {
                mLogWriter.write("," + aEntry);
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Updates log information
     * 
     * @param aEntry
     */
    public void updateLogger(int aEntry)
    {
        updateLogger("," + aEntry);
    }

    /**
     * Updates log information
     * 
     * @param aEntry
     */
    public void updateLogger(double aEntry)
    {
       updateLogger("," + aEntry);
    }

    /**
     * Updates log information
     * 
     * @param aEntry
     */
    public void updateLogger(boolean aEntry)
    {
        updateLogger("," + aEntry);

    }

    /**
     * Stops accepting log entries
     */
    public void endLogger()
    {
        try
        {
            if (mLogWriter != null)
            {
                mLogWriter.write("\n");
                mLogWriter.flush();
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Closes file-stream
     */
    public void stop()
    {
        try
        {
            if (mLogWriter != null)
            {
                mLogWriter.close();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Lets robot check for when to log
     */
    public boolean logNow()
    {
        if (mCurrentLogCount < mConfigLogCount)
        {
            mCurrentLogCount++;
            return false;
        }
        else
        {
            mCurrentLogCount = 0;
            return true;
        }
    }
}
