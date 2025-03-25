package vn.edu.fpt.electricalconponents.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.edu.fpt.electricalconponents.MyApplication;
import vn.edu.fpt.electricalconponents.R;
import vn.edu.fpt.electricalconponents.apis.product.ProductApiHandler;
import vn.edu.fpt.electricalconponents.models.CartItem;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<CartItem> cartItems;
    private CartClickListener listener;

    public CartAdapter(List<CartItem> cartItems, CartClickListener listener) {
        this.cartItems = cartItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_product, parent, false);
        return new CartViewHolder(view);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        System.out.println(cartItems.get(position));
        holder.itemQuantity.setText(String.valueOf(cartItems.get(position).getQuantity()));
        String productId = cartItems.get(position).getProductId();
        holder.btnRemove.setOnClickListener(v -> listener.onRemoveClick(productId));
        holder.productPrice.setText(
                String.format("%,d VNĐ",
                                cartItems.get(position).getPrice() * cartItems.get(position).getQuantity())
                        .replaceAll(",", ".")
        );
        // Load product image using a library like Glide or Picasso
        ProductApiHandler.getInstance(MyApplication.getInstance())
                .getOneProduct(productId)
                .subscribe(
                        product -> {
                            System.out.println(product);
                            Glide.with(holder.itemView.getContext()).load(product.getImageUrl()).into(holder.productImage);
                            holder.productName.setText(product.getName());
                        }, Throwable::printStackTrace
                );
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static interface CartClickListener {
        void onRemoveClick(String productId);
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView itemQuantity, productName, productPrice;
        Button btnRemove;
        ImageView productImage;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            itemQuantity = itemView.findViewById(R.id.cartProductItm_txtQuantity);
            productName = itemView.findViewById(R.id.cartProductItm_txtName);
            productPrice = itemView.findViewById(R.id.cartProductItm_txtPrice);
            btnRemove = itemView.findViewById(R.id.cartFrg_btnCheckout);
            productImage = itemView.findViewById(R.id.cartProductItm_img);
        }
    }
}
