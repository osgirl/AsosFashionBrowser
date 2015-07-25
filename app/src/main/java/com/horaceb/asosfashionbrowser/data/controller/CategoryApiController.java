package com.horaceb.asosfashionbrowser.data.controller;

import com.horaceb.asosfashionbrowser.api.AsosApiManager;
import com.horaceb.asosfashionbrowser.api.json.CategoryListing;
import com.horaceb.asosfashionbrowser.api.json.Description;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Wraps the request to the API
 * Created by HoraceBG on 23/07/15.
 */
public class CategoryApiController implements Callback<CategoryListing> {

    private OnResultListener listener;

    @Override
    public void success(CategoryListing categoryListing, Response response) {
        listener.onSuccess();
    }

    @Override
    public void failure(RetrofitError error) {
        listener.onError();
    }

    public interface OnResultListener {
        void onSuccess();

        void onError();
    }

    public CategoryApiController(final OnResultListener listener) {
        this.listener = listener;
    }

    public void getCategories(final Description description) {
        switch (description) {
            case WOMEN:
                AsosApiManager.getApi().getFemaleCategories(this);
                break;
            case MEN:
                AsosApiManager.getApi().getMaleCategories(this);
                break;
        }
    }
}
