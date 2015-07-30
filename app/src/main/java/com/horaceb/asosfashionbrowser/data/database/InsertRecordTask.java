package com.horaceb.asosfashionbrowser.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import com.horaceb.asosfashionbrowser.data.model.ItemDetail;
import com.horaceb.asosfashionbrowser.data.provider.FashionBrowserContract;

/**
 * Performs insert query on the content provider in the background
 */
public class InsertRecordTask extends AsyncTask<Void, Void, Boolean> {

    private ItemDetail itemDetail;
    private Context context;

    public InsertRecordTask(final ItemDetail itemDetail, final Context context) {
        this.itemDetail = itemDetail;
        this.context = context;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        Uri shoppingCartUri = FashionBrowserContract.SHOPPING_CART_URI;
        String[] args = new String[]{FashionBrowserContract.ShoppingCartColumns.PRODUCT_ID, FashionBrowserContract.ShoppingCartColumns.QUANTITY};
        // Do we have any records with this product Id?
        Cursor cursor = context.getContentResolver().query(shoppingCartUri, args, FashionBrowserContract.ShoppingCartColumns.PRODUCT_ID + " = ?", new String[]{itemDetail.getProductId()}, null);
        if (cursor != null && cursor.moveToFirst()) {
            // There's a record with the product id already - update instead
            ContentValues values = new ContentValues();
            values.put(FashionBrowserContract.ShoppingCartColumns.QUANTITY, cursor.getCount() + 1);
            context.getContentResolver().update(shoppingCartUri, values, FashionBrowserContract.ShoppingCartColumns.PRODUCT_ID + " = ?", new String[]{itemDetail.getProductId()});
            cursor.close();
        } else {
            // New value - add it
            ContentValues values = new ContentValues();
            values.put(FashionBrowserContract.ShoppingCartColumns.PRODUCT_ID, itemDetail.getProductId());
            values.put(FashionBrowserContract.ShoppingCartColumns.PRODUCT_NAME, itemDetail.getName());
            values.put(FashionBrowserContract.ShoppingCartColumns.PRICE, itemDetail.getPrice());
            values.put(FashionBrowserContract.ShoppingCartColumns.QUANTITY, 1);
            context.getContentResolver().insert(shoppingCartUri, values);
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }


    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        //TODO Send user feedback of completion
    }
}
