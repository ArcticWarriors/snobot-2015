package com.snobot;

import com.snobot.xlib.PropertyManager.DoubleProperty;
import com.snobot.xlib.PropertyManager.IntegerProperty;
import com.snobot.xlib.PropertyManager.StringProperty;
import com.snobot.xlib.XboxButtonMap;

/**
 * This is the maps of the ports.
 * 
 * @author jbnol_000
 *
 */
public class Properties2015
{

    // ////////////////////////////////////////////
    // Port mappings
    // ////////////////////////////////////////////

    // Joysticks
    public static final IntegerProperty sOPERATOR_JOYSTICK_PORT = new IntegerProperty("OperatorJoystickPort", 1);
    public static final IntegerProperty sDRIVER_FLIGHTSTICK_1_PORT = new IntegerProperty("DriverFlightstick1Port", 0);
    public static final IntegerProperty sDRIVER_FLIGHTSTICK_2_PORT = new IntegerProperty("DriverFlightstick2Port", 2);

    // Flightsticks Driving
    public static final IntegerProperty sFLIGHTSTICKS_X_AXIS = new IntegerProperty("FlightsticksXAxis", 2);
    public static final IntegerProperty sFLIGHTSTICKS_Y_AXIS = new IntegerProperty("FlightsticksYAxis", 1);

    // Operator XBox
    public static final IntegerProperty sXBOX_BUTTON_CLAW_OPEN = new IntegerProperty("XboxClawOpenButton", XboxButtonMap.RB_BUTTON);
    public static final IntegerProperty sXBOX_BUTTON_CLAW_CLOSE = new IntegerProperty("XboxClawCloseButton", XboxButtonMap.LB_BUTTON);

    public static final IntegerProperty sSTACKER_TO_FLOOR_BTN = new IntegerProperty("XboxStackerToFloor", XboxButtonMap.A_BUTTON);
    public static final IntegerProperty sSTACKER_TO_SCORINGPLATFORM_BTN = new IntegerProperty("XboxStackerToScoringPlatform", XboxButtonMap.X_BUTTON);
    public static final IntegerProperty sSTACKER_TO_ONE_STACK_BTN = new IntegerProperty("XboxStackerToOneStack", XboxButtonMap.Y_BUTTON);
    public static final IntegerProperty sSTACKER_COOP_HEIGHT_BTN = new IntegerProperty("XboxStackerCoOpHeight", XboxButtonMap.B_BUTTON);

    public static final IntegerProperty sMOVE_RAKE = new IntegerProperty("MoveRake", XboxButtonMap.RIGHT_Y_AXIS);

    // Motors
    public static final IntegerProperty sDRIVE_MOTOR_LEFT_1 = new IntegerProperty("LeftDriveMotor1", 0);
    public static final IntegerProperty sDRIVE_MOTOR_RIGHT_1 = new IntegerProperty("RightDriveMotor1", 1);
    public static final IntegerProperty sSTACKER_MOTOR = new IntegerProperty("StackerMotor", 2);
    public static final IntegerProperty sRAKE_MOTOR = new IntegerProperty("RakeMotor", 3);

    // Solenoid
    public static final DoubleProperty sAIR_PRESSURE_RANGE_MIN = new DoubleProperty("AirPressureRangeMin", 60);
    public static final IntegerProperty sCLAW_HAND_SOLENOID = new IntegerProperty("ClawHandSolenoid", 2);
    public static final IntegerProperty sCLAW_ARM_SOLENOID = new IntegerProperty("ClawArmSolenoid", 1);

    // Digital Inputs
    public static final IntegerProperty sSTACKER_UPPER_LIMIT_SWITCH = new IntegerProperty("UpperStackerLimit", 1);
    public static final IntegerProperty sSTACKER_LOWER_LIMIT_SWITCH = new IntegerProperty("LowerStackerLimit", 2);

    public static final IntegerProperty sRIGHT_DRIVE_ENC_A = new IntegerProperty("RightDriveEnc_A", 5);
    public static final IntegerProperty sRIGHT_DRIVE_ENC_B = new IntegerProperty("RightDriveEnc_B", 6);
    public static final IntegerProperty sLEFT_DRIVE_ENC_A = new IntegerProperty("LeftDriveEnc_A", 7);
    public static final IntegerProperty sLEFT_DRIVE_ENC_B = new IntegerProperty("LeftDriveEnc_B", 8);
    public static final IntegerProperty sRAKE_MOTOR_LS = new IntegerProperty("RakeMotorLimitSwitch", 0);

    // Analog Inputs
    public static final IntegerProperty sGYRO_SENSOR = new IntegerProperty("GyroSensor", 0);
    public static final IntegerProperty sSTACKER_POT = new IntegerProperty("StackerPot", 1);
    public static final IntegerProperty sTRANSDUCER = new IntegerProperty("Transducer", 2);

    // ////////////////////////////////////////////
    // Control Values
    // ////////////////////////////////////////////

