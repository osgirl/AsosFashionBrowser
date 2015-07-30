package com.horaceb.asosfashionbrowser.data.database;

import com.horaceb.asosfashionbrowser.data.provider.FashionBrowserContract;

public interface DatabaseSchema {

    String DATABASE_NAME = "fashionbrowser.db";

    String TABLE_CATEGORY = "category";

    String CREATE_TABLE_CATEGORY = "CREATE TABLE category (" +
            FashionBrowserContract.CategoryColumns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            FashionBrowserContract.CategoryColumns.CATEGORY_ID + " TEXT," +
            FashionBrowserContract.CategoryColumns.NAME + " TEXT," +
            FashionBrowserContract.CategoryColumns.PRODUCT_COUNT + " INTEGER NOT NULL, " +
            FashionBrowserContract.CategoryColumns.GENDER + " TEXT, " +
            "UNIQUE (" + FashionBrowserContract.CategoryColumns.CATEGORY_ID + ") ON CONFLICT REPLACE" +
            ")";

    String DROP_TABLE_CATEGORY = "DROP TABLE IF EXISTS " + TABLE_CATEGORY;

    String TABLE_SHOPPING_CART = "shopping_cart";

    String CREATE_TABLE_SHOPPING_CART = "CREATE TABLE shopping_cart (" +
            FashionBrowserContract.ShoppingCartColumns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            FashionBrowserContract.ShoppingCartColumns.PRODUCT_ID + " TEXT," +
            FashionBrowserContract.ShoppingCartColumns.PRICE + " TEXT," +
            FashionBrowserContract.ShoppingCartColumns.QUANTITY + " INTEGER NOT NULL, " +
            FashionBrowserContract.ShoppingCartColumns.PRODUCT_NAME + " TEXT)";

    String DROP_TABLE_SHOPPING_CART = "DROP TABLE IF EXISTS " + TABLE_SHOPPING_CART;


    String TABLE_WISHLIST = "wishlist";

    String CREATE_TABLE_WISHLIST = "CREATE TABLE wishlist (" +
            FashionBrowserContract.WishlistColumns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            FashionBrowserContract.WishlistColumns.PRODUCT_ID + " TEXT," +
            FashionBrowserContract.WishlistColumns.PRICE + " TEXT," +
            FashionBrowserContract.WishlistColumns.PRODUCT_NAME + " TEXT, " +
            "UNIQUE (" + FashionBrowserContract.WishlistColumns.PRODUCT_ID + ") ON CONFLICT REPLACE" +
            ")";

    String DROP_TABLE_WISHLIST = "DROP TABLE IF EXISTS " + TABLE_WISHLIST;


}
