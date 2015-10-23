
package com.snobot;

import com.snobot.drivetrain.SnobotDriveTrain;
import com.snobot.shooter.SnobotShooter;
import com.snobot.xlib.ASnobot;
import com.snobot.xlib.PropertyManager;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Snobot2012 extends ASnobot
{

	//Shooter
	private SpeedController mShooterMotor;
	private Solenoid mShooterSolenoid; 
	private Joystick mShooterJoystick;
	private SnobotShooter mShooter;
	
	//Drive Train
	private SpeedController mLeftMotor;
	private SpeedController mRightMotor;
	private Joystick mDriveJoystick;
	private SnobotDriveTrain mDriveTrain;
	
    /**
     * This function is run when the robot is first started up and should be used for any initialization code.
     */
    @Override
    public void robotInit()
    {
    	//Shooter
    	mShooterMotor = new Talon(0); //TODO Port Mapping
    	mShooterSolenoid = new Solenoid(0); //TODO Port Mapping
    	mShooterJoystick = new Joystick(0); //TODO Port mapping
    	mShooter = new SnobotShooter(mShooterMotor, mShooterSolenoid, mShooterJoystick);
    	
    	//Drive Train
    	mLeftMotor = new Talon (1); //TODO Port Mapping
    	mRightMotor = new Talon (2); //TODO Port Mapping
    	mDriveJoystick = new Joystick (1); //TODO Port Mapping
    	mDriveTrain = new SnobotDriveTrain(mLeftMotor, mRightMotor, mDriveJoystick);
    	
    	
    	mSubsystems.add(mDriveTrain);
    	mSubsystems.add(mShooter);
    	
        init();
        rereadPreferences();

        // Now all the preferences should be loaded, make sure they are all
        // saved
        PropertyManager.saveIfUpdated();
    }

    @Override
    public void updateLog()
    {
        // Nothing to do....
    }
    
}
