package com.snobot.commands;

import com.snobot.ConfigurationNames;
import com.snobot.Snobot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import fake_java.io.BufferedReader;
import fake_java.io.File;
import fake_java.io.FileReader;
import fake_java.util.ArrayList;
import fake_java.util.List;
import fake_java.util.StringTokenizer;

public class CommandParser
{
    private static final String sDELIMITER = " ";
    private Snobot mSnobot;
    
    public CommandParser(Snobot aSnobot)
    {
        mSnobot = aSnobot;
    }

    /**
     * Interprets a line as a Command and adds it to mCommands
     * 
     * @param aLine
     *            Line of text
     */
    private void commandParser(CommandGroup aGroup, String aLine)
    {
 
    }
    
    public CommandGroup readFile(String aFilePath)
    {
    	System.out.println("Reading auton file : " + aFilePath);
    	CommandGroup output = new CommandGroup(aFilePath);
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(aFilePath));
            
            String line;
            while((line = br.readLine()) != null)
            {
                this.commandParser(output, line);
            }
            
            br.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return output;
    }

    public CommandGroup parseAutonString(String aAutonString)
    {
    	CommandGroup output = new CommandGroup("From String...");
        StringTokenizer tokenizer = new StringTokenizer(aAutonString, "\n");

        while (tokenizer.hasMoreElements())
        {
            this.commandParser(output, tokenizer.nextToken());
        }
        
        return output;
    }
}
