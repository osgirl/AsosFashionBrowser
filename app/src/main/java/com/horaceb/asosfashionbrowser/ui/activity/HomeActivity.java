package com.horaceb.asosfashionbrowser.ui.activity;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Loader;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.horaceb.asosfashionbrowser.PreferenceHelper;
import com.horaceb.asosfashionbrowser.R;
import com.horaceb.asosfashionbrowser.api.json.Description;
import com.horaceb.asosfashionbrowser.data.provider.FashionBrowserContract;
import com.horaceb.asosfashionbrowser.receiver.ConnectionStateReceiver;
import com.horaceb.asosfashionbrowser.service.CategoryIntentService;
import com.horaceb.asosfashionbrowser.service.CategorySyncReceiver;
import com.horaceb.asosfashionbrowser.ui.fragment.ItemDetailFragment;
import com.horaceb.asosfashionbrowser.ui.fragment.ProductCatalogueFragment;
import com.horaceb.asosfashionbrowser.ui.fragment.TextFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

import static com.horaceb.asosfashionbrowser.IntentActions.ERROR;
import static com.horaceb.asosfashionbrowser.IntentActions.IN_PROGRESS;
import static com.horaceb.asosfashionbrowser.IntentActions.RECEIVER;
import static com.horaceb.asosfashionbrowser.IntentActions.SUCCESSFUL;
import static com.horaceb.asosfashionbrowser.PreferenceKeys.SELECTED_CATEGORY_DESCRIPTION;

/**
 * The activity that houses the navigation drawer
 * and any displayed Fragments.
 * <p/>
 * Created by HoraceBG on 23/07/15.
 */
public class HomeActivity extends AppCompatActivity implements CategorySyncReceiver.Receiver, LoaderManager.LoaderCallbacks<Cursor>, TabLayout.OnTabSelectedListener, ProductCatalogueFragment.OnCatalogueItemSelected, ConnectionStateReceiver.OnConnectionChangedListener {


    private static final String ATTACHED_FRAGMENT_TAG = "attached_fragment_tag";

    private static final int LOADER_ID = 0;

    @Bind(R.id.home_drawer_layout)
    DrawerLayout drawerLayout;

    @Bind(R.id.home_toolbar)
    Toolbar toolbar;

    @Bind(R.id.nav_drawer_gender_tabs)
    TabLayout genderCategoryTabs;

    @Bind(R.id.left_nav_drawer_list)
    ListView navigationDrawerList;

    @Bind(R.id.internet_connection_banner)
    TextView internetWarningView;

    private ActionBarDrawerToggle toggle;
    private SimpleCursorAdapter adapter;

    private ConnectionStateReceiver connectionStateReceiver;

    @Override
    protected void onResume() {
        super.onResume();
        connectionStateReceiver = new ConnectionStateReceiver();
        connectionStateReceiver.addListener(this);
        registerReceiver(connectionStateReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        connectionStateReceiver.removeListener(this);
        unregisterReceiver(connectionStateReceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            // attach default Fragment
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, TextFragment.newInstance("This is text")).commit();

            // Load the categories in the background for display in the navigation drawer
            CategorySyncReceiver receiver = new CategorySyncReceiver(new Handler());
            receiver.setReceiver(this);
            Intent intent = new Intent(Intent.ACTION_SYNC, FashionBrowserContract.CATEGORY_URI, this, CategoryIntentService.class);
            intent.putExtra(RECEIVER, receiver);
            startService(intent);

        }

        setSupportActionBar(toolbar);
        setupNavigationDrawer();

        // Query the provider for our categories
        final String selectedDescription = getSelectedCategoryTab();
        getLoaderManager().initLoader(LOADER_ID, buildQueryBundle(selectedDescription), this);
    }

    private String getSelectedCategoryTab() {
        PreferenceHelper preferenceHelper = new PreferenceHelper();
        String description = preferenceHelper.getPreference(SELECTED_CATEGORY_DESCRIPTION);
        if (description == null) {
            // Ladies first...
            description = Description.WOMEN.name();
        }
        return description;
    }


