package com.snobot.drivetrain;

import com.snobot.xlib.ISubsystem;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Sets up specific snobot drive train
 * 
 * @author Ayush/Ammar
 *
 */
public class SnobotDriveTrain implements ISubsystem
{

    private SpeedController mSpeedControllerLeft;
    private SpeedController mSpeedControllerRight;
    private RobotDrive mRobotDrive;
    private Joystick mJoystick;
    
    /**
     * Takes 2 speed controllers and joy stick arguments
     * 
     * @param aSpeedControllerLeft
     *            Argument for left Speed Controller
     * @param aSpeedControllerRight
     *            Argument for right Speed Controller
     * @param aDriverJoystick
     *            Argument Driver Joy stick
     */
    public SnobotDriveTrain(
    		SpeedController aSpeedControllerLeft, 
    		SpeedController aSpeedControllerRight,
    		Joystick aDriverJoystick)
    {
        mSpeedControllerLeft = aSpeedControllerLeft;
        mSpeedControllerRight = aSpeedControllerRight;
        mRobotDrive = new RobotDrive(mSpeedControllerLeft, mSpeedControllerRight);
        mJoystick = aDriverJoystick;

        mRobotDrive.setSafetyEnabled(false); // TODO - PJ - probably not the
                                             // safest thing for competition....
       
    }

    @Override
    public void init()
    {
        rereadPreferences();
    }

    @Override
    public void update()
    {

    }

    @Override
    public void control()
    {
    	mRobotDrive.setLeftRightMotorOutputs(mJoystick.getRawAxis(1), mJoystick.getRawAxis(2));
    }

    @Override
    public void rereadPreferences()
    {
        
    }

    @Override
    public void updateSmartDashboard()
    {
        
    }

    @Override
    public void updateLog()
    {
    }

    @Override
    public void stop()
    {
    }
}
