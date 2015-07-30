package com.horaceb.asosfashionbrowser.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.horaceb.asosfashionbrowser.R;
import com.horaceb.asosfashionbrowser.data.controller.ProductDetailsApiController;
import com.horaceb.asosfashionbrowser.data.database.InsertRecordTask;
import com.horaceb.asosfashionbrowser.data.model.ItemDetail;
import com.horaceb.asosfashionbrowser.ui.adapter.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * The detail view for a selected item.
 */
public class ItemDetailFragment extends ContentLoadingFragment<ItemDetail> {

    private static final String KEY_PRODUCT_ID = "key_product_id";
    private static final String KEY_ITEM_DETAILS = "key_item_details";

    @Bind(R.id.item_image_pager)
    ViewPager viewPager;

    @Bind(R.id.product_detail_title)
    TextView titleView;

    @Bind(R.id.product_detail_price)
    TextView priceView;

    @Bind(R.id.product_detail_additional_info)
    TextView additionalInfoView;

    @Bind(R.id.product_detail_care)
    TextView productCareView;

    @Bind(R.id.product_detail_code)
    TextView productCodeView;

    @Bind(R.id.add_to_bag_cta)
    Button buyButton;

    public static ItemDetailFragment newInstance(final long productId) {
        ItemDetailFragment fragment = new ItemDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(KEY_PRODUCT_ID, productId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_item_details;
    }

    @OnClick(R.id.add_to_wishlist_cta)
    void onAddToWishlistClicked() {
        Toast.makeText(getActivity(), "Not implemented", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.add_to_bag_cta)
    void onAddToCartClicked() {
        // Task
        InsertRecordTask task = new InsertRecordTask(getItem(), getActivity().getApplicationContext());
        task.execute();
    }

    @Override
    protected String getContentKey() {
        return KEY_ITEM_DETAILS;
    }

    @Override
    protected void makeRequest() {
        ProductDetailsApiController controller = new ProductDetailsApiController();
        controller.getProductDetails(getArguments().getLong(KEY_PRODUCT_ID), this);
    }

    @Override
    protected void bindViews(ItemDetail item) {
        viewPager.setAdapter(new FragmentPagerAdapter(getFragmentManager(), buildFragments(item.getImageUrls())));
        titleView.setText(item.getName());
        priceView.setText(item.getPrice());
        additionalInfoView.setText(item.getAdditionalInfo());
        productCareView.setText(item.getCareInfo());
        productCodeView.setText(item.getProductId());
        buyButton.setText(getString(R.string.add_to_cart_button, item.getPrice()));
    }

    private List<Fragment> buildFragments(List<String> imageUrls) {
        List<Fragment> fragments = new ArrayList<>();
        for (String imageUrl : imageUrls) {
            fragments.add(ProductImageFragment.newInstance(imageUrl));
        }
        return fragments;
    }

    @Override
    public void onEmpty(String emptyMessage) {

    }
}
