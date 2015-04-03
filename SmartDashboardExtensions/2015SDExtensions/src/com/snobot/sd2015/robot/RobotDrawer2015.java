package com.snobot.sd2015.robot;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class RobotDrawer2015 extends JPanel
{
    // Chassis dimensions
    private static final double sCHASSIS_WIDTH = 32;
    private static final double sCHASSIS_HEIGHT = 3.25;
    private static final double sCHASSIS_X_START = 25;
    private static final double sCHASSIS_Y_START = 80;

    // Claw Tower dimensions
    private static final double sCLAW_TO_CHASSIS = 21;
    private static final double sCLAW_TOWER_HEIGHT = 47.25;
    private static final double sCLAW_ARM_LENGTH = 47.25;
    private static final double sCLAW_TOWER_WIDTH = 3;
    private static final double sCLAW_TOWER_ARM_WIDTH = 1;
    private static final double sCLAW_TOWER_X_START = (sCHASSIS_X_START + sCLAW_TO_CHASSIS);
    private static final double sCLAW_TOWER_Y_START = (sCHASSIS_Y_START - sCLAW_TOWER_HEIGHT);

    // Stacker Tower dimensions
    private static final double sCHASSIS_TO_STACKER = 0;
    private static final double sSTACKER_TOWER_HEIGHT = 34.375;
    private static final double sSTACKER_TOWER_WIDTH = 3;
    private static final double sSTACKER_TOWER_X_START = (sCHASSIS_X_START + sCHASSIS_TO_STACKER);
    private static final double sSTACKER_TOWER_Y_START = (sCHASSIS_Y_START - sSTACKER_TOWER_HEIGHT);

    // Stacker Fork dimensions
    private static final double sSTACKER_FORK_LENGTH = 20.75;
    private static final double sSTACKER_FORK_WIDTH = 1;
    private static final double sSTACKER_FORK_X_START = (sSTACKER_TOWER_X_START - sSTACKER_FORK_LENGTH);
    private static final double sSTACKER_FORK_Y_START = (sSTACKER_TOWER_Y_START + sSTACKER_TOWER_HEIGHT);

    private static final double sROBOT_WIDTH = sSTACKER_TOWER_X_START + sCHASSIS_WIDTH + sSTACKER_FORK_LENGTH
            + (sCLAW_TOWER_HEIGHT - sCLAW_TO_CHASSIS);
    private static final double sROBOT_HEIGHT = sCHASSIS_HEIGHT + sSTACKER_TOWER_HEIGHT + sCLAW_TOWER_HEIGHT;

    // Claw dimensions
    private static final double sCLAW_X_START = (sCLAW_TOWER_X_START);
    private static final double sCLAW_Y_START = (sCLAW_TOWER_Y_START);
    private static final double sCLAW_WIDTH = 13;
    private static final double sCLAW_HEIGHT = 5;

    // Limit Switch dimensions
    private static final double sLIMIT_SWITCH_HEIGHT = 1;
    private static final double sLIMIT_SWITCH_WIDTH = 1;
    private static final double sLOWER_LIMIT_X_START = (sSTACKER_TOWER_X_START - 1);
    private static final double sLOWER_LIMIT_Y_START = (sSTACKER_TOWER_Y_START + (sSTACKER_TOWER_HEIGHT));
    private static final double sUPPER_LIMIT_X_START = (sSTACKER_TOWER_X_START - 1);
    private static final double sUPPER_LIMIT_Y_START = (sSTACKER_TOWER_Y_START);

    private double mScaleFactor;

    private boolean mClawOpen;
    private boolean mClawUp;
    private double mStackerHeight;
    private boolean mUpperLimitSwitch;
    private boolean mLowerLimitSwitch;

    private static final Color sROBOT_BASE_COLOR = Color.black;
    private static final Color sROBOT_FORK_COLOR = Color.blue;
    private static final Color sROBOT_STACKER_COLOR = Color.gray;
    private static final Color sROBOT_ARM_COLOR = Color.magenta;
    private static final Color sROBOT_TOWER_COLOR = Color.gray;
    private static final Color sROBOT_CLAW_CLOSED_COLOR = Color.orange;
    private static final Color sROBOT_CLAW_OPEN_COLOR = Color.cyan;

    public RobotDrawer2015()
    {
        updateSize();
        setPreferredSize(new Dimension(200, 200));

        addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentResized(ComponentEvent arg0)
            {
                updateSize();
            }
        });
    }

    public void updateSize()
    {
        double horizontalScaleFactor = (getWidth() / sROBOT_WIDTH);
        double verticalScaleFactor = (getHeight() / sROBOT_HEIGHT);

        double minScaleFactor = Math.min(horizontalScaleFactor, verticalScaleFactor);

        mScaleFactor = minScaleFactor;

        // System.out.println("Scale Factor: " + mScaleFactor);
        // System.out.println("  Horizontal factor : " + horizontalScaleFactor + ", width : " + getWidth() + ", " + sROBOT_WIDTH);
        // System.out.println("  Vertical factor : " + verticalScaleFactor + ", width : " + getHeight() + ", " + sROBOT_HEIGHT);

        repaint();
    }

    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;

        g.clearRect(0, 0, (int) getSize().getWidth(), (int) getSize().getHeight());

        drawRobotBase(g2d);
        drawForklift(g2d);
        drawStacker(g2d);
        drawClawTower(g2d);
        drawClawArm(g2d);
        drawClaw(g2d);
        drawUpperLimitSwitch(g2d);
        drawLowerLimitSwitch(g2d);

    }

    private void drawLowerLimitSwitch(Graphics2D g2d)
    {
        Rectangle2D lowerLimitSwitch = new Rectangle2D.Double(
                sLOWER_LIMIT_X_START * mScaleFactor,
                sLOWER_LIMIT_Y_START * mScaleFactor,
                sLIMIT_SWITCH_HEIGHT * mScaleFactor,
                sLIMIT_SWITCH_WIDTH * mScaleFactor);

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

    private void drawUpperLimitSwitch(Graphics2D g2d)
    {
        Rectangle2D upperLimitSwitch = new Rectangle2D.Double(
                sUPPER_LIMIT_X_START * mScaleFactor,
                sUPPER_LIMIT_Y_START * mScaleFactor,
                sLIMIT_SWITCH_HEIGHT * mScaleFactor,
                sLIMIT_SWITCH_WIDTH * mScaleFactor);

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
                sCHASSIS_X_START * mScaleFactor,
                sCHASSIS_Y_START * mScaleFactor,
                sCHASSIS_WIDTH * mScaleFactor,
                sCHASSIS_HEIGHT * mScaleFactor);

        g2d.setColor(sROBOT_BASE_COLOR);
        g2d.fill(robotBase);
    }

    private void drawForklift(Graphics2D g2d)
    {
        Rectangle2D forklift = new Rectangle2D.Double(
                sSTACKER_FORK_X_START * mScaleFactor,
                (sSTACKER_FORK_Y_START - mStackerHeight) * mScaleFactor,
                sSTACKER_FORK_LENGTH * mScaleFactor,
                sSTACKER_FORK_WIDTH * mScaleFactor);

        g2d.setColor(sROBOT_FORK_COLOR);
        g2d.fill(forklift);
    }

    private void drawStacker(Graphics2D g2d)
    {
        Rectangle2D stacker = new Rectangle2D.Double(
                sSTACKER_TOWER_X_START * mScaleFactor,
                sSTACKER_TOWER_Y_START * mScaleFactor,
                sSTACKER_TOWER_WIDTH * mScaleFactor,
                sSTACKER_TOWER_HEIGHT * mScaleFactor);

        g2d.setColor(sROBOT_STACKER_COLOR);
        g2d.fill(stacker);
    }

    private void drawClawTower(Graphics2D g2d)
    {
        Rectangle2D claw = new Rectangle2D.Double(
                sCLAW_TOWER_X_START * mScaleFactor,
                sCLAW_TOWER_Y_START * mScaleFactor,
                sCLAW_TOWER_WIDTH * mScaleFactor,
                sCLAW_TOWER_HEIGHT * mScaleFactor);

        g2d.setColor(sROBOT_TOWER_COLOR);
        g2d.fill(claw);
    }

    private void drawClawArm(Graphics2D g2d)
    {
        double rotation;

        if (mClawUp)
        {
            rotation = -100;
        }
        else
        {
            rotation = -20;
        }

        Rectangle2D arm = new Rectangle2D.Double(
                sCLAW_TOWER_X_START,
                sCLAW_TOWER_Y_START,
                sCLAW_TOWER_ARM_WIDTH,
                sCLAW_ARM_LENGTH);

        AffineTransform transform = new AffineTransform();
        transform.scale(mScaleFactor, mScaleFactor);
        transform.rotate(Math.toRadians(rotation), sCLAW_TOWER_X_START, sCLAW_TOWER_Y_START);

        Shape shape = transform.createTransformedShape(arm);
        g2d.setColor(sROBOT_ARM_COLOR);
        g2d.fill(shape);
    }

    private void drawClaw(Graphics2D g2d)
    {
        double rotation = mClawUp ? -100 : -20;

        double sTRIG_VARIABLE = sCLAW_ARM_LENGTH * (Math.cos(Math.toRadians(rotation)));
        double sWIDTH_BETWEEN_CLAW_TOWER_ARM = sCLAW_ARM_LENGTH * (Math.sin(Math.toRadians(rotation)));

        Rectangle2D claw = new Rectangle2D.Double(
                (sCLAW_X_START - sWIDTH_BETWEEN_CLAW_TOWER_ARM) * mScaleFactor,
                (sCLAW_Y_START + sTRIG_VARIABLE) * mScaleFactor,
                sCLAW_WIDTH * mScaleFactor,
                sCLAW_HEIGHT * mScaleFactor);

        g2d.setColor(mClawOpen ? sROBOT_CLAW_OPEN_COLOR : sROBOT_CLAW_CLOSED_COLOR);
        g2d.fill(claw);
    }

    /**
     * True if claw is open else false
     * 
     * @param aClawOpen
     */
    public void setClawOpen(boolean aClawOpen)
    {
        mClawOpen = aClawOpen;
    }

    /**
     * 
     * @param aClawUp
     */
    public void setClawUp(boolean aClawUp)
    {
        mClawUp = aClawUp;
    }

    /**
     * 
     * @param aStackerHeight
     */
    public void setStackerHeight(double aStackerHeight)
    {
        mStackerHeight = aStackerHeight;
        updateSize();
    }

    /**
     * 
     * @param aUpperLimitSwitch
     */
    public void setUpperLimitSwtich(boolean aUpperLimitSwitch)
    {
        mUpperLimitSwitch = aUpperLimitSwitch;
    }

    /**
     * 
     * @param aLowerLimitSwitch
     */
    public void setLowerLimitSwitch(boolean aLowerLimitSwitch)
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
