package com.snobot.simulator.joysticks;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JTabbedPane;

import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class ViewAllGamepadsDialog extends JDialog
{
    private static final long serialVersionUID = 6955764742666051259L;

    private Thread mUpdateThread;
    private List<GamepadViewer> viewers;

    public ViewAllGamepadsDialog()
    {
        JTabbedPane mainPane = new JTabbedPane();
        viewers = new ArrayList<GamepadViewer>();

        ControllerEnvironment ce = ControllerEnvironment.getDefaultEnvironment();
        Controller[] cs = ce.getControllers();
        for (int i = 0; i < cs.length; i++)
        {
            if (cs[i].getType() == Controller.Type.GAMEPAD || cs[i].getType() == Controller.Type.STICK)
            {
                GamepadViewer view = new GamepadViewer(cs[i]);
                viewers.add(view);
                mainPane.addTab(cs[i].getName(), view);
            }
        }

        setContentPane(mainPane);

        mUpdateThread = new Thread(new Runnable()
        {

            @Override
            public void run()
            {

                boolean run = true;

                while (run)
                {
                    for (GamepadViewer v : viewers)
                    {
                        v.update();
                    }

                    try
                    {
                        Thread.sleep(20);
                    }
                    catch (InterruptedException e)
                    {
                        run = false;
                    }
                }
            }
        });

        mUpdateThread.start();
    }

}
