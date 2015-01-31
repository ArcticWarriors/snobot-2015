package com.snobot.commands;

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
    private ArrayList<String> mLines;

    private Snobot mSnobot;
    private CommandGroup mCommands;
    
    //TODO this is for testing only...
    private static final String AUTO_MODE = 
            "drive_forward 1 1 1\n" + 
            "drive_forward 2 3 5\n";

    public CommandParser(Snobot aSnobot)
    {
        mSnobot = aSnobot;
        mCommands = new CommandGroup("Autonomous Group");
        mLines = new ArrayList<String>();
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
                newCommand = new DriveForward(Double.parseDouble(args.get(1)), Double.parseDouble(args.get(2)), mSnobot.getDriveTrain(),
                        mSnobot.getPositioner());
                System.out.println("Creating foward command");
                break;
            }
        }
        catch (Exception e)
        {
            e.getStackTrace();
        }

        if (isParallel)
        {
            mCommands.addParallel(newCommand);
        }
        else
        {
            mCommands.addSequential(newCommand);
        }
    }

    /**
     * Takes text from an outside source and splits different lines
     */
    private void getAndSplitLines()
    {
        // TODO Need input to set rawCommandString
        String rawCommandString = AUTO_MODE;

        StringTokenizer tokenizer = new StringTokenizer(rawCommandString, "\n");

        List<String> args = new ArrayList<>();

        while (tokenizer.hasMoreElements())
        {
            args.add(tokenizer.nextToken());
        }

        mLines.clear();
        for (String line : args)
        {
            mLines.add(line);
        }
    }

    public CommandGroup getCommands()
    {
        return this.mCommands;
    }

    /**
     * Iterates through mLines and gives it to commandParser()
     */
    private void feedLines()
    {
        for (String line : mLines)
        {
            this.commandParser(line);
        }
    }
    
    public void fullParse()
    {
        this.getAndSplitLines();
        this.feedLines();
    }
}
