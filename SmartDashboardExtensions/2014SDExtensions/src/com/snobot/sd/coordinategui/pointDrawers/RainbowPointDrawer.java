package com.snobot.sd.coordinategui.pointDrawers;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import com.snobot.sd.coordinategui.Coordinate;
import com.snobot.sd.util.Util;

public class RainbowPointDrawer extends APointDrawer
{
    private double mPointMemory;
    
    public RainbowPointDrawer(FeetToPixelConverter aConverter)
    {
        super(aConverter);
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
            int alpha = (int) (255 - lSubtract);
            Color c = new Color(Color.HSBtoRGB(i/255.0f, 1.0f, 1.0f));
            g.setColor(Util.alphaColor(c, alpha));

            drawCoordinate(g, aCoordinates.get(i));
        }
    }

    

}
