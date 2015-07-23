package com.horaceb.asosfashionbrowser.data;

import com.horaceb.asosfashionbrowser.model.CategoryListing;
import com.horaceb.asosfashionbrowser.model.Description;
import com.horaceb.asosfashionbrowser.request.RestAdapterCreator;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.GET;

/**
 * Wraps the request to the API
 * Created by HoraceBG on 23/07/15.
 */
public class CategoryApiController implements Callback<CategoryListing> {

    private static final String API_URL = "https://dl.dropboxusercontent.com/u/1559445/ASOS/SampleApi";
    private OnResultListener listener;

    @Override
    public void success(CategoryListing categoryListing, Response response) {

    }

    @Override
    public void failure(RetrofitError error) {

    }

    public interface OnResultListener {
        void onSuccess();

        void onError();
    }

    private final RestAdapter adapter;

    public CategoryApiController(final OnResultListener listener) {
        this.listener = listener;
        adapter = new RestAdapterCreator(API_URL).createAdapter();
    }

    public void getCategories(final Description description) {
        final CategoryApi api = adapter.create(CategoryApi.class);
        switch (description) {
            case WOMEN:
                api.getFemaleCategories(this);
                break;
            case MEN:
                api.getMaleCategories(this);
                break;
        }
    }

    private interface CategoryApi {

        @GET("/cats_women.json")
        void getFemaleCategories(Callback<CategoryListing> callback);

        @GET("/cats_men.json")
        void getMaleCategories(Callback<CategoryListing> callback);

    }
}
