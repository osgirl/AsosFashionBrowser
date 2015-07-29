package com.horaceb.asosfashionbrowser.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.horaceb.asosfashionbrowser.R;

import butterknife.Bind;

/**
 * Simple Fragment explaining some of the stuff I've done in the app
 */
public class TextFragment extends BaseFragment {

    private static final String KEY_TEXT = "key_text";

    @Bind(R.id.app_detail_text)
    TextView textView;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_text;
    }

    public static TextFragment newInstance(@NonNull final String text) {
        TextFragment fragment = new TextFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TEXT, text);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView.setText(getArguments().getString(KEY_TEXT));
    }
}
