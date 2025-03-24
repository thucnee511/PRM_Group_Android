package vn.edu.fpt.electricalconponents.apis.cart;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import vn.edu.fpt.electricalconponents.apis.ApiItemResponse;
import vn.edu.fpt.electricalconponents.apis.ApiListResponse;
import vn.edu.fpt.electricalconponents.models.Cart;
import vn.edu.fpt.electricalconponents.models.CartItem;

public interface CartApi {
    @GET
    Observable<ApiListResponse<Cart>> getCarts();
    @GET("{id}/items")
    Observable<ApiListResponse<CartItem>> getCartItems(@Path("id") String id, int page, int size);
    @POST("{id}/items")
    Observable<ApiItemResponse<CartItem>> createCart(@Path("id") String id, String productId, int quantity);
    @PUT("{id}/items")
    Observable<ApiItemResponse<CartItem>> updateCart(@Path("id") String id, String productId, int quantity);
    @PUT("{id}/items")
    Observable<ApiItemResponse<CartItem>> deleteCart(@Path("id") String id, String productId);
}
