package com.snobot.sd.coordinategui.standalone;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.swing.JColorChooser;
import javax.swing.JPanel;

import com.snobot.sd.coordinategui.Coordinate;
import com.snobot.sd.coordinategui.FieldDrawer;
import com.snobot.sd.coordinategui.FieldDrawerPanel;
import com.snobot.sd.coordinategui.FieldDrawerPanel.DrawingType;
import com.snobot.sd.coordinategui.widget.CoordinateWidget_2014;


public class StandalonePanel extends JPanel 
{
	private static final long serialVersionUID = 3475280226420887056L;
	

	private static final String sPROPERTIES_FILE = "properties.properties";
	private static final String sDRAW_AUTON_KEY = "draw_auton";
	private static final String sPOINT_MEMORY = "point_memory";
	private static final String sPOINT_COLOR = "point_color";
    private static final String sDRAWING_MODE_KEY = "drawing_mode";
    private static final String sUPDATE_RATE_KEY = "update_rate_ms";
	
	
	
    private int mSleepTime;

	private FieldDrawerPanel mFieldDrawer;
	private PropertiesPanel mPropertiesPanel;
	private GuiUpdaterThreadedThing mLivePointDrawer;
	private Properties mProperties = new Properties();

    private List<Coordinate> mLoadedPoints;

	
	public StandalonePanel()
	{
        
		mFieldDrawer = new FieldDrawerPanel(new CoordinateWidget_2014.FieldProperties2014());
		mPropertiesPanel = new PropertiesPanel();
		setLayout(new BorderLayout(0, 0));
		
		add(mFieldDrawer);
		add(mPropertiesPanel, BorderLayout.EAST);
		

        mFieldDrawer.setDrawingType(FieldDrawerPanel.DrawingType.Robot);
        mFieldDrawer.setDrawAuton(false);
        
        readProperties();
        updatePropertiesPanel();
        addListeners();
        saveProperties();
	}
	
	private void updatePropertiesPanel()
	{
        mPropertiesPanel.mDrawAutonBox.setSelected(mFieldDrawer.getDrawAuton());
        mPropertiesPanel.mPointMemoryField.setText("" + mFieldDrawer.getPointMemory());
		mPropertiesPanel.mColorPanel.setBackground(mFieldDrawer.getPointColor());
		
		mPropertiesPanel.setDrawingMode(mFieldDrawer.getDrawingType());
	}

	public FieldDrawer getFieldDrawer() {
		return mFieldDrawer.getFieldDrawer();
	}
	

	public void loadAutonFile(final String aFile)
    {
		String text = AutonPointFileReader.readAutonFile(aFile);
		mPropertiesPanel.mAutonomousBox.setText(text);
        mFieldDrawer.setAutonPoints(AutonPointFileReader.readPointsFromString(text));
    }
    
    public void loadPointFile(final String aFile)
    {        
    	mLoadedPoints = CoordinateFileReader.readFile(aFile);
    	drawPoints();
    }
    
    public void drawPoints()
    {
    	if(mLoadedPoints == null)
    	{
    		return;
    	}
        if(mLivePointDrawer != null)
        {
        	mLivePointDrawer.terminate();
        }
        mLivePointDrawer = null;
        mLivePointDrawer = new GuiUpdaterThreadedThing(mFieldDrawer.getFieldDrawer(), mSleepTime);
        mLivePointDrawer.drawPoints(mLoadedPoints);
    }
	
	private ActionListener mActionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			if(arg0.getSource() == mPropertiesPanel.mClearBtn)
			{
				mFieldDrawer.clearPoints();
			}
			else if(arg0.getSource() == mPropertiesPanel.mDrawAutonBox)
			{
				mFieldDrawer.setDrawAuton(mPropertiesPanel.mDrawAutonBox.isSelected());
			}
			else if(arg0.getSource() == mPropertiesPanel.mDrivingModeBox)
			{
				mFieldDrawer.setDrawingType(mPropertiesPanel.getDrawingType(
						mPropertiesPanel.mDrivingModeBox.getSelectedItem().toString() ));		
			}
			else if(arg0.getSource() == mPropertiesPanel.mDrawAgainBtn)
			{
				drawPoints();
			}
			
			saveProperties();
		}
	};
	
	private KeyListener mKeyListener = new KeyAdapter() {
		
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER)
			{
				if(e.getSource() == mPropertiesPanel.mPointMemoryField)
				{
					System.out.println("Setting point memery");
					mFieldDrawer.setPointMemory( Double.parseDouble(
                                                mPropertiesPanel.mPointMemoryField.getText()) );
				}
				
				saveProperties();
			}
		}
	};

	
	private void readProperties()
	{
		try 
		{
			File f = new File(sPROPERTIES_FILE);
			
			if(f.exists())
			{
				mProperties.load(new FileReader(f));
			}
			else
			{
				System.err.println("Could not read properties file at '" + sPROPERTIES_FILE + "', will use defaults");
			}
			
			mFieldDrawer.setDrawAuton( Boolean.parseBoolean( 
					mProperties.getProperty(sDRAW_AUTON_KEY, "false") ));
			mFieldDrawer.setPointMemory( Double.parseDouble( 
					mProperties.getProperty(sPOINT_MEMORY, "100") ));
			mFieldDrawer.setPointColor( new Color( Integer.parseInt( 
					mProperties.getProperty(sPOINT_COLOR, "" + 0xFF000000) )) );

			String drawingMode = mProperties.getProperty(sDRAWING_MODE_KEY, 
					DrawingType.Rainbow.toString());
			mFieldDrawer.setDrawingType( DrawingType.valueOf(drawingMode) );
			mSleepTime = Integer.parseInt(mProperties.getProperty(sUPDATE_RATE_KEY, "5"));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	private void saveProperties()
	{
		try 
		{
			mProperties.put(sDRAW_AUTON_KEY, "" + mFieldDrawer.getDrawAuton());
			mProperties.put(sPOINT_MEMORY, "" + mFieldDrawer.getPointMemory());
			mProperties.put(sPOINT_COLOR, Integer.toString(mFieldDrawer.getPointColor().getRGB()));
            mProperties.put(sDRAWING_MODE_KEY, mFieldDrawer.getDrawingType().toString());
            mProperties.put(sUPDATE_RATE_KEY, "" + mSleepTime);
			
			mProperties.store(new FileWriter(new File(sPROPERTIES_FILE)), "");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void addListeners()
	{
		mPropertiesPanel.mClearBtn.addActionListener(mActionListener);
		mPropertiesPanel.mDrawAutonBox.addActionListener(mActionListener);
		mPropertiesPanel.mDrivingModeBox.addActionListener(mActionListener);
		mPropertiesPanel.mDrawAgainBtn.addActionListener(mActionListener);
		mPropertiesPanel.mPointMemoryField.addKeyListener(mKeyListener);
		
		mPropertiesPanel.mColorPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				Color newColor = JColorChooser.showDialog(
						StandalonePanel.this, "Choose Point Color", mFieldDrawer.getPointColor());
				
				if(newColor != null)
				{
					mFieldDrawer.setPointColor(newColor);
					mPropertiesPanel.mColorPanel.setBackground(newColor);
				}
				
				saveProperties();
			}
		});		
	}
	
	
}
