// Copyright (c) National Instruments 2009.  All Rights Reserved.
// Do Not Edit... this file is generated!

package edu.wpi.first.wpilibj.fpga;


public class tDIO extends tSystem
{

   public tDIO(final int sys_index)
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
// Accessors for FilterSelect
//////////////////////////////////////////////////////////////////////////////////////////////////
   public static final int kFilterSelect_NumElements = 16;
   public static final int kFilterSelect_ElementSize = 2;
   public static final int kFilterSelect_ElementMask = 0x3;
   private static final int kDIO0_FilterSelect_Address = 0x8268;
   private static final int kDIO1_FilterSelect_Address = 0x82D4;
   private static final int kFilterSelect_Addresses [] =
   {
      kDIO0_FilterSelect_Address,
      kDIO1_FilterSelect_Address,
   };

   public void writeFilterSelect(final int bitfield_index, final int value)
   {
   }
   public byte readFilterSelect(final int bitfield_index)
   {
       return 0;
   }

//////////////////////////////////////////////////////////////////////////////////////////////////
// Accessors for I2CDataToSend
//////////////////////////////////////////////////////////////////////////////////////////////////
   private static final int kDIO0_I2CDataToSend_Address = 0x8240;
   private static final int kDIO1_I2CDataToSend_Address = 0x82AC;
   private static final int kI2CDataToSend_Addresses [] =
   {
      kDIO0_I2CDataToSend_Address,
      kDIO1_I2CDataToSend_Address,
   };

   public void writeI2CDataToSend(final long value)
   {
   }
   public long readI2CDataToSend()
   {
       return 0;
   }

//////////////////////////////////////////////////////////////////////////////////////////////////
// Accessors for DO
//////////////////////////////////////////////////////////////////////////////////////////////////
   private static final int kDIO0_DO_Address = 0x8208;
   private static final int kDIO1_DO_Address = 0x8274;
   private static final int kDO_Addresses [] =
   {
      kDIO0_DO_Address,
      kDIO1_DO_Address,
   };

   public void writeDO(final int value)
   {
   }
   public int readDO()
   {
       return 0;
   }

//////////////////////////////////////////////////////////////////////////////////////////////////
// Accessors for FilterPeriod
//////////////////////////////////////////////////////////////////////////////////////////////////
   public static final int kFilterPeriod_NumElements = 3;
   public static final int kFilterPeriod_ElementSize = 8;
   public static final int kFilterPeriod_ElementMask = 0xFF;
   private static final int kDIO0_FilterPeriod_Address = 0x8264;
   private static final int kDIO1_FilterPeriod_Address = 0x82D0;
   private static final int kFilterPeriod_Addresses [] =
   {
      kDIO0_FilterPeriod_Address,
      kDIO1_FilterPeriod_Address,
   };

   public void writeFilterPeriod(final int bitfield_index, final int value)
   {
   }
   public short readFilterPeriod(final int bitfield_index)
   {
       return 0;
   }

//////////////////////////////////////////////////////////////////////////////////////////////////
// Accessors for OutputEnable
//////////////////////////////////////////////////////////////////////////////////////////////////
   private static final int kDIO0_OutputEnable_Address = 0x8210;
   private static final int kDIO1_OutputEnable_Address = 0x827C;
   private static final int kOutputEnable_Addresses [] =
   {
      kDIO0_OutputEnable_Address,
      kDIO1_OutputEnable_Address,
   };

   public void writeOutputEnable(final int value)
   {
   }
   public int readOutputEnable()
   {
       return 0;
   }

//////////////////////////////////////////////////////////////////////////////////////////////////
// Accessors for Pulse
//////////////////////////////////////////////////////////////////////////////////////////////////
   private static final int kDIO0_Pulse_Address = 0x825C;
   private static final int kDIO1_Pulse_Address = 0x82C8;
   private static final int kPulse_Addresses [] =
   {
      kDIO0_Pulse_Address,
      kDIO1_Pulse_Address,
   };

