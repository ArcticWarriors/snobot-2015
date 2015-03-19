package com.team254.lib.trajectory.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.StringTokenizer;

import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.Segment;
import com.team254.lib.trajectory.Trajectory;

/**
 *
 * @author Jared341
 */
public class TextFileDeserializer implements IPathDeserializer
{

    public Path deserializeFromFile(String aFilename)
    {
        File the_file = new File(aFilename);

        try
        {
            BufferedReader br = new BufferedReader(new FileReader(the_file));

            StringBuilder fileContents = new StringBuilder();

            String line;

            while ((line = br.readLine()) != null)
            {
                fileContents.append(line + "\n");
            }

            br.close();

            return deserialize(fileContents.toString());
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Could not find the path file.  It should be at : '" + the_file.getAbsolutePath() + "'");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return new Path();
    }

    public Path deserialize(String serialized)
    {
        StringTokenizer tokenizer = new StringTokenizer(serialized, "\n");
        System.out.println("Parsing path string...");
        System.out.println("String has " + serialized.length() + " chars");
        System.out.println("Found " + tokenizer.countTokens() + " tokens");

        tokenizer.nextToken(); // Skip header

        Trajectory left = new Trajectory();
        Trajectory right = new Trajectory();

        while (tokenizer.hasMoreElements())
        {
            StringTokenizer line_tokenizer = new StringTokenizer(tokenizer.nextToken(), ",");

            Segment leftSegment = new Segment();

            leftSegment.pos = Double.parseDouble(line_tokenizer.nextToken());
            leftSegment.vel = Double.parseDouble(line_tokenizer.nextToken());
            leftSegment.acc = Double.parseDouble(line_tokenizer.nextToken());
            leftSegment.jerk = Double.parseDouble(line_tokenizer.nextToken());
            leftSegment.heading = Double.parseDouble(line_tokenizer.nextToken());
            leftSegment.dt = Double.parseDouble(line_tokenizer.nextToken());
            leftSegment.x = Double.parseDouble(line_tokenizer.nextToken());
            leftSegment.y = Double.parseDouble(line_tokenizer.nextToken());

            Segment rightSegment = new Segment();
            rightSegment.pos = Double.parseDouble(line_tokenizer.nextToken());
            rightSegment.vel = Double.parseDouble(line_tokenizer.nextToken());
            rightSegment.acc = Double.parseDouble(line_tokenizer.nextToken());
            rightSegment.jerk = Double.parseDouble(line_tokenizer.nextToken());
            rightSegment.heading = Double.parseDouble(line_tokenizer.nextToken());
            rightSegment.dt = Double.parseDouble(line_tokenizer.nextToken());
            rightSegment.x = Double.parseDouble(line_tokenizer.nextToken());
            rightSegment.y = Double.parseDouble(line_tokenizer.nextToken());

            left.add(leftSegment);
            right.add(rightSegment);
        }

        System.out.println("...finished parsing path from string.");
        return new Path("My Path", new Trajectory.Pair(left, right));
    }

}
