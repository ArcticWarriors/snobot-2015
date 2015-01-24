package com.snobot;

import edu.wpi.first.wpilibj.Preferences;

public class ConfigurationNames {
	
	private static boolean sPropertyAdded = false;
	
	//Joysticks
	public static final String sOPERATOR_JOYSTICK_PORT = "OperatorJoystickPort";
	public static final String sDRIVER_FLIGHTSTICK_1_PORT = "DriverFlightstick1Port";
	public static final String sDRIVER_FLIGHTSTICK_2_PORT = "DriverFlightstick2Port";
	
<<<<<<< HEAD
=======
	public static final String sJoystickMode_Xbox = "Xbox mode";
	public static final String sJoystickMode_flightstick = "Flightstick Mode";
	public static final String sJoystickMode = "sJoystickMode";
	
	//Gem Pad X or Xbox Controller 
		public static final int sXbox_Left_X_Axis = 0;
		public static final int sXbox_Left_Y_Axis = 1;
		public static final int sXbox_Left_Trigger = 2;
		public static final int sXbox_Right_Trigger = 3;
		public static final int sXbox_Right_X_Axis = 4;
		public static final int sXbox_Right_Y_Axis = 5;
		
		public static final int sXbox_Button_A = 1;
		public static final int sXbox_Button_B = 2;
		public static final int sXbox_Button_X = 3;
		public static final int sXbox_Button_Y = 4;
		public static final int sXbox_Button_Left_Bumper = 5;
		public static final int sXbox_Button_Right_Bumper = 6;
		public static final int sXbox_Button_Left_Joystick_Press = 9;
		public static final int sXbox_Button_Right_Joystick_Press = 10;
		
	//Flightsticks Control
		
		public static final int sFlightsticks_Y_Axis = 1;
		public static final int sFlightsticks_X_Axis = 0;
		public static final int sFlightsticks_Button_4 = 4;
		public static final int sFlightsticks_Button_5 = 5;
		
>>>>>>> 132caa961a8c577ee3fc92269ba9bc058750c8e3
	//Motors
	public static final String sDRIVE_MOTOR_LEFT_1 = "LeftDriveMotor1";
	public static final String sDRIVE_MOTOR_LEFT_2 = "LeftDriveMotor2";
	public static final String sDRIVE_MOTOR_RIGHT_1 = "RightDriveMotor1";
	public static final String sDRIVE_MOTOR_RIGHT_2 = "RightDriveMotor2";
	public static final String sSTACKER_DEFAULT_SPEED = "StackerDefaultSpeed";
	
	//Solenoid

	//Digital Inputs
<<<<<<< HEAD
	public static final String sSTACKER_UPPER_LIMIT_SWITCH_PORT_1 = "UpperStackerLimit";
	public static final String sSTACKER_LOWER_LIMIT_SWITCH_PORT_1 = "LowerStackerLimit";

	//Analog Inputs
	public static final int sOperator_Left_Axis = 1;

=======
	
	//Analog Inputs
	
	
	
>>>>>>> 132caa961a8c577ee3fc92269ba9bc058750c8e3
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
