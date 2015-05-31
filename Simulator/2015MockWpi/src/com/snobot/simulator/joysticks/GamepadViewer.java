package com.snobot.simulator.joysticks;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.java.games.input.Component;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Controller;

public class GamepadViewer extends JPanel
{

    private static final long serialVersionUID = 4539191614222518711L;

    private Map<Identifier, JCheckBox> mButtonToDisplayMap;
    private Map<Identifier, JTextField> mAxisToDisplayMap;

    private Controller mController;

    public GamepadViewer(Controller aController)
    {
        mController = aController;
        mButtonToDisplayMap = new HashMap<Component.Identifier, JCheckBox>();
        mAxisToDisplayMap = new HashMap<Component.Identifier, JTextField>();

        List<Identifier> axisIdentifiers = new ArrayList<Component.Identifier>();
        List<Identifier> buttonIdentifiers = new ArrayList<Component.Identifier>();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Component[] components = mController.getComponents();

        for (int i = 0; i < components.length; i++)
        {

            if (components[i].isAnalog())
            {
                axisIdentifiers.add(components[i].getIdentifier());
            }
            else
            {
                buttonIdentifiers.add(components[i].getIdentifier());
            }
        }

        for (int i = 0; i < axisIdentifiers.size(); ++i)
        {
            JTextField field = new JTextField(10);
            field.setEditable(false);

            JPanel row = new JPanel();
            row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));
            row.add(new JLabel("Component " + i));
            row.add(field);
            add(row);

            mAxisToDisplayMap.put(axisIdentifiers.get(i), field);
        }

        for (int i = 0; i < buttonIdentifiers.size(); ++i)
        {
            JCheckBox field = new JCheckBox();
            field.setEnabled(false);

            JPanel row = new JPanel();
            row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));
            row.add(new JLabel("Component " + i));
            row.add(field);
            add(row);

            mButtonToDisplayMap.put(buttonIdentifiers.get(i), field);
        }
    }

    private DecimalFormat df = new DecimalFormat("0.000");

    public void update()
    {
        mController.poll();

        for (Entry<Identifier, JCheckBox> pair : mButtonToDisplayMap.entrySet())
        {
            boolean set = mController.getComponent(pair.getKey()).getPollData() == 1;
            mButtonToDisplayMap.get(pair.getKey()).setSelected(set);
        }

        for (Entry<Identifier, JTextField> pair : mAxisToDisplayMap.entrySet())
        {
            double value = mController.getComponent(pair.getKey()).getPollData();
            mAxisToDisplayMap.get(pair.getKey()).setText(df.format(value));
        }
    }
}
