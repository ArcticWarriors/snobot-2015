package edu.wpi.first.wpilibj.hal;

import java.nio.IntBuffer;
import java.nio.ByteBuffer;
import java.nio.LongBuffer;

import com.snobot.simulator.AnalogWrapper;
import com.snobot.simulator.SensorActuatorRegistry;
import com.snobot.simulator.SpeedControllerWrapper;

public class AnalogJNI extends JNIWrapper {

	private static AnalogWrapper getWrapperFromBuffer(ByteBuffer buffer)
	{
		int port = buffer.get(0);
		return SensorActuatorRegistry.get().getAnalog().get(port);
	}
	
	/**
	 * <i>native declaration : AthenaJava\target\native\include\HAL\Analog.h:58</i><br>
	 * enum values
	 */
	public static interface AnalogTriggerType {
		/** <i>native declaration : AthenaJava\target\native\include\HAL\Analog.h:54</i> */
		public static final int kInWindow = 0;
		/** <i>native declaration : AthenaJava\target\native\include\HAL\Analog.h:55</i> */
		public static final int kState = 1;
		/** <i>native declaration : AthenaJava\target\native\include\HAL\Analog.h:56</i> */
		public static final int kRisingPulse = 2;
		/** <i>native declaration : AthenaJava\target\native\include\HAL\Analog.h:57</i> */
		public static final int kFallingPulse = 3;
	};

	public static ByteBuffer initializeAnalogInputPort(ByteBuffer port_pointer, IntBuffer status)
    {
		int port = port_pointer.get(0);
		AnalogWrapper wrapper = new AnalogWrapper();
		SensorActuatorRegistry.get().register(wrapper, port);
		
		return port_pointer;
    }
	public static ByteBuffer initializeAnalogOutputPort(ByteBuffer port_pointer, IntBuffer status)
    {
		int port = port_pointer.get(0);
		AnalogWrapper wrapper = new AnalogWrapper();
		SensorActuatorRegistry.get().register(wrapper, port);
		
		return port_pointer;
    }
	public static byte checkAnalogModule(byte module)
    {
		return 0;
    }
	public static byte checkAnalogInputChannel(int pin)
    {
		return (byte) (SensorActuatorRegistry.get().getAnalog().containsKey(pin) ? 0 : 1);
    }
	public static byte checkAnalogOutputChannel(int pin)
    {
		return (byte) (SensorActuatorRegistry.get().getAnalog().containsKey(pin) ? 0 : 1);
    }
	public static void setAnalogOutput(ByteBuffer port_pointer, double voltage, IntBuffer status)
    {
		getWrapperFromBuffer(port_pointer).setVoltage(voltage);
    }
	public static double getAnalogOutput(ByteBuffer port_pointer, IntBuffer status)
    {
		return getWrapperFromBuffer(port_pointer).getVoltage();
    }
	public static void setAnalogSampleRate(double samplesPerSecond, IntBuffer status)
    {

    }
	public static double getAnalogSampleRate(IntBuffer status)
    {
		return 100;
    }
	public static void setAnalogAverageBits(ByteBuffer analog_port_pointer, int bits, IntBuffer status)
    {

    }
	public static int getAnalogAverageBits(ByteBuffer analog_port_pointer, IntBuffer status)
    {
		return 0;
    }
	public static void setAnalogOversampleBits(ByteBuffer analog_port_pointer, int bits, IntBuffer status)
    {

    }
	public static int getAnalogOversampleBits(ByteBuffer analog_port_pointer, IntBuffer status)
    {
		return 0;
    }
	public static short getAnalogValue(ByteBuffer analog_port_pointer, IntBuffer status)
    {
		return 0;
    }
	public static int getAnalogAverageValue(ByteBuffer analog_port_pointer, IntBuffer status)
    {
		return 0;
    }
	public static int getAnalogVoltsToValue(ByteBuffer analog_port_pointer, double voltage, IntBuffer status)
    {
		return 0;
    }
	public static double getAnalogVoltage(ByteBuffer analog_port_pointer, IntBuffer status)
    {
		return 0;
    }
	public static double getAnalogAverageVoltage(ByteBuffer analog_port_pointer, IntBuffer status)
    {
		return 0;
    }
	public static int getAnalogLSBWeight(ByteBuffer analog_port_pointer, IntBuffer status)
    {
		return 0;
    }
	public static int getAnalogOffset(ByteBuffer analog_port_pointer, IntBuffer status)
    {
		return 0;
    }
	public static byte isAccumulatorChannel(ByteBuffer analog_port_pointer, IntBuffer status)
    {
		return 0;
    }
	public static void initAccumulator(ByteBuffer analog_port_pointer, IntBuffer status)
    {

    }
	public static void resetAccumulator(ByteBuffer analog_port_pointer, IntBuffer status)
    {

    }
	public static void setAccumulatorCenter(ByteBuffer analog_port_pointer, int center, IntBuffer status)
    {

    }
	public static void setAccumulatorDeadband(ByteBuffer analog_port_pointer, int deadband, IntBuffer status)
    {

    }
	public static long getAccumulatorValue(ByteBuffer analog_port_pointer, IntBuffer status)
    {
		return 0;
    }
	public static int getAccumulatorCount(ByteBuffer analog_port_pointer, IntBuffer status)
    {
		return 0;
    }
	public static void getAccumulatorOutput(ByteBuffer analog_port_pointer, LongBuffer value, IntBuffer count, IntBuffer status)
    {

    }
	public static ByteBuffer initializeAnalogTrigger(ByteBuffer port_pointer, IntBuffer index, IntBuffer status)
    {
		return port_pointer;
    }
	public static void cleanAnalogTrigger(ByteBuffer analog_trigger_pointer, IntBuffer status)
    {

    }
	public static void setAnalogTriggerLimitsRaw(ByteBuffer analog_trigger_pointer, int lower, int upper, IntBuffer status)
    {

    }
	public static void setAnalogTriggerLimitsVoltage(ByteBuffer analog_trigger_pointer, double lower, double upper, IntBuffer status)
    {

    }
	public static void setAnalogTriggerAveraged(ByteBuffer analog_trigger_pointer, byte useAveragedValue, IntBuffer status)
    {

    }
	public static void setAnalogTriggerFiltered(ByteBuffer analog_trigger_pointer, byte useFilteredValue, IntBuffer status)
    {

    }
	public static byte getAnalogTriggerInWindow(ByteBuffer analog_trigger_pointer, IntBuffer status)
    {
		return 0;
    }
	public static byte getAnalogTriggerTriggerState(ByteBuffer analog_trigger_pointer, IntBuffer status)
    {
		return 0;
    }
	public static byte getAnalogTriggerOutput(ByteBuffer analog_trigger_pointer, int type, IntBuffer status)
    {
		return 0;
    }
}
