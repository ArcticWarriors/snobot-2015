package com.snobot.simulator;

public class MotorParameters
{
    public String mMotorName;
    public double mFreeSpeed_RPM;
    public double mStallTorque_NM;
    public double mStallCurrent_Amp;
    public double mFreeCurrent_Amp;
    public double mPower_Watt;

    public MotorParameters()
    {
        mMotorName = "";
        mFreeSpeed_RPM = mStallTorque_NM = mStallCurrent_Amp = mFreeCurrent_Amp = mPower_Watt = 1;
    }

    public MotorParameters(String aMotorName, double aFreeSpeed_RPM, double aStallTorque_NM, double aStallCurrent_Amp, double aFreeCurrent_Amp)
    {
        mMotorName = aMotorName;
        mFreeSpeed_RPM = aFreeSpeed_RPM;
        mStallTorque_NM = aStallTorque_NM;
        mStallCurrent_Amp = aStallCurrent_Amp;
        mFreeCurrent_Amp = aFreeCurrent_Amp;
        mPower_Watt = mFreeSpeed_RPM / 2 * (1 / 60.0 * 2 * Math.PI) * mStallTorque_NM / 2;
    }

    @Override
    public String toString()
    {
        return "MotorParameters [mMotorName=" + mMotorName + ", mFreeSpeed_RPM=" + mFreeSpeed_RPM + ", mStallTorque_NM=" + mStallTorque_NM
                + ", mStallCurrent_Amp=" + mStallCurrent_Amp + ", mFreeCurrent_Amp=" + mFreeCurrent_Amp + ", mPower_Watt=" + mPower_Watt + "]";
    }

    public static class CIM_MotorParams extends MotorParameters
    {
        public CIM_MotorParams()
        {
            super("CIM", 5310, 2.43, 133.00, 2.70);
        }
    }

    public static class MiniCIM_MotorParams extends MotorParameters
    {
        public MiniCIM_MotorParams()
        {
            super("MiniCIM", 6200, 1.40, 86.00, 1.50);
        }
    }

    public static class BAG_Motor_MotorParams extends MotorParameters
    {
        public BAG_Motor_MotorParams()
        {
            super("BAG Motor", 14000, 0.40, 41.00, 1.80);
        }
    }

    public static class BAG_Motor_w__100_1_VersaPlanetary_MotorParams extends MotorParameters
    {
        public BAG_Motor_w__100_1_VersaPlanetary_MotorParams()
        {
            super("BAG Motor w/ 100:1 VersaPlanetary", 133, 38.00, 41.00, 1.80);
        }
    }

    public static class RS_775_18___12V__MotorParams extends MotorParameters
    {
        public RS_775_18___12V__MotorParams()
        {
            super("RS-775-18 (@12V)", 13000, 0.78, 86.67, 1.80);
        }
    }

    public static class RS_775_12_MotorParams extends MotorParameters
    {
        public RS_775_12_MotorParams()
        {
            super("RS-775-12", 7300, 0.4315, 30, 1.1);
        }
    }

    public static class RS_555_MotorParams extends MotorParameters
    {
        public RS_555_MotorParams()
        {
            super("RS-555", 7750, 0.21, 0.40, 15.00);
        }
    }

    public static class RS_550_MotorParams extends MotorParameters
    {
        public RS_550_MotorParams()
        {
            super("RS-550", 19300, 0.49, 85.00, 1.40);
        }
    }

    public static class RS_545_12_MotorParams extends MotorParameters
    {
        public RS_545_12_MotorParams()
        {
            super("RS-545-12", 16800, 0.1667, 21, 0.9);
        }
    }

    public static class RS_540_12_MotorParams extends MotorParameters
    {
        public RS_540_12_MotorParams()
        {
            super("RS-540-12", 16800, 0.2788, 42, 1);
        }
    }

    public static class RS395_12_MotorParams extends MotorParameters
    {
        public RS395_12_MotorParams()
        {
            super("RS395-12", 15500, 0.1178, 15, 0.5);
        }
    }

    public static class RS390_12_MotorParams extends MotorParameters
    {
        public RS390_12_MotorParams()
        {
            super("RS390-12", 12180, 0.1331, 14.5, 0.3);
        }
    }

    public static class AM_PG_Motor__no_gearbox__MotorParams extends MotorParameters
    {
        public AM_PG_Motor__no_gearbox__MotorParams()
        {
            super("AM PG Motor (no gearbox)", 5325, 0.3188, 22, 0.6);
        }
    }

