package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author PJ
 */
public class MotorMode_Button implements ControlMode
{
    protected final SpeedController mController;
    protected final String mName;
    protected final Joystick mJoystick;
    protected final int mUpButton;
    protected final int mDownButton;
    
    public MotorMode_Button(SpeedController aController, String aName, 
            Joystick aJoystick, int aUpButton, int aDownButton)
    {
        mController = aController;
        mName = aName;
        mJoystick = aJoystick;
        mUpButton = aUpButton;
        mDownButton = aDownButton;
    }

    public void update()
    {
        double speed = mController.get();
        if(mJoystick.getRawButton(mUpButton))
        {
            speed += .05;
        }
        else if(mJoystick.getRawButton(mDownButton))
        {
            speed -= .05;
        }
        
        speed = Utilities.LimitMotor(speed);
        
        mController.set(speed);
        
        SmartDashboard.putNumber(mName + " speed", mController.get());
    }
    
}
