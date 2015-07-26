package com.horaceb.asosfashionbrowser.api;

import retrofit.ErrorHandler;
import retrofit.RetrofitError;

/**
 * Handles errors for synchronous calls.
 */
public class CustomErrorHandler implements ErrorHandler {

    @Override
    public Throwable handleError(RetrofitError cause) {
        switch (cause.getResponse().getStatus()) {
            case 400:
                throw new RuntimeException("Bad request");
            default:
                throw new RuntimeException("");
        }
    }
}
