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
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

public class AutonWidget extends StaticWidget
{
    private AutonPanel mPanel;
    
    public AutonWidget() {
        this.setLayout(new BorderLayout());
        mPanel = new AutonPanel();

        this.add(mPanel, BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(WidgetConfiguration.TEXT_AREA_WIDGET_SIZE_X, WidgetConfiguration.TEXT_AREA_WIDGET_SIZE_Y));
        
        mPanel.addSendListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                Robot.getTable().putString(SmartDashboardNames.sSD_COMMAND_TEXT, mPanel.getTextArea().getText());
                Robot.getTable().putBoolean(SmartDashboardNames.sSAVE_REQUEST, false);
            }
        });
        
        mPanel.addSaveListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                Robot.getTable().putString(SmartDashboardNames.sSD_COMMAND_TEXT, mPanel.getTextArea().getText());
                Robot.getTable().putBoolean(SmartDashboardNames.sSAVE_REQUEST, true);
            }
        });
        
        ITableListener radioListener = new ITableListener()
        {
            @Override
            public void valueChanged(ITable arg0, String arg1, Object arg2, boolean arg3)
            {
                mPanel.getTextArea().setText(Robot.getTable().getString(SmartDashboardNames.sROBOT_COMMAND_TEXT));
                
            }
            
        };
        
        ITableListener errorListener = new ITableListener()
        {
            @Override
            public void valueChanged(ITable arg0, String arg1, Object arg2, boolean arg3) 
            {
                // TODO set flag
            }
            
        };

        Robot.getTable().addTableListener(SmartDashboardNames.sROBOT_COMMAND_TEXT, radioListener, true);
        Robot.getTable().addTableListener(SmartDashboardNames.sCOMMAND_ERROR_BOOL, errorListener, true);
        
        this.setVisible(true);
    }

    @Override
    public void propertyChanged(Property arg0) {
        // Nothing to do
    }

    @Override
    public void init() {
        // Nothing to do
    }

}
