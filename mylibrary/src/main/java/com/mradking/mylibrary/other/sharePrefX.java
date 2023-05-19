package com.mradking.mylibrary.other;

import android.content.Context;
import android.content.SharedPreferences;

public class sharePrefX {
     private static final String PREF_NAME = "MyAppPreferences";

        // Method to save a String value in shared preferences
        public static void saveString(Context context, String key, String value) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(key, value);
            editor.apply();
        }

        // Method to retrieve a String value from shared preferences
        public static String getString(Context context, String key, String defaultValue) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            return sharedPreferences.getString(key, defaultValue);
        }

        // Method to save an integer value in shared preferences
        public static void saveInt(Context context, String key, int value) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(key, value);
            editor.apply();
        }

        // Method to retrieve an integer value from shared preferences
        public static int getInt(Context context, String key, int defaultValue) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            return sharedPreferences.getInt(key, defaultValue);
        }

        // Method to save a boolean value in shared preferences
        public static void saveBoolean(Context context, String key, boolean value) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(key, value);
            editor.apply();
        }

        // Method to retrieve a boolean value from shared preferences
        public static boolean getBoolean(Context context, String key, boolean defaultValue) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            return sharedPreferences.getBoolean(key, defaultValue);
        }

        // Method to clear all values in shared preferences
        public static void clearPreferences(Context context) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
        }

    public static boolean containsKey(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.contains(key);
    }
    }


