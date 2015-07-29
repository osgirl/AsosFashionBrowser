package com.horaceb.asosfashionbrowser.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.horaceb.asosfashionbrowser.R;
import com.horaceb.asosfashionbrowser.data.controller.ProductCatalogueApiController;
import com.horaceb.asosfashionbrowser.data.model.Catalogue;
import com.horaceb.asosfashionbrowser.ui.adapter.CatalogueAdapter;

import butterknife.Bind;
import butterknife.OnItemClick;

/**
 * Displays a collection of items based on the provided identifier.
 * Created by HoraceBG on 28/07/15.
 */
public class ProductCatalogueFragment extends BaseFragment implements ProductCatalogueApiController.OnResponseListener {

    private static final String KEY_CATEGORY_ID = "key_category_id";
    private static final String KEY_CATALOGUE = "key_catalogue";

    public interface OnCatalogueItemSelected {
        void onItemSelected(final long productId);
    }

    @Bind(R.id.item_grid)
    GridView gridView;

    @Bind(R.id.catalogue_name)
    TextView catalogueName;

    @Bind(R.id.catalogue_item_count)
    TextView itemCountView;

    @Bind(R.id.error_message)
    TextView errorTextView;

    @Bind(R.id.catalogue_progress_container)
    View progressContainer;

    private Catalogue catalogue;
    private OnCatalogueItemSelected itemSelectedListener;

    public static ProductCatalogueFragment newInstance(final String categoryId) {
        ProductCatalogueFragment fragment = new ProductCatalogueFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_CATEGORY_ID, categoryId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Register Activity as listener on item selected
        try {
            itemSelectedListener = (OnCatalogueItemSelected) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnCatalogueItemSelected");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            // Request our list of products
            progressContainer.setVisibility(View.VISIBLE);
            ProductCatalogueApiController controller = new ProductCatalogueApiController();
            controller.getProductByCategory(getArguments().getString(KEY_CATEGORY_ID), this);
        } else if (savedInstanceState.containsKey(KEY_CATALOGUE)) {
            // Get the values from the Bundle - no need to make the request again
            catalogue = savedInstanceState.getParcelable(KEY_CATALOGUE);
            populateViews();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (catalogue != null) {
            outState.putParcelable(KEY_CATALOGUE, catalogue);
        }
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_item_collection;
    }


    @OnItemClick(R.id.item_grid)
    void onItemClicked(int position) {
        itemSelectedListener.onItemSelected(gridView.getAdapter().getItemId(position));
    }

    @Override
    public void onSuccess(final Catalogue catalogue) {
        this.catalogue = catalogue;
        progressContainer.setVisibility(View.GONE);
        populateViews();
    }

    private void populateViews() {
        catalogueName.setText(catalogue.getDescription());
        itemCountView.setText(getString(R.string.items_text, catalogue.getItemCount()));
        gridView.setAdapter(new CatalogueAdapter(getActivity(), catalogue.getItemListings()));
    }

    @Override
    public void onError(final String errorMessage) {
        progressContainer.setVisibility(View.GONE);
        errorTextView.setVisibility(View.VISIBLE);
    }

}
