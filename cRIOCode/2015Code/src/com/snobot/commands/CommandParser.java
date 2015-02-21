package com.snobot.commands;

import com.snobot.SmartDashboardNames;
import com.snobot.Snobot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import fake_java.io.BufferedReader;
import fake_java.io.FileReader;
import fake_java.util.StringTokenizer;

public class CommandParser
{
    private static final String sDELIMITER = " ";
    private Snobot mSnobot;
    private String mErrorText;
    private boolean mSuccess;
    
    public CommandParser(Snobot aSnobot)
    {
        mSnobot = aSnobot;
        mErrorText = "";
        mSuccess = false;

    }

    /**
     * Interprets a line as a Command and adds it to mCommands
     * 
     * @param aLine
     *            Line of text
     */
    private void commandParser(CommandGroup aGroup, String aLine)
    {
        addError("Command parsing is not implemented...");
    }
    
    private CommandGroup createNewCommandGroup(String aName)
    {
        return new CommandGroup(aName)
        {
            
            protected void end()
            {
                System.out.println("Command group finished!");
            }
        };
    }

    private void addError(String aError)
    {
        // Put the '#' so we can pretend like the error text is a comment
        mErrorText += "# " + aError + "\n";
        mSuccess = false;
    }

    private void publishParsingResults(String aCommandString)
    {
        if (mErrorText.length() != 0)
        {
            aCommandString += "\n\n# There was an error parsing the commands...\n#\n";
            aCommandString += mErrorText;
        }

        SmartDashboard.putString(SmartDashboardNames.sROBOT_COMMAND_TEXT, aCommandString);
        SmartDashboard.putBoolean(SmartDashboardNames.sSUCCESFULLY_PARSED_AUTON, mSuccess);
    }

    public CommandGroup readFile(String aFilePath)
    {
        mSuccess = true;
        mErrorText = "";
        CommandGroup output = createNewCommandGroup(aFilePath);

        System.out.println("Reading auton file : " + aFilePath);
        SmartDashboard.putString(SmartDashboardNames.sAUTON_FILENAME, aFilePath);

        String fileContents = "";
    	
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(aFilePath));
            
            String line;
            while((line = br.readLine()) != null)
            {
                System.out.println("Read line !!!");
                this.commandParser(output, line);
                fileContents += line + "\n";
            }
            
            
            br.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            addError("Error reading file '" + aFilePath + "'");
        }
        
        publishParsingResults(fileContents);

        return output;
    }

    public CommandGroup parseAutonString(String aAutonString)
    {
        mSuccess = true;
        mErrorText = "";
        CommandGroup output = createNewCommandGroup("From String");
        StringTokenizer tokenizer = new StringTokenizer(aAutonString, "\n");

        while (tokenizer.hasMoreElements())
        {
            this.commandParser(output, tokenizer.nextToken());
        }

        publishParsingResults(aAutonString);

        return output;
    }

    public CommandGroup saveAutonMode()
    {
        String new_text = SmartDashboard.getString(SmartDashboardNames.sSD_COMMAND_TEXT, "");
        String filename = SmartDashboard.getString(SmartDashboardNames.sAUTON_FILENAME, "auton_file.txt");

        System.out.println("Saving auton mode to " + filename);
        
        return readFile(filename);
    }
}
