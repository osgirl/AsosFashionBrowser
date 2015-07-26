package com.horaceb.asosfashionbrowser.service;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/**
 * Helps dispatch the result of an on-going
 * IntentService request to its listeners.
 */
public class CategorySyncReceiver extends ResultReceiver {

    private Receiver receiver;

    public interface Receiver {
        void onReceiveResult(final int resultCode, Bundle resultData);
    }

    /**
     * Create a new ResultReceive to receive results.  Your
     * {@link #onReceiveResult} method will be called from the thread running
     * <var>handler</var> if given, or from an arbitrary thread if null.
     */
    public CategorySyncReceiver(Handler handler) {
        super(handler);
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (receiver != null) {
            receiver.onReceiveResult(resultCode, resultData);
        }
    }
}
