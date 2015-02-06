// Copyright (c) National Instruments 2009.  All Rights Reserved.
// Do Not Edit... this file is generated!

package edu.wpi.first.wpilibj.fpga;

public class tAlarm extends tSystem
{

   public tAlarm()
   {
      super();

   }

   protected void finalize()
   {
      super.finalize();
   }

   public static final int kNumSystems = 1;






//////////////////////////////////////////////////////////////////////////////////////////////////
// Accessors for Enable
//////////////////////////////////////////////////////////////////////////////////////////////////
   private static final int kAlarm_Enable_Address = 0x8448;

   public static void writeEnable(final boolean value)
   {
   }
   public static boolean readEnable()
   {
       return false;
   }

//////////////////////////////////////////////////////////////////////////////////////////////////
// Accessors for TriggerTime
//////////////////////////////////////////////////////////////////////////////////////////////////
   private static final int kAlarm_TriggerTime_Address = 0x8444;

   public static void writeTriggerTime(final long value)
   {
   }
   public static long readTriggerTime()
   {
       return 0;
   }




}
