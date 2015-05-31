/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2012. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.communication;

/**
 * Class for calibrating the analog inputs.
 * @author jhersh
 */
public class AICalibration {
    //UINT32 FRC_NetworkCommunication_nAICalibration_getLSBWeight(const UINT32 aiSystemIndex, const UINT32 channel, INT32 *status);

    /**
     * Get the weight of the least significant bit.
     * @param aiSystemIndex The system index.
     * @param channel The analog channel.
     * @return The LSB weight.
     */
    public static long getLSBWeight(int aiSystemIndex, int channel) {
        return 0;
    }

    /**
     * Get the offset.
     * @param aiSystemIndex The system index.
     * @param channel The analog channel.
     * @return The offset.
     */
    public static int getOffset(int aiSystemIndex, int channel) {
        return 0;
    }
}
