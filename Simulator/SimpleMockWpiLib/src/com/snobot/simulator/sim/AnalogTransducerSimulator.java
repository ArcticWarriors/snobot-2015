package com.snobot.simulator.sim;

import com.snobot.simulator.AnalogWrapper;
import com.snobot.simulator.CompressorWrapper;
import com.snobot.simulator.SensorActuatorRegistry;

public class AnalogTransducerSimulator implements ISimulatorUpdater
{
    private final AnalogWrapper mTransducer;
    private final CompressorWrapper mCompressor;

    public AnalogTransducerSimulator(AnalogWrapper aTransducer)
    {
        mTransducer = aTransducer;
        mCompressor = SensorActuatorRegistry.get().getCompressor();
    }

    @Override
    public void update()
    {
        mCompressor.update();
        double pressure = mCompressor.getAirPressure();

        double voltage = (pressure + 16.821) / 33.125;

        mTransducer.setVoltage(voltage);

        System.out.println("Air pressure: " + pressure);
    }

}
