
package com.snobot;

import com.snobot.xlib.ASnobot;
import com.snobot.xlib.PropertyManager;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Snobot2012 extends ASnobot
{

    /**
     * This function is run when the robot is first started up and should be used for any initialization code.
     */
    @Override
    public void robotInit()
    {

        init();
        rereadPreferences();

        // Now all the preferences should be loaded, make sure they are all
        // saved
        PropertyManager.saveIfUpdated();
    }

    @Override
    public void updateLog()
    {
        // Nothing to do....
    }
    
}
