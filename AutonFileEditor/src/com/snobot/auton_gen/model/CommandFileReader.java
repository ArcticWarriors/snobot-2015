package com.snobot.auton_gen.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.snobot.auton_gen.model.CommandConfig.CommandArgs;

public class CommandFileReader
{
    private Map<String, CommandConfig> mAvailableCommands;

    protected final String mDelimiter;
    protected final String mCommentStart;

    public CommandFileReader(String aDelimiter, String aCommentStart)
    {
        mDelimiter = aDelimiter;
        mCommentStart = aCommentStart;

        mAvailableCommands = new HashMap<>();
    }

    public void setAvailableCommands(List<CommandConfig> aCommands)
    {
        mAvailableCommands.clear();

        for (CommandConfig config : aCommands)
        {
            mAvailableCommands.put(config.getCommandName(), config);
        }
    }

    public List<CommandConfig> readFile(String aFile)
    {
        List<CommandConfig> output = new ArrayList<CommandConfig>();

        try
        {
            BufferedReader br = new BufferedReader(new FileReader(aFile));

            String line;
            while ((line = br.readLine()) != null)
            {
                CommandConfig config = parseLine(line);
                if (config != null)
                {
                    output.add(config);
                }
            }

            br.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return output;
    }

    protected CommandConfig parseLine(String aLine)
    {
        aLine = aLine.trim();
        if (aLine.isEmpty() || aLine.startsWith(mCommentStart))
        {
            return null;
        }

        StringTokenizer tokenizer = new StringTokenizer(aLine, mDelimiter);

        List<String> args = new ArrayList<>();

        while (tokenizer.hasMoreElements())
        {
            args.add(tokenizer.nextToken());
        }

        String name = args.get(0);
        if (mAvailableCommands.containsKey(name))
        {
            CommandConfig availableCommand = mAvailableCommands.get(name);
            CommandConfig readCommand = new CommandConfig(availableCommand);

            List<CommandArgs> readArgs = readCommand.getCommandArgs();

            // System.out.println("Parsing command : " + name);

            if (args.size() - 1 != readArgs.size())
            {
                System.err.println("Could not parse line " + aLine + ", arg sizes do not match");
            }
            else
            {
                for (int i = 1; i < args.size(); ++i)
                {
                    readArgs.get(i - 1).value = args.get(i);
                }
            }

            return readCommand;

        }
        else
        {
            System.err.println("Unknown command : " + name);
        }

        return null;
    }
}
