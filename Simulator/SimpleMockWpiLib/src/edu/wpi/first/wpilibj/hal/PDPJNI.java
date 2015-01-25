package edu.wpi.first.wpilibj.hal;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class PDPJNI extends JNIWrapper {
	public static double getPDPTemperature(IntBuffer status)
    {
		return 0;
    }
	public static double getPDPVoltage(IntBuffer status)
    {
		return 0;
    }
	public static double getPDPChannelCurrent(byte channel, IntBuffer status)
    {
		return 0;
    }
	public static double getPDPTotalCurrent(IntBuffer status)
    {
		return 0;
    }
	public static double getPDPTotalPower(IntBuffer status)
    {
		return 0;
    }
	public static double getPDPTotalEnergy(IntBuffer status)
    {
		return 0;
    }
	public static void resetPDPTotalEnergy(IntBuffer status)
    {

    }
	public static void clearPDPStickyFaults(IntBuffer status)
    {

    }
}