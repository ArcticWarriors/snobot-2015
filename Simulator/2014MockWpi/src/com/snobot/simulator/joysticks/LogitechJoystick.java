package com.snobot.simulator.joysticks;
import java.util.Arrays;

import net.java.games.input.Component.Identifier;

public class LogitechJoystick extends GamepadJoystick
{
	private static final Identifier[] sAXIS = new Identifier[]{
		Identifier.Axis.X,   //Left x
		Identifier.Axis.Y,   //Left Y 
		null,
		null,
		Identifier.Axis.Z,   //Right x 
		Identifier.Axis.RZ   //Right x 
	};
	
	private static final Identifier[] sBUTTONS = new Identifier[]{
		Identifier.Button._0, Identifier.Button._1, Identifier.Button._2, Identifier.Button._3,
		Identifier.Button._4, Identifier.Button._5, Identifier.Button._6, Identifier.Button._7
	};
	
	
	public LogitechJoystick() 
	{
		super("Logitech Dual Action", Arrays.asList(sAXIS), Arrays.asList(sBUTTONS));
	}
}
