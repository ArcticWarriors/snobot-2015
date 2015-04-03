package com.snobot.sd2014.coordinategui.widget;

import com.snobot.sd2014.coordinategui.FieldProperties;

/**
 *
 * @author PJ
 */
public class CoordinateWidget_2014 extends BaseCoordinateWidget
{
    private static final long serialVersionUID = -2522966975598389159L;
	public static final String NAME = "2014 Snobot Coordinates Widget";
	
	public static class FieldProperties2014 extends FieldProperties
	{
	    public FieldProperties2014()
	    {
	        super("2014FrcField2.png", 27.0, 54.0);
	    }
	}

    /**
     * Constructor.  Sets up the properties and the field drawer
     */
    public CoordinateWidget_2014()
    {
        super(false, new FieldProperties2014());
    }
}
