package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author PJ
 */
public class SolenoidMode_Toggle 
{
    private final Solenoid mSolenoid;
    private final Joystick mJoystick;
    private final int mButton;
    private final String mName;
    
    private final ToggleButton toggleButton;
    
    public SolenoidMode_Toggle(Solenoid aSolenoid, String aName, Joystick aJoystick, int aButton)
    {
        mSolenoid = aSolenoid;
        mJoystick = aJoystick;
        mButton = aButton;
        mName = aName;
        
        toggleButton = new ToggleButton();
    }
    
    public void update()
    {
        boolean state = toggleButton.update(mJoystick.getRawButton(mButton));
        
        mSolenoid.set(state);
        SmartDashboard.putBoolean(mName + " speed", mSolenoid.get());
    }

}
