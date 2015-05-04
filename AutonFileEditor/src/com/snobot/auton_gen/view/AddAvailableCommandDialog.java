package com.snobot.auton_gen.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.snobot.auton_gen.model.CommandConfig;

public class AddAvailableCommandDialog extends JDialog
{
    private final JPanel mCommandArgsPanel = new JPanel();

    // private JTextField mCommandNameField = new JTextField("");
    // private JTextField mDefaultValueField = new JTextField("");
    // private JComboBox<String> mTypeBox = new JComboBox<String>();

    private boolean mWasOkHit;
    private int mArgCounter = 0;

    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
        try
        {
            AddAvailableCommandDialog dialog = new AddAvailableCommandDialog();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
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

        addArg("", null);
        addArg("", null);
        addArg("", null);
        addArg("", null);
        addArg("", null);

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
                        dispose();
                    }
                });
            }
            {
                JButton cancelButton = new JButton("Cancel");
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
                {
                    JLabel lblCommandName = new JLabel("Command Name");
                    getContentPane().add(lblCommandName, BorderLayout.NORTH);
                }
                GridBagLayout gbl_mCommandArgsPanel = new GridBagLayout();
                getContentPane().add(mCommandArgsPanel, BorderLayout.CENTER);
                mCommandArgsPanel.setLayout(gbl_mCommandArgsPanel);


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
    }

    private void addArg(String argName, JComponent aEditorBox)
    {
        JTextField mArgNameField = new JTextField("");
        JTextField mDefaultValueField = new JTextField("");
        JComboBox<String> mTypeBox = new JComboBox<String>();

        // Name
        {
            JLabel lblArgName = new JLabel("Arg Name");
            GridBagConstraints gbc_lblArgName = new GridBagConstraints();
            gbc_lblArgName.insets = new Insets(0, 0, 5, 5);
            gbc_lblArgName.fill = GridBagConstraints.BOTH;
            gbc_lblArgName.gridx = 0;
            gbc_lblArgName.gridy = mArgCounter * 4 + 0;
            mCommandArgsPanel.add(lblArgName, gbc_lblArgName);
        }
        {
            GridBagConstraints gbc_mCommandNameField = new GridBagConstraints();
            gbc_mCommandNameField.fill = GridBagConstraints.HORIZONTAL;
            gbc_mCommandNameField.insets = new Insets(0, 0, 5, 0);
            gbc_mCommandNameField.gridx = 1;
            gbc_mCommandNameField.gridy = mArgCounter * 4 + 0;
            mCommandArgsPanel.add(mArgNameField, gbc_mCommandNameField);
        }

        // Default
        {
            JLabel lblDefaultValue = new JLabel("Default Value");
            GridBagConstraints gbc_lblDefaultValue = new GridBagConstraints();
            gbc_lblDefaultValue.fill = GridBagConstraints.HORIZONTAL;
            gbc_lblDefaultValue.insets = new Insets(0, 0, 5, 5);
            gbc_lblDefaultValue.gridx = 0;
            gbc_lblDefaultValue.gridy = mArgCounter * 4 + 1;
            System.out.println(gbc_lblDefaultValue);
            mCommandArgsPanel.add(lblDefaultValue, gbc_lblDefaultValue);
        }
        {
            GridBagConstraints gbc_mDefaultValueField = new GridBagConstraints();
            gbc_mDefaultValueField.fill = GridBagConstraints.HORIZONTAL;
            gbc_mDefaultValueField.insets = new Insets(0, 0, 5, 0);
            gbc_mDefaultValueField.gridx = 1;
            gbc_mDefaultValueField.gridy = mArgCounter * 4 + 1;
            mCommandArgsPanel.add(mDefaultValueField, gbc_mDefaultValueField);
        }

        // Type
        {
            JLabel lblType = new JLabel("Type");
            GridBagConstraints gbc_lblType = new GridBagConstraints();
            gbc_lblType.fill = GridBagConstraints.HORIZONTAL;
            gbc_lblType.insets = new Insets(0, 0, 0, 5);
            gbc_lblType.gridx = 0;
            gbc_lblType.gridy = mArgCounter * 4 + 2;
            mCommandArgsPanel.add(lblType, gbc_lblType);
        }
        {
            GridBagConstraints gbc_mTypeBox = new GridBagConstraints();
            gbc_mTypeBox.fill = GridBagConstraints.HORIZONTAL;
            gbc_mTypeBox.gridx = 1;
            gbc_mTypeBox.gridy = mArgCounter * 4 + 2;
            mTypeBox.setModel(new DefaultComboBoxModel<String>(new String[]
            { "integer", "double", "boolean", "String" }));
            mCommandArgsPanel.add(mTypeBox, gbc_mTypeBox);
        }

        ++mArgCounter;
    }

    public boolean wasOkHit()
    {
        return mWasOkHit;
    }

    public CommandConfig getCommandConfig()
    {
        CommandConfig output = new CommandConfig();

        return output;
    }

}
