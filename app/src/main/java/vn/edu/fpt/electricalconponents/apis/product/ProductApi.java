package vn.edu.fpt.electricalconponents.apis.product;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import vn.edu.fpt.electricalconponents.apis.ApiItemResponse;
import vn.edu.fpt.electricalconponents.apis.ApiListResponse;
import vn.edu.fpt.electricalconponents.apis.product.dto.CreateEditProductRequest;
import vn.edu.fpt.electricalconponents.models.Product;

public interface ProductApi {
    @GET
    Observable<ApiListResponse<Product>> getAllProducts(int page, int size, String keyword,
                                                        int minPrice, int maxPrice,
                                                        String categoryId, String brandId, int order);

    @GET("{id}")
    Observable<ApiItemResponse<Product>> getOneProduct(@Path("id") String id);

    @POST
    Observable<ApiItemResponse<Product>> createProduct(@Body CreateEditProductRequest request);

    @PUT("{id}")
    Observable<ApiItemResponse<Product>> updateProduct(@Path("id") String id,
                                                       @Body CreateEditProductRequest request);

    @DELETE("{id}")
    Observable<ApiItemResponse<Product>> deleteProduct(@Path("id") String id);
}
