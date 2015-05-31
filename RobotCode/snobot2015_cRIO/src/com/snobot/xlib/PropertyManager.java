package com.snobot.xlib;

import edu.wpi.first.wpilibj.Preferences;

public class PropertyManager
{
    private static boolean sPropertyAdded = false;

    public static class DoubleProperty
    {
        protected String mKey;
        protected double mDefault;

        public DoubleProperty(String aKey, double aDefault)
        {
            mKey = aKey;
            mDefault = aDefault;

            // Force a get-or-save operation. This will guarantee that
            // all the properties are added in the order they get constructed,
            // and that they will all immediately be written into the file
            // rather than have a lazy-instantiation thing going on
            getValue();
        }

    //    @Override
        public double getValue()
        {
            if (Preferences.getInstance().containsKey(mKey))
            {
                return Preferences.getInstance().getDouble(mKey, mDefault);
            }

            sPropertyAdded = true;
            Preferences.getInstance().putDouble(mKey, mDefault);
            return mDefault;
        }

        public String getKey()
        {
            return mKey;
        }

    }

    public static class IntegerProperty
    {
        protected String mKey;
        protected int mDefault;

        public IntegerProperty(String aKey, int aDefault)
        {
            mKey = aKey;
            mDefault = aDefault;

            // Force a get-or-save operation. This will guarantee that
            // all the properties are added in the order they get constructed,
            // and that they will all immediately be written into the file
            // rather than have a lazy-instantiation thing going on
            getValue();
        }

    //    @Override
        public int getValue()
        {
            if (Preferences.getInstance().containsKey(mKey))
            {
                return Preferences.getInstance().getInt(mKey, mDefault);
            }

            sPropertyAdded = true;
            Preferences.getInstance().putInt(mKey, mDefault);
            return mDefault;
        }

        public String getKey()
        {
            return mKey;
        }
    }

    public static class StringProperty
    {
        protected String mKey;
        protected String mDefault;

        public StringProperty(String aKey, String aDefault)
        {
            mKey = aKey;
            mDefault = aDefault;

            // Force a get-or-save operation. This will guarantee that
            // all the properties are added in the order they get constructed,
            // and that they will all immediately be written into the file
            // rather than have a lazy-instantiation thing going on
            getValue();
        }
        
    //    @Override
        public String getValue()
        {
            if (Preferences.getInstance().containsKey(mKey))
            {
                return Preferences.getInstance().getString(mKey, mDefault);
            }

            sPropertyAdded = true;
            Preferences.getInstance().putString(mKey, mDefault);
            return mDefault;
        }

        public String getKey()
        {
            return mKey;
        }
    }

    public static void saveIfUpdated()
    {
        if (sPropertyAdded)
        {
            Preferences.getInstance().save();
            System.out.println("-------------------------------------------");
            System.out.println("Config file updated, saving it");
            System.out.println("-------------------------------------------");
            sPropertyAdded = false;
        }
    }
}
