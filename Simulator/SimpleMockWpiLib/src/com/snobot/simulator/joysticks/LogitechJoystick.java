package com.snobot.simulator.joysticks;
import java.util.Arrays;

import net.java.games.input.Component.Identifier;

public class LogitechJoystick extends GamepadJoystick
{
	public LogitechJoystick() 
	{
		super(
				"Logitech Dual Action",

				Arrays.asList(
					Identifier.Axis.X,   //Left x
					Identifier.Axis.Y,   //Left Y 
					null,
					null,
					Identifier.Axis.Z,   //Right x 
					Identifier.Axis.RZ   //Right x 
				), 

				Arrays.asList(
					Identifier.Button._0, Identifier.Button._1, Identifier.Button._2, Identifier.Button._3,
					Identifier.Button._4, Identifier.Button._5, Identifier.Button._6, Identifier.Button._7
				));
	}
}
