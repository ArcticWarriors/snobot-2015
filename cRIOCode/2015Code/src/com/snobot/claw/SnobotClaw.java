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
    private double mRobotAirPressure;
    private Logger mLogger;
    private Solenoid mClawHandSolenoid;
    private Solenoid mClawArmSolenoid;
    

    private boolean mRumbleOn;

    private double mAirPressureRangeMin;

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
        mClawHandSolenoid = aClawHandSolenoid;
        mClawArmSolenoid = aClawArmSolenoid;

    }

    
    public void openClaw()
    {
        mClawHandSolenoid.set(true);
    }

    
    public void closeClaw()
    {
        mClawHandSolenoid.set(false);

    }

    
    public void moveClawUp()
    {
        mClawArmSolenoid.set(true);

    }

    
    public void moveClawDown()
    {
        mClawArmSolenoid.set(false);
    }

    
    public void init()
    {
        mLogger.addHeader("Claw Up/Down Pressure");
        mLogger.addHeader("Claw Open/Close Pressure");
        rereadPreferences();
    }

    
    public void update()
    {
        mRobotAirPressure = -1;
        
        mRumbleOn = mRobotAirPressure < mAirPressureRangeMin;
    }

    
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

    
    public void rereadPreferences()
    {

        mAirPressureRangeMin = ConfigurationNames.getOrSetPropertyDouble(ConfigurationNames.sAIR_PRESSURE_RANGE_MIN, 50);
    }

    
    public void updateSmartDashboard()
    {
        SmartDashboard.putNumber(SmartDashboardNames.sCLAW_AIR_PRESSURE, mRobotAirPressure);
    }

    
    public void updateLog()
    {
        mLogger.updateLogger(mRobotAirPressure);

    }

    
    public void stop()
    {
        // TODO Auto-generated method stub

    }

    
    public double getRobotAirPressure()
    {
        // TODO Auto-generated method stub
        return 0;
    }

}