    private void setupNavigationDrawer() {
        buildTabs();
        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.setDrawerListener(toggle);
        getDelegate().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getDelegate().getSupportActionBar().setHomeButtonEnabled(true);
        getDelegate().getSupportActionBar().setDisplayShowTitleEnabled(false);
        toggle.syncState();

        adapter = new SimpleCursorAdapter(
                this, R.layout.list_item_category,
                null,
                new String[]{FashionBrowserContract.CategoryColumns.NAME},
                new int[]{R.id.category_name},
                0);

        navigationDrawerList.setAdapter(adapter);
    }

    private Bundle buildQueryBundle(final String description) {
        Bundle bundle = new Bundle();
        bundle.putString(SELECTED_CATEGORY_DESCRIPTION, description);
        return bundle;
    }

    /**
     * Manually put together some tabs to display above the navigationDrawer.
     * Clicking on these tabs will toggle between the category types
     */
    private void buildTabs() {
        for (Description description : Description.values()) {
            genderCategoryTabs.addTab(buildTab(description));
        }
        genderCategoryTabs.setOnTabSelectedListener(this);
        String description = getSelectedCategoryTab();

        // Pre select tab based on preference
        for (int i = 0; i < genderCategoryTabs.getTabCount(); i++) {
            if (genderCategoryTabs.getTabAt(i) != null) {
                if (genderCategoryTabs.getTabAt(i).getText().equals(description)) {
                    genderCategoryTabs.getTabAt(i).select();
                }
            }
        }
    }

    private TabLayout.Tab buildTab(Description description) {
        return genderCategoryTabs.newTab()
                .setText(description.name())
                .setTag(description)
                .setContentDescription(description.name());
    }

    @OnItemClick(R.id.left_nav_drawer_list)
    void onNavDrawerItemClicked(int position) {
        Cursor cursor = ((SimpleCursorAdapter) navigationDrawerList.getAdapter()).getCursor();
        cursor.moveToPosition(position);
        int index = cursor.getColumnIndex(FashionBrowserContract.CategoryColumns.CATEGORY_ID);
        String categoryId = cursor.getString(index);

        // Switch fragments
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, ProductCatalogueFragment.newInstance(categoryId), ATTACHED_FRAGMENT_TAG).commit();
        navigationDrawerList.setItemChecked(position, true);
        drawerLayout.closeDrawer(Gravity.LEFT);
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

        // Get the categories for that match the selected gender
        String selection = null;
        String[] selectionArgs = null;
        if (args != null && args.containsKey(SELECTED_CATEGORY_DESCRIPTION)) {
            selection = FashionBrowserContract.CategoryColumns.GENDER + " = ?";
            selectionArgs = new String[]{args.getString(SELECTED_CATEGORY_DESCRIPTION)};
        }

        return new CursorLoader(this, categoryUri, null, selection, selectionArgs, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
        // Scroll to the top of the list when we switch between category lists
        navigationDrawerList.setSelectionAfterHeaderView();
    }

    @Override
    public void onLoaderReset(Loader loader) {

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if (tab.getText() != null) {
            String description = tab.getText().toString();
            new PreferenceHelper().setPreference(SELECTED_CATEGORY_DESCRIPTION, description);
            getLoaderManager().restartLoader(LOADER_ID, buildQueryBundle(description), this);
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onItemSelected(long productId) {
        // Switch to the detail fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, ItemDetailFragment.newInstance(productId), ATTACHED_FRAGMENT_TAG).addToBackStack(null).commit();
    }

    @Override
    public void onConnectionAvailable() {
        internetWarningView.setVisibility(View.GONE);
    }

    @Override
    public void onConnectionUnavailable() {
        internetWarningView.setVisibility(View.VISIBLE);
    }
}
