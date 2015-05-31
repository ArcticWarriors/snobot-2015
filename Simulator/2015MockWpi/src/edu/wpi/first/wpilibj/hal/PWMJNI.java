package edu.wpi.first.wpilibj.hal;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import com.snobot.simulator.SensorActuatorRegistry;
import com.snobot.simulator.SpeedControllerWrapper;


public class PWMJNI extends DIOJNI {
	
	private static SpeedControllerWrapper getWrapperFromBuffer(ByteBuffer digital_port_pointer)
	{
		int port = digital_port_pointer.get(0);
		return SensorActuatorRegistry.get().getSpeedControllers().get(port);
	}
	
	public static boolean allocatePWMChannel(ByteBuffer digital_port_pointer, IntBuffer status)
    {
		int port = digital_port_pointer.get(0);
        SpeedControllerWrapper wrapper = new SpeedControllerWrapper(port);
		SensorActuatorRegistry.get().register(wrapper, port);
		return true;
    }
	public static void freePWMChannel(ByteBuffer digital_port_pointer, IntBuffer status)
    {

    }
	public static void setPWM(ByteBuffer digital_port_pointer, short value, IntBuffer status)
    {
    }
	
	public static short getPWM(ByteBuffer digital_port_pointer, IntBuffer status)
    {
		return 0;
    }
	public static void latchPWMZero(ByteBuffer digital_port_pointer, IntBuffer status)
    {

    }
	public static void setPWMPeriodScale(ByteBuffer digital_port_pointer, int squelchMask, IntBuffer status)
    {

    }
	public static ByteBuffer allocatePWM(IntBuffer status)
    {
		return null;
    }
	public static void freePWM(ByteBuffer pwmGenerator, IntBuffer status)
    {

    }
	public static void setPWMRate(double rate, IntBuffer status)
    {

    }
	public static void setPWMDutyCycle(ByteBuffer pwmGenerator, double dutyCycle, IntBuffer status)
    {

    }
	public static void setPWMOutputChannel(ByteBuffer pwmGenerator, int pin, IntBuffer status)
    {

    }


	public static void __setPWM(ByteBuffer digital_port_pointer, double speed) {
		getWrapperFromBuffer(digital_port_pointer).set(speed);
	}
	
	public static double __getPWM(ByteBuffer digital_port_pointer) {
		return getWrapperFromBuffer(digital_port_pointer).get();
	}
}
