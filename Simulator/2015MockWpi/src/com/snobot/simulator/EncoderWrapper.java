package com.snobot.simulator;

public class EncoderWrapper extends ASensorWrapper
{
    public EncoderWrapper(int aIndexA, int aIndexB)
    {
        super("Encoder (" + aIndexA + ", " + aIndexB + ")");
    }

    private SpeedControllerWrapper mSpeedController;

    private double distance_per_tick = 1 / 255.0;

    public double getDecodedDistance()
    {
        if (mSpeedController == null)
        {
            return 0;
        }
        return mSpeedController.getPosition() / 4.0;
    }

    public int getRaw()
    {
        if (mSpeedController == null)
        {
            return 0;
        }
        return (int) (mSpeedController.getPosition() / distance_per_tick);
    }

    public void reset()
    {
        if (mSpeedController != null)
        {
            mSpeedController.resetDistance();
        }
    }

    public void setDistancePerTick(double aDPT)
    {
        distance_per_tick = aDPT;
    }

    public void setSpeedController(SpeedControllerWrapper rightDriveMotor)
    {
        mSpeedController = rightDriveMotor;
    }

}