package com.snobot.simulator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GenMotorParamsClass
{

    public GenMotorParamsClass() throws IOException
    {
        InputStream resource = getClass().getResourceAsStream("/com/snobot/simulator/MotorParameters.csv");

        BufferedReader br = new BufferedReader(new InputStreamReader(resource));

        String line;

        String output = "package com.snobot.simulator;\n"
                + "public class MotorParameters\n"
                + "{\n"
                + "    public String mMotorName;\n"
                + "    public double mFreeSpeed_RPM;\n"
                + "    public double mStallTorque_NM;\n"
                + "    public double mStallCurrent_Amp;\n"
                + "    public double mFreeCurrent_Amp;\n"
                + "    public double mPower_Watt;\n"
                + "\n"
                + "    public MotorParameters()\n"
                + "    {\n"
                + "        mMotorName = \"\";"
                + "        mFreeSpeed_RPM = mStallTorque_NM = mStallCurrent_Amp = mFreeCurrent_Amp = mPower_Watt = 1;\n"
                + "    }\n"
                + "\n"
                + "    public MotorParameters(String aMotorName, double aFreeSpeed_RPM, double aStallTorque_NM, double aStallCurrent_Amp, double aFreeCurrent_Amp)\n"
                + "    {\n"
                + "        mMotorName = aMotorName;"
                + "        mFreeSpeed_RPM = aFreeSpeed_RPM;\n"
                + "        mStallTorque_NM = aStallTorque_NM;\n"
                + "        mStallCurrent_Amp = aStallCurrent_Amp;\n"
                + "        mFreeCurrent_Amp = aFreeCurrent_Amp;\n"
                + "        mPower_Watt = mFreeSpeed_RPM / 2 * (1 / 60.0 * 2 * Math.PI) * mStallTorque_NM / 2;\n"
                + "    }\n"
                + "\n"
                + "\n"
                + "        @Override\n"
                + "        public String toString()\n"
                + "        {\n"
                + "            return \"MotorParameters [mMotorName=\" + mMotorName + \", mFreeSpeed_RPM=\" + mFreeSpeed_RPM + \", mStallTorque_NM=\" + mStallTorque_NM\n"
                + "                    + \", mStallCurrent_Amp=\" + mStallCurrent_Amp + \", mFreeCurrent_Amp=\" + mFreeCurrent_Amp + \", mPower_Watt=\" + mPower_Watt + \"]\";\n"
                + "        }\n"
                + ""
                + ""
                + ""
                + ""
                + ""
                + ""
                + ""
                + ""
                + "";

        while ((line = br.readLine()) != null)
        {
            String[] parts = line.split(",");

            String full_name = parts[0];
            String class_name = full_name + "_MotorParams";
            class_name = class_name.replaceAll(" ", "_");
            class_name = class_name.replaceAll("-", "_");
            class_name = class_name.replaceAll(":", "_");
            class_name = class_name.replaceAll("@", "_");
            class_name = class_name.replaceAll("/", "_");
            class_name = class_name.replaceAll("\\(", "_");
            class_name = class_name.replaceAll("\\)", "_");

            output += "\n"
                    + "    public static class " + class_name + " extends MotorParameters\n"
                    + "    {\n"
                    + "        public " + class_name + "()\n"
                    + "        {\n"
                    + "            super(\"" + full_name + "\", " + parts[1] + ", " + parts[2] + ", " + parts[3] + ", " + parts[4] + ");\n"
                    + "        }\n"
                    + "    }\n"
                    + "";

        }

        output += "}\n";

        System.out.println(output);
    }

    public static void main(String[] args) throws IOException
    {
        new GenMotorParamsClass();
    }
}
