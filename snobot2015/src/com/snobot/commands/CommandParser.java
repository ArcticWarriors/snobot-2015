package com.snobot.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.snobot.ConfigurationNames;
import com.snobot.SmartDashboardNames;
import com.snobot.Snobot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

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
                // TODO Debugger; remove later
                System.out.println("Command parsed!");
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
                System.out.println("Moving claw");
                newCommand = new MoveClaw(
                        Boolean.parseBoolean(args.get(1)),
                        Double.parseDouble(args.get(2)),
                        mSnobot.getSnobotClaw());
                break;
            case ConfigurationNames.sSMART_STACK_COMMAND:
                newCommand = new SmartStack(
                        Integer.parseInt(args.get(1)),
                        mSnobot.getSnobotStacker());
                break;
                    
            }
        }
        catch (Exception e)
        {
            e.getStackTrace();
        }
        
        
        if (newCommand==null)
        {
            System.out.println("Can't add null command");
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
            String fileContents = "";
            BufferedReader br = new BufferedReader(new FileReader(aFilePath));
            
            String line;
            while((line = br.readLine()) != null)
            {
                this.commandParser(output, line);
                fileContents += line + "\n";
            }
            
            SmartDashboard.putString(SmartDashboardNames.sROBOT_COMMAND_TEXT, fileContents);
            
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
