package com.horaceb.asosfashionbrowser.data.controller;

import android.support.annotation.NonNull;

import com.horaceb.asosfashionbrowser.api.json.ProductDetails;
import com.horaceb.asosfashionbrowser.data.model.ItemDetail;

public class ProductTransformer {

    public ItemDetail transform(@NonNull final ProductDetails productDetails) {
        ItemDetail detail = new ItemDetail();
        detail.setDescription(productDetails.getDescription());
        detail.setAdditionalInfo(productDetails.getAdditionalInfo());
        detail.setProductId(String.valueOf(productDetails.getProductId()));
        detail.setPrice(productDetails.getCurrentPrice());
        detail.setBrand(productDetails.getBrand());
        detail.setCareInfo(productDetails.getCareInfo());
        detail.setImageUrls(productDetails.getProductImageUrls());
        detail.setName(productDetails.getTitle());
        return detail;
    }
}
