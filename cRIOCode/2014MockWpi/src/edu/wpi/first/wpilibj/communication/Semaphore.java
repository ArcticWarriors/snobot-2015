/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2012. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.communication;

import com.snobot.simulator.RobotStateSingleton;

import edu.wpi.first.wpilibj.Timer;

/**
 * Class exposing VxWorks semaphores.
 * @author dtjones
 */
public class Semaphore {

    /** Integer ms value indicating wait forever. */
    public static final int WAIT_FOREVER = -1;

    static final int SEM_Q_FIFO                = 0x00; /* first in first out queue */
    static final int SEM_Q_PRIORITY            = 0x01; /* priority sorted queue */
    static final int SEM_DELETE_SAFE           = 0x04; /* owner delete safe (mutex opt.) */
    static final int SEM_INVERSION_SAFE        = 0x08; /* no priority inversion (mutex opt.) */
    static final int SEM_EVENTSEND_ERR_NOTIFY  = 0x10; /* notify when eventRsrcSend fails */
    static final int SEM_INTERRUPTIBLE         = 0x20; /* interruptible on RTP signal */

    /**
     * Options to create a semaphore with.
     */
    public static class Options {
        int value = 0;
        /**
         * Set true to use a priority sorted queue, false to use first-in first-out
         * @param priority
         */
        public void setPrioritySorted(boolean priority) {
            if (priority) value |= SEM_Q_PRIORITY;
            else value &= ~SEM_Q_PRIORITY;
        }
        /**
         * Set whether or not the semaphore is delete safe.
         * @param delSafe True to make the semaphore delete safe.
         */
        public void setDeleteSafe(boolean delSafe) {
            if (delSafe) value |= SEM_DELETE_SAFE;
            else value &= ~SEM_DELETE_SAFE;
        }
        /**
         * Set whether the semaphore is inversion safe.
         * @param invSafe True to set the semaphore to inversion safe.
         */
        public void setInversionSafe(boolean invSafe) {
            if (invSafe) value |= SEM_INVERSION_SAFE;
            else value &= ~SEM_INVERSION_SAFE;
        }
        /**
         * Set whether the semaphore should notify on an error.
         * @param errNot True to set error notify.
         */
        public void setErrorNotify(boolean errNot) {
            if (errNot) value |= SEM_EVENTSEND_ERR_NOTIFY;
            else value &= ~SEM_EVENTSEND_ERR_NOTIFY;
        }
        /**
         * Set whether the semaphore is interruptable.
         * @param intable True allows this semaphore to be interrupted.
         */
        public void setInterruptable(boolean intable) {
            if (intable) value |= SEM_INTERRUPTIBLE;
            else value &= ~SEM_INTERRUPTIBLE;
        }
    }

    private void checkStatus (int status) throws SemaphoreException{
    }

    /**
     * Create a new semaphore.
     * @param options The options to create the semaphore with.
     */
    public Semaphore (Options options) {
    }

    /**
     * Create a semaphore with the given initial state.
     * @param options The options to create the semaphore with.
     * @param initialState The initial state for the semaphore to have.
     */
    public Semaphore (Options options, boolean initialState) {
    }

    /**
     * Create a counting semaphore with the given value.
     * @param options The options to create the semaphore with.
     * @param count The initial count for the semaphore to hold.
     */
    public Semaphore (Options options, int count) {
    }

    /**
     * Unblock every task that is blocked by the semaphore.
     */
    public void flush() throws SemaphoreException{
    }

    /**
     * Release the semaphore.
     */
    public void give() throws SemaphoreException{
    }

    /**
     * Take the semaphore. Block for timeout milliseconds.
     * @param timeout The maximum time in milliseconds to block for the semaphore.
     * @throws SemaphoreException if the lock can't be take in timeout seconds, or some other semaphore error condition occurs.
     */
    public void takeMillis(int timeout) throws SemaphoreException{
        Timer.delay(.020);
        RobotStateSingleton.get().updateLoopListeners();
    }

    /**
     * Take the semaphore. Block forever until semaphore is available.
     * @throws SemaphoreException if some semaphore error condition occurs.
     */
    public void takeForever() throws SemaphoreException{
        takeMillis(WAIT_FOREVER);
    }

    /*
     * Non-blocking version of take(). Try to take the Semaphore.
     * 
     * @return If succeeded return true, otherwise return false.
     * @throws SemaphoreException if some semaphore error condition occurs.
     */
    public boolean tryTake() throws SemaphoreException{
        return false;
    }

    /**
     * Close the semaphore.
     */
    public void close () throws SemaphoreException{
    }

    /**
     * Release all resources associated with the semaphore.
     */
    public void free () throws SemaphoreException{
    }
}
