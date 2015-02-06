// Copyright (c) National Instruments 2009.  All Rights Reserved.
// Do Not Edit... this file is generated!

package edu.wpi.first.wpilibj.fpga;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import com.snobot.simulator.SensorActuatorRegistry;
import com.snobot.simulator.SolenoidWrapper;

import edu.wpi.first.wpilibj.SensorBase;

public class tSolenoid extends tSystem
{

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
   }
   public static short readDO7_0(final int bitfield_index)
   {
       return 0;
   }




}
