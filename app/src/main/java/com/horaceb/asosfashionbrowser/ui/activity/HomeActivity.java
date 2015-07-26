package com.horaceb.asosfashionbrowser.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.horaceb.asosfashionbrowser.R;
import com.horaceb.asosfashionbrowser.data.provider.FashionBrowserContract;
import com.horaceb.asosfashionbrowser.service.CategoryIntentService;
import com.horaceb.asosfashionbrowser.service.CategorySyncReceiver;

import butterknife.ButterKnife;

import static com.horaceb.asosfashionbrowser.IntentActions.ERROR;
import static com.horaceb.asosfashionbrowser.IntentActions.IN_PROGRESS;
import static com.horaceb.asosfashionbrowser.IntentActions.RECEIVER;
import static com.horaceb.asosfashionbrowser.IntentActions.SUCCESSFUL;

/**
 * The activity that houses the navigation drawer
 * and any displayed Fragments.
 * <p/>
 * Created by HoraceBG on 23/07/15.
 */
public class HomeActivity extends AppCompatActivity implements CategorySyncReceiver.Receiver {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        CategorySyncReceiver receiver = new CategorySyncReceiver(new Handler());
        receiver.setReceiver(this);
        Intent intent = new Intent(Intent.ACTION_SYNC, FashionBrowserContract.CATEGORY_URI, this, CategoryIntentService.class);
        intent.putExtra(RECEIVER, receiver);
        startService(intent);
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        switch (resultCode) {
            case IN_PROGRESS:
                // Display some kind of progress indicator if we're still here
                break;
            case SUCCESSFUL:
                // Remove progress indicator
                break;
            case ERROR:
                // Display an error to the user
                break;
        }
    }
}
