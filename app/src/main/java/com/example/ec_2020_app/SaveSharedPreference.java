package com.example.ec_2020_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveSharedPreference
{
    static final String FULL_NAME= "username";
    static final String MOBILE = "json";
    static final String EMAIL = "email";
    static final String COLLEGE = "college";
    static final String VISITEDLINKS = "links";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }



    public static String getFullName(Context ctx)
    {
        return getSharedPreferences(ctx).getString(FULL_NAME, "");
    }

    public static void setFullName(Context ctx, String name)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(FULL_NAME, name);
        editor.commit();
    }

    public static void setMobile(Context ctx, String mobile)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(MOBILE, mobile);
        editor.commit();
    }

    public static String getMobile(Context ctx)
    {
        return getSharedPreferences(ctx).getString(MOBILE, "");
    }

    public static void setEmail(Context ctx, String email)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(EMAIL, email);
        editor.commit();
    }

    public static String getEmail(Context ctx)
    {
        return getSharedPreferences(ctx).getString(EMAIL, "");
    }

    public static void setCollege(Context ctx, String college)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(COLLEGE, college);
        editor.commit();
    }

    public static String getCollege(Context ctx)
    {
        return getSharedPreferences(ctx).getString(COLLEGE, "");
    }

    public static void setVisitedlinks(Context ctx, String links)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(VISITEDLINKS, links);
        editor.commit();
    }

    public static String getVisitedlinks(Context ctx)
    {
        return getSharedPreferences(ctx).getString(VISITEDLINKS, "");
    }



    public static void clear(Context ctx)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear(); //clear all stored data
        editor.commit();
    }

}
