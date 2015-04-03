package com.snobot.simulator;

public class EncoderPair
{
    public int portA;
    public int portB;

    public EncoderPair(int aPortA, int aPortB)
    {
        portA = aPortA;
        portB = aPortB;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + portA;
        result = prime * result + portB;
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EncoderPair other = (EncoderPair) obj;
        if (portA != other.portA)
            return false;
        if (portB != other.portB)
            return false;
        return true;
    }

}