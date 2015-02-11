/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2012. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.communication;

import com.sun.cldc.jna.Structure;

/**
 * Contains the code necessary to communicate between the robot and the driver station.
 */
public final class FRCControl {

    /**
     * The size of the IO configuration data
     */
    public static final int IO_CONFIG_DATA_SIZE = 32;
    /**
     * The size of the user control data
     */
    public static final int USER_CONTROL_DATA_SIZE = 936 - IO_CONFIG_DATA_SIZE;
    /**
     * The size of the user status data
     */
    public static final int USER_STATUS_DATA_SIZE = 984;
    /**
     * The size of the user driver station display data
     */
    public static final int USER_DS_LCD_DATA_SIZE = 128;

    private FRCControl() {
    }

    /**
     * A simple 1-element cache that keeps a pointer to native memory around.
     * Works best if repeatedly asked for buffers of same size.
     *
     * WARNING: It's expected that the users of this cache are synchronized
     *
     * @TODO free the cache at shutdown...
     */
    public static class CachedNativeBuffer {

        public void free() {
        }
    } /* CachedNativeBuffer */


    public static abstract class DynamicControlData extends Structure {
    }
    
    private static final CachedNativeBuffer controlDataCache = new CachedNativeBuffer();
    private static final CachedNativeBuffer statusDataCacheHigh = new CachedNativeBuffer();
    private static final CachedNativeBuffer statusDataCacheLow = new CachedNativeBuffer();
    private static final CachedNativeBuffer ioConfigDataCache = new CachedNativeBuffer();

    /**
     * Get the control data from the driver station. The parameter "data"
     * is only updated when the method returns 0.
     *
     * @param data the object to store the results in (out param)
     * @param wait_ms the maximum time to wait
     * @return 0 if new data, 1 if no new data, 2 if access timed out.
     */
    public static int getCommonControlData(FRCCommonControlData data, int wait_ms) {
        data.read();
        return 0;
    }

    /**
     * Get the dynamic control data from the driver station. The parameter 
     * "dynamicData" is only updated when the method returns 0.
     * @param type The type to get.
     * @param dynamicData The array to hold the result in.
     * @param maxLength The maximum length of the data.
     * @param wait_ms The maximum time to wait.
     * @return 0 if new data, 1 if no new data, 2 if access timed out.
     */
    public static int getDynamicControlData(byte type, DynamicControlData dynamicData, int maxLength, int wait_ms) {
        return 0;
    }

    /**
     * Right new io config data.
     * @param ioConfig The data to write / read
     * @param wait_ms The maximum time to wait.
     * @return 0 if new data, 1 if no new data, 2 if access timed out.
     */
    public static int overrideIOConfig(DynamicControlData ioConfig, int wait_ms) {
        return 0;
    }

    /**
     * Set the status data to send to the ds
     *
     * @param battery the battery voltage
     * @param dsDigitalOut value to set the digital outputs on the ds to
     * @param updateNumber unique ID for this update (incrementing)
     * @param userDataHigh additional high-priority user data bytes
     * @param userDataHighLength number of high-priority data bytes
     * @param userDataLow additional low-priority user data bytes
     * @param userDataLowLength number of low-priority data bytes
     * @param wait_ms the timeout
     * @return 0 on success, 1 if userData.length is too big, 2 if semaphore could not be taken in wait_ms.
     */
    public static int setStatusData(double battery, int dsDigitalOut,
            int updateNumber, byte[] userDataHigh, int userDataHighLength,
            byte[] userDataLow, int userDataLowLength, int wait_ms) {
        return 0;
    }

    /**
     * Send data to the driver station's error panel
     * @param bytes the byte array containing the properly formatted information for the display
     * @param length the length of the byte array
     * @param timeOut the maximum time to wait
     */
    public static void setErrorData(byte[] bytes, int length, int timeOut) {
    }

    /**
     * Send data to the driver station's user panel
     * @param bytes the byte array containing the properly formatted information for the display
     * @param length the length of the byte array
     * @param timeOut the maximum time to wait
     */
    public static void setUserDsLcdData(byte[] bytes, int length, int timeOut) {
    }

    /**
     * Set the semaphore for the communications task to use
     * @param sem the semaphore to use
     */
    public static void setNewDataSem(Semaphore sem) {
    }

    /**
     * Let the DS know that the user is loading a new app.
     */
    public static void observeUserProgramStarting() {
    }
    
    public static void observeUserProgramDisabled() {
    }
    
    public static void observeUserProgramAutonomous() {
    }
    
    public static void observeUserProgramTeleop() {
    }
    
    public static void observeUserProgramTest() {
    }
}
