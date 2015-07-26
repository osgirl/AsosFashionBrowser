package com.horaceb.asosfashionbrowser.api;

import retrofit.RestAdapter;

public class AsosApiManager {

    private static final String API_URL = "https://dl.dropboxusercontent.com/u/1559445/ASOS/SampleApi";

    private static AsosApi api = null;

    private AsosApiManager() {
        // Block instance creation
    }

    public static AsosApi getApi() {
        if (api == null) {
            api = new RestAdapter.Builder().setEndpoint(API_URL)
                    .setLogLevel(RestAdapter.LogLevel.BASIC)
                    .build()
                    .create(AsosApi.class);
        }
        return api;
    }
}
