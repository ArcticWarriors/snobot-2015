package com.snobot.xlib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.snobot.SmartDashboardNames;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public abstract class ACommandParser
{
    private final String mDelimiter;
    private final String mCommentStart;

    protected String mErrorText;
    protected boolean mSuccess;

    public ACommandParser(String aDelimiter, String aCommentStart)
    {
        mErrorText = "";
        mSuccess = false;
        mDelimiter = aDelimiter;
        mCommentStart = aCommentStart;
    }

    protected void addError(String aError)
    {
        // Put the '#' so we can pretend like the error text is a comment
        mErrorText += "# " + aError + "\n";
        mSuccess = false;
    }

    protected void initReading()
    {
        mSuccess = true;
        mErrorText = "";
    }

    protected CommandGroup createNewCommandGroup(String aName)
    {
        return new CommandGroup(aName)
        {
            @Override
            protected void end()
            {
                super.end();
                System.out.println("Command group finished!");
            }
        };
    }

    /**
     * Interprets a line as a Command and adds it to mCommands
     * 
     * @param aLine Line of text
     */
    protected void parseLine(CommandGroup aGroup, String aLine)
    {
        aLine = aLine.trim();
        if (aLine.isEmpty() || aLine.startsWith(mCommentStart))
        {
            return;
        }

        StringTokenizer tokenizer = new StringTokenizer(aLine, mDelimiter);

        List<String> args = new ArrayList<>();

        while (tokenizer.hasMoreElements())
        {
            args.add(tokenizer.nextToken());
        }

        Command newCommand = parseCommand(args);

        if (newCommand == null)
        {
            mSuccess = false;
        }
        else
        {
            aGroup.addSequential(newCommand);
        }
    }

    public CommandGroup readFile(String aFilePath)
    {
        initReading();

        CommandGroup output = createNewCommandGroup(aFilePath);

        SmartDashboard.putString(SmartDashboardNames.sAUTON_FILENAME, aFilePath);

        String fileContents = "";

        try
        {
            BufferedReader br = new BufferedReader(new FileReader(aFilePath));

            String line;
            while ((line = br.readLine()) != null)
            {
                this.parseLine(output, line);
                fileContents += line + "\n";
            }

            br.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        publishParsingResults(fileContents);

        return output;
    }

    public SendableChooser loadAutonFiles(String aDir, String aIgnoreString)
    {
        SendableChooser output = new SendableChooser();
        File autonDr = new File(aDir);

        System.out.println("Reading auton files from directory " + autonDr.getAbsolutePath());
        System.out.println(" Using filter : \"" + aIgnoreString + "\"");

        try
        {
            SnobotAutonCrawler fileProcessor = new SnobotAutonCrawler(aIgnoreString);
            Files.walkFileTree(Paths.get(autonDr.toURI()), fileProcessor);

            for (Path p : fileProcessor.mPaths)
            {
                output.addObject(p.getFileName().toString(), p.toString());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return output;
    }

    private static final class SnobotAutonCrawler extends SimpleFileVisitor<Path>
    {
        private List<Path> mPaths;
        private String mIgnoreString;

        public SnobotAutonCrawler(String aIgnoreString)
        {
            mPaths = new ArrayList<Path>();
            mIgnoreString = aIgnoreString;
        }

        @Override
        public FileVisitResult visitFile(Path aFile, BasicFileAttributes aAttrs) throws IOException
        {
            System.out.println("  Keeping file " + aFile);
            mPaths.add(aFile);

            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path aDir, BasicFileAttributes aAttrs) throws IOException
        {
            Path dirName = aDir.getFileName();
            if (dirName.startsWith(mIgnoreString))
            {
                System.out.println(" Skipping directory: " + dirName);
                return FileVisitResult.SKIP_SUBTREE;
            }
            else
            {
                System.out.println(" Processing directory: " + dirName);
                return FileVisitResult.CONTINUE;
            }
        }
    }

    protected abstract Command parseCommand(List<String> args);

    protected abstract void publishParsingResults(String fileContents);
}
