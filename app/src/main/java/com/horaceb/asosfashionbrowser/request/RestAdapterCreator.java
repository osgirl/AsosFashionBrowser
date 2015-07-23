package com.horaceb.asosfashionbrowser.request;

import retrofit.RestAdapter;

public class RestAdapterCreator {

    private final String endpoint;

    public RestAdapterCreator(final String endpoint) {
        this.endpoint = endpoint;
    }

    public RestAdapter createAdapter() {
        return new RestAdapter.Builder()
                .setEndpoint(endpoint)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
    }
}
