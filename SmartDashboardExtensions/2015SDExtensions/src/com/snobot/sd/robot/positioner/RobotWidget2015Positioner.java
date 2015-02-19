package com.snobot.sd.robot.positioner;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class RobotWidget2015Positioner extends JPanel
{
    /**
     * 
     */
    private static final long serialVersionUID = 616523100310426790L;
    private BufferedImage mFieldImage;
    private BufferedImage mRobotImage;
    private Dimension mRobotImageDimension;
    private double mScale;

    private double mRobotX;
    private double mRobotY;
    private double mRobotHeading;

    public RobotWidget2015Positioner() {

        mFieldImage = readFieldImage("field.png");
        mRobotImage = readFieldImage("robot_edited.png");

        if (mFieldImage != null) {
            setPreferredSize(new Dimension(mFieldImage.getWidth(), mFieldImage.getHeight()));
        }
        if (mRobotImage != null){
            setPreferredSize(new Dimension(mRobotImage.getWidth(), mRobotImage.getHeight()));
        }
        updateScale();
    }

    private static final double FIELD_WIDTH_INCH = 432;
    private static final double FIELD_HEIGHT_INCH = 360;

    private static final double ROBOT_WIDTH_INCH = 27.5;
    private static final double ROBOT_HEIGHT_INCH = 58.5;

    public void updateScale() {
        int width = this.getWidth();
        int height = this.getHeight();

        double widthScale = width / FIELD_WIDTH_INCH;
        double heightScale = height / FIELD_HEIGHT_INCH;

        System.out.println("Update scale : " + width + ", " + height + ", ws = " + widthScale + ", hs = " + heightScale);

        if (widthScale < heightScale) {
            mScale = widthScale;
        }
        else if (widthScale > heightScale) {
            mScale = heightScale;
        }

        repaint();
    }

    private BufferedImage readFieldImage(String aFilename) {

        BufferedImage output = null;
        // String filename = "field.png";
        try {
            InputStream in = getClass().getResourceAsStream(aFilename);

            // Image exists
            if (in != null) {
                output = ImageIO.read(in);

                if (output == null) {
                    System.out.println("Could not open image file : " + aFilename);
                }

                updateScale();
            }
            else {
                System.out.println("Could not find image file : '/2015SDExtensions/src/com/snobot" + "/sd/robot/positioner/field.png'");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.err.println(ex);
        }

        return output;
    }

    public void paint(Graphics g) {
        super.paint(g);

        if (mFieldImage != null) {
            g.drawImage(mFieldImage, 0, 0, (int) (FIELD_WIDTH_INCH * mScale), (int) (FIELD_HEIGHT_INCH * mScale), null);
            g.drawImage(mRobotImage, (int) mRobotX, (int) mRobotY, (int) (ROBOT_WIDTH_INCH * mScale), (int) (ROBOT_HEIGHT_INCH * mScale), null);
        }
    }

    public void setRobotPosition(double aRobotX, double aRobotY, double aRobotHeading) {
        mRobotX = aRobotX;
        mRobotY = aRobotY;
        mRobotHeading = aRobotHeading;
    }

    public void updateSize()
    {
        // TODO Auto-generated method stub

    }
}
