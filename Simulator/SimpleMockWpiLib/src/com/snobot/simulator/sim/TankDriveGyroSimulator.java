package com.snobot.simulator.sim;

import com.snobot.simulator.AnalogWrapper;
import com.snobot.simulator.EncoderWrapper;

public class TankDriveGyroSimulator implements ISimulatorUpdater {


	private EncoderWrapper mLeftEncoder;
	private EncoderWrapper mRightEncoder;
	private AnalogWrapper mGyroPort;
	
	private double mAngle; //degrees
	private boolean mIsSetup;
	
	public TankDriveGyroSimulator(EncoderWrapper aLeftEncoder, EncoderWrapper aRightEncoder, AnalogWrapper aGyroPort)
	{
		mLeftEncoder = aLeftEncoder;
		mRightEncoder = aRightEncoder;
		mGyroPort = aGyroPort;
		
		mIsSetup = mLeftEncoder != null && mRightEncoder != null && mGyroPort != null;

		if(!mIsSetup)
		{
			System.err.println("Can't simulate gyro, some inputs are null");
		}
	}

	@Override
	public void update() {
		
		if(mIsSetup)
		{

	        double rightDist = mRightEncoder.getDistance() / 4.0;
	        double leftDist  = mLeftEncoder.getDistance() / 4.0;
	        
	        mAngle = (double)(leftDist - rightDist)/(double)(3.14159*22.0/12.0)*(180.0);
	        
	        mGyroPort.setAccumulator(mAngle); //multiply by volts per degree second
//	        System.out.println("SIMULATOR : angle=" + mAngle + ", right=" + rightDist + ", left=" + leftDist);
		}
	}
}
