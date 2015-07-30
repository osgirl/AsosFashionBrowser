package com.horaceb.asosfashionbrowser.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.horaceb.asosfashionbrowser.R;
import com.horaceb.asosfashionbrowser.data.provider.FashionBrowserContract;

import butterknife.Bind;

/**
 * Displays a user's shopping cart
 */
public class CartFragment extends BaseFragment {

    @Bind(R.id.shopping_cart_list)
    ListView listView;

    private SimpleCursorAdapter adapter;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_user_cart;
    }

    public static CartFragment newInstance() {
        return new CartFragment();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new SimpleCursorAdapter(
                getActivity(), R.layout.list_item_shopping_cart,
                null,
                new String[]{FashionBrowserContract.ShoppingCartColumns.PRODUCT_NAME, FashionBrowserContract.ShoppingCartColumns.PRICE, FashionBrowserContract.ShoppingCartColumns.PRODUCT_ID},
                new int[]{R.id.cart_name, R.id.cart_price, R.id.cart_code},
                0);
        listView.setAdapter(adapter);
    }
}
