package com.horaceb.asosfashionbrowser.data;

import com.horaceb.asosfashionbrowser.model.ProductCategoryListings;
import com.horaceb.asosfashionbrowser.request.RestAdapterCreator;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by HoraceBG on 25/07/15.
 */
public class ProductByCategoryApiController implements Callback<ProductCategoryListings> {

    private static final String API_URL = "https://dl.dropboxusercontent.com/u/1559445/ASOS/SampleApi";

    private final ProductByCategoryApi api;

    public ProductByCategoryApiController() {
        RestAdapter adapter = new RestAdapterCreator(API_URL).createAdapter();
        api = adapter.create(ProductByCategoryApi.class);
    }

    public void getProductByCategory(final String catId) {
        api.getProductsByCategory(catId, this);
    }

    @Override
    public void success(ProductCategoryListings productCategoryListings, Response response) {
    }

    @Override
    public void failure(RetrofitError error) {
    }

    private interface ProductByCategoryApi {

        @GET("/anycat_products.json")
        void getProductsByCategory(@Query("catid") final String catId, Callback<ProductCategoryListings> callback);

    }
}
