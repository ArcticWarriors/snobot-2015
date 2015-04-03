package com.snobot.sd2014.coordinategui.pointDrawers;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import com.snobot.sd2014.coordinategui.Coordinate;


public class FadedPointDrawer extends APointDrawer
{
    private Color mPointColor;
    private double mPointMemory;
    
    public FadedPointDrawer(FeetToPixelConverter aConverter)
    {
        super(aConverter);
        mPointColor = Color.green;
        mPointMemory = 1;
    }
    
    public void setPointMemory(double aPointMemory)
    {
        mPointMemory = aPointMemory;
    }

    @Override
    public void drawPoints(Graphics g, List<Coordinate> aCoordinates) {

        int size = aCoordinates.size();        
        int lMin = (int) (size - mPointMemory);
        if(lMin < 0)
        {
            lMin = 0;
        }
        
        for (int i = size-1; i >= lMin; --i)
        {
            double lSubtract = (255 - (i + 1 - lMin) / mPointMemory * 255);
            int alpha = (int) (mPointColor.getAlpha() - lSubtract);
            
            g.setColor(new Color(mPointColor.getRed(), mPointColor.getGreen(), mPointColor.getBlue(), alpha));
            drawCoordinate(g, aCoordinates.get(i));
        }
    }

    public void setColor(Color aColor) {
        mPointColor = aColor;
    }
}
