package com.horaceb.asosfashionbrowser.api;

import android.os.Parcelable;

/**
 * Response listener for requests made to the API.
 * Created by HoraceBG on 29/07/15.
 */
public interface OnResultListener<T extends Parcelable> {
    void onSuccess(final T item);

    void onError(final String errorMessage);

    void onEmpty(final String emptyMessage);
}
