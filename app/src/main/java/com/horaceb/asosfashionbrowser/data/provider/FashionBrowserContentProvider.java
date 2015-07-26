package com.horaceb.asosfashionbrowser.data.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import com.horaceb.asosfashionbrowser.data.database.DatabaseSchema;
import com.horaceb.asosfashionbrowser.data.database.FashionBrowserOpenHelper;

/**
 * Created by HoraceBG on 23/07/15.
 */
public class FashionBrowserContentProvider extends ContentProvider {

    public static final int CATEGORY_LIST = 1;
    public static final int CATEGORY_ID = 2;

    private FashionBrowserOpenHelper helper;

    private static final UriMatcher URI_MATCHER;

    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(FashionBrowserContract.AUTHORITY, "category", CATEGORY_LIST);
        URI_MATCHER.addURI(FashionBrowserContract.AUTHORITY, "category/#", CATEGORY_ID);
    }

    @Override
    public boolean onCreate() {
        helper = new FashionBrowserOpenHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase database = helper.getWritableDatabase();
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();

        switch (URI_MATCHER.match(uri)) {
            case CATEGORY_LIST:
                builder.setTables(DatabaseSchema.TABLE_CATEGORY);
                if (TextUtils.isEmpty(sortOrder)) {
                    sortOrder = FashionBrowserContract.Categories.DEFAULT_SORT_ORDER;
                }
                break;
            case CATEGORY_ID:
                builder.setTables(DatabaseSchema.TABLE_CATEGORY);
                builder.appendWhere(FashionBrowserContract.CategoryColumns.ID + " = " + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("This URI is not supported: " + uri);
        }

        Cursor cursor = builder.query(database, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), FashionBrowserContract.CONTENT_URI);
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        switch (URI_MATCHER.match(uri)) {
            case CATEGORY_LIST:
                return FashionBrowserContract.Categories.CONTENT_TYPE;
            case CATEGORY_ID:
                return FashionBrowserContract.Categories.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("This URI is not supported: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase database = helper.getWritableDatabase();

        switch (URI_MATCHER.match(uri)) {
            case CATEGORY_LIST:
                long id = database.insert(DatabaseSchema.TABLE_CATEGORY, null, values);
                getContext().getContentResolver().notifyChange(uri, null);
                return uri.buildUpon().appendEncodedPath(String.valueOf(id)).build();
            default:
                throw new IllegalArgumentException("This URI is not supported for insertion: " + uri);
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase database = helper.getWritableDatabase();
        switch (URI_MATCHER.match(uri)) {
            case CATEGORY_LIST:
                return database.delete(DatabaseSchema.TABLE_CATEGORY, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("This URI is not supported for deletion: " + uri);
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
