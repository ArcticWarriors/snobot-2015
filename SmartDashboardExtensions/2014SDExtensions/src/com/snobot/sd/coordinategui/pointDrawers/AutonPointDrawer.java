package com.snobot.sd.coordinategui.pointDrawers;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import com.snobot.sd.coordinategui.AutonPoint;
import com.snobot.sd.util.Util;

public abstract class AutonPointDrawer
{
    protected Color mAutonColor;
    protected Color mAutonDbColor;
    
    public AutonPointDrawer()
    {

        mAutonColor = Color.black;
        mAutonDbColor = Util.alphaColor(Color.green, 50);
    }
    

    public abstract void drawPoints(Graphics g, List<AutonPoint> aAutonPoints);
    
    public static class NullAutonPointDrawer extends AutonPointDrawer
    {

        @Override
        public void drawPoints(Graphics g, List<AutonPoint> aAutonPoints) {
        }
        
    }
}
