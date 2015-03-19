package com.snobot.sd.auton;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class AutonDrawer extends JPanel 
{
	private static final long serialVersionUID = -5686692507280263529L;
	
	private JTextField mCurrentCommandBox;
	private JTextArea mAutonCommands = new JTextArea();
	private JPanel mSuccessfulReadPanel = new JPanel();
	private JTextField mModeNumberBox;
	private JTextField mModeNameBox;

	public AutonDrawer()
	{
		initComponents();
	}
	
	public void setAutonInfo(boolean aReadSuccess, int aModeNumber, String aModeName, String aCurrentCommand, String aAllCommands)
	{
		mModeNumberBox.setText("" + aModeNumber);
		mModeNameBox.setText(aModeName);
		mAutonCommands.setText(aAllCommands);
		mCurrentCommandBox.setText(aCurrentCommand);
		
		if(aReadSuccess)
		{
			mSuccessfulReadPanel.setBackground(Color.green);
		}
		else
		{
			mSuccessfulReadPanel.setBackground(Color.red);
		}
		
	}
	
	private void initComponents()
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 135, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblAutonMode = new JLabel("Mode Number");
		GridBagConstraints gbc_lblAutonMode = new GridBagConstraints();
		gbc_lblAutonMode.anchor = GridBagConstraints.EAST;
		gbc_lblAutonMode.insets = new Insets(0, 0, 5, 5);
		gbc_lblAutonMode.gridx = 0;
		gbc_lblAutonMode.gridy = 0;
		add(lblAutonMode, gbc_lblAutonMode);
		
		mModeNumberBox = new JTextField();
		GridBagConstraints gbc_mModeNumberBox = new GridBagConstraints();
		gbc_mModeNumberBox.insets = new Insets(0, 0, 5, 0);
		gbc_mModeNumberBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_mModeNumberBox.gridx = 1;
		gbc_mModeNumberBox.gridy = 0;
		add(mModeNumberBox, gbc_mModeNumberBox);
		mModeNumberBox.setColumns(10);
		
		JLabel lblName = new JLabel("Mode Name");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 1;
		add(lblName, gbc_lblName);
		
		mModeNameBox = new JTextField();
		GridBagConstraints gbc_mModeNameBox = new GridBagConstraints();
		gbc_mModeNameBox.insets = new Insets(0, 0, 5, 0);
		gbc_mModeNameBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_mModeNameBox.gridx = 1;
		gbc_mModeNameBox.gridy = 1;
		add(mModeNameBox, gbc_mModeNameBox);
		mModeNameBox.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Current Command");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 2;
		add(lblNewLabel, gbc_lblNewLabel);
		
		mCurrentCommandBox = new JTextField();
		GridBagConstraints gbc_mCurrentCommandBox = new GridBagConstraints();
		gbc_mCurrentCommandBox.insets = new Insets(0, 0, 5, 0);
		gbc_mCurrentCommandBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_mCurrentCommandBox.gridx = 1;
		gbc_mCurrentCommandBox.gridy = 2;
		add(mCurrentCommandBox, gbc_mCurrentCommandBox);
		mCurrentCommandBox.setColumns(10);
		
		JLabel lblAutonCommands = new JLabel("Auton Commands");
		GridBagConstraints gbc_lblAutonCommands = new GridBagConstraints();
		gbc_lblAutonCommands.insets = new Insets(0, 0, 5, 5);
		gbc_lblAutonCommands.gridx = 0;
		gbc_lblAutonCommands.gridy = 3;
		add(lblAutonCommands, gbc_lblAutonCommands);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;
		add(scrollPane, gbc_scrollPane);
		
		scrollPane.setViewportView(mAutonCommands);
		
		JLabel lblReadFile = new JLabel("Read File");
		GridBagConstraints gbc_lblReadFile = new GridBagConstraints();
		gbc_lblReadFile.insets = new Insets(0, 0, 0, 5);
		gbc_lblReadFile.gridx = 0;
		gbc_lblReadFile.gridy = 4;
		add(lblReadFile, gbc_lblReadFile);
		
		GridBagConstraints gbc_mSuccessfulReadPanel = new GridBagConstraints();
		gbc_mSuccessfulReadPanel.fill = GridBagConstraints.BOTH;
		gbc_mSuccessfulReadPanel.gridx = 1;
		gbc_mSuccessfulReadPanel.gridy = 4;
		add(mSuccessfulReadPanel, gbc_mSuccessfulReadPanel);
		
	}
}
