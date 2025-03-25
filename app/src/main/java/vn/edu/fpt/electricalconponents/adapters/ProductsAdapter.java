package vn.edu.fpt.electricalconponents.adapters;

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

import vn.edu.fpt.electricalconponents.R;
import vn.edu.fpt.electricalconponents.models.Product;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {
    private final List<Product> products;
    private final ProductClickListener listener;

    public ProductsAdapter(List<Product> products, ProductClickListener listener) {
        this.products = products;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = products.get(position);
        // Bind product data to the view holder
        holder.productName.setText(product.getName());
        holder.productPrice.setText(String.format("%,d VNĐ", product.getPrice()).replaceAll(",", "."));
        // Load product image using a library like Glide or Picasso
        Glide.with(holder.itemView.getContext()).load(product.getImageUrl()).into(holder.productImage);

        holder.productDetails.setOnClickListener(v -> listener.onDetailsClick(product.getId()));
        holder.addToCart.setOnClickListener(v -> listener.onAddToCartClick(product.getId()));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public interface ProductClickListener {
        void onDetailsClick(String productId);

        void onAddToCartClick(String productId);
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName;
        TextView productPrice;
        Button productDetails;
        Button addToCart;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productItm_img);
            productName = itemView.findViewById(R.id.productItm_txtName);
            productPrice = itemView.findViewById(R.id.productItm_txtPrice);
            productDetails = itemView.findViewById(R.id.productItm_btnDetail);
            addToCart = itemView.findViewById(R.id.productItm_btnAddCart);
        }
    }
}
