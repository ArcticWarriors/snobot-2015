package com.snobot.simulator.joysticks;

public interface IMockJoystick
{

    int getAxisCount();

    int getButtonCount();

    void setRumble(short s);

    short[] getAxisValues();

    short[] getPovValues();

    int getButtonMask();

}
