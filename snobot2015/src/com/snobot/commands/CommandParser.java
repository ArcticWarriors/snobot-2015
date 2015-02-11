package com.snobot.commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.snobot.ConfigurationNames;
import com.snobot.SmartDashboardNames;
import com.snobot.Snobot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
        String commandName = args.get(0);

        boolean isParallel;
        if (commandName.startsWith("&"))
        {
            isParallel = true;
            args.set(0, commandName.substring(1));
        }
        else
        {
            isParallel = false;
        }

        try
        {
            switch (commandName)
            {

            case ConfigurationNames.sDRIVE_FORWARD_COMMAND:
                newCommand = new DriveForward(
                        Double.parseDouble(args.get(1)), 
                        Double.parseDouble(args.get(2)), 
                        Double.parseDouble(args.get(3)),
                        mSnobot.getDriveTrain(),
                        mSnobot.getPositioner());
                break;
                
            case ConfigurationNames.sDRIVE_ROTATE_COMMAND:
                newCommand = new DriveRotate(
                        Double.parseDouble(args.get(1)), 
                        Double.parseDouble(args.get(2)), 
                        Double.parseDouble(args.get(3)),
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
            case ConfigurationNames.sSMART_STACK_COMMAND:
                newCommand = new SmartStack(
                        Integer.parseInt(args.get(1)),
                        mSnobot.getSnobotStacker());
                break;
                    
            }
        }
        catch (IndexOutOfBoundsException e)
        {
            System.err.println("!!!!!! Index out of bounds... " + e.getMessage());
            SmartDashboard.putBoolean(SmartDashboardNames.sCOMMAND_ERROR_BOOL, false);
            SmartDashboard.putString(SmartDashboardNames.sCOMMAND_ERROR_TEXT, 
                    "Not enough arguments for the command: " + commandName);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        
        if (newCommand==null)
        {
            System.out.println("Can't add null command for name : " + commandName);
            SmartDashboard.putBoolean(SmartDashboardNames.sCOMMAND_ERROR_BOOL, false);
            SmartDashboard.putString(SmartDashboardNames.sCOMMAND_ERROR_TEXT, 
                    "'" + commandName + "' is not a valid command");
        }
        
        else if (isParallel)
        {
        	aGroup.addParallel(newCommand);
        	SmartDashboard.putBoolean(SmartDashboardNames.sCOMMAND_ERROR_BOOL, true);
        }
        else
        {
        	aGroup.addSequential(newCommand);
        	SmartDashboard.putBoolean(SmartDashboardNames.sCOMMAND_ERROR_BOOL, true);
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
