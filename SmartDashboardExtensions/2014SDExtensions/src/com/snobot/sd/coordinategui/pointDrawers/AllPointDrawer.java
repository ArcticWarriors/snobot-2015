package com.snobot.sd.coordinategui.pointDrawers;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import com.snobot.sd.coordinategui.Coordinate;


public class AllPointDrawer extends APointDrawer
{
    private Color mPointColor;
    
    public AllPointDrawer(FeetToPixelConverter aConverter)
    {
        super(aConverter);
        mPointColor = Color.green;
    }

    @Override
    public void drawPoints(Graphics g, List<Coordinate> aCoordinates) {

        g.setColor(mPointColor);
        for (int i = 0; i < aCoordinates.size(); ++i)
        {
            drawCoordinate(g, aCoordinates.get(i));
        }
    }


    public void setColor(Color aColor) {
        mPointColor = aColor;
    }
}
