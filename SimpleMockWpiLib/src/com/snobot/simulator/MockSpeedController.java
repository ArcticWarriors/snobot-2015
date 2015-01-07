package com.snobot.simulator;

import edu.wpi.first.wpilibj.SensorBase;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.util.AllocationException;

public class MockSpeedController extends SensorBase implements SpeedController {

	private double value;
	
	public MockSpeedController(int aPort)
	{

		SensorBase.checkPWMChannel(aPort);
		if(!SensorActuatorRegistry.get().register(this, aPort))
		{
			throw new AllocationException("PWM port " + aPort
					+ " is already allocated");
		}
	}
	
	@Override
	public double get() {
		return value;
	}

	@Override
	public void set(double speed, byte syncGroup) {
		set(speed);
	}

	@Override
	public void set(double speed) {
		value = speed;
	}

	@Override
	public void disable() {
	}

}
