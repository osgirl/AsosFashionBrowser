package com.horaceb.asosfashionbrowser.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * An item within a catalogue
 * Created by HoraceBG on 28/07/15.
 */
public class ItemListing implements Parcelable {

    private String imageUrl;
    private int productId;
    private String title;
    private String price;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ItemListing() {

    }

    protected ItemListing(Parcel in) {
        imageUrl = in.readString();
        productId = in.readInt();
        title = in.readString();
        price = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageUrl);
        dest.writeInt(productId);
        dest.writeString(title);
        dest.writeString(price);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ItemListing> CREATOR = new Parcelable.Creator<ItemListing>() {
        @Override
        public ItemListing createFromParcel(Parcel in) {
            return new ItemListing(in);
        }

        @Override
        public ItemListing[] newArray(int size) {
            return new ItemListing[size];
        }
    };
}
