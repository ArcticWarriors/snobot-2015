package com.snobot.omit.autons;

import java.awt.BorderLayout;
import java.awt.Dimension;

import edu.wpi.first.smartdashboard.gui.StaticWidget;
import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.smartdashboard.robot.Robot;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;


public class TextWidget extends StaticWidget
{
    private static String mButtonKey = "ButtonIsPressed";
    private TextPanel mPanel;

    public TextWidget()
    {
        mPanel = new TextPanel();
        mPanel.getPanel().setPreferredSize(new Dimension(200, 100));
        ITableListener mListener = new ITableListener()
        {

            @Override
            public void valueChanged(ITable arg0, String arg1, Object arg2, boolean arg3)
            {
                if (Robot.getTable().getBoolean(mButtonKey))
                {
                    Robot.getTable().putString("Autons Omitted", mPanel.getTextArea().getText());
                    Robot.getTable().putBoolean(mButtonKey, false);
                }

            }

        };
        this.setLayout(new BorderLayout());
        // this.add(mPanel, BorderLayout.CENTER);
        this.setVisible(true);

    }

    @Override
    public void propertyChanged(Property arg0)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void init()
    {
        // TODO Auto-generated method stub

    }

}