   public void writePulse(final int value)
   {
   }
   public int readPulse()
   {
       return 0;
   }

//////////////////////////////////////////////////////////////////////////////////////////////////
// Accessors for SlowValue
//////////////////////////////////////////////////////////////////////////////////////////////////
   private static final int kSlowValue_RelayFwd_BitfieldMask = 0x000FF000;
   private static final int kSlowValue_RelayFwd_BitfieldOffset = 12;
   private static final int kSlowValue_RelayRev_BitfieldMask = 0x00000FF0;
   private static final int kSlowValue_RelayRev_BitfieldOffset = 4;
   private static final int kSlowValue_I2CHeader_BitfieldMask = 0x0000000F;
   private static final int kSlowValue_I2CHeader_BitfieldOffset = 0;
   private static final int kDIO0_SlowValue_Address = 0x8254;
   private static final int kDIO1_SlowValue_Address = 0x82C0;
   private static final int kSlowValue_Addresses [] =
   {
      kDIO0_SlowValue_Address,
      kDIO1_SlowValue_Address,
   };

   public void writeSlowValue(final int value)
   {
   }
   public void writeSlowValue_RelayFwd(final int value)
   {
   }
   public void writeSlowValue_RelayRev(final int value)
   {
   }
   public void writeSlowValue_I2CHeader(final int value)
   {
   }
   public int readSlowValue()
   {
       return 0;
   }
   public short readSlowValue_RelayFwd()
   {
       return 0;
   }
   public short readSlowValue_RelayRev()
   {
       return 0;
   }
   public byte readSlowValue_I2CHeader()
   {
       return 0;
   }

//////////////////////////////////////////////////////////////////////////////////////////////////
// Accessors for I2CStatus
//////////////////////////////////////////////////////////////////////////////////////////////////
   private static final int kI2CStatus_Transaction_BitfieldMask = 0x04000000;
   private static final int kI2CStatus_Transaction_BitfieldOffset = 26;
   private static final int kI2CStatus_Done_BitfieldMask = 0x02000000;
   private static final int kI2CStatus_Done_BitfieldOffset = 25;
   private static final int kI2CStatus_Aborted_BitfieldMask = 0x01000000;
   private static final int kI2CStatus_Aborted_BitfieldOffset = 24;
   private static final int kI2CStatus_DataReceivedHigh_BitfieldMask = 0x00FFFFFF;
   private static final int kI2CStatus_DataReceivedHigh_BitfieldOffset = 0;
   private static final int kDIO0_I2CStatus_Address = 0x8250;
   private static final int kDIO1_I2CStatus_Address = 0x82BC;
   private static final int kI2CStatus_Addresses [] =
   {
      kDIO0_I2CStatus_Address,
      kDIO1_I2CStatus_Address,
   };

   public int readI2CStatus()
   {
       return 0;
   }
   public byte readI2CStatus_Transaction()
   {
       return 0;
   }
   public boolean readI2CStatus_Done()
   {
       return false;
   }
   public boolean readI2CStatus_Aborted()
   {
       return false;
   }
   public int readI2CStatus_DataReceivedHigh()
   {
       return 0;
   }

//////////////////////////////////////////////////////////////////////////////////////////////////
// Accessors for I2CDataReceived
//////////////////////////////////////////////////////////////////////////////////////////////////
   private static final int kDIO0_I2CDataReceived_Address = 0x824C;
   private static final int kDIO1_I2CDataReceived_Address = 0x82B8;
   private static final int kI2CDataReceived_Addresses [] =
   {
      kDIO0_I2CDataReceived_Address,
      kDIO1_I2CDataReceived_Address,
   };

   public long readI2CDataReceived()
   {
       return 0;
   }

//////////////////////////////////////////////////////////////////////////////////////////////////
// Accessors for DI
//////////////////////////////////////////////////////////////////////////////////////////////////
   private static final int kDIO0_DI_Address = 0x820C;
   private static final int kDIO1_DI_Address = 0x8278;
   private static final int kDI_Addresses [] =
   {
      kDIO0_DI_Address,
      kDIO1_DI_Address,
   };

