package com.snobot.simulator;

import edu.wpi.first.wpilibj.Relay.Value;

public class RelayWrapper
{
    private boolean forwards;
    private boolean reverse;

    public Value get()
    {

        if (forwards && !reverse)
        {
            return Value.kForward;
        }
        else if (!forwards && reverse)
        {
            return Value.kReverse;
        }
        else if (forwards && reverse)
        {
            return Value.kOn;
        }
        else
        {
            return Value.kOff;
        }
    }

    public void setRelayForwards(boolean b)
    {
        forwards = b;
    }

    public void setRelayReverse(boolean b)
    {
        reverse = b;
    }

    public boolean getRelayForwards()
    {
        return forwards;
    }

    public boolean getRelayReverse()
    {
        return reverse;
    }

}
