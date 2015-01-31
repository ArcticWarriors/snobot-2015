package com.snobot.drivetrain;

import com.snobot.ISubsystem;

/**
 * Main Drive Train interface.
 * 
 * @author Ayush/Ammar
 *
 */
public interface IDriveTrain extends ISubsystem
{

    /**
     * Will set speed of motors.
     * 
     * @param aRight
     *            Right Motor Speed(1 means forward; -1 means reverse.)
     * @param aLeft
     *            Left Motor Speed(1 means forward; -1 means reverse.)
     */
    public void setMotorSpeed(double aLeft, double aRight);

    public void setDefaultMeasure(UnitOfMeasure aMeasure);

    public double calculateDistanceRight();

    public double calculateDistanceLeft();

    public enum UnitOfMeasure
    {
        Feet, Meters, Inches, Centimeters
    }
}
