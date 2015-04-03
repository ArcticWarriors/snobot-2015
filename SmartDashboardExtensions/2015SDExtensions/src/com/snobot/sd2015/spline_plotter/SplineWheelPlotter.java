package com.snobot.sd2015.spline_plotter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class SplineWheelPlotter extends JPanel
{
    private XYSeriesCollection mCollection;
    private XYSeries mIdealPosition;
    private XYSeries mIdealVelocity;
    // private XYSeries mIdealRightPosition;
    // private XYSeries mIdealRightVelocity;

    private XYSeries mRealPosition;
    private XYSeries mRealVelocity;
    // private XYSeries mRealRightPosition;
    // private XYSeries mRealRightVelocity;

    JPanel m_chartPanel;

    public SplineWheelPlotter(String chartTitle)
    {
        setLayout(new BorderLayout());
        mIdealPosition = new XYSeries("Ideal  Position");
        mIdealVelocity = new XYSeries("Ideal  Velocity");

        mRealPosition = new XYSeries("Real  Position");
        mRealVelocity = new XYSeries("Real  Velocity");

        mCollection = new XYSeriesCollection();
        mCollection.addSeries(mIdealPosition);
        mCollection.addSeries(mIdealVelocity);
        mCollection.addSeries(mRealPosition);
        mCollection.addSeries(mRealVelocity);

        // final TimeSeriesCollection dataset1 = createRandomDataset("Series 1");
        final JFreeChart chart = ChartFactory.createXYLineChart(
                chartTitle,
                "Time (sec)",
                "Data",
                mCollection,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);
        chart.setBackgroundPaint(Color.white);

        m_chartPanel = new ChartPanel(chart);
        m_chartPanel.setPreferredSize(new Dimension(400, 300));
        m_chartPanel.setBackground(getBackground());

        add(m_chartPanel, BorderLayout.CENTER);
    }

    public void setPath(List<Double> position, List<Double> velocity)
    {
        mIdealPosition.clear();
        mIdealVelocity.clear();
        // mIdealRightPosition.clear();
        // mIdealRightVelocity.clear();
        clearActuals();

        for (int i = 0; i < position.size(); ++i)
        {
            mIdealPosition.add(i, position.get(i));
            mIdealVelocity.add(i, velocity.get(i));
        }
    }

    public void clearActuals()
    {
        mRealPosition.clear();
        mRealVelocity.clear();
    }

    public void setPoint(int index, double position, double velocity)
    {
        mRealPosition.add(index, position);
        mRealVelocity.add(index, velocity);
    }

}
