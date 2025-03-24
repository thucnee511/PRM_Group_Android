package vn.edu.fpt.electricalconponents.apis.order;

import java.time.LocalDateTime;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import vn.edu.fpt.electricalconponents.apis.ApiItemResponse;
import vn.edu.fpt.electricalconponents.apis.ApiListResponse;
import vn.edu.fpt.electricalconponents.models.Order;
import vn.edu.fpt.electricalconponents.models.OrderItem;

public interface OrderApi {
    @GET
    Observable<ApiListResponse<Order>> getAllOrders(int page, int size, String userId,
                                                    LocalDateTime fromDate, LocalDateTime toDate,
                                                    String orderType, String productId);

    @POST
    Observable<ApiItemResponse<Order>> createOrder(String cartId);

    @GET("{id}")
    Observable<ApiItemResponse<OrderItem>> getOneOrder(@Path("id") String id);
}