    // Stacker
    public static final DoubleProperty sPOT_TOP_MIN_VOLT = new DoubleProperty("StackerPotTopMinVoltage", 3.596190);
    public static final DoubleProperty sPOT_BOT_MAX_VOLT = new DoubleProperty("StackerPotBotMaxVoltage", 4.9731405);
    public static final DoubleProperty sSTACKER_MAX_HEIGHT = new DoubleProperty("StackerMaxHeight", 24);
    public static final DoubleProperty sSTACKER_LIMIT_SPEED_UP = new DoubleProperty("StackerLimitSpeedUp", 0.7);
    public static final DoubleProperty sSTACKER_LIMIT_SPEED_DOWN = new DoubleProperty("StackerLimitSpeedDown", -0.35);
    public static final DoubleProperty sSTACKER_GROUND_HEIGHT = new DoubleProperty("StackerGroundHeight", 0);
    public static final DoubleProperty sSTACKER_SCORING_PLATFORM_HEIGHT = new DoubleProperty("StackerScoringPlatformHeight", 2);
    public static final DoubleProperty sSTACKER_ONE_STACK_HEIGHT = new DoubleProperty("StackerOneStackeHeight", 13.1);
    public static final DoubleProperty sSTACKER_COOP_HEIGHT = new DoubleProperty("StackerCoOpHeight", 23.5);
    public static final DoubleProperty sSTACKER_STACKING_MARGIN = new DoubleProperty("StackerStackingMargin", .5);
    public static final DoubleProperty sSTACKER_KP = new DoubleProperty("StackerKp", .35);
    public static final DoubleProperty sSTACKER_UP_DEADBAND = new DoubleProperty("XboxJoystickStackerUp", .2);
    public static final DoubleProperty sSTACKER_DOWN_DEADBAND = new DoubleProperty("XboxJoystickStackerDown", -.2);

    // Drivetrain
    public static final DoubleProperty sLEFT_ENC_DPP = new DoubleProperty("LeftEncDpp", -0.0389483932);
    public static final DoubleProperty sRIGHT_ENC_DPP = new DoubleProperty("RightEncDpp", 0.0389483932);

    // Rake
    public static final IntegerProperty sRAKE_MOTOR_SPEED = new IntegerProperty("RakeMotorSpeed", 4);

    public static final IntegerProperty sUSE_CAMERA = new IntegerProperty("UseCamera", 0);

    // Logger
    public static final IntegerProperty sLOG_COUNT = new IntegerProperty("LogCount", 25);
    public static final StringProperty sLOG_FILE_PATH = new StringProperty("LogFilePath", "logs/");

    // ////////////////////////////////////////////
    // Autonomous
    // ////////////////////////////////////////////
    public static final String sDEFAULT_PATH_DIR = "/home/lvuser/paths/";
    public static final String sDEFAULT_AUTON_DIR = "/home/lvuser/autonomous/";

    public static final StringProperty sPATH_DIR = new StringProperty("PathDir", sDEFAULT_PATH_DIR);
    public static final StringProperty sAUTON_DIR = new StringProperty("AutonDir", sDEFAULT_AUTON_DIR);
    public static final StringProperty sAUTON_IGNORE_STRING = new StringProperty("AutonIgnoreString", "");

    // Smart rotate
    public static final DoubleProperty sDRIVE_ROTATE_KP_VALUE = new DoubleProperty("DriveRotateKPValue", 0.01);
    public static final DoubleProperty sDRIVE_ROTATE_MIN_ERROR = new DoubleProperty("MinRotateError", 2);

    // Smart drive
    public static final DoubleProperty sDRIVE_FORWARD_KP_VALUE = new DoubleProperty("DriveForwardKPValue", 0.01);
    public static final DoubleProperty sDRIVE_FORWARD_MIN_ERROR = new DoubleProperty("MinForwardError", 2);

    // Turn Path
    public static final DoubleProperty sTURN_PATH_KP = new DoubleProperty("TurnPathKP", 0.005);
    public static final DoubleProperty sTURN_PATH_KD = new DoubleProperty("TurnPathKD", 0);
    public static final DoubleProperty sTURN_PATH_KV = new DoubleProperty("TurnPathKVel", 0.0053);
    public static final DoubleProperty sTURN_PATH_KA = new DoubleProperty("TurnPathKAccel", 0.00174);

    // Drive path
    public static final DoubleProperty sDRIVE_PATH_KP = new DoubleProperty("DrivePathKP", 0.0174);
    public static final DoubleProperty sDRIVE_PATH_KD = new DoubleProperty("DrivePathKD", 0);
    public static final DoubleProperty sDRIVE_PATH_KV = new DoubleProperty("DrivePathKVel", .009);
    public static final DoubleProperty sDRIVE_PATH_KA = new DoubleProperty("DrivePathKAccel", 0.0037);
    public static final DoubleProperty sSPLINE_K_TURN = new DoubleProperty("SplineKTurn", .017);

}
