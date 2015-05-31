package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author PJ
 */
public class SolenoidMode_Momentary 
{
    private final Solenoid mSolenoid;
    private final Joystick mJoystick;
    private final int mButton;
    private final String mName;
    
    public SolenoidMode_Momentary(Solenoid aSolenoid, String aName, Joystick aJoystick, int aButton)
    {
        mSolenoid = aSolenoid;
        mJoystick = aJoystick;
        mButton = aButton;
        mName = aName;
    }
    
    public void update()
    {
        mSolenoid.set(mJoystick.getRawButton(mButton));
        SmartDashboard.putBoolean(mName + " speed", mSolenoid.get());
    }

}
