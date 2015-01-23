package edu.wpi.first.wpilibj.hal;

import java.nio.IntBuffer;
import java.nio.ByteBuffer;

public class EncoderJNI extends JNIWrapper {
	
	public static ByteBuffer initializeEncoder(byte port_a_module, int port_a_pin, byte port_a_analog_trigger, byte port_b_module, int port_b_pin, byte port_b_analog_trigger, byte reverseDirection, IntBuffer index, IntBuffer status)
    {
		return null;
    }
	public static void freeEncoder(ByteBuffer encoder_pointer, IntBuffer status)
    {

    }
	public static void resetEncoder(ByteBuffer encoder_pointer, IntBuffer status)
    {

    }
	public static int getEncoder(ByteBuffer encoder_pointer, IntBuffer status)
    {
		return 0;
    }
	public static double getEncoderPeriod(ByteBuffer encoder_pointer, IntBuffer status)
    {
		return 0;
    }
	public static void setEncoderMaxPeriod(ByteBuffer encoder_pointer, double maxPeriod, IntBuffer status)
    {

    }
	public static byte getEncoderStopped(ByteBuffer encoder_pointer, IntBuffer status)
    {
		return 0;
    }
	public static byte getEncoderDirection(ByteBuffer encoder_pointer, IntBuffer status)
    {
		return 0;
    }
	public static void setEncoderReverseDirection(ByteBuffer encoder_pointer, byte reverseDirection, IntBuffer status)
    {

    }
	public static void setEncoderSamplesToAverage(ByteBuffer encoder_pointer, int samplesToAverage, IntBuffer status)
    {

    }
	public static int getEncoderSamplesToAverage(ByteBuffer encoder_pointer, IntBuffer status)
    {
		return 0;
    }
	public static void setEncoderIndexSource(ByteBuffer digital_port, int pin, boolean analogTrigger, boolean activeHigh, boolean edgeSensitive, IntBuffer status)
    {

    }
}
