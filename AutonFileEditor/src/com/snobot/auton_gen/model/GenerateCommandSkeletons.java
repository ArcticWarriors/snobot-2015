package com.snobot.auton_gen.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import com.snobot.auton_gen.model.CommandConfig.CommandArgs;

public class GenerateCommandSkeletons
{
    private final String mTemplateFile;

    public GenerateCommandSkeletons(String aTemplate)
    {
        mTemplateFile = aTemplate;
    }

    public void generateCommand(String aDumpDir, String aPackage, List<CommandConfig> aConfig)
    {
        
        for (CommandConfig config : aConfig)
        {
            writeSingleConfig(aDumpDir, aPackage, config);
        }
    }

    private void writeSingleConfig(String outDir, String aPackage, CommandConfig aConfig)
    {
        String class_name = aConfig.getCommandName();
        String var_decl = "";
        String constructor_args = "";
        String var_init = "";

        for (int i = 0; i < aConfig.getCommandArgs().size(); ++i)
        {
            CommandArgs arg = aConfig.getCommandArgs().get(i);
            String sanitized_name = arg.argName.replace(" ", "");

            var_decl += "    private " + arg.type + " m" + sanitized_name + ";\n";
            constructor_args += "" + arg.type + " a" + sanitized_name;
            var_init += "        m" + sanitized_name + " = a" + sanitized_name + ";\n";

            if (i + 1 != aConfig.getCommandArgs().size())
            {
                constructor_args += ", ";
            }
        }

        String template = readTemplate();
        template = template.replace("{{ var_decl }}", var_decl);
        template = template.replace("{{ class_name }}", class_name);
        template = template.replace("{{ constructor_args }}", constructor_args);
        template = template.replace("{{ var_init }}", var_init);
        template = template.replace("{{ package }}", aPackage);

        try
        {
            BufferedWriter bw = new BufferedWriter(new FileWriter(outDir + "/" + class_name + ".java"));

            bw.write(template);
            bw.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private String readTemplate()
    {
        StringBuilder output = new StringBuilder();


        try
        {
            BufferedReader br = new BufferedReader(new FileReader(mTemplateFile));

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
