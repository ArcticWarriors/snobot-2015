package com.snobot.simulator;
import net.java.games.input.*;
import net.java.games.input.Component.Identifier;

public class LogitechJoystick implements IMockJoystick 
{

	enum GamepadAxis
	{
		LeftX,
		LeftY,
		RightX,
		RightY,
	}

	private Identifier[] mAxis = new Identifier[]{
			Identifier.Axis.X,   //Left x
			Identifier.Axis.Y,   //Left Y 
			null,
			null,
			Identifier.Axis.Z,   //Right x 
			Identifier.Axis.RZ,  //Right x 
			};
	
	private Identifier[] mButtons = new Identifier[]{
			Identifier.Button._0, Identifier.Button._1, Identifier.Button._2, Identifier.Button._3,
			Identifier.Button._4, Identifier.Button._5, Identifier.Button._6, Identifier.Button._7
			};

	private Controller mController = null;
	
	public LogitechJoystick(int aJoystickIndex) 
	{
		ControllerEnvironment ce = ControllerEnvironment.getDefaultEnvironment(); 

		Controller[] cs = ce.getControllers(); 
		for (int i = 0; i < cs.length; i++) 
		{
			if(!cs[i].getName().equals("Logitech Dual Action"))
			{
				continue;
			}
			
			mController = cs[i];
		}
	}

	@Override
	public boolean getRawButton(int aIndex) 
	{
		mController.poll();
		return mController.getComponent(mButtons[aIndex]).getPollData() == 1;
	}

	@Override
	public double getRawAxis(int aIndex) 
	{
		mController.poll();
		return mController.getComponent(mAxis[aIndex]).getPollData();
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
}
