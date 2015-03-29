package com.snobot.joystick;

import com.snobot.ConfigurationNames;
import com.snobot.logger.Logger;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Implements Driver Joystick Interface Sets up Snobot Flight Stick for Driver
 * Ayush/Ammar
 *
 */
public class SnobotFlightstickJoystick implements IDriverJoystick
{

    private Joystick mLeftFlightStick;
    private Joystick mRightFlightStick;
    private DriveMode mDriveMode;
    private Logger mLogger;
    
    private double mTankLeftYAxis;
    private double mTankRightYAxis;
    private double mArcadeLeftSpeed;
    private double mArcadeRightRotation;
    /**
     * Constructor for Flight Stick
     * 
     * @param aLeftFlightStick
     *            Argument for Left Flight Stick
     * @param aRightFlightStick
     *            Argument for Right Flight Stick
     */
    public SnobotFlightstickJoystick(Joystick aLeftFlightStick, Joystick aRightFlightStick, Logger aLogger)
    {
        System.out.println("Creating flightstick joystick");
        mLeftFlightStick = aLeftFlightStick;
        mRightFlightStick = aRightFlightStick;
        mLogger = aLogger;
    }

    @Override
    public void init()
    {
        mLogger.addHeader("Tank Mode: Left Y Axis");
        mLogger.addHeader("Tank Mode: Right Y Axis");
        // mLogger.addHeader("Arcade Mode: Speed (1 to -1)");
        // mLogger.addHeader("Arcade Mode: Right X Axis");
        mLogger.addHeader("Drive Mode");
        mDriveMode = DriveMode.Tank;
    }

    @Override
    public void update()
    {
        if (mRightFlightStick.getRawButton(ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sFLIGHTSTICKS_BUTTON_SWITCH_TO_TANK, 4)))
        {
            mDriveMode = DriveMode.Tank;
        }
        // else if
        // (mRightFlightStick.getRawButton(ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sFLIGHTSTICKS_BUTTON_SWITCH_TO_ARCADE,
        // 5)))
        // {
        // mDriveMode = DriveMode.Arcade;
        // }
        
        mTankLeftYAxis = mLeftFlightStick.getRawAxis(ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sFLIGHTSTICKS_Y_AXIS, 1));
        mTankRightYAxis = mRightFlightStick.getRawAxis(ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sFLIGHTSTICKS_Y_AXIS, 1));
        // mArcadeLeftSpeed =
        // mLeftFlightStick.getRawAxis(ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sFLIGHTSTICKS_Y_AXIS,
        // 1));
        // mArcadeRightRotation =
        // mRightFlightStick.getRawAxis(ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sFLIGHTSTICKS_X_AXIS,
        // 0));
    }

    @Override
    public void control()
    {
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
        // Left Y Axis
        mLogger.updateLogger(mTankLeftYAxis);

        // Right Y Axis
        mLogger.updateLogger(mTankRightYAxis);

        // Speed
        // mLogger.updateLogger(mArcadeLeftSpeed);

        // Angle of the Joy stick (for arcade drive)
        // mLogger.updateLogger(mArcadeRightRotation);
        
        mLogger.updateLogger(getDriveMode().toString());
    }

    @Override
    public void stop()
    {
    }

    @Override
    public double getLeftY()
    {
        return mTankLeftYAxis;
    }

    @Override
    public double getRightY()
    {
        return mTankRightYAxis;
    }

    @Override
    public double getSpeed()
    {
        return mArcadeLeftSpeed;
    }

    @Override
    public double getRotate()
    {
        return mArcadeRightRotation;
    }

    @Override
    public DriveMode getDriveMode()
    {
        return mDriveMode;
    }

    @Override
    public boolean getDriveForward()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean getDriveBackward()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isSmarturDriving()
    {
        // TODO Auto-generated method stub
        return false;
    }

}
