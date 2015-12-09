package com.snobot.position;

public interface IPositioner
{

    /**
     * Calculates the value of mRadianRotation in degrees
     * 
     * @return mRadianRotation in human-friendly degrees
     */
    public abstract double getSnobotDegrees();

    /**
     * Gets Snobot's X-position from mPositionX
     * 
     * @return Snobot's X-position
     */
    public abstract double getSnobotX();

    /**
     * Gets Snobot's Y-position from mPositionY
     * 
     * @return Snobot's Y-position
     */
    public abstract double getSnobotY();

    /**
     * Gets Snobot's radian rotation from mRadianRotation
     * 
     * @return Snobot's rotation in radians
     */
    public abstract double getSnobotRadians();

    public abstract void setPosition(double aX, double aY, double aAngle);

    /**
     * Sets Snobot's X-position
     * 
     * @param aXPosition
     *            Snobot's new X-position
     */
    public abstract void setSnobotXPosition(double aXPosition);

    /**
     * Sets Snobot's Y-position
     * 
     * @param aYPosition
     *            Snobot's new Y-position
     */
    public abstract void setSnobotYPosition(double aYPosition);

    /**
     * Sets Snobot's orientation
     * 
     * @param aDegrees
     *            Snobot's new orientation in degrees
     */
    public abstract void setSnobotDegreeRotation(double aDegrees);

    /**
     * Gets the total distance traveled
     * 
     * @return The total distance traveled by Snobot
     */
    public abstract double getTotalDistance();

}