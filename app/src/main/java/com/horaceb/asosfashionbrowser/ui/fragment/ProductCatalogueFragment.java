package com.horaceb.asosfashionbrowser.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
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
public class ProductCatalogueFragment extends ContentLoadingFragment<Catalogue> {

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
    protected int getLayoutResourceId() {
        return R.layout.fragment_item_collection;
    }


    @OnItemClick(R.id.item_grid)
    void onItemClicked(int position) {
        itemSelectedListener.onItemSelected(gridView.getAdapter().getItemId(position));
    }

    @Override
    protected String getContentKey() {
        return KEY_CATALOGUE;
    }

    @Override
    protected void makeRequest() {
        ProductCatalogueApiController controller = new ProductCatalogueApiController();
        controller.getProductByCategory(getArguments().getString(KEY_CATEGORY_ID), this);
    }

    @Override
    protected void bindViews(Catalogue item) {
        catalogueName.setText(item.getDescription());
        itemCountView.setText(getString(R.string.items_text, item.getItemCount()));
        gridView.setAdapter(new CatalogueAdapter(getActivity(), item.getItemListings()));
    }

    @Override
    public void onEmpty(String emptyMessage) {

    }

}
