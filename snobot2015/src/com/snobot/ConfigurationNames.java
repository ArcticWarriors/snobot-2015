package com.snobot;

import edu.wpi.first.wpilibj.Preferences;

public class ConfigurationNames {
	
	private static boolean sPropertyAdded = false;
	
	//Joysticks
	public static final String sOPERATOR_JOYSTICK_PORT = "OperatorJoystickPort";
	public static final String sDRIVER_FLIGHTSTICK_1_PORT = "DriverFlightstick1Port";
	public static final String sDRIVER_FLIGHTSTICK_2_PORT = "DriverFlightstick2Port";
	
	public static final String sJoystickMode_Xbox = "Xbox mode";
	public static final String sJoystickMode_flightstick = "Flightstick Mode";
	public static final String sJoystickMode = "sJoystickMode";
	
	//Motors
	public static final String sDRIVE_MOTOR_LEFT_1 = "LeftDriveMotor1";
	public static final String sDRIVE_MOTOR_LEFT_2 = "LeftDriveMotor2";
	public static final String sDRIVE_MOTOR_RIGHT_1 = "RightDriveMotor1";
	public static final String sDRIVE_MOTOR_RIGHT_2 = "RightDriveMotor2";
	
	//Solenoid

	//Digital Inputs

	//Analog Inputs

	public static double getOrSetPropertyDouble(String aName, double aDefault)
	{
		if(Preferences.getInstance().containsKey(aName))
		{
			return Preferences.getInstance().getDouble(aName, aDefault);
		}
		
		sPropertyAdded = true;
		Preferences.getInstance().putDouble(aName, aDefault);
		return aDefault;
	}

	public static int getOrSetPropertyInt(String aName, int aDefault)
	{
		if(Preferences.getInstance().containsKey(aName))
		{
			return Preferences.getInstance().getInt(aName, aDefault);
		}
		
		sPropertyAdded = true;
		Preferences.getInstance().putInt(aName, aDefault);
		return aDefault;
	}

	public static String getOrSetPropertyString(String aName, String aDefault)
	{
		if(Preferences.getInstance().containsKey(aName))
		{
			return Preferences.getInstance().getString(aName, aDefault);
		}
		
		sPropertyAdded = true;
		Preferences.getInstance().putString(aName, aDefault);
		return aDefault;
	}
	
	public static void saveIfUpdated()
	{
		if(sPropertyAdded)
		{
			Preferences.getInstance().save();
			System.out.println("-------------------------------------------");
			System.out.println("Config file updated, saving it");
			System.out.println("-------------------------------------------");
		}
	}

}
