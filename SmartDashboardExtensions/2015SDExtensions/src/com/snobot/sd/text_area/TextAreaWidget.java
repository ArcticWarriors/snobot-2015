package com.snobot.sd.text_area;

import java.awt.Dimension;

import com.snobot.sd.config.WidgetConfiguration;

import edu.wpi.first.smartdashboard.gui.StaticWidget;
import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.smartdashboard.robot.Robot;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

public class TextAreaWidget extends StaticWidget{
    
    private static final String sAUTON_SD_NAME = "Y-position";
    
    /**
     * 
     */
    private static final long serialVersionUID = 4327540744618999872L;
    SDTextArea mTextArea;
    
    public TextAreaWidget()
    {
        this.setPreferredSize(new Dimension(WidgetConfiguration.TEXT_AREA_WIDGET_SIZE_X, 
                WidgetConfiguration.TEXT_AREA_WIDGET_SIZE_Y));
        
        mTextArea = new SDTextArea();
        super.add(mTextArea.getScrollPane());
        
        ITableListener listener = new ITableListener() {
            
            @Override
            public void valueChanged(ITable arg0, String arg1, Object arg2, boolean arg3) {
                System.out.println("Value changed... " + arg1 + ", " + arg2 + ", "  + arg3);
            }
        };
        
        Robot.getTable().addTableListener(sAUTON_SD_NAME, listener, true);
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
