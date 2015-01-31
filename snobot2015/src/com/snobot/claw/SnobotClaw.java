package com.snobot.claw;

import com.snobot.ConfigurationNames;
import com.snobot.SmartDashboardNames;
import com.snobot.logger.Logger;
import com.snobot.operatorjoystick.IOperatorJoystick;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Class for the snobot claw and implements the main interface for claw
 * 
 * @author Alec/Jeffrey
 *
 */
public class SnobotClaw implements IClaw
{

    private IOperatorJoystick mOperatorJoystick;
    private double mClawPressureA;
    private double mClawPressureB;
    private Logger mLogger;
    private AnalogInput mTransducer;
    private Solenoid mClawHandSolenoid;
    private Solenoid mClawArmSolenoid;
    

    private boolean mRumbleOn;

    private double mAirPressureRangeMin, mAirPressureRangeMax;

    /**
     * Constructs a SnobotClaw object
     * 
     * @param aJoystick
     *            Argument for operator Joystick
     *
     */
    public SnobotClaw(IOperatorJoystick aJoystick, Logger alogger, AnalogInput aTransducer, Solenoid aClawHandSolenoid, Solenoid aClawArmSolenoid)
    {
        mOperatorJoystick = aJoystick;
        mLogger = alogger;
        mTransducer = aTransducer;
        mClawHandSolenoid = aClawHandSolenoid;
        mClawArmSolenoid = aClawArmSolenoid;

    }

    @Override
    public void openClaw()
    {
        mClawHandSolenoid.set(ConfigurationNames.sCLAW_HAND_OPEN);

    }

    @Override
    public void closeClaw()
    {
        mClawHandSolenoid.set(ConfigurationNames.sCLAW_HAND_CLOSE);

    }

    @Override
    public void moveClawUp()
    {
        mClawArmSolenoid.set(ConfigurationNames.sCLAW_ARM_UP);

    }

    @Override
    public void moveClawDown()
    {
        mClawArmSolenoid.set(ConfigurationNames.sCLAW_ARM_DOWN);
    }

    @Override
    public void init()
    {
        // TODO Auto-generated method stub
        mLogger.addHeader("Claw Up/Down Pressure");
        mLogger.addHeader("Claw Open/Close Pressure");
        rereadPreferences();
    }

    @Override
    public void update()
    {
        // TODO Auto-generated method stub
        mClawPressureA = -1;
        mClawPressureB = -1;

        if (mClawPressureA < mAirPressureRangeMin)
        {
            mRumbleOn = true;
        }
        else
        {
            mRumbleOn = false;
        }
    }

    @Override
    public void control()
    {
        mOperatorJoystick.setRumble(mRumbleOn);
        
        if (mOperatorJoystick.getClawOpen())
        {
            openClaw();
        }
        else if (mOperatorJoystick.getClawClose())
        {
            closeClaw();
        }
        else
        {
            stop();
        }
        
        if (mOperatorJoystick.getClawUp())
        {
            moveClawUp();
        }
        else if (mOperatorJoystick.getClawDown())
        {
            moveClawDown();
        }
        else
        {
            stop();
        }
    }

    @Override
    public void rereadPreferences()
    {

        mAirPressureRangeMin = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sAir_Pressure_Range_Min, 50);
    }

    @Override
    public void updateSmartDashboard()
    {
        SmartDashboard.putNumber(SmartDashboardNames.sCLAW_AIR_PRESSURE, mClawPressureA);
        SmartDashboard.putNumber(SmartDashboardNames.sCLAW_AIR_PRESSURE, mClawPressureB);
    }

    @Override
    public void updateLog()
    {
        mLogger.updateLogger(mClawPressureA);

        mLogger.updateLogger(mClawPressureB);

    }

    @Override
    public void stop()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public double getAirPressureA()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getAirPressureB()
    {
        // TODO Auto-generated method stub
        return 0;
    }

}
