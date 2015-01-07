package com.snobot.simulator;

public class JoystickFactory {

	public IMockJoystick create(int aJoystickIndex) {
		return new KeyboardJoystick();
	}

}
