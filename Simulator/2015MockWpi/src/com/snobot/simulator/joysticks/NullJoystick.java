package com.snobot.simulator.joysticks;

public class NullJoystick implements IMockJoystick
{
    private static final int sNUM_BUTTONS = 10;
    private static final int sNUM_AXIS = 6;

    private short[] mAxis;
    private short[] mPov;

    public NullJoystick()
    {
        mAxis = new short[sNUM_BUTTONS];
        mPov = new short[4];
    }

    // @Override
    // public boolean getRawButton(int aIndex) {
    // return false;
    // }
    //
    // @Override
    // public double getRawAxis(int aIndex) {
    // return 0;
    // }
    //
    // @Override
    // public double getX() {
    // return 0;
    // }
    //
    // @Override
    // public double getY() {
    // return 0;
    // }

    @Override
    public int getAxisCount()
    {
        return sNUM_AXIS;
    }

    @Override
    public int getButtonCount()
    {
        return sNUM_BUTTONS;
    }

    @Override
    public void setRumble(short s)
    {
    }

    @Override
    public short[] getAxisValues()
    {
        return mAxis;
    }

    @Override
    public short[] getPovValues()
    {
        return mPov;
    }

    @Override
    public int getButtonMask()
    {
        return 0;
    }

}
