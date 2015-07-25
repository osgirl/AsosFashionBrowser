package com.horaceb.asosfashionbrowser;

import android.app.Application;

import com.horaceb.asosfashionbrowser.api.AsosApi;
import com.horaceb.asosfashionbrowser.api.AsosApiManager;

public class FashionBrowserApplication extends Application {

    private static AsosApi api;

    @Override
    public void onCreate() {
        super.onCreate();
        api = AsosApiManager.getApi();
    }

    public static AsosApi getApi() {
        return api;
    }
}
