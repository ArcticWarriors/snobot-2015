package com.snobot.sd.auton;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.snobot.sd.config.SmartDashboardNames;
import com.snobot.sd.config.WidgetConfiguration;

import edu.wpi.first.smartdashboard.gui.StaticWidget;
import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.smartdashboard.robot.Robot;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

public class AutonWidget extends StaticWidget
{
    private String mCommandText;
    
    private AutonPanel mPanel;
    
    public AutonWidget() {
        this.setLayout(new BorderLayout());
        mPanel = new AutonPanel();
        
        this.add(mPanel, BorderLayout.CENTER);
        
        this.setPreferredSize(new Dimension(WidgetConfiguration.TEXT_AREA_WIDGET_SIZE_X, 
                WidgetConfiguration.TEXT_AREA_WIDGET_SIZE_Y));
        
        mPanel.addSendListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                mCommandText = mPanel.getTextArea().getText();
                System.out.println(mCommandText);
                
                Robot.getTable().putString(SmartDashboardNames.sSD_COMMAND_TEXT, mCommandText);
            }
        });
        
        ITableListener radioListener = new ITableListener()
        {

            @Override
            public void valueChanged(ITable arg0, String arg1, Object arg2, boolean arg3) {
//                if (SmartDashboardNames.sSEND_BUTTON_PRESSED.equals("Old"))
                {
                    System.out.println("So far, so good.");
                    mPanel.getTextArea().setText(Robot.getTable().getString(SmartDashboardNames.sROBOT_COMMAND_TEXT));
                }
                
            }
            
        };
        Robot.getTable().addTableListener(SmartDashboardNames.sROBOT_COMMAND_TEXT, 
                radioListener, true);
        
        this.setVisible(true);
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