   public int readDI()
   {
       return 0;
   }

//////////////////////////////////////////////////////////////////////////////////////////////////
// Accessors for PulseLength
//////////////////////////////////////////////////////////////////////////////////////////////////
   private static final int kDIO0_PulseLength_Address = 0x8260;
   private static final int kDIO1_PulseLength_Address = 0x82CC;
   private static final int kPulseLength_Addresses [] =
   {
      kDIO0_PulseLength_Address,
      kDIO1_PulseLength_Address,
   };

   public void writePulseLength(final int value)
   {
   }
   public short readPulseLength()
   {
       return 0;
   }

//////////////////////////////////////////////////////////////////////////////////////////////////
// Accessors for PWMPeriodScale
//////////////////////////////////////////////////////////////////////////////////////////////////
   public static final int kPWMPeriodScale_NumElements = 10;
   public static final int kPWMPeriodScale_ElementSize = 2;
   public static final int kPWMPeriodScale_ElementMask = 0x3;
   private static final int kDIO0_PWMPeriodScale_Address = 0x823C;
   private static final int kDIO1_PWMPeriodScale_Address = 0x82A8;
   private static final int kPWMPeriodScale_Addresses [] =
   {
      kDIO0_PWMPeriodScale_Address,
      kDIO1_PWMPeriodScale_Address,
   };

   public void writePWMPeriodScale(final int bitfield_index, final int value)
   {
   }
   public byte readPWMPeriodScale(final int bitfield_index)
   {
       return 0;
   }

//////////////////////////////////////////////////////////////////////////////////////////////////
// Accessors for DO_PWMDutyCycle
//////////////////////////////////////////////////////////////////////////////////////////////////
   public static final int kDO_PWMDutyCycle_NumElements = 4;
   public static final int kDO_PWMDutyCycle_ElementSize = 8;
   public static final int kDO_PWMDutyCycle_ElementMask = 0xFF;
   private static final int kDIO0_DO_PWMDutyCycle_Address = 0x826C;
   private static final int kDIO1_DO_PWMDutyCycle_Address = 0x82D8;
   private static final int kDO_PWMDutyCycle_Addresses [] =
   {
      kDIO0_DO_PWMDutyCycle_Address,
      kDIO1_DO_PWMDutyCycle_Address,
   };

   public void writeDO_PWMDutyCycle(final int bitfield_index, final int value)
   {
   }
   public short readDO_PWMDutyCycle(final int bitfield_index)
   {
       return 0;
   }

//////////////////////////////////////////////////////////////////////////////////////////////////
// Accessors for BFL
//////////////////////////////////////////////////////////////////////////////////////////////////
   private static final int kDIO0_BFL_Address = 0x8258;
   private static final int kDIO1_BFL_Address = 0x82C4;
   private static final int kBFL_Addresses [] =
   {
      kDIO0_BFL_Address,
      kDIO1_BFL_Address,
   };

   public void writeBFL(final boolean value)
   {
   }
   public boolean readBFL()
   {
       return false;
   }

//////////////////////////////////////////////////////////////////////////////////////////////////
// Accessors for I2CConfig
//////////////////////////////////////////////////////////////////////////////////////////////////
   private static final int kI2CConfig_Address_BitfieldMask = 0x7F800000;
   private static final int kI2CConfig_Address_BitfieldOffset = 23;
   private static final int kI2CConfig_BytesToRead_BitfieldMask = 0x00700000;
   private static final int kI2CConfig_BytesToRead_BitfieldOffset = 20;
   private static final int kI2CConfig_BytesToWrite_BitfieldMask = 0x000E0000;
   private static final int kI2CConfig_BytesToWrite_BitfieldOffset = 17;
   private static final int kI2CConfig_DataToSendHigh_BitfieldMask = 0x0001FFFE;
   private static final int kI2CConfig_DataToSendHigh_BitfieldOffset = 1;
   private static final int kI2CConfig_BitwiseHandshake_BitfieldMask = 0x00000001;
   private static final int kI2CConfig_BitwiseHandshake_BitfieldOffset = 0;
   private static final int kDIO0_I2CConfig_Address = 0x8244;
   private static final int kDIO1_I2CConfig_Address = 0x82B0;
   private static final int kI2CConfig_Addresses [] =
   {
      kDIO0_I2CConfig_Address,
      kDIO1_I2CConfig_Address,
   };

