package edu.wpi.first.wpilibj.hal;

import java.nio.IntBuffer;
import java.nio.ByteBuffer;

import com.snobot.simulator.DigitalSourceWrapper;
import com.snobot.simulator.SensorActuatorRegistry;

public class DIOJNI extends JNIWrapper {
	
	public static ByteBuffer initializeDigitalPort(ByteBuffer port_pointer, IntBuffer status)
    {
		return port_pointer;
    }
	public static byte allocateDIO(ByteBuffer digital_port_pointer, byte input, IntBuffer status)
    {
		int pin = digital_port_pointer.get(0);
		System.out.println("Allocating DIO : " + pin);
		DigitalSourceWrapper wrapper = new DigitalSourceWrapper();
		SensorActuatorRegistry.get().register(wrapper, pin);
		
		return 0;

    }
	public static void freeDIO(ByteBuffer digital_port_pointer, IntBuffer status)
    {

    }
	public static void setDIO(ByteBuffer digital_port_pointer, short value, IntBuffer status)
    {

    }
	public static byte getDIO(ByteBuffer digital_port_pointer, IntBuffer status)
    {
		return 0;

    }
	public static byte getDIODirection(ByteBuffer digital_port_pointer, IntBuffer status)
    {
		return 0;

    }
	public static void pulse(ByteBuffer digital_port_pointer, double pulseLength, IntBuffer status)
    {

    }
	public static byte isPulsing(ByteBuffer digital_port_pointer, IntBuffer status)
    {
		return 0;

    }
	public static byte isAnyPulsing(IntBuffer status)
    {
		return 0;

    }
	public static short getLoopTiming(IntBuffer status)
    {
		return 0;

    }
}
