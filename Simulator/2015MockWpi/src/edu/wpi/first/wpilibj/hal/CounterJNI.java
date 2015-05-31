package edu.wpi.first.wpilibj.hal;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class CounterJNI extends JNIWrapper {

    public static ByteBuffer initializeCounter(int mode, IntBuffer index, IntBuffer status)
    {
        return null;
    }

    public static void freeCounter(ByteBuffer counter_pointer, IntBuffer status)
    {
    }

    public static void setCounterAverageSize(ByteBuffer counter_pointer, int size, IntBuffer status)
    {
    }

    public static void setCounterUpSource(ByteBuffer counter_pointer, int pin, byte analogTrigger, IntBuffer status)
    {
    }

    public static void setCounterUpSourceEdge(ByteBuffer counter_pointer, byte risingEdge, byte fallingEdge, IntBuffer status)
    {
    }

    public static void clearCounterUpSource(ByteBuffer counter_pointer, IntBuffer status)
    {
    }

    public static void setCounterDownSource(ByteBuffer counter_pointer, int pin, byte analogTrigger, IntBuffer status)
    {
    }

    public static void setCounterDownSourceEdge(ByteBuffer counter_pointer, byte risingEdge, byte fallingEdge, IntBuffer status)
    {
    }

    public static void clearCounterDownSource(ByteBuffer counter_pointer, IntBuffer status)
    {
    }

    public static void setCounterUpDownMode(ByteBuffer counter_pointer, IntBuffer status)
    {
    }

    public static void setCounterExternalDirectionMode(ByteBuffer counter_pointer, IntBuffer status)
    {
    }

    public static void setCounterSemiPeriodMode(ByteBuffer counter_pointer, byte highSemiPeriod, IntBuffer status)
    {
    }

    public static void setCounterPulseLengthMode(ByteBuffer counter_pointer, double threshold, IntBuffer status)
    {
    }

    public static int getCounterSamplesToAverage(ByteBuffer counter_pointer, IntBuffer status)
    {
        return 0;
    }

    public static void setCounterSamplesToAverage(ByteBuffer counter_pointer, int samplesToAverage, IntBuffer status)
    {
    }

    public static void resetCounter(ByteBuffer counter_pointer, IntBuffer status)
    {
    }

    public static int getCounter(ByteBuffer counter_pointer, IntBuffer status)
    {
        return 0;
    }

    public static double getCounterPeriod(ByteBuffer counter_pointer, IntBuffer status)
    {
        return 0;
    }

    public static void setCounterMaxPeriod(ByteBuffer counter_pointer, double maxPeriod, IntBuffer status)
    {
    }

    public static void setCounterUpdateWhenEmpty(ByteBuffer counter_pointer, byte enabled, IntBuffer status)
    {
    }

    public static byte getCounterStopped(ByteBuffer counter_pointer, IntBuffer status)
    {
        return 0;
    }

    public static byte getCounterDirection(ByteBuffer counter_pointer, IntBuffer status)
    {
        return 0;
    }

    public static void setCounterReverseDirection(ByteBuffer counter_pointer, byte reverseDirection, IntBuffer status)
    {
    }
}
