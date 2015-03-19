package com.snobot.simulator.joysticks;

public interface IMockJoystick {

	int getAxisCount();

	int getButtonCount();

	void setRumble(short s);
	
    byte[] getAxisValues();

    byte[] getPovValues();

	int getButtonMask();

}
