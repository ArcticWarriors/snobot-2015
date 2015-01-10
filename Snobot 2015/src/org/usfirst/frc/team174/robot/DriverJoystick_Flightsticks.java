package org.usfirst.frc.team174.robot;

import edu.wpi.first.wpilibj.Joystick;

public class DriverJoystick_Flightsticks implements DriverJoystick {

	
	public enum DriveMode
	{
		TwoStick, OneStick
	}
	
	
	private Joystick rightJoystick;
	private Joystick leftJoystick;
	private  DriveMode driveMode;
	
	public DriverJoystick_Flightsticks (Joystick arightJoystick, Joystick aleftJoystick){
		rightJoystick = arightJoystick;
		leftJoystick = aleftJoystick;
		
		driveMode = DriveMode.TwoStick;
		
	}
	
	/* (non-Javadoc)
	 * @see org.usfirst.frc.team174.robot.DriverJoystick#getRight()
	 */
	@Override
	public double getRight () {
		
		return rightJoystick.getY();
	}
	
	/* (non-Javadoc)
	 * @see org.usfirst.frc.team174.robot.DriverJoystick#getLeft()
	 */
	@Override
	public double getLeft (){
		
		return leftJoystick.getY();
		
	}

	/* (non-Javadoc)
	 * @see org.usfirst.frc.team174.robot.DriverJoystick#getSpeed()
	 */
	@Override
	public double getSpeed () {
		
		return rightJoystick.getY();
	}
	
	/* (non-Javadoc)
	 * @see org.usfirst.frc.team174.robot.DriverJoystick#getRotate()
	 */
	@Override
	public double getRotate (){
		
		return rightJoystick.getX();
		
	}
	
	/* (non-Javadoc)
	 * @see org.usfirst.frc.team174.robot.DriverJoystick#getmode()
	 */
	@Override
	public DriveMode getmode () {
		if (rightJoystick.getRawButton(0) == true)
    	{
			driveMode = DriveMode.TwoStick;
    	}
    	else if (rightJoystick.getRawButton(1) == true)
    	{
			driveMode = DriveMode.OneStick;
    	}
		
		return driveMode;
	}
}
