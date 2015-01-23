package com.snobot.sd.coordinategui.widget;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.List;

import com.snobot.sd.SDKeys;
import com.snobot.sd.coordinategui.AutonPoint;
import com.snobot.sd.coordinategui.AutonPointParser;
import com.snobot.sd.coordinategui.Coordinate;
import com.snobot.sd.coordinategui.FieldDrawerPanel;
import com.snobot.sd.coordinategui.FieldProperties;
import com.snobot.sd.coordinategui.FieldDrawerPanel.DrawingType;
import com.snobot.sd.util.AutoUpdateWidget;

import edu.wpi.first.smartdashboard.properties.ColorProperty;
import edu.wpi.first.smartdashboard.properties.IntegerProperty;
import edu.wpi.first.smartdashboard.properties.MultiProperty;
import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.smartdashboard.properties.StringProperty;
import edu.wpi.first.smartdashboard.robot.Robot;

public abstract class BaseCoordinateWidget extends AutoUpdateWidget
{

    /**
     * 
     */
    private static final long serialVersionUID = 3668444816215597158L;
    
    private static final String sFadePropertyText    = "Fade Over Time";
    private static final String sAllPropertyText     = "All Points";
    private static final String sSinglePropertyText  = "Single Point";
    private static final String sRainbowPropertyText = "NYANCAT LOLOLOL";
    private static final String sRobotPropertyText = "Robot";
    
    private IntegerProperty mPointMemory;
    private ColorProperty mPointColor;
    private MultiProperty mModeProperty;
    private StringProperty mClearProperty;
    private StringProperty mDrawAutonProperty;
    
    private FieldDrawerPanel mPanel;

    public BaseCoordinateWidget(boolean aDebug, FieldProperties aFieldProperties) {
        super(aDebug);
        
        mPanel = new FieldDrawerPanel(aFieldProperties);

        
        mPointMemory = new IntegerProperty(this, "Point Memory", 100);
        mPointColor =  new ColorProperty(this, "Point Color", Color.magenta);
        mModeProperty = new MultiProperty(this, "Drawing Mode");
        mClearProperty = new StringProperty(this, "Clear Points");
        mDrawAutonProperty = new StringProperty(this, "Draw Auton");
        
        mModeProperty.add(sAllPropertyText, "All");
        mModeProperty.add(sFadePropertyText, "Fade");
        mModeProperty.add(sRainbowPropertyText, "LOLOL NYANCAT");
        mModeProperty.add(sSinglePropertyText, "Single");
        mModeProperty.add(sRobotPropertyText, "Robot");
    
        mPanel.setDrawAuton(true);
        
        setLayout(new BorderLayout());
        add(mPanel, BorderLayout.CENTER);
        
        addComponentListener(mResizeListener);
    }


    
    /**
     * Called when the drawing mode has been updated.  Updates the drawing panel
     */
    private void handleDrawingMode()
    {
        String text = mModeProperty.getValue().toString();
        System.out.println("Handling drawing mode... : '" + text + "'");

        for(String key : FieldDrawerPanel.sDRAWING_TYPE_MAP.keySet())
        {
            System.out.println(" Checking agains '" + key + "'");
            if(text.equals(key))
            {
                System.out.println("  GOT IT");
                mPanel.setDrawingType(FieldDrawerPanel.sDRAWING_TYPE_MAP.get(key));
                break;
            }
        }
    }


    @Override
    public void init()
    {
        revalidate();
        repaint();
    }

    @Override
    public void propertyChanged(Property property)
    {
        System.out.println("Property changed... " + property );
        if (property == mPointMemory)
        {
            mPanel.setPointMemory(mPointMemory.getValue());
        } 
        else if(property == mPointColor)
        {
//            mPanel.setPointColor(mPointColor.getValue());
        }
        else if(property == mModeProperty)
        {
            handleDrawingMode();
        }
        else if(property == mClearProperty)
        {
            String text = mClearProperty.getValue();
            if( !(text.equals("False") || text.equals("0")) )
            {
                mPanel.clearPoints();
            }
        }
        else if(property == mDrawAutonProperty)
        {
            String text = mDrawAutonProperty.getValue();
            if( text.equals("False") || text.equals("0"))
            {
                mPanel.setDrawAuton(false);
            }
            else
            {
                mPanel.setDrawAuton(true);
            }
        }
    }
    
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        
        Coordinate c = new Coordinate();
        c.x = Robot.getTable().getNumber(SDKeys.sDISTANCE_X_SD_KEY, 0);
        c.y = Robot.getTable().getNumber(SDKeys.sDISTANCE_Y_SD_KEY, 0);
        c.angle = Robot.getTable().getNumber(SDKeys.sANGLE_KEY, 0);
        mPanel.addPoint(c);            


        double turningPointX = Robot.getTable().getNumber("TP_X", Double.NaN);
        double turningPointY = Robot.getTable().getNumber("TP_Y", Double.NaN);
        if(turningPointX != Double.NaN && turningPointY != Double.NaN)
        {
            mPanel.drawTurningPoint(turningPointX, turningPointY);
        }
        else
        {
            mPanel.clearTurningPoint();
        }
        
        String newAutonText = Robot.getTable().getString(SDKeys.sAUTON_COMMANDS_KEY, "");
//        if(!newAutonText.equals(mLastAutonText))
        {
            List<AutonPoint> autonPoints = AutonPointParser.readPoints(newAutonText);
            mPanel.setAutonPoints(autonPoints);
        }  
    }
    
    
    /**
     * Resize listener.  Updates the scale of field panel when the panel has been
     * resized
     */
    private ComponentListener mResizeListener = new ComponentAdapter() 
    {
        @Override
        public void componentResized(ComponentEvent e)
        {
          mPanel.updateScale();
          System.out.println("Size updated");
        }
    };
}
