package com.snobot.simulator.sim;

import com.snobot.simulator.EncoderWrapper;
import com.snobot.simulator.SpeedControllerWrapper;

public class LinearEncoderCalculator implements ISimulatorUpdater
{

	private SpeedControllerWrapper mSpeedController;
	private EncoderWrapper mEncoder;
	private double mKp;
	

	public LinearEncoderCalculator(SpeedControllerWrapper aSpeedController, EncoderWrapper aEncoder)
	{
		this(aSpeedController, aEncoder, 1.0);
	}
	public LinearEncoderCalculator(SpeedControllerWrapper aSpeedController, EncoderWrapper aEncoder, double aKp)
	{
		mSpeedController = aSpeedController;
		mEncoder = aEncoder;
		mKp = aKp;
		
		if(mSpeedController == null || mEncoder == null)
		{
			System.err.println("Warning, you have given a null paramter, simulation is not possible");
		}
	}

	public void setSimulatorParams(double aKp) {
		mKp = aKp;
	}
	
	public void update()
	{
		if(mEncoder != null && mSpeedController != null)
		{
			double distance_travelled = mSpeedController.get() * mKp;
			mEncoder.addDistanceDelta(distance_travelled);
		}
		
	}
}
