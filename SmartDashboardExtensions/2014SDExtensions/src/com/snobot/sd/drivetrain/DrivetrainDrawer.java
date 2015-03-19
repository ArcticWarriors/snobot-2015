package com.snobot.sd.drivetrain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

import com.snobot.sd.util.Util;

public class DrivetrainDrawer extends JPanel
{
    private static final long serialVersionUID = 8780079810598726762L;
    
    protected static final double sDrivetrainWidth = 28;	//Inches
    protected static final double sDrivetrainLength = 28;	//Inches
    protected static final double sLongestDim = Math.sqrt(sDrivetrainWidth * sDrivetrainWidth + sDrivetrainLength * sDrivetrainLength); 
    
    protected static final double sWheelDiameter = 6;		//Inches
    protected static final double sWheelInset_x = 2;		//Inches
    protected static final double sWheelInset_y = 2;		//Inches

    protected static final double sRobotDirSize_inches = 1;	
    
    protected static final double sRobotCenterX = sDrivetrainWidth / 2;
    protected static final double sRobotCenterY = sDrivetrainLength / 2;
    
    protected static final Color mRobotColor = new Color(0x0066ff);
    protected static final Color sFRONT_INDICATOR = Color.black;
    
    protected double mPixelsPerInch = 15;
    protected double mWheelWidth_px = mPixelsPerInch/2;
    protected double mWheelDirSize_px = mWheelWidth_px * 2;	
   	
    protected double mPortFrontAngle, mPortBackAngle, mStarFrontAngle, mStarBackAngle;
    protected double mPortFrontSteeringSpeed, mPortBackSteeringSpeed, mStarFrontSteeringSpeed, mStarBackSteeringSpeed;
    protected double mPortFrontDriveSpeed, mPortBackDriveSpeed, mStarFrontDriveSpeed, mStarBackDriveSpeed;
    protected double mRobotAngle;
    protected Dimension mDimension;
    protected Point2D mRotationPoint;

    public DrivetrainDrawer()
    {
    	setBackground(Color.black);
        updateSize();
        setPreferredSize(mDimension);

        addComponentListener(new ComponentAdapter() {
    		@Override
    		public void componentResized(ComponentEvent arg0) {
    			updateSize();
    		}
    	});
    }
    
    public void updateSize()
    {
		double minDimensionSize = Math.min(getSize().getWidth(), getSize().getHeight());
		
		mPixelsPerInch = (minDimensionSize / sLongestDim);
	    mWheelWidth_px = mPixelsPerInch/2.0;
	    mWheelDirSize_px = mWheelWidth_px * 2.0;

        int size = (int) (sLongestDim * mPixelsPerInch) + 5;
        mDimension = new Dimension(size, size);
        mRotationPoint = new Point2D.Double( size/2, size/2);

        repaint();
        
    }

    public void updateRobotAngle(double aAngle)
    {
        mRobotAngle = aAngle;
    }

    public void updateWheelAngle(double aPortFrontAngle, double aPortBackAngle, double aStarFrontAngle, double aStarBackAngle)
    {
        mPortFrontAngle = aPortFrontAngle;
        mPortBackAngle 	= aPortBackAngle;
        mStarFrontAngle = aStarFrontAngle;
        mStarBackAngle 	= aStarBackAngle;
        repaint();
    }

    public void updateWheelSpeed(double aPortFrontSpeed, double aPortBackSpeed, double aStarFrontSpeed, double aStarBackSpeed)
    {
        mPortFrontSteeringSpeed = aPortFrontSpeed;
        mPortBackSteeringSpeed 	= aPortBackSpeed;
        mStarFrontSteeringSpeed = aStarFrontSpeed;
        mStarBackSteeringSpeed 	= aStarBackSpeed;
    }

    public void updateDriveSpeed(double aPortFrontSpeed, double aPortBackSpeed, double aStarFrontSpeed, double aStarBackSpeed)
    {
        mPortFrontDriveSpeed = aPortFrontSpeed;
        mPortBackDriveSpeed  = aPortBackSpeed;
        mStarFrontDriveSpeed = aStarFrontSpeed;
        mStarBackDriveSpeed  = aStarBackSpeed;
    }

    @Override
    public void paint(Graphics g)
    {
    	super.paint(g);
        g.clearRect(0, 0, (int) mDimension.getWidth(), (int) mDimension.getHeight());

        drawRobot(g);
        drawWheels(g);
    }

