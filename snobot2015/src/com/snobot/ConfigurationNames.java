package com.snobot;

import edu.wpi.first.wpilibj.Preferences;

/**
 * This is the maps of the ports.
 * 
 * @author jbnol_000
 *
 */
public class ConfigurationNames
{

    // Joysticks
    public static final String sOPERATOR_JOYSTICK_PORT = "OperatorJoystickPort";
    public static final String sDRIVER_FLIGHTSTICK_1_PORT = "DriverFlightstick1Port";
    public static final String sDRIVER_FLIGHTSTICK_2_PORT = "DriverFlightstick2Port";

    // Flightsticks Control
    public static final String sFLIGHTSTICKS_Y_AXIS = "FlightsticksYAxis";
    public static final String sFLIGHTSTICKS_X_AXIS = "FlighsticksXAxis";
    public static final String sFLIGHTSTICKS_BUTTON_SWITCH_TO_TANK = "FlighstickButtonSwitchToTank";
    public static final String sFLIGHTSTICKS_BUTTON_SWITCH_TO_ARCADE = "FlightstickButtonSwitchToArcade";
    
    public static final String sXBOX_JOYSTICK_STACKER_UP = "XboxJoystickStackerUp";
    public static final String sXBOX_JOYSTICK_STACKER_DOWN = "XboxJoystickStackerDown";
    public static final String sXBOX_BUTTON_CLAW_UP = "XboxClawUpButton";
    public static final String sXBOX_BUTTON_CLAW_DOWN = "XboxClawDownButton";
    public static final String sXBOX_BUTTON_CLAW_OPEN = "XboxClawOpenButton";
    public static final String sXBOX_BUTTON_CLAW_CLOSE = "XboxClawCloseButton";
    public static final String sXBOX_BUTTON_TANK_MODE = "XboxTankModeButton";
    public static final String sXBOX_BUTTON_ARCADE_MODE = "XboxArcadeModeButton";

    public static final String sSTACKER_TO_FLOOR_BTN = "XboxStackerToFloor";
    public static final String sSTACKER_TO_SCORINGPLATFORM_BTN = "XboxStackerToScoringPlatform";
    public static final String sSTACKER_TO_ONE_STACK_BTN = "XboxStackerToOneStack";

    // Motors
    public static final String sDRIVE_MOTOR_LEFT_1 = "LeftDriveMotor1";
    public static final String sDRIVE_MOTOR_RIGHT_1 = "RightDriveMotor1";
    public static final String sSTACKER_MOTOR = "StackerMotor";

    // Solenoid
    public static final String sAIR_PRESSURE_RANGE_MIN = "AirPressureRangeMin";
    public static final String sCLAW_HAND_SOLENOID = "ClawHandSolenoid";
    public static final String sCLAW_ARM_SOLENOID = "ClawArmSolenoid";

    // Digital Inputs
    public static final String sSTACKER_UPPER_LIMIT_SWITCH = "UpperStackerLimit";
    public static final String sSTACKER_LOWER_LIMIT_SWITCH = "LowerStackerLimit";

    public static final String sRIGHT_DRIVE_ENC_A = "RightDriveEnc_A";
    public static final String sRIGHT_DRIVE_ENC_B = "RightDriveEnc_B";
    public static final String sLEFT_DRIVE_ENC_A = "LeftDriveEnc_A";
    public static final String sLEFT_DRIVE_ENC_B = "LeftDriveEnc_B";
    public static final String sSTACKER_POT = "StackerPot";

    // Analog Inputs
    public static final String sGYRO_SENSOR = "GyroSensor";

    // Misc. Variables
    public static final String sSTACKER_GROUND_HEIGHT = "StackerGroundHeight";
    public static final String sSTACKER_SCORING_PLATFORM_HEIGHT = "StackerScoringPlatformHeight"; 
    public static final String sSTACKER_ONE_STACK_HEIGHT = "StackerOneStackeHeight";
    public static final String sSTACKER_STACKING_MARGIN = "StackerStackingMargin";

    public static final String sSTACKER_POT_VOLTS_PER_INCH = "StackerPotVoltagePerInch";
    public static final String sSTACKER_POT_MIN_VOLTS = "StackerPotMinVoltage";

    public static final String sDRIVE_ROTATE_KP_VALUE = "DriveRotateKPValue";
    public static final String sDRIVE_ROTATE_MIN_ERROR = "MinRotateError";

    public static final String sDRIVE_FORWARD_KP_VALUE = "DriveForwardKPValue";
    public static final String sDRIVE_FORWARD_MIN_ERROR = "MinForwardError";

    public static final String sAUTON_DIR = "AutonDir";
    public static final String sDEFAULT_AUTON_DIR = "../../snobot2015/resources/autonoumous/";

    // Logger
    public static final String sLOG_COUNT = "LogCount";
    public static final String sLOG_FILE_PATH = "LogFilePath";

    // Autonomous Commands
    public static final String sDRIVE_FORWARD_COMMAND = "DriveForward";
    public static final String sDRIVE_FORWARD_SMARTER_COMMAND = "DriveForwardSmart";
    public static final String sDRIVE_ROTATE_COMMAND = "DriveRotate";
    public static final String sDRIVE_ROTATE_SMARTER_COMMAND = "DriveRotateSmart";
    public static final String sRAW_STACK_COMMAND = "RawStack";
    public static final String sCLAW_GRAB_COMMAND = "ClawGrab";
    public static final String sMOVE_CLAW_COMMAND = "MoveClaw";
    public static final String sSMART_STACK_COMMAND = "SmartStack";
    
    // ////////////////////////////////
    // Preference Wrapper Functions //
    // ////////////////////////////////

    private static boolean sPropertyAdded = false;

    public static double getOrSetPropertyDouble(String aName, double aDefault)
    {
        if (Preferences.getInstance().containsKey(aName))
        {
            return Preferences.getInstance().getDouble(aName, aDefault);
        }

        sPropertyAdded = true;
        Preferences.getInstance().putDouble(aName, aDefault);
        return aDefault;
    }

    public static int getOrSetPropertyInt(String aName, int aDefault)
    {
        if (Preferences.getInstance().containsKey(aName))
        {
            return Preferences.getInstance().getInt(aName, aDefault);
        }

        sPropertyAdded = true;
        Preferences.getInstance().putInt(aName, aDefault);
        return aDefault;
    }

    public static String getOrSetPropertyString(String aName, String aDefault)
    {
        if (Preferences.getInstance().containsKey(aName))
        {
            return Preferences.getInstance().getString(aName, aDefault);
        }

        sPropertyAdded = true;
        Preferences.getInstance().putString(aName, aDefault);
        return aDefault;
    }

    public static void saveIfUpdated()
    {
        if (sPropertyAdded)
        {
            Preferences.getInstance().save();
            System.out.println("-------------------------------------------");
            System.out.println("Config file updated, saving it");
            System.out.println("-------------------------------------------");
        }
    }

}
