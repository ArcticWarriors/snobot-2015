package com.snobot.sd.coordinategui.pointDrawers;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.List;

import com.snobot.sd.coordinategui.AutonPoint;

public class SimpleAutonPointDrawer extends AutonPointDrawer
{

    protected int mDotSize;
    protected FeetToPixelConverter mConverter;

    public SimpleAutonPointDrawer(FeetToPixelConverter aConverter)
    {
        mDotSize = 4;
        mConverter = aConverter;
    }
    
    /**
     * Draws an autonomous point.  Draws a circle for the deadband level
     * around the point, as well as the point
     * @param g 
     */
    public void drawPoints(Graphics g, List<AutonPoint> aAutonPoints)
    {
        g.setColor(mAutonDbColor);
        for(AutonPoint c : aAutonPoints)
        {

            Point center = mConverter.convertPoint(new Point2D.Double(c.x, -c.y));
            
            int xPixels = center.x;
            int yPixels = center.y;
//            
            int radius = mConverter.convertRadius(c.deadband);
            int dbX = (int) ( xPixels) - radius;
            int dbY = (int) ( yPixels) - radius;
//            
            g.fillOval(dbX , dbY , radius * 2 , radius * 2);
        }
        
        
        g.setColor(mAutonColor);
        for(AutonPoint c : aAutonPoints)
        {
            Point center = mConverter.convertPoint(new Point2D.Double(c.x, -c.y));
            
            int x = center.x - mDotSize / 2;
            int y = center.y - mDotSize / 2;
            g.fillOval(x , y , mDotSize , mDotSize);
        }
    }
}
