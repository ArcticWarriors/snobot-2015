// Class for handling interrupts.
// Copyright (c) National Instruments 2008.  All Rights Reserved.

package edu.wpi.first.wpilibj.fpga;

public class tInterruptManager extends tSystem
{

   private static final int kFPGA_INTERRUPT_BASE_ADDRESS = 0x8000;
   private static final int kFPGA_INTERRUPT_ACKNOWLEDGE_ADDRESS = (kFPGA_INTERRUPT_BASE_ADDRESS + 0xC);

   //tInterruptHandler m_handler;
   private int m_interruptMask;
   //int _taskId;
   private boolean m_watcher;
   private boolean m_enabled;
   //void *m_userParam;

   // maintain the interrupts that are already dealt with.
   private static int m_globalInterruptMask = 0;
   //static SEM_ID m_globalInterruptMaskSemaphore = semMCreate(SEM_Q_PRIORITY | SEM_DELETE_SAFE | SEM_INVERSION_SAFE);

   public tInterruptManager (int interruptMask, boolean watcher)
   {
      super();
   }

   protected void finalize()
   {
   }

   public int watch(int timeoutInMs)
   {
       return 0;
   }

   protected void acknowledge()
   {
   }

   protected void reserve()
   {
   }

   protected void unreserve()
   {
   }

}
