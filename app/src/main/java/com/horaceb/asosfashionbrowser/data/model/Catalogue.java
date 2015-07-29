package com.horaceb.asosfashionbrowser.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Refers to a collection of products and their category description.
 */
public class Catalogue implements Parcelable {

    private String description;
    private List<ItemListing> itemListings;
    private int itemCount;

    public Catalogue() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ItemListing> getItemListings() {
        return itemListings;
    }

    public void setItemListings(List<ItemListing> itemListings) {
        this.itemListings = itemListings;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    protected Catalogue(Parcel in) {
        description = in.readString();
        if (in.readByte() == 0x01) {
            itemListings = new ArrayList<>();
            in.readList(itemListings, ItemListing.class.getClassLoader());
        } else {
            itemListings = null;
        }
        itemCount = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
        if (itemListings == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(itemListings);
        }
        dest.writeInt(itemCount);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Catalogue> CREATOR = new Parcelable.Creator<Catalogue>() {
        @Override
        public Catalogue createFromParcel(Parcel in) {
            return new Catalogue(in);
        }

        @Override
        public Catalogue[] newArray(int size) {
            return new Catalogue[size];
        }
    };
}