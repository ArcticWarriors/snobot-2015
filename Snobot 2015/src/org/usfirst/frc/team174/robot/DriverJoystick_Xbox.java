package org.usfirst.frc.team174.robot;

import org.usfirst.frc.team174.robot.DriverJoystick_Flightsticks.DriveMode;

import edu.wpi.first.wpilibj.Joystick;

public class DriverJoystick_Xbox implements DriverJoystick{
	
	private Joystick joystick;
	
	private DriveMode driveMode = DriveMode.TwoStick;
	
	public DriverJoystick_Xbox(Joystick aJoystick)
	{
		joystick = aJoystick;
	}

	@Override
	public double getRight() {
		return joystick.getRawAxis(5);
	}

	@Override
	public double getLeft() {
		return joystick.getRawAxis(1);
	}

	@Override
	public double getSpeed() {
		return joystick.getRawAxis(1);
	}

	@Override
	public double getRotate() {
		return joystick.getRawAxis(4);
	}

	@Override
	public DriveMode getmode() {
		if(joystick.getRawButton(3)){
			driveMode=DriverJoystick_Flightsticks.DriveMode.TwoStick;
		}
		else if(joystick.getRawButton(2)){
			driveMode=DriverJoystick_Flightsticks.DriveMode.OneStick;
		}
		return driveMode;
	}

}
