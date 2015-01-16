package com.snobot.sd.coordinategui;

public abstract class FieldProperties
{

    public final String sFIELD_IMG_PATH;
    public final double sFIELD_WIDTH_FT;
    public final double sFIELD_HEIGHT_FT;
    
    public FieldProperties(String aFieldImage, double aFieldWidth, double aFieldHeight)
    {
        sFIELD_IMG_PATH = aFieldImage;
        sFIELD_WIDTH_FT = aFieldWidth;
        sFIELD_HEIGHT_FT = aFieldHeight;
    }
    
}
