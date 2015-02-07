package com.snobot.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.snobot.ConfigurationNames;
import com.snobot.Snobot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

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
    	aLine = aLine.trim();
    	if(aLine.isEmpty() || aLine.startsWith("#"))
    	{
    		return;
    	}
    	
        StringTokenizer tokenizer = new StringTokenizer(aLine, sDELIMITER);

        List<String> args = new ArrayList<>();

        while (tokenizer.hasMoreElements())
        {
            args.add(tokenizer.nextToken());
        }

        Command newCommand = null;

        boolean isParallel;
        if (args.get(0).startsWith("&"))
        {
            isParallel = true;
            args.set(0, args.get(0).substring(1));
        }
        else
        {
            isParallel = false;
        }

        try
        {
            switch (args.get(0))
            {

            case ConfigurationNames.sDRIVE_FORWARD_COMMAND:
                newCommand = new DriveForward(
                        Double.parseDouble(args.get(1)), 
                        Double.parseDouble(args.get(2)), 
                        mSnobot.getDriveTrain(),
                        mSnobot.getPositioner());
                break;
                
            case ConfigurationNames.sDRIVE_ROTATE_COMMAND:
                newCommand = new DriveRotate(
                        Double.parseDouble(args.get(1)), 
                        Double.parseDouble(args.get(2)), 
                        mSnobot.getDriveTrain(), 
                        mSnobot.getPositioner());
                break;
            case ConfigurationNames.sRAW_STACK_COMMAND:
                newCommand = new RawStack(
                        Double.parseDouble(args.get(1)),
                        Boolean.parseBoolean(args.get(2)),
                        mSnobot.getSnobotStacker());
                break;
            case ConfigurationNames.sCLAW_GRAB_COMMAND:
                    newCommand = new ClawGrab(
                            Boolean.parseBoolean(args.get(1)), 
                            Double.parseDouble(args.get(2)), 
                            mSnobot.getSnobotClaw());
                break;
            case ConfigurationNames.sMOVE_CLAW_COMMAND:
                newCommand = new MoveClaw(
                        Boolean.parseBoolean(args.get(1)),
                        Double.parseDouble(args.get(2)),
                        mSnobot.getSnobotClaw());
                break;
                    
            }
        }
        catch (IndexOutOfBoundsException e)
        {
            System.err.println("!!!!!! Index out of bounds... " + e.getMessage());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        
        if (newCommand==null)
        {
            System.out.println("Can't add null command for name : " + args.get(0));
        }
        
        else if (isParallel)
        {
        	aGroup.addParallel(newCommand);
        }
        else
        {
        	aGroup.addSequential(newCommand);
        }
    }
    
    public CommandGroup readFile(String aFilePath)
    {
    	System.out.println("Reading auton file : " + aFilePath);
    	CommandGroup output = new CommandGroup(aFilePath)
    	{
    	    @Override
    	    protected void end()
    	    {
    	        System.out.println("Command group finished!");
    	    }
    	};
    	
    	
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
