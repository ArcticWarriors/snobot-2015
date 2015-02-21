package com.snobot.simulator.joysticks;

public class NullJoystick implements IMockJoystick 
{
	private static final int sNUM_BUTTONS = 10;
	private static final int sNUM_AXIS = 6;

    private byte[] mAxis;
    private byte[] mPov;
	
	public NullJoystick()
	{
        mAxis = new byte[sNUM_BUTTONS];
        mPov = new byte[0];
	}

//	@Override
//	public boolean getRawButton(int aIndex) {
//		return false;
//	}
//
//	@Override
//	public double getRawAxis(int aIndex) {
//		return 0;
//	}
//
//	@Override
//	public double getX() {
//		return 0;
//	}
//
//	@Override
//	public double getY() {
//		return 0;
//	}

	@Override
	public int getAxisCount() {
		return sNUM_AXIS;
	}

	@Override
	public int getButtonCount() {
		return sNUM_BUTTONS;
	}

	@Override
	public void setRumble(short s) {
	}

	@Override
    public byte[] getAxisValues()
    {
		return mAxis;
	}

	@Override
    public byte[] getPovValues()
    {
		return mPov;
	}

	@Override
	public int getButtonMask() {
		return 0;
	}

}
