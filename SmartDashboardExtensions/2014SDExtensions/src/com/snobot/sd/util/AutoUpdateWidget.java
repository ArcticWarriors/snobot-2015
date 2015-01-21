package com.snobot.sd.util;

import edu.wpi.first.smartdashboard.gui.StaticWidget;

/**
 *
 * @author PJ
 */
public abstract class AutoUpdateWidget extends StaticWidget
{
	private static final long serialVersionUID = -5324757383577336302L;
	
	private final static int UPDATE_TIME = 50;
    private final static int GC_TIME = 10000;
    private final BGThread bgThread;
    private final GCThread gcThread;
    protected final boolean mDebug;
    
    public AutoUpdateWidget(boolean aDebug)
    {
        bgThread = new BGThread();
        gcThread = new GCThread();
        
        mDebug = aDebug;
        
        bgThread.start();
        gcThread.start();
    }
       
    
    public class GCThread extends Thread
    {

        boolean destroyed = false;

        @Override
        public void run()
        {
            while (!destroyed)
            {
                try
                {
                    Thread.sleep(GC_TIME);
                } catch (InterruptedException ex)
                {
                }
                System.gc();
            }
        }

        @Override
        public void destroy()
        {
            destroyed = true;
            interrupt();
        }
    }

    public class BGThread extends Thread
    {

        boolean destroyed = false;

        public BGThread()
        {
            super("Camera Background");
        }

        @Override
        public void run()
        {
            while (!destroyed)
            {
                repaint();
                try
                {
                    Thread.sleep(UPDATE_TIME);
                } catch (InterruptedException ex)
                {
                }
            }

        }

        @Override
        public void destroy()
        {
            destroyed = true;
        }
    }
}
