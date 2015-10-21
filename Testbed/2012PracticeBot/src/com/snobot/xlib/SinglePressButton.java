package com.snobot.xlib;

/**
 * Rising edge detector button. Will return true when a rising edge has been detected.
 * 
 * @author RICH
 */
public class SinglePressButton
{
    private boolean mLastButtonState;
    private boolean mCurrentState;

    /**
     * Constructor. Initializes the last state to false
     */
    public SinglePressButton()
    {
        mLastButtonState = false;
        mCurrentState = false;
    }

    /**
     * Will check for a rising edge using the last saved state and the provided state
     * 
     * @param isPressed
     *            The current state
     * @return True if a rising edge has occurred
     */
    public boolean update(boolean isPressed)
    {
        // Rising edge detector
        mCurrentState = !mLastButtonState && isPressed;
        mLastButtonState = isPressed;

        return mCurrentState;
    }

    public boolean get()
    {
        return mCurrentState;
    }
}
