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
    private CommandGroup mCommands;
    
    public CommandParser(Snobot aSnobot)
    {
        mSnobot = aSnobot;
        mCommands = new CommandGroup("Autonomous Group");
    }

    /**
     * Interprets a line as a Command and adds it to mCommands
     * 
     * @param aLine
     *            Line of text
     */
    private void commandParser(String aLine)
    {
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
                            mSnobot.getSnobotClaw());
                break;
            case ConfigurationNames.sMOVE_CLAW_COMMAND:
                System.out.println("Moving claw");
                newCommand = new MoveClaw(
                        Boolean.parseBoolean(args.get(1)), 
                        mSnobot.getSnobotClaw());
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
            mCommands.addParallel(newCommand);
        }
        else
        {
            mCommands.addSequential(newCommand);
        }
    }


    public CommandGroup getCommands()
    {
        return this.mCommands;
    }
    
    public void parseAutonString(String aAutonString)
    {
        StringTokenizer tokenizer = new StringTokenizer(aAutonString, "\n");

        while (tokenizer.hasMoreElements())
        {
            this.commandParser(tokenizer.nextToken());
        }
    }
    
    public void readFile(String aFilePath)
    {
        File autonFile = new File(aFilePath);
        
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(autonFile));
            
            String line;
            while((line = br.readLine()) != null)
            {
                this.commandParser(line);
            }
            
            br.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.err.println(autonFile.getAbsolutePath());
        }
    }
}
