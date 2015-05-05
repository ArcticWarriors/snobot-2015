package com.snobot.auton_gen.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import com.snobot.auton_gen.model.CommandConfig.CommandArgs;

public class GenerateCommandReader
{

    public void generateCommand(String aDefaultDumpDir, List<CommandConfig> aConfig)
    {

        String the_package = "test.test";
        String outDir = aDefaultDumpDir;

        String command_name_constants = "";
        String command_cases = "";

        for (int i = 0; i < aConfig.size(); ++i)
        {
            CommandConfig config = aConfig.get(i);

            String constant_name = config.getCommandName().replace(" ", "_").toUpperCase();
            String constant_val = config.getCommandName().replace(" ", "");
            String class_name = config.getCommandName();

            command_name_constants += "   private static final String s" + constant_name + " = \"" + constant_val + "\";\n";

            command_cases += "            case s" + constant_name + ":\n            {\n";
            command_cases += getParsedArgs(config);
            command_cases += "                newCommand = new " + class_name + "(" + getConstructorArguments(config) + ");\n";
            command_cases += "                break;\n            }\n";
        }

        String template = readTemplate();
        template = template.replace("{{ package }}", the_package);
        template = template.replace("{{ command_name_constants }}", command_name_constants);
        template = template.replace("{{ command_cases }}", command_cases);

        try
        {
            BufferedWriter bw = new BufferedWriter(new FileWriter(outDir + "/CommandParser.java"));

            bw.write(template);
            bw.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    private String getParsedArgs(CommandConfig aConfig)
    {
        String output = "";

        for (int i = 0; i < aConfig.getCommandArgs().size(); ++i)
        {
            CommandArgs arg = aConfig.getCommandArgs().get(i);
            String sanitized_name = arg.argName.replace(" ", "");

            String type_name = arg.type;
            output += "                " + arg.type + " l" + sanitized_name + " = ";

            if (type_name.equals("int"))
            {
                output += "Integer.parseInt(args.get(" + (i + 1) + "))";
            }
            else if (type_name.equals("String"))
            {
                output += "args.get(" + (i + 1) + ")";
            }
            else
            {
                type_name = type_name.substring(0, 1).toUpperCase() + type_name.substring(1);
                output += type_name + ".parse" + type_name + "(args.get(" + (i + 1) + "))";
            }
            
            output += ";\n";
        }

        return output;
    }

    private String getConstructorArguments(CommandConfig aConfig)
    {
        String output = "";

        for (int i = 0; i < aConfig.getCommandArgs().size(); ++i)
        {
            CommandArgs arg = aConfig.getCommandArgs().get(i);
            String sanitized_name = arg.argName.replace(" ", "");

            output += "l" + sanitized_name;

            if (i + 1 != aConfig.getCommandArgs().size())
            {
                output += ", ";
            }
        }

        return output;
    }

    private String readTemplate()
    {
        StringBuilder output = new StringBuilder();


        try
        {
            BufferedReader br = new BufferedReader(new FileReader("templates/java_command_reader.template"));

            String line;

            while ((line = br.readLine()) != null)
            {
                output.append(line + "\n");
            }

            br.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return output.toString();
    }

}
