package com.snobot.sd.text_area;

import java.awt.Dimension;

import com.snobot.sd.config.WidgetConfiguration;

import edu.wpi.first.smartdashboard.gui.StaticWidget;
import edu.wpi.first.smartdashboard.properties.Property;

public class TextAreaWidget extends StaticWidget{
    /**
     * 
     */
    private static final long serialVersionUID = 4327540744618999872L;
    TextArea mTextArea;
    
    public TextAreaWidget()
    {
        this.setPreferredSize(new Dimension(WidgetConfiguration.TEXT_AREA_WIDGET_SIZE_X, 
                WidgetConfiguration.TEXT_AREA_WIDGET_SIZE_Y));
        
        mTextArea = new TextArea();
        super.add(mTextArea.getScrollPane());
    }

    @Override
    public void propertyChanged(Property arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void init() {
        mTextArea.getScrollPane().setVisible(true);
    }

}
