package com.snobot.simulator;

import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Component.Identifier;

public class GamepadJoystick implements IMockJoystick {

	enum GamepadAxis
	{
		LeftX,
		LeftY,
		RightX,
		RightY,
	}

	private final Identifier[] mAxis;
	private final Identifier[] mButtons;
	private Controller mController;
	
	public GamepadJoystick(String aName, Identifier[] aAxisList, Identifier[] aButtonList) 
	{
		PrintJinputThings.printAll();
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
		if(mController != null)
		{
			mController.poll();
			return mController.getComponent(mButtons[aIndex]).getPollData() == 1;
		}
		
		System.err.println("Was not able to create joystick");
		return false;
	}

	@Override
	public double getRawAxis(int aIndex) 
	{
		if(mController != null)
		{
			mController.poll();
			return mController.getComponent(mAxis[aIndex]).getPollData();
		}
		
		System.err.println("Was not able to create joystick");
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
