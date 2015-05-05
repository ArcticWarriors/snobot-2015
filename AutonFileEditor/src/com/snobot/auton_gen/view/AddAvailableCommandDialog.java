package com.snobot.auton_gen.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.snobot.auton_gen.model.CommandConfig;
import com.snobot.auton_gen.model.CommandConfig.CommandArgs;

public class AddAvailableCommandDialog extends JDialog
{
    private final JPanel mCommandArgsPanel = new JPanel();

    private boolean mWasOkHit;
    private int mArgCounter = 0;
    private JTextField mCommandNameField;

    private List<Storage> mStorages = new ArrayList<Storage>();

    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
        try
        {
            AddAvailableCommandDialog dialog = new AddAvailableCommandDialog();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

            dialog.addArg();
            dialog.addArg();
            dialog.addArg();
            dialog.addArg();
            dialog.addArg();

            dialog.pack();
            dialog.setVisible(true);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public AddAvailableCommandDialog()
    {
        initComponents();

        mWasOkHit = false;
    }

    private void initComponents()
    {
        setBounds(100, 100, 450, 168);
        getContentPane().setLayout(new BorderLayout());


        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("OK");
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);

                okButton.addActionListener(new ActionListener()
                {

                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        mWasOkHit = true;

                        if (mCommandNameField.getText().isEmpty())
                        {
                            JOptionPane.showMessageDialog(AddAvailableCommandDialog.this, "Command name cannot be empty");
                        }
                        else
                        {
                            dispose();
                        }
                    }
                });
            }
            {
                JButton cancelButton = new JButton("Cancel");
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);


                cancelButton.addActionListener(new ActionListener()
                {

                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        dispose();
                    }
                });
            }
        }
        {
            JPanel panel = new JPanel();
            getContentPane().add(panel, BorderLayout.CENTER);
            panel.setLayout(new BorderLayout(0, 0));
            GridBagLayout gbl_mCommandArgsPanel = new GridBagLayout();
            gbl_mCommandArgsPanel.columnWeights = new double[]
            { 1.0, 1.0, 0.0 };

            JScrollPane pane = new JScrollPane();
            pane.setViewportView(mCommandArgsPanel);

            panel.add(pane, BorderLayout.CENTER);
            mCommandArgsPanel.setLayout(gbl_mCommandArgsPanel);

            {
                JLabel lblArgName = new JLabel("Arg Name");
                GridBagConstraints gbc_lblArgName = new GridBagConstraints();
                gbc_lblArgName.insets = new Insets(0, 0, 5, 5);
                gbc_lblArgName.fill = GridBagConstraints.BOTH;
                gbc_lblArgName.gridx = 0;
                gbc_lblArgName.gridy = 0;
                mCommandArgsPanel.add(lblArgName, gbc_lblArgName);
            }
            {
                JLabel lblDefaultValue = new JLabel("Default Value");
                GridBagConstraints gbc_lblDefaultValue = new GridBagConstraints();
                gbc_lblDefaultValue.fill = GridBagConstraints.HORIZONTAL;
                gbc_lblDefaultValue.insets = new Insets(0, 0, 5, 5);
                gbc_lblDefaultValue.gridx = 1;
                gbc_lblDefaultValue.gridy = 0;
                System.out.println(gbc_lblDefaultValue);
                mCommandArgsPanel.add(lblDefaultValue, gbc_lblDefaultValue);
            }
            {
                JLabel lblType = new JLabel("Type");
                GridBagConstraints gbc_lblType = new GridBagConstraints();
                gbc_lblType.fill = GridBagConstraints.HORIZONTAL;
                gbc_lblType.insets = new Insets(0, 0, 0, 5);
                gbc_lblType.gridx = 2;
                gbc_lblType.gridy = 0;
                mCommandArgsPanel.add(lblType, gbc_lblType);
            }
            {
                JButton addArgBtn = new JButton("Add Agument");
                panel.add(addArgBtn, BorderLayout.SOUTH);

                addArgBtn.addActionListener(new ActionListener()
                {

                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        addArg();
                        invalidate();
                        revalidate();
                    }
                });
            }
        }
        {
            JPanel panel = new JPanel();
            getContentPane().add(panel, BorderLayout.NORTH);
            {
                JLabel lblCommandName = new JLabel("Command Name");
                panel.add(lblCommandName);
            }
            {
                mCommandNameField = new JTextField();
                panel.add(mCommandNameField);
                mCommandNameField.setColumns(10);
            }
        }
    }

    private class Storage
    {
        private JTextField mArgNameField = new JTextField("");
        private JTextField mDefaultValueField = new JTextField("");
        private JComboBox<String> mTypeBox = new JComboBox<String>();

        public Storage()
        {

        }
    }

    private void addArg()
    {
        Storage storage = new Storage();

        {
            GridBagConstraints gbc_mCommandNameField = new GridBagConstraints();
            gbc_mCommandNameField.fill = GridBagConstraints.HORIZONTAL;
            gbc_mCommandNameField.insets = new Insets(0, 0, 5, 0);
            gbc_mCommandNameField.gridx = 0;
            gbc_mCommandNameField.gridy = mArgCounter + 1;
            mCommandArgsPanel.add(storage.mArgNameField, gbc_mCommandNameField);
        }
        {
            GridBagConstraints gbc_mDefaultValueField = new GridBagConstraints();
            gbc_mDefaultValueField.fill = GridBagConstraints.HORIZONTAL;
            gbc_mDefaultValueField.insets = new Insets(0, 0, 5, 0);
            gbc_mDefaultValueField.gridx = 1;
            gbc_mDefaultValueField.gridy = mArgCounter + 1;
            mCommandArgsPanel.add(storage.mDefaultValueField, gbc_mDefaultValueField);
        }

        {
            GridBagConstraints gbc_mTypeBox = new GridBagConstraints();
            gbc_mTypeBox.fill = GridBagConstraints.HORIZONTAL;
            gbc_mTypeBox.gridx = 2;
            gbc_mTypeBox.gridy = mArgCounter + 1;
            storage.mTypeBox.setModel(new DefaultComboBoxModel<String>(new String[]
            { "integer", "double", "boolean", "String" }));
            mCommandArgsPanel.add(storage.mTypeBox, gbc_mTypeBox);
        }

        mStorages.add(storage);

        ++mArgCounter;
    }

    public boolean wasOkHit()
    {
        return mWasOkHit;
    }

    public CommandConfig getCommandConfig()
    {
        CommandConfig output = new CommandConfig();

        output.setCommandName(mCommandNameField.getText());

        for (int i = 0; i < mStorages.size(); ++i)
        {
            Storage storage = mStorages.get(i);

            output.getCommandArgs().add(
                    new CommandArgs(storage.mArgNameField.getText(), storage.mDefaultValueField.getText(), storage.mTypeBox.getSelectedItem()
                            .toString()));
        }

        return output;
    }

}
