// Copyright (c) National Instruments 2009.  All Rights Reserved.
// Do Not Edit... this file is generated!

package edu.wpi.first.wpilibj.fpga;

public class tInterrupt extends tSystem
{

   public tInterrupt(final int sys_index)
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

   public static final int kNumSystems = 8;
   public final int m_SystemIndex;





//////////////////////////////////////////////////////////////////////////////////////////////////
// Accessors for TimeStamp
//////////////////////////////////////////////////////////////////////////////////////////////////
   private static final int kInterrupt0_TimeStamp_Address = 0x83D4;
   private static final int kInterrupt1_TimeStamp_Address = 0x83DC;
   private static final int kInterrupt2_TimeStamp_Address = 0x83E4;
   private static final int kInterrupt3_TimeStamp_Address = 0x83EC;
   private static final int kInterrupt4_TimeStamp_Address = 0x83F4;
   private static final int kInterrupt5_TimeStamp_Address = 0x83FC;
   private static final int kInterrupt6_TimeStamp_Address = 0x8404;
   private static final int kInterrupt7_TimeStamp_Address = 0x840C;
   private static final int kTimeStamp_Addresses [] =
   {
      kInterrupt0_TimeStamp_Address,
      kInterrupt1_TimeStamp_Address,
      kInterrupt2_TimeStamp_Address,
      kInterrupt3_TimeStamp_Address,
      kInterrupt4_TimeStamp_Address,
      kInterrupt5_TimeStamp_Address,
      kInterrupt6_TimeStamp_Address,
      kInterrupt7_TimeStamp_Address,
   };

   public long readTimeStamp()
   {
       return 0;
   }

//////////////////////////////////////////////////////////////////////////////////////////////////
// Accessors for Config
//////////////////////////////////////////////////////////////////////////////////////////////////
   private static final int kConfig_Source_Channel_BitfieldMask = 0x000001E0;
   private static final int kConfig_Source_Channel_BitfieldOffset = 5;
   private static final int kConfig_Source_Module_BitfieldMask = 0x00000010;
   private static final int kConfig_Source_Module_BitfieldOffset = 4;
   private static final int kConfig_Source_AnalogTrigger_BitfieldMask = 0x00000008;
   private static final int kConfig_Source_AnalogTrigger_BitfieldOffset = 3;
   private static final int kConfig_RisingEdge_BitfieldMask = 0x00000004;
   private static final int kConfig_RisingEdge_BitfieldOffset = 2;
   private static final int kConfig_FallingEdge_BitfieldMask = 0x00000002;
   private static final int kConfig_FallingEdge_BitfieldOffset = 1;
   private static final int kConfig_WaitForAck_BitfieldMask = 0x00000001;
   private static final int kConfig_WaitForAck_BitfieldOffset = 0;
   private static final int kInterrupt0_Config_Address = 0x83D0;
   private static final int kInterrupt1_Config_Address = 0x83D8;
   private static final int kInterrupt2_Config_Address = 0x83E0;
   private static final int kInterrupt3_Config_Address = 0x83E8;
   private static final int kInterrupt4_Config_Address = 0x83F0;
   private static final int kInterrupt5_Config_Address = 0x83F8;
   private static final int kInterrupt6_Config_Address = 0x8400;
   private static final int kInterrupt7_Config_Address = 0x8408;
   private static final int kConfig_Addresses [] =
   {
      kInterrupt0_Config_Address,
      kInterrupt1_Config_Address,
      kInterrupt2_Config_Address,
      kInterrupt3_Config_Address,
      kInterrupt4_Config_Address,
      kInterrupt5_Config_Address,
      kInterrupt6_Config_Address,
      kInterrupt7_Config_Address,
   };

   public void writeConfig(final int value)
   {

   }
   public void writeConfig_Source_Channel(final int value)
   {
   }
   public void writeConfig_Source_Module(final int value)
   {
   }
   public void writeConfig_Source_AnalogTrigger(final boolean value)
   {
   }
   public void writeConfig_RisingEdge(final boolean value)
   {
   }
   public void writeConfig_FallingEdge(final boolean value)
   {
   }
   public void writeConfig_WaitForAck(final boolean value)
   {
   }
   public int readConfig()
   {
       return 0;
   }
   public byte readConfig_Source_Channel()
   {
       return 0;
   }
   public byte readConfig_Source_Module()
   {
       return 0;
   }
   public boolean readConfig_Source_AnalogTrigger()
   {
       return false;
   }
   public boolean readConfig_RisingEdge()
   {
       return false;
   }
   public boolean readConfig_FallingEdge()
   {
       return false;
   }
   public boolean readConfig_WaitForAck()
   {
       return false;
   }





}
