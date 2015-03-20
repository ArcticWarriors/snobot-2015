package com.snobot.commands;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.snobot.ConfigurationNames;
import com.snobot.SmartDashboardNames;
import com.snobot.Snobot;
import com.snobot.commands.raw.DriveForward;
import com.snobot.commands.raw.DriveRotate;
import com.snobot.commands.raw.RawDriveFoward;
import com.snobot.commands.raw.RawRotateCommand;
import com.snobot.commands.raw.RawStack;
import com.snobot.xlib.path.SimplePathPoint;
import com.snobot.xlib.path.simple.SimplePathDeserializer;
import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.io.TextFileDeserializer;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
        String pathsDir = ConfigurationNames.getOrSetPropertyString(ConfigurationNames.sPATH_DIR, ConfigurationNames.sDEFAULT_PATH_DIR);
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
            commandName = commandName.substring(1);
        }
        else
        {
            isParallel = false;
        }

        try
        {
            switch (commandName)
            {
            
            // Not really a command, but a special case
            case ConfigurationNames.sSET_POSITION_COMMAND:
                mSnobot.getPositioner().setPosition(
                        Double.parseDouble(args.get(1)),
                        Double.parseDouble(args.get(2)),
                        Double.parseDouble(args.get(3)));
                break;
            case ConfigurationNames.sDRIVE_FORWARD_COMMAND:
                newCommand = new DriveForward(
                        Double.parseDouble(args.get(1)), 
                        Double.parseDouble(args.get(2)), 
                        Double.parseDouble(args.get(3)),
                        mSnobot.getDriveTrain(),
                        mSnobot.getPositioner());
                break;
                
            case ConfigurationNames.sDRIVE_FORWARD_SMARTER_COMMAND:
                newCommand = new DriveForwardSmartur(
                        Double.parseDouble(args.get(1)), 
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

            case ConfigurationNames.sRAW_DRIVE_COMMAND:
                newCommand = new RawDriveFoward(Double.parseDouble(args.get(1)), Double.parseDouble(args.get(2)),
                        mSnobot.getDriveTrain());
                break;

            case ConfigurationNames.sRAW_ROTATE_COMMAND:
                newCommand = new RawRotateCommand(Double.parseDouble(args.get(1)), Double.parseDouble(args.get(2)),
                        mSnobot.getDriveTrain());
                break;

            case ConfigurationNames.sDRIVE_ROTATE_SMARTER_COMMAND:
                newCommand = new DriveRotateSmartur(
                        Double.parseDouble(args.get(1)), 
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
            case ConfigurationNames.sTURN_SIMPLE_COMMAND:
            {
                String path = pathsDir + "/" + args.get(1);
                SimplePathDeserializer mSimpleDeserializer = new SimplePathDeserializer();
                List<SimplePathPoint> points = mSimpleDeserializer.deserialize(path);

                if (points.isEmpty())
                {
                    addError("Could not read SimplePoint path at '" + path + "'");
                }

                newCommand = new TurnSimplePath(mSnobot.getDriveTrain(), mSnobot.getPositioner(), points);

                break;
            }
            case ConfigurationNames.sSTRAIGHT_SIMPLE_COMMAND:
            {
                String path = pathsDir + "/" + args.get(1);
                SimplePathDeserializer mSimpleDeserializer = new SimplePathDeserializer();
                List<SimplePathPoint> points = mSimpleDeserializer.deserialize(path);

                if (points.isEmpty())
                {
                    addError("Could not read SimplePoint path at '" + path + "'");
                }

                newCommand = new StraightSimplePath(mSnobot.getDriveTrain(), mSnobot.getPositioner(), points);

                break;
            }
            case ConfigurationNames.sDRIVE_SPLINE_COMMAND:
            {
                String path = pathsDir + "/" + args.get(1);
                TextFileDeserializer mSimpleDeserializer = new TextFileDeserializer();
                Path points = mSimpleDeserializer.deserializeFromFile(path);

                if (points.getPair() == null)
                {
                    addError("Could not read trajectory path at '" + path + "'");
                }
                else
                {
                    newCommand = new TrajectoryPathCommand(mSnobot.getDriveTrain(), mSnobot.getPositioner(), points);
                }


                break;
            }
            case ConfigurationNames.sTHREE_TOTE_STACK_COMMAND:
            {
                newCommand = new ThreeToteStackCommand(mSnobot.getSnobotStacker(), Double.parseDouble(args.get(1)), Double.parseDouble(args.get(2)),
                        Double.parseDouble(args.get(3)), Double.parseDouble(args.get(4)));

                break;
            }
            default:
                addError("Unknown command name: " + commandName);
                break;
            }
        }
        catch (IndexOutOfBoundsException e)
        {
            addError("You have not specified enough arguments for the command: " + commandName + ".  " + e.getMessage());
        }
        catch (Exception e)
        {
            addError("Unknown exception has occured parsing: " + commandName + ".  " + e.getMessage());
            e.printStackTrace();
        }
        
        
        if (newCommand==null)
        {
            mSuccess = false;
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
    
    private CommandGroup createNewCommandGroup(String aName)
    {
        return new CommandGroup(aName)
        {
            @Override
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
        if (!mErrorText.isEmpty())
        {
            aCommandString += "\n\n# There was an error parsing the commands...\n#\n";
            aCommandString += mErrorText;
        }

        SmartDashboard.putString(SmartDashboardNames.sROBOT_COMMAND_TEXT, aCommandString);
        SmartDashboard.putBoolean(SmartDashboardNames.sSUCCESFULLY_PARSED_AUTON, mSuccess);
    }

    private void initReading()
    {
        mSuccess = true;
        mErrorText = "";

        mSnobot.getPositioner().setPosition(0, 0, 0);
    }

    public CommandGroup readFile(String aFilePath)
    {
        initReading();

        CommandGroup output = createNewCommandGroup(aFilePath);

        SmartDashboard.putString(SmartDashboardNames.sAUTON_FILENAME, aFilePath);

        String fileContents = "";
    	
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(aFilePath));
            
            String line;
            while((line = br.readLine()) != null)
            {
                this.commandParser(output, line);
                fileContents += line + "\n";
            }
            
            
            br.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        publishParsingResults(fileContents);

        return output;
    }

    public CommandGroup parseAutonString(String aAutonString)
    {
        initReading();

        CommandGroup output = createNewCommandGroup("From String");
        StringTokenizer tokenizer = new StringTokenizer(aAutonString, "\n");

        while (tokenizer.hasMoreElements())
        {
            this.commandParser(output, tokenizer.nextToken());
        }

        publishParsingResults(aAutonString);

        return output;
    }

    public void saveAutonMode()
    {
        String new_text = SmartDashboard.getString(SmartDashboardNames.sSD_COMMAND_TEXT, "");
        String filename = SmartDashboard.getString(SmartDashboardNames.sAUTON_FILENAME, "auton_file.txt");
        
        try
        {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filename)));
            bw.write(new_text);
            bw.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
