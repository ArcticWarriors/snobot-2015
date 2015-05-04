package com.snobot.auton_gen.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CommandEditorPanel extends JPanel
{
    private JLabel mCommandNameField;
    private JPanel mCommandArgsPanel;
    private int mArgCounter = 0;

    public CommandEditorPanel()
    {
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

    public void addIntArg(String argName, int defaultValue)
    {
        addArg(argName, new JTextField("" + defaultValue));
    }

    public void addDoubleArg(String argName, double defaultValue)
    {
        addArg(argName, new JTextField("" + defaultValue));
    }

    public void addStringArg(String argName, String defaultValue)
    {
        addArg(argName, new JTextField(defaultValue));
    }

    public void addBooleanArg(String argName, boolean defaultValue)
    {
        JCheckBox box = new JCheckBox();
        box.setSelected(defaultValue);
        addArg(argName, box);
    }

    public void setCommandName(String aCommandName)
    {
        mCommandNameField.setText(aCommandName);
    }

    public void clearArgs()
    {
        mArgCounter = 0;
        mCommandArgsPanel.removeAll();
        repaint();
    }
}
