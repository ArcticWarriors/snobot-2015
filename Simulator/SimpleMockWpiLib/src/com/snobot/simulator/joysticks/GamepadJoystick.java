package com.snobot.simulator.joysticks;

import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Component.Identifier;

public class GamepadJoystick implements IMockJoystick {

	private final Identifier[] mAxis;
	private final Identifier[] mButtons;
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
	}

	@Override
	public boolean getRawButton(int aIndex) 
	{
		if(mController != null && aIndex < mButtons.length)
		{
			mController.poll();
			return mController.getComponent(mButtons[aIndex]).getPollData() == 1;
		}
		
		if(mController == null)
		{
			System.err.println("Controller is null.  The simulator could not setup a controller with the name '" + mName + "'");
		}
		else if(aIndex >= mButtons.length)
		{
			System.out.println("The button " + aIndex + " was not setup for this simulator joystick.  Note that this could be the simulators fault");
		}
		
		return false;
	}

	@Override
	public double getRawAxis(int aIndex) 
	{
		if(mController != null && aIndex < mAxis.length)
		{
			mController.poll();
			return mController.getComponent(mAxis[aIndex]).getPollData();
		}
		
		if(mController == null)
		{
			System.err.println("Controller is null.  The simulator could not setup a controller with the name '" + mName + "'");
		}
		else if(aIndex >= mAxis.length)
		{
			System.out.println("The axis " + aIndex + " was not setup for this simulator joystick.  Note that this could be the simulators fault");
		}
		
		return 0.0;
	}

	@Override
	public double getX() 
	{
		return getRawAxis(2);
	}

	@Override
	public double getY() 
	{
		return getRawAxis(1);
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
		// TODO Auto-generated method stub
		
	}
}
