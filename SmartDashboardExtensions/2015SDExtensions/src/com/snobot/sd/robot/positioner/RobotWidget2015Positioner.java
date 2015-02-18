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
    // private Dimension mFieldImageDimension;
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
        // mFieldImageDimension = new Dimension();
        // this.readFieldImage();
        updateScale();
    }

    private static final double FIELD_WIDTH_INCH = 432;
    private static final double FIELD_HEIGHT_INCH = 360;

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

                // if (output != null) {
                // setPreferredSize(new Dimension(output.getWidth(),
                // output.getHeight()));
                // }
                // else {
                // System.out.println("Could not open image file : " +
                // filename);
                // }
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
        // try {
        // InputStream in =
        // getClass().getResourceAsStream("/2015SDExtensions/src/com/snobot" +
        // "/sd/robot/positioner/robot_edited.png");
        //
        // // Image exists
        // if (in != null) {
        // mRobotImage = ImageIO.read(in);
        //
        // if (mRobotImage != null) {
        // setPreferredSize(new Dimension(mFieldImage.getWidth(),
        // mFieldImage.getHeight()));
        // }
        // else {
        // System.out.println("Could not open image file : " +
        // "'/2015SDExtensions/src/com/snobot/sd/robot/positioner/robot_edited.png'");
        // }
        // updateScale();
        // }
        // else {
        // System.out.println("Could not find image file : " +
        // "'/2015SDExtensions/src/com/snobot/sd/robot/positioner/robot_edited.png'");
        // }
        // }
        // catch (Exception ex) {
        // System.err.println(ex);
        // }

        return output;
    }

    public void paint(Graphics g) {
        super.paint(g);

        if (mFieldImage != null) {
            g.drawImage(mFieldImage, 0, 0, (int) (FIELD_WIDTH_INCH * mScale), (int) (FIELD_HEIGHT_INCH * mScale), null);
        }
        // if (mRobotImage != null) {
        // g.drawImage(mRobotImage, (int) this.mRobotX, (int) this.mRobotY,
        // (int) (mFieldImageDimension.width * this.mScale),
        // (int) (mFieldImageDimension.height * this.mScale), null);
        // }
    }

    public void setRobotPosition(double aRobotX, double aRobotY, double aRobotHeading) {
        mRobotX = aRobotX;
        mRobotY = aRobotY;
        mRobotHeading = aRobotHeading;
    }
}