    private void drawWheels(Graphics g)
    {
        double leftInset 	= (sLongestDim - sDrivetrainWidth + sWheelInset_x + sWheelDiameter) / 2;
        double topInset 	= (sLongestDim - sDrivetrainLength + sWheelInset_y) / 2;
        double rightInset 	= (sLongestDim + sDrivetrainWidth  - (sWheelInset_x + sWheelDiameter) )/2 ;
        double botInset 	= (topInset + sDrivetrainLength - sWheelInset_y) - sWheelDiameter;

        drawWheel(g, mPortFrontAngle, mPortFrontSteeringSpeed, mPortFrontDriveSpeed, leftInset,  topInset);
        drawWheel(g, mPortBackAngle,  mPortBackSteeringSpeed,  mPortBackDriveSpeed,  leftInset,  botInset);
        drawWheel(g, mStarFrontAngle, mStarFrontSteeringSpeed, mStarFrontDriveSpeed, rightInset, topInset);
        drawWheel(g, mStarBackAngle,  mStarBackSteeringSpeed,  mStarBackDriveSpeed,  rightInset, botInset);
    }
    

    private void drawWheel(Graphics g, double aAngle, double aSteeringSpeed, double aDrivingSpeed, double left, double top)
    {
        g.setColor(Util.getMotorColor(aSteeringSpeed));

        Rectangle2D rectangle = new Rectangle2D.Double(0, 0, mWheelWidth_px, sWheelDiameter * mPixelsPerInch);

        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(mRobotAngle), mRotationPoint.getX(), mRotationPoint.getY());
        transform.translate(left  * mPixelsPerInch, top  * mPixelsPerInch);
        transform.rotate(Math.toRadians(aAngle), rectangle.getX() + rectangle.getWidth() / 2, rectangle.getY() + rectangle.getHeight() / 2);
        
        
        Shape shape = transform.createTransformedShape(rectangle);
        ((Graphics2D) g).fill(shape);
        
        //Draw wheel direction box
        g.setColor(Util.getMotorColor(aDrivingSpeed));
        Rectangle2D dir = new Rectangle2D.Double(-mWheelDirSize_px/5, 0, mWheelDirSize_px, mWheelDirSize_px);
        shape = transform.createTransformedShape(dir);
        ((Graphics2D) g).fill(shape);
        
        drawVectorLine(g, rectangle, aAngle, left, top);
    }
    
    private void drawVectorLine(Graphics g, Rectangle2D rectangle, double aAngle, double left, double top)
    {
        if(left == (sLongestDim - sDrivetrainWidth + sWheelInset_x + sWheelDiameter) / 2)
        {
            return;
        }
        Line2D line = new Line2D.Double(0, -100, 0, 100);

        AffineTransform transform = new AffineTransform();
        transform.translate(left  * mPixelsPerInch, top  * mPixelsPerInch);
//        transform.translate(mWheelWidth_px, sWheelDiameter * mPixelsPerInch);
        transform.rotate(Math.toRadians(aAngle + 90), rectangle.getX() + rectangle.getWidth() / 2, rectangle.getY() + rectangle.getHeight() / 2);
        transform.scale(10, 10);

        g.setColor(Color.red);
        Shape shape = transform.createTransformedShape(line);
        ((Graphics2D) g).draw(shape);
    }

    
    private void drawRobot(Graphics g)
    {
        g.setColor(mRobotColor);

        Rectangle2D rectangle = new Rectangle2D.Double( (sLongestDim - sDrivetrainWidth)/2, (sLongestDim - sDrivetrainLength)/2, sDrivetrainWidth, sDrivetrainLength);

        Point2D ltmp = new Point2D.Double(
                (rectangle.getX() + rectangle.getWidth() / 2),
                (rectangle.getY() + rectangle.getHeight() / 2));
        
        AffineTransform transform = new AffineTransform();
        transform.scale(mPixelsPerInch, mPixelsPerInch);
        transform.rotate(Math.toRadians(mRobotAngle), ltmp.getX(), ltmp.getY());

        Shape shape = transform.createTransformedShape(rectangle);
        ((Graphics2D) g).fill(shape);

        //Draw robot direction box
        Rectangle2D dir = new Rectangle2D.Double((sLongestDim - sDrivetrainWidth)/2 + sDrivetrainWidth/2, (sLongestDim - sDrivetrainLength)/2, 
        		sRobotDirSize_inches, sRobotDirSize_inches);
        shape = transform.createTransformedShape(dir);
        g.setColor(sFRONT_INDICATOR);
        ((Graphics2D) g).fill(shape);

    }
    
    
}
