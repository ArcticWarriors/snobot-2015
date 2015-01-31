package com.snobot.joystick;

import com.snobot.XboxButtonMap;
import com.snobot.logger.Logger;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

/**
 * Implements Driver Joy stick Interface Sets up Snobot xBox Joy stick for
 * Driver Ayush/Ammar
 */
public class SnobotXBoxDriverJoystick implements IDriverJoystick
{

    private Joystick mXBoxStick;
    private DriveMode mDriveMode;
    private Logger mLogger;

    /**
     * Constructor for xBox Joy stick
     * 
     * @param aXBoxStick
     *            Argument for xBox Stick
     */
    public SnobotXBoxDriverJoystick(Joystick aXBoxStick, Logger aLogger, DriveMode aDriveMode)
    {
        System.out.println("Creating xbox joystick");
        mXBoxStick = aXBoxStick;
        mDriveMode = aDriveMode;
        mLogger = aLogger;
    }

    @Override
    public void init()
    {
        mLogger.addHeader("Tank Mode: Left Y Axis");
        mLogger.addHeader("Tank Mode: Right Y Axis");
        mLogger.addHeader("Arcade Mode: Speed");
        mLogger.addHeader("Arcade Mode: Right X Axis");
        mDriveMode = DriveMode.Tank;
    }

    @Override
    public void update()
    {
        if (mXBoxStick.getRawButton(XboxButtonMap.A_BUTTON))
        {
            mDriveMode = DriveMode.Tank;
        }
        else if (mXBoxStick.getRawButton(XboxButtonMap.B_BUTTON))
        {
            mDriveMode = DriveMode.Arcade;
        }
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
        // TODO Update Drive mode
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
        return mXBoxStick.getRawAxis(XboxButtonMap.LEFT_Y_AXIS);
    }

    @Override
    public double getRightY()
    {
        return mXBoxStick.getRawAxis(XboxButtonMap.RIGHT_Y_AXIS);
    }

    @Override
    public double getSpeed()
    {
        return mXBoxStick.getRawAxis(XboxButtonMap.LEFT_Y_AXIS);
    }

    @Override
    public double getRotate()
    {
        return mXBoxStick.getRawAxis(XboxButtonMap.RIGHT_X_AXIS);
    }

    @Override
    public DriveMode getDriveMode()
    {
        return mDriveMode;
    }
}
