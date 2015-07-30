package com.horaceb.asosfashionbrowser.util;

import com.horaceb.asosfashionbrowser.PreferenceHelper;

import java.util.Date;

import static com.horaceb.asosfashionbrowser.PreferenceKeys.LAST_CATEGORY_SYNC;

/**
 * Simple helper class that helps decide whether or not it is time to sync
 * The locally stored categories
 */
public class SyncHelper {

    private static final int SYNC_INTERVAL_TIME_HOURS = 1;

    public static boolean isTimeToSyncCategories() {
        PreferenceHelper helper = new PreferenceHelper();
        final long lastSyncTime = helper.getLongPreference(LAST_CATEGORY_SYNC);
        final long difference = (new Date().getTime() - lastSyncTime);

        return getHours(difference) >= SYNC_INTERVAL_TIME_HOURS;
    }

    private static int getHours(final long millis) {
        return (int) ((millis / (1000 * 60 * 60)) % 24);
    }
}
