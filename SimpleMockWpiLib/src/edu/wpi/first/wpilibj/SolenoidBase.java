/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2012. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj;


/**
 * SolenoidBase class is the common base class for the Solenoid and
 * DoubleSolenoid classes.
 */
public abstract class SolenoidBase extends SensorBase {

    protected int m_moduleNumber; ///< The number of the solenoid module being used.

    /**
     * Constructor.
     *
     * @param moduleNumber The PCM CAN ID
     */
    public SolenoidBase(final int moduleNumber) {
        m_moduleNumber = moduleNumber;
    }
}
