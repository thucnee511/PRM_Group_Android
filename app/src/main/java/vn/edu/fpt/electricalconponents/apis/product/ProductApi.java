package vn.edu.fpt.electricalconponents.apis.product;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import vn.edu.fpt.electricalconponents.apis.ApiItemResponse;
import vn.edu.fpt.electricalconponents.apis.ApiListResponse;
import vn.edu.fpt.electricalconponents.apis.product.dto.CreateEditProductRequest;
import vn.edu.fpt.electricalconponents.models.Product;

public interface ProductApi {
    @GET("product")
    Observable<ApiListResponse<Product>> getAllProducts(@Query("page") Integer page,
                                                        @Query("size") Integer size,
                                                        @Query("keyword") String keyword,
                                                        @Query("minPrice") Integer minPrice,
                                                        @Query("maxPrice") Integer maxPrice,
                                                        @Query("categoryId") String categoryId,
                                                        @Query("brandId") String brandId,
                                                        @Query("order") Integer order);

    @GET("product/{id}")
    Observable<ApiItemResponse<Product>> getOneProduct(@Path("id") String id);

    @POST("product")
    Observable<ApiItemResponse<Product>> createProduct(@Body CreateEditProductRequest request);

    @PUT("product/{id}")
    Observable<ApiItemResponse<Product>> updateProduct(@Path("id") String id,
                                                       @Body CreateEditProductRequest request);

    @DELETE("product/{id}")
    Observable<ApiItemResponse<Product>> deleteProduct(@Path("id") String id);
}
