package com.snobot.sd.coordinategui.widget;

import com.snobot.sd.coordinategui.FieldProperties;

/**
 *
 * @author PJ
 */
public class CoordinateWidget_2013 extends BaseCoordinateWidget
{
	public static final String NAME = "2013 Snobot Coordinates Widget";
	
	private static final long serialVersionUID = -2522966975598389159L;
	
	public static class FieldProperties2013 extends FieldProperties
	{
	    public FieldProperties2013()
	    {
	        super("2013FrcField.jpg", 27.0, 54.0);
	    }
	}

    /**
     * Constructor.  Sets up the properties and the field drawer
     */
    public CoordinateWidget_2013()
    {
        super(false, new FieldProperties2013());
    }
}
