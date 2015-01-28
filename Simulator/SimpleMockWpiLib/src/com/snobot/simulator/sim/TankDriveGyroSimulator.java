package com.snobot.simulator.sim;

import com.snobot.simulator.AnalogWrapper;
import com.snobot.simulator.EncoderWrapper;

public class TankDriveGyroSimulator implements ISimulatorUpdater {


	private EncoderWrapper mLeftEncoder;
	private EncoderWrapper mRightEncoder;
	private AnalogWrapper mGyroPort;
	
	private double mAngle; //degrees
	
	public TankDriveGyroSimulator(EncoderWrapper aLeftEncoder, EncoderWrapper aRightEncoder, AnalogWrapper aGyroPort)
	{
		mLeftEncoder = aLeftEncoder;
		mRightEncoder = aRightEncoder;
		mGyroPort = aGyroPort;
	}

	@Override
	public void update() {
		
		if(mRightEncoder != null && mLeftEncoder != null && mGyroPort != null)
		{

	        double rightDist = mRightEncoder.getDistance() / 4.0;
	        double leftDist  = mLeftEncoder.getDistance() / 4.0;
	        
	        mAngle = (double)(leftDist - rightDist)/(double)(3.14159*22.0/12.0)*(180.0);
	        
	        mGyroPort.setAccumulator(mAngle); //multiply by volts per degree second
//	        System.out.println("SIMULATOR : angle=" + mAngle + ", right=" + rightDist + ", left=" + leftDist);
		}
		else
		{
			System.err.println("Can't simulate gyro, some inputs are null");
		}
	}
}
