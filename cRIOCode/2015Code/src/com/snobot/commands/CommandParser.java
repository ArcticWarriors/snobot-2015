package com.snobot.commands;

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
    
    public CommandGroup readFile(String aFilePath)
    {
         return new CommandGroup(aFilePath);
    }

    public CommandGroup parseAutonString(String aAutonString)
    {
         return new CommandGroup();
    }
}
