package com.snobot.simulator.sim;

import com.snobot.simulator.AnalogWrapper;
import com.snobot.simulator.EncoderWrapper;

public class TankDriveGyroSimulator implements ISimulatorUpdater {


	private EncoderWrapper mLeftEncoder;
	private EncoderWrapper mRightEncoder;
	private AnalogWrapper mGyroPort;
	
	private double mAngle; //degrees
	private boolean mIsSetup;

    private boolean mIsLeftReversed;
    private boolean mIsRightReversed;

    private double mKP;
	
	public TankDriveGyroSimulator(EncoderWrapper aLeftEncoder, EncoderWrapper aRightEncoder, AnalogWrapper aGyroPort)
	{
		mLeftEncoder = aLeftEncoder;
		mRightEncoder = aRightEncoder;
		mGyroPort = aGyroPort;
		
		mIsSetup = mLeftEncoder != null && mRightEncoder != null && mGyroPort != null;

        mKP = 22.0 / 12.0;

		if(!mIsSetup)
		{
			System.err.println("Can't simulate gyro, some inputs are null");
		}
	}

    public void setIsReverse(boolean isLeftReversed, boolean isRightReversed)
    {
        mIsLeftReversed = isLeftReversed;
        mIsRightReversed = isRightReversed;
    }

	@Override
	public void update() {
		
		if(mIsSetup)
		{

	        double rightDist = mRightEncoder.getDecodedDistance();
	        double leftDist  = mLeftEncoder.getDecodedDistance();

            if (mIsLeftReversed == true)
            {
                leftDist *= -1;
            }
            if (mIsRightReversed == true)
            {
                rightDist *= -1;
            }

            mAngle = (double) (leftDist - rightDist) / (double) (3.14159 * mKP) * (180.0);
	        
	        mGyroPort.setAccumulator(mAngle); //multiply by volts per degree second
            // System.out.println("SIMULATOR : angle=" + mAngle + ", right=" + rightDist + ", left=" + leftDist);
		}
	}
}
