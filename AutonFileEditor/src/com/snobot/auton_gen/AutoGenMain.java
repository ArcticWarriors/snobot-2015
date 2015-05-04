package com.snobot.auton_gen;

import java.awt.EventQueue;

import com.snobot.auton_gen.view.AutoGenFrame;

public class AutoGenMain
{
    
    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    AutoGenFrame frame = new AutoGenFrame();
                    frame.setVisible(true);
                    frame.pack();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
}

