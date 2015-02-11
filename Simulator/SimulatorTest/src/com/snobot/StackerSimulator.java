package com.snobot;

import com.snobot.simulator.AnalogWrapper;
import com.snobot.simulator.DigitalSourceWrapper;
import com.snobot.simulator.SpeedControllerWrapper;
import com.snobot.simulator.sim.ISimulatorUpdater;
import com.snobot.simulator.sim.LinearPotCalculator;
import com.snobot.simulator.sim.PotWrapper;

public class StackerSimulator implements ISimulatorUpdater
{

    private DigitalSourceWrapper mUpperStackerLimit;
    private DigitalSourceWrapper mLowerStackerLimit;
    private PotWrapper mPotWrapper;
    private LinearPotCalculator mPotCalculator;
    private boolean mSetup;
	
    public StackerSimulator(
            SpeedControllerWrapper aStackerMotor,
            AnalogWrapper aStackerEncoder,
			DigitalSourceWrapper aUpperStackerLimit,
            DigitalSourceWrapper aLowerStackerLimit,
            double aMinPotVoltage,
            double aVoltsPerUnit)
			
	{		
        mPotWrapper = new PotWrapper(aStackerEncoder, aMinPotVoltage, aVoltsPerUnit);
        mPotCalculator = new LinearPotCalculator(aStackerMotor, mPotWrapper);
		mLowerStackerLimit = aLowerStackerLimit;
		mUpperStackerLimit = aUpperStackerLimit;
			
        mSetup = mLowerStackerLimit != null && mUpperStackerLimit != null && mPotCalculator != null;

		if(!mSetup)
		{
			System.err.println("Can't simulate the stacker, null param given");
			return;
		}
	}
	
	@Override
	public void update()
    {
        mPotCalculator.update();

        double distance = mPotWrapper.getDistance();
		
		if (distance <= 0) 
		{
			mLowerStackerLimit.set(false);
		}
		else
		{
			mLowerStackerLimit.set(true);
		}
		
		if (distance >= 20) 
		{	
			mUpperStackerLimit.set(false);
		}
		else 
		{
			mUpperStackerLimit.set(true);
		}
	}

}
