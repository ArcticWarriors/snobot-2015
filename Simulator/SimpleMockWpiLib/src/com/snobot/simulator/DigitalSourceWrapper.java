package com.snobot.simulator;

public class DigitalSourceWrapper 
{

	private boolean mState;
	
	public DigitalSourceWrapper()
	{
		mState = false;
	}
	
	public boolean get()
	{
		return mState;
	}
	
	public void set(boolean aState)
	{
		mState = aState;
	}
}
