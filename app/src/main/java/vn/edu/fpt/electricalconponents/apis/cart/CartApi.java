package vn.edu.fpt.electricalconponents.apis.cart;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import vn.edu.fpt.electricalconponents.apis.ApiItemResponse;
import vn.edu.fpt.electricalconponents.apis.ApiListResponse;
import vn.edu.fpt.electricalconponents.models.Cart;
import vn.edu.fpt.electricalconponents.models.CartItem;

public interface CartApi {
    @GET("carts")
    Observable<ApiItemResponse<Cart>> getCarts();

    @GET("carts/{id}/items")
    Observable<ApiListResponse<CartItem>> getCartItems(@Path("id") String id);

    @POST("carts/{id}/items")
    Observable<ApiItemResponse<CartItem>> createCart(@Path("id") String id, @Query("productId") String productId, @Query("quantity") int quantity);

    @PUT("carts/{id}/items")
    Observable<ApiItemResponse<CartItem>> updateCart(@Path("id") String id, @Query("productId") String productId, @Query("quantity") int quantity);

    @DELETE("carts/{id}/items")
    Observable<ApiItemResponse<CartItem>> deleteCart(@Path("id") String id, @Query("productId") String productId);
}
