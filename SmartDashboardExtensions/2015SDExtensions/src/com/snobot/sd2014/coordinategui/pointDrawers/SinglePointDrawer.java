package com.snobot.sd2014.coordinategui.pointDrawers;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import com.snobot.sd2014.coordinategui.Coordinate;


public class SinglePointDrawer extends APointDrawer
{
    private Color mPointColor;
    
    public SinglePointDrawer(FeetToPixelConverter aConverter)
    {
        super(aConverter);
        mPointColor = Color.green;
    }

    @Override
    public void drawPoints(Graphics g, List<Coordinate> aCoordinates) {

        int lastIndex = aCoordinates.size() - 1;
        if(lastIndex >= 0)
        {
            g.setColor(mPointColor);
            drawCoordinate(g, aCoordinates.get(lastIndex));

        }
    }

    public void setColor(Color aColor) {
        mPointColor = aColor;
    }

}
