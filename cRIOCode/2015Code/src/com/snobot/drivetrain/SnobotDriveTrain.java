package com.snobot.drivetrain;

import com.snobot.SmartDashboardNames;
import com.snobot.joystick.IDriverJoystick;
import com.snobot.joystick.IDriverJoystick.DriveMode;
import com.snobot.logger.Logger;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Sets up specific snobot drive train
 * 
 * @author Ayush/Ammar
 *
 */
public class SnobotDriveTrain implements IDriveTrain
{

    private SpeedController mSpeedControllerLeft;
    private SpeedController mSpeedControllerRight;
    private IDriverJoystick mDriverJoystick;
    private RobotDrive mRobotDrive;

    private Encoder mEncoderLeft;
    private Encoder mEncoderRight;

    private Logger mLogger;
    private UnitOfMeasure mDefaultMeasure;
    private double mDistanceLeftTrack;
    private double mDistanceRightTrack;

    
    
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
    public SnobotDriveTrain(SpeedController aSpeedControllerLeft, SpeedController aSpeedControllerRight, IDriverJoystick aDriverJoystick,
             Encoder aEncoderLeft, Encoder aEncoderRight, Logger aLogger)
    {
        mSpeedControllerLeft = aSpeedControllerLeft;
        mSpeedControllerRight = aSpeedControllerRight;
        mDriverJoystick = aDriverJoystick;
        mRobotDrive = new RobotDrive(mSpeedControllerLeft, mSpeedControllerRight);
        mDefaultMeasure = UnitOfMeasure.Feet;
        mEncoderLeft = aEncoderLeft;
        mEncoderRight = aEncoderRight;
        mLogger=aLogger;
        
        mRobotDrive.setSafetyEnabled(false); //TODO - PJ - probably not the safest thing for competition....
    }

    
    public void init()
    {
        mLogger.addHeader("Left Drive Speed");
        mLogger.addHeader("Right Drive Speed");
    }

    
    public void update()
    {
        mDistanceLeftTrack = mEncoderLeft.getDistance();
        mDistanceRightTrack = mEncoderRight.getDistance(); 
        mSpeedControllerLeft.get();
        mSpeedControllerRight.get();
        
    }

    
    public void control()
    {
        
        if (IDriverJoystick.DriveMode.Arcade == mDriverJoystick.getDriveMode())
        {
            mRobotDrive.arcadeDrive(mDriverJoystick.getSpeed(), mDriverJoystick.getRotate());
        }
        else if (IDriverJoystick.DriveMode.Tank == mDriverJoystick.getDriveMode())
        {
            mRobotDrive.tankDrive(mDriverJoystick.getLeftY(), mDriverJoystick.getRightY(), true);
        }

    }

    
    public void rereadPreferences()
    {
    }

    
    public void updateSmartDashboard()
    {
        SmartDashboard.putNumber(SmartDashboardNames.sLEFT_DRIVE_SPEED, mSpeedControllerLeft.get());
        SmartDashboard.putNumber(SmartDashboardNames.sRIGHT_DRIVE_SPEED, mSpeedControllerRight.get());
        SmartDashboard.putNumber(SmartDashboardNames.sSNOBOT_DISTANCE_LEFT, mDistanceLeftTrack);
        SmartDashboard.putNumber(SmartDashboardNames.sSNOBOT_DISTANCE_RIGHT, mDistanceRightTrack);
    }

    
    public void updateLog()
    {
        mLogger.updateLogger(mSpeedControllerLeft.get());
        mLogger.updateLogger(mSpeedControllerRight.get());
    }

    
    public void stop()
    {
        this.setMotorSpeed(0, 0);

    }

    
    public void setMotorSpeed(double aLeft, double aRight)
    {
        mRobotDrive.setLeftRightMotorOutputs(aLeft, aRight);
    }

    
    public double calculateDistanceRight()
    {
        return mDistanceRightTrack;
    }

    
    public double calculateDistanceLeft()
    {
        return mDistanceLeftTrack;
    }

    
    public void setDefaultMeasure(UnitOfMeasure aMeasure)
    {
        mDefaultMeasure = aMeasure;

    }
    
    public void resetEncoders()
    {
        this.mEncoderLeft.reset();
        this.mEncoderRight.reset();
    }
}
