package com.horaceb.asosfashionbrowser.ui.activity;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.horaceb.asosfashionbrowser.R;
import com.horaceb.asosfashionbrowser.ui.fragment.BaseFragment;

import butterknife.Bind;

/**
 * Displays an image and some text.
 */
public class IntroFragment extends BaseFragment {

    private static final String KEY_IMAGE_RES = "key_image_res";
    private static final String KEY_STRING_RES = "key_string_res";

    @Bind(R.id.intro_image)
    ImageView imageView;

    @Bind(R.id.intro_text)
    TextView textView;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_intro;
    }

    public static IntroFragment newInstance(@DrawableRes final int imageResId, @StringRes final int stringResId) {
        IntroFragment fragment = new IntroFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_IMAGE_RES, imageResId);
        bundle.putInt(KEY_STRING_RES, stringResId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView.setImageResource(getArguments().getInt(KEY_IMAGE_RES));
        textView.setText(getArguments().getInt(KEY_STRING_RES));
    }
}
