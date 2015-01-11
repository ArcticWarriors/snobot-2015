package com.snobot.simulator.sim;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;

public class LinearEncoderCalculator {

	private SpeedController mSpeedController;
	private Encoder mEncoder;
	
	
	public LinearEncoderCalculator(SpeedController aSpeedController, Encoder aEncoder)
	{
		mSpeedController = aSpeedController;
		mEncoder = aEncoder;
	}
	
	public void update()
	{
		double kP = .04;
		
		double distance_travelled = mSpeedController.get() * kP;
		
		mEncoder.__addDistanceDelta(distance_travelled);
	}
}
