package com.snobot.sd.robot;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class RobotDrawer extends JPanel
{
    private static final double sROBOT_LENGTH = 28;
    private static final double sROBOT_HEIGHT = 12;
    
    private static final double sCHASIS_LENGTH = 40;
    private static final double sCHASIS_HEIGHT = 6;
    private static final double sCHASIS_WIDTH = 27;
    
    private static final double sCLAW_HEIGHT_START = 44;
    private static final double sCLAW_HEIGHT_END = 46;
    private static final double sCLAW_LENGTH = 13;
    
    private static final double sSTACKER_HEIGHT = 34;
    private static final double sSTACKER_FORK_LENGTH = 13;
    
    private boolean mClawOpen;
    private boolean mClawUp;
    private double mStackerHeight;
    private boolean mUpperLimitSwitch;
    private boolean mLowerLimitSwitch;
    
    public RobotDrawer()
    {
        setPreferredSize(new Dimension(200, 200));
    }
    
    public void paint(Graphics upperLimitSwitch)
    {
        if (mUpperLimitSwitch)
        {
        upperLimitSwitch.setColor(Color.green);
        upperLimitSwitch.fillRect(0, 0, 10, 10);
        }
        else
        {
            upperLimitSwitch.setColor(Color.red);
            upperLimitSwitch.fillRect(0, 0, 10, 10);
        }
    }
    
    
    
    /**
     * True if claw is open else false
     * @param aClawOpen
     */
    public void setClawOpen (boolean aClawOpen)
    {
        mClawOpen = aClawOpen;
    }
    
    /**
     * 
     * @param aClawUp
     */
    public void setClawUp (boolean aClawUp)
    {
        mClawUp = aClawUp;
    }
    
    /**
     * 
     * @param aStackerHeight
     */
    public void setStackerHeight (double aStackerHeight)
    {
        mStackerHeight = aStackerHeight;
    }
    
    /**
     * 
     * @param aUpperLimitSwitch
     */
    public void setUpperLimitSwtich (boolean aUpperLimitSwitch)
    {
        mUpperLimitSwitch = aUpperLimitSwitch;
    }
    
    /**
     * 
     * @param aLowerLimitSwitch
     */
    public void setLowerLimitSwitch (boolean aLowerLimitSwitch)
    {
        mLowerLimitSwitch = aLowerLimitSwitch;
    }
    
    /**
     * 
     * @return True if claw is open else claw is closed
     */
    public boolean isClawOpen()
    {
        return mClawOpen;
    }
    
    /**
     * 
     * @return True if claw is up else claw is down
     */
    public boolean isClawUp()
    {
        return mClawUp;
    }
    
    /**
     * 
     * @return Stacker height
     */
    public double getStackerHeight()
    {
        return mStackerHeight;
    }
    
    /**
     * 
     * @return true if the upper limit switch is hit else false
     */
    public boolean isUpperLimitSwitch()
    {
        return mUpperLimitSwitch;
    }
    
    /**
     * 
     * @return true if the lower limit switch is hit else false
     */
    public boolean isLowerLimitSwitch()
    {
        return mLowerLimitSwitch;
    }
}
