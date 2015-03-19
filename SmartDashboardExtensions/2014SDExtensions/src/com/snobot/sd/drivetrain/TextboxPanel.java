package com.snobot.sd.drivetrain;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class TextboxPanel extends JPanel 
{
	private static final long serialVersionUID = -243167405158277408L;
	
	private static final String sNUMBER_FORMAT_STRING = "%.2f";

	private JTextField mAngle0Field;
	private JTextField mAngle1Field;
	private JTextField mAngle2Field;
	private JTextField mAngle3Field;
	private JTextField mXSpeedBox;
	private JTextField mYSpeedBox;
	private JTextField mSteeringSpeed0Box;
	private JTextField mSteeringSpeed1Box;
	private JTextField mSteeringSpeed2Box;
	private JTextField mSteeringSpeed3Box;
	private JTextField mDrivetrainSpeed0Box;
	private JTextField mDrivetrainSpeed1Box;
	private JTextField mDrivetrainSpeed2Box;
	private JTextField mDrivetrainSpeed3Box;
	private JTextField mAngleBox;
	private JCheckBox mUseGyroCheckbox;

	private JLabel mDriveLabel0;
	private JLabel mDriveLabel1;
	private JLabel mDriveLabel2;
	private JLabel mDriveLabel3;
	private JLabel lblDriveMode;
	private JTextField mDriveModeBox;
	
	public TextboxPanel() 
	{
		initComponents();
	}

	public void updateWheelAngles(double aPortFrontAngle,
			double aPortBackAngle, double aStarFrontAngle, double aStarBackAngle) {
		mAngle0Field.setText(String.format(sNUMBER_FORMAT_STRING, aPortFrontAngle));
		mAngle1Field.setText(String.format(sNUMBER_FORMAT_STRING, aPortBackAngle));
		mAngle2Field.setText(String.format(sNUMBER_FORMAT_STRING, aStarFrontAngle));
		mAngle3Field.setText(String.format(sNUMBER_FORMAT_STRING, aStarBackAngle));
	}

	public void updateRobotSpeed(double aXSpeed, double aYSpeed) {
		mXSpeedBox.setText(String.format(sNUMBER_FORMAT_STRING, aXSpeed));
		mYSpeedBox.setText(String.format(sNUMBER_FORMAT_STRING, aYSpeed));
	}

	public void updateWheelSpeed(double aPortFrontSpeed, double aPortBckSpeed,
			double aStarFrontSpeed, double aStarBckSpeed) {
		
		mSteeringSpeed0Box.setText(String.format(sNUMBER_FORMAT_STRING, aPortFrontSpeed));
		mSteeringSpeed1Box.setText(String.format(sNUMBER_FORMAT_STRING, aPortBckSpeed));
		mSteeringSpeed2Box.setText(String.format(sNUMBER_FORMAT_STRING, aStarFrontSpeed));
		mSteeringSpeed3Box.setText(String.format(sNUMBER_FORMAT_STRING, aStarBckSpeed));
	}

	public void updateDrivetrainSpeed(double aPortFrontSpeed, double aPortBckSpeed,
			double aStarFrontSpeed, double aStarBckSpeed) {
		
		mDrivetrainSpeed0Box.setText(String.format(sNUMBER_FORMAT_STRING, aPortFrontSpeed));
		mDrivetrainSpeed1Box.setText(String.format(sNUMBER_FORMAT_STRING, aPortBckSpeed));
		mDrivetrainSpeed2Box.setText(String.format(sNUMBER_FORMAT_STRING, aStarFrontSpeed));
		mDrivetrainSpeed3Box.setText(String.format(sNUMBER_FORMAT_STRING, aStarBckSpeed));
	}

	public void updateRobotAngle(double aRobotAngle) {
		mAngleBox.setText(String.format(sNUMBER_FORMAT_STRING, aRobotAngle));
	}

	public boolean isUseGyroSelected() {
		return mUseGyroCheckbox.isSelected();
	}
	
	public void setDriveMode(String aDriveMode) {
		mDriveModeBox.setText(aDriveMode);
	}
	
	private void initComponents()
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 87, 73, 0, 72 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel mAngle0Lbl = new JLabel("Angle[0]");

		// //////////////////////////////////////
		GridBagConstraints gbc_mAngle0Lbl = new GridBagConstraints();
		gbc_mAngle0Lbl.anchor = GridBagConstraints.EAST;
		gbc_mAngle0Lbl.fill = GridBagConstraints.VERTICAL;
		gbc_mAngle0Lbl.insets = new Insets(0, 0, 5, 5);
		gbc_mAngle0Lbl.gridx = 0;
		gbc_mAngle0Lbl.gridy = 0;
		add(mAngle0Lbl, gbc_mAngle0Lbl);

		mAngle0Field = new JTextField();
		GridBagConstraints gbc_mAngle0Field = new GridBagConstraints();
		gbc_mAngle0Field.insets = new Insets(0, 0, 5, 5);
		gbc_mAngle0Field.fill = GridBagConstraints.BOTH;
		gbc_mAngle0Field.gridx = 1;
		gbc_mAngle0Field.gridy = 0;
		add(mAngle0Field, gbc_mAngle0Field);
		
				JLabel lSpeed0Lbl = new JLabel("Speed[0]");
				
						// ////////////////////////////////////
						GridBagConstraints gbc_lSpeed0Lbl = new GridBagConstraints();
						gbc_lSpeed0Lbl.anchor = GridBagConstraints.EAST;
						gbc_lSpeed0Lbl.fill = GridBagConstraints.VERTICAL;
						gbc_lSpeed0Lbl.insets = new Insets(0, 0, 5, 5);
						gbc_lSpeed0Lbl.gridx = 2;
						gbc_lSpeed0Lbl.gridy = 0;
						add(lSpeed0Lbl, gbc_lSpeed0Lbl);
		
				mSteeringSpeed0Box = new JTextField();
				GridBagConstraints gbc_mSteeringSpeed0Box = new GridBagConstraints();
				gbc_mSteeringSpeed0Box.fill = GridBagConstraints.BOTH;
				gbc_mSteeringSpeed0Box.insets = new Insets(0, 0, 5, 0);
				gbc_mSteeringSpeed0Box.gridx = 3;
				gbc_mSteeringSpeed0Box.gridy = 0;
				add(mSteeringSpeed0Box, gbc_mSteeringSpeed0Box);
				mSteeringSpeed0Box.setHorizontalAlignment(JTextField.RIGHT);
		JLabel mAngle1Lbl = new JLabel("Angle[1]");

		GridBagConstraints gbc_mAngle1Lbl = new GridBagConstraints();
		gbc_mAngle1Lbl.anchor = GridBagConstraints.EAST;
		gbc_mAngle1Lbl.fill = GridBagConstraints.VERTICAL;
		gbc_mAngle1Lbl.insets = new Insets(0, 0, 5, 5);
		gbc_mAngle1Lbl.gridx = 0;
		gbc_mAngle1Lbl.gridy = 1;
		add(mAngle1Lbl, gbc_mAngle1Lbl);
		
		mAngle1Field = new JTextField();
		GridBagConstraints gbc_mAngle1Field = new GridBagConstraints();
		gbc_mAngle1Field.fill = GridBagConstraints.BOTH;
		gbc_mAngle1Field.insets = new Insets(0, 0, 5, 5);
		gbc_mAngle1Field.gridx = 1;
		gbc_mAngle1Field.gridy = 1;
		add(mAngle1Field, gbc_mAngle1Field);
		
		JLabel lSpeed1Lbl = new JLabel("Speed[1]");
		GridBagConstraints gbc_lSpeed1Lbl = new GridBagConstraints();
		gbc_lSpeed1Lbl.anchor = GridBagConstraints.EAST;
		gbc_lSpeed1Lbl.fill = GridBagConstraints.VERTICAL;
		gbc_lSpeed1Lbl.insets = new Insets(0, 0, 5, 5);
		gbc_lSpeed1Lbl.gridx = 2;
		gbc_lSpeed1Lbl.gridy = 1;
		add(lSpeed1Lbl, gbc_lSpeed1Lbl);
				
				
		mSteeringSpeed1Box = new JTextField();
		GridBagConstraints gbc_mSteeringSpeed1Box = new GridBagConstraints();
		gbc_mSteeringSpeed1Box.fill = GridBagConstraints.BOTH;
		gbc_mSteeringSpeed1Box.insets = new Insets(0, 0, 5, 0);
		gbc_mSteeringSpeed1Box.gridx = 3;
		gbc_mSteeringSpeed1Box.gridy = 1;
		add(mSteeringSpeed1Box, gbc_mSteeringSpeed1Box);
		mSteeringSpeed1Box.setHorizontalAlignment(JTextField.RIGHT);
		
		JLabel mAngle2Lbl = new JLabel("Angle[2]");
		GridBagConstraints gbc_mAngle2Lbl = new GridBagConstraints();
		gbc_mAngle2Lbl.anchor = GridBagConstraints.EAST;
		gbc_mAngle2Lbl.fill = GridBagConstraints.VERTICAL;
		gbc_mAngle2Lbl.insets = new Insets(0, 0, 5, 5);
		gbc_mAngle2Lbl.gridx = 0;
		gbc_mAngle2Lbl.gridy = 2;
		add(mAngle2Lbl, gbc_mAngle2Lbl);
		
		mAngle2Field = new JTextField();
		GridBagConstraints gbc_mAngle2Field = new GridBagConstraints();
		gbc_mAngle2Field.fill = GridBagConstraints.BOTH;
		gbc_mAngle2Field.insets = new Insets(0, 0, 5, 5);
		gbc_mAngle2Field.gridx = 1;
		gbc_mAngle2Field.gridy = 2;
		add(mAngle2Field, gbc_mAngle2Field);
		
		JLabel lSpeed2Lbl = new JLabel("Speed[2]");
		GridBagConstraints gbc_lSpeed2Lbl = new GridBagConstraints();
		gbc_lSpeed2Lbl.anchor = GridBagConstraints.EAST;
		gbc_lSpeed2Lbl.fill = GridBagConstraints.VERTICAL;
		gbc_lSpeed2Lbl.insets = new Insets(0, 0, 5, 5);
		gbc_lSpeed2Lbl.gridx = 2;
		gbc_lSpeed2Lbl.gridy = 2;
		add(lSpeed2Lbl, gbc_lSpeed2Lbl);
			
		mSteeringSpeed2Box = new JTextField();
		GridBagConstraints gbc_mSteeringSpeed2Box = new GridBagConstraints();
		gbc_mSteeringSpeed2Box.fill = GridBagConstraints.BOTH;
		gbc_mSteeringSpeed2Box.insets = new Insets(0, 0, 5, 0);
		gbc_mSteeringSpeed2Box.gridx = 3;
		gbc_mSteeringSpeed2Box.gridy = 2;
		add(mSteeringSpeed2Box, gbc_mSteeringSpeed2Box);
		mSteeringSpeed2Box.setHorizontalAlignment(JTextField.RIGHT);
		JLabel mAngle3Lbl = new JLabel("Angle[3]");

		GridBagConstraints gbc_mAngle3Lbl = new GridBagConstraints();
		gbc_mAngle3Lbl.anchor = GridBagConstraints.EAST;
		gbc_mAngle3Lbl.fill = GridBagConstraints.VERTICAL;
		gbc_mAngle3Lbl.insets = new Insets(0, 0, 5, 5);
		gbc_mAngle3Lbl.gridx = 0;
		gbc_mAngle3Lbl.gridy = 3;
		add(mAngle3Lbl, gbc_mAngle3Lbl);
		
		mAngle3Field = new JTextField();
		GridBagConstraints gbc_mAngle3Field = new GridBagConstraints();
		gbc_mAngle3Field.fill = GridBagConstraints.BOTH;
		gbc_mAngle3Field.insets = new Insets(0, 0, 5, 5);
		gbc_mAngle3Field.gridx = 1;
		gbc_mAngle3Field.gridy = 3;
		add(mAngle3Field, gbc_mAngle3Field);
		mAngle3Field.setHorizontalAlignment(JTextField.RIGHT);
		
		JLabel lSpeed3Lbl = new JLabel("Speed[3]");
		GridBagConstraints gbc_lSpeed3Lbl = new GridBagConstraints();
		gbc_lSpeed3Lbl.anchor = GridBagConstraints.EAST;
		gbc_lSpeed3Lbl.fill = GridBagConstraints.VERTICAL;
		gbc_lSpeed3Lbl.insets = new Insets(0, 0, 5, 5);
		gbc_lSpeed3Lbl.gridx = 2;
		gbc_lSpeed3Lbl.gridy = 3;
		add(lSpeed3Lbl, gbc_lSpeed3Lbl);
				
		mSteeringSpeed3Box = new JTextField();
		GridBagConstraints gbc_mSteeringSpeed3Box = new GridBagConstraints();
		gbc_mSteeringSpeed3Box.fill = GridBagConstraints.BOTH;
		gbc_mSteeringSpeed3Box.insets = new Insets(0, 0, 5, 0);
		gbc_mSteeringSpeed3Box.gridx = 3;
		gbc_mSteeringSpeed3Box.gridy = 3;
		add(mSteeringSpeed3Box, gbc_mSteeringSpeed3Box);
		mSteeringSpeed3Box.setHorizontalAlignment(JTextField.RIGHT);
		
		mDriveLabel0 = new JLabel("Drive[0]");
		GridBagConstraints gbc_mDriveLabel0 = new GridBagConstraints();
		gbc_mDriveLabel0.insets = new Insets(0, 0, 5, 5);
		gbc_mDriveLabel0.anchor = GridBagConstraints.EAST;
		gbc_mDriveLabel0.gridx = 0;
		gbc_mDriveLabel0.gridy = 4;
		add(mDriveLabel0, gbc_mDriveLabel0);
		
		

		mDrivetrainSpeed0Box = new JTextField();
		GridBagConstraints gbc_mDrivetrainSpeed0Box = new GridBagConstraints();
		gbc_mDrivetrainSpeed0Box.fill = GridBagConstraints.HORIZONTAL;
		gbc_mDrivetrainSpeed0Box.insets = new Insets(0, 0, 5, 5);
		gbc_mDrivetrainSpeed0Box.gridx = 1;
		gbc_mDrivetrainSpeed0Box.gridy = 4;
		add(mDrivetrainSpeed0Box, gbc_mDrivetrainSpeed0Box);
		
		lblDriveMode = new JLabel("Drive Mode");
		GridBagConstraints gbc_lblDriveMode = new GridBagConstraints();
		gbc_lblDriveMode.anchor = GridBagConstraints.EAST;
		gbc_lblDriveMode.insets = new Insets(0, 0, 5, 5);
		gbc_lblDriveMode.gridx = 2;
		gbc_lblDriveMode.gridy = 4;
		add(lblDriveMode, gbc_lblDriveMode);
		
		mDriveModeBox = new JTextField();
		mDriveModeBox.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_mDriveModeBox = new GridBagConstraints();
		gbc_mDriveModeBox.insets = new Insets(0, 0, 5, 0);
		gbc_mDriveModeBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_mDriveModeBox.gridx = 3;
		gbc_mDriveModeBox.gridy = 4;
		add(mDriveModeBox, gbc_mDriveModeBox);
		
		mDriveLabel1 = new JLabel("Drive[1]");
		GridBagConstraints gbc_mDriveLabel1 = new GridBagConstraints();
		gbc_mDriveLabel1.insets = new Insets(0, 0, 5, 5);
		gbc_mDriveLabel1.anchor = GridBagConstraints.EAST;
		gbc_mDriveLabel1.gridx = 0;
		gbc_mDriveLabel1.gridy = 5;
		add(mDriveLabel1, gbc_mDriveLabel1);
		
		mDrivetrainSpeed1Box = new JTextField();
		GridBagConstraints gbc_mDrivetrainSpeed1Box = new GridBagConstraints();
		gbc_mDrivetrainSpeed1Box.fill = GridBagConstraints.HORIZONTAL;
		gbc_mDrivetrainSpeed1Box.insets = new Insets(0, 0, 5, 5);
		gbc_mDrivetrainSpeed1Box.gridx = 1;
		gbc_mDrivetrainSpeed1Box.gridy = 5;
		add(mDrivetrainSpeed1Box, gbc_mDrivetrainSpeed1Box);
		
				JLabel lblXSpeed = new JLabel("X Speed");
				
						// ////////////////////////////////////
						GridBagConstraints gbc_lblXSpeed = new GridBagConstraints();
						gbc_lblXSpeed.anchor = GridBagConstraints.EAST;
						gbc_lblXSpeed.fill = GridBagConstraints.VERTICAL;
						gbc_lblXSpeed.insets = new Insets(0, 0, 5, 5);
						gbc_lblXSpeed.gridx = 2;
						gbc_lblXSpeed.gridy = 5;
						add(lblXSpeed, gbc_lblXSpeed);
		mXSpeedBox = new JTextField();
		GridBagConstraints gbc_mXSpeedBox = new GridBagConstraints();
		gbc_mXSpeedBox.fill = GridBagConstraints.BOTH;
		gbc_mXSpeedBox.insets = new Insets(0, 0, 5, 0);
		gbc_mXSpeedBox.gridx = 3;
		gbc_mXSpeedBox.gridy = 5;
		add(mXSpeedBox, gbc_mXSpeedBox);
		mXSpeedBox.setHorizontalAlignment(JTextField.RIGHT);
		
		mDriveLabel2 = new JLabel("Drive[2]");
		GridBagConstraints gbc_mDriveLabel2 = new GridBagConstraints();
		gbc_mDriveLabel2.insets = new Insets(0, 0, 5, 5);
		gbc_mDriveLabel2.anchor = GridBagConstraints.EAST;
		gbc_mDriveLabel2.gridx = 0;
		gbc_mDriveLabel2.gridy = 6;
		add(mDriveLabel2, gbc_mDriveLabel2);
		mDrivetrainSpeed2Box = new JTextField();
		GridBagConstraints gbc_mDrivetrainSpeed2Box = new GridBagConstraints();
		gbc_mDrivetrainSpeed2Box.fill = GridBagConstraints.HORIZONTAL;
		gbc_mDrivetrainSpeed2Box.insets = new Insets(0, 0, 5, 5);
		gbc_mDrivetrainSpeed2Box.gridx = 1;
		gbc_mDrivetrainSpeed2Box.gridy = 6;
		add(mDrivetrainSpeed2Box, gbc_mDrivetrainSpeed2Box);
				JLabel lblYSpeed = new JLabel("Y Speed");
				
						GridBagConstraints gbc_lblYSpeed = new GridBagConstraints();
						gbc_lblYSpeed.anchor = GridBagConstraints.EAST;
						gbc_lblYSpeed.fill = GridBagConstraints.VERTICAL;
						gbc_lblYSpeed.insets = new Insets(0, 0, 5, 5);
						gbc_lblYSpeed.gridx = 2;
						gbc_lblYSpeed.gridy = 6;
						add(lblYSpeed, gbc_lblYSpeed);
		
				mYSpeedBox = new JTextField();
				GridBagConstraints gbc_mYSpeedBox = new GridBagConstraints();
				gbc_mYSpeedBox.fill = GridBagConstraints.BOTH;
				gbc_mYSpeedBox.insets = new Insets(0, 0, 5, 0);
				gbc_mYSpeedBox.gridx = 3;
				gbc_mYSpeedBox.gridy = 6;
				add(mYSpeedBox, gbc_mYSpeedBox);
				mYSpeedBox.setHorizontalAlignment(JTextField.RIGHT);
		
		mDriveLabel3 = new JLabel("Drive[3]");
		GridBagConstraints gbc_mDriveLabel3 = new GridBagConstraints();
		gbc_mDriveLabel3.insets = new Insets(0, 0, 0, 5);
		gbc_mDriveLabel3.anchor = GridBagConstraints.EAST;
		gbc_mDriveLabel3.gridx = 0;
		gbc_mDriveLabel3.gridy = 7;
		add(mDriveLabel3, gbc_mDriveLabel3);
		mDrivetrainSpeed3Box = new JTextField();
		GridBagConstraints gbc_mDrivetrainSpeed3Box = new GridBagConstraints();
		gbc_mDrivetrainSpeed3Box.fill = GridBagConstraints.HORIZONTAL;
		gbc_mDrivetrainSpeed3Box.insets = new Insets(0, 0, 0, 5);
		gbc_mDrivetrainSpeed3Box.gridx = 1;
		gbc_mDrivetrainSpeed3Box.gridy = 7;
		add(mDrivetrainSpeed3Box, gbc_mDrivetrainSpeed3Box);
		
		
		

		mAngle0Field.setHorizontalAlignment(JTextField.RIGHT);
		mAngle1Field.setHorizontalAlignment(JTextField.RIGHT);
		mAngle2Field.setHorizontalAlignment(JTextField.RIGHT);

		mAngle0Field.setText(String.format(sNUMBER_FORMAT_STRING, 12345.6789));
		
				mUseGyroCheckbox = new JCheckBox("Ignore Gryo");
				mUseGyroCheckbox.setSelected(true);
				mUseGyroCheckbox.setHorizontalTextPosition(SwingConstants.LEFT);
				
						GridBagConstraints gbc_chckbxIgnoreGryo = new GridBagConstraints();
						gbc_chckbxIgnoreGryo.anchor = GridBagConstraints.EAST;
						gbc_chckbxIgnoreGryo.fill = GridBagConstraints.VERTICAL;
						gbc_chckbxIgnoreGryo.insets = new Insets(0, 0, 0, 5);
						gbc_chckbxIgnoreGryo.gridx = 2;
						gbc_chckbxIgnoreGryo.gridy = 7;
						add(mUseGyroCheckbox, gbc_chckbxIgnoreGryo);
						
								mAngleBox = new JTextField();
								GridBagConstraints gbc_mAngleBox = new GridBagConstraints();
								gbc_mAngleBox.fill = GridBagConstraints.BOTH;
								gbc_mAngleBox.gridx = 3;
								gbc_mAngleBox.gridy = 7;
								add(mAngleBox, gbc_mAngleBox);
								mAngleBox.setHorizontalAlignment(JTextField.RIGHT);
	}

}
