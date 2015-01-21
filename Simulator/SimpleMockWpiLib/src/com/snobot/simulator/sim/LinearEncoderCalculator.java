package com.snobot.simulator.sim;

import com.snobot.simulator.EncoderWrapper;
import com.snobot.simulator.SpeedControllerWrapper;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;

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
	}

	public void setSimulatorParams(double aKp) {
		mKp = aKp;
	}
	
	public void update()
	{
		double distance_travelled = mSpeedController.get() * mKp;
		
		mEncoder.__addDistanceDelta(distance_travelled);
	}
}
