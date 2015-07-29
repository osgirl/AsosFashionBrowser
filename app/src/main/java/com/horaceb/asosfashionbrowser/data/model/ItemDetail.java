package com.horaceb.asosfashionbrowser.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class ItemDetail implements Parcelable {

    private String name;
    private String price;
    private String productId;
    private List<String> imageUrls;
    private String brand;
    private String additionalInfo;
    private String careInfo;
    private String description;

    public ItemDetail() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getCareInfo() {
        return careInfo;
    }

    public void setCareInfo(String careInfo) {
        this.careInfo = careInfo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    protected ItemDetail(Parcel in) {
        name = in.readString();
        price = in.readString();
        productId = in.readString();
        if (in.readByte() == 0x01) {
            imageUrls = new ArrayList<>();
            in.readList(imageUrls, String.class.getClassLoader());
        } else {
            imageUrls = null;
        }
        brand = in.readString();
        additionalInfo = in.readString();
        careInfo = in.readString();
        description = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(price);
        dest.writeString(productId);
        if (imageUrls == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(imageUrls);
        }
        dest.writeString(brand);
        dest.writeString(additionalInfo);
        dest.writeString(careInfo);
        dest.writeString(description);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ItemDetail> CREATOR = new Parcelable.Creator<ItemDetail>() {
        @Override
        public ItemDetail createFromParcel(Parcel in) {
            return new ItemDetail(in);
        }

        @Override
        public ItemDetail[] newArray(int size) {
            return new ItemDetail[size];
        }
    };
}