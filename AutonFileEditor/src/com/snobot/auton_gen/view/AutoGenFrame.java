package com.snobot.auton_gen.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Properties;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import org.yaml.snakeyaml.Yaml;

import com.snobot.auton_gen.model.CommandConfig;
import com.snobot.auton_gen.model.CommandFileReader;
import com.snobot.auton_gen.model.GenerateCommandReader;
import com.snobot.auton_gen.model.GenerateCommandSkeletons;

public class AutoGenFrame extends JFrame
{
    private static final String sPROPERTIES_FILE = "properties.properties";
    private static final String sAUTON_DIR_PROP = "auton_dir";
    private static final String sAVAILABLE_COMMANDS_FILE_PROP = "available_commands";
    private static final String sACTIVE_FILE_PROP = "active_file";

    private static final String sLEFT_FRAME_X = "frame_x";
    private static final String sLEFT_FRAME_Y = "frame_y";
    private static final String sLEFT_FRAME_WIDTH = "frame_width";
    private static final String sLEFT_FRAME_HEIGHT = "frame_height";

    private String mAvailableCommandsFile;
    private String mAutonFileDirectory;
    private String mGenDirectory;
    private String mGenPackageDirectory;
    private String mCommandReaderTemplate;
    private String mCommandSkeletonTemplate;

    private List<CommandConfig> mAvailableCommands;
    private String mActiveFileName;

    private AutonGenPanel contentPane;

    private Properties mProperties;

    /**
     * Create the frame.
     */
    public AutoGenFrame()
    {

        initComponents();
        loadProperties();

        mAvailableCommands = readAvailableCommands(mAvailableCommandsFile);
        mGenDirectory = "C:\\Users\\PJ\\GitHub\\2015teams\\snobot2015\\AutonFileEditor\\src\\test\\test";
        mGenPackageDirectory = "test.test";
        mCommandReaderTemplate = "templates/java_command_reader.template";
        mCommandSkeletonTemplate = "templates/java_cmd_skelteton.template";

        if (!mActiveFileName.isEmpty())
        {
            openAutonFile(new File(mActiveFileName));
            onFileChanged();
        }

    }

