package com.snobot.rake;

import com.snobot.SmartDashboardNames;
import com.snobot.logger.Logger;
import com.snobot.operatorjoystick.IOperatorJoystick;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SnobotRake implements IRake
{

    private SpeedController mRakeMotor;
    private IOperatorJoystick mOperatorJoystick;
    private DigitalInput mLimitSwitch;
    private boolean mLimitSwitchPressed;
    private Logger mLogger;

    /**
     * Constructs a SnobotRake object
     * 
     * @param aRakeMotor
     * @param aOperatorJoystick
     * @param aLimitSwitch
     *            Argument of operator Rake Motor, Operator Joystick, Limit Switch
     */
    public SnobotRake(SpeedController aRakeMotor, IOperatorJoystick aOperatorJoystick, DigitalInput aLimitSwitch, Logger aLogger)
    {
        mRakeMotor = aRakeMotor;
        mOperatorJoystick = aOperatorJoystick;
        mLimitSwitch = aLimitSwitch;
        mLogger = aLogger;
    }

//    @Override
    public void init()
    {
        stop();
        mLogger.addHeader("LimitSwitchDepressed");
        mLogger.addHeader("RakeMotor");
        rereadPreferences();
    }

//    @Override
    public void update()
    {
        mLimitSwitchPressed = !mLimitSwitch.get();
    }

//    @Override
    public void control()
    {

        double speed = mOperatorJoystick.getMoveRake();

        if (mLimitSwitchPressed && speed > 0)
        {
            mRakeMotor.set(0);
            System.out.println("STOPPING RAKE");
        }
        else
        {
            mRakeMotor.set(speed);
        }
    }

//    @Override
    public void rereadPreferences()
    {
    }

//    @Override
    public void updateSmartDashboard()
    {

        SmartDashboard.putBoolean(SmartDashboardNames.sLIMIT_SWITCH_DEPRESSED, mLimitSwitchPressed);
        SmartDashboard.putNumber(SmartDashboardNames.sRAKE_MOTOR, mRakeMotor.get());

    }

//    @Override
    public void updateLog()
    {

        mLogger.updateLogger(mLimitSwitchPressed);
        mLogger.updateLogger(mRakeMotor.get());
    }

//    @Override
    public void stop()
    {

        mRakeMotor.set(0);
    }

//    @Override
    public void moveRakeOut()
    {
        mRakeMotor.set(-1);
    }

//    @Override
    public void moveRakeIn()
    {
        mRakeMotor.set(1);
    }

}
