package com.snobot.sd2014.coordinategui.widget;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import com.snobot.sd2014.coordinategui.Coordinate;
import com.snobot.sd2014.coordinategui.FieldDrawerPanel;
import com.snobot.sd2014.coordinategui.FieldProperties;
import com.snobot.sd2015.config.SmartDashboardNames;
import com.snobot.sd2015.util.AutoUpdateWidget;

import edu.wpi.first.smartdashboard.properties.ColorProperty;
import edu.wpi.first.smartdashboard.properties.IntegerProperty;
import edu.wpi.first.smartdashboard.properties.MultiProperty;
import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.smartdashboard.properties.StringProperty;
import edu.wpi.first.smartdashboard.robot.Robot;

public class BaseCoordinateWidget extends AutoUpdateWidget
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

    public BaseCoordinateWidget(boolean aDebug, FieldProperties aFieldProperties)
    {
        super(aDebug, 20);

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

    @Override
    protected void poll() throws Exception
    {
        Coordinate c = new Coordinate();
        c.x = Robot.getTable().getNumber(SmartDashboardNames.sSNOBOT_X_POSITION, 0) / 12.0;
        c.y = Robot.getTable().getNumber(SmartDashboardNames.sSNOBOT_Y_POSITION, 0) / 12.0;
        c.angle = Robot.getTable().getNumber(SmartDashboardNames.sSNOBOT_HEADING, 0);
        mPanel.addPoint(c);
    }
}
