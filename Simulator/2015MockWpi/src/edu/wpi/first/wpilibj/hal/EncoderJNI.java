package edu.wpi.first.wpilibj.hal;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import com.snobot.simulator.EncoderPair;
import com.snobot.simulator.EncoderWrapper;
import com.snobot.simulator.SensorActuatorRegistry;

public class EncoderJNI extends JNIWrapper {

	private static EncoderWrapper getWrapperFromBuffer(ByteBuffer digital_port_pointer)
	{
		int portA = digital_port_pointer.get(0);
		int portB = digital_port_pointer.get(1);
		
		EncoderWrapper wrapper = SensorActuatorRegistry.get().getEncoder(portA, portB);
		
		return wrapper;
	}
	
	public static ByteBuffer initializeEncoder(byte port_a_module, int port_a_pin, byte port_a_analog_trigger, byte port_b_module, int port_b_pin, byte port_b_analog_trigger, byte reverseDirection, IntBuffer index, IntBuffer status)
    {
		ByteBuffer buffer = ByteBuffer.allocate(2);
		buffer.put((byte) port_a_pin);
		buffer.put((byte) port_b_pin);
		
        EncoderWrapper wrapper = new EncoderWrapper(port_a_pin, port_b_pin);
		EncoderPair ports = new EncoderPair(port_a_pin, port_b_pin);
		SensorActuatorRegistry.get().register(wrapper, ports);
		
		return buffer;
    }
	public static void freeEncoder(ByteBuffer encoder_pointer, IntBuffer status)
    {

    }
	public static void resetEncoder(ByteBuffer encoder_pointer, IntBuffer status)
    {
		getWrapperFromBuffer(encoder_pointer).reset();
    }
	public static int getEncoder(ByteBuffer encoder_pointer, IntBuffer status)
    {
		return getWrapperFromBuffer(encoder_pointer).getRaw();
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
