package vn.edu.fpt.electricalconponents.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.fpt.electricalconponents.MyApplication;
import vn.edu.fpt.electricalconponents.R;
import vn.edu.fpt.electricalconponents.adapters.CartAdapter;
import vn.edu.fpt.electricalconponents.apis.cart.CartApiHandler;
import vn.edu.fpt.electricalconponents.models.CartItem;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment implements CartAdapter.CartClickListener {
    private String cartId;
    private Button btnCheckout;
    private List<CartItem> cartItems;
    private CartAdapter adapter;
    private RecyclerView rcv;


    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        init(view);
        loadCartItems();
        return view;
    }

    private void init(View view) {
        cartItems = new ArrayList<>();
        btnCheckout = view.findViewById(R.id.cartFrg_btnCheckout);
        rcv = view.findViewById(R.id.cartFrg_rcv);
        adapter = new CartAdapter(cartItems, this);
        rcv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rcv.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadCartItems();
    }

    @SuppressLint({"CheckResult", "NotifyDataSetChanged"})
    private void loadCartItems() {
        CartApiHandler.getInstance(MyApplication.getInstance())
                .getCart()
                .subscribe(
                        cart -> {
                            System.out.println(cart);
                            CartApiHandler.getInstance(MyApplication.getInstance())
                                    .getCartItems(cart.getId())
                                    .subscribe(response -> {
                                        requireActivity().runOnUiThread(() -> {
                                            cartItems.clear();
                                            System.out.println(response);
                                            cartItems.addAll(response.getData());
                                            adapter.notifyDataSetChanged();
                                        });
                                    });
                            requireActivity().runOnUiThread(() -> {
                                cartId = cart.getId();
                            });

                        }
                        , Throwable::printStackTrace
                );
    }

    @Override
    public void onRemoveClick(String productId) {

    }
}