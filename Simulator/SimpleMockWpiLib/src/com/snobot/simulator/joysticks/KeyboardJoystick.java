package com.snobot.simulator.joysticks;

import java.util.Arrays;

import net.java.games.input.Component.Identifier;

public class KeyboardJoystick extends GamepadJoystick
{

	public KeyboardJoystick()
	{
		super( 
				"Standard PS/2 Keyboard",

				Arrays.asList(
						Identifier.Key.W,
						Identifier.Key.A,
						Identifier.Key.S,
						Identifier.Key.D,
						Identifier.Key.I,
						Identifier.Key.J,
						Identifier.Key.K,
						Identifier.Key.L
				), 
				
				Arrays.asList(
						Identifier.Key._0,
						Identifier.Key._1,
						Identifier.Key._2,
						Identifier.Key._3,
						Identifier.Key._4,
						Identifier.Key._5,
						Identifier.Key._6,
						Identifier.Key._7,
						Identifier.Key._8,
						Identifier.Key._9
				));
	}
}
