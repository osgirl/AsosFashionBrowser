package com.horaceb.asosfashionbrowser.ui.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.TextView;

import com.horaceb.asosfashionbrowser.R;
import com.horaceb.asosfashionbrowser.api.OnResultListener;

import butterknife.Bind;

/**
 * Fragment that handles calls to the backend to display some content.
 * Changes the content on display to reflect the result of a an
 * ongoing request.
 * <p/>
 * Persists the returned items in Bundle during configuration changes.
 * T must implement Parcelable.
 * <p/>
 */
public abstract class ContentLoadingFragment<T extends Parcelable> extends BaseFragment implements OnResultListener<T> {

    @Bind(R.id.error_message)
    TextView errorTextView;

    @Bind(R.id.catalogue_progress_container)
    View progressContainer;

    private T item;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            progressContainer.setVisibility(View.VISIBLE);
            makeRequest();
        } else if (savedInstanceState.containsKey(getContentKey())) {
            // Get the values from the Bundle - no need to make the request again
            item = savedInstanceState.getParcelable(getContentKey());
            bindViews(item);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (item != null) {
            outState.putParcelable(getContentKey(), item);
        }
    }

    @Override
    public void onSuccess(final T item) {
        // Fade between progress and content view
        this.item = item;
        progressContainer.setVisibility(View.GONE);
        bindViews(item);
    }

    /**
     * @return The key used to store the content displayed
     * in the Bundle during configuration changes
     */
    protected abstract String getContentKey();

    /**
     * Make the request to the API here.
     */
    protected abstract void makeRequest();

    /**
     * Bind the content from the response to the appropriate views
     *
     * @param item The content to display in the Fragment
     */
    protected abstract void bindViews(T item);

    @Override
    public void onError(String errorMessage) {
        // Fade between progress and error
        progressContainer.setVisibility(View.GONE);
        errorTextView.setVisibility(View.VISIBLE);
        errorTextView.setText(errorMessage);
    }
}
