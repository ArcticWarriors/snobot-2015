package com.snobot.simulator;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.communication.HALControlWord;
import edu.wpi.first.wpilibj.hal.HALUtil;

public class RobotStateSingleton {

    public interface LoopListener
    {
    	public void looped();
    }
    
	private static RobotStateSingleton sInstance = new RobotStateSingleton();

	private boolean enabled = false;
	private boolean autonomous = false;
	private boolean test = false;

	private ArrayList<LoopListener> mListeners = new ArrayList<>();
	
	private RobotStateSingleton()
	{
		
	}
	public static RobotStateSingleton get()
	{
		return sInstance;
	}
	
    public void addLoopListener(LoopListener aListener)
    {
    	mListeners.add(aListener);
    }
    
    public void updateLoopListeners()
    {
		for(LoopListener listener : mListeners)
		{
			listener.looped();
		}
    }

	public float getMatchTime()
    {
		if(enabled)
		{
			return HALUtil.getFPGATime(null) * 1e-6f;
		}
		return 0;

    }

	public void setDisabled(boolean aDisabled) {
		enabled = !aDisabled;
	}
	public void setAutonomous(boolean aAutonomous) {
		autonomous = aAutonomous;
	}
	public void setTest(boolean aTest) {
		test = aTest;
	}

//	public void reset()
//	{
//		enabled = true;
//		autonomous = false;
//		test = false;
//	}

	public HALControlWord getControlWord() {
		return new HALControlWord(
				enabled,
				autonomous,
				test,
				false,
				true,
				true
		);
	}
}
