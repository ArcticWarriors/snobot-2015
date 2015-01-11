package com.snobot.simulator.sim;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;

public class LinearEncoderCalculator implements ISimulatorUpdater
{

	private SpeedController mSpeedController;
	private Encoder mEncoder;
	private double mKp;
	

	public LinearEncoderCalculator(SpeedController aSpeedController, Encoder aEncoder)
	{
		this(aSpeedController, aEncoder, 1.0);
	}
	public LinearEncoderCalculator(SpeedController aSpeedController, Encoder aEncoder, double aKp)
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
