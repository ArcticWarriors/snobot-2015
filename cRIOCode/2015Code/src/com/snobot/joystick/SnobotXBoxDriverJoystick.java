package com.snobot.joystick;

import com.snobot.ConfigurationNames;
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
    private Logger mLogger;

    private double mTankLeftYAxis;
    private double mTankRightYAxis;
    private double mArcadeLeftSpeed;
    private double mArcadeRightRotation;
    
    private int mTankMode;
    private int mArcadeMode;
    /**
     * Constructor for xBox Joy stick
     * 
     * @param aXBoxStick
     *            Argument for xBox Stick
     */
    public SnobotXBoxDriverJoystick(Joystick aXBoxStick, Logger aLogger)
    {
        System.out.println("Creating xbox joystick");
        mXBoxStick = aXBoxStick;
        mLogger = aLogger;
    }

    
    public void init()
    {
        mLogger.addHeader("Tank Mode: Left Y Axis");
        mLogger.addHeader("Tank Mode: Right Y Axis");
        mLogger.addHeader("Arcade Mode: Speed (1 to -1)");
        mLogger.addHeader("Arcade Mode: Right X Axis");
        mLogger.addHeader("Drive Mode");
        
        rereadPreferences();
    }

    
    public void update()
    {
        
        mTankLeftYAxis = mXBoxStick.getRawAxis(XboxButtonMap.LEFT_Y_AXIS);
        mTankRightYAxis = mXBoxStick.getRawAxis(XboxButtonMap.RIGHT_Y_AXIS);
        mArcadeLeftSpeed = mXBoxStick.getRawAxis(XboxButtonMap.LEFT_Y_AXIS);
        mArcadeRightRotation = mXBoxStick.getRawAxis(XboxButtonMap.RIGHT_X_AXIS);
    }

    
    public void control()
    {
        // TODO Auto-generated method stub

    }

    
    public void rereadPreferences()
    {
        // TODO Auto-generated method stub

        mTankMode = ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sXBOX_BUTTON_TANK_MODE, XboxButtonMap.A_BUTTON);
        mArcadeMode = ConfigurationNames.getOrSetPropertyInt(ConfigurationNames.sXBOX_BUTTON_ARCADE_MODE, XboxButtonMap.B_BUTTON);
    }

    
    public void updateSmartDashboard()
    {
        // TODO Auto-generated method stub

    }

    
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
