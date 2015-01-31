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
    private DriveMode mDriveMode;

    private Encoder mEncoderLeft;
    private Encoder mEncoderRight;

    private Logger mLogger;
    private UnitOfMeasure mDefaultMeasure;

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
            DriveMode aDriveMode, Encoder aEncoderLeft, Encoder aEncoderRight)
    {
        mSpeedControllerLeft = aSpeedControllerLeft;
        mSpeedControllerRight = aSpeedControllerRight;
        mDriverJoystick = aDriverJoystick;
        mRobotDrive = new RobotDrive(mSpeedControllerLeft, mSpeedControllerRight);
        mDriveMode = aDriveMode;
        mDefaultMeasure = UnitOfMeasure.Feet;
        mEncoderLeft = aEncoderLeft;
        mEncoderRight = aEncoderRight;
        
        mRobotDrive.setSafetyEnabled(false); //TODO - PJ - probably not the safest thing for compitiion....
    }

    @Override
    public void init()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void update()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void control()
    {
        // TODO Add switch between the two drives

        if (IDriverJoystick.DriveMode.Arcade == mDriverJoystick.getDriveMode())
        {
            mRobotDrive.arcadeDrive(mDriverJoystick.getSpeed(), mDriverJoystick.getRotate());
        }
        else if (IDriverJoystick.DriveMode.Tank == mDriverJoystick.getDriveMode())
        {
            mRobotDrive.tankDrive(mDriverJoystick.getLeftY(), mDriverJoystick.getRightY(), true);
        }

    }

    @Override
    public void rereadPreferences()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateSmartDashboard()
    {
        SmartDashboard.putNumber(SmartDashboardNames.sLEFT_DRIVE_SPEED, mSpeedControllerLeft.get());
        SmartDashboard.putNumber(SmartDashboardNames.sRIGHT_DRIVE_SPEED, mSpeedControllerRight.get());
        SmartDashboard.putNumber("Left Distance", this.calculateDistanceLeft());
        SmartDashboard.putNumber("Right Distance", this.calculateDistanceRight());
    }

    @Override
    public void updateLog()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void stop()
    {
        this.setMotorSpeed(0, 0);

    }

    @Override
    public void setMotorSpeed(double aLeft, double aRight)
    {
        mRobotDrive.setLeftRightMotorOutputs(aLeft, aRight);

    }

    @Override
    public double calculateDistanceRight()
    {
        return -mEncoderRight.getDistance();
    }

    @Override
    public double calculateDistanceLeft()
    {
        return mEncoderLeft.getDistance();
    }

    @Override
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
