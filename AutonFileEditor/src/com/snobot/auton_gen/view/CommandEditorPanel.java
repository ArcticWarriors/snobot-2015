package com.snobot.auton_gen.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.snobot.auton_gen.model.CommandConfig;
import com.snobot.auton_gen.model.CommandConfig.CommandArgs;

public class CommandEditorPanel extends JPanel
{
    private static final String sCOMMAND_UPDATED_PROP = "command_updated";

    private JLabel mCommandNameField;
    private JPanel mCommandArgsPanel;
    private int mArgCounter = 0;

    private abstract class X
    {
        public abstract String getValue();

        public abstract String getName();

        public abstract String getType();
    }

    private List<X> mCommandArgs;

    private PropertyChangeSupport mPropertyChange;

    public CommandEditorPanel()
    {
        mPropertyChange = new PropertyChangeSupport(this);
        mCommandArgs = new ArrayList<CommandEditorPanel.X>();

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]
        { 341, 0 };
        gridBagLayout.rowHeights = new int[]
        { 14, 286, 0 };
        gridBagLayout.columnWeights = new double[]
        { 1.0, Double.MIN_VALUE };
        gridBagLayout.rowWeights = new double[]
        { 0.0, 0.0, Double.MIN_VALUE };
        setLayout(gridBagLayout);
        mCommandNameField = new JLabel();

        GridBagConstraints gbc_mCommandNameField = new GridBagConstraints();
        gbc_mCommandNameField.anchor = GridBagConstraints.NORTH;
        gbc_mCommandNameField.fill = GridBagConstraints.HORIZONTAL;
        gbc_mCommandNameField.insets = new Insets(0, 0, 5, 0);
        gbc_mCommandNameField.gridx = 0;
        gbc_mCommandNameField.gridy = 0;
        add(mCommandNameField, gbc_mCommandNameField);

        mCommandArgsPanel = new JPanel();
        GridBagConstraints gbc_mCommandArgsPanel = new GridBagConstraints();
        gbc_mCommandArgsPanel.fill = GridBagConstraints.HORIZONTAL;
        gbc_mCommandArgsPanel.anchor = GridBagConstraints.NORTH;
        gbc_mCommandArgsPanel.gridx = 0;
        gbc_mCommandArgsPanel.gridy = 1;
        add(mCommandArgsPanel, gbc_mCommandArgsPanel);

        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]
        { 85, 48 };
        gbl.columnWeights = new double[]
        { 0.0, 1.0 };
        mCommandArgsPanel.setLayout(gbl);
    }

    private void addArg(String argName, JComponent aEditorBox)
    {
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.anchor = GridBagConstraints.WEST;
        gbc1.gridx = 0;
        gbc1.gridy = mArgCounter;

        mCommandArgsPanel.add(new JLabel(argName), gbc1);

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.gridx = 1;
        gbc2.gridy = mArgCounter;
        mCommandArgsPanel.add(aEditorBox, gbc2);

        ++mArgCounter;
    }

    private void getTextField(String name, String type, String aValue)
    {
        JTextField field = new JTextField(aValue);
        TextFieldGetter getter = new TextFieldGetter(type, name, field);
        mCommandArgs.add(getter);

        getter.mField.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                onUpdate();
            }
        });

        addArg(name, field);
    }

    private void getCheckbox(String name, boolean aValue)
    {
        JCheckBox field = new JCheckBox();
        field.setSelected(aValue);
        CheckboxGetter getter = new CheckboxGetter(name, field);
        mCommandArgs.add(getter);

        getter.mField.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                onUpdate();
            }
        });

        addArg(name, field);
    }

    public void addIntArg(String argName, int defaultValue)
    {
        getTextField(argName, "int", "" + defaultValue);
    }

    public void addDoubleArg(String argName, double defaultValue)
    {
        getTextField(argName, "double", "" + defaultValue);
    }

    public void addStringArg(String argName, String defaultValue)
    {
        getTextField(argName, "String", defaultValue);
    }

    public void addBooleanArg(String argName, boolean defaultValue)
    {
        getCheckbox(argName, defaultValue);
    }

    public void setCommandName(String aCommandName)
    {
        mCommandNameField.setText(aCommandName);
    }

    public void clearArgs()
    {
        mArgCounter = 0;
        mCommandArgsPanel.removeAll();
        mCommandArgs.clear();
        repaint();
    }

    private void onUpdate()
    {
        CommandConfig output = new CommandConfig();

        output.setCommandName(mCommandNameField.getText());

        for (int i = 0; i < mCommandArgs.size(); ++i)
        {
            output.getCommandArgs()
                    .add(new CommandArgs(mCommandArgs.get(i).getName(), mCommandArgs.get(i).getValue(), mCommandArgs.get(i).getType()));
        }

        mPropertyChange.firePropertyChange(sCOMMAND_UPDATED_PROP, null, output);
    }

    public void addUpdateListener(PropertyChangeListener aListener)
    {
        mPropertyChange.addPropertyChangeListener(sCOMMAND_UPDATED_PROP, aListener);
    }

    private class TextFieldGetter extends X
    {
        private JTextField mField;
        private String mType;
        private String mName;

        public TextFieldGetter(String type, String name, JTextField aField)
        {
            mField = aField;
            mType = type;
            mName = name;
        }

        public String getValue()
        {
            return mField.getText();
        }

        public String getName()
        {
            return mName;
        }

        public String getType()
        {
            return mType;
        }
    }

    private class CheckboxGetter extends X
    {
        private JCheckBox mField;
        private String mName;

        public CheckboxGetter(String name, JCheckBox aField)
        {
            mField = aField;
            mName = name;
        }

        public String getValue()
        {
            return "" + mField.isSelected();
        }

        public String getName()
        {
            return mName;
        }

        public String getType()
        {
            return "boolean";
        }
    }
}
