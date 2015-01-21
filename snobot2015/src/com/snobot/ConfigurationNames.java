package com.snobot;

import edu.wpi.first.wpilibj.Preferences;

public class ConfigurationNames {
	
	//Joysticks
	public static final String sOPERATOR_JOYSTICK_PORT = "OperatorJoystickPort";
	public static final String sDRIVER_FLIGHTSTICK_1_PORT = "DriverFlightstick1Port";
	public static final String sDRIVER_FLIGHTSTICK_2_PORT = "DriverFlightstick2Port";
	
	//Motors
	public static final String sDRIVE_MOTOR_LEFT_1 = "LeftDriveMotor1";
	public static final String sDRIVE_MOTOR_LEFT_2 = "LeftDriveMotor2";
	public static final String sDRIVE_MOTOR_RIGHT_1 = "RightDriveMotor1";
	public static final String sDRIVE_MOTOR_RIGHT_2 = "RightDriveMotor2";
	
	//Solenoid

	//Digital Inputs

	//Analog Inputs

	public static double getOrSetProperty(String aName, double aDefault)
	{
		if(Preferences.getInstance().containsKey(aName))
		{
			return Preferences.getInstance().getDouble(aName, aDefault);
		}
		Preferences.getInstance().putDouble(aName, aDefault);
		return aDefault;
	}

}
