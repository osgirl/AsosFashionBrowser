package com.horaceb.asosfashionbrowser.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.horaceb.asosfashionbrowser.R;

import butterknife.Bind;

/**
 * Displays a given image
 */
public class ProductImageFragment extends BaseFragment {

    private static final String KEY_IMAGE_URL = "key_image_url";

    @Bind(R.id.product_image)
    ImageView imageView;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_product_image;
    }

    public static ProductImageFragment newInstance(@NonNull final String imageUrl) {
        ProductImageFragment fragment = new ProductImageFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_IMAGE_URL, imageUrl);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Glide.with(this)
                .load(getArguments().getString(KEY_IMAGE_URL))
                .placeholder(R.drawable.ic_image_placeholder)
                .crossFade()
                .into(imageView);
    }
}
