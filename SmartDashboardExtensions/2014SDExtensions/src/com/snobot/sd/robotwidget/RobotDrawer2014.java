/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.snobot.sd.robotwidget;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import com.snobot.sd.util.Util;

/**
 *
 * @author Alex Wong
 */
public class RobotDrawer2014 extends JPanel
{
	private static final long serialVersionUID = 1030593494019986766L;

    private static final int sFRAME_X = 0;
    private static final int sFRAME_Y = 0;
    
    private static final double sROBOT_LENGTH = 28;
    private static final double sROBOT_HEIGHT = 9;
    private static final double sLONGEST_DIM=2.1*(Math.sqrt(sROBOT_HEIGHT*sROBOT_HEIGHT + sROBOT_LENGTH*sROBOT_LENGTH));
    private static final double sROBOT_END = sFRAME_X - sROBOT_LENGTH;
          
    private static final double sSHOOTER_HINGE_HEIGHT = 32;
    private static final double sSHOOTER_HINGE_WIDTH = 1;
    private static final double sSHOOTER_FRAME_HEIGHT = 24;
    private static final double sSHOOTER_FRAME_WIDTH = 1;
    
    private static final double sHARVESTOR_HINGE_HEIGHT = 24;
    private static final double sHARVESTOR_HINGE_WIDTH = 1;
    private static final double sHARVESTER_POINT_Y = sFRAME_Y; 
    private static final double sHARVESTOR_POINT_X = sROBOT_END;

    private static final double sHARVESTOR_IN_ANGLE = 0;
    private static final double sHARVESTOR_OUT_ANGLE = 45;

    private static final double sSHOOTER_DOWN_ANGLE = 0;
    private static final double sSHOOTER_UP_ANGLE = 260;

    private static final double sTRANSLATE_X_SCALER = 5;
    private static final double sTRANSLATE_Y_SCALER = 50;
    
    //Colors used to draw the components
    private static final Color sBACKGROUND_COLOR   = Color.black;
    private static final Color sBASE_COLOR         = Color.black;
    private static final Color sSHOOTER_ARM_COLOR  = new Color(0x0067e7);
    private static final Color sSHOOTER_BACK_COLOR = new Color(0xe70068);
    private static final Color sHARVESTOR_COLOR    = new Color(0xe77f00);
    

	private double mScaling_Factor;
    private double mTranslateX;
    private double mTranslateY;
    
    private Dimension mDimension=new Dimension(400,400);
            
    private double mHarvestorAngle;
    private double mShooterAngle;
    
    //Super-structure information, updated from the model
    private boolean mArmUp;
    private boolean mShooterUp;
    private double mRollerSpeed;
    
    public RobotDrawer2014()
    {
        mArmUp = true;
        mShooterUp = false;
        mRollerSpeed = 0;

        setBackground(sBACKGROUND_COLOR);
        updateSize();
        setPreferredSize(new Dimension(300,300));
        
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
		
        //Scaling_Factor = (minDimensionSize / 80);
        mScaling_Factor = (minDimensionSize / sLONGEST_DIM);
        int size = (int) (sLONGEST_DIM * mScaling_Factor) + 5;
        
        mDimension = new Dimension(size, size);
        mTranslateY=sTRANSLATE_Y_SCALER * mScaling_Factor;
        mTranslateX=sTRANSLATE_X_SCALER * mScaling_Factor;
        repaint();
    }
    
    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        super.paint(g);
        
        if (mArmUp){
            mHarvestorAngle=sHARVESTOR_IN_ANGLE;
        }
        else{
           mHarvestorAngle=sHARVESTOR_OUT_ANGLE;
       }

