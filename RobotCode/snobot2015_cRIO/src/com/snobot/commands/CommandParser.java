package com.snobot.commands;


import com.snobot.Properties2015;
import com.snobot.SmartDashboardNames;
import com.snobot.Snobot;
import com.snobot.commands.raw.DriveForward;
import com.snobot.commands.raw.DriveRotate;
import com.snobot.commands.raw.RawDriveFoward;
import com.snobot.commands.raw.RawRotateCommand;
import com.snobot.commands.raw.RawStack;
import com.snobot.xlib.ACommandParser;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import fake_java.io.File;
import fake_java.io.FileWriter;
import java.util.Vector;

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
    public static final String sPARALLEL_COMMAND = "Parallel";
    public static final String sWAIT_COMMAND = "Wait";

    public CommandParser(Snobot aSnobot)
    {
        super(sDELIMITER, sCOMMENT_START);
        mSnobot = aSnobot;

    }

//    @Override
    protected Command parseCommand(Vector args)
    {
        String commandName = (String) args.elementAt(0);

        Command newCommand = null;

        try
        {
 
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

//    @Override
    protected void publishParsingResults(String aCommandString)
    {
        if (mErrorText.length() != 0)
        {
            aCommandString += "\n\n# There was an error parsing the commands...\n#\n";
            aCommandString += mErrorText;
        }

        SmartDashboard.putString(SmartDashboardNames.sROBOT_COMMAND_TEXT, aCommandString);
        SmartDashboard.putBoolean(SmartDashboardNames.sSUCCESFULLY_PARSED_AUTON, mSuccess);
    }

//    @Override
    protected void initReading()
    {
        super.initReading();

        mSnobot.getPositioner().setPosition(0, 0, 0);
        SmartDashboard.putString(SmartDashboardNames.sSIMPLE_IDEAL_PATH, "");
        SmartDashboard.putString(SmartDashboardNames.sSPINE_IDEAL, "");
    }

    public void saveAutonMode()
    {
        String new_text = SmartDashboard.getString(SmartDashboardNames.sSD_COMMAND_TEXT, "");
        String filename = SmartDashboard.getString(SmartDashboardNames.sAUTON_FILENAME, "auton_file.txt");

        System.out.println("*****************************************");
        System.out.println("Saving auton mode");
        System.out.println("*****************************************");

        try
        {
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
