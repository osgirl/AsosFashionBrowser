package com.horaceb.asosfashionbrowser.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.horaceb.asosfashionbrowser.PreferenceHelper;
import com.horaceb.asosfashionbrowser.R;
import com.horaceb.asosfashionbrowser.data.provider.FashionBrowserContract;
import com.horaceb.asosfashionbrowser.service.CategoryIntentService;
import com.horaceb.asosfashionbrowser.service.CategorySyncReceiver;
import com.horaceb.asosfashionbrowser.ui.adapter.FragmentPagerAdapter;
import com.horaceb.asosfashionbrowser.ui.fragment.TextFragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.horaceb.asosfashionbrowser.IntentActions.ERROR;
import static com.horaceb.asosfashionbrowser.IntentActions.IN_PROGRESS;
import static com.horaceb.asosfashionbrowser.IntentActions.RECEIVER;
import static com.horaceb.asosfashionbrowser.IntentActions.SUCCESSFUL;
import static com.horaceb.asosfashionbrowser.PreferenceKeys.LAST_CATEGORY_SYNC;
import static com.horaceb.asosfashionbrowser.PreferenceKeys.USER_BEEN_HERE;

/**
 * Displays a simple intro about the app and
 * starts the download of the categories in the background.
 * <p/>
 * If no categories could be downloaded - it will not let the user into the app
 */
public class IntroActivity extends NetworkAwareActivity implements CategorySyncReceiver.Receiver, ViewPager.OnPageChangeListener {

    @Bind(R.id.internet_connection_banner)
    TextView internetWarningView;

    @Bind(R.id.progress_bar)
    ProgressBar progressBar;

    @Bind(R.id.intro_pager)
    ViewPager viewPager;

    @Bind(R.id.intro_next)
    ImageButton nextButton;

    @Bind(R.id.intro_done)
    Button doneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            // Load the categories in the background for display in the navigation drawer
            sendCategoryRequest();
        }
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(), getIntroFragments()));
        viewPager.addOnPageChangeListener(this);
    }

    @NonNull
    private List<Fragment> getIntroFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(IntroFragment.newInstance(R.drawable.ic_logo, R.string.welcome_message));
        fragments.add(TextFragment.newInstance(getString(R.string.message_about_app)));
        fragments.add(TextFragment.newInstance(getString(R.string.final_message)));
        return fragments;
    }

    private void sendCategoryRequest() {
        if (isConnected()) {
            CategorySyncReceiver receiver = new CategorySyncReceiver(new Handler());
            receiver.setReceiver(this);
            Intent intent = new Intent(Intent.ACTION_SYNC, FashionBrowserContract.CATEGORY_URI, this, CategoryIntentService.class);
            intent.putExtra(RECEIVER, receiver);
            startService(intent);
        }
    }

    @OnClick(R.id.intro_next)
    void onNextClicked() {
        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
    }

    @OnClick(R.id.intro_done)
    void onDoneClicked() {
        long lastSync = new PreferenceHelper().getLongPreference(LAST_CATEGORY_SYNC);
        // If we've had a successful sync, go to the home page of the app
        if (lastSync > 0) {
            // Only set the user been here preference when we're happy there's no more to do on this screen
            new PreferenceHelper().setPreference(USER_BEEN_HERE, true);
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        } else {
            // Show message Dialog
            new AlertDialog.Builder(this)
                    .setTitle(R.string.error)
                    .setMessage(R.string.no_sync_error)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            sendCategoryRequest();
                        }
                    }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Leave the app
                    finish();
                }
            }).show();

        }
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        switch (resultCode) {
            case IN_PROGRESS:
                progressBar.setVisibility(View.VISIBLE);
                break;
            case SUCCESSFUL:
                progressBar.setVisibility(View.GONE);
                PreferenceHelper helper = new PreferenceHelper();
                helper.setPreference(LAST_CATEGORY_SYNC, new Date().getTime());
                break;
            case ERROR:
                // Display an error to the user
                progressBar.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == viewPager.getAdapter().getCount() - 1) {
            // We're on the last page, replace the buttons
            nextButton.setVisibility(View.GONE);
            doneButton.setVisibility(View.VISIBLE);
        } else {
            nextButton.setVisibility(View.VISIBLE);
            doneButton.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected View getConnectionWarningView() {
        return internetWarningView;
    }

    @Override
    public void onConnectionAvailable() {
        super.onConnectionAvailable();
        // Automatically try again when connection resumes if we've not already been successful
        long lastSync = new PreferenceHelper().getLongPreference(LAST_CATEGORY_SYNC);
        if (lastSync == 0) {
            sendCategoryRequest();
        }
    }
}
