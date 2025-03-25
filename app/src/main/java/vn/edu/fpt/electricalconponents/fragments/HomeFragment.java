package vn.edu.fpt.electricalconponents.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.fpt.electricalconponents.MyApplication;
import vn.edu.fpt.electricalconponents.R;
import vn.edu.fpt.electricalconponents.adapters.ProductsAdapter;
import vn.edu.fpt.electricalconponents.apis.product.ProductApiHandler;
import vn.edu.fpt.electricalconponents.models.Product;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements ProductsAdapter.ProductClickListener {
    private RecyclerView rcv;
    private ProductsAdapter adapter;
    private List<Product> products;
    private EditText edtSearch;
    private Button btnSearch;

    public HomeFragment() {
    }

    public static HomeFragment newInstance(String param1, String param2) {
        return new HomeFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        init(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadProducts();
    }


    private void init(View view) {
        rcv = view.findViewById(R.id.homeFrg_rcv);
        edtSearch = view.findViewById(R.id.homeFrg_edtSearch);
        btnSearch = view.findViewById(R.id.homeFrg_btnSearch);
        products = new ArrayList<>();
        adapter = new ProductsAdapter(products, this);
        rcv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rcv.setAdapter(adapter);
        loadProducts();
    }

    @SuppressLint("CheckResult")
    private void loadProducts() {

        String keyword = edtSearch.getText().toString();
        ProductApiHandler.getInstance(MyApplication.getInstance())
                .getAllProducts(null, null, keyword, null, null, null, null, null)
                .subscribe(response -> {
                    requireActivity().runOnUiThread(() -> {
                        products.clear();
                        System.out.println(response);
                        products.addAll(response.getData());
                        adapter.notifyDataSetChanged();
                    });
                }, error -> {
                    error.printStackTrace();
                });
    }

    @Override
    public void onDetailsClick(String productId) {

    }

    @Override
    public void onAddToCartClick(String productId) {

    }
}