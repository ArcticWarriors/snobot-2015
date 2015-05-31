// Copyright (c) National Instruments 2009.  All Rights Reserved.
// Do Not Edit... this file is generated!

package edu.wpi.first.wpilibj.fpga;

public class tEncoder extends tSystem
{

   public tEncoder(final int sys_index)
   {
      super();
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

   public static final int kNumSystems = 4;
   public final int m_SystemIndex;








//////////////////////////////////////////////////////////////////////////////////////////////////
// Accessors for Output
//////////////////////////////////////////////////////////////////////////////////////////////////
   private static final int kOutput_Direction_BitfieldMask = 0x80000000;
   private static final int kOutput_Direction_BitfieldOffset = 31;
   private static final int kOutput_Value_BitfieldMask = 0x7FFFFFFF;
   private static final int kOutput_Value_BitfieldOffset = 0;
   private static final int kEncoder0_Output_Address = 0x8388;
   private static final int kEncoder1_Output_Address = 0x839C;
   private static final int kEncoder2_Output_Address = 0x83B0;
   private static final int kEncoder3_Output_Address = 0x83C4;
   private static final int kOutput_Addresses [] =
   {
      kEncoder0_Output_Address,
      kEncoder1_Output_Address,
      kEncoder2_Output_Address,
      kEncoder3_Output_Address,
   };

   public int readOutput()
   {
       return 0;
   }
   public boolean readOutput_Direction()
   {
       return false;
   }
   public int readOutput_Value()
   {
       return 0;
   }

//////////////////////////////////////////////////////////////////////////////////////////////////
// Accessors for Config
//////////////////////////////////////////////////////////////////////////////////////////////////
   private static final int kConfig_ASource_Channel_BitfieldMask = 0x001E0000;
   private static final int kConfig_ASource_Channel_BitfieldOffset = 17;
   private static final int kConfig_ASource_Module_BitfieldMask = 0x00010000;
   private static final int kConfig_ASource_Module_BitfieldOffset = 16;
   private static final int kConfig_ASource_AnalogTrigger_BitfieldMask = 0x00008000;
   private static final int kConfig_ASource_AnalogTrigger_BitfieldOffset = 15;
   private static final int kConfig_BSource_Channel_BitfieldMask = 0x00007800;
   private static final int kConfig_BSource_Channel_BitfieldOffset = 11;
   private static final int kConfig_BSource_Module_BitfieldMask = 0x00000400;
   private static final int kConfig_BSource_Module_BitfieldOffset = 10;
   private static final int kConfig_BSource_AnalogTrigger_BitfieldMask = 0x00000200;
   private static final int kConfig_BSource_AnalogTrigger_BitfieldOffset = 9;
   private static final int kConfig_IndexSource_Channel_BitfieldMask = 0x000001E0;
   private static final int kConfig_IndexSource_Channel_BitfieldOffset = 5;
   private static final int kConfig_IndexSource_Module_BitfieldMask = 0x00000010;
   private static final int kConfig_IndexSource_Module_BitfieldOffset = 4;
   private static final int kConfig_IndexSource_AnalogTrigger_BitfieldMask = 0x00000008;
   private static final int kConfig_IndexSource_AnalogTrigger_BitfieldOffset = 3;
   private static final int kConfig_IndexActiveHigh_BitfieldMask = 0x00000004;
   private static final int kConfig_IndexActiveHigh_BitfieldOffset = 2;
   private static final int kConfig_Reverse_BitfieldMask = 0x00000002;
   private static final int kConfig_Reverse_BitfieldOffset = 1;
   private static final int kConfig_Enable_BitfieldMask = 0x00000001;
   private static final int kConfig_Enable_BitfieldOffset = 0;
   private static final int kEncoder0_Config_Address = 0x8380;
   private static final int kEncoder1_Config_Address = 0x8394;
   private static final int kEncoder2_Config_Address = 0x83A8;
   private static final int kEncoder3_Config_Address = 0x83BC;
   private static final int kConfig_Addresses [] =
   {
      kEncoder0_Config_Address,
      kEncoder1_Config_Address,
      kEncoder2_Config_Address,
      kEncoder3_Config_Address,
   };

   public void writeConfig(final int value)
   {
   }
   public void writeConfig_ASource_Channel(final int value)
   {
   }
   public void writeConfig_ASource_Module(final int value)
   {
   }
   public void writeConfig_ASource_AnalogTrigger(final boolean value)
   {
   }
   public void writeConfig_BSource_Channel(final int value)
   {
   }
   public void writeConfig_BSource_Module(final int value)
   {
   }
   public void writeConfig_BSource_AnalogTrigger(final boolean value)
   {
   }
   public void writeConfig_IndexSource_Channel(final int value)
   {
   }
   public void writeConfig_IndexSource_Module(final int value)
   {
   }
   public void writeConfig_IndexSource_AnalogTrigger(final boolean value)
   {
   }
   public void writeConfig_IndexActiveHigh(final boolean value)
   {
   }
   public void writeConfig_Reverse(final boolean value)
   {
   }
   public void writeConfig_Enable(final boolean value)
   {
   }
   public int readConfig()
   {
       return 0;
   }
   public byte readConfig_ASource_Channel()
   {
       return 0;
   }
   public byte readConfig_ASource_Module()
   {
       return 0;
   }
   public boolean readConfig_ASource_AnalogTrigger()
   {
       return false;
   }
   public byte readConfig_BSource_Channel()
   {
       return 0;
   }
   public byte readConfig_BSource_Module()
   {
       return 0;
   }
   public boolean readConfig_BSource_AnalogTrigger()
   {
       return false;
   }
   public byte readConfig_IndexSource_Channel()
   {
       return 0;
   }
   public byte readConfig_IndexSource_Module()
   {
       return 0;

   }
   public boolean readConfig_IndexSource_AnalogTrigger()
   {
       return false;
   }
   public boolean readConfig_IndexActiveHigh()
   {
       return false;
   }
   public boolean readConfig_Reverse()
   {
       return false;
   }
   public boolean readConfig_Enable()
   {
       return false;
   }

//////////////////////////////////////////////////////////////////////////////////////////////////
// Accessors for TimerOutput
//////////////////////////////////////////////////////////////////////////////////////////////////
   private static final int kTimerOutput_Period_BitfieldMask = 0xFFFFFE00;
   private static final int kTimerOutput_Period_BitfieldOffset = 9;
   private static final int kTimerOutput_Period_FixedPointIntegerShift = 1;
   private static final int kTimerOutput_Count_BitfieldMask = 0x000001FE;
   private static final int kTimerOutput_Count_BitfieldOffset = 1;
   private static final int kTimerOutput_Stalled_BitfieldMask = 0x00000001;
   private static final int kTimerOutput_Stalled_BitfieldOffset = 0;
   private static final int kEncoder0_TimerOutput_Address = 0x8390;
   private static final int kEncoder1_TimerOutput_Address = 0x83A4;
   private static final int kEncoder2_TimerOutput_Address = 0x83B8;
   private static final int kEncoder3_TimerOutput_Address = 0x83CC;
   private static final int kTimerOutput_Addresses [] =
   {
      kEncoder0_TimerOutput_Address,
      kEncoder1_TimerOutput_Address,
      kEncoder2_TimerOutput_Address,
      kEncoder3_TimerOutput_Address,
   };

   public int readTimerOutput()
   {
       return 0;
   }
   public int readTimerOutput_Period()
   {
       return 0;
   }
   public byte readTimerOutput_Count()
   {
       return 0;
   }
   public boolean readTimerOutput_Stalled()
   {
       return false;
   }

//////////////////////////////////////////////////////////////////////////////////////////////////
// Accessors for Reset
//////////////////////////////////////////////////////////////////////////////////////////////////
   private static final int kEncoder0_Reset_Address = 0x8384;
   private static final int kEncoder1_Reset_Address = 0x8398;
   private static final int kEncoder2_Reset_Address = 0x83AC;
   private static final int kEncoder3_Reset_Address = 0x83C0;
   private static final int kReset_Addresses [] =
   {
      kEncoder0_Reset_Address,
      kEncoder1_Reset_Address,
      kEncoder2_Reset_Address,
      kEncoder3_Reset_Address,
   };

   public void strobeReset()
   {
   }

//////////////////////////////////////////////////////////////////////////////////////////////////
// Accessors for TimerConfig
//////////////////////////////////////////////////////////////////////////////////////////////////
   private static final int kTimerConfig_StallPeriod_BitfieldMask = 0xFFFFFF00;
   private static final int kTimerConfig_StallPeriod_BitfieldOffset = 8;
   private static final int kTimerConfig_StallPeriod_FixedPointIntegerShift = 1;
   private static final int kTimerConfig_AverageSize_BitfieldMask = 0x000000FE;
   private static final int kTimerConfig_AverageSize_BitfieldOffset = 1;
   private static final int kTimerConfig_UpdateWhenEmpty_BitfieldMask = 0x00000001;
   private static final int kTimerConfig_UpdateWhenEmpty_BitfieldOffset = 0;
   private static final int kEncoder0_TimerConfig_Address = 0x838C;
   private static final int kEncoder1_TimerConfig_Address = 0x83A0;
   private static final int kEncoder2_TimerConfig_Address = 0x83B4;
   private static final int kEncoder3_TimerConfig_Address = 0x83C8;
   private static final int kTimerConfig_Addresses [] =
   {
      kEncoder0_TimerConfig_Address,
      kEncoder1_TimerConfig_Address,
      kEncoder2_TimerConfig_Address,
      kEncoder3_TimerConfig_Address,
   };

   public void writeTimerConfig(final int value)
   {
   }
   public void writeTimerConfig_StallPeriod(final int value)
   {
   }
   public void writeTimerConfig_AverageSize(final int value)
   {
   }
   public void writeTimerConfig_UpdateWhenEmpty(final boolean value)
   {
   }
   public int readTimerConfig()
   {
       return 0;

   }
   public int readTimerConfig_StallPeriod()
   {
       return 0;
   }
   public byte readTimerConfig_AverageSize()
   {
       return 0;
   }
   public boolean readTimerConfig_UpdateWhenEmpty()
   {
       return false;
   }





}
