package com.horaceb.asosfashionbrowser.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Receives notification based on the status of the device connectivity.
 * This approach works pre Lollipop
 */
public class ConnectionStateReceiver extends BroadcastReceiver {

    public interface OnConnectionChangedListener {
        void onConnectionAvailable();

        void onConnectionUnavailable();
    }

    private List<OnConnectionChangedListener> listeners;
    private boolean connectionAvailable;

    public ConnectionStateReceiver() {
        listeners = new ArrayList<>();
    }

    /**
     * Every time the network status changes, inform any listeners
     */
    public void onReceive(Context context, Intent intent) {
        if (intent.getExtras() != null) {
            final ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            final NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

            if (networkInfo == null) {
                connectionAvailable = false;
                notifyListeners();
            } else if (networkInfo.isConnectedOrConnecting()) {
                connectionAvailable = true;
                notifyListeners();
            } else if (intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, Boolean.FALSE)) {
                connectionAvailable = false;
                notifyListeners();
            }
        }
    }

    private void notifyListeners() {
        for (OnConnectionChangedListener listener : listeners) {
            if (connectionAvailable) {
                listener.onConnectionAvailable();
            } else {
                listener.onConnectionUnavailable();
            }
        }
    }

    public void addListener(OnConnectionChangedListener listener) {
        listeners.add(listener);
    }

    public void removeListener(OnConnectionChangedListener listener) {
        listeners.remove(listener);
    }
}

