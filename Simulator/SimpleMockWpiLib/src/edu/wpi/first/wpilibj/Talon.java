/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2012. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj;

import com.snobot.simulator.MockSpeedController;

/**
 * Cross the Road Electronics (CTRE) Talon and Talon SR Speed Controller
 */
public class Talon extends MockSpeedController {


    /**
     * Constructor for a Talon (original or Talon SR)
     *
     * @param channel The PWM channel that the Talon is attached to. 0-9 are on-board, 10-19 are on the MXP port
     */
    public Talon(final int channel) {
        super(channel);
    }

}