   public void writeI2CConfig(final int value)
   {
   }
   public void writeI2CConfig_Address(final int value)
   {
   }
   public void writeI2CConfig_BytesToRead(final int value)
   {
   }
   public void writeI2CConfig_BytesToWrite(final int value)
   {
   }
   public void writeI2CConfig_DataToSendHigh(final int value)
   {
   }
   public void writeI2CConfig_BitwiseHandshake(final boolean value)
   {
   }
   public int readI2CConfig()
   {
       return 0;
   }
   public short readI2CConfig_Address()
   {
       return 0;
   }
   public byte readI2CConfig_BytesToRead()
   {
       return 0;
   }
   public byte readI2CConfig_BytesToWrite()
   {
       return 0;
   }
   public int readI2CConfig_DataToSendHigh()
   {
       return 0;
   }
   public boolean readI2CConfig_BitwiseHandshake()
   {
       return false;
   }

//////////////////////////////////////////////////////////////////////////////////////////////////
// Accessors for DO_PWMConfig
//////////////////////////////////////////////////////////////////////////////////////////////////
   private static final int kDO_PWMConfig_PeriodPower_BitfieldMask = 0x000F0000;
   private static final int kDO_PWMConfig_PeriodPower_BitfieldOffset = 16;
   private static final int kDO_PWMConfig_OutputSelect_0_BitfieldMask = 0x0000F000;
   private static final int kDO_PWMConfig_OutputSelect_0_BitfieldOffset = 12;
   private static final int kDO_PWMConfig_OutputSelect_1_BitfieldMask = 0x00000F00;
   private static final int kDO_PWMConfig_OutputSelect_1_BitfieldOffset = 8;
   private static final int kDO_PWMConfig_OutputSelect_2_BitfieldMask = 0x000000F0;
   private static final int kDO_PWMConfig_OutputSelect_2_BitfieldOffset = 4;
   private static final int kDO_PWMConfig_OutputSelect_3_BitfieldMask = 0x0000000F;
   private static final int kDO_PWMConfig_OutputSelect_3_BitfieldOffset = 0;
   private static final int kDIO0_DO_PWMConfig_Address = 0x8270;
   private static final int kDIO1_DO_PWMConfig_Address = 0x82DC;
   private static final int kDO_PWMConfig_Addresses [] =
   {
      kDIO0_DO_PWMConfig_Address,
      kDIO1_DO_PWMConfig_Address,
   };

   public void writeDO_PWMConfig(final int value)
   {
   }
   public void writeDO_PWMConfig_PeriodPower(final int value)
   {
   }
   public void writeDO_PWMConfig_OutputSelect_0(final int value)
   {
   }
   public void writeDO_PWMConfig_OutputSelect_1(final int value)
   {
   }
   public void writeDO_PWMConfig_OutputSelect_2(final int value)
   {
   }
   public void writeDO_PWMConfig_OutputSelect_3(final int value)
   {
   }
   public int readDO_PWMConfig()
   {
       return 0;
   }
   public byte readDO_PWMConfig_PeriodPower()
   {
       return 0;
   }
   public byte readDO_PWMConfig_OutputSelect_0()
   {
       return 0;
   }
   public byte readDO_PWMConfig_OutputSelect_1()
   {
       return 0;
   }
   public byte readDO_PWMConfig_OutputSelect_2()
   {
       return 0;
   }
   public byte readDO_PWMConfig_OutputSelect_3()
   {
       return 0;
   }

//////////////////////////////////////////////////////////////////////////////////////////////////
// Accessors for I2CStart
//////////////////////////////////////////////////////////////////////////////////////////////////
   private static final int kDIO0_I2CStart_Address = 0x8248;
   private static final int kDIO1_I2CStart_Address = 0x82B4;
   private static final int kI2CStart_Addresses [] =
   {
      kDIO0_I2CStart_Address,
      kDIO1_I2CStart_Address,
   };

