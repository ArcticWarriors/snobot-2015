package com.snobot.sd.coordinategui.standalone;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;

import javax.swing.border.LineBorder;

import com.snobot.sd.coordinategui.FieldDrawerPanel;
import com.snobot.sd.coordinategui.FieldDrawerPanel.DrawingType;


public class PropertiesPanel extends JPanel 
{

	private static final long serialVersionUID = -4783871326672648844L;
	
	public JTextField mPointMemoryField;
	public JButton mClearBtn;
	public JButton mDrawAgainBtn;
	public JCheckBox mDrawAutonBox;
	public JComboBox<String> mDrivingModeBox;
	public JTextArea mAutonomousBox;
	public JPanel mColorPanel;

    private Map<DrawingType, String> mReverseDrawingTypeMap;

	/**
	 * Create the panel.
	 */
	public PropertiesPanel() 
	{
	    mReverseDrawingTypeMap = new HashMap<FieldDrawerPanel.DrawingType, String>();
        for(String key : FieldDrawerPanel.sDRAWING_TYPE_MAP.keySet())
        {
            mReverseDrawingTypeMap.put(FieldDrawerPanel.sDRAWING_TYPE_MAP.get(key), key);
        }
        
		initComponents();
	}
	
	private void initComponents()
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{115, 173, 0};
		gridBagLayout.rowHeights = new int[]{18, 14, 14, 14, 14, 14, 14, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblDrawingMode = new JLabel("Drawing Mode");
		GridBagConstraints gbc_lblDrawingMode = new GridBagConstraints();
		gbc_lblDrawingMode.fill = GridBagConstraints.BOTH;
		gbc_lblDrawingMode.insets = new Insets(0, 0, 5, 5);
		gbc_lblDrawingMode.gridx = 0;
		gbc_lblDrawingMode.gridy = 0;
		add(lblDrawingMode, gbc_lblDrawingMode);
		
		mDrivingModeBox = new JComboBox<String>();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.BOTH;
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		add(mDrivingModeBox, gbc_comboBox);
		
		JLabel lblNewLabel = new JLabel("Point Color");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		add(lblNewLabel, gbc_lblNewLabel);
		
		mColorPanel = new JPanel();
		mColorPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.WEST;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		add(mColorPanel, gbc_panel);
		
		JLabel lblPointMemory = new JLabel("Point Memory");
		GridBagConstraints gbc_lblPointMemory = new GridBagConstraints();
		gbc_lblPointMemory.fill = GridBagConstraints.BOTH;
		gbc_lblPointMemory.insets = new Insets(0, 0, 5, 5);
		gbc_lblPointMemory.gridx = 0;
		gbc_lblPointMemory.gridy = 2;
		add(lblPointMemory, gbc_lblPointMemory);
		
		mPointMemoryField = new JTextField();
		mPointMemoryField.setText("100");
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.BOTH;
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 2;
		add(mPointMemoryField, gbc_textField_1);
		mPointMemoryField.setColumns(10);
		
		JLabel lblClear = new JLabel("Clear");
		GridBagConstraints gbc_lblClear = new GridBagConstraints();
		gbc_lblClear.fill = GridBagConstraints.BOTH;
		gbc_lblClear.insets = new Insets(0, 0, 5, 5);
		gbc_lblClear.gridx = 0;
		gbc_lblClear.gridy = 3;
		add(lblClear, gbc_lblClear);
		
		mClearBtn = new JButton("Clear");
		GridBagConstraints gbc_btnClear = new GridBagConstraints();
		gbc_btnClear.fill = GridBagConstraints.BOTH;
		gbc_btnClear.insets = new Insets(0, 0, 5, 0);
		gbc_btnClear.gridx = 1;
		gbc_btnClear.gridy = 3;
		add(mClearBtn, gbc_btnClear);
		
		JLabel lblDrawAutonomous = new JLabel("Draw Autonomous");
		GridBagConstraints gbc_lblDrawAutonomous = new GridBagConstraints();
		gbc_lblDrawAutonomous.fill = GridBagConstraints.BOTH;
		gbc_lblDrawAutonomous.insets = new Insets(0, 0, 5, 5);
		gbc_lblDrawAutonomous.gridx = 0;
		gbc_lblDrawAutonomous.gridy = 4;
		add(lblDrawAutonomous, gbc_lblDrawAutonomous);
		
		mDrawAutonBox = new JCheckBox("");
		mDrawAutonBox.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_checkBox = new GridBagConstraints();
		gbc_checkBox.fill = GridBagConstraints.BOTH;
		gbc_checkBox.insets = new Insets(0, 0, 5, 0);
		gbc_checkBox.gridx = 1;
		gbc_checkBox.gridy = 4;
		add(mDrawAutonBox, gbc_checkBox);
		

		JLabel lblDrawAgain = new JLabel("Draw Again");
		GridBagConstraints gbc_lblDrawAgain = new GridBagConstraints();
		gbc_lblDrawAgain.fill = GridBagConstraints.BOTH;
		gbc_lblDrawAgain.insets = new Insets(0, 0, 5, 5);
		gbc_lblDrawAgain.gridx = 0;
		gbc_lblDrawAgain.gridy = 5;
		add(lblDrawAgain, gbc_lblDrawAgain);
		
		mDrawAgainBtn = new JButton("Draw Again");
		GridBagConstraints gbc_mDrawAgainBtn = new GridBagConstraints();
		gbc_mDrawAgainBtn.fill = GridBagConstraints.BOTH;
		gbc_mDrawAgainBtn.insets = new Insets(0, 0, 5, 0);
		gbc_mDrawAgainBtn.gridx = 1;
		gbc_mDrawAgainBtn.gridy = 5;
		add(mDrawAgainBtn, gbc_mDrawAgainBtn);
		
		JLabel lblAutonomous = new JLabel("Autonomous");
		GridBagConstraints gbc_lblAutonomous = new GridBagConstraints();
		gbc_lblAutonomous.fill = GridBagConstraints.BOTH;
		gbc_lblAutonomous.insets = new Insets(0, 0, 5, 5);
		gbc_lblAutonomous.gridx = 0;
		gbc_lblAutonomous.gridy = 6;
		add(lblAutonomous, gbc_lblAutonomous);
		
		
		mAutonomousBox = new JTextArea();
		mAutonomousBox.setEditable(false);
		GridBagConstraints gbc_mAutonomousBox = new GridBagConstraints();
		gbc_mAutonomousBox.gridheight = 3;
		gbc_mAutonomousBox.fill = GridBagConstraints.BOTH;
		gbc_mAutonomousBox.gridx = 1;
		gbc_mAutonomousBox.gridy = 6;
		add(mAutonomousBox, gbc_mAutonomousBox);
		

		setupDrawingBox();
	}

	private void setupDrawingBox()
	{
	    for(String key : FieldDrawerPanel.sDRAWING_TYPE_MAP.keySet())
	    {
	        mDrivingModeBox.addItem(key);
	    }
	}
	
	public void setDrawingMode(DrawingType aType)
	{
	    mDrivingModeBox.setSelectedItem(mReverseDrawingTypeMap.get(aType));
	}
	

	public DrawingType getDrawingType(String aText)
	{
        for(String key : FieldDrawerPanel.sDRAWING_TYPE_MAP.keySet())
        {
            if(aText.equals(key))
            {
                return FieldDrawerPanel.sDRAWING_TYPE_MAP.get(key);
            }
        }
        
	    System.err.println("Unknown driving mode: " + aText);
		
		return DrawingType.Rainbow;
		
	}

}
