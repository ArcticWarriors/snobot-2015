package edu.wpi.first.wpilibj.hal;

import java.nio.IntBuffer;

public class PowerJNI extends JNIWrapper {
	
	public static float getVinVoltage(IntBuffer status)
    {
		return 0;
    }
	public static float getVinCurrent(IntBuffer status)
    {
		return 0;
    }
	public static float getUserVoltage6V(IntBuffer status)
    {
		return 0;
    }
	public static float getUserCurrent6V(IntBuffer status)
    {
		return 0;
    }
	public static boolean getUserActive6V(IntBuffer status)
    {
		return false;
    }
	public static int getUserCurrentFaults6V(IntBuffer status)
    {
		return 0;
    }
	public static float getUserVoltage5V(IntBuffer status)
    {
		return 0;
    }
	public static float getUserCurrent5V(IntBuffer status)
    {
		return 0;
    }
	public static boolean getUserActive5V(IntBuffer status)
    {
		return false;
    }
	public static int getUserCurrentFaults5V(IntBuffer status)
    {
		return 0;
    }
	public static float getUserVoltage3V3(IntBuffer status)
    {
		return 0;
    }
	public static float getUserCurrent3V3(IntBuffer status)
    {
		return 0;
    }
	public static boolean getUserActive3V3(IntBuffer status)
    {
		return false;
    }
	public static int getUserCurrentFaults3V3(IntBuffer status)
    {
		return 0;
    }
}