   public void strobeI2CStart()
   {
   }


//////////////////////////////////////////////////////////////////////////////////////////////////
// Accessors for LoopTiming
//////////////////////////////////////////////////////////////////////////////////////////////////
   private static final int kDIO_LoopTiming_Address = 0x8200;

   public static int readLoopTiming()
   {
       return 0;
   }

//////////////////////////////////////////////////////////////////////////////////////////////////
// Accessors for PWMConfig
//////////////////////////////////////////////////////////////////////////////////////////////////
   private static final int kPWMConfig_Period_BitfieldMask = 0xFFFF0000;
   private static final int kPWMConfig_Period_BitfieldOffset = 16;
   private static final int kPWMConfig_MinHigh_BitfieldMask = 0x0000FFFF;
   private static final int kPWMConfig_MinHigh_BitfieldOffset = 0;
   private static final int kDIO_PWMConfig_Address = 0x8204;

   public static void writePWMConfig(final int value)
   {
   }
   public static void writePWMConfig_Period(final int value)
   {
   }
   public static void writePWMConfig_MinHigh(final int value)
   {
   }
   public static int readPWMConfig()
   {
       return 0;
   }
   public static int readPWMConfig_Period()
   {
       return 0;
   }
   public static int readPWMConfig_MinHigh()
   {
       return 0;
   }


//////////////////////////////////////////////////////////////////////////////////////////////////
// Accessors for PWMValue
//////////////////////////////////////////////////////////////////////////////////////////////////
   public static final int kPWMValue_NumRegisters = 10;
   private static final int kDIO0_PWMValue0_Address = 0x8214;
   private static final int kDIO0_PWMValue1_Address = 0x8218;
   private static final int kDIO0_PWMValue2_Address = 0x821C;
   private static final int kDIO0_PWMValue3_Address = 0x8220;
   private static final int kDIO0_PWMValue4_Address = 0x8224;
   private static final int kDIO0_PWMValue5_Address = 0x8228;
   private static final int kDIO0_PWMValue6_Address = 0x822C;
   private static final int kDIO0_PWMValue7_Address = 0x8230;
   private static final int kDIO0_PWMValue8_Address = 0x8234;
   private static final int kDIO0_PWMValue9_Address = 0x8238;
   private static final int kDIO1_PWMValue0_Address = 0x8280;
   private static final int kDIO1_PWMValue1_Address = 0x8284;
   private static final int kDIO1_PWMValue2_Address = 0x8288;
   private static final int kDIO1_PWMValue3_Address = 0x828C;
   private static final int kDIO1_PWMValue4_Address = 0x8290;
   private static final int kDIO1_PWMValue5_Address = 0x8294;
   private static final int kDIO1_PWMValue6_Address = 0x8298;
   private static final int kDIO1_PWMValue7_Address = 0x829C;
   private static final int kDIO1_PWMValue8_Address = 0x82A0;
   private static final int kDIO1_PWMValue9_Address = 0x82A4;
   private static final int kPWMValue_Addresses [] =
   {
      kDIO0_PWMValue0_Address,
      kDIO0_PWMValue1_Address,
      kDIO0_PWMValue2_Address,
      kDIO0_PWMValue3_Address,
      kDIO0_PWMValue4_Address,
      kDIO0_PWMValue5_Address,
      kDIO0_PWMValue6_Address,
      kDIO0_PWMValue7_Address,
      kDIO0_PWMValue8_Address,
      kDIO0_PWMValue9_Address,
      kDIO1_PWMValue0_Address,
      kDIO1_PWMValue1_Address,
      kDIO1_PWMValue2_Address,
      kDIO1_PWMValue3_Address,
      kDIO1_PWMValue4_Address,
      kDIO1_PWMValue5_Address,
      kDIO1_PWMValue6_Address,
      kDIO1_PWMValue7_Address,
      kDIO1_PWMValue8_Address,
      kDIO1_PWMValue9_Address,
   };

   public void writePWMValue(final int reg_index, final int value)
   {
   }
   public short readPWMValue(final int reg_index)
   {
       return 0;
   }



}
