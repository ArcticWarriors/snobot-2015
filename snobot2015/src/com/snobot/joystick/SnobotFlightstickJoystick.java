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
        mLogger.addHeader("Arcade Mode: Speed (1 to -1)");
        mLogger.addHeader("Arcade Mode: Right X Axis");
        mDriveMode = DriveMode.Tank;
    }

    @Override
    public void update()
    {
        if (mRightFlightStick.getRawButton(ConfigurationNames.sFlightsticks_Button_4))
        {
            mDriveMode = DriveMode.Tank;
        }
        else if (mRightFlightStick.getRawButton(ConfigurationNames.sFlightsticks_Button_5))
        {
            mDriveMode = DriveMode.Arcade;
        }
        
        mTankLeftYAxis = mLeftFlightStick.getRawAxis(ConfigurationNames.sFlightsticks_Y_Axis);
        mTankRightYAxis = mRightFlightStick.getRawAxis(ConfigurationNames.sFlightsticks_Y_Axis);
        mArcadeLeftSpeed = mLeftFlightStick.getRawAxis(ConfigurationNames.sFlightsticks_Y_Axis);
        mArcadeRightRotation = mRightFlightStick.getRawAxis(ConfigurationNames.sFlightsticks_X_Axis);
    }

    @Override
    public void control()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void rereadPreferences()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateSmartDashboard()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateLog()
    {
        // Left Y Axis
        mLogger.updateLogger(getLeftY());

        // Right Y Axis
        mLogger.updateLogger(getRightY());

        // Speed
        mLogger.updateLogger(getSpeed());

        // Angle of the Joy stick (for arcade drive)
        mLogger.updateLogger(getRotate());
    }

    @Override
    public void stop()
    {
        // TODO Auto-generated method stub

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

}
