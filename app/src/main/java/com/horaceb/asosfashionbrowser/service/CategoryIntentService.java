package com.horaceb.asosfashionbrowser.service;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

import com.horaceb.asosfashionbrowser.data.controller.CategoryApiController;
import com.horaceb.asosfashionbrowser.data.model.Category;
import com.horaceb.asosfashionbrowser.data.provider.FashionBrowserContract;

import java.util.ArrayList;
import java.util.List;

import static com.horaceb.asosfashionbrowser.IntentActions.ERROR;
import static com.horaceb.asosfashionbrowser.IntentActions.IN_PROGRESS;
import static com.horaceb.asosfashionbrowser.IntentActions.RECEIVER;
import static com.horaceb.asosfashionbrowser.IntentActions.SUCCESSFUL;

/**
 * A simple Intent service that handles the initial
 * retrieval of categories in its own thread.
 * <p/>
 * Uses a ContentProvider to save the results for loading later on
 */
public class CategoryIntentService extends IntentService {

    public static final String TAG = CategoryIntentService.class.getSimpleName();

    public CategoryIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        ResultReceiver resultReceiver = intent.getParcelableExtra(RECEIVER);
        List<Category> categories;
        CategoryApiController apiController = new CategoryApiController();
        resultReceiver.send(IN_PROGRESS, Bundle.EMPTY);
        categories = apiController.retrieveAllCategories();
        if (categories != null) {
            List<ContentValues> contentValues = new ArrayList<>();
            for (Category category : categories) {
                ContentValues values = new ContentValues();
                values.put(FashionBrowserContract.CategoryColumns.CATEGORY_ID, category.getCategoryId());
                values.put(FashionBrowserContract.CategoryColumns.NAME, category.getName());
                values.put(FashionBrowserContract.CategoryColumns.PRODUCT_COUNT, category.getProductCount());
                values.put(FashionBrowserContract.CategoryColumns.GENDER, category.getGender());
                contentValues.add(values);

            }
            ContentValues[] valueList = new ContentValues[]{};
            final ContentResolver resolver = getContentResolver();
            resolver.bulkInsert(FashionBrowserContract.CATEGORY_URI, contentValues.toArray(valueList));
            resultReceiver.send(SUCCESSFUL, Bundle.EMPTY);
        } else {
            // Communicate that there has been an error via the receiver
            resultReceiver.send(ERROR, Bundle.EMPTY);
        }
    }
}
