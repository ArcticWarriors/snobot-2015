package com.snobot.simulator;

public class SpeedControllerWrapper {

	private double mSpeed;
	
	public SpeedControllerWrapper()
	{
		mSpeed = 0;
	}

	public double get() {
		return mSpeed;
	}

	public void set(double speed) {
		mSpeed = speed;
	}
}
