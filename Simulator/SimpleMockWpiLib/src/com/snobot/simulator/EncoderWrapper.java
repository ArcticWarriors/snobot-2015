package com.snobot.simulator;

public class EncoderWrapper {
	
	private double distance_traveled = 0;

	public void addDistanceDelta(double aDistance) {
		distance_traveled += aDistance;
	}
	
	public double getDistance()
	{
		return distance_traveled;
	}

	public int getRaw() {
		return (int) (distance_traveled * 255.0);
	}

	public void reset() {
		distance_traveled = 0;
	}

}