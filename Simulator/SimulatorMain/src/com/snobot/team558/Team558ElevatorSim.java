package com.snobot.team558;

import com.snobot.simulator.DigitalSourceWrapper;
import com.snobot.simulator.EncoderWrapper;

public class Team558ElevatorSim
{
    EncoderWrapper mElevatorEncoder;
    DigitalSourceWrapper mLowerLimitSwitch;
    DigitalSourceWrapper mUpperLimitSwitch;

    public Team558ElevatorSim(EncoderWrapper elevatorEncoder, DigitalSourceWrapper lowerLimitSwitch, DigitalSourceWrapper upperLimitSwitch)
    {
        mElevatorEncoder = elevatorEncoder;
        mLowerLimitSwitch = lowerLimitSwitch;
        mUpperLimitSwitch = upperLimitSwitch;
    }

    public void update()
    {
        double distance = mElevatorEncoder.getDecodedDistance();

        if (distance < 0)
        {
            mLowerLimitSwitch.set(false);
        }
        else
        {
            mLowerLimitSwitch.set(true);
        }


        if (distance > 20)
        {
            mUpperLimitSwitch.set(false);
        }
        else
        {
            mUpperLimitSwitch.set(true);
        }
    }
}
