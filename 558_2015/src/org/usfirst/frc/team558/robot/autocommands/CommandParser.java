package org.usfirst.frc.team558.robot.autocommands;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import com.snobot.xlib.ACommandParser;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CommandParser extends ACommandParser
{
    private static final String sDELIMITER = " ";
    private static final String sCOMMENT_START = "#";

    // Autonomous Commands
    private static final String sPARALLEL_COMMAND = "Parallel";
    private static final String sWAIT_COMMAND = "Wait";
    private static final String sCLOSEGRIPPERCOMMAND = "CloseGripperCommand";
    private static final String sDONOTHINGCOMMAND = "DoNothingCommand";
    private static final String sDRIVEBACKWARDSCOMMAND = "DriveBackwardsCommand";
    private static final String sDRIVEFORWARDCOMMAND = "DriveForwardCommand";
    private static final String sRAISEELEVATORCOMMAND = "RaiseElevatorCommand";
    private static final String sSTOPELEVATORCOMMAND = "StopElevatorCommand";
    private static final String sTESTENCODERCOMMAND = "TestEncoderCommand";
    private static final String sTURNENCODERCOMMAND = "TurnEncoderCommand";
    private static final String sWHALETAILFINSOUTCOMMAND = "WhaleTailFinsOutCommand";
    private static final String sWHALETAILINCOMMAND = "WhaleTailInCommand";
    private static final String sWHALETAILOUTCOMMAND = "WhaleTailOutCommand";

    public CommandParser()
    {
        super(sDELIMITER, sCOMMENT_START);

    }

    @Override
    protected Command parseCommand(List<String> args)
    {
        String commandName = args.get(0);

        Command newCommand = null;

        try
        {
            switch (commandName)
            {
            case sPARALLEL_COMMAND:
            {
                newCommand = parseParallelCommand(args);
                break;
            }
            case sWAIT_COMMAND:
            {
                double time = Double.parseDouble(args.get(1));
                newCommand = new WaitCommand(time);
                break;
            }

            case sCLOSEGRIPPERCOMMAND:
            {

                newCommand = new CloseGripperCommand();
                break;
            }

            case sDONOTHINGCOMMAND:
            {
                double lTimeout = Double.parseDouble(args.get(1));

                newCommand = new DoNothingCommand( lTimeout);
                break;
            }

            case sDRIVEBACKWARDSCOMMAND:
            {
                double lTimeout = Double.parseDouble(args.get(1));

                newCommand = new DriveBackwardsCommand( lTimeout);
                break;
            }

            case sDRIVEFORWARDCOMMAND:
            {
                double lTimeout = Double.parseDouble(args.get(1));

                newCommand = new DriveForwardCommand( lTimeout);
                break;
            }

            case sRAISEELEVATORCOMMAND:
            {
                double lTimeout = Double.parseDouble(args.get(1));

                newCommand = new RaiseElevatorCommand( lTimeout);
                break;
            }

            case sSTOPELEVATORCOMMAND:
            {

                newCommand = new StopElevatorCommand();
                break;
            }

            case sTESTENCODERCOMMAND:
            {
                double lDistance = Double.parseDouble(args.get(1));
                double lSpeed = Double.parseDouble(args.get(2));
                double lTimeout = Double.parseDouble(args.get(3));

                newCommand = new TestEncoderCommand( lDistance,  lSpeed,  lTimeout);
                break;
            }

            case sTURNENCODERCOMMAND:
            {
                double lAngle = Double.parseDouble(args.get(1));
                double lSpeed = Double.parseDouble(args.get(2));
                double lTimeout = Double.parseDouble(args.get(3));

                newCommand = new TurnEncoderCommand( lAngle,  lSpeed,  lTimeout);
                break;
            }

            case sWHALETAILFINSOUTCOMMAND:
            {

                newCommand = new WhaleTailFinsOutCommand();
                break;
            }

            case sWHALETAILINCOMMAND:
            {
                double lTimeout = Double.parseDouble(args.get(1));

                newCommand = new WhaleTailInCommand( lTimeout);
                break;
            }

            case sWHALETAILOUTCOMMAND:
            {
                double lTimeout = Double.parseDouble(args.get(1));

                newCommand = new WhaleTailOutCommand( lTimeout);
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
            addError("Unknown exception has occurred parsing: " + commandName + ".  " + e.getMessage());
            e.printStackTrace();
        }

        return newCommand;
    }

    @Override
    protected void publishParsingResults(String aCommandString)
    {
        if (!mErrorText.isEmpty())
        {
            aCommandString += "\n\n# There was an error parsing the commands...\n#\n";
            aCommandString += mErrorText;
        }

        SmartDashboard.putString("Robot Command text", aCommandString);
        SmartDashboard.putBoolean("Parsed Command", mSuccess);

        System.out.println(aCommandString);
    }

    public void saveAutonMode()
    {
        String new_text = SmartDashboard.getString("SD Command text", "");
        String filename = SmartDashboard.getString("Auton Filename", "auton_file.txt");

        System.out.println("*****************************************");
        System.out.println("Saving auton mode");
        System.out.println("*****************************************");

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