package com.snobot;

import com.snobot.simulator.DigitalSourceWrapper;
import com.snobot.simulator.EncoderWrapper;
import com.snobot.simulator.SpeedControllerWrapper;
import com.snobot.simulator.sim.LinearEncoderCalculator;

public class StackerSImulator extends LinearEncoderCalculator {

	DigitalSourceWrapper mUpperStackerLimit;
	DigitalSourceWrapper mLowerStackerLimit;
	SpeedControllerWrapper mStackerMotor; 
	EncoderWrapper mStackerEncoder;
	
	public StackerSImulator( SpeedControllerWrapper aStackerMotor,
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
		
		if (mStackerEncoder.getDistance() <= 0) 
		{
			mLowerStackerLimit.set(true);
		}
		else
		{
			mLowerStackerLimit.set(false);
		}
		
		if (mStackerEncoder.getDistance() >= 20) 
		{	
			mUpperStackerLimit.set(true);
		}
		else 
		{
			mUpperStackerLimit.set(false);
		}
	}

}
