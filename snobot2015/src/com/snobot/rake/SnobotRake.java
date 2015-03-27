package com.snobot.rake;

import com.snobot.ConfigurationNames;
import com.snobot.SmartDashboardNames;
import com.snobot.logger.Logger;
import com.snobot.operatorjoystick.IOperatorJoystick;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SnobotRake implements IRake {

    private SpeedController mRakeMotor;
    private IOperatorJoystick mOperatorJoystick;
    private DigitalInput mLimitSwitch;
    private boolean mLimitSwitchPressed;
    private Logger mLogger;
    private double mRakeValueIn;
    private double mRakeValueOut;

    /**
     * Constructs a SnobotRake object
     * 
     * @param aRakeMotor
     * @param aOperatorJoystick
     * @param aLimitSwitch
     *            Argument of operator Rake Motor, Operator Joystick, Limit
     *            Switch
     */
    public SnobotRake(SpeedController aRakeMotor, IOperatorJoystick aOperatorJoystick, DigitalInput aLimitSwitch, Logger aLogger) {
        mRakeMotor = aRakeMotor;
        mOperatorJoystick = aOperatorJoystick;
        mLimitSwitch = aLimitSwitch;
        mLogger = aLogger;
    }

    @Override
    public void init() {
        stop();
        mLogger.addHeader("LimitSwitchDepressed");
        mLogger.addHeader("RakeMotor");
        rereadPreferences();
    }

    @Override
    public void update() {
        mLimitSwitchPressed = !mLimitSwitch.get();

        mRakeValueOut = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sRAKE_JOYSTICK_VALUE_UP, -.2);
        mRakeValueIn = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sRAKE_JOYSTICK_VALUE_DOWN, .2);


    }

    @Override
    public void control() {

        mRakeMotor.set(mOperatorJoystick.getMoveRake());

        // System.out.println("Speed : " + mMotorSpeed + ", limit = " +
        // mLimitSwitchPressed);
        // if (mMotorSpeed > mRakeValueOut)
        // {
        // moveRakeOut();
        // }
        // // If i want to move the rake in and the limit witch isn't pressed
        // else if (mMotorSpeed < mRakeValueIn && !mLimitSwitchPressed)
        // {
        // moveRakeIn();
        // }
        // else
        // {
        // stop();
        // }
    }

    @Override
    public void rereadPreferences() {
    }

    @Override
    public void updateSmartDashboard() {

        SmartDashboard.putBoolean(SmartDashboardNames.sLIMIT_SWITCH_DEPRESSED, mLimitSwitchPressed);
        SmartDashboard.putNumber(SmartDashboardNames.sRAKE_MOTOR, mRakeMotor.get());
        SmartDashboard.putNumber(SmartDashboardNames.sRAKE_JOYSTICK_VALUE_UP, mRakeValueOut);
        SmartDashboard.putNumber(SmartDashboardNames.sRAKE_JOYSTICK_VALUE_DOWN, mRakeValueIn);

    }

    @Override
    public void updateLog() {


        mLogger.updateLogger(mLimitSwitchPressed);
        mLogger.updateLogger(mRakeMotor.get());
    }

    @Override
    public void stop() {

        mRakeMotor.set(0);
    }

    @Override
    public void moveRakeOut() {
        mRakeMotor.set(-1);
    }

    @Override
    public void moveRakeIn() {
        mRakeMotor.set(1);
    }

}