        if (mShooterUp){
            mShooterAngle=sSHOOTER_UP_ANGLE;
        }
        else{
            mShooterAngle=sSHOOTER_DOWN_ANGLE;
        }
      
        
        g.clearRect(0,0,(int) mDimension.getWidth(),(int) mDimension.getHeight());
        drawBase(g2d);
        drawShooter(g2d);
        drawShooterArm(g2d);
        drawHarvester(g2d);
        drawAxle(g2d);
        
    }
    
    protected void drawHarvester(Graphics2D g2d)
    {
        Rectangle2D harvester = new Rectangle2D.Double(sHARVESTOR_POINT_X,sHARVESTER_POINT_Y,sHARVESTOR_HINGE_WIDTH, sHARVESTOR_HINGE_HEIGHT);
        AffineTransform transform = new AffineTransform();
        transform.translate(mTranslateX, mTranslateY);
        transform.scale(mScaling_Factor, mScaling_Factor);
        transform.rotate(Math.toRadians(180));  
        transform.rotate(Math.toRadians(mHarvestorAngle),sHARVESTOR_POINT_X,sHARVESTER_POINT_Y);  
        
        Shape shape = transform.createTransformedShape(harvester);
        g2d.setColor(sHARVESTOR_COLOR);
        g2d.fill(shape);
        
    }        
    
    protected void drawAxle(Graphics2D g2d)
    {
        Ellipse2D base = new Ellipse2D.Double(sROBOT_END-.5,sHARVESTOR_HINGE_HEIGHT-2,2,2);
        AffineTransform transform = new AffineTransform();
        transform.translate(mTranslateX, mTranslateY);
        transform.scale(mScaling_Factor, mScaling_Factor);
        transform.rotate(Math.toRadians(180));
        transform.rotate(Math.toRadians(mHarvestorAngle), sHARVESTOR_POINT_X,sHARVESTER_POINT_Y);
        
        Shape shape = transform.createTransformedShape(base);
        g2d.setColor(Util.getMotorColor(mRollerSpeed));
        g2d.fill(shape);
    }
    
    protected void drawShooter(Graphics2D g2d)
    {
        Rectangle2D base = new Rectangle2D.Double(sFRAME_X, sFRAME_Y, sSHOOTER_FRAME_WIDTH, sSHOOTER_FRAME_HEIGHT);        
        
        AffineTransform transform = new AffineTransform();
        transform.translate(mTranslateX, mTranslateY);
        transform.scale(mScaling_Factor, mScaling_Factor);
        transform.rotate(Math.toRadians(180));
        
        Shape shape = transform.createTransformedShape(base);
        g2d.setColor(sSHOOTER_BACK_COLOR);
        g2d.fill(shape);
    }
    
    protected void drawShooterArm(Graphics2D g2d){
        Rectangle2D shooter = new Rectangle2D.Double(15,-13,sSHOOTER_HINGE_WIDTH,sSHOOTER_HINGE_HEIGHT);
        AffineTransform transform = new AffineTransform();
        transform.translate(mTranslateX, mTranslateY);
        transform.scale(mScaling_Factor, mScaling_Factor);
        transform.rotate(Math.toRadians(308));
        transform.rotate(Math.toRadians(mShooterAngle),15,-13);
        
        Shape shape = transform.createTransformedShape(shooter);
        g2d.setColor(sSHOOTER_ARM_COLOR);
        g2d.fill(shape);  
    }
    
    protected void drawBase(Graphics2D g2d)
    {
        Rectangle2D base = new Rectangle2D.Double(sFRAME_X, sFRAME_Y, sROBOT_LENGTH, sROBOT_HEIGHT);
        
        AffineTransform transform = new AffineTransform();
        transform.translate(mTranslateX, mTranslateY);
        transform.scale(mScaling_Factor, mScaling_Factor);
        
        Shape shape = transform.createTransformedShape(base);
        g2d.setColor(sBASE_COLOR);
        g2d.fill(shape);
    }   
    
    public void setArmUp(boolean aArmUp)
    {
        mArmUp = aArmUp;
    }
    
    public void setShooterUp(boolean aShooterUp)
    {
        mShooterUp = aShooterUp;
    }
    
    public void setRollerSpeed(double aRollerSpeed) 
    {
            mRollerSpeed = aRollerSpeed;
    }
    
    
    public boolean isArmUp()
    {
        return mArmUp;
    }
    
    public boolean isShooterUp()
    {
        return mShooterUp;
    }
    
    public double getRollerSpeed() 
    {
        return mRollerSpeed;
    } 
    
    public String toString() 
	{
		return "RobotDrawer2014 [mArmUp=" + mArmUp + ", mShooterUp="
				+ mShooterUp + ", mRollerSpeed=" + mRollerSpeed + "]";
	}
}
