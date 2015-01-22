package com.snobot.sd.coordinategui.pointDrawers;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;

import com.snobot.sd.coordinategui.Coordinate;


public abstract class APointDrawer implements PointDrawer
{
    protected int mDotSize;
    protected FeetToPixelConverter mConverter;
    
    public APointDrawer(FeetToPixelConverter aConverter)
    {
        mDotSize = 6;
        mConverter = aConverter;
    }

    /**
     * Draws a coordinate onto the field.  Assumes the color was already set
     * @param g The graphics to draw onto
     * @param c The coordinate to draw
     */
    protected void drawCoordinate(Graphics g, Coordinate c)
    {
        Point center = mConverter.convertPoint(new Point2D.Double(c.x, -c.y));
        
        int x = center.x - mDotSize / 2;
        int y = center.y - mDotSize / 2;
        g.fillOval(x , y , mDotSize, mDotSize);
    }
}
