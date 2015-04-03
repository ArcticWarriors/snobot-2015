package com.snobot.sd2014.coordinategui.pointDrawers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

import com.snobot.sd2014.coordinategui.Coordinate;


public class RobotPointDrawer extends APointDrawer
{
    private Color mPointColor;
    private Color mRobotColor;
    private int robotWidth = 40;
    private int robotHeight = 40;
    
    public RobotPointDrawer(FeetToPixelConverter aConverter)
    {
        super(aConverter);
        mPointColor = Color.green;
        mRobotColor = Color.blue;
    }

    @Override
    public void drawPoints(Graphics g, List<Coordinate> aCoordinates) {

        int lastIndex = aCoordinates.size() - 1;
        if(lastIndex < 0)
        {
            return;
        }
        
        Coordinate lastcoord = aCoordinates.get(lastIndex);
        
        
        Graphics2D g2 = (Graphics2D) g;
        drawRobot(g2, lastcoord);
        drawReferencePoint(g2, lastcoord);
    }
    
    private void drawRobot(Graphics2D g, Coordinate c)
    {
        Point center = mConverter.convertPoint(new Point2D.Double(c.x, -c.y));
        
        int robotCenter_x = center.x - robotWidth/2;
        int robotCenter_y = center.y - robotHeight/2;

        Rectangle2D robot = new Rectangle2D.Double(0, 0, robotWidth, robotHeight);

        int pivotX = robotCenter_x + robotWidth/2;
        int pivotY = robotCenter_y + robotHeight/2;
        
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(c.angle), pivotX, pivotY);
        transform.translate(robotCenter_x, robotCenter_y);

        Shape shape = transform.createTransformedShape(robot);
        g.setColor(mRobotColor);
        g.fill(shape);
    }
    
    private void drawReferencePoint(Graphics2D g, Coordinate c)
    {
        Point center = mConverter.convertPoint(new Point2D.Double(c.x, -c.y));

        double halfRobotHeight = robotHeight / 2;

        double dx = halfRobotHeight * Math.sin(Math.toRadians(c.angle)); 
        double dy = halfRobotHeight * Math.cos(Math.toRadians(c.angle));

        int pointX = (int) (center.x + dx - mDotSize / 2);
        int pointY = (int) (center.y - dy - mDotSize / 2);
        
        g.setColor(mPointColor);
        g.fillOval(pointX, pointY, mDotSize, mDotSize);
    }

    public void setColor(Color aColor) {
        mPointColor = aColor;
    }

}
