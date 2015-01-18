package com.snobot.sd.coordinategui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.snobot.sd.coordinategui.pointDrawers.APointDrawer;
import com.snobot.sd.coordinategui.pointDrawers.AutonPointDrawer;
import com.snobot.sd.coordinategui.pointDrawers.FeetToPixelConverter;
import com.snobot.sd.coordinategui.pointDrawers.PointDrawer;
import com.snobot.sd.coordinategui.pointDrawers.SimpleAutonPointDrawer;
import com.snobot.sd.coordinategui.pointDrawers.SinglePointDrawer;

/**
 * This class is responsible for writing position coordinates and autonomous
 * desired points on a image of the field
 * @author Rich
 */
public class FieldDrawer extends JPanel implements FeetToPixelConverter
{
	private static final long serialVersionUID = -6639011412001859264L;
	
	private static final int sMAX_POINTS = 3000; //The list will be restricted to this may points
	
    private LinkedList<AutonPoint> mAutonPoints;
    private LinkedList<Coordinate> mCoordinates;
    private BufferedImage mFieldImage;
    private double mScale;
    private Dimension mImageDimension;
    private boolean mDrawAuton;

    private Coordinate mTurningPoint = null;
    
    private final PointDrawer mTurningPointDrawer;
    private PointDrawer mPointDrawer;
    private AutonPointDrawer mAutonPointDrawer;
    
    private FieldProperties mFieldProps;
    
    /**
     * Constructor.  Reads the field image file, sets up default values
     */
    public FieldDrawer(FieldProperties aFieldProps)
    {
        mFieldProps = aFieldProps;
        mCoordinates = new LinkedList<Coordinate>();
        mAutonPoints = new LinkedList<AutonPoint>();
        mImageDimension = new Dimension();
        mDrawAuton = true;
        mScale = 23.092592; //Default value based on expected size of image
        
        mPointDrawer = new PointDrawer.NullPointDrawer();
        mAutonPointDrawer = new AutonPointDrawer.NullAutonPointDrawer();
        mTurningPointDrawer = new SinglePointDrawer(this);

        setBackground(Color.green);
        readFieldImage();
    }
    
    public void setDrawAuton(boolean aDrawAuton)
    {
        mDrawAuton = aDrawAuton;
        repaint();
    }
    
    public boolean getDrawAuton()
    {
    	return mDrawAuton;
    }
    
    /**
     * Reads the image file.  If it is not found nothing happens
     */
    private void readFieldImage()
    {
       try 
       {
          InputStream in = getClass().getResourceAsStream(mFieldProps.sFIELD_IMG_PATH);

          //Image exists
          if (in != null)
          {
            mFieldImage = ImageIO.read(in);
            
            if(mFieldImage != null)
            {
                setPreferredSize(new Dimension(mFieldImage.getWidth(), mFieldImage.getHeight()));
            }
            else
            {
                System.out.println("Could not open image file : '" + mFieldProps.sFIELD_IMG_PATH + "'");
            }
            updateScale();
          }
          else
          {
              System.out.println("Could not find image file : '" + mFieldProps.sFIELD_IMG_PATH + "'");
          }
       } 
       catch (Exception ex) 
       {
           System.err.println(ex);
       }
    }
    
    /**
     * Updates the scale for drawing.  Uses the width of the panel to determine
     * the aspect ratio and scale for feet->pixels
     */
    public void updateScale()
    {
        int width = getWidth();
        int height = getHeight();
        
        double widthScale = width / mFieldProps.sFIELD_WIDTH_FT;
        double heightScale = height / mFieldProps.sFIELD_HEIGHT_FT;
        
        if(widthScale < heightScale)
        {
            mScale = widthScale;
            mImageDimension.width = width;
            mImageDimension.height = (int) (mFieldProps.sFIELD_HEIGHT_FT * mScale);
        }
        else
        {
            mScale = heightScale;
            mImageDimension.width = (int) (mFieldProps.sFIELD_WIDTH_FT * mScale);
            mImageDimension.height = height;
        }

        repaint();
    }

    /**
     * Sets the points for autonomous locations
     * @param aPoints The autonomous points
     */
    public void setAutonPoints(List<AutonPoint> aPoints)
    {
        mAutonPoints = new LinkedList<AutonPoint>(aPoints);
    }
    
    public void addAutonPoint(AutonPoint aPoint)
    {
        mAutonPoints.add(aPoint);
    }
    
    /**
     * Adds a point to the coordinates to draw
     * @param aCoord The new point
     */
    public void addPoint(Coordinate aCoord)
    {
        mCoordinates.add(aCoord);
        trimPoints();
        repaint();
    }
    
    /**
     * Adds a collection for points to draw
     * @param aCoords The points
     */
    public void addPoints(Collection<Coordinate> aCoords)
    {
        mCoordinates.addAll(aCoords);
        trimPoints();
        repaint();
    }
    
    /**
     * Sets the points to draw, overwriting the old values.  Same effect as 
     * calling {@link #clearPoints() clearPoints} and {@link  #addPoints(java.util.Collection) addPoints }
     * @param aCoords The points
     */
    public void setPoints(Collection<Coordinate> aCoords)
    {
        mCoordinates = new LinkedList<Coordinate>(aCoords);
        trimPoints();
        repaint();
    }
    
    /**
     * Clears the coordinates to draw
     */
    public void clearPoints()
    {
        mCoordinates.clear();
        trimPoints();
        repaint();
    }
    
    private void trimPoints()
    {
        while(mCoordinates.size() > sMAX_POINTS)
        {
            mCoordinates.removeFirst();
        }
    }

    public void drawTurningPoint(double x, double y) {
        mTurningPoint = new Coordinate(x, y, 0);
    }
    public void clearTurningPoint() {
        mTurningPoint = null;
    }
    
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        if(mFieldImage != null)
        {
            g.drawImage(mFieldImage, 0, 0, mImageDimension.width, mImageDimension.height, null);
        }

        mPointDrawer.drawPoints(g, mCoordinates);

//        System.out.println(mPointDrawer);
        if(mDrawAuton)
        {           
            mAutonPointDrawer.drawPoints(g, mAutonPoints);
        }
        
        if(mTurningPoint != null)
        {
            mTurningPointDrawer.drawPoints(g, Arrays.asList(mTurningPoint));
            
        }
    }

    public void setPointDrawer(APointDrawer aPointDrawer) {
        mPointDrawer = aPointDrawer;
    }

    public void setAutonPointDrawer(SimpleAutonPointDrawer aPointDrawer) {
        mAutonPointDrawer = aPointDrawer;
    }

    /**
     * Gets the x-shift, in feet, to transpose the coordinates to the desired
     * (0,0) point
     * @return The X position, in feet of (0,0)
     */
    private double getXShift()
    {
        return mFieldProps.sFIELD_WIDTH_FT / 2;
    }
    
    /**
     * Gets the y-shift, in feet, to transpose the coordinates to the desired
     * (0,0) point
     * @return The Y position, in feet of (0,0)
     */
    private double getYShift()
    {
        return mFieldProps.sFIELD_HEIGHT_FT / 2;
//        return mFieldProps.sFIELD_HEIGHT_FT / 2;
    }
    
    @Override
    public Point convertPoint(Point2D aPoint) {
        int x = (int) ( (aPoint.getX() + getXShift()) * mScale);
        int y = (int) ( (aPoint.getY() + getYShift()) * mScale);
        
        return new Point(x, y);
    }

    @Override
    public int convertRadius(double aRadius) {
        return (int) (Math.ceil(aRadius * mScale));
    }
}
