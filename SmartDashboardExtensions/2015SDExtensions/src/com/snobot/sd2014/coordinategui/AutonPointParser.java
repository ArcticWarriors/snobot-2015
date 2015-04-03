package com.snobot.sd2014.coordinategui;

import java.util.ArrayList;
import java.util.List;

/**
 * Parses a block of text that contains autonomous commands.  Assumes each
 * command is on a newline.  Looks for driving commands and parses out their
 * (x,y) and deadband positions
 * @author PJ
 */
public class AutonPointParser 
{
    private AutonPointParser()
    {
        
    }
    
    
    /**
     * Reads a list of autonomous commands and returns them in a list
     * @param aModeListing A string of autonomous commands, separated by a newline
     * @return The list of points
     */
    public static List<AutonPoint> readPoints(String aModeListing)
    {
        List<AutonPoint> lDrivingPositions = new ArrayList<AutonPoint>();
        
        String[] lines = aModeListing.split("\n");
        for(String line : lines)
        {
            line = line.trim();
            if(line.isEmpty())
            {
                continue;
            }

            int lStart = line.indexOf("(");
            int lEnd = line.lastIndexOf(")");
            if(lStart == -1 || lEnd == -1)
            {
                continue;
            }

            String commandName = line.substring(0, lStart).trim();
            if(!isGoodName(commandName))
            {
                continue;
            }


            String paramTxt = line.substring(lStart + 1, lEnd);
            String[] lParams = paramTxt.split(",");
            AutonPoint point = parsePosition(lParams);
            if(point != null)
            {
                lDrivingPositions.add(point);
            }

        }
        return lDrivingPositions;
    }      
    
    /**
     * Checks to see if the command name is something that we can parse
     * @param aName - The command name
     * @return True if it is a parsable command
     */
    private static boolean isGoodName(String aName)
    {
        return 
                aName.equals("DriveToPositionCommand") || 
                aName.equals("DrivePosition") || 
                aName.equals("DriveToPosition");
    }
    
    /**
     * Parses a position based on the given parameters
     * @param aParams The list of parameters
     * @return A new AutonPoint, or null if it could not be parsed
     */
    private static AutonPoint parsePosition(String[] aParams)
    {
        try
        {
            double x  = Double.parseDouble(aParams[0]);
            double y  = Double.parseDouble(aParams[1]);
            double db = 1;
            
            if(aParams.length >= 3)
        	{
                db = Double.parseDouble(aParams[2]);
        	}

            AutonPoint c = new AutonPoint(x, y, db);
            return c;
        }
        catch(NumberFormatException e)
        {

        }
        
        return null;
    }
}
