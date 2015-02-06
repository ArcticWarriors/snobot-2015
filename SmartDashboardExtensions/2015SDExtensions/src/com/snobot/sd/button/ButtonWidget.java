package com.snobot.sd.button;

import java.awt.Dimension;

import com.snobot.sd.config.WidgetConfiguration;

import edu.wpi.first.smartdashboard.gui.StaticWidget;
import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

public class ButtonWidget extends StaticWidget{
    
    /**
     * 
     */
    private static final long serialVersionUID = 2204833389346005703L;
    private SDButton mButton;
    
    public ButtonWidget()
    {
        this.setPreferredSize(new Dimension(WidgetConfiguration.BUTTON_SIZE_X, 
                WidgetConfiguration.BUTTON_SIZE_Y+20));
        
        mButton = new SDButton();
        super.add(mButton.getButtonPanel());
        
        ITableListener listener = new ITableListener() {
            
            @Override
            public void valueChanged(ITable arg0, String arg1, Object arg2, boolean arg3) {
                System.out.println("Value changed... " + arg1 + ", " + arg2 + ", "  + arg3);
            }
        };
    }

    @Override
    public void propertyChanged(Property arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void init() {
        // TODO Auto-generated method stub
        
    }

    
}
