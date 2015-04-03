package com.snobot.sd2014.coordinategui.standalone;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author PJ
 */
public class CoordinateGui extends JFrame
{
	private static final long serialVersionUID = 1909543741886022995L;

	private static final String sDEFAULT_FILECHOOSER_PATH = ".";

    //private FieldDrawer mFieldDrawer;
    private StandalonePanel mPanel;


    /**
     * Constructor. Sets up the fields
     */
    public CoordinateGui()
    {
        super("Coordinate Display");
        
        mPanel = new StandalonePanel();
        //mFieldDrawer = new FieldDrawer();
        
        setContentPane(mPanel);
        addMenuBar();
        
        repaint();

        mPanel.loadPointFile("xxx.log");
//        mPanel.loadPointFile("SpiralTest.log");
//        mPanel.loadPointFile("Test.log");
        
        setSize(new Dimension(400, 400));
    }

    private void addMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenuItem openItem = new JMenuItem("Open File");
        fileMenu.add(openItem);

        openItem.addActionListener(mOpenFileAction);
    }
    

    private ActionListener mOpenFileAction = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            final JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File(sDEFAULT_FILECHOOSER_PATH));
            chooser.setDialogTitle("Select input file");
            chooser.setAcceptAllFileFilterUsed(false);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
            	mPanel.loadPointFile(chooser.getSelectedFile().toString());
            }
        }
    };
        

    /**
     * Creates and shows the GUI.
     * @param args Ignored
     */
    public static void main(String[] args)
    {
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                CoordinateGui frame = new CoordinateGui();
                frame.pack();
                frame.setVisible(true);
                frame.repaint();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
        
    }
}
