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
			DigitalSourceWrapper mLowerStackerLimit) 
			
	{		
		super( aStackerMotor, aStackerEncoder);
			mStackerMotor = aStackerMotor;
			mStackerEncoder = aStackerEncoder;
	}
	
	@Override
	public void update()
	{
		super.update();

		if (mStackerEncoder.getDistance() >= 0 && mStackerEncoder.getDistance() <= 2){
			mLowerStackerLimit.set(true);
		}
		else if (mStackerEncoder.getDistance() == 20){
			mUpperStackerLimit.set(true);
		}
		else {
			mUpperStackerLimit.set(false);
			mLowerStackerLimit.set(false);
		}
	}

}
