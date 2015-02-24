package com.snobot.xlib.simple_trajectory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SimplePathDeserializer
{

    public List<SimplePathPoint> deserialize(String aFileName)
    {
        List<SimplePathPoint> outputPath = new ArrayList<SimplePathPoint>();

        try
        {
            BufferedReader br = new BufferedReader(new FileReader(aFileName));

            String line;
            StringTokenizer line_tokenizer;
            boolean first_line = true;

            while ((line = br.readLine()) != null)
            {
                // System.out.println("Line: " + line);

                // Skip headers
                if (first_line)
                {
                    first_line = false;
                }
                else
                {
                    line_tokenizer = new StringTokenizer(line, ",");

                    SimplePathPoint segment = new SimplePathPoint();
                    line_tokenizer.nextToken(); // Skip the first column
                    segment.dt = Double.parseDouble(line_tokenizer.nextToken());
                    segment.angle = Double.parseDouble(line_tokenizer.nextToken());
                    segment.velocity = Double.parseDouble(line_tokenizer.nextToken());
                    segment.acceleration = Double.parseDouble(line_tokenizer.nextToken());

                    outputPath.add(segment);
                }
            }

            br.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return outputPath;
    }
}
