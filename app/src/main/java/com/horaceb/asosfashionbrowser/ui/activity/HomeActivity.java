package com.horaceb.asosfashionbrowser.ui.activity;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.horaceb.asosfashionbrowser.R;
import com.horaceb.asosfashionbrowser.data.provider.FashionBrowserContract;
import com.horaceb.asosfashionbrowser.service.CategoryIntentService;
import com.horaceb.asosfashionbrowser.service.CategorySyncReceiver;

import butterknife.Bind;
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
public class HomeActivity extends AppCompatActivity implements CategorySyncReceiver.Receiver, LoaderManager.LoaderCallbacks<Cursor> {

    @Bind(R.id.home_drawer_layout)
    DrawerLayout drawerLayout;

    @Bind(R.id.home_toolbar)
    Toolbar toolbar;

    @Bind(R.id.left_nav_drawer_list)
    ListView navigationDrawerList;

    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        // Load the categories in the background for display in the navigation drawer
        CategorySyncReceiver receiver = new CategorySyncReceiver(new Handler());
        receiver.setReceiver(this);
        Intent intent = new Intent(Intent.ACTION_SYNC, FashionBrowserContract.CATEGORY_URI, this, CategoryIntentService.class);
        intent.putExtra(RECEIVER, receiver);
        startService(intent);

        setSupportActionBar(toolbar);
        setupNavigationDrawer();

        // Prepare to query the provider
        getLoaderManager().initLoader(0, null, this);

    }


    private void setupNavigationDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.setDrawerListener(toggle);
        getDelegate().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getDelegate().getSupportActionBar().setHomeButtonEnabled(true);
        toggle.syncState();

        adapter = new SimpleCursorAdapter(
                this, R.layout.list_item_category,
                null,
                new String[]{FashionBrowserContract.CategoryColumns.NAME},
                new int[]{R.id.category_name},
                0);

        navigationDrawerList.setAdapter(adapter);
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

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri categoryUri = FashionBrowserContract.CATEGORY_URI;
        return new CursorLoader(this, categoryUri, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader loader) {

    }
}
