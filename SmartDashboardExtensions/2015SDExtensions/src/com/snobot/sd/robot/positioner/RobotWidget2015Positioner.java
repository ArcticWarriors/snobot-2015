package com.snobot.sd.robot.positioner;

import java.awt.Dimension;
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
    private Dimension mImageDimension;
    private double mScale;

    private void updateScale() {
        int width = this.getWidth();
        int height = this.getHeight();

        double widthScale = width / 360;
        double heightScale = height / 432;

        if (widthScale < heightScale) {
            mScale = widthScale;
            mImageDimension.width = width;
            mImageDimension.height = (int) (432 * mScale);
        }
        else {
            mScale = heightScale;
            mImageDimension.width = (int) (360 * mScale);
            mImageDimension.height = height;
        }

        repaint();
    }

    private void readFieldImage() {
        try {
            InputStream in = getClass().getResourceAsStream("/2015SDExtensions/src/com/snobot/sd/robot/positioner/field.png");

            // Image exists
            if (in != null) {
                mFieldImage = ImageIO.read(in);

                if (mFieldImage != null) {
                    setPreferredSize(new Dimension(mFieldImage.getWidth(), mFieldImage.getHeight()));
                }
                else {
                    System.out.println("Could not open image file : '/2015SDExtensions/src/com/snobot/sd/robot/positioner/field.png'");
                }
                updateScale();
            }
            else {
                System.out.println("Could not find image file : '/2015SDExtensions/src/com/snobot/sd/robot/positioner/field.png'");
            }
        }
        catch (Exception ex) {
            System.err.println(ex);
        }
    }
}
