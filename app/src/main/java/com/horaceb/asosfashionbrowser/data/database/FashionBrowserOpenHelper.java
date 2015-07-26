package com.horaceb.asosfashionbrowser.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HoraceBG on 25/07/15.
 */
public class FashionBrowserOpenHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;

    public FashionBrowserOpenHelper(Context context) {
        super(context, DatabaseSchema.DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseSchema.CREATE_TABLE_CATEGORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO: Should have an upgrade strategy
        db.execSQL(DatabaseSchema.DROP_TABLE_CATEGORY);
        onCreate(db);
    }
}
