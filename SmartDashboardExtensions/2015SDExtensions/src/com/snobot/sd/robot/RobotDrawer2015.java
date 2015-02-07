package com.snobot.sd.robot;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class RobotDrawer2015 extends JPanel
{    
    private static final double sCHASIS_WIDTH = 40;
    private static final double sCHASIS_HEIGHT = 6;
    private static final double sCHASIS_X_START = 50.75;
    private static final double sCHASIS_Y_START = 40;
    
    private static final double sCLAW_HEIGHT_START = 44;
    private static final double sCLAW_HEIGHT_END = 46;
    private static final double sCLAW_LENGTH = 13;
//    private static final double sCLAW_X_START;
//    private static final double sCLAW_Y_START;
    
    private static final double sSTACKER_HEIGHT = 34.375;
    private static final double sSTACKER_WIDTH = 1;
    private static final double sSTACKER_X_START = 50.75;
    private static final double sSTACKER_Y_START = sCHASIS_Y_START-sSTACKER_HEIGHT;
    
    private static final double sSTACKER_FORK_LENGTH = 20.75;
    private static final double sSTACKER_FORK_HEIGHT = 1;
    private static final double sSTACKER_FORK_X_START = 30;
    private static final double sSTACKER_FORK_Y_START = sCHASIS_Y_START-1;
    
    private static final double sROBOT_WIDTH = sSTACKER_X_START + sCHASIS_WIDTH+sSTACKER_FORK_LENGTH;
    private static final double sROBOT_HEIGHT = sCHASIS_HEIGHT+sSTACKER_HEIGHT;
    
    private static final double sLIMIT_SWITCH_HEIGHT = 1;
    private static final double sLIMIT_SWITCH_WIDTH =1;
    private static final double sLOWER_LIMIT_X_START = sCHASIS_X_START-1;
    private static final double sLOWER_LIMIT_Y_START = sCHASIS_Y_START-1;
    private static final double sUPPER_LIMIT_X_START = sCHASIS_X_START-1;
    private static final double sUPPER_LIMIT_Y_START = (sCHASIS_Y_START-sSTACKER_HEIGHT);
    
    private Dimension mDimension = new Dimension(400,400);
    private double mScaleFactor;
    
    private boolean mClawOpen;
    private boolean mClawUp;
    private double mStackerHeight;
    private boolean mUpperLimitSwitch;
    private boolean mLowerLimitSwitch;
    
    
    private static final Color sROBOT_BASE_COLOR = Color.black;
    private static final Color sROBOT_FORK_COLOR = Color.blue;
    private static final Color sROBOT_STACKER_COLOR = Color.gray;
    public RobotDrawer2015()
    {
        updateSize();
        setPreferredSize(new Dimension(200, 200));
        

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent arg0) {
                updateSize();
            }
        });
    }
    
      public void updateSize()
    {
        double horizontalScaleFactor= (getWidth()/sROBOT_WIDTH);
        double verticalScaleFactor= (getHeight()/sROBOT_HEIGHT);
          
        double minScaleFactor = Math.min(horizontalScaleFactor, verticalScaleFactor);
        
        mScaleFactor = minScaleFactor;
        
        System.out.println("Scale Factor: " + mScaleFactor);
        System.out.println("  Horizontal factor : " + horizontalScaleFactor + ", width : " + getWidth() + ", " + sROBOT_WIDTH);
        System.out.println("  Vertical factor : " + verticalScaleFactor + ", width : " + getHeight() + ", " + sROBOT_HEIGHT);
        
        repaint();
    }
    
    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        
        g.clearRect(0, 0, (int) getSize().getWidth(), (int) getSize().getHeight());

        drawRobotBase(g2d);
        drawForklift(g2d);
        drawClaw(g2d);
        drawStacker(g2d);
        drawUpperLimitSwitch(g2d);
        drawLowerLimitSwitch(g2d);
    }

  
    private void drawLowerLimitSwitch(Graphics2D g2d)
    {
        Rectangle2D lowerLimitSwitch= new Rectangle2D.Double(
                sLOWER_LIMIT_X_START* mScaleFactor, 
                sLOWER_LIMIT_Y_START * mScaleFactor, 
                sLIMIT_SWITCH_HEIGHT*mScaleFactor, 
                sLIMIT_SWITCH_WIDTH*mScaleFactor);
        
        if (mLowerLimitSwitch)
        {
            g2d.setColor(Color.green);
        }
        else
        {
            g2d.setColor(Color.red);
        }
        
        g2d.fill(lowerLimitSwitch);
    }
    
    private void drawUpperLimitSwitch (Graphics2D g2d)
    {
        Rectangle2D upperLimitSwitch = new Rectangle2D.Double(
                sUPPER_LIMIT_X_START* mScaleFactor,  
                sUPPER_LIMIT_Y_START* mScaleFactor, 
                sLIMIT_SWITCH_HEIGHT*mScaleFactor, 
                sLIMIT_SWITCH_WIDTH*mScaleFactor);
        
        if (mUpperLimitSwitch)
        {
            g2d.setColor(Color.green);
        }
        else
        {
            g2d.setColor(Color.red);
        }
        
        g2d.fill(upperLimitSwitch);
    }
    
    private void drawRobotBase(Graphics2D g2d)
    {
        Rectangle2D robotBase = new Rectangle2D.Double(
                sCHASIS_X_START * mScaleFactor, 
                sCHASIS_Y_START * mScaleFactor, 
                sCHASIS_WIDTH * mScaleFactor, 
                sCHASIS_HEIGHT * mScaleFactor);
        g2d.setColor(sROBOT_BASE_COLOR);
        g2d.fill(robotBase);
    }
    
    private void drawForklift (Graphics2D g2d)
    {
        Rectangle2D forklift = new Rectangle2D.Double(
                sSTACKER_FORK_X_START * mScaleFactor, 
                (sSTACKER_FORK_Y_START-mStackerHeight) * mScaleFactor, 
                sSTACKER_FORK_LENGTH*mScaleFactor, 
                sSTACKER_FORK_HEIGHT*mScaleFactor);
        g2d.setColor(sROBOT_FORK_COLOR);
        g2d.fill(forklift);
    }
    
    private void drawStacker (Graphics2D g2d)
    {
        Rectangle2D stacker = new Rectangle2D.Double(
                sSTACKER_X_START * mScaleFactor, 
                sSTACKER_Y_START * mScaleFactor,
                sSTACKER_WIDTH * mScaleFactor, 
                sSTACKER_HEIGHT * mScaleFactor);
        g2d.setColor(sROBOT_STACKER_COLOR);
        g2d.fill(stacker);
    }
    
    private void drawClaw (Graphics2D g2d)
    {
        
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
        updateSize();
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
