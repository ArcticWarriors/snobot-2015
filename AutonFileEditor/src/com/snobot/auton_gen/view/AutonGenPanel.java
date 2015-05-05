package com.snobot.auton_gen.view;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.snobot.auton_gen.model.CommandConfig;
import com.snobot.auton_gen.model.CommandConfig.CommandArgs;

public class AutonGenPanel extends JPanel
{
    private DefaultListModel<CommandConfig> mCommandListModel;
    private JList<CommandConfig> mCommandsList;
    private CommandEditorPanel mCommandEditorPanel;

    public AutonGenPanel()
    {
        initComponents();
    }

    private void onItemSelected(CommandConfig aItem)
    {
        mCommandEditorPanel.clearArgs();
        mCommandEditorPanel.setCommandName(aItem.getCommandName());

        for (CommandArgs commandArg : aItem.getCommandArgs())
        {
            if (commandArg.type.equals("boolean"))
            {
                mCommandEditorPanel.addBooleanArg(commandArg.argName, Boolean.parseBoolean(commandArg.value));
            }
            else if (commandArg.type.equals("double"))
            {
                mCommandEditorPanel.addDoubleArg(commandArg.argName, Double.parseDouble(commandArg.value));
            }
            else if (commandArg.type.equals("int"))
            {
                mCommandEditorPanel.addIntArg(commandArg.argName, Integer.parseInt(commandArg.value));
            }
            else if (commandArg.type.equals("String"))
            {
                mCommandEditorPanel.addStringArg(commandArg.argName, commandArg.value);
            }
        }

        invalidate();
        validate();
    }

    public void setCommands(List<CommandConfig> aCommands)
    {
        mCommandListModel.clear();

        for (CommandConfig command : aCommands)
        {
            mCommandListModel.addElement(command);
        }

        if (!aCommands.isEmpty())
        {
            mCommandsList.setSelectedIndex(0);
        }
    }

    private void initComponents()
    {
        mCommandListModel = new DefaultListModel<>();
        mCommandsList = new JList<>(mCommandListModel);
        mCommandEditorPanel = new CommandEditorPanel();

        JLabel lblCommands = new JLabel("Commands:");

        JPanel panel = new JPanel();

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(lblCommands)
                                                        .addComponent(mCommandsList, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE))
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(mCommandEditorPanel, GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)))
                                .addContainerGap())
                );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(mCommandEditorPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                        .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(lblCommands)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(mCommandsList, GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18))
                );
        panel.setLayout(new GridLayout(0, 2, 0, 0));

        JButton btnAdd = new JButton("Add");
        panel.add(btnAdd);

        JButton btnRemove = new JButton("Remove");
        panel.add(btnRemove);
        setLayout(groupLayout);

        mCommandsList.getSelectionModel().addListSelectionListener(new ListSelectionListener()
        {

            @Override
            public void valueChanged(ListSelectionEvent arg0)
            {
                CommandConfig config = mCommandsList.getSelectedValue();
                if (!arg0.getValueIsAdjusting() && config != null)
                {
                    onItemSelected(config);
                }
            }
        });
    }
}
