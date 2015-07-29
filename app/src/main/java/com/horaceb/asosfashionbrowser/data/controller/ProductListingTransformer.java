package com.horaceb.asosfashionbrowser.data.controller;

import com.horaceb.asosfashionbrowser.api.json.ProductCategoryListings;
import com.horaceb.asosfashionbrowser.api.json.ProductListing;
import com.horaceb.asosfashionbrowser.data.model.Catalogue;
import com.horaceb.asosfashionbrowser.data.model.ItemListing;

import java.util.ArrayList;
import java.util.List;

public class ProductListingTransformer {

    public Catalogue transform(final ProductCategoryListings listings) {
        Catalogue catalogue = new Catalogue();
        catalogue.setDescription(listings.getDescription());
        catalogue.setItemCount(listings.getItemCount());
        catalogue.setItemListings(buildItemListings(listings));
        return catalogue;
    }

    private List<ItemListing> buildItemListings(final ProductCategoryListings listings) {
        final List<ItemListing> itemListings = new ArrayList<>();

        for (ProductListing productListing : listings.getListings()) {
            ItemListing item = new ItemListing();
            item.setImageUrl(productListing.getProductImageUrl().get(0));
            item.setPrice(productListing.getCurrentPrice());
            item.setProductId(productListing.getProductId());
            item.setTitle(productListing.getTitle());
            itemListings.add(item);
        }
        return itemListings;
    }
}
