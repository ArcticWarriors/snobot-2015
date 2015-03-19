package com.snobot.sd.coordinategui;

/**
 * Represents an autonomous points.  Contains an x,y and deadband
 * @author PJ
 */
public class AutonPoint 
{
    /** The x coordinate, in feet */
    public double x;
    /** The y coordinate, in feet */
    public double y;
    /** The deadband of the command, in feet */
    public double deadband;
    
    /**
     * Default constructor.  Sets the (x,y) coordinates to zero
     */
    public AutonPoint()
    {
        this(0, 0);
    }
    
    /**
     * Constructor.  Sets the (x,y) coordinate to the given values
     * @param ax The x coordinate
     * @param ay The y coordinate
     */
    public AutonPoint(double ax, double ay)
    {
        this(ax, ay, 0);
    }
    
    /**
     * Constructor.  Sets the (x,y) position and the deadband
     * @param ax The x coordinate
     * @param ay The y coordinate
     * @param aDb The deadband
     */
    public AutonPoint(double ax, double ay, double aDb)
    {
        x = ax;
        y = ay;
        deadband = aDb;
    }

    @Override
    public String toString()
    {
        return "AutonPoint{" + "x=" + x + ", y=" + y + ", deadband=" + deadband + '}';
    }
}
