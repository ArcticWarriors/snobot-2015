package com.snobot.sd;

import edu.wpi.first.smartdashboard.gui.elements.bindings.AbstractValueWidget;
import javax.swing.*;

import edu.wpi.first.smartdashboard.properties.*;
import edu.wpi.first.smartdashboard.types.*;

/**
 * 
 */
public class TextAreaWidget extends AbstractValueWidget {
	
	private static final long serialVersionUID = -5510495890696309586L;

    public static final DataType[] TYPES = {DataType.BASIC};
    public static final String NAME = "Text Area Widget";

    private ThreadSafeTextArea valueField;

    public void init() {
    	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    	JLabel nameLabel = new JLabel(getFieldName());

		valueField = new ThreadSafeTextArea(getFieldName());
		setStringBinding(valueField);
    	valueField.setEditable(false);

        add(nameLabel);
        add(valueField);
    }

    @Override
    public void propertyChanged(Property property) {
    }
    
}
