// Copyright (c) National Instruments 2009.  All Rights Reserved.
// Do Not Edit... this file is generated!

package edu.wpi.first.wpilibj.fpga;

import com.snobot.simulator.SensorActuatorRegistry;
import com.snobot.simulator.SolenoidWrapper;

public class tSolenoid extends tSystem
{

    static
    {
        for (int i = 1; i <= 8; ++i)
        {
            initializeSolenoidPort(i);
        }
    }

    public static void initializeSolenoidPort(int pin)
    {        
        SolenoidWrapper wrapper = new SolenoidWrapper();
        SensorActuatorRegistry.get().register(wrapper, pin);
    }

   public tSolenoid()
   {
      super();

      for(int i = 1; i <= 8; ++i)
      {
          initializeSolenoidPort(i);
      }
   }

   protected void finalize()
   {
      super.finalize();
   }

   public static final int kNumSystems = 1;





//////////////////////////////////////////////////////////////////////////////////////////////////
// Accessors for DO7_0
//////////////////////////////////////////////////////////////////////////////////////////////////
   public static final int kDO7_0_NumElements = 2;
   public static final int kDO7_0_ElementSize = 8;
   public static final int kDO7_0_ElementMask = 0xFF;
   private static final int kSolenoid_DO7_0_Address = 0x8144;

   public static void writeDO7_0(final int bitfield_index, final int value)
   {
        // System.out.println("Writing " + value);
        // for(int i = 0; i < 8; ++i)
        // {
        // boolean isSet = ((value >> i) & 1) == 1;
        // System.out.println("  Setting " + i + " to " + isSet + " ( " +
        // Integer.toBinaryString(value) + ")");
        // getWrapperFromBuffer(i).set(isSet);
        // }
   }
   public static short readDO7_0(final int bitfield_index)
   {
        // System.out.println("Num things : " +
        // SensorActuatorRegistry.get().getSolenoids().size());
        short output = 0;
        // for (int i = 0; i < 8; ++i)
        // {
        // output |= getWrapperFromBuffer(i).get() ? 0 : (1 << i);
        // }

        return output;
   }

    private static SolenoidWrapper getWrapperFromBuffer(int digital_port_pointer)
    {
        return SensorActuatorRegistry.get().getSolenoids().get(digital_port_pointer + 1);
    }


}
