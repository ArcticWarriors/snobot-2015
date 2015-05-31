package com.snobot.xlib;

import java.util.Vector;

import com.snobot.SmartDashboardNames;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import fake_java.util.StringTokenizer;

public abstract class ACommandParser
{
    protected final String mDelimiter;
    protected final String mCommentStart;

    protected String mErrorText;
    protected boolean mSuccess;

    public ACommandParser(String aDelimiter, String aCommentStart)
    {
        mErrorText = "";
        mSuccess = false;
        mDelimiter = aDelimiter;
        mCommentStart = aCommentStart;
    }

    protected void addError(String aError)
    {
        // Put the '#' so we can pretend like the error text is a comment
        mErrorText += "# " + aError + "\n";
        mSuccess = false;
    }

    protected void initReading()
    {
        mSuccess = true;
        mErrorText = "";
    }

    protected CommandGroup createNewCommandGroup(String aName)
    {
        return new CommandGroup(aName)
        {
        //    @Override
            protected void end()
            {
                super.end();
                System.out.println("Command group finished!");
            }
        };
    }

    /**
     * Interprets a line as a Command and adds it to mCommands
     * 
     * @param aLine
     *            Line of text
     * @param b
     */
    protected void parseLine(CommandGroup aGroup, String aLine, boolean aAddParallel)
    {
        aLine = aLine.trim();
        if (aLine.length() == 0 || aLine.startsWith(mCommentStart))
        {
            return;
        }

        StringTokenizer tokenizer = new StringTokenizer(aLine, mDelimiter);

        Vector args = new Vector();

        while (tokenizer.hasMoreElements())
        {
            args.addElement(tokenizer.nextToken());
        }

        Command newCommand = parseCommand(args);

        if (newCommand == null)
        {
            mSuccess = false;
        }
        else
        {
            if (aAddParallel)
            {
                aGroup.addParallel(newCommand);
            }
            else
            {
                aGroup.addSequential(newCommand);
            }
        }
    }

    protected CommandGroup parseParallelCommand(Vector args)
    {
        String parallel_line = "";
        for (int i = 1; i < args.size(); ++i)
        {
            parallel_line += args.elementAt(i) + " ";
        }

        CommandGroup parallelCommands = new CommandGroup();

        return parallelCommands;
    }

    public CommandGroup readFile(String aFilePath)
    {
        initReading();

        CommandGroup output = createNewCommandGroup(aFilePath);

        SmartDashboard.putString(SmartDashboardNames.sAUTON_FILENAME, aFilePath);

        String fileContents = "";

        try
        {
            // BufferedReader br = new BufferedReader(new FileReader(aFilePath));
            //
            // String line;
            // while ((line = br.readLine()) != null)
            // {
            // this.parseLine(output, line, false);
            // fileContents += line + "\n";
            // }
            //
            // br.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        publishParsingResults(fileContents);

        return output;
    }

    public SendableChooser loadAutonFiles(String aDir, String aIgnoreString)
    {
        SendableChooser output = new SendableChooser();

        output.addObject("EMPTY", new CommandGroup());

        return output;
    }

    protected abstract Command parseCommand(Vector args);

    protected abstract void publishParsingResults(String fileContents);
}
