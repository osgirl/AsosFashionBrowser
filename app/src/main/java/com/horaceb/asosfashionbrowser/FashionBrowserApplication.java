package com.horaceb.asosfashionbrowser;

import android.app.Application;
import android.content.Context;

import com.horaceb.asosfashionbrowser.api.AsosApi;
import com.horaceb.asosfashionbrowser.api.AsosApiManager;

public class FashionBrowserApplication extends Application {

    private static FashionBrowserApplication applicationContext;
    private static AsosApi api;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
        api = AsosApiManager.getApi();
    }

    public static AsosApi getApi() {
        return api;
    }

    /**
     * Useful helper to ensure the use of the Application rather than
     * the Activity context
     *
     * @return The Application Context
     */
    static Context getContext() {
        return applicationContext;
    }
}
