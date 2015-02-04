package edu.wpi.first.wpilibj.hal;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class CompressorJNI extends JNIWrapper {
	
	public static ByteBuffer initializeCompressor(byte module)
	{
		return null;
	}
	public static boolean checkCompressorModule(byte module)
	{
		return true;
	}
	
	public static boolean getCompressor(ByteBuffer pcm_pointer, IntBuffer status)
	{
		return false;
	}
	
	public static void setClosedLoopControl(ByteBuffer pcm_pointer, boolean value, IntBuffer status)
	{

	}
	public static boolean getClosedLoopControl(ByteBuffer pcm_pointer, IntBuffer status)
	{
		return false;
	}
	
	public static boolean getPressureSwitch(ByteBuffer pcm_pointer, IntBuffer status)
	{
		return false;
	}
	public static float getCompressorCurrent(ByteBuffer pcm_pointer, IntBuffer status)
	{
		return 0;
	}

	public static boolean getCompressorCurrentTooHighFault(ByteBuffer pcm_pointer, IntBuffer status)
	{
		return false;
	}
	
	public static boolean getCompressorCurrentTooHighStickyFault(ByteBuffer pcm_pointer, IntBuffer status)
	{
		return false;
	}
	
	public static boolean getCompressorShortedStickyFault(ByteBuffer pcm_pointer, IntBuffer status)
	{
		return false;
	}
	
	public static boolean getCompressorShortedFault(ByteBuffer pcm_pointer, IntBuffer status)
	{
		return false;
	}
	
	public static boolean getCompressorNotConnectedStickyFault(ByteBuffer pcm_pointer, IntBuffer status)
	{
		return false;
	}
	
	public static boolean getCompressorNotConnectedFault(ByteBuffer pcm_pointer, IntBuffer status)
	{
		return false;
	}
	
	public static void clearAllPCMStickyFaults(ByteBuffer pcm_pointer, IntBuffer status)
	{
	}
}
