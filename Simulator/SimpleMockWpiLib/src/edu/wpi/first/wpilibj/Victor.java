/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2012. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj;

import com.snobot.simulator.MockSpeedController;

/**
 * VEX Robotics Victor 888 Speed Controller
 * 
 * The Vex Robotics Victor 884 Speed Controller can also be used with this
 * class but may need to be calibrated per the Victor 884 user manual.
 */
public class Victor extends MockSpeedController implements SpeedController {


    /**
     * Constructor.
     *
     * @param channel The PWM channel that the Victor is attached to. 0-9 are on-board, 10-19 are on the MXP port
     */
    public Victor(final int channel) {
        super(channel);
    }

}
