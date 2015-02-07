package com.snobot;

import com.snobot.simulator.DigitalSourceWrapper;
import com.snobot.simulator.EncoderWrapper;
import com.snobot.simulator.SpeedControllerWrapper;
import com.snobot.simulator.sim.LinearEncoderCalculator;

public class StackerSimulator extends LinearEncoderCalculator {

	DigitalSourceWrapper mUpperStackerLimit;
	DigitalSourceWrapper mLowerStackerLimit;
	
	public StackerSimulator( SpeedControllerWrapper aStackerMotor,
			EncoderWrapper aStackerEncoder, 
			DigitalSourceWrapper aUpperStackerLimit,
			DigitalSourceWrapper aLowerStackerLimit) 
			
	{		
		super( aStackerMotor, aStackerEncoder);
		mLowerStackerLimit = aLowerStackerLimit;
		mUpperStackerLimit = aUpperStackerLimit;
			
		mSetup = mSetup && mLowerStackerLimit != null && mUpperStackerLimit != null; 

		if(!mSetup)
		{
			System.err.println("Can't simulate the stacker, null param given");
			return;
		}
	}
	
	@Override
	public void update()
	{
		super.update();
		
		double distance = mEncoder.getDecodedDistance();
		
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
