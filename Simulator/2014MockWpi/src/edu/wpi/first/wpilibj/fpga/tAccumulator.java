// Copyright (c) National Instruments 2009.  All Rights Reserved.
// Do Not Edit... this file is generated!

package edu.wpi.first.wpilibj.fpga;

public class tAccumulator extends tSystem
{

   public tAccumulator(final int sys_index)
   {
       m_SystemIndex = sys_index;
   }

   protected void finalize()
   {
      super.finalize();
   }

   public int getSystemIndex()
   {
      return m_SystemIndex;
   }

   public static final int kNumSystems = 2;
   public final int m_SystemIndex;


   public static class tOutput
   {
      public tOutput(int regValue1, int regValue2, int regValue3)
      {
         Value = (((long)regValue1) << 32) | regValue2;
         Count = regValue3;
      }
      public final long Value;
      public final int Count;
   }




//////////////////////////////////////////////////////////////////////////////////////////////////
// Accessors for Output
//////////////////////////////////////////////////////////////////////////////////////////////////
   private static final int kOutput_Value_BitfieldMask = 0x00000000;
   private static final int kOutput_Value_BitfieldOffset = 32;
   private static final int kOutput_Count_BitfieldMask = 0xFFFFFFFF;
   private static final int kOutput_Count_BitfieldOffset = 0;
   private static final int kAccumulator0_Output_Address = 0x8184;
   private static final int kAccumulator1_Output_Address = 0x8194;
   private static final int kOutput_Addresses [] =
   {
      kAccumulator0_Output_Address,
      kAccumulator1_Output_Address,
   };

   public tOutput readOutput()
   {
       return null;
   }
   public long readOutput_Value()
   {
       return 0;
   }
   public long readOutput_Count()
   {
       return 0;
   }

//////////////////////////////////////////////////////////////////////////////////////////////////
// Accessors for Center
//////////////////////////////////////////////////////////////////////////////////////////////////
   private static final int kAccumulator0_Center_Address = 0x817C;
   private static final int kAccumulator1_Center_Address = 0x818C;
   private static final int kCenter_Addresses [] =
   {
      kAccumulator0_Center_Address,
      kAccumulator1_Center_Address,
   };

   public void writeCenter(final int value)
   {
   }
   public int readCenter()
   {
       return 0;
   }

//////////////////////////////////////////////////////////////////////////////////////////////////
// Accessors for Deadband
//////////////////////////////////////////////////////////////////////////////////////////////////
   private static final int kAccumulator0_Deadband_Address = 0x8188;
   private static final int kAccumulator1_Deadband_Address = 0x8198;
   private static final int kDeadband_Addresses [] =
   {
      kAccumulator0_Deadband_Address,
      kAccumulator1_Deadband_Address,
   };

   public void writeDeadband(final int value)
   {
   }
   public int readDeadband()
   {
       return 0;
   }

//////////////////////////////////////////////////////////////////////////////////////////////////
// Accessors for Reset
//////////////////////////////////////////////////////////////////////////////////////////////////
   private static final int kAccumulator0_Reset_Address = 0x8180;
   private static final int kAccumulator1_Reset_Address = 0x8190;
   private static final int kReset_Addresses [] =
   {
      kAccumulator0_Reset_Address,
      kAccumulator1_Reset_Address,
   };

   public void strobeReset()
   {
   }





}
