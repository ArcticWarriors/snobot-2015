package edu.wpi.first.wpilibj.templates;

/**
 * Creates a toggle button (one click on, next click off). Checks for a rising
 * edge to indicate the state has changed.
 *
 *
 *
 *
 * <code>
 * boolean triggerState = control.getDriverJoystick().getTrigger();
 * boolean isHigh = testButton.update(triggerState);
 *
 * if(isHigh)
 * {
 * mShooterMotor.set(1.0);
 * }
 * else if (state == false )
 * {
 * mShooterMotor.set(0);
 * }
 * </code>
 *
 * @author RICH
 */
public class ToggleButton
{

    private boolean mState;
    private boolean mLastButtonState;

    /**
     * Constructor. Defaults the state to LOW
     */
    public ToggleButton() {
        this(false);
    }

    /**
     * Constructor. Sets the state based on the given input
     *
     * @param aStartHigh Starting state (true = HIGH, false = LOW)
     */
    public ToggleButton(boolean aStartHigh) {
        mState = aStartHigh;
        mLastButtonState = false;
    }

    /**
     * Checks for a rising edge. If it has occurred, the output state will be
     * toggled
     *
     * @param isPressed The current button state
     * @return The toggle state
     */
    public boolean update(boolean isPressed) {
        //Rising edge detector
        if (!mLastButtonState && isPressed) {
            mState = !mState;
        }
        mLastButtonState = isPressed;

        return mState;
    }

    public boolean get() {
        return mState;
    }
}
