package com.digitalgeenie.comicbookapp.utils.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;

public class ComicBookAppSharedPreferences {

    // Shared Preferences file name
    private static final String PREF_NAME = "comicbookapp-welcome";
    private static final String IS_FIRST_LAUNCH = "isFirstLaunch";
    private SharedPreferences sharedPreferences;

    public ComicBookAppSharedPreferences(Context context) {
        int PRIVATE_MODE = 0;
        this.sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
    }

    public boolean isFirstLaunch() {
        return sharedPreferences.getBoolean(IS_FIRST_LAUNCH, true);
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_FIRST_LAUNCH, isFirstTime);
        editor.apply();
    }
}
