package com.horaceb.asosfashionbrowser.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.horaceb.asosfashionbrowser.R;
import com.horaceb.asosfashionbrowser.data.controller.CategoryApiController;
import com.horaceb.asosfashionbrowser.data.controller.ProductApiController;
import com.horaceb.asosfashionbrowser.data.controller.ProductByCategoryApiController;
import com.horaceb.asosfashionbrowser.api.json.Description;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * The activity that houses the navigation drawer
 * and any displayed Fragments.
 * <p/>
 * Created by HoraceBG on 23/07/15.
 */
public class HomeActivity extends AppCompatActivity implements CategoryApiController.OnResultListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.test_button)
    public void onButtonClicked() {
        CategoryApiController controller = new CategoryApiController(this);
        controller.getCategories(Description.WOMEN);

        ProductApiController apiController = new ProductApiController();
        apiController.getProductDetails("catalog01_1000_6930");

        ProductByCategoryApiController productByCategoryApiController = new ProductByCategoryApiController();
        productByCategoryApiController.getProductByCategory("catalog01_1000_6930");
    }

    @Override
    public void onSuccess() {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }
}
