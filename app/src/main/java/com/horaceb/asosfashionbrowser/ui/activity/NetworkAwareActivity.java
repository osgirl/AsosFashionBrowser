package com.horaceb.asosfashionbrowser.ui.activity;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.horaceb.asosfashionbrowser.receiver.ConnectionStateReceiver;

public abstract class NetworkAwareActivity extends AppCompatActivity implements ConnectionStateReceiver.OnConnectionChangedListener {

    private static final String KEY_CONNECTED = "key_connected";

    private ConnectionStateReceiver connectionStateReceiver;
    private boolean connected;

    @Override
    protected void onResume() {
        super.onResume();
        connectionStateReceiver = new ConnectionStateReceiver();
        connectionStateReceiver.addListener(this);
        registerReceiver(connectionStateReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_CONNECTED, connected);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        connected = savedInstanceState.getBoolean(KEY_CONNECTED);
    }

    @Override
    protected void onPause() {
        super.onPause();
        connectionStateReceiver.removeListener(this);
        unregisterReceiver(connectionStateReceiver);
    }

    protected abstract View getConnectionWarningView();

    @Override
    public void onConnectionAvailable() {
        getConnectionWarningView().setVisibility(View.GONE);
        connected = true;
    }

    @Override
    public void onConnectionUnavailable() {
        getConnectionWarningView().setVisibility(View.VISIBLE);
        connected = false;
    }

    protected boolean isConnected() {
        return connected;
    }
}
