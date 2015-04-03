package com.snobot.sd2014.coordinategui.pointDrawers;

import java.awt.Graphics;
import java.util.List;

import com.snobot.sd2014.coordinategui.Coordinate;


public interface PointDrawer
{

    public void drawPoints(Graphics g, List<Coordinate> aCoordinates);
    
    public static class NullPointDrawer implements PointDrawer
    {

        @Override
        public void drawPoints(Graphics g, List<Coordinate> aCoordinates) {
        }
        
    }
}
