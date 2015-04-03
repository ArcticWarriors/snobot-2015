package com.snobot.sd2015.auton;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.snobot.sd2015.config.SmartDashboardNames;

import edu.wpi.first.smartdashboard.gui.StaticWidget;
import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.smartdashboard.robot.Robot;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

public class AutonWidget extends StaticWidget
{
    public static final String NAME = "2015 Auton Widget";

    private AutonPanel mPanel;

    public AutonWidget()
    {
        mPanel = new AutonPanel();

        setLayout(new BorderLayout());
        add(mPanel, BorderLayout.CENTER);

        mPanel.addSaveListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                Robot.getTable().putString(SmartDashboardNames.sSD_COMMAND_TEXT, mPanel.getTextArea().getText());
                Robot.getTable().putBoolean(SmartDashboardNames.sSAVE_AUTON, true);
            }
        });

        mPanel.addTextChangedListener(new DocumentListener()
        {
            private void onChange()
            {
                Robot.getTable().putBoolean(SmartDashboardNames.sSAVE_AUTON, false);
            }

            @Override
            public void removeUpdate(DocumentEvent e)
            {
                onChange();
            }

            @Override
            public void insertUpdate(DocumentEvent e)
            {
                onChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e)
            {
                onChange();
            }
        });

        ITableListener textUpdatedListener = new ITableListener()
        {
            @Override
            public void valueChanged(ITable arg0, String arg1, Object arg2, boolean arg3)
            {
                Robot.getTable().putBoolean(SmartDashboardNames.sSAVE_AUTON, false);
                mPanel.getTextArea().setText(Robot.getTable().getString(SmartDashboardNames.sROBOT_COMMAND_TEXT));

            }
        };

        ITableListener errorListener = new ITableListener()
        {
            @Override
            public void valueChanged(ITable arg0, String arg1, Object arg2, boolean arg3)
            {
                boolean parseSuccess = Robot.getTable().getBoolean(SmartDashboardNames.sSUCCESFULLY_PARSED_AUTON);
                mPanel.setParseSuccess(parseSuccess);
            }
        };

        ITableListener filenameListener = new ITableListener()
        {
            @Override
            public void valueChanged(ITable arg0, String arg1, Object arg2, boolean arg3)
            {
                String filename = Robot.getTable().getString(SmartDashboardNames.sAUTON_FILENAME);
                System.out.println("Filename updated to " + filename);
            }
        };

        Robot.getTable().addTableListener(SmartDashboardNames.sROBOT_COMMAND_TEXT, textUpdatedListener, true);
        Robot.getTable().addTableListener(SmartDashboardNames.sSUCCESFULLY_PARSED_AUTON, errorListener, true);
        Robot.getTable().addTableListener(SmartDashboardNames.sAUTON_FILENAME, filenameListener, true);

        this.setVisible(true);
    }

    @Override
    public void propertyChanged(Property arg0)
    {
        // Nothing to do
    }

    @Override
    public void init()
    {
        // Nothing to do
    }

}
