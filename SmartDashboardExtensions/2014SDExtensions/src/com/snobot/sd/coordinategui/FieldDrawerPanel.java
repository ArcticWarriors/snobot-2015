package com.snobot.sd.coordinategui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import com.snobot.sd.coordinategui.pointDrawers.AllPointDrawer;
import com.snobot.sd.coordinategui.pointDrawers.FadedPointDrawer;
import com.snobot.sd.coordinategui.pointDrawers.RainbowPointDrawer;
import com.snobot.sd.coordinategui.pointDrawers.RobotPointDrawer;
import com.snobot.sd.coordinategui.pointDrawers.SimpleAutonPointDrawer;
import com.snobot.sd.coordinategui.pointDrawers.SinglePointDrawer;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FieldDrawerPanel extends JPanel
{
    /**
     * 
     */
    private static final long serialVersionUID = 3583044002168513234L;


    /**
     * Sets the way to draw coordinates on the field
     */
    public enum DrawingType
    {
        /** Draws only the most recent point on the field */
        SinglePoint,
        /** Draws all of the points encountered */
        DrawAll,
        /** Fades the points over time */
        Fade,
        /** Same as Fade, but colors the points with a rainbow effect */
        Rainbow,
        /** Draws a box representing the robot */
        Robot
    }
    
    public final static Map<String, DrawingType> sDRAWING_TYPE_MAP;
    
    static
    {
        sDRAWING_TYPE_MAP = new HashMap<String, FieldDrawerPanel.DrawingType>();
        sDRAWING_TYPE_MAP.put("LOLOL NYANCAT", DrawingType.Rainbow);
        sDRAWING_TYPE_MAP.put("Fade", DrawingType.Fade);
        sDRAWING_TYPE_MAP.put("All", DrawingType.DrawAll);
        sDRAWING_TYPE_MAP.put("Single", DrawingType.SinglePoint);
        sDRAWING_TYPE_MAP.put("Robot", DrawingType.Robot);
    }
    
    private DrawingType mDrawingType;
    private double mPointMemory;

    private SinglePointDrawer mSinglePointDrawer;
    private FadedPointDrawer mFadedPointDrawer;
    private AllPointDrawer mAllPointDrawer;
    private RainbowPointDrawer mRainbowPointDrawer;
    private RobotPointDrawer mRobotPointDrawer;
    
    private SimpleAutonPointDrawer mAutonPointDrawer;
    
    private FieldDrawer mFieldDrawer;
    private Color mDefaultColor;
    
    public FieldDrawerPanel(FieldProperties aFieldProps)
    {
        mDrawingType = DrawingType.Fade;

        mPointMemory = 300;
        mFieldDrawer = new FieldDrawer(aFieldProps);
        
        mSinglePointDrawer = new SinglePointDrawer(mFieldDrawer);
        mRainbowPointDrawer = new RainbowPointDrawer(mFieldDrawer);
        mFadedPointDrawer = new FadedPointDrawer(mFieldDrawer);
        mAllPointDrawer = new AllPointDrawer(mFieldDrawer);
        mRobotPointDrawer = new RobotPointDrawer(mFieldDrawer);
        
        mAutonPointDrawer = new SimpleAutonPointDrawer(mFieldDrawer);
        
        mFieldDrawer.setPointDrawer(mFadedPointDrawer);
        mFieldDrawer.setAutonPointDrawer(mAutonPointDrawer);
        
        setLayout(new BorderLayout());
        add(mFieldDrawer);
        
        JPanel panel = new JPanel();
        add(panel, BorderLayout.SOUTH);
        
        JLabel lblX = new JLabel("X");
        panel.add(lblX);
        
        mXField = new JTextField();
        panel.add(mXField);
        mXField.setColumns(5);
        
        JLabel lblY = new JLabel("Y");
        panel.add(lblY);
        
        mYField = new JTextField();
        panel.add(mYField);
        mYField.setColumns(5);
        
        JLabel lblAngle = new JLabel("Angle");
        panel.add(lblAngle);
        
        mAngleField = new JTextField();
        mAngleField.setColumns(5);
        panel.add(mAngleField);
        addComponentListener(mResizeListener);
    }

    
    /**
     * Sets the drawing type for displaying coordinate locations
     * @param aType The drawing type
     */
    public void setDrawingType(DrawingType aType)
    {
        mDrawingType = aType;
        System.out.println("Drawingtype = " + aType);
        
        switch(mDrawingType)
        {
        case DrawAll:
            mFieldDrawer.setPointDrawer(mAllPointDrawer);
            break;
        case Fade:
            mFieldDrawer.setPointDrawer(mFadedPointDrawer);
            break;
        case Rainbow:
            mFieldDrawer.setPointDrawer(mRainbowPointDrawer);
            break;
        case SinglePoint:
            mFieldDrawer.setPointDrawer(mSinglePointDrawer);
            break;
        case Robot:
            mFieldDrawer.setPointDrawer(mRobotPointDrawer);
            break;
        }
    }
    
    public DrawingType getDrawingType()
    {
        return mDrawingType;
    }

    
    /**
     * Sets the number of points to draw when the drawing mode is Fade
     * @param value The number of points to use in fade mode
     */
    public void setPointMemory(Integer value)
    {
        double dVal = value;
        setPointMemory(dVal);
    }
    public void setPointMemory(Double value)
    {
        System.out.println("Setting point memeory : " + value);
        mPointMemory = value;
        mFadedPointDrawer.setPointMemory(mPointMemory);
        mRainbowPointDrawer.setPointMemory(mPointMemory);
    }
    
    public double getPointMemory()
    {
        return mPointMemory;
    }

    public void setDrawAuton(boolean aDrawAuton) {
        mFieldDrawer.setDrawAuton(aDrawAuton);
    }

    public boolean getDrawAuton() {
        return mFieldDrawer.getDrawAuton();
    }

    public FieldDrawer getFieldDrawer() {
        return mFieldDrawer;
    }

    public void clearPoints() {
        mFieldDrawer.clearPoints();
    }


    public void addPoint(Coordinate aCoord) {
        mFieldDrawer.addPoint(aCoord);

        mXField.setText(    String.format("%.2f", aCoord.x));
        mYField.setText(    String.format("%.2f", aCoord.y));
        mAngleField.setText(String.format("%.2f", aCoord.angle));
    }


    public void updateScale() {
        mFieldDrawer.updateScale();
    }


    public void setAutonPoints(List<AutonPoint> aPoints) {
        mFieldDrawer.setAutonPoints(aPoints);
    }


    public Color getPointColor() {
        return mDefaultColor;
    }


    public void setPointColor(Color aColor) {
        mDefaultColor = aColor;
        

        mSinglePointDrawer.setColor(aColor);
        mFadedPointDrawer.setColor(aColor);
        mAllPointDrawer.setColor(aColor);
        
    }

    
    /**
     * Listens for when the panel is resized so the scall can be updated.
     */
    private ComponentListener mResizeListener = new ComponentAdapter() {

        @Override
        public void componentResized(ComponentEvent e)
        {
            updateScale();
//            System.out.println("Resizing " + getSize());
        }
    };
    private JTextField mXField;
    private JTextField mYField;
    private JTextField mAngleField;

    public void drawTurningPoint(double x, double y) {
        mFieldDrawer.drawTurningPoint(x, y);
    }
    public void clearTurningPoint() {
        mFieldDrawer.clearTurningPoint();
    }
}
