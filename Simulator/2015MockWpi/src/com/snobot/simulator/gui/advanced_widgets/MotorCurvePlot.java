package com.snobot.simulator.gui.advanced_widgets;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.snobot.simulator.MotorParameters;
import com.snobot.simulator.MotorParameters.CIM_MotorParams;

public class MotorCurvePlot extends JPanel
{
    private XYSeries mCurrent;
    private XYSeries mSpeedCurve;
    private XYSeries mPowerCurve;
    private XYSeries mEfficiency;

    private final JFreeChart mChart;

    public MotorCurvePlot()
    {

        mSpeedCurve = new XYSeries("Speed");
        mPowerCurve = new XYSeries("Power");
        mCurrent = new XYSeries("Current");
        mEfficiency = new XYSeries("Efficiency");

        XYSeriesCollection rightSeries = new XYSeriesCollection();
        XYSeriesCollection leftSeries = new XYSeriesCollection();

        rightSeries.addSeries(mSpeedCurve);
        leftSeries.addSeries(mCurrent);
        leftSeries.addSeries(mPowerCurve);
        leftSeries.addSeries(mEfficiency);

        mChart = ChartFactory.createXYLineChart(
                "No Title",
                "Load (N-m)",
                "",
                null,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);

        mChart.setTitle("");

        mChart.setBackgroundPaint(Color.white);

        setupRightPlot(mChart, rightSeries);
        setupLeftPlot(mChart, leftSeries);

        JPanel chartPanel = new ChartPanel(mChart);
        chartPanel.setPreferredSize(new Dimension(400, 300));
        chartPanel.setBackground(getBackground());

        setLayout(new BorderLayout());
        add(chartPanel, BorderLayout.CENTER);
    }

    private void setupRightPlot(JFreeChart chart, XYSeriesCollection series)
    {
        int plot_location = 0;

        final XYPlot plot = chart.getXYPlot();
        final NumberAxis axis = new NumberAxis("Current (A), Efficiency (%), Power (W)");
        final StandardXYItemRenderer renderer = new StandardXYItemRenderer();
        renderer.setSeriesPaint(0, Color.blue);
        axis.setAutoRangeIncludesZero(false);
        plot.setRangeAxis(plot_location, axis);
        plot.setDataset(plot_location, series);
        plot.mapDatasetToRangeAxis(plot_location, plot_location);
        plot.setRenderer(plot_location, renderer);
    }

    private void setupLeftPlot(JFreeChart chart, XYSeriesCollection series)
    {
        int plot_location = 1;

        final XYPlot plot = chart.getXYPlot();
        final NumberAxis axis = new NumberAxis("Current (A), Efficiency (%), Power (W)");
        final StandardXYItemRenderer renderer = new StandardXYItemRenderer();
        renderer.setSeriesPaint(0, Color.red);
        renderer.setSeriesPaint(1, Color.green);
        renderer.setSeriesPaint(2, Color.magenta);
        axis.setAutoRangeIncludesZero(false);
        plot.setRangeAxis(plot_location, axis);
        plot.setDataset(plot_location, series);
        plot.mapDatasetToRangeAxis(plot_location, plot_location);
        plot.setRenderer(plot_location, renderer);
    }

    public void setMotorParams(MotorParameters aParams)
    {
        mChart.setTitle(aParams.mMotorName);

        double speedSlope = (0 - aParams.mFreeSpeed_RPM) / aParams.mStallTorque_NM;
        double currentSlope = (aParams.mStallCurrent_Amp - aParams.mFreeCurrent_Amp) / aParams.mStallTorque_NM;

        for (double torque = 0; torque < aParams.mStallTorque_NM; torque += .01)
        {
            double speed = torque * speedSlope + aParams.mFreeSpeed_RPM;
            double current = torque * currentSlope + aParams.mFreeCurrent_Amp;
            double power = speed * (1 / 60.0 * 2 * Math.PI) * torque;
            double efficiency = power / (12 * current) * 100;

            mCurrent.add(torque, current);
            mSpeedCurve.add(torque, speed);
            mPowerCurve.add(torque, power);
            mEfficiency.add(torque, efficiency);
        }
    }

    public static void main(String[] args) throws IOException
    {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MotorCurvePlot panel = new MotorCurvePlot();
        frame.add(panel);

        MotorParameters params = new CIM_MotorParams();
        panel.setMotorParams(params);

        frame.setVisible(true);
        frame.pack();

    }
}