    public static class AM_PG27_Gearmotor_MotorParams extends MotorParameters
    {
        public AM_PG27_Gearmotor_MotorParams()
        {
            super("AM PG27 Gearmotor", 198, 8.5410, 22, 0.6);
        }
    }

    public static class AM_PG71_Gearmotor_MotorParams extends MotorParameters
    {
        public AM_PG71_Gearmotor_MotorParams()
        {
            super("AM PG71 Gearmotor", 75, 22.5000, 22, 0.6);
        }
    }

    public static class AM_PG188_Gearmotor_MotorParams extends MotorParameters
    {
        public AM_PG188_Gearmotor_MotorParams()
        {
            super("AM PG188 Gearmotor", 28, 44.7400, 22, 0.6);
        }
    }

    public static class AM_9015_MotorParams extends MotorParameters
    {
        public AM_9015_MotorParams()
        {
            super("AM 9015", 16000, 0.428, 63.8, 1.2);
        }
    }

    public static class FP_00801_0673_MotorParams extends MotorParameters
    {
        public FP_00801_0673_MotorParams()
        {
            super("FP 00801-0673", 20770, 0.5324, 108.7, 0.82);
        }
    }

    public static class FP_00968_2719_MotorParams extends MotorParameters
    {
        public FP_00968_2719_MotorParams()
        {
            super("FP 00968-2719", 16100, 0.406, 63, 2);
        }
    }

    public static class FP_00968_9015_MotorParams extends MotorParameters
    {
        public FP_00968_9015_MotorParams()
        {
            super("FP 00968-9015", 15600, 0.44982, 70, 1.25);
        }
    }

    public static class FP_00968_9012_MotorParams extends MotorParameters
    {
        public FP_00968_9012_MotorParams()
        {
            super("FP 00968-9012", 15600, 0.42016, 63.5, 1);
        }
    }

    public static class FP_00968_9013_MotorParams extends MotorParameters
    {
        public FP_00968_9013_MotorParams()
        {
            super("FP 00968-9013", 16700, 0.475242, 75, 2);
        }
    }

    public static class Denso_Window_Motor_RH_262100_3030__fc13_103__MotorParams extends MotorParameters
    {
        public Denso_Window_Motor_RH_262100_3030__fc13_103__MotorParams()
        {
            super("Denso Window Motor RH 262100-3030 (fc13-103)", 84, 10.6, 18.6, 1.8);
        }
    }

    public static class Denso_Window_Motor_LH_262100_3040__fc13_102__MotorParams extends MotorParameters
    {
        public Denso_Window_Motor_LH_262100_3040__fc13_102__MotorParams()
        {
            super("Denso Window Motor LH 262100-3040 (fc13-102)", 84, 10.6, 21, 1.8);
        }
    }

    public static class Nippon_Denso_E6DF_14A365_BB_or_E6DF_14A366_BB_MotorParams extends MotorParameters
    {
        public Nippon_Denso_E6DF_14A365_BB_or_E6DF_14A366_BB_MotorParams()
        {
            super("Nippon-Denso E6DF-14A365-BB or E6DF-14A366-BB", 92, 9.194, 20, 0.5);
        }
    }

    public static class Keyang_Window_Motor_RH_MotorParams extends MotorParameters
    {
        public Keyang_Window_Motor_RH_MotorParams()
        {
            super("Keyang Window Motor RH", 70, 13.276, 20, 0.5);
        }
    }

    public static class Snowblower_motor_MotorParams extends MotorParameters
    {
        public Snowblower_motor_MotorParams()
        {
            super("Snowblower motor", 100, 11.298, 24, 5);
        }
    }

    public static class Denso_Throttle_Control_AE235100_0160_MotorParams extends MotorParameters
    {
        public Denso_Throttle_Control_AE235100_0160_MotorParams()
        {
            super("Denso Throttle Control AE235100-0160", 5300, 0.127, 7, 0.4);
        }
    }

    public static class VEX_393_Motor__as_shipped__MotorParams extends MotorParameters
    {
        public VEX_393_Motor__as_shipped__MotorParams()
        {
            super("VEX 393 Motor (as shipped)", 167, 2.78, 8.00, 0.62);
        }
    }

    public static class VEX_393_Motor__high_speed__MotorParams extends MotorParameters
    {
        public VEX_393_Motor__high_speed__MotorParams()
        {
            super("VEX 393 Motor (high speed)", 267, 1.73, 8.00, 0.62);
        }
    }
}
