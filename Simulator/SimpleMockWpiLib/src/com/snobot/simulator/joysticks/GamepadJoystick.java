package com.snobot.simulator.joysticks;

import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Component.Identifier;

public class GamepadJoystick implements IMockJoystick {

	private final Identifier[] mAxis;
	private final Identifier[] mButtons;
	private final short[] mAxisValues;
	private final short[] mPovValues;
	private final String mName;
	private Controller mController;

	
	public GamepadJoystick(String aName, Identifier[] aAxisList, Identifier[] aButtonList) 
	{
		mName = aName;

		ControllerEnvironment ce = ControllerEnvironment.getDefaultEnvironment(); 

		Controller[] cs = ce.getControllers(); 
		for (int i = 0; i < cs.length; i++) 
		{
			if(!cs[i].getName().equals(aName))
			{
				continue;
			}
			
			mController = cs[i];
		}
		
		mAxis = aAxisList;
		mButtons = aButtonList;

		mAxisValues = new short[aAxisList.length];
		mPovValues = new short[0];
	}

	@Override
	public int getAxisCount() {
		return mAxis.length;
	}

	@Override
	public int getButtonCount() {
		return mButtons.length;
	}

	@Override
	public void setRumble(short s) {
		//TODO implement rumble...
	}

	@Override
	public short[] getAxisValues() {
		
		if(mController != null)
		{
			mController.poll();
	
			for(int i = 0; i < mAxis.length; ++i)
			{
				mAxisValues[i] = (short)(mController.getComponent(mAxis[i]).getPollData() * 127);
			}
		}
		else
		{
			System.err.println("Controller is null.  The simulator could not setup a controller with the name '" + mName + "'");
		}
		
		return mAxisValues;
	}

	@Override
	public int getButtonMask() {
		
		int output = 0;
		
		if(mController != null)
		{
			mController.poll();
			
			for(int i = 0; i < mButtons.length; ++i)
			{
				int pressed = mController.getComponent(mButtons[i]).getPollData() == 0 ? 0 : 1;
				output += (pressed << i);
			}
		}
		else
		{
			System.err.println("Controller is null.  The simulator could not setup a controller with the name '" + mName + "'");
		}
		
		return output;
	}

	@Override
	public short[] getPovValues() {
		return mPovValues;
	}
}
