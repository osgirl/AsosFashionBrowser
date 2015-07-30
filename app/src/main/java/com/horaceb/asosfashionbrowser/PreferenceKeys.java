package com.horaceb.asosfashionbrowser;

/**
 * Keys for use when storing and retrieving SharedPreferences.
 * Created by HoraceBG on 27/07/15.
 */
public abstract class PreferenceKeys {

    /**
     * Which of the category descriptions (WOMEN/ MEN) has been selected
     */
    public static final String SELECTED_CATEGORY_DESCRIPTION = "selected_category_description";

    /**
     * When was the last successful category sync?
     */
    public static final String LAST_CATEGORY_SYNC = "last_category_sync";

    /**
     * Has the user opened the app before?
     */
    public static final String USER_BEEN_HERE = "user_been_here";


}
