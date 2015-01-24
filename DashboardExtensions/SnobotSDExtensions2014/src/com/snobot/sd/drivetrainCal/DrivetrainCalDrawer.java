package com.snobot.sd.drivetrainCal;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;

public class DrivetrainCalDrawer extends JPanel {
	
	private static final long serialVersionUID = 491590504195971848L;
	
	private JTextField mTestedWheelBox;
	private JTextField mWheelBias0;
	private JTextField mWheelBias1;
	private JTextField mWheelBias2;
	private JTextField mWheelBias3;
	private JPanel panel;
	

	public void updateWheelBias(
			int aTestedWheel, boolean aCalibrating,
			double aBias0, double aBias1, double aBias2, double aBias3) 
	{
		mTestedWheelBox.setText("" + aTestedWheel);
		
		mWheelBias0.setText("" + aBias0);
		mWheelBias1.setText("" + aBias1);
		mWheelBias2.setText("" + aBias2);
		mWheelBias3.setText("" + aBias3);
		
		if(aCalibrating)
		{
			panel.setBackground(Color.green);
		}
		else
		{
			panel.setBackground(Color.red);
		}
	}
	
	public DrivetrainCalDrawer() {
		initComponents();
	}
	
	private void initComponents()
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lCalibratingLbl = new JLabel("Calibrating");
		GridBagConstraints gbc_lCalibratingLbl = new GridBagConstraints();
		gbc_lCalibratingLbl.insets = new Insets(0, 0, 5, 5);
		gbc_lCalibratingLbl.anchor = GridBagConstraints.EAST;
		gbc_lCalibratingLbl.gridx = 0;
		gbc_lCalibratingLbl.gridy = 0;
		add(lCalibratingLbl, gbc_lCalibratingLbl);
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		
		JLabel lTestedWheelLbl = new JLabel("Tested Wheel");
		GridBagConstraints gbc_lTestedWheelLbl = new GridBagConstraints();
		gbc_lTestedWheelLbl.anchor = GridBagConstraints.EAST;
		gbc_lTestedWheelLbl.insets = new Insets(0, 0, 5, 5);
		gbc_lTestedWheelLbl.gridx = 0;
		gbc_lTestedWheelLbl.gridy = 1;
		add(lTestedWheelLbl, gbc_lTestedWheelLbl);
		
		mTestedWheelBox = new JTextField();
		GridBagConstraints gbc_mTestedWheelBox = new GridBagConstraints();
		gbc_mTestedWheelBox.insets = new Insets(0, 0, 5, 0);
		gbc_mTestedWheelBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_mTestedWheelBox.gridx = 1;
		gbc_mTestedWheelBox.gridy = 1;
		add(mTestedWheelBox, gbc_mTestedWheelBox);
		mTestedWheelBox.setColumns(10);
		
		JLabel lWheelBias0Lbl = new JLabel("Wheel Bias[0]");
		GridBagConstraints gbc_lWheelBias0Lbl = new GridBagConstraints();
		gbc_lWheelBias0Lbl.anchor = GridBagConstraints.EAST;
		gbc_lWheelBias0Lbl.insets = new Insets(0, 0, 5, 5);
		gbc_lWheelBias0Lbl.gridx = 0;
		gbc_lWheelBias0Lbl.gridy = 2;
		add(lWheelBias0Lbl, gbc_lWheelBias0Lbl);
		
		mWheelBias0 = new JTextField();
		GridBagConstraints gbc_mWheelBias0 = new GridBagConstraints();
		gbc_mWheelBias0.insets = new Insets(0, 0, 5, 0);
		gbc_mWheelBias0.fill = GridBagConstraints.HORIZONTAL;
		gbc_mWheelBias0.gridx = 1;
		gbc_mWheelBias0.gridy = 2;
		add(mWheelBias0, gbc_mWheelBias0);
		mWheelBias0.setColumns(10);
		
		JLabel lWheelBias1Lbl = new JLabel("Wheel Bias[1]");
		GridBagConstraints gbc_lWheelBias1Lbl = new GridBagConstraints();
		gbc_lWheelBias1Lbl.anchor = GridBagConstraints.EAST;
		gbc_lWheelBias1Lbl.insets = new Insets(0, 0, 5, 5);
		gbc_lWheelBias1Lbl.gridx = 0;
		gbc_lWheelBias1Lbl.gridy = 3;
		add(lWheelBias1Lbl, gbc_lWheelBias1Lbl);
		
		mWheelBias1 = new JTextField();
		GridBagConstraints gbc_mWheelBias1 = new GridBagConstraints();
		gbc_mWheelBias1.insets = new Insets(0, 0, 5, 0);
		gbc_mWheelBias1.fill = GridBagConstraints.HORIZONTAL;
		gbc_mWheelBias1.gridx = 1;
		gbc_mWheelBias1.gridy = 3;
		add(mWheelBias1, gbc_mWheelBias1);
		mWheelBias1.setColumns(10);
		
		JLabel lWheelBias2Lbl = new JLabel("Wheel Bias[2]");
		GridBagConstraints gbc_lWheelBias2Lbl = new GridBagConstraints();
		gbc_lWheelBias2Lbl.insets = new Insets(0, 0, 5, 5);
		gbc_lWheelBias2Lbl.anchor = GridBagConstraints.EAST;
		gbc_lWheelBias2Lbl.gridx = 0;
		gbc_lWheelBias2Lbl.gridy = 4;
		add(lWheelBias2Lbl, gbc_lWheelBias2Lbl);
		
		mWheelBias2 = new JTextField();
		GridBagConstraints gbc_mWheelBias2 = new GridBagConstraints();
		gbc_mWheelBias2.insets = new Insets(0, 0, 5, 0);
		gbc_mWheelBias2.fill = GridBagConstraints.HORIZONTAL;
		gbc_mWheelBias2.gridx = 1;
		gbc_mWheelBias2.gridy = 4;
		add(mWheelBias2, gbc_mWheelBias2);
		mWheelBias2.setColumns(10);
		
		JLabel lWheelBias3Lbl = new JLabel("Wheel Bias[3]");
		GridBagConstraints gbc_lWheelBias3Lbl = new GridBagConstraints();
		gbc_lWheelBias3Lbl.insets = new Insets(0, 0, 0, 5);
		gbc_lWheelBias3Lbl.anchor = GridBagConstraints.EAST;
		gbc_lWheelBias3Lbl.gridx = 0;
		gbc_lWheelBias3Lbl.gridy = 5;
		add(lWheelBias3Lbl, gbc_lWheelBias3Lbl);
		
		mWheelBias3 = new JTextField();
		GridBagConstraints gbc_mWheelBias3 = new GridBagConstraints();
		gbc_mWheelBias3.fill = GridBagConstraints.HORIZONTAL;
		gbc_mWheelBias3.gridx = 1;
		gbc_mWheelBias3.gridy = 5;
		add(mWheelBias3, gbc_mWheelBias3);
		mWheelBias3.setColumns(10);
	}

	
}
