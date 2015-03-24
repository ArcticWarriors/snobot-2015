package com.snobot.sd.path_plotter;

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

public class PathPlotterPanel extends JPanel
{
    private XYSeriesCollection mCollection;
    private XYSeries mIdealVelocity;
    private XYSeries mRealVelocity;

    JPanel m_chartPanel;

    public PathPlotterPanel()
    {
        setLayout(new BorderLayout());
        mIdealVelocity = new XYSeries("Ideal Velocity");
        mRealVelocity = new XYSeries("Real Velocity");

        mCollection = new XYSeriesCollection();
        mCollection.addSeries(mIdealVelocity);
        mCollection.addSeries(mRealVelocity);


        // mIdealVelocity.addChangeListener(arg0);

        // XYSeries

        // final TimeSeriesCollection dataset1 = createRandomDataset("Series 1");
        final JFreeChart chart = ChartFactory.createXYLineChart(
                "XXXX",
                "Time (units)",
                "Data",
                mCollection,
                PlotOrientation.VERTICAL,
                false,
                true,
                false);
        chart.setBackgroundPaint(Color.white);


        m_chartPanel = new ChartPanel(chart);
        m_chartPanel.setPreferredSize(new Dimension(400, 300));
        m_chartPanel.setBackground(getBackground());

        add(m_chartPanel, BorderLayout.CENTER);
    }

    public void setPath(List<Double> path_points)
    {
        mIdealVelocity.clear();
        clearActuals();

        for (int i = 0; i < path_points.size(); ++i)
        {
            mIdealVelocity.add(i, path_points.get(i));
        }
    }

    public void clearActuals()
    {
        mRealVelocity.clear();
    }

    public void setVelPoint(int index, double velocity)
    {
        System.out.println("Setting velocity point at " + index + " to " + velocity);
        mRealVelocity.add(index, velocity);
    }

}
