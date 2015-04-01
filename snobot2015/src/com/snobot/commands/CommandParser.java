package com.snobot.commands;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import com.snobot.Properties2015;
import com.snobot.SmartDashboardNames;
import com.snobot.Snobot;
import com.snobot.commands.raw.DriveForward;
import com.snobot.commands.raw.DriveRotate;
import com.snobot.commands.raw.RawDriveFoward;
import com.snobot.commands.raw.RawRotateCommand;
import com.snobot.commands.raw.RawStack;
import com.snobot.xlib.ACommandParser;
import com.snobot.xlib.path.SimplePathPoint;
import com.snobot.xlib.path.simple.SimplePathDeserializer;
import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.io.TextFileDeserializer;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CommandParser extends ACommandParser
{
    private static final String sDELIMITER = " ";
    private static final String sCOMMENT_START = "#";
    private Snobot mSnobot;

    // Autonomous Commands
    public static final String sSET_POSITION_COMMAND = "SetPosition";
    public static final String sDRIVE_FORWARD_COMMAND = "DriveForward";
    public static final String sDRIVE_FORWARD_SMARTER_COMMAND = "DriveForwardSmart";
    public static final String sDRIVE_ROTATE_COMMAND = "DriveRotate";
    public static final String sDRIVE_ROTATE_SMARTER_COMMAND = "DriveRotateSmart";
    public static final String sRAW_STACK_COMMAND = "RawStack";
    public static final String sCLAW_GRAB_COMMAND = "ClawGrab";
    public static final String sMOVE_CLAW_COMMAND = "MoveClaw";
    public static final String sSMART_STACK_COMMAND = "SmartStack";
    public static final String sRAW_DRIVE_COMMAND = "RawDrive";
    public static final String sRAW_ROTATE_COMMAND = "RawRotate";
    public static final String sTURN_SIMPLE_COMMAND = "SimplePathRotate";
    public static final String sSTRAIGHT_SIMPLE_COMMAND = "SimplePathDrive";
    public static final String sDRIVE_SPLINE_COMMAND = "DriveSplineCommand";
    public static final String sTHREE_TOTE_STACK_COMMAND = "ThreeToteStackCommand";
    public static final String sRAKE_COMMAND = "RakeCommand";
    
    public CommandParser(Snobot aSnobot)
    {
        super(sDELIMITER, sCOMMENT_START);
        mSnobot = aSnobot;

    }

    protected Command parseCommand(List<String> args)
    {
        String commandName = args.get(0);

        Command newCommand = null;

        String pathsDir = Properties2015.sPATH_DIR.getValue();


        try
        {
            switch (commandName)
            {
            
            // Not really a command, but a special case
            case sSET_POSITION_COMMAND:
                mSnobot.getPositioner().setPosition(
                        Double.parseDouble(args.get(1)),
                        Double.parseDouble(args.get(2)),
                        Double.parseDouble(args.get(3)));
                break;
            case sDRIVE_FORWARD_COMMAND:
                newCommand = new DriveForward(
                        Double.parseDouble(args.get(1)), 
                        Double.parseDouble(args.get(2)), 
                        Double.parseDouble(args.get(3)),
                        mSnobot.getDriveTrain(),
                        mSnobot.getPositioner());
                break;
                
            case sDRIVE_FORWARD_SMARTER_COMMAND:
                newCommand = new DriveForwardSmartur(
                        Double.parseDouble(args.get(1)), 
                        mSnobot.getDriveTrain(),
                        mSnobot.getPositioner());
                break;

            case sDRIVE_ROTATE_COMMAND:
                newCommand = new DriveRotate(
                        Double.parseDouble(args.get(1)), 
                        Double.parseDouble(args.get(2)), 
                        Double.parseDouble(args.get(3)),
                        mSnobot.getDriveTrain(), 
                        mSnobot.getPositioner());
                break;

            case sRAW_DRIVE_COMMAND:
                newCommand = new RawDriveFoward(Double.parseDouble(args.get(1)), Double.parseDouble(args.get(2)),
                        mSnobot.getDriveTrain());
                break;

            case sRAW_ROTATE_COMMAND:
                newCommand = new RawRotateCommand(Double.parseDouble(args.get(1)), Double.parseDouble(args.get(2)),
                        mSnobot.getDriveTrain());
                break;

            case sDRIVE_ROTATE_SMARTER_COMMAND:
                newCommand = new DriveRotateSmartur(
                        Double.parseDouble(args.get(1)), 
                        mSnobot.getDriveTrain(), 
                        mSnobot.getPositioner());
                break;

            case sRAW_STACK_COMMAND:
                newCommand = new RawStack(
                        Double.parseDouble(args.get(1)),
                        Boolean.parseBoolean(args.get(2)),
                        mSnobot.getSnobotStacker());
                break;
                
            case sCLAW_GRAB_COMMAND:
                    newCommand = new ClawGrab(
                        Boolean.parseBoolean(args.get(1)), 
                        Double.parseDouble(args.get(2)), 
                        mSnobot.getSnobotClaw());
                break;
                
            case sMOVE_CLAW_COMMAND:
                newCommand = new MoveClaw(
                        Boolean.parseBoolean(args.get(1)),
                        Double.parseDouble(args.get(2)),
                        mSnobot.getSnobotClaw());
                break;
                
            case sSMART_STACK_COMMAND:
                newCommand = new SmartStack(
                        Integer.parseInt(args.get(1)),
                        mSnobot.getSnobotStacker());
                break;
            case sTURN_SIMPLE_COMMAND:
            {
                String path = pathsDir + "/" + args.get(1);
                SimplePathDeserializer mSimpleDeserializer = new SimplePathDeserializer();
                List<SimplePathPoint> points = mSimpleDeserializer.deserialize(path);
                double hackFactor = 1;

                if (args.size() >= 3)
                {
                    hackFactor = Double.parseDouble(args.get(2));
                }

                if (points.isEmpty())
                {
                    addError("Could not read SimplePoint path at '" + path + "'");
                }

                newCommand = new TurnSimplePath(mSnobot.getDriveTrain(), mSnobot.getPositioner(), points, hackFactor);

                break;
            }
            case sSTRAIGHT_SIMPLE_COMMAND:
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
            case sDRIVE_SPLINE_COMMAND:
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
            case sTHREE_TOTE_STACK_COMMAND:
            {
                newCommand = new ThreeToteStackCommand(mSnobot.getSnobotStacker(), Double.parseDouble(args.get(1)), Double.parseDouble(args.get(2)),
                        Double.parseDouble(args.get(3)), Double.parseDouble(args.get(4)));

                break;
            }
            case sRAKE_COMMAND:
            {
                boolean goDown = Boolean.parseBoolean(args.get(2));
                newCommand = new RakeCommand(mSnobot.getRake(), Double.parseDouble(args.get(1)), goDown);

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

        return newCommand;
    }

    protected void publishParsingResults(String aCommandString)
    {
        if (!mErrorText.isEmpty())
        {
            aCommandString += "\n\n# There was an error parsing the commands...\n#\n";
            aCommandString += mErrorText;
        }

        SmartDashboard.putString(SmartDashboardNames.sROBOT_COMMAND_TEXT, aCommandString);
        SmartDashboard.putBoolean(SmartDashboardNames.sSUCCESFULLY_PARSED_AUTON, mSuccess);
    }

    protected void initReading()
    {
        super.initReading();

        mSnobot.getPositioner().setPosition(0, 0, 0);
        SmartDashboard.putString(SmartDashboardNames.sSIMPLE_IDEAL_PATH, "");
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
