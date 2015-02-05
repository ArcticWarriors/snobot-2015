package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author PJ
 */
public class MotorMode_Stick implements ControlMode
{
    protected final SpeedController mController;
    protected final String mName;
    protected final Joystick mJoystick;
    protected final int mAxis;
    
    public MotorMode_Stick(SpeedController aController, String aName, Joystick aJoystick, int aAxis)
    {
        mController = aController;
        mName = aName;
        mJoystick = aJoystick;
        mAxis = aAxis;
    }

    public void update()
    {
        mController.set(mJoystick.getRawAxis(mAxis));
    }

    public void updateSmartDashboard()
    {
        SmartDashboard.putNumber(mName + " speed", mController.get());
    }
    
}
