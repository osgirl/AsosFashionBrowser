package com.horaceb.asosfashionbrowser.data;

import com.horaceb.asosfashionbrowser.model.ProductCategoryListings;
import com.horaceb.asosfashionbrowser.model.ProductDetails;
import com.horaceb.asosfashionbrowser.request.RestAdapterCreator;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by HoraceBG on 24/07/15.
 */
public class ProductApiController implements Callback<ProductDetails> {

    private static final String API_URL = "https://dl.dropboxusercontent.com/u/1559445/ASOS/SampleApi";

    private final ProductApi api;

    public ProductApiController() {
        RestAdapter adapter = new RestAdapterCreator(API_URL).createAdapter();
        api = adapter.create(ProductApi.class);
    }

    public void getProductDetails(final String catId) {
        api.getProductDetails(catId, this);
    }

    @Override
    public void success(ProductDetails productDetails, Response response) {
        String s = "";
        String x = "";
    }

    @Override
    public void failure(RetrofitError error) {
        String s = "";
        String x = "";
    }

    private interface ProductApi {

        @GET("/anyproduct_details.json")
        void getProductDetails(@Query("catid") final String catId, Callback<ProductDetails> callback);
    }
}
