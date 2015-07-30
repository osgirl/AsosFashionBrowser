package com.horaceb.asosfashionbrowser.data.provider;

import android.content.ContentResolver;
import android.net.Uri;

/**
 * Simple contract class to help with mapping to tables.
 */
public class FashionBrowserContract {

    public static final String AUTHORITY = "com.horaceb.asosfashionbrowser";
    private static final String CATEGORY_TABLE = "category";
    private static final String SHOPPING_CART_TABLE = "shopping_cart";
    private static final String WISHLIST_TABLE = "wishlist";

    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final Uri CATEGORY_URI = Uri.parse("content://" + AUTHORITY + "/" + CATEGORY_TABLE);

    public interface CategoryColumns {
        String ID = "_id";
        String CATEGORY_ID = "category_id";
        String NAME = "name";
        String PRODUCT_COUNT = "product_count";
        String GENDER = "gender";
    }

    public interface ShoppingCartColumns {
        String ID = "_id";
        String PRODUCT_ID = "product_id";
        String PRODUCT_NAME = "product_name";
        String PRICE = "price";
        String QUANTITY = "quantity";
    }

    public interface WishlistColumns {
        String ID = "_id";
        String PRODUCT_ID = "product_id";
        String PRODUCT_NAME = "product_name";
        String PRICE = "price";
    }

    public static final class Categories {

        /**
         * Mime type for an item directory
         */
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + AUTHORITY + CATEGORY_TABLE;

        /**
         * Mime type for an individual item
         */
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + AUTHORITY + CATEGORY_TABLE;

        public static final String[] PROJECTION_ALL = {
                CATEGORY_TABLE + "." + CategoryColumns.ID,
                CATEGORY_TABLE + "." + CategoryColumns.CATEGORY_ID,
                CATEGORY_TABLE + "." + CategoryColumns.NAME,
                CATEGORY_TABLE + "." + CategoryColumns.PRODUCT_COUNT,
                CATEGORY_TABLE + "." + CategoryColumns.GENDER
        };

        public static final String DEFAULT_SORT_ORDER = CategoryColumns.NAME + " ASC";

    }

    public static class ShoppingCart {

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + AUTHORITY + SHOPPING_CART_TABLE;

        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + AUTHORITY + SHOPPING_CART_TABLE;

        public static final String DEFAULT_SORT_ORDER = ShoppingCartColumns.PRODUCT_NAME + " ASC";
    }

    public static class Wishlist {

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + AUTHORITY + WISHLIST_TABLE;

        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + AUTHORITY + WISHLIST_TABLE;

        public static final String DEFAULT_SORT_ORDER = WishlistColumns.PRODUCT_NAME + " ASC";
    }
}
