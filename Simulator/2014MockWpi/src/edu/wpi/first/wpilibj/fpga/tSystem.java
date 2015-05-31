package edu.wpi.first.wpilibj.fpga;

import edu.wpi.first.wpilibj.communication.BumARioHandle;

public abstract class tSystem implements ExpectedFPGASignature//, DMAChannelDescriptors
{
   protected static int m_DeviceHandle=0;
   private static int m_ReferenceCount=0;

   private static final String kRIO_DEVICE_NAME = "RIO0";
   private static final int kFPGA_RESET_REGISTER = 0x8102;
   private static final int kFPGA_COMMAND_REGISTER = 0x8104;
   private static final int kFPGA_COMMAND_ENABLE_CLEAR = 4;
   private static final int kFPGA_COMMAND_ENABLE_IN = 2;
   private static final int kFPGA_INTERRUPT_BASE_ADDRESS = 0x8000;
   private static final int kFPGA_SIGNATURE_REGISTER = 0x8108;
   private static final int kMITE_IOPCR_REGISTER = 0x470;
   private static final int kMITE_IOPCR_32BIT = 0xC00231;

   protected tSystem()
   {
   }

   private static void printGUID(int guid[])
   {
   }

   protected void finalize()
   {
   }

	/**
	 * Releases the native C++ resources held by the tSystem instance.
	 */
	public void Release()
   {
	}
}
