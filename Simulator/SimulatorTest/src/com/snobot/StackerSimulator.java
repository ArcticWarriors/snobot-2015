package com.snobot;

import com.snobot.simulator.DigitalSourceWrapper;
import com.snobot.simulator.SpeedControllerWrapper;
import com.snobot.simulator.sim.DistanceCalculator;
import com.snobot.simulator.sim.ISimulatorUpdater;
import com.snobot.simulator.sim.LinearEncoderCalculator;

public class StackerSimulator implements ISimulatorUpdater
{

    private DigitalSourceWrapper mUpperStackerLimit;
    private DigitalSourceWrapper mLowerStackerLimit;
    private DistanceCalculator mPotWrapper;
    private LinearEncoderCalculator mPotCalculator;
    private boolean mSetup;
	
    public StackerSimulator(
            SpeedControllerWrapper aStackerMotor,
			DigitalSourceWrapper aUpperStackerLimit,
            DigitalSourceWrapper aLowerStackerLimit,
            DistanceCalculator aPotWrapper)
			
	{		
        mPotWrapper = aPotWrapper;
        mPotCalculator = new LinearEncoderCalculator(aStackerMotor, mPotWrapper);
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

    public void setSimulatorParams(double aKp)
    {
        mPotCalculator.setSimulatorParams(aKp);
    }

}
