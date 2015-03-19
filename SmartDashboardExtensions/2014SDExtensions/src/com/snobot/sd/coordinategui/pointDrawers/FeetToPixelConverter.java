package com.snobot.sd.coordinategui.pointDrawers;

import java.awt.Point;
import java.awt.geom.Point2D;

public interface FeetToPixelConverter
{

    public abstract Point convertPoint(Point2D aPoint);
    public abstract int convertRadius(double aRadius);
}
