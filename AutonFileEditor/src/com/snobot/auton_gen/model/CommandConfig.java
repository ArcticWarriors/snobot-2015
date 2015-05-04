package com.snobot.auton_gen.model;

import java.util.ArrayList;
import java.util.List;

public class CommandConfig
{
    public static class CommandArgs
    {
        public String argName;
        public String value;
        public String type;

        public CommandArgs()
        {
            this("ARG NAME", "0.0", "double");
        }

        public CommandArgs(String aArgName, String aDefaultValue, String aType)
        {
            argName = aArgName;
            value = aDefaultValue;
            type = aType;
        }

        public CommandArgs(CommandArgs commandArgs)
        {
            this(commandArgs.argName, commandArgs.value, commandArgs.type);
        }
    }

    private String commandName;
    private List<CommandArgs> commandArgs;

    public CommandConfig()
    {
        commandName = "COMMAND NAME";
        commandArgs = new ArrayList<CommandConfig.CommandArgs>();
    }

    public CommandConfig(CommandConfig other)
    {
        commandName = other.commandName;
        commandArgs = new ArrayList<>();

        for (int i = 0; i < other.commandArgs.size(); ++i)
        {
            commandArgs.add(new CommandArgs(other.commandArgs.get(i)));
        }
    }


    public String getCommandName()
    {
        return commandName;
    }

    public void setCommandName(String commandName)
    {
        this.commandName = commandName;
    }

    public List<CommandArgs> getCommandArgs()
    {
        return commandArgs;
    }

    public void setCommandArgs(List<CommandArgs> commandArgs)
    {
        this.commandArgs = commandArgs;
    }

    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        builder.append(getCommandName() + " ");

        for (CommandArgs arg : getCommandArgs())
        {
            builder.append(arg.value + " ");
        }

        return builder.toString();
    }

}
