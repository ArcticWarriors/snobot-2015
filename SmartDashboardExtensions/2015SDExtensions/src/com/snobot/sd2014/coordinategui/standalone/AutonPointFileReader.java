package com.snobot.sd2014.coordinategui.standalone;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

import com.snobot.sd2014.coordinategui.AutonPoint;
import com.snobot.sd2014.coordinategui.AutonPointParser;

/**
 * Reads a file containing a list of autonomous points, separated by a newline
 * @author PJ
 */
public class AutonPointFileReader 
{

    private AutonPointFileReader()
    {
    }
    
    /**
     * Reads the autonomous points in the given file.
     * @param aFile The file to read
     * @return The list of autonomous points
     */
    public static List<AutonPoint> readPointsFromFile(String aFile)
    {
    	String text = readAutonFile(aFile);
        return AutonPointParser.readPoints(text);
    }      

    public static List<AutonPoint> readPointsFromString(String aString)
    {
        return AutonPointParser.readPoints(aString);
    }      
    
    public static String readAutonFile(final String aFile)
    {

        StringBuilder autonText = new StringBuilder();
        String line;

        try
        {
            FileReader fr = new FileReader(aFile);
            BufferedReader br = new BufferedReader(fr);

            while ((line = br.readLine()) != null)
            {
                line = line.trim();
                if(line.isEmpty())
                {
                    continue;
                }
                
                autonText.append(line).append("\n");
            }
            
            br.close();
        }
        catch (Exception ex)
        {
            System.err.println(ex);
        }
        
        return autonText.toString();
    }
}
