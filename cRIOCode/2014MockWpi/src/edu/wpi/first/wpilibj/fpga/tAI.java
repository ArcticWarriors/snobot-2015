// Copyright (c) National Instruments 2009.  All Rights Reserved.
// Do Not Edit... this file is generated!

package edu.wpi.first.wpilibj.fpga;

public class tAI extends tSystem
{

   public tAI(final int sys_index)
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

   public static final int kNumSystems = 2;
   public final int m_SystemIndex;











//////////////////////////////////////////////////////////////////////////////////////////////////
// Accessors for Config
//////////////////////////////////////////////////////////////////////////////////////////////////
   private static final int kConfig_ScanSize_BitfieldMask = 0x1C000000;
   private static final int kConfig_ScanSize_BitfieldOffset = 26;
   private static final int kConfig_ConvertRate_BitfieldMask = 0x03FFFFFF;
   private static final int kConfig_ConvertRate_BitfieldOffset = 0;
   private static final int kAI0_Config_Address = 0x8154;
   private static final int kAI1_Config_Address = 0x8168;
   private static final int kConfig_Addresses [] =
   {
      kAI0_Config_Address,
      kAI1_Config_Address,
   };

   public void writeConfig(final int value)
   {
   }
   public void writeConfig_ScanSize(final int value)
   {
   }
   public void writeConfig_ConvertRate(final int value)
   {
   }
   public int readConfig()
   {
       return 0;
   }
   public byte readConfig_ScanSize()
   {
       return 0;
   }
   public int readConfig_ConvertRate()
   {
       return 0;
   }

//////////////////////////////////////////////////////////////////////////////////////////////////
// Accessors for LoopTiming
//////////////////////////////////////////////////////////////////////////////////////////////////
   private static final int kAI0_LoopTiming_Address = 0x8164;
   private static final int kAI1_LoopTiming_Address = 0x8178;
   private static final int kLoopTiming_Addresses [] =
   {
      kAI0_LoopTiming_Address,
      kAI1_LoopTiming_Address,
   };

   public long readLoopTiming()
   {
       return 0;
   }

//////////////////////////////////////////////////////////////////////////////////////////////////
// Accessors for OversampleBits
//////////////////////////////////////////////////////////////////////////////////////////////////
   public static final int kOversampleBits_NumElements = 8;
   public static final int kOversampleBits_ElementSize = 4;
   public static final int kOversampleBits_ElementMask = 0xF;
   private static final int kAI0_OversampleBits_Address = 0x815C;
   private static final int kAI1_OversampleBits_Address = 0x8170;
   private static final int kOversampleBits_Addresses [] =
   {
      kAI0_OversampleBits_Address,
      kAI1_OversampleBits_Address,
   };

   public void writeOversampleBits(final int bitfield_index, final int value)
   {
   }
   public byte readOversampleBits(final int bitfield_index)
   {
       return 0;
   }

//////////////////////////////////////////////////////////////////////////////////////////////////
// Accessors for AverageBits
//////////////////////////////////////////////////////////////////////////////////////////////////
   public static final int kAverageBits_NumElements = 8;
   public static final int kAverageBits_ElementSize = 4;
   public static final int kAverageBits_ElementMask = 0xF;
   private static final int kAI0_AverageBits_Address = 0x8160;
   private static final int kAI1_AverageBits_Address = 0x8174;
   private static final int kAverageBits_Addresses [] =
   {
      kAI0_AverageBits_Address,
      kAI1_AverageBits_Address,
   };

   public void writeAverageBits(final int bitfield_index, final int value)
   {
   }
   public byte readAverageBits(final int bitfield_index)
   {
       return 0;
   }

//////////////////////////////////////////////////////////////////////////////////////////////////
// Accessors for ScanList
//////////////////////////////////////////////////////////////////////////////////////////////////
   public static final int kScanList_NumElements = 8;
   public static final int kScanList_ElementSize = 3;
   public static final int kScanList_ElementMask = 0x7;
   private static final int kAI0_ScanList_Address = 0x8158;
   private static final int kAI1_ScanList_Address = 0x816C;
   private static final int kScanList_Addresses [] =
   {
      kAI0_ScanList_Address,
      kAI1_ScanList_Address,
   };

   public void writeScanList(final int bitfield_index, final int value)
   {
   }
   public byte readScanList(final int bitfield_index)
   {
       return 0;
   }


//////////////////////////////////////////////////////////////////////////////////////////////////
// Accessors for Output
//////////////////////////////////////////////////////////////////////////////////////////////////
   private static final int kAI_Output_Address = 0x8150;

   public static int readOutput()
   {
       return 0;
   }

//////////////////////////////////////////////////////////////////////////////////////////////////
// Accessors for LatchOutput
//////////////////////////////////////////////////////////////////////////////////////////////////
   private static final int kAI_LatchOutput_Address = 0x814C;

   public static void strobeLatchOutput()
   {
   }

//////////////////////////////////////////////////////////////////////////////////////////////////
// Accessors for ReadSelect
//////////////////////////////////////////////////////////////////////////////////////////////////
   private static final int kReadSelect_Channel_BitfieldMask = 0x0000001C;
   private static final int kReadSelect_Channel_BitfieldOffset = 2;
   private static final int kReadSelect_Module_BitfieldMask = 0x00000002;
   private static final int kReadSelect_Module_BitfieldOffset = 1;
   private static final int kReadSelect_Averaged_BitfieldMask = 0x00000001;
   private static final int kReadSelect_Averaged_BitfieldOffset = 0;
   private static final int kAI_ReadSelect_Address = 0x8148;

   public static void writeReadSelect(final int value)
   {
   }
   public static void writeReadSelect_Channel(final int value)
   {
   }
   public static void writeReadSelect_Module(final int value)
   {
   }
   public static void writeReadSelect_Averaged(final boolean value)
   {
   }
   public static int readReadSelect()
   {
       return 0;
   }
   public static byte readReadSelect_Channel()
   {
       return 0;
   }
   public static byte readReadSelect_Module()
   {
       return 0;
   }
   public static boolean readReadSelect_Averaged()
   {
       return false;
   }




}
