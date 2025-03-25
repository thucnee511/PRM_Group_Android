package vn.edu.fpt.electricalconponents.activities.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import vn.edu.fpt.electricalconponents.MyApplication;
import vn.edu.fpt.electricalconponents.R;
import vn.edu.fpt.electricalconponents.apis.product.ProductApiHandler;

public class DetailActivity extends AppCompatActivity {
    private ImageView img;
    private TextView txtName;
    private TextView txtPrice;
    private TextView txtDescription;
    private Button btnAddToCart;
    private String productId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        Intent intent = getIntent();
        productId = intent.getStringExtra("productId");
        setContentView(R.layout.activity_detail);
        init();
        setData();
    }

    private void init() {
        img = findViewById(R.id.detailAtv_img);
        txtName = findViewById(R.id.detailAtv_txtName);
        txtPrice = findViewById(R.id.detailAtv_txtPrice);
        txtDescription = findViewById(R.id.detailAtv_txtDescription);
        btnAddToCart = findViewById(R.id.detailAtv_btnAddToCart);
        btnAddToCart.setOnClickListener(v -> onBtnAddToCartClick());
    }

    @SuppressLint("CheckResult")
    private void setData() {
        ProductApiHandler
                .getInstance(MyApplication.getInstance())
                .getOneProduct(productId)
                .subscribe(
                        product -> {
                            runOnUiThread(() -> {
                                txtName.setText(product.getName());
                                txtPrice.setText(String.format("%,d VNĐ", product.getPrice()).replaceAll(",", "."));
                                Glide.with(this).load(product.getImageUrl()).into(img);
                                txtDescription.setText(product.getDescription());
                            });
                        }
                );
    }

    private void onBtnAddToCartClick() {

    }
}