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

    
    public void init()
    {
        mLogger.addHeader("Tank Mode: Left Y Axis");
        mLogger.addHeader("Tank Mode: Right Y Axis");
        mLogger.addHeader("Arcade Mode: Speed (1 to -1)");
        mLogger.addHeader("Arcade Mode: Right X Axis");
    }

    
    public void update()
    {        
        mTankLeftYAxis = mLeftFlightStick.getRawAxis(ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sFlightsticks_Y_Axis, 1));
        mTankRightYAxis = mRightFlightStick.getRawAxis(ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sFlightsticks_Y_Axis, 1));
        mArcadeLeftSpeed = mLeftFlightStick.getRawAxis(ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sFlightsticks_Y_Axis, 1));
        mArcadeRightRotation = mRightFlightStick.getRawAxis(ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sFlightsticks_X_Axis, 0));
    }

    
    public void control()
    {
        // TODO Auto-generated method stub

    }

    
    public void rereadPreferences()
    {
        // TODO Auto-generated method stub

    }

    
    public void updateSmartDashboard()
    {
        // TODO Auto-generated method stub

    }

    
    public void updateLog()
    {
        //TODO Update Drive Mode
        // Left Y Axis
        mLogger.updateLogger(mTankLeftYAxis);

        // Right Y Axis
        mLogger.updateLogger(mTankRightYAxis);

        // Speed
        mLogger.updateLogger(mArcadeLeftSpeed);

        // Angle of the Joy stick (for arcade drive)
        mLogger.updateLogger(mArcadeRightRotation);
    }

    
    public void stop()
    {
        // TODO Auto-generated method stub

    }

    
    public double getLeftY()
    {
        return mTankLeftYAxis;
    }

    
    public double getRightY()
    {
        return mTankRightYAxis;
    }

    
    public double getSpeed()
    {
        return mArcadeLeftSpeed;
    }

    
    public double getRotate()
    {
        return mArcadeRightRotation;
    }

}
