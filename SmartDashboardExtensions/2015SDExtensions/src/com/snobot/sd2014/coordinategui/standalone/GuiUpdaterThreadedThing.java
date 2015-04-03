package com.snobot.sd2014.coordinategui.standalone;

import java.util.ArrayList;
import java.util.List;

import com.snobot.sd2014.coordinategui.Coordinate;
import com.snobot.sd2014.coordinategui.FieldDrawer;


public class GuiUpdaterThreadedThing extends Thread
{
	FieldDrawer mFieldDrawer;
	List<Coordinate> mPoints;
	long mSleepTime;
	boolean mRunning;
	
	public GuiUpdaterThreadedThing(FieldDrawer aPanel, long aSleepTime)
	{
		mFieldDrawer = aPanel;
		mSleepTime = aSleepTime;
		mPoints = new ArrayList<Coordinate>();
		mRunning = false;
	}
	
	public void drawPoints(final List<Coordinate> aPoints)
	{
        mFieldDrawer.clearPoints();
        mPoints = aPoints;
        start();
	}
	
	public void terminate()
	{
		mRunning = false;
	}
	
	public void run()
	{
		mRunning = true;
        try
        {
            for(int i = 0; i < mPoints.size() && mRunning; ++i)
            {
                mFieldDrawer.addPoint(mPoints.get(i));
                Thread.sleep(mSleepTime);
            }
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
        mRunning = false;
        
        System.out.println("Done w/ thread");
	}
}
