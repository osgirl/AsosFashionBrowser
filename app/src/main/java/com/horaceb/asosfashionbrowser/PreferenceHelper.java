package com.horaceb.asosfashionbrowser;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

/**
 * Simple helper class for the storage and retrieval of
 * preferences.
 * <p/>
 */
public class PreferenceHelper {

    private final SharedPreferences preferences;

    public PreferenceHelper() {
        // This ensures that we always use the application Context
        this.preferences = PreferenceManager.getDefaultSharedPreferences(FashionBrowserApplication.getContext());
    }

    public void setPreference(@NonNull final String key, @NonNull final String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void setPreference(@NonNull final String key, final long value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public void setPreference(@NonNull final String key, final boolean value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public String getPreference(final String key) {
        return preferences.getString(key, null);
    }

    public long getLongPreference(final String key) {
        return preferences.getLong(key, 0);
    }

    public boolean getBooleanPreference(final String key) {
        return preferences.getBoolean(key, false);
    }
}