    private void loadProperties()
    {
        mProperties = new Properties();

        File file = new File(sPROPERTIES_FILE);

        if (file.exists())
        {
            try
            {
                mProperties.load(new FileReader(file));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        mAvailableCommandsFile = mProperties.getProperty(sAVAILABLE_COMMANDS_FILE_PROP, "test.yml");
        mAutonFileDirectory = mProperties.getProperty(sAUTON_DIR_PROP, ".");
        mActiveFileName = mProperties.getProperty(sACTIVE_FILE_PROP, "");
        int x = Integer.parseInt(mProperties.getProperty(sLEFT_FRAME_X, "" + 10));
        int y = Integer.parseInt(mProperties.getProperty(sLEFT_FRAME_Y, "" + 10));
        int w = Integer.parseInt(mProperties.getProperty(sLEFT_FRAME_WIDTH, "" + 300));
        int h = Integer.parseInt(mProperties.getProperty(sLEFT_FRAME_HEIGHT, "" + 300));

        setBounds(x, y, w, h);
    }

    private void saveProperties()
    {
        mProperties.setProperty(sAVAILABLE_COMMANDS_FILE_PROP, mAvailableCommandsFile);
        mProperties.setProperty(sAUTON_DIR_PROP, mAutonFileDirectory);
        mProperties.setProperty(sACTIVE_FILE_PROP, mActiveFileName);

        int x = getLocation().x;
        int y = getLocation().y;
        int w = getSize().width;
        int h = getSize().height;

        mProperties.setProperty(sLEFT_FRAME_X, "" + x);
        mProperties.setProperty(sLEFT_FRAME_Y, "" + y);
        mProperties.setProperty(sLEFT_FRAME_WIDTH, "" + w);
        mProperties.setProperty(sLEFT_FRAME_HEIGHT, "" + h);

        try
        {
            mProperties.store(new FileWriter(sPROPERTIES_FILE), "");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private List<CommandConfig> readAvailableCommands(String aFile)
    {
        Yaml yaml = new Yaml();
        List<CommandConfig> configs = null;

        try
        {
            configs = (List<CommandConfig>) yaml.load(new FileInputStream(aFile));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return configs;
    }

    private void dumpAvailableCommands(String aFile)
    {
        Yaml yaml = new Yaml();

        try
        {
            String yamlString = yaml.dump(mAvailableCommands);
            BufferedWriter bw = new BufferedWriter(new FileWriter(aFile));
            bw.write(yamlString);
            bw.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    private void initComponents()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnFile = new JMenu("File");
        menuBar.add(mnFile);

        JMenuItem mntmNew = new JMenuItem("New...");
        mntmNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        mnFile.add(mntmNew);

        JMenuItem mntmOpen = new JMenuItem("Open...");
        mntmOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        mnFile.add(mntmOpen);

        JMenuItem mntmSave = new JMenuItem("Save...");
        mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        mnFile.add(mntmSave);

        JMenuItem mntmSaveAs = new JMenuItem("Save As...");
        mntmSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
        mnFile.add(mntmSaveAs);

        JMenu mnEdit = new JMenu("Edit");
        menuBar.add(mnEdit);

        JMenuItem mntmAddCommandType = new JMenuItem("Add Command Type");
//        mnEdit.add(mntmAddCommandType);

        JMenuItem mntmGenCommandReader = new JMenuItem("Gen Command Reader");
        mnEdit.add(mntmGenCommandReader);

        JMenuItem mntmGenCommandSkeletons = new JMenuItem("Gen Command Skeletons");
        mnEdit.add(mntmGenCommandSkeletons);
        

        mntmNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("New selected");
            }
        });
        mntmSave.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                if (mActiveFileName == null || mActiveFileName.isEmpty())
                {
                    saveAsAutonFile();
                }
                else
                {
                    saveAutonFile(new File(mActiveFileName));
                }
            }
        });

        mntmSaveAs.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                saveAsAutonFile();
            }
        });

        mntmOpen.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                openAutonFile();
            }
        });

        mntmAddCommandType.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                addNewCommandType();
            }
        });

        mntmGenCommandReader.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                new GenerateCommandReader(mCommandReaderTemplate).generateCommand(mGenDirectory, mGenPackageDirectory,
                        mAvailableCommands);

                JOptionPane.showMessageDialog(AutoGenFrame.this, "CommandParser generation succesful!");
            }
        });

        mntmGenCommandSkeletons.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                new GenerateCommandSkeletons(mCommandSkeletonTemplate).generateCommand(mGenDirectory, mGenPackageDirectory,
                        mAvailableCommands);

                JOptionPane.showMessageDialog(AutoGenFrame.this, "Command skeleton generation succesful!");
            }
        });
        
        this.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent arg0)
            {
                saveProperties();
            }
        });

        contentPane = new AutonGenPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
    }

    private void addNewCommandType()
    {
        AddAvailableCommandDialog dialog = new AddAvailableCommandDialog();
        dialog.setModal(true);
        dialog.setVisible(true);

        boolean okHit = dialog.wasOkHit();

        if (okHit)
        {
            CommandConfig config = dialog.getCommandConfig();
            mAvailableCommands.add(config);
            dumpAvailableCommands(mAvailableCommandsFile);
        }

    }

    private void openAutonFile()
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(mAutonFileDirectory));

        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            File file = chooser.getSelectedFile();
            openAutonFile(file);
        }

    }

    private void openAutonFile(File file)
    {

        mAutonFileDirectory = file.getParent();
        mActiveFileName = file.getAbsolutePath();
        onFileChanged();

        CommandFileReader reader = new CommandFileReader(" ", "#");
        reader.setAvailableCommands(mAvailableCommands);
        List<CommandConfig> commands = reader.readFile(file.getAbsolutePath());
        contentPane.setCommands(commands);
    }

    private void saveAsAutonFile()
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(mAutonFileDirectory));

        int returnVal = chooser.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            File file = chooser.getSelectedFile();

            saveAutonFile(file);
        }

    }

    private void saveAutonFile(File aFile)
    {
        List<CommandConfig> commands = contentPane.getCommands();

        try
        {
            BufferedWriter bw = new BufferedWriter(new FileWriter(aFile));

            for (CommandConfig config : commands)
            {
                bw.write(config.toString().trim() + "\n");
            }
            bw.close();
        }
        catch (Exception e)
        {

        }
    }

    private void onFileChanged()
    {
        setTitle("Editing - " + mActiveFileName);
    }

}
