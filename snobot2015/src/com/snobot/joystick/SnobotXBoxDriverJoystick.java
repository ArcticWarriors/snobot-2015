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

    private double mTankLeftYAxis;
    private double mTankRightYAxis;
    private double mArcadeLeftSpeed;
    private double mArcadeRightRotation;
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
        mLogger.addHeader("Arcade Mode: Speed (1 to -1)");
        mLogger.addHeader("Arcade Mode: Right X Axis");
        mLogger.addHeader("Drive Mode");
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
        
        mTankLeftYAxis = mXBoxStick.getRawAxis(XboxButtonMap.LEFT_Y_AXIS);
        mTankRightYAxis = mXBoxStick.getRawAxis(XboxButtonMap.RIGHT_Y_AXIS);
        mArcadeLeftSpeed = mXBoxStick.getRawAxis(XboxButtonMap.LEFT_Y_AXIS);
        mArcadeRightRotation = mXBoxStick.getRawAxis(XboxButtonMap.RIGHT_X_AXIS);
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
        mLogger.updateLogger(mTankLeftYAxis);

        // Right Y Axis
        mLogger.updateLogger(mTankRightYAxis);

        // Speed
        mLogger.updateLogger(mArcadeLeftSpeed);

        // Angle of the Joy stick (for arcade drive)
        mLogger.updateLogger(mArcadeRightRotation);
        
        
        mLogger.updateLogger(getDriveMode().toString());
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
