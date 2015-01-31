package com.snobot;

import edu.wpi.first.wpilibj.Preferences;

/**
 * This is the maps of the ports.
 * 
 * @author jbnol_000
 *
 */
public class ConfigurationNames {

    // Joysticks
    public static final String sOPERATOR_JOYSTICK_PORT = "OperatorJoystickPort";
    public static final String sDRIVER_FLIGHTSTICK_1_PORT = "DriverFlightstick1Port";
    public static final String sDRIVER_FLIGHTSTICK_2_PORT = "DriverFlightstick2Port";

    // Flightsticks Control
    // TODO PJ doesn't much care for this
    public static final int sFlightsticks_Y_Axis = 1;
    public static final int sFlightsticks_X_Axis = 0;
    public static final int sFlightsticks_Button_4 = 4;
    public static final int sFlightsticks_Button_5 = 5;

    // Motors
    public static final String sDRIVE_MOTOR_LEFT_1 = "LeftDriveMotor1";
    public static final String sDRIVE_MOTOR_RIGHT_1 = "RightDriveMotor1";
    public static final String sSTACKER_MOTOR = "StackerMotor";

    // Solenoid
    public static final String sAir_Pressure_Range_Min = "AirPressureRangeMin";
    public static final String sCLAW_HAND_SOLENOID = "ClawHandSolenoid";
    public static final String sCLAW_ARM_SOLENOID = "ClawArmSolenoid";

    // Digital Inputs
    public static final String sSTACKER_UPPER_LIMIT_SWITCH_PORT_1 = "UpperStackerLimit";
    public static final String sSTACKER_LOWER_LIMIT_SWITCH_PORT_1 = "LowerStackerLimit";

    public static final String sRIGHT_DRIVE_ENC_A = "RightDriveEnc_A";
    public static final String sRIGHT_DRIVE_ENC_B = "RightDriveEnc_B";
    public static final String sLEFT_DRIVE_ENC_A = "LeftDriveEnc_A";
    public static final String sLEFT_DRIVE_ENC_B = "LeftDriveEnc_B";
    public static final String sSTACKER_ENCODER_A = "StackerEncoder_A";
    public static final String sSTACKER_ENCODER_B = "StackerEncoder_B";

    // Analog Inputs
    public static final String sGYRO_SENSOR = "GyroSensor";

    // Misc. Variables
    public static final String sSTACKER_DEFAULT_SPEED = "StackerDefaultSpeed";

    // Logger
    public static final String sLOG_COUNT = "LogCount";
    public static final String sLOG_FILE_PATH = "LogFilePath";

    // Autonomous Commands
    public static final String sDRIVE_FORWARD_COMMAND = "drive_forward";
    // ////////////////////////////////
    // Preference Wrapper Functions //
    // ////////////////////////////////

    private static boolean sPropertyAdded = false;

    public static double getOrSetPropertyDouble(String aName, double aDefault) {
        if (Preferences.getInstance().containsKey(aName)) {
            return Preferences.getInstance().getDouble(aName, aDefault);
        }

        sPropertyAdded = true;
        Preferences.getInstance().putDouble(aName, aDefault);
        return aDefault;
    }

    public static int getOrSetPropertyInt(String aName, int aDefault) {
        if (Preferences.getInstance().containsKey(aName)) {
            return Preferences.getInstance().getInt(aName, aDefault);
        }

        sPropertyAdded = true;
        Preferences.getInstance().putInt(aName, aDefault);
        return aDefault;
    }

    public static String getOrSetPropertyString(String aName, String aDefault) {
        if (Preferences.getInstance().containsKey(aName)) {
            return Preferences.getInstance().getString(aName, aDefault);
        }

        sPropertyAdded = true;
        Preferences.getInstance().putString(aName, aDefault);
        return aDefault;
    }

    public static void saveIfUpdated() {
        if (sPropertyAdded) {
            Preferences.getInstance().save();
            System.out.println("-------------------------------------------");
            System.out.println("Config file updated, saving it");
            System.out.println("-------------------------------------------");
        }
    }

}
