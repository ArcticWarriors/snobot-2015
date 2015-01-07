/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2012. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj;

import com.snobot.simulator.MockSpeedController;

/**
 * Texas Instruments / Vex Robotics Jaguar Speed Controller as a PWM device.
 * @see CANJaguar CANJaguar for CAN control
 */
public class Jaguar extends MockSpeedController {


    /**
     * Constructor.
     *
     * @param channel The PWM channel that the Jaguar is attached to. 0-9 are on-board, 10-19 are on the MXP port
     */
    public Jaguar(final int channel) {
        super(channel);
    }
}
